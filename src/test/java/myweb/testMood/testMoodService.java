package myweb.testMood;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.qjk.entiy.Mood;
import com.qjk.service.MoodService;
import com.qjk.util.DateUtil;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class testMoodService extends AbstractJUnit4SpringContextTests{


	@Resource
	MoodService moodService;
	
	@Test
	public void testLoadMood(){
		
		Mood mood=moodService.loadMood();
		
		Map<String,Object> attrbutes=mood.getAttributes();
			
		for (String  key : attrbutes.keySet()) {
			System.out.println(key+"  ,  "+attrbutes.get(key));
		}
		
		System.out.println(mood);
		
	}
	
	@Test
	public void testDoMoodFavor(){
		
		Mood mood=moodService.doMoodFavor(16L);
		
		Map<String,Object> attrbutes=mood.getAttributes();
		
		for (String  key : attrbutes.keySet()) {
			
			System.out.println(key+"  ,  "+attrbutes.get(key));
		}
		
	}
	
	@Test
	public void testMoodAdd() throws InterruptedException{

		Mood mood=new Mood();
		mood.setTitle("���˽���˧˧��");
		mood.setContent("���Ʋ��岨");
		mood.setCreateTime(DateUtil.now());
		
		Thread.sleep(100);
		moodService.addMood(mood);

		Mood mood1=new Mood();
		mood1.setTitle("���˽���˧˧��");
		mood1.setContent("��ë����ˮ");
		mood1.setCreateTime(DateUtil.now());
		Thread.sleep(100);
		moodService.addMood(mood1);

		Mood mood2=new Mood();
		mood2.setTitle("���˽���˧˧��");
		mood2.setContent("���������");
		mood2.setCreateTime(DateUtil.now());
		Thread.sleep(100);
		moodService.addMood(mood2);

		Mood mood3=new Mood();
		mood3.setTitle("���˽���˧˧��");
		mood3.setContent("����");
		mood3.setCreateTime(DateUtil.now());
		Thread.sleep(100);
		moodService.addMood(mood3);

		
	}
	
	@Test
	public void testLoadMoodList(){
		
		List<Mood> moodList=moodService.loadMoodList(0,200);
		
		for (Mood mood : moodList) {
			
			System.out.println(mood);
		}
		
	}
	
	
}
