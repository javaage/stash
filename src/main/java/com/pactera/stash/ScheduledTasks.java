package com.pactera.stash;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.pactera.stash.mapper.service.RecordService;
import com.pactera.stash.mapper.service.StockService;
import com.pactera.stash.util.QueryStash;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

	@Autowired
	public StockService allStockService;

	@Autowired
	public RecordService recordService;

	@Scheduled(fixedRate = 1000 * 30)
	public void reportCurrentTime() {
		System.out.println("Scheduling Tasks Examples: The time is now ");
		System.out.println(new Date());
	}

	// 每1分钟执行一次
	@Scheduled(cron = "0 */1 *  * * * ")
	public void reportCurrentByCron() {
		System.out.println("Scheduling Tasks Examples By Cron: The time is now ");
		System.out.println(new Date());

		try {
			ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();  
			poolTaskExecutor.setQueueCapacity(200);  
			poolTaskExecutor.setCorePoolSize(2);  
			poolTaskExecutor.setMaxPoolSize(1000);    
			poolTaskExecutor.initialize();
			
			QueryStash query = new QueryStash();
			allStockService.initQuery();
			for (final String strUrlStock : Application.arrUrlStock) {
				System.out.println(strUrlStock);
				poolTaskExecutor.execute(new Runnable(){

					@Override
					public void run() {
						try {
							recordService.saveRecords(strUrlStock);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				});
				
			}
		} catch (Exception exp) {
			System.out.println(exp.toString());
		}
	}

}