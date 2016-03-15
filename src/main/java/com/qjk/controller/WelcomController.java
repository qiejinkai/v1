package com.qjk.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qjk.entiy.Mood;
import com.qjk.service.MoodService;
import com.qjk.util.JsonUtil;

@Controller
public class WelcomController {

	@Resource
	MoodService moodService;
	
	@RequestMapping("/welcome")
	public String welcome(HttpServletRequest request){
		HttpSession session=request.getSession();
		
		session.setAttribute("locate", "welcome");
		
		return "welcome";
	}
	
	@RequestMapping("/loadMood")
	public void loadMood(HttpServletResponse response) throws IOException{
		
			Mood mood=moodService.loadMood();			
			
			if(mood != null){
				
				response.getWriter().print(JsonUtil.toJson(mood));
			}
			
	}
	

	@RequestMapping("/doMoodFavor")
	public void doMoodFavor(HttpServletRequest request,HttpServletResponse response) throws IOException{
			
			String moodId=request.getParameter("objectId");
			
			if(null != moodId){
				
				Mood mood=moodService.doMoodFavor(Long.parseLong(moodId));			
				
				if(mood != null){
					
					response.getWriter().print(JsonUtil.toJson(mood));
				}
			}
			
	}
	
	
}
