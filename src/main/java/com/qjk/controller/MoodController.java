package com.qjk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qjk.entiy.Mood;
import com.qjk.exception.NullException;
import com.qjk.service.MoodService;
import com.qjk.util.DateUtil;
import com.qjk.util.JsonUtil;
import com.qjk.util.Value;


@Controller
public class MoodController {


	@Resource
	MoodService moodService;
	
	@RequestMapping("/mood")
	public String welcome(HttpServletRequest request){
		HttpSession session=request.getSession();
		
		session.setAttribute("locate", "mood");
		
		return "mood/mood";
	}
	
	@RequestMapping("/loadMoodList")
	public void loadMoodList(HttpServletResponse response,HttpServletRequest request) throws IOException{

		int offSet=Integer.parseInt((String)request.getParameter("offSet"));
		int pageSize=Integer.parseInt((String)request.getParameter("pageSize"));
		
		PrintWriter pw =null ;
		try{
			pw= response.getWriter();
			
			List<Mood> moodList=moodService.loadMoodList(offSet,pageSize);			
			
			if(moodList != null && 0 != moodList.size()){
				
				pw.print(JsonUtil.toJson(moodList.toArray()));
			}else{
				pw.print(JsonUtil.toJson(""));
			}
			
		}finally{
			
			if(pw != null){
				pw.flush();
				pw.close();
			}
		}
		
	}
	
	@RequestMapping("/addMood")
	public void addMood(HttpServletResponse response,HttpServletRequest request) throws NullException{
		PrintWriter pw =null ;
		try{
			request.setCharacterEncoding("utf-8");
			
			pw= response.getWriter();
			
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			String location=request.getParameter("location");
			String sequence=request.getParameter("sequence");
			String moodState=request.getParameter("moodState");
			String moodType=request.getParameter("moodType");
			
			Value.nullValueValidate("title",title,"content",content,"location",location,"sequence",sequence,"moodState",moodState,"moodType",moodType);
			
			Mood mood = new Mood(title, DateUtil.now(), content, Integer.parseInt(moodType), Integer.parseInt(sequence), Integer.parseInt(moodState), new HashMap<String,Object>(), location);
	
			moodService.addMood(mood);	
			
			pw.print("success");
			
		}catch(NullException e){
			
			pw.print(e.getMessage());
			
		}catch (Exception e) {
			
			pw.print(e.getMessage());
			
		}finally{
			
			if(pw != null){
				pw.flush();
				pw.close();
			}
		}
		
	}
	
	@RequestMapping("/lockMood")
	public void lockMood(HttpServletResponse response,HttpServletRequest request) throws NullException{
		PrintWriter pw =null ;
		try{
			request.setCharacterEncoding("utf-8");
			
			pw= response.getWriter();
			
			String id=request.getParameter("objectId");
			
			if(Value.isEmpty(id)){
				
				throw new NullException("心情ID为空");
			}
			
			int moodId = Value.intValueOf(id, 0);
			
			if(moodId == 0){
				
				throw new NullException("请输入正确的心情ID");
			}
	
			Mood mood= moodService.doLockMood(moodId);	
			
			pw.print(mood.getMoodState());
			
		}catch(NullException e){
			
			pw.print(e.getMessage());
			
		}catch (Exception e) {
			
			pw.print(e.getMessage());
			
		}finally{
			
			if(pw != null){
				pw.flush();
				pw.close();
			}
		}
		
	}
	
	@RequestMapping("/deleteMood")
	public void deleteMood(HttpServletResponse response,HttpServletRequest request) throws NullException{
		PrintWriter pw =null ;
		try{
			request.setCharacterEncoding("utf-8");
			
			pw= response.getWriter();
			
			String id=request.getParameter("objectId");
			
			if(Value.isEmpty(id)){
				
				throw new NullException("心情ID为空");
			}
			
			int moodId = Value.intValueOf(id, 0);
			
			if(moodId == 0){
				
				throw new NullException("请输入正确的心情ID");
			}
	
			moodService.deleteMood(new Mood(moodId));
			
			pw.print("success");
			
		}catch(NullException e){
			
			pw.print(e.getMessage());
			
		}catch (Exception e) {
			
			pw.print(e.getMessage());
			
		}finally{
			
			if(pw != null){
				pw.flush();
				pw.close();
			}
		}
		
	}
	
	
	@RequestMapping("/stickyMood")
	public void stickyMood(HttpServletResponse response,HttpServletRequest request) throws NullException{
		PrintWriter pw =null ;
		try{
			request.setCharacterEncoding("utf-8");
			
			pw= response.getWriter();
			
			String id=request.getParameter("objectId");
			
			if(Value.isEmpty(id)){
				
				throw new NullException("心情ID为空");
			}
			
			int moodId = Value.intValueOf(id, 0);
			
			if(moodId == 0){
				
				throw new NullException("请输入正确的心情ID");
			}
	
			Mood mood= moodService.dostickyMood(moodId);	
			
			pw.print(mood.getSequence());
			
		}catch(NullException e){
			
			pw.print(e.getMessage());
			
		}catch (Exception e) {
			
			pw.print(e.getMessage());
			
		}finally{
			
			if(pw != null){
				pw.flush();
				pw.close();
			}
		}
		
	}
}
