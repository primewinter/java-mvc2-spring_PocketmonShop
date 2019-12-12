package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {

	
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao purchaseDao;
	public void setProductDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}
	public PurchaseServiceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public int addPurchase(Purchase purchase) throws Exception {
		return purchaseDao.addPurchase(purchase);
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return purchaseDao.getPurchase(tranNo);
	}

	@Override
	public Purchase getSale(int tranNo) throws Exception {
		return purchaseDao.getPurchase(tranNo);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception {
		//getPurchaseList 하기 위한 Map 생성
		Map<String, Object> dbMap = new HashMap<String, Object>();
		dbMap.put("search", search);
		dbMap.put("buyerId", userId);
		
		List<Purchase> list = (List<Purchase>) purchaseDao.getPurchaseList(dbMap);
		
		//getTotalCount 하기 위한 Map 생성
		Map<String, Object> countResult = new HashMap<String, Object>();
		countResult.put("search", search);
		countResult.put("buyerId", userId);
		int totalCount = purchaseDao.getTotalCount(countResult);
		
		//DB에 다녀온 정보(list, totalCount) 넣기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
		
	}
	
	public Map<String, Object> getCancelPurchaseList(Search search, String userId) throws Exception {
		//getPurchaseList 하기 위한 Map 생성
		Map<String, Object> dbMap = new HashMap<String, Object>();
		dbMap.put("search", search);
		dbMap.put("buyerId", userId);
		
		List<Purchase> list = (List<Purchase>) purchaseDao.getCancelPurchaseList(dbMap);
		
		//getTotalCount 하기 위한 Map 생성
		Map<String, Object> countResult = new HashMap<String, Object>();
		countResult.put("search", search);
		countResult.put("buyerId", userId);
		int totalCount = purchaseDao.getTotalCount2(countResult);
		
		//DB에 다녀온 정보(list, totalCount) 넣기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	@Override
	public Map<String, Object> getSaleList(Search search) throws Exception {
		Map<String, Object> dbMap = new HashMap<String, Object>();
		dbMap.put("search", search);
		
		List<Purchase> list = (List<Purchase>) purchaseDao.getSaleList(dbMap);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("search", search);
		int totalCount = purchaseDao.getTotalCountAll(result);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		purchaseDao.updatePurchase(purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		purchaseDao.updateTranCode(purchase);
	}
	
	public void updateDlvyStatus(Purchase purchase) throws Exception{
		purchaseDao.updateDlvyStatus(purchase);
	}
	
	public void cancelPurchase(Purchase purchase) throws Exception {
		purchaseDao.cancelPurchase(purchase);
	}
	
	public void requestRefund(Purchase purchase) throws Exception {
		purchaseDao.requestRefund(purchase);
	}
	
	public void completeRefund(Purchase purchase) throws Exception {
		purchaseDao.completeRefund(purchase);
	}

}
