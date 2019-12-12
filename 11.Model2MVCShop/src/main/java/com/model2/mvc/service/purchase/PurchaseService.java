package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseService {
	
	// �����ϱ�
	public int addPurchase(Purchase purchase) throws Exception;
	
	// ���� �� ����(user)
	public Purchase getPurchase(int tranNo) throws Exception;
	
	// �ŷ� �� ����(admin)
	public Purchase getSale(int tranNo) throws Exception;
	

	// ���ų��� ����Ʈ(user)
	public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception;
	
	// ��ҳ��� ����Ʈ(user)
	public Map<String, Object> getCancelPurchaseList(Search search, String userId) throws Exception;
	
	// �ŷ����� ����Ʈ(admin)
	public Map<String, Object> getSaleList(Search search) throws Exception;
	
	
	// �ŷ� ���� ����
	public void updatePurchase(Purchase purchase) throws Exception;
	
	// �ŷ��ڵ� ������Ʈ
	public void updateTranCode(Purchase purchase) throws Exception;
	
	// ��ۻ��� ������Ʈ
	public void updateDlvyStatus(Purchase purchase) throws Exception;
	
	// ��ǰ �߼� �� �ֹ����
	public void cancelPurchase(Purchase purchase) throws Exception;
	
	// ��ǰ �߼� �� ȯ�ҿ�û
	public void requestRefund(Purchase purchase) throws Exception;
	
	// ȯ�� �Ϸ�
	public void completeRefund(Purchase purchase) throws Exception;
	
}
