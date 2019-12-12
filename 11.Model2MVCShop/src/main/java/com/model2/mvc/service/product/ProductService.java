package com.model2.mvc.service.product;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductService {
	
	// ��ǰ���
	public void addProduct(Product product) throws Exception;
	
	// ��ǰ �� ����
	public Product getProduct(int prodNo) throws Exception;
	
	// ��ǰ���� ����Ʈ
	public Map<String, Object> getProductList(Search search) throws Exception;
	
	// ��ǰ���� ����
	public void updateProduct(Product product) throws Exception;
	
	// ��ǰ ����
	public void deleteProduct(String[] prodNoArr) throws Exception;
	
	
	// ���� ���ϸ�
	public String getPocketmon(int pocketmonNo) throws Exception;
	
	// ���ο� ������ ��ǰ��
	public Map<String, Object> getBestProduct() throws Exception;	
	
}