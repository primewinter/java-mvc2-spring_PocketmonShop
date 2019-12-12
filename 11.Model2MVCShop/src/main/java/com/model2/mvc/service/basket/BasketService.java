package com.model2.mvc.service.basket;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Basket;

public interface BasketService {
	
	// 장바구니 추가
	public void addBasket(Basket basket) throws Exception;
	
	// 장바구니 리스트
	public Map<String, Object> getBasketList(Search search, List<String> visitors) throws Exception;
	
	// 장바구니 삭제
	public void deleteBasket(List<String> basketNoArr) throws Exception;
	
	// 장바구니 조회
	public Basket getProdNo(int basketNo) throws Exception;
	

}
