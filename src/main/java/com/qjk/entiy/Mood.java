package com.qjk.entiy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.type.Alias;

/**
 * 心情实体
 * @author qiejinkai
 *
 */

@Alias("Mood")
public class Mood extends Entiy implements Serializable{

	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 创建时间
	 */
	private long createTime;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 类型
	 */
	private int moodType = 0;

	/**
	 * 顺序 默认0不置顶
	 */
	private int sequence = 0;

	/**
	 * 状态 默认0显示
	 */
	private int moodState = 0;

	/**
	 * 属性
	 */
	private Map<String,Object> attributes =new HashMap<String, Object>();

	/**
	 * 地点
	 */
	private String location;





	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public int getMoodState() {
		return moodState;
	}


	public void setMoodState(int moodState) {
		this.moodState = moodState;
	}

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

	public int getMoodType() {
		return moodType;
	}


	public void setMoodType(int moodType) {
		this.moodType = moodType;
	}


	public int getSequence() {
		return sequence;
	}


	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Mood() {

	}





	public Mood(String title, long createTime, String content, int moodType,
				int sequence, int moodState, Map<String, Object> attributes,
				String location) {
		super();
		this.title = title;
		this.createTime = createTime;
		this.content = content;
		this.moodType = moodType;
		this.sequence = sequence;
		this.moodState = moodState;
		this.attributes = attributes;
		this.location = location;
	}


	public Mood(int moodId) {
		this.objectId = moodId;
	}


	@Override
	public String toString() {
		return this.getObjectId()+" , "+ this.title+" , "+this.content;
	}


	public Map<String, Object> getAttributes() {
		return attributes;
	}


	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}





}
