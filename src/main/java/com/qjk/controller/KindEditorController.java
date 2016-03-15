package com.qjk.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KindEditorController {
	
	
	private static Map<String, String> extMap = new HashMap<String, String>();
	
	static	{
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file",
				"doc,docx,xls,xlsx,ppt,htm,html,xml,txt,zip,rar,gz,bz2");
	}

	/**
	 * �ļ��ϴ�
	 * 
	 * @param request
	 *            {@link HttpServletRequest}
	 * @param response
	 *            {@link HttpServletResponse}
	 * @return json response
	 */
	@RequestMapping(value = "/kindEditorFileUpload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> fileUpload(HttpServletRequest request,
			HttpServletResponse response) {
		// ��Ŀ¼·��������ָ������·�������� /var/www/attached/
		String savePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "attached/";
		System.out.println(savePath);
		// ��Ŀ¼URL������ָ������·�������� http://www.yoursite.com/attached/
		String saveUrl = request.getContextPath() + "/attached/";
		// �ļ����汾��Ŀ¼·��
		// String savePath = "d:/attached/";
		// �ļ�����Ŀ¼URL
		// String saveUrl = request.getContextPath() + savePath.substring(2);
		if (!ServletFileUpload.isMultipartContent(request)) {
			return getError("��ѡ���ļ���");
		}
		// ���Ŀ¼
		/*
		 * File uploadDir = new File(savePath); if(!uploadDir.isDirectory()){
		 * return getError("�ϴ�Ŀ¼�����ڡ�"); } //���Ŀ¼дȨ�� if(!uploadDir.canWrite()){
		 * return getError("�ϴ�Ŀ¼û��дȨ�ޡ�"); }
		 */
		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}

		if (!extMap.containsKey(dirName)) {
			return getError("Ŀ¼������ȷ��");
		}
		// �����ļ���
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		// ����ļ���С
		long maxSize = 2<<21;

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List<?> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException fe) {
			return getError("�����ļ��쳣��");
		}
		Iterator<?> itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			String fileName = item.getName();
			if (!item.isFormField()) {
				// ����ļ���С
				if (item.getSize() > maxSize) {
					return getError("�ϴ��ļ���С�������ơ�");
				}
				// �����չ��
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					return getError("�ϴ��ļ���չ���ǲ��������չ����\nֻ����"
							+ extMap.get(dirName) + "��ʽ��");
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
				} catch (Exception e) {
					return getError("�ϴ��ļ�ʧ�ܡ�");
				}

				Map<String, Object> succMap = new HashMap<String, Object>();
				succMap.put("error", 0);
				succMap.put("url", saveUrl + newFileName);
				return succMap;
			}
		}

		return null;
	}

	private Map<String, Object> getError(String errorMsg) {
		Map<String, Object> errorMap = new HashMap<String, Object>();
		errorMap.put("error", 1);
		errorMap.put("message", errorMsg);
		return errorMap;
	}

	/**
	 * �ļ��ռ�
	 * 
	 * @param request
	 *            {@link HttpServletRequest}
	 * @param response
	 *            {@link HttpServletResponse}
	 * @return json
	 */
	@RequestMapping(value = "/kidnEditorFileManager")
	@ResponseBody
	public Object fileManager(HttpServletRequest request,
			HttpServletResponse response) {
		
		String rootPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "attached/";
		
		String rootUrl = request.getContextPath() + "/attached/";
		// ��Ŀ¼·��������ָ������·��
		//String rootPath = "d:/attached/";
		// ��Ŀ¼URL������ָ������·�������� http://www.yoursite.com/attached/
		//String rootUrl = request.getContextPath() + rootPath.substring(2);
		// ͼƬ��չ��
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };
		String dirName = request.getParameter("dir");
		
		if (dirName != null) {
			if (!Arrays.<String> asList(
					new String[] { "image", "flash", "media", "file" })
					.contains(dirName)) {
				return "Invalid Directory name.";
			}
			rootPath += dirName + "/";
			rootUrl += dirName + "/";
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		// ����path���������ø�·����URL
		String path = request.getParameter("path") != null ? request
				.getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0,
					currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0,
					str.lastIndexOf("/") + 1) : "";
		}
		// ������ʽ��name or size or type
		String order = request.getParameter("order") != null ? request
				.getParameter("order").toLowerCase() : "name";
		// ������ʹ��..�ƶ�����һ��Ŀ¼
		if (path.indexOf("..") >= 0) {
			return "Access is not allowed.";
		}
		// ���һ���ַ�����/
		if (!"".equals(path) && !path.endsWith("/")) {
			return "Parameter is not valid.";
		}
		// Ŀ¼�����ڻ���Ŀ¼
		File currentPathFile = new File(currentPath);
		if (!currentPathFile.isDirectory()) {
			return "Directory does not exist.";
		}
		// ����Ŀ¼ȡ���ļ���Ϣ
		List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
		if (currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if (file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if (file.isFile()) {
					String fileExt = fileName.substring(
							fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String> asList(fileTypes)
							.contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime",
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file
								.lastModified()));
				fileList.add(hash);
			}
		}
		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);
		return result;
	}

	private class NameComparator implements Comparator<Map<String, Object>> {
		public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filename"))
						.compareTo((String) hashB.get("filename"));
			}
		}
	}

	private class SizeComparator implements Comparator<Map<String, Object>> {
		public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long) hashA.get("filesize")) > ((Long) hashB
						.get("filesize"))) {
					return 1;
				} else if (((Long) hashA.get("filesize")) < ((Long) hashB
						.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	private class TypeComparator implements Comparator<Map<String, Object>> {
		public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filetype"))
						.compareTo((String) hashB.get("filetype"));
			}
		}
	}

	private static final Logger LOGGER = Logger
			.getLogger(KindEditorController.class);

	@RequestMapping("/attached/{fileType}/{uploadDate}/{fileName}.{suffix}")
	public void attached(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String fileType,
			@PathVariable String uploadDate, @PathVariable String suffix,
			@PathVariable String fileName) {
		// ����suffix������ӦContentType
		 response.setContentType("text/html; charset=UTF-8");

		InputStream is = null;
		
		OutputStream os = null;
		try {

			String rootUrl = request.getContextPath() + "/attached/";

			File file = new File(rootUrl + fileType + "/" + uploadDate + "/"
					+ fileName + "." + suffix);
			is = new FileInputStream(file);
			byte[] buffer = new byte[is.available()];
			is.read(buffer);

			os = new BufferedOutputStream(response.getOutputStream());
			os.write(buffer);
			os.flush();
		} catch (Exception e) {
			// �ж�suffix
			// ͼƬ��������ڴ���ʾһ��Ĭ��ͼƬ
			// file��ʾ�ļ����𻵵ȴ�����ʾ...
			LOGGER.error("��ȡ�ļ�ʧ��", e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					LOGGER.error("��ȡ�ļ�ʧ��", e);
				}

				if (os != null) {
					try {
						os.close();
					} catch (IOException e) {
						LOGGER.error("��ȡ�ļ�ʧ��", e);
					}
				}
			}
		}

	}

}
