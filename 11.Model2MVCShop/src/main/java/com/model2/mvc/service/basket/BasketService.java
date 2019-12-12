package com.model2.mvc.service.basket;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Basket;

public interface BasketService {
	
	// ��ٱ��� �߰�
	public void addBasket(Basket basket) throws Exception;
	
	// ��ٱ��� ����Ʈ
	public Map<String, Object> getBasketList(Search search, List<String> visitors) throws Exception;
	
	// ��ٱ��� ����
	public void deleteBasket(List<String> basketNoArr) throws Exception;
	
	// ��ٱ��� ��ȸ
	public Basket getProdNo(int basketNo) throws Exception;
	

}
