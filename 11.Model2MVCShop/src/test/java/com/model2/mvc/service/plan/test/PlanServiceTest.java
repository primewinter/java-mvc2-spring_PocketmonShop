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
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)

//==> Meta-Data 를 다양하게 Wiring 하자...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
																	"classpath:config/context-aspect.xml",
																	"classpath:config/context-mybatis.xml",
																	"classpath:config/context-transaction.xml" })

public class PlanServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("planServiceImpl")
	private PlanService planService;
	
	/* Plan Controller 테스트용 와이어링 */
	/*
	 * @Autowired
	 * 
	 * @Qualifier("planSubServiceImpl") private PlanSubService planSubService;
	 */

	
	@Test
	public void getUndoneList() throws Exception {
		
		String userId = "admin";
		System.out.println("시작!");
		int count = 0;
		while( count < 5 ) {
			if ( planService.getUndoneCount(userId) > 0 ) { 
				List<Plan> planList= planService.getUndoneList(userId);
				System.out.println(userId+"님의 plan 개수 :: "+planList.size());
				for(Plan pl : planList) {
					System.out.println(pl.getPlanTitle()+" || "+pl.getStartDateString()+" || "+pl.getPlanId());
					for(Todo todo : pl.getTodoList()) {
						System.out.println("미완료된 항목 : "+todo.getTodoName());
					}
				}
				Random rand = new Random();
				int random = rand.nextInt(planList.size());
				System.out.println("\nplanList.size() : "+planList.size() +" || "+random+"번째 플랜리스트에서 ");
				List<Todo> todoList = planList.get(random).getTodoList();
				random = rand.nextInt(todoList.size());
				System.out.print("todoList.size() : "+todoList.size()+" || "+random+"번째 todo 추출 :: ");
				Todo todo = todoList.get(random);
				
				Push push = new Push(); 
				push.setPushType("T"); 
				push.setRefId(todo.getPlanId());
				push.setPushMsg(todo.getTodoName()); 
				System.out.println("\n\n"+userId+"에게 보내는 PUSH ::: "+push); 
			}
			count++;
		}
	}
	
	
}