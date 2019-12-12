package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseService {
	
	// 구매하기
	public int addPurchase(Purchase purchase) throws Exception;
	
	// 구매 상세 정보(user)
	public Purchase getPurchase(int tranNo) throws Exception;
	
	// 거래 상세 정보(admin)
	public Purchase getSale(int tranNo) throws Exception;
	

	// 구매내역 리스트(user)
	public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception;
	
	// 취소내역 리스트(user)
	public Map<String, Object> getCancelPurchaseList(Search search, String userId) throws Exception;
	
	// 거래내역 리스트(admin)
	public Map<String, Object> getSaleList(Search search) throws Exception;
	
	
	// 거래 정보 수정
	public void updatePurchase(Purchase purchase) throws Exception;
	
	// 거래코드 업데이트
	public void updateTranCode(Purchase purchase) throws Exception;
	
	// 배송상태 업데이트
	public void updateDlvyStatus(Purchase purchase) throws Exception;
	
	// 상품 발송 전 주문취소
	public void cancelPurchase(Purchase purchase) throws Exception;
	
	// 상품 발송 후 환불요청
	public void requestRefund(Purchase purchase) throws Exception;
	
	// 환불 완료
	public void completeRefund(Purchase purchase) throws Exception;
	
}
