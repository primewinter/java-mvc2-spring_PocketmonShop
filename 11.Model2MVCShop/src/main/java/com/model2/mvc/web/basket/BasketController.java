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


//==> 회원관리 Controller
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
	//setter Method 구현 않음
		
	public BasketController(){
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
	
	
	@RequestMapping( value="addBasket" )
	public String addBasket( @ModelAttribute("basket") Basket basket, @ModelAttribute("user") User sessionUser, @RequestParam("prodNo")int prodNo, HttpServletRequest request, HttpSession session) throws Exception {

		System.out.println("/addBasket.do");
		//Business Logic
		//Basket basket = new Basket();
		User user = new User();
		String visitor = null;
		Cookie[] cookies = request.getCookies();
		if(session.getAttribute("user") != null ) { //로그인 상태라면 userId가 간다.
			user = (User)session.getAttribute("user");
			visitor = user.getUserId();
			System.out.println("로그인 상태라면 최종 유저는 아이디어야 마땅하지... -->"+visitor);
		} else { //로그아웃 상태라면 ip주소가 간다.
				if (cookies!=null && cookies.length > 0) {
					for (int i = 0; i < cookies.length; i++) {
						Cookie cookie = cookies[i];
						System.out.println("cookie 여러개 중 한개 : "+cookie.getName());
						if (cookie.getName().equals("visitors")) { //재방문자의 쿠키는?!
							visitor = cookie.getValue();
							user.setUserId(visitor);
							System.out.println("비로그인 + visitors 갖고 있는 자 "+user.getUserId());
						} else {
							visitor = IPUtil.getClientIP(request);
							user.setUserId(visitor);
							System.out.println("왠지는 모르겠지만.. IP 걍 갖고 와벌임;"+visitor);
						}
					}
				}
		}
		System.out.println("최종 유저는?? "+visitor);
		basket.setVisitor(user);
		Product prod = productService.getProduct(prodNo);
		basket.setProduct(prod);
		System.out.println("파람으로 받아온 basket의 quantity 는? "+basket.getQuantity());
		basketService.addBasket(basket);
		return "forward:/product/getProduct";
	}
	
	
	@RequestMapping(value="listBasket")
	public String listBasket( @ModelAttribute("search") Search search , Model model , HttpSession session, HttpServletRequest request) throws Exception{
		System.out.println("야야야야야야야ㅑㅇ:");
		System.out.println("디버깅!!! : "+search.getCurrentPage());
		System.out.println("/listBasket.do");
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		System.out.println("[pageSize] : "+pageSize);
		// Business logic 수행
		User userVO = new User();
		
		// 쿠키 중에서 visitors쿠키 갖고오기
		Cookie[] cookies = request.getCookies();
		List<String> visitor = new ArrayList<String>();
		if (cookies!=null && cookies.length > 0) { // 온갖 쿠키들 중에서...
		
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("visitors")) { // 재방문자라면 ip주소 갖고온다
					visitor.add(cookie.getValue());
					System.out.println("재방문자가 visitors 갖고 왔어용");
				}
			}
			
			if ( visitor.size() == 0 ) { //첫방문자
				if(session.getAttribute("user") != null ) { //회원이 다른 컴퓨터로 들어왔다면 userId, ip주소 넣기
					userVO = (User)session.getAttribute("user");
					visitor.add(userVO.getUserId());
					System.out.println("회원이 다른 컴퓨터로 들어왔다.");
				}
				visitor.add(IPUtil.getClientIP(request));
				
			} else { //재방문자
				System.out.println("재방문자중에...");
				if(session.getAttribute("user") != null) { //재방문자 + 로그인 상태라면 userId넣기
					System.out.println("재방문자 + 로그인 상태다");
					userVO = (User)session.getAttribute("user");
					visitor.add(userVO.getUserId());
				}
			}
			
			
		}
		System.out.println(":::::::db에 갈 visior "+visitor);
		
		Map<String , Object> map=basketService.getBasketList(search, visitor);
		System.out.println("[currentPage] : "+search.getCurrentPage());
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		// Model 과 View 연결
		model.addAttribute("basket", map);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		System.out.println("Debugging : " +search.getCurrentPage());
		System.out.println(">>>>>>>>>>listBasket : "+map);
		System.out.println("ㅎㅎㅎㅎㅎㅎㅎlistBasket 완료"+resultPage);
		
		return "forward:/basket/listBasket.jsp";
	}
	
	@RequestMapping(value="deleteBasket")
	public String deleteBasket( @RequestParam("chkBasketNo") List<String> basketNoArr) throws Exception {
		System.out.println("/deleteBasket.do");
		basketService.deleteBasket(basketNoArr);
		
		for(String i : basketNoArr) {
			System.out.println("List에 담기는가?" +i);
		}

		return "forward:/basket/listBasket";
	}
	
	@RequestMapping(value="buyBasket")
	public String buyBasket( @RequestParam("chkBasketNo") List<String> basketNoArr, HttpSession session, Model model ) throws Exception {
		System.out.println("/buyBasket.do");
		List<Product> prodList = new ArrayList<Product>();
		
		for(String basketNo : basketNoArr ) {
			System.out.println("장바구니에서 온 번호 : "+ basketNo);
			Basket basket = basketService.getProdNo(Integer.parseInt(basketNo));
			Product product = basket.getProduct();
			product.setQuantity(basket.getQuantity());
			prodList.add(product);
			System.out.println("\t\t\t하나 담음 "+product);
		}
		
		model.addAttribute("basketNoArr", basketNoArr);
		model.addAttribute("productList", prodList);
		model.addAttribute("user", (User)session.getAttribute("user"));
		System.out.println("addPurchase로 가게씀");
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
}