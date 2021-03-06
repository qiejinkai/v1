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
	 * 文件上传
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
		// 根目录路径，可以指定绝对路径，比如 /var/www/attached/
		String savePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "attached/";
		System.out.println(savePath);
		// 根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
		String saveUrl = request.getContextPath() + "/attached/";
		// 文件保存本地目录路径
		// String savePath = "d:/attached/";
		// 文件保存目录URL
		// String saveUrl = request.getContextPath() + savePath.substring(2);
		if (!ServletFileUpload.isMultipartContent(request)) {
			return getError("请选择文件。");
		}
		// 检查目录
		/*
		 * File uploadDir = new File(savePath); if(!uploadDir.isDirectory()){
		 * return getError("上传目录不存在。"); } //检查目录写权限 if(!uploadDir.canWrite()){
		 * return getError("上传目录没有写权限。"); }
		 */
		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}

		if (!extMap.containsKey(dirName)) {
			return getError("目录名不正确。");
		}
		// 创建文件夹
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

		// 最大文件大小
		long maxSize = 2<<21;

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List<?> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException fe) {
			return getError("接收文件异常。");
		}
		Iterator<?> itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			String fileName = item.getName();
			if (!item.isFormField()) {
				// 检查文件大小
				if (item.getSize() > maxSize) {
					return getError("上传文件大小超过限制。");
				}
				// 检查扩展名
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					return getError("上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(dirName) + "格式。");
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
				} catch (Exception e) {
					return getError("上传文件失败。");
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
	 * 文件空间
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
		// 根目录路径，可以指定绝对路径
		//String rootPath = "d:/attached/";
		// 根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
		//String rootUrl = request.getContextPath() + rootPath.substring(2);
		// 图片扩展名
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
		// 根据path参数，设置各路径和URL
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
		// 排序形式，name or size or type
		String order = request.getParameter("order") != null ? request
				.getParameter("order").toLowerCase() : "name";
		// 不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			return "Access is not allowed.";
		}
		// 最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			return "Parameter is not valid.";
		}
		// 目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if (!currentPathFile.isDirectory()) {
			return "Directory does not exist.";
		}
		// 遍历目录取的文件信息
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
		// 根据suffix设置响应ContentType
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
			// 判断suffix
			// 图片请求可以在此显示一个默认图片
			// file显示文件已损坏等错误提示...
			LOGGER.error("读取文件失败", e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					LOGGER.error("读取文件失败", e);
				}

				if (os != null) {
					try {
						os.close();
					} catch (IOException e) {
						LOGGER.error("读取文件失败", e);
					}
				}
			}
		}

	}

}
