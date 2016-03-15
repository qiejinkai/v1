package com.qjk.dao;

import java.util.List;

import com.qjk.entiy.Mood;
import com.qjk.entiy.MoodVo;

/**
 * 
 * @author qiejinkai
 *
 */
public interface MoodDao {
	
	public void addMood(Mood mood);
	
	public void updateMood(Mood mood);

	public void deleteMood(Mood mood);
	
	public void deleteMood(long id);

	public Mood queryMoodById(long id);
	
	public List<Mood> queryMoodList();
	
	public MoodVo queryMoodVo(MoodVo moodVo);

	public void doMoodFavor(Long moodId);

	public Mood queryMood(Mood mood);
}
