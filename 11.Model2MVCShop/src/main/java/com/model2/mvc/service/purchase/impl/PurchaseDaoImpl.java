package com.model2.mvc.service.purchase.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {

	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public PurchaseDaoImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public int addPurchase(Purchase purchase) throws Exception {
			sqlSession.insert("PurchaseMapper.addTranInfo", purchase);
			sqlSession.insert("PurchaseMapper.insertTranHistory", purchase);
			System.out.println("==addTranInfo&tranHistory 완료");
			sqlSession.insert("PurchaseMapper.addPayment", purchase);
			System.out.println("==addPayment 완료");
			for(Product product : purchase.getPurchaseProd()) {
				sqlSession.insert("PurchaseMapper.addTranProd", product);
				System.out.println("==addTranProd 완료");
			}
			if(purchase.getPayment().getPointPay() > 0 ) {
				sqlSession.insert("PurchaseMapper.addUsedPointHistory", purchase);
				System.out.println("==addPointHistory(사용) 완료");
			}
			sqlSession.insert("PurchaseMapper.addSavedPointHistory", purchase);
			System.out.println("==addPointHistory(적립) 완료");
			sqlSession.insert("PurchaseMapper.addDlvy", purchase);
			System.out.println("==addDlvy 완료");
			for(Product product : purchase.getPurchaseProd()) {
				sqlSession.update("PurchaseMapper.updateStock", product);
				System.out.println("==updateStock 완료");
			}
			sqlSession.update("PurchaseMapper.updatePoint", purchase);
			System.out.println("==updatePoint완료");
			
			return sqlSession.selectOne("PurchaseMapper.getTranNo", purchase);
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
	}

	@Override
	public List<Purchase> getSaleList(Map<String, Object> map) throws Exception {
		return sqlSession.selectList("PurchaseMapper.getSaleList", map);
	}

	@Override
	public List<Purchase> getPurchaseList(Map<String, Object> map ) throws Exception{
		return sqlSession.selectList("PurchaseMapper.getPurchaseList", map);
	}
	
	@Override
	public List<Purchase> getCancelPurchaseList(Map<String, Object> map ) throws Exception{
		return sqlSession.selectList("PurchaseMapper.getCancelPurchaseList", map);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updateTranCode", purchase);
		sqlSession.insert("PurchaseMapper.insertTranHistory2", purchase);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", map);
	}
	
	//취소 내역 조회 count
	@Override
	public int getTotalCount2(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getTotalCount2", map);
	}
	
	//모든 거래 내역 관리
	public int getTotalCountAll(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getTotalCountAll", map);
	}
	
	public void updateDlvyStatus(Purchase purchase) throws Exception{
		sqlSession.update("PurchaseMapper.updateDlvyStatus", purchase);
	}
	
	public void cancelPurchase(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updateTranCode2", purchase);
		sqlSession.insert("PurchaseMapper.insertTranHistory2", purchase);
		for(Product product : purchase.getPurchaseProd()) {
			sqlSession.update("PurchaseMapper.addStock", product);
		}
		sqlSession.delete("PurchaseMapper.deletePointHistory", purchase);
		sqlSession.update("PurchaseMapper.updatePointCancel", purchase);
	}
	
	public void requestRefund(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updateTranCode", purchase);
		sqlSession.insert("PurchaseMapper.insertTranHistory2", purchase);
		
		sqlSession.delete("PurchaseMapper.deletePointHistory", purchase);
		sqlSession.update("PurchaseMapper.updatePointCancel", purchase);
	}

	public void completeRefund(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updateTranCode2", purchase);
		sqlSession.insert("PurchaseMapper.insertTranHistory2", purchase);
		for(Product product : purchase.getPurchaseProd()) {
			sqlSession.update("PurchaseMapper.addStock", product);
		}
	}
}
