package com.model2.mvc.web.product;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


//==> 회원관리 Controller
@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
		
	public ProductController(){
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
	
	
	@RequestMapping( value = "addProduct" )
	public String addProduct( @ModelAttribute("product") Product product, @RequestParam(required = false)MultipartFile[] file) throws Exception {

		System.out.println("/addProduct.do");
		System.out.println("@modelAttribute PRODUCT : "+product);
		System.out.println("@requestParam file : "+file);
		
		// 넘어온 파일 폴더에 저장
		if( file != null ) {
			String saveName = "";
			for (int i = 0 ; i <file.length ; i++) {
				saveName += saveFile(file[i])+",";
			}
			System.out.println("상품의 이미지 파일 : "+saveName);
			product.setFileName(saveName);
		} else {
			product.setFileName("monsterball.png");
		}
		
		productService.addProduct(product);
		
		return "forward:/product/addProduct.jsp";
	}
	
	@RequestMapping( value = "getProduct", method=RequestMethod.GET)
	public String getProduct( @RequestParam("prodNo") int prodNo , HttpServletRequest request, Model model ) throws Exception {
		
		System.out.println("/getProduct.do");
		System.out.println("보고 싶은 상품 번호 : "+prodNo);
		//Business Logic
		Product product = productService.getProduct(prodNo);
		// Model 과 View 연결
		model.addAttribute("product", product);
		
		return "forward:/product/getProduct.jsp";
	}
	
	@RequestMapping( value="updateProduct", method=RequestMethod.GET)
	public String updateProduct( @RequestParam("prodNo") int prodNo , Model model ) throws Exception{

		System.out.println("/updateUserView.do");
		//Business Logic
		// Model 과 View 연결
		model.addAttribute("product", productService.getProduct(prodNo));
		
		return "forward:/product/updateProduct.jsp";
	}
	
	@RequestMapping( value="updateProduct", method=RequestMethod.POST)
	public String updateProduct( @ModelAttribute("product") Product product , @RequestParam(required = false)MultipartFile[] file, Model model , HttpSession session) throws Exception{

		System.out.println("/updateProduct.do");
		//Business Logic
		if( file != null && file.length != 0 ) {
			String saveName = "";
			for (int i = 0 ; i <file.length ; i++) {
				saveName += saveFile(file[i])+",";
			}
			System.out.println("상품의 이미지 파일 : "+saveName);
			product.setFileName(saveName);
		} else {
			product.setFileName("monsterball.png");
		}
		productService.updateProduct(product);
		
		return "redirect:/product/getProduct?prodNo="+product.getProdNo();
	}
	
	
	
	@RequestMapping( value="listProduct" )
	public String listProduct( @ModelAttribute("search") Search search , Model model , HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		System.out.println("/listProduct.do");
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String , Object> map=productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		System.out.println("ㅎㅎㅎㅎㅎㅎㅎlistProduct 완료"+resultPage);
		
		return "forward:/product/listProduct.jsp?";
	}
	
	@RequestMapping( value="deleteProduct" )
	public String deleteProduct( @ModelAttribute("search") Search search, @RequestParam("chk_prodNo") String[] prodNoArr, Model model) throws Exception {
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String , Object> map=productService.getProductList(search);
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);

		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		productService.deleteProduct(prodNoArr);
		
		return "forward:/product/listProduct";
	}
	
	//업로드한 파일 저장할 절대경로
	private static final String UPLOAD_PATH = "C:\\Users\\USER\\git\\repository3\\11.Model2MVCShop\\WebContent\\images\\uploadFiles";
	
	//MultipartFile로 받아서 파일 저장하고 이름 추출하는 메소드
	private String saveFile(MultipartFile file){
	    // 파일 이름 변경
	    String saveName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

	    // 저장할 File 객체를 생성(껍데기 파일)ㄴ
	    File saveFile = new File(UPLOAD_PATH,saveName); // 저장할 폴더 이름, 저장할 파일 이름

	    try {
	        file.transferTo(saveFile); // 업로드 파일에 saveFile이라는 껍데기 입힘
	    } catch ( Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	    
	    return saveName;
	}
}