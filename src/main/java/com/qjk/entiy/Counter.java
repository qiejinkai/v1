package com.qjk.entiy;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 计数器
 * @author qiejinkai
 *
 */
@Alias("Counter")
public class Counter extends Entiy implements Serializable{

	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 计数
	 */
	private long count = 0;

	/**
	 * 被统计的实体ID
	 */
	private long entiyId;

	/**
	 * 别名
	 */
	private String alias;

	/**
	 * 修改日期
	 */
	private long updateTime;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getEntiyId() {
		return entiyId;
	}

	public void setEntiyId(long entiyId) {
		this.entiyId = entiyId;
	}

	public String getAlias() {
		return alias;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Counter(long count, long entiyId, String alias, long updateTime) {
		super();
		this.count = count;
		this.entiyId = entiyId;
		this.alias = alias;
		this.updateTime = updateTime;
	}
	public Counter() {
		// TODO Auto-generated constructor stub
	}




}
