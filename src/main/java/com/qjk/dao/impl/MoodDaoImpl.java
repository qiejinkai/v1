package com.qjk.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qjk.dao.MoodDao;
import com.qjk.entiy.Mood;
import com.qjk.entiy.MoodVo;

/**
 * 
 * @author qiejinkai
 *
 */

@Repository
public class MoodDaoImpl extends BaseDaoImpl<Mood> implements MoodDao{

	@Override
	public void addMood(Mood mood) {
		
		super.add(mood);
	}

	@Override
	public void updateMood(Mood mood) {
		
		super.update(mood);
	}

	@Override
	public void deleteMood(Mood mood) {
		
		super.delete(mood);
	}
	
	@Override
	public void deleteMood(long id) {
		
		super.deleteById(Mood.class, id);
		
	}

	@Override
	public Mood queryMoodById(long id) {
		
		return super.queryById(Mood.class, id);
	}

	@Override
	public List<Mood> queryMoodList() {
		
		return super.queryList(Mood.class);
	}
	
	@Override
	public MoodVo queryMoodVo(MoodVo moodVo) {
		
		List<Mood> moodList=super.sqlSessionTemplate.selectList("queryMoodVo",moodVo);
		
		//Object count=super.sqlSessionTemplate.selectOne("countMoodVo",moodVo);
		
		moodVo.setCount(moodList.size());
		
		moodVo.setList(moodList);
		
		return moodVo;
	}
	
	@Override
	public void doMoodFavor(Long moodId) {
		
		super.sqlSessionTemplate.update("doMoodFavor",moodId);
		
	}

	@Override
	public Mood queryMood(Mood mood) {
		
		return super.query(mood);
	}
	
	

}
