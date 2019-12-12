package com.model2.mvc.service.basket;

import java.util.List;
import java.util.Map;

import com.model2.mvc.service.domain.Basket;

public interface BasketDao {
	
	// insert
	public void addBasket(Basket basket) throws Exception;
	
	// select list
	public List<Basket> getBasketList(Map<String, Object> map) throws Exception;
	
	// delete
	public void deleteBasket(List<String> basketNoArr) throws Exception;
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount) return
	public int getTotalCount(Map<String, Object> map) throws Exception;
	
	public Basket getProdNo(int basketNo) throws Exception;

}
