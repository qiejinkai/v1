package com.qjk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AticleController {

	
	@RequestMapping("/aticle")
	public String welcome(HttpServletRequest request){
		HttpSession session=request.getSession();
		
		session.setAttribute("locate", "aticle");
		
		return "aticle/aticle";
	}
}
