package com.model2.mvc.web.basket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.IPUtil;
import com.model2.mvc.service.basket.BasketService;
import com.model2.mvc.service.domain.Basket;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;


//==> ȸ������ Controller
@Controller
@RequestMapping("/basket/*")
public class BasketController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	@Autowired
	@Qualifier("basketServiceImpl")
	private BasketService basketService;
	//setter Method ���� ����
		
	public BasketController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml ���� �Ұ�
	//==> �Ʒ��� �ΰ��� �ּ��� Ǯ�� �ǹ̸� Ȯ�� �Ұ�
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping( value="addBasket" )
	public String addBasket( @ModelAttribute("basket") Basket basket, @ModelAttribute("user") User sessionUser, @RequestParam("prodNo")int prodNo, HttpServletRequest request, HttpSession session) throws Exception {

		System.out.println("/addBasket.do");
		//Business Logic
		//Basket basket = new Basket();
		User user = new User();
		String visitor = null;
		Cookie[] cookies = request.getCookies();
		if(session.getAttribute("user") != null ) { //�α��� ���¶�� userId�� ����.
			user = (User)session.getAttribute("user");
			visitor = user.getUserId();
			System.out.println("�α��� ���¶�� ���� ������ ���̵��� ��������... -->"+visitor);
		} else { //�α׾ƿ� ���¶�� ip�ּҰ� ����.
				if (cookies!=null && cookies.length > 0) {
					for (int i = 0; i < cookies.length; i++) {
						Cookie cookie = cookies[i];
						System.out.println("cookie ������ �� �Ѱ� : "+cookie.getName());
						if (cookie.getName().equals("visitors")) { //��湮���� ��Ű��?!
							visitor = cookie.getValue();
							user.setUserId(visitor);
							System.out.println("��α��� + visitors ���� �ִ� �� "+user.getUserId());
						} else {
							visitor = IPUtil.getClientIP(request);
							user.setUserId(visitor);
							System.out.println("������ �𸣰�����.. IP �� ���� �͹���;"+visitor);
						}
					}
				}
		}
		System.out.println("���� ������?? "+visitor);
		basket.setVisitor(user);
		Product prod = productService.getProduct(prodNo);
		basket.setProduct(prod);
		System.out.println("�Ķ����� �޾ƿ� basket�� quantity ��? "+basket.getQuantity());
		basketService.addBasket(basket);
		return "forward:/product/getProduct";
	}
	
	
	@RequestMapping(value="listBasket")
	public String listBasket( @ModelAttribute("search") Search search , Model model , HttpSession session, HttpServletRequest request) throws Exception{
		System.out.println("�߾߾߾߾߾߾ߤ���:");
		System.out.println("�����!!! : "+search.getCurrentPage());
		System.out.println("/listBasket.do");
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		System.out.println("[pageSize] : "+pageSize);
		// Business logic ����
		User userVO = new User();
		
		// ��Ű �߿��� visitors��Ű �������
		Cookie[] cookies = request.getCookies();
		List<String> visitor = new ArrayList<String>();
		if (cookies!=null && cookies.length > 0) { // �°� ��Ű�� �߿���...
		
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("visitors")) { // ��湮�ڶ�� ip�ּ� ����´�
					visitor.add(cookie.getValue());
					System.out.println("��湮�ڰ� visitors ���� �Ծ��");
				}
			}
			
			if ( visitor.size() == 0 ) { //ù�湮��
				if(session.getAttribute("user") != null ) { //ȸ���� �ٸ� ��ǻ�ͷ� ���Դٸ� userId, ip�ּ� �ֱ�
					userVO = (User)session.getAttribute("user");
					visitor.add(userVO.getUserId());
					System.out.println("ȸ���� �ٸ� ��ǻ�ͷ� ���Դ�.");
				}
				visitor.add(IPUtil.getClientIP(request));
				
			} else { //��湮��
				System.out.println("��湮���߿�...");
				if(session.getAttribute("user") != null) { //��湮�� + �α��� ���¶�� userId�ֱ�
					System.out.println("��湮�� + �α��� ���´�");
					userVO = (User)session.getAttribute("user");
					visitor.add(userVO.getUserId());
				}
			}
			
			
		}
		System.out.println(":::::::db�� �� visior "+visitor);
		
		Map<String , Object> map=basketService.getBasketList(search, visitor);
		System.out.println("[currentPage] : "+search.getCurrentPage());
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		// Model �� View ����
		model.addAttribute("basket", map);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		System.out.println("Debugging : " +search.getCurrentPage());
		System.out.println(">>>>>>>>>>listBasket : "+map);
		System.out.println("��������������listBasket �Ϸ�"+resultPage);
		
		return "forward:/basket/listBasket.jsp";
	}
	
	@RequestMapping(value="deleteBasket")
	public String deleteBasket( @RequestParam("chkBasketNo") List<String> basketNoArr) throws Exception {
		System.out.println("/deleteBasket.do");
		basketService.deleteBasket(basketNoArr);
		
		for(String i : basketNoArr) {
			System.out.println("List�� ���°�?" +i);
		}

		return "forward:/basket/listBasket";
	}
	
	@RequestMapping(value="buyBasket")
	public String buyBasket( @RequestParam("chkBasketNo") List<String> basketNoArr, HttpSession session, Model model ) throws Exception {
		System.out.println("/buyBasket.do");
		List<Product> prodList = new ArrayList<Product>();
		
		for(String basketNo : basketNoArr ) {
			System.out.println("��ٱ��Ͽ��� �� ��ȣ : "+ basketNo);
			Basket basket = basketService.getProdNo(Integer.parseInt(basketNo));
			Product product = basket.getProduct();
			product.setQuantity(basket.getQuantity());
			prodList.add(product);
			System.out.println("\t\t\t�ϳ� ���� "+product);
		}
		
		model.addAttribute("basketNoArr", basketNoArr);
		model.addAttribute("productList", prodList);
		model.addAttribute("user", (User)session.getAttribute("user"));
		System.out.println("addPurchase�� ���Ծ�");
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
}