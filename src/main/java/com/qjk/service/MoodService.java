package com.qjk.service;

import java.util.List;

import com.qjk.entiy.Mood;

/**
 * 心情业务逻辑
 * @author qiejinkai
 *
 */
public interface MoodService {
	
	public void addMood(Mood mood);
	
	public void updateMood(Mood mood);
	
	public void deleteMood(Mood mood) throws Exception;

	public Mood queryMood(long id);
	
	public List<Mood> queryMoodList();
	
	public Mood loadMood();

	public Mood doMoodFavor(long moodId);

	public List<Mood> loadMoodList(int offSet, int pageSize);

	public Mood doLockMood(int moodId) throws Exception;
	public Mood dostickyMood(int moodId) throws Exception;

	
}
