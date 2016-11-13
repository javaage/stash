package com.pactera.stash;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.pactera.stash.Application;
import com.pactera.stash.mapper.service.RecordService;
import com.pactera.stash.util.QueryStash;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringApplicationConfiguration(classes = Application.class) 
public class AllStockServiceTest {
	
	public RecordService recordImpl;
	@Test
	public void start(){
//		recordImpl = new RecordService();
//		System.out.println("hello");
//		Record record = new Record();
//		record.setCode("1");
//		//record.setIndex(1.2);
//		record.setTime(new Date());
//		recordImpl.insert(record);
	}
	
	@Test
	public void doGet() throws Exception{
		String strUrl = "http://hq.sinajs.cn/list=sh600089";
		System.out.println(QueryStash.doGet(strUrl));
	}
}
