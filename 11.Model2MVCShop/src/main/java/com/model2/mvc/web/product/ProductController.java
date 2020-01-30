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


//==> ȸ������ Controller
@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method ���� ����
		
	public ProductController(){
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
	
	
	@RequestMapping( value = "addProduct" )
	public String addProduct( @ModelAttribute("product") Product product, @RequestParam(required = false)MultipartFile[] file) throws Exception {

		System.out.println("/addProduct.do");
		System.out.println("@modelAttribute PRODUCT : "+product);
		System.out.println("@requestParam file : "+file);
		
		// �Ѿ�� ���� ������ ����
		if( file != null ) {
			String saveName = "";
			for (int i = 0 ; i <file.length ; i++) {
				saveName += saveFile(file[i])+",";
			}
			System.out.println("��ǰ�� �̹��� ���� : "+saveName);
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
		System.out.println("���� ���� ��ǰ ��ȣ : "+prodNo);
		//Business Logic
		Product product = productService.getProduct(prodNo);
		// Model �� View ����
		model.addAttribute("product", product);
		
		return "forward:/product/getProduct.jsp";
	}
	
	@RequestMapping( value="updateProduct", method=RequestMethod.GET)
	public String updateProduct( @RequestParam("prodNo") int prodNo , Model model ) throws Exception{

		System.out.println("/updateUserView.do");
		//Business Logic
		// Model �� View ����
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
			System.out.println("��ǰ�� �̹��� ���� : "+saveName);
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
		
		// Business logic ����
		Map<String , Object> map=productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		// Model �� View ����
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		System.out.println("��������������listProduct �Ϸ�"+resultPage);
		
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
	
	//���ε��� ���� ������ ������
	private static final String UPLOAD_PATH = "C:\\Users\\USER\\git\\repository3\\11.Model2MVCShop\\WebContent\\images\\uploadFiles";
	
	//MultipartFile�� �޾Ƽ� ���� �����ϰ� �̸� �����ϴ� �޼ҵ�
	private String saveFile(MultipartFile file){
	    // ���� �̸� ����
	    String saveName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

	    // ������ File ��ü�� ����(������ ����)��
	    File saveFile = new File(UPLOAD_PATH,saveName); // ������ ���� �̸�, ������ ���� �̸�

	    try {
	        file.transferTo(saveFile); // ���ε� ���Ͽ� saveFile�̶�� ������ ����
	    } catch ( Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	    
	    return saveName;
	}
}