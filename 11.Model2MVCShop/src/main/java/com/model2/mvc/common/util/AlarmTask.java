package com.model2.mvc.common.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.model2.mvc.service.domain.Plan;

//@Component
public class AlarmTask {

	
	public AlarmTask() {
		System.out.println(this.getClass());
	}
	
	//@Scheduled(cron = "0 0 10 * * *")
	public void AlarmPlan() {
		long now = System.currentTimeMillis();
		List<Plan> list = new ArrayList<Plan>();
		// 1. d-30 planner
		for(Plan plan : list ) {
			
		}
		// 2. d-7 planner
		
		// 3. d-3 planner
		
		// 4. d-1 planner
		
	}
	
}
