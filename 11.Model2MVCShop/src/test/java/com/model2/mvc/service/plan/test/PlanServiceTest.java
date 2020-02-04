package com.model2.mvc.service.plan.test;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import com.model2.mvc.common.util.Util;
import com.model2.mvc.service.domain.City;
import com.model2.mvc.service.domain.Daily;
import com.model2.mvc.service.domain.Day;
import com.model2.mvc.service.domain.Memo;
import com.model2.mvc.service.domain.Offer;
import com.model2.mvc.service.domain.Party;
import com.model2.mvc.service.domain.Plan;
import com.model2.mvc.service.domain.Push;
import com.model2.mvc.service.domain.Stuff;
import com.model2.mvc.service.domain.Todo;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.plan.PlanService;
//import com.model2.mvc.service.planSub.PlanSubService;

/*
 *	FileName :  PlanServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)

//==> Meta-Data �� �پ��ϰ� Wiring ����...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
																	"classpath:config/context-aspect.xml",
																	"classpath:config/context-mybatis.xml",
																	"classpath:config/context-transaction.xml" })

public class PlanServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("planServiceImpl")
	private PlanService planService;
	
	/* Plan Controller �׽�Ʈ�� ���̾ */
	/*
	 * @Autowired
	 * 
	 * @Qualifier("planSubServiceImpl") private PlanSubService planSubService;
	 */

	
	@Test
	public void getUndoneList() throws Exception {
		
		String userId = "admin";
		System.out.println("����!");
		int count = 0;
		while( count < 5 ) {
			if ( planService.getUndoneCount(userId) > 0 ) { 
				List<Plan> planList= planService.getUndoneList(userId);
				System.out.println(userId+"���� plan ���� :: "+planList.size());
				for(Plan pl : planList) {
					System.out.println(pl.getPlanTitle()+" || "+pl.getStartDateString()+" || "+pl.getPlanId());
					for(Todo todo : pl.getTodoList()) {
						System.out.println("�̿Ϸ�� �׸� : "+todo.getTodoName());
					}
				}
				Random rand = new Random();
				int random = rand.nextInt(planList.size());
				System.out.println("\nplanList.size() : "+planList.size() +" || "+random+"��° �÷�����Ʈ���� ");
				List<Todo> todoList = planList.get(random).getTodoList();
				random = rand.nextInt(todoList.size());
				System.out.print("todoList.size() : "+todoList.size()+" || "+random+"��° todo ���� :: ");
				Todo todo = todoList.get(random);
				
				Push push = new Push(); 
				push.setPushType("T"); 
				push.setRefId(todo.getPlanId());
				push.setPushMsg(todo.getTodoName()); 
				System.out.println("\n\n"+userId+"���� ������ PUSH ::: "+push); 
			}
			count++;
		}
	}
	
	
}