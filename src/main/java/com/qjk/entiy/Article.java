package com.qjk.entiy;

import java.util.HashMap;
import java.util.Map;

public class Article extends Entiy{

	/**
	 * ??????
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ??????
	 */
	private String title;
	
	/**
	 * ??????????
	 */
	private long createTime;

	/**
	 * ??????
	 */
	private String content;
	
	/**
	 * ??????
	 */
	private String nutshell;
	
	/**
	 * ??????
	 */
	private String classify ;
	
	/**
	 * ???? ????0???????
	 */
	private int sequence = 0;
	
	/**
	 * ?? ????0???? 
	 */
	private int status = 0;
	
	/**
	 * ??????
	 */
	private Map<String,Object> attributes =new HashMap<String, Object>();
	
	/**
	 * ????
	 */
	private String location;
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNutshell() {
		return nutshell;
	}

	public void setNutshell(String nutshell) {
		this.nutshell = nutshell;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
	
}
