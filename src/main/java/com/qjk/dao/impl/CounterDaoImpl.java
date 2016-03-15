package com.qjk.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qjk.dao.CounterDao;
import com.qjk.entiy.Counter;

/**
 * 
 * @author qiejinkai
 *
 */
@Repository
public class CounterDaoImpl extends BaseDaoImpl<Counter> implements CounterDao {

	@Override
	public void addCounter(Counter counter) {
		
		super.add(counter);
	}

	@Override
	public void updateCounter(Counter counter) {
		
		super.update(counter);
	}

	@Override
	public void deleteCounter(Counter counter) {
		
		super.delete(counter);
	}

	@Override
	public Counter queryCounterById(long id) {
		
		return super.queryById(Counter.class, id);
	}

	@Override
	public Counter queryCounter(Counter counter) {
		
		return super.query(counter);
	}

	@Override
	public List<Counter> queryCounterList() {
		
		return super.queryList(Counter.class);
	}

	@Override
	public void doAddCounter(Map<String,Object> map) {
		
		super.sqlSessionTemplate.update("doAddCounter",map);
	}

	@Override
	public void doMinusCounter(Map<String,Object> map ) {
		
		super.sqlSessionTemplate.update("doMinusCounter",map);
	}

	@Override
	public List<Counter> queryCounterList(Counter counter) {
		
		return super.queryList(counter);
	}
	

}
