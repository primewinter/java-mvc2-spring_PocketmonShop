package com.model2.mvc.common.util;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import com.model2.mvc.service.domain.User;

@Component
public class TaskTestService {
	
	/*
	 * @Scheduled(cron="0/10 * * * * *") public static void TestScheduler(){
	 * System.out.println("�α��� �� 10�ʸ��� ����˴ϴ�."); }
	 */
	
	private static ThreadPoolTaskScheduler scheduler;
	 
    public static void stopScheduler(User dbUser) {
        System.out.println(dbUser.getUserId()+"���� �ð谡 ����ϴ�... ������..."+new Date());
    	scheduler.shutdown();
    }
 
    public static <V> void startScheduler(User dbUser) {
    	ScheduledFuture<?> task = new ScheduledFuture<V>() {

			@Override
			public long getDelay(TimeUnit arg0) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int compareTo(Delayed o) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public V get() throws InterruptedException, ExecutionException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public V get(long timeout, TimeUnit unit)
					throws InterruptedException, ExecutionException, TimeoutException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isCancelled() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isDone() {
				// TODO Auto-generated method stub
				return false;
			}
		};
    	System.out.println("startScheduler()");
        scheduler = new ThreadPoolTaskScheduler();
        scheduler.initialize();
        // �����췯�� ���۵Ǵ� �κ� 
        scheduler.schedule(getRunnable(dbUser), getTrigger());
    }
 
    private static Runnable getRunnable(User dbUser){
        return () -> {
            // do something 
            System.out.println(dbUser.getUserId()+"���� �ð谡 °��°�� ���ư��ϴ�.... " +new Date());
        };
    }
 
    private static Trigger getTrigger() {
        // �۾� �ֱ� ���� 
        return new PeriodicTrigger(5, TimeUnit.SECONDS);
    }
    
}


