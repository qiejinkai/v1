package myweb.testMood;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.qjk.dao.MoodDao;
import com.qjk.entiy.Mood;
import com.qjk.entiy.MoodVo;
import com.qjk.util.DateUtil;


@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class testMoodDao extends AbstractJUnit4SpringContextTests{
	
	@Resource
	MoodDao moodDao;
	
	@Test
	public void testMoodDaoAdd(){
		
		Mood mood=new Mood();
		mood.setTitle("开心ing");
		mood.setContent("今天发工资");
		mood.setCreateTime(DateUtil.now());
		
		moodDao.addMood(mood);
		
	}

	@Test
	public void testMoodDaoQueryall(){
		
		List<Mood> moodList=moodDao.queryMoodList();
		
		for (Mood mood : moodList) {
			System.out.println(mood.toString());
		}
		
	}
	@Test
	public void testMoodDaoQueryById(){
		
		Mood mood=moodDao.queryMoodById(1);
		
		System.out.println(mood);
		
	}
	

	@Test
	public void testMoodDaoUpdate(){
		
		Mood mood=moodDao.queryMoodById(2);
		
		System.out.println("before :"+mood);
		
		mood.setContent("今天下雨了");
		
		moodDao.updateMood(mood);;
		
		 mood=moodDao.queryMoodById(2);
		
		System.out.println("after :"+mood);
		
	}
	
	@Test
	public void testMoodDaoDelete(){
		Mood m=moodDao.queryMoodById(2);
		moodDao.deleteMood(m);
		
		List<Mood> moodList=moodDao.queryMoodList();
		
		for (Mood mood : moodList) {
			System.out.println(mood.toString());
		}
		
	}

	@Test
	public void testMoodDaoDeleteById(){
		
		
		moodDao.deleteMood(1);
		
	}
	

	@Test
	public void testMoodDaoQueryMoodVo(){
		
		MoodVo mv=new MoodVo();
		
		mv.setOrder(" objectId desc ");
		
		mv.planOffSet();
		
		Mood mood =new Mood();
		
		mood.setMoodState(1);
		
		mv.setT(mood);
		
		List<Mood> moodList=moodDao.queryMoodVo(mv).getList();
		
		for (Mood m : moodList) {
			System.out.println(m.toString());
		}
		
	}
	
	
	
	
}
