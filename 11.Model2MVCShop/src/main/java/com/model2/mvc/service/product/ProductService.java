package com.model2.mvc.service.product;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductService {
	
	// 상품등록
	public void addProduct(Product product) throws Exception;
	
	// 상품 상세 정보
	public Product getProduct(int prodNo) throws Exception;
	
	// 상품정보 리스트
	public Map<String, Object> getProductList(Search search) throws Exception;
	
	// 상품정보 수정
	public void updateProduct(Product product) throws Exception;
	
	// 상품 삭제
	public void deleteProduct(String[] prodNoArr) throws Exception;
	
	
	// 랜덤 포켓몬
	public String getPocketmon(int pocketmonNo) throws Exception;
	
	// 메인에 보여줄 상품들
	public Map<String, Object> getBestProduct() throws Exception;	
	
}