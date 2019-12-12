package com.model2.mvc.service.basket.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.basket.BasketDao;
import com.model2.mvc.service.domain.Basket;

@Repository("basketDaoImpl")
public class BasketDaoImpl implements BasketDao{

	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// Constructor
	public BasketDaoImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addBasket(Basket basket) throws Exception {
		int number = sqlSession.selectOne("BasketMapper.checkDpl", basket);
		if( number == 0 ) {
			System.out.println("새로 addBasket 함"+number);
			sqlSession.insert("BasketMapper.addBasket", basket);
		} else {
			System.out.println("이미 있어서 updateBasket 함"+number);
			sqlSession.update("BasketMapper.updateBasket", basket);
		}
	}

	@Override
	public List<Basket> getBasketList(Map<String, Object> map) throws Exception {
		return sqlSession.selectList("BasketMapper.getBasketList", map);
	}

	public void deleteBasket(List<String> basketNoArr) throws Exception {
		sqlSession.update("BasketMapper.deleteBasket", basketNoArr);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne("BasketMapper.getTotalCount", map);
	}
	
	public Basket getProdNo(int basketNo) throws Exception {
		return sqlSession.selectOne("BasketMapper.getProdNo", basketNo);
	}

}
