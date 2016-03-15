package com.qjk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qjk.dao.CounterDao;
import com.qjk.dao.MoodDao;
import com.qjk.entiy.Counter;
import com.qjk.entiy.Mood;
import com.qjk.entiy.MoodVo;
import com.qjk.exception.NullException;
import com.qjk.lock.Lock;
import com.qjk.service.MoodService;
import com.qjk.util.DateUtil;

/**
 * 
 * @author qiejinkai
 *
 */

@Service
public class MoodServiceImpl implements MoodService{

	@Resource
	private MoodDao moodDao;
	@Resource
	private CounterDao counterDao;
	
	@Override
	public void addMood(Mood mood) {
		
		moodDao.addMood(mood);
		
		mood=moodDao.queryMood(mood);

		counterDao.addCounter(new Counter(0, mood.getObjectId(), "skim", DateUtil.now()) );
		counterDao.addCounter(new Counter(0, mood.getObjectId(), "favor", DateUtil.now()) );
		
	}

	@Override
	public void updateMood(Mood mood) {
		
		moodDao.updateMood(mood);
		
	}

	@Override
	public void deleteMood(Mood mood) throws Exception{
		
		 mood = moodDao.queryMood(mood);
		
		if(mood == null){
			
			throw new NullException("心情不存在,或已被删除");
		}
		
		moodDao.deleteMood(mood);
		
	}

	@Override
	public Mood queryMood(long id) {
		
		return moodDao.queryMoodById(id);
	}

	@Override
	public List<Mood> queryMoodList() {
		
		return moodDao.queryMoodList();
	}

	@Override
	public Mood loadMood() {
		
		Mood mood=new Mood();
		
		mood.setMoodState(0);
		
		MoodVo moodVo=new MoodVo();
		
		moodVo.setT(mood);
		
		moodVo.setOffSet(0);
		
		moodVo.setPageSize(1);
		
		moodVo.setOrder(" sequence desc ,createTime desc ");
		
		moodVo= moodDao.queryMoodVo(moodVo);
		
		if(0 == moodVo.getList().get(0).getObjectId()){
			
			return null;
		}else{
			
			mood = moodVo.getList().get(0);
			
			List<Counter>counterList = counterDao.queryCounterList(new Counter(0,mood.getObjectId(),null,0));
			
			for (Counter c : counterList) {
				
				mood.getAttributes().put(c.getAlias(), c.getCount());
			}
			
			return mood;
		}
		
	}
	
	@Override
	public Mood doMoodFavor(long moodId) {
		
		Lock lock=Lock.getLock();
		
		synchronized (lock) {
			
			Map<String,Object> map = new HashMap<String, Object>();
			
			map.put("entiyId", moodId);
			
			map.put("alias", "favor");
			
			counterDao.doAddCounter(map);
			
			Mood mood =new Mood();
			
			List<Counter>counterList = counterDao.queryCounterList(new Counter(0,moodId,null,0));
			
			for (Counter c : counterList) {
				
				mood.getAttributes().put(c.getAlias(), c.getCount());
			}
			
			return mood;
		}
		
	}

	@Override
	public List<Mood> loadMoodList(int offSet,int pageSize) {
		Mood mood=new Mood();
		
		mood.setMoodState(2);
		
		MoodVo moodVo=new MoodVo();
		
		moodVo.setT(mood);
		
		moodVo.setOffSet(offSet);
		
		moodVo.setPageSize(pageSize);
		
		moodVo.setOrder(" sequence desc ,createTime desc ");
		
		moodVo= moodDao.queryMoodVo(moodVo);
		
		if(moodVo.getList().size() ==0 ||0 == moodVo.getList().get(0).getObjectId()){
			
			return null;
		}else{
			
			List<Mood> moodList=  moodVo.getList();
			
			for (Mood m : moodList) {
				
				List<Counter>counterList = counterDao.queryCounterList(new Counter(0,m.getObjectId(),null,0));
				
				for (Counter c : counterList) {
					
					m.getAttributes().put(c.getAlias(), c.getCount());
				}
			}
			
			
			return moodList;
		}
	}

	@Override
	public Mood doLockMood(int moodId) throws Exception{
		
		Mood mood = moodDao.queryMoodById(moodId);
		
		if(mood == null){
			
			throw new NullException("心情不存在,或已被删除");
		}
		
		int moodState = mood.getMoodState();
		
		mood.setMoodState((1 - moodState));
		
		moodDao.updateMood(mood);
		
		return mood;
		
	}

	@Override
	public Mood dostickyMood(int moodId) throws Exception{
		
		Mood mood = moodDao.queryMoodById(moodId);
		
		if(mood == null){
			
			throw new NullException("心情不存在,或已被删除");
		}
		
		int sequence = mood.getSequence();
		
		mood.setSequence((1 - sequence));
		
		moodDao.updateMood(mood);
		
		return mood;
		
	}
	
	
}
