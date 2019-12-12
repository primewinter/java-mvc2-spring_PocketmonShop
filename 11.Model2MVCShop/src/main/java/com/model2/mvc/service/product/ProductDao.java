package com.model2.mvc.service.product;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductDao {
	
	// insert
	public void addProduct(Product product) throws Exception;
	
	// select one
	public Product getProduct(int prodNo) throws Exception;
	
	// select list
	public List<Product> getProductList(Search search) throws Exception;
	
	// update
	public void updateProduct(Product product) throws Exception;
	
	// delete
	public void deleteProduct(String[] prodNoArr) throws Exception;
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount) return
	public int getTotalCount(Search search) throws Exception;
	
	// 랜덤 포켓몬
	public String getPocketmon(int pocketmonNo) throws Exception;
	
	// 메인에 보여줄 상품
	public List<Product> getBestProduct() throws Exception;

}
