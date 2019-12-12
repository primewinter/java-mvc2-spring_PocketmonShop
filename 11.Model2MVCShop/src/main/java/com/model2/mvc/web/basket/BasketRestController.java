package com.model2.mvc.web.basket;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.service.basket.BasketService;
import com.model2.mvc.service.domain.Basket;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;


//==> 회원관리 Controller
@RestController
@RequestMapping("/basket/*")
public class BasketRestController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	@Autowired
	@Qualifier("basketServiceImpl")
	private BasketService basketService;
	//setter Method 구현 않음
		
	public BasketRestController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping( value = "json/addBasket")
	public boolean addBasket( @RequestBody Map<String, Object> map) throws Exception {
		System.out.println("/json/addBasket : 시작");

		Basket basket = new Basket();
		String userId = (String)map.get("user");
		System.out.println("사용자 : " +userId.trim());
		
		Integer quantity = Integer.parseInt((String)map.get("quantity"));
		System.out.println("수량 : "+quantity);
		
		String prodNo = (String)map.get("prodNo");
		System.out.println("상품 번호 : "+Integer.parseInt(prodNo));
		Product prod = productService.getProduct(Integer.parseInt(prodNo));
		
		User user =  new User();
		user.setUserId(userId.trim());
		basket.setQuantity(quantity);
		basket.setVisitor(user);
		basket.setProduct(prod);
		basketService.addBasket(basket);
		
		System.out.println("/json/addBasket : 끝");
		
		if( userId != null && quantity != null && prodNo != null) {
			return true;
		} else {
			return false;
		}
		
	}

}