package com.qjk.dao;

import java.util.List;
import java.util.Map;

import com.qjk.entiy.Counter;

/**
 * 
 * @author qiejinkai
 *
 */
public interface CounterDao {
	
	public void addCounter(Counter counter);
	
	public void updateCounter(Counter counter);
	
	public void deleteCounter(Counter counter);
	
	public Counter queryCounterById(long id);
	
	public Counter queryCounter(Counter counter);
	
	public List<Counter> queryCounterList();
	
	public void doAddCounter(Map<String, Object> map);
	
	public void doMinusCounter(Map<String, Object> map);

	public List<Counter> queryCounterList(Counter counter);

}
