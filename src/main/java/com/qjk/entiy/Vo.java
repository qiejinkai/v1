package com.qjk.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * ????????
 * 
 * @author qiejinkai
 *
 */
public class Vo<T> implements Serializable{
	
	/**
	 *????
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ??????????
	 */
	private T t;

	/**
	 * ????
	 */
	private long count = 0 ;
	
	/**
	 * ???????
	 */
	private String order;
	
	
	/**
	 * ????
	 **/
	private int pageIndex = 1 ;

	/**
	 * ??????
	 **/
	private int pageSize = 10;
	
	/**
	 * ?????????
	 */
	private int offSet = 0;
	
	/**
	 * ??????
	 */
	private List<T> list;
	

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public int getOffSet() {
		return offSet;
	}

	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}
	
	public void planOffSet(){
		this.offSet=(this.pageIndex-1)*this.pageSize;
	}
	
	
	
	
	
	
}
