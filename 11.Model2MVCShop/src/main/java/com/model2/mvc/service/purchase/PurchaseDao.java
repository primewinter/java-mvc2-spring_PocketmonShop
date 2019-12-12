package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {

	// insert
	public int addPurchase(Purchase purchase) throws Exception;
	
	// select one
	public Purchase getPurchase(int tranNo) throws Exception;
	
	// select List(Admin)
	public List<Purchase> getSaleList(Map<String, Object> map) throws Exception;
	
	// select List(User)
	public List<Purchase> getPurchaseList(Map<String, Object> map) throws Exception;
	
	// select CancelList
	public List<Purchase> getCancelPurchaseList(Map<String, Object> map) throws Exception;
	
	// update
	public void updatePurchase(Purchase purchase) throws Exception;
	
	// update TranCode
	public void updateTranCode(Purchase purchase) throws Exception;
	
	// update 배송상태
	public void updateDlvyStatus(Purchase purchase) throws Exception;
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount) return
	public int getTotalCount(Map<String, Object> map) throws Exception;
	
	// 취소내역 totalCount 
	public int getTotalCount2(Map<String, Object> map) throws Exception;
	
	// 모든 거래 내역 totalCount
	public int getTotalCountAll(Map<String, Object> map) throws Exception;
	
	// 주문전 purchase 취소
	public void cancelPurchase(Purchase purchase) throws Exception;
	
	// 발송 후 환불 요청
	public void requestRefund(Purchase purchase) throws Exception;
	
	// 환불 완료
	public void completeRefund(Purchase purchase) throws Exception;
	
	
}
