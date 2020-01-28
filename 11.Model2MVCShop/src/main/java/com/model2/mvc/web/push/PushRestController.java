package com.model2.mvc.web.push;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Push;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.push.PushService;

@RestController
@RequestMapping("/push/*")
public class PushRestController {
	
	@Autowired
	@Qualifier("pushServiceImpl")
	private PushService pushService;

	public PushRestController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping(value="json/addPush")
	public void addPush(@RequestBody Push push) throws Exception {
		System.out.println("json/addPush :: @RequestBody Push : "+push);
		pushService.addPush(push);
	}
	
	@RequestMapping(value="json/getUnreadCount")
	public int getUnreadCount(@RequestBody User user) throws Exception {
		System.out.println("json/getUnreadCount :: @RequestBody User : "+user);
		int unreadCount = pushService.getUnreadCount(user.getUserId());

		return unreadCount;
	}
	
	@RequestMapping(value="json/getPushList/{userId}", method=RequestMethod.GET)
	public Map<String, Object> getPushList(@PathVariable("userId")String userId) throws Exception {
		System.out.println("json/getPushList :: @PathVariable : "+userId);
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(pageSize);
		Map<String , Object> map = pushService.getPushList(search, userId);
		System.out.println(" :: pushServic.getPushList(search, userId) 완료");
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", map.get("list"));
		result.put("resultPage", resultPage);
		result.put("search", search);
		
		return result;
	}
	
	@RequestMapping(value="json/getPushList/{userId}", method=RequestMethod.POST)
	public List<Push> getPushList(@PathVariable("userId")String userId, @RequestBody Map<String, Object> jsonMap) throws Exception {
		System.out.println("json/getScrollList_POST :: @PathVariable : "+userId);
		
		ObjectMapper objectmapper = new ObjectMapper();
		Search search = objectmapper.convertValue(jsonMap.get("search"), Search.class);
		System.out.println("json/getScrollList_POST :: @RequestBody Search: "+search);
		
		if(search.getCurrentPage()==0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		Map<String , Object> map = pushService.getPushList(search, userId);
		System.out.println(" :: pushService.getPushList(search, userId) 완료");
		
		return (List<Push>)map.get("list");
	}
	
	@RequestMapping(value="json/readPush/{userId}")
	public void readPush(@PathVariable("userId") String userId) throws Exception {
		System.out.println("json/readPush :: "+userId);
		pushService.readPush(userId);
	}
	
	@RequestMapping(value="json/deletePush")
	public void deletePush(@RequestBody List<String> pushId ) throws Exception {
		System.out.println("json/deletePush :: "+pushId);
		pushService.deletePush(pushId);
	}
	
	@RequestMapping(value="json/getUnreadCount/{userId}")
	public int getUnreadCount(@PathVariable("userId") String userId ) throws Exception {
		System.out.println("json/getUnreadCount :: "+userId);
		return pushService.getUnreadCount(userId);
	}

}
