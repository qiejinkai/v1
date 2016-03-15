package com.qjk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadAction {

	@RequestMapping("/upload")
	public String upload(){
		
		return "upload/upload";
	}
	
	@RequestMapping("/fileUpload")
	public String fileUpload(@RequestParam(value = "file", required = true) MultipartFile file){
		
		String contentType=file.getContentType();
		
		System.out.println(contentType);
		
		
		return "success";
	}
}
