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
	
	// update ��ۻ���
	public void updateDlvyStatus(Purchase purchase) throws Exception;
	
	// �Խ��� Page ó���� ���� ��ü Row(totalCount) return
	public int getTotalCount(Map<String, Object> map) throws Exception;
	
	// ��ҳ��� totalCount 
	public int getTotalCount2(Map<String, Object> map) throws Exception;
	
	// ��� �ŷ� ���� totalCount
	public int getTotalCountAll(Map<String, Object> map) throws Exception;
	
	// �ֹ��� purchase ���
	public void cancelPurchase(Purchase purchase) throws Exception;
	
	// �߼� �� ȯ�� ��û
	public void requestRefund(Purchase purchase) throws Exception;
	
	// ȯ�� �Ϸ�
	public void completeRefund(Purchase purchase) throws Exception;
	
	
}
