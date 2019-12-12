package com.model2.mvc.service.basket.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.basket.BasketDao;
import com.model2.mvc.service.basket.BasketService;
import com.model2.mvc.service.domain.Basket;

@Service("basketServiceImpl")
public class BasketServiceImpl implements BasketService {

	
	@Autowired
	@Qualifier("basketDaoImpl")
	private BasketDao basketDao;
	public void setBasketDao(BasketDao basketDao) {
		this.basketDao = basketDao;
	}
	public BasketServiceImpl() {
		System.out.println(this.getClass());
	}

	public void addBasket(Basket basket) throws Exception {
		basketDao.addBasket(basket);
	}

	public Map<String, Object> getBasketList(Search search, List<String> visitors) throws Exception {
		
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("search", search);
		sqlMap.put("visitors", visitors);
		
		List<Basket> list = basketDao.getBasketList(sqlMap);
		int totalCount = basketDao.getTotalCount(sqlMap);
				
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	public void deleteBasket(List<String> basketNoArr) throws Exception {
		basketDao.deleteBasket(basketNoArr);
	}
	
	public Basket getProdNo(int basketNo) throws Exception {
		return basketDao.getProdNo(basketNo);
	}
	
}
