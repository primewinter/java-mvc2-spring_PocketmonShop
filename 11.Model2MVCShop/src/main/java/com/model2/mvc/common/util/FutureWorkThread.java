package com.model2.mvc.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FutureWorkThread extends Thread{

	private String startDate;

	public FutureWorkThread( String startDate ){
	}
	

	public void run() {
		try {
			System.out.println("run();");
			List<Long> arr = timeUntil(startDate);
			for(long delay : arr) {
				sleep(delay);
				work();
			}
		} catch ( InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}


	public void work(){
		System.out.println( "수행할 작업");
	}

	

	public static List<Long> timeUntil( String startDate ) throws Exception {
		System.out.println("timeUntil();");
		SimpleDateFormat toDate = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		Date date = toDate.parse(startDate);
		long startLong = date.getTime();
		long[] dDay = {30, 7, 3, 1};
		List<Long> list = new ArrayList<Long>();
		for(long temp : dDay) {
			long diff = startLong - 24*60*60*1000*temp;
			if( diff > 0 ) {
				list.add(diff);
			}
		}
		/*
		long before30 = startLong - (24*60*60*1000*30); //30일 전
		long before7 = startLong - (24*60*60*1000*7);
		long before3 = startLong - (24*60*60*1000*3);
		long before1 = startLong - (24*60*60*1000);
		
		long[] sleepArr = {before30, before7, before3, before1};
		 */
		return list;
	}

}



