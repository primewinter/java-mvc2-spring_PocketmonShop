package com.model2.mvc.web.purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.basket.BasketService;
import com.model2.mvc.service.domain.DeliveryInfo;
import com.model2.mvc.service.domain.Payment;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;


//==> ȸ������ Controller
@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	@Autowired
	@Qualifier("basketServiceImpl")
	private BasketService basketService;
	//setter Method ���� ����
		
	public PurchaseController(){
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
	
	
	@RequestMapping( value="addPurchase", method=RequestMethod.POST )
	public String addPurchase(@RequestParam("prodNo") List<Integer> prodNo, @ModelAttribute("deliveryInfo") DeliveryInfo dlvyInfo, Model model,
												@ModelAttribute("payment") Payment payment, HttpSession session,@RequestParam(value="quantity") List<Integer> quantity,
												@RequestParam(value="basketNo", required=false) List<String> basketNoArr) throws Exception {

		System.out.println("/addPurchase.do");
		//Business Logic
		Purchase purchase = new Purchase();
		
		List<Product> prod = new ArrayList<Product>();
		
		for(int i = 0; i <prodNo.size(); i++ ) {
			Product purchaseProd = productService.getProduct(prodNo.get(i));
			purchaseProd.setQuantity(quantity.get(i));
			prod.add(purchaseProd);
		}
		User user = (User)session.getAttribute("user");
		purchase.setBuyer(user);
		purchase.setDlvyInfo(dlvyInfo);
		purchase.setPayment(payment);
		System.out.println("::: payment ���� : "+payment);
		purchase.setPurchaseProd(prod);
		purchase.setTranCode("�ֹ��Ϸ�");
		int tranNo = purchaseService.addPurchase(purchase);
		purchase.setTranNo(tranNo);
		System.out.println("/addPurchase �Ϸ�::: tranNo = "+tranNo);
		
		if(basketNoArr != null) {
			for(int i=0; i<basketNoArr.size(); i++) {
				System.out.println("��ٱ��� �Ѿ�Ծ�� "+basketNoArr.get(i));
			}
			basketService.deleteBasket(basketNoArr);
		}
		
		session.setAttribute("user", userService.getUser(user.getUserId()));
		model.addAttribute("purchase", purchase);
		model.addAttribute("purchaseProd", prod);
		model.addAttribute("payment", payment);
		model.addAttribute("dlvyInfo", dlvyInfo);
		
		return "forward:/purchase/addPurchase.jsp";
	}
	
	@RequestMapping( value = "addPurchase", method=RequestMethod.GET )
	public String addPurchase( @RequestParam(value="prodNo", required=false) List<Integer> prodNo, @RequestParam(value="quantity", required=false) int quantity, HttpSession session, Model model ) throws Exception{

		System.out.println("/addPurchaseView.do");

		List<Product> prodList = new ArrayList<Product>();
		for(int prod : prodNo) {
			Product product =productService.getProduct(prod);
			product.setQuantity(quantity);
			prodList.add(product);
		}
		
		
		model.addAttribute("productList", prodList);
		model.addAttribute("user", (User)session.getAttribute("user"));
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
	@RequestMapping( value ="getPurchase", method=RequestMethod.GET )
	public String getPurchase( @RequestParam("tranNo") int tranNo , Model model ) throws Exception {
		
		System.out.println("/getPurchase.do");
		System.out.println("���� ���� �ŷ� ��ȣ : "+tranNo);
		//Business Logic
		Purchase purchase = purchaseService.getPurchase(tranNo);
		// Model �� View ����
		model.addAttribute("purchase", purchase);
		
		return  "forward:/purchase/getPurchase.jsp";
	}
	
	@RequestMapping( value = "getSale", method=RequestMethod.GET )
	public String getSale( @RequestParam("tranNo") int tranNo , Model model ) throws Exception {
		
		System.out.println("/getSale.do");
		System.out.println("���� ���� �ŷ� ��ȣ : "+tranNo);
		//Business Logic
		Purchase purchase = purchaseService.getPurchase(tranNo);
		// Model �� View ����
		model.addAttribute("purchase", purchase);
		
		return  "forward:/purchase/getSale.jsp";
	}
	
	@RequestMapping( value = "updatePurchase", method=RequestMethod.GET )
	public String updatePurchase( @RequestParam("tranNo") int tranNo, Model model ) throws Exception{

		System.out.println("/updatePurchaseView.do");
		//Business Logic
		// Model �� View ����
		Purchase purchase = purchaseService.getPurchase(tranNo);
		model.addAttribute("purchase", purchase);
		System.out.println("���� purchase�� payment�� �Ƿ� ���°�!? "+purchase.getPayment());
		
		return "forward:/purchase/updatePurchaseView.jsp";
	}
	
	@RequestMapping( value= "updatePurchase", method=RequestMethod.POST)
	public String updatePurchase( @ModelAttribute("dlvyInfo") DeliveryInfo dlvyInfo, @RequestParam("tranNo") int tranNo, Model model) throws Exception{

		System.out.println("/updatePurchase.do");
		//Business Logic
		System.out.println("�޾ƿ��°�??! dlvyInfo"+dlvyInfo);
		Purchase purchase = new Purchase();
		purchase.setDlvyInfo(dlvyInfo);
		purchase.setTranNo(tranNo);
		purchaseService.updatePurchase(purchase);
		
		return  "redirect:/purchase/getPurchase?tranNo="+tranNo;
	}
	
	
	
	@RequestMapping( value="listSale")
	public String listSale( @ModelAttribute("search") Search search, Model model) throws Exception{
		
		System.out.println("/listSale.do");
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic ����
		Map<String , Object> map = purchaseService.getSaleList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		// Model �� View ����
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		System.out.println("��������������listPurchase �Ϸ�"+resultPage);
		
		return "forward:/purchase/listSale.jsp?";
	}
	
	@RequestMapping(value="listPurchase")
	public String listPurchase( @ModelAttribute("search") Search search, HttpSession session, Model model) throws Exception{
		
		System.out.println("/listPurchase.do");
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic ����
		User user = (User)session.getAttribute("user");
		Map<String , Object> map = purchaseService.getPurchaseList(search, user.getUserId());
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		// Model �� View ����
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		System.out.println("��������������listPurchase �Ϸ�"+resultPage);
		
		return "forward:/purchase/listPurchase.jsp?";
	}
	
	@RequestMapping(value="listCancelPurchase")
	public String listCancelPurchase( @ModelAttribute("search") Search search, HttpSession session, Model model) throws Exception{
		
		System.out.println("/listCancelPurchase.do");
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic ����
		User user = (User)session.getAttribute("user");
		Map<String , Object> map = purchaseService.getCancelPurchaseList(search, user.getUserId());
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		// Model �� View ����
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		System.out.println("��������������listPurchase �Ϸ�"+resultPage);
		
		return "forward:/purchase/listCancelPurchase.jsp";
	}
	
	@RequestMapping(value="updateTranCode")
	public String updateTranCode( @RequestParam("list") String list, @RequestParam("tranNo") String tNum, @ModelAttribute("search") Search search, HttpServletRequest request, HttpSession session,  Model model) throws Exception{

		System.out.println("/updateTranCode.do");
		//Business Logic
		Purchase purchase = new Purchase();
		int tranNo=Integer.parseInt(tNum);
		System.out.println("=========tNum :::"+tNum);
		String tranCode = "";
		if( request.getParameter("list").equals("purchase") ) {
			tranCode = "��ۿϷ�";
			System.out.println("null �� �ƴ� tranCode = "+tranCode);
		} else {
			tranCode =request.getParameter(tNum);
			System.out.println("SaleList���� �� tranCode : "+tranCode);
		}
		purchase.setTranCode(tranCode);
		System.out.println("������������ ����� tranCode : "+tranCode);
		purchase.setTranNo(tranNo);
		if(tranCode.equals("ȯ�ҿϷ�")) {
			purchase = purchaseService.getPurchase(tranNo);
			purchase.setTranCode(tranCode);
			purchaseService.completeRefund(purchase);
		} else {		
			purchaseService.updateTranCode(purchase);
		}
	
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		System.out.println("�޴� ��𿡼� �Ծ�? "+list);
		if(list.equals("purchase") ) {
			User user = (User)session.getAttribute("user");
			Map<String , Object> map = purchaseService.getPurchaseList(search, user.getUserId());
			
			Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
			
			model.addAttribute("list", map.get("list"));
			model.addAttribute("resultPage", resultPage);
			model.addAttribute("search", search);
			System.out.println("���̾ listPurchase�� ����!");
			return "forward:/purchase/listPurchase";
		} else {
			
			Map<String , Object> map = purchaseService.getSaleList(search);
			
			Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
			
			// Model �� View ����
			model.addAttribute("list", map.get("list"));
			model.addAttribute("resultPage", resultPage);
			model.addAttribute("search", search);
			System.out.println("�����ڿ��� listSale�� ����!");
			 return "forward:/purchase/listSale";
		}
	}
	
	@RequestMapping(value="cancelPurchase")
	public ModelAndView cancelPurchase(@RequestParam("tranNo") int tranNo, HttpSession session, Model model) throws Exception {
		
		System.out.println("::::::�ֹ� ���");
		Purchase purchase = new Purchase();
		purchase = purchaseService.getPurchase(tranNo);
		purchase.setTranCode("�ֹ����");
		
		User user = userService.getUser(purchase.getBuyer().getUserId());
		session.setAttribute("user", user);
		
		purchaseService.cancelPurchase(purchase);
		
		return new ModelAndView("forward:/purchase/listPurchase");
	}
	
	@RequestMapping(value="requestRefund")
	public ModelAndView requestRefund(@RequestParam("tranNo") int tranNo, Model model ) throws Exception {
		
		System.out.println("::::::::ȯ�� ��û");
		Purchase purchase = purchaseService.getPurchase(tranNo);
		purchase.setTranCode("ȯ�� ��û");
		purchaseService.requestRefund(purchase);
		
		User user = userService.getUser(purchase.getBuyer().getUserId());
		model.addAttribute("user", user);
		
		return new ModelAndView("forward:/purchase/listPurchase");
	}
	
	
}