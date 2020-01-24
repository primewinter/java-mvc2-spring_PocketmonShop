package com.model2.mvc.service.push.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Push;
import com.model2.mvc.service.push.PushDao;
import com.model2.mvc.service.push.PushService;

@Service("pushServiceImpl")
public class PushServiceImpl implements PushService {

	
	@Autowired
	@Qualifier("pushDaoImpl")
	private PushDao pushDao;

	@Override
	public void addPush(Push push) throws Exception {
		pushDao.addPush(push);
	}

	@Override
	public Map<String, Object> getPushList(Search search, String userId) throws Exception {
		//getPushList�� parameterType�� ���� Map ����
		Map<String, Object> dbMap = new HashMap<String, Object>();
		dbMap.put("search", search);
		dbMap.put("receiverId", userId);
		System.out.println(" :::: pushDao.getPushList(dbMap) ����");
		List<Push> list = pushDao.getPushList(dbMap);
		System.out.println(" :::: pushDao.getPushList(dbMap) �Ϸ�");
		for(Push push : list ) {
			System.out.println("��Ͽ� ���� �˸� : "+push);
		}
		//getTotalCount �ϱ� ���� Map ����
		Map<String, Object> countResult = new HashMap<String, Object>();
		countResult.put("search", search);
		countResult.put("receiverId", userId);
		int totalCount = pushDao.getTotalCount(countResult);
		System.out.println(" :::: pushDao.getTotalCount �Ϸ� [totalCount : "+totalCount+"]");
		//DB�� �ٳ�� ����(list, totalCount) �ֱ�
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	@Override
	public void readPush(String userId) throws Exception {
		pushDao.readPush(userId);
	}

	@Override
	public void deletePush(List<String> pushId) throws Exception {
		pushDao.deletePush(pushId);
	}

	@Override
	public int getUnreadCount(String userId) throws Exception {
		return pushDao.getUnreadCount(userId);
	}
	

}
