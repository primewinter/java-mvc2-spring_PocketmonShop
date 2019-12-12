package com.model2.mvc.web.product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


//==> ȸ������ Controller
@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method ���� ����
		
	public ProductRestController(){
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
	
	
	@RequestMapping( value = "json/addProduct", method=RequestMethod.POST)
	public void addProduct( @RequestBody Product product ) throws Exception {

		System.out.println("/json/addProduct : POST");
		System.out.println("@RequestBody PRODUCT : "+product);
		
		productService.addProduct(product);
		
	}
	
	@RequestMapping( value = "json/getProduct/{prodNo}", method=RequestMethod.GET)
	public Map<String, Object> getProduct( @PathVariable int prodNo ) throws Exception {
		
		System.out.println("/getProduct.do");
		System.out.println("���� ���� ��ǰ ��ȣ : "+prodNo);
		//Business Logic
		Product product = productService.getProduct(prodNo);
		// Model �� View ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product", product);
		
		return map;
	}
	
	@RequestMapping( value="json/updateProduct/{prodNo}", method=RequestMethod.GET)
	public  Map<String, Object> updateProduct( @PathVariable int prodNo ) throws Exception{

		System.out.println("/updateUserView.do");
		//Business Logic
		// Model �� View ����
		Product product = productService.getProduct(prodNo);
		
		// Model �� View ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product", product);
		
		return map;
	}
	
	@RequestMapping( value="json/updateProduct", method=RequestMethod.POST)
	public void updateProduct( @RequestBody Product product ) throws Exception{

		System.out.println("/updateProduct.do");
		//Business Logic
		productService.updateProduct(product);
		
	}
	
	
	
	@RequestMapping( value="json/listProduct", method=RequestMethod.GET )
	public List<Product> listProduct( @RequestBody Search search, @RequestParam int currentPage ) throws Exception{
		
		System.out.println("json/listProduct POST");
		System.out.println("@RequestBody Search : "+search);
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		System.out.println("������ ��");
		// Business logic ����
		Map<String , Object> map=productService.getProductList(search);
		System.out.println("productService �ٳ��");
//		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
//		
		// Model �� View ����
		System.out.println("map�� ���� �� : "+map.get("list"));
		
		return (List<Product>)map.get("list");
	}
	
	@RequestMapping( value="json/listProduct", method=RequestMethod.POST )
	public List<Product> listProduct( @RequestBody Map<String, Object> jsonMap ) throws Exception{

		ObjectMapper objectmapper = new ObjectMapper();
		Search search = objectmapper.convertValue(jsonMap.get("search"), Search.class);
		
		System.out.println("json/listProduct.do GET");
		System.out.println("@RequestBody jsonMap : "+jsonMap);
		
//		Search search = new Search();
		search.setCurrentPage(search.getCurrentPage());
		search.setPageSize(pageSize);
		System.out.println("������ ��"+pageSize);
		// Business logic ����
		Map<String , Object> map=productService.getProductList(search);
		System.out.println("productService �ٳ��");
		
//		result.put("resultPage", resultPage);
//		result.put("search", search);
		System.out.println("map�� ���� �� : "+map.get("list"));
		
		return (List<Product>)map.get("list");
	}
	
	@RequestMapping( value="json/deleteProduct" )
	public Map<String, Object> deleteProduct( @RequestBody Map<String, Object> mama ) throws Exception {
		System.out.println("json/deleteProduct ����");

		ObjectMapper objectmapper = new ObjectMapper();
		String[] prodNoArr = objectmapper.convertValue(mama.get("list"), String[].class);
		Search search = objectmapper.convertValue(mama.get("search"), Search.class);
		
		System.out.println("�̾Ҵ�");
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		
		search.setPageSize(pageSize);

		Map<String , Object> map=productService.getProductList(search);
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("�������");
		productService.deleteProduct(prodNoArr);
		System.out.println("�����Դ�");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", map.get("list"));
		result.put("resultPage", resultPage);
		result.put("search", search);
		System.out.println("json/deleteProduct �Ϸ�"+resultPage);
		
		return result;
		
	}
	
	@RequestMapping(value = "json/searchPocketmon", produces = "application/text;charset=utf8")
	public String searchPocketmon(@RequestParam(value="libSearch") String libSearch , HttpServletRequest request ) throws Exception {
		
		request.setCharacterEncoding("euc-kr");
		System.out.println("json/searchPocketmon");
//		ObjectMapper objectmapper = new ObjectMapper();
//		Search search = objectmapper.convertValue(map.get("search"), Search.class);
//		String searchKeyword = search.getSearchKeyword();
		System.out.println("�˻� �ܾ� : "+libSearch);
		System.out.println("request���� libSearch : "+request.getParameter("libSearch"));

		String url = "https://namu.wiki/w/"+libSearch; //ũ�Ѹ��� url����
		Document doc = null;        //Document���� �������� ��ü �ҽ��� ����ȴ�

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		//select�� �̿��Ͽ� ���ϴ� �±׸� �����Ѵ�. select�� ���ϴ� ���� �������� ���� �߿��� ����̴�.
		Elements element = doc.select("div.wiki-heading-content");    
		System.out.println(element.toString());
		System.out.println("============================================================");

		//Iterator�� ����Ͽ� �ϳ��� �� ��������
		Iterator<Element> ie1 = element.select("div.wiki-paragraph").iterator();
//		Iterator<Element> ie2 = element.select("strong.title").iterator();
		String result ="";
		while (ie1.hasNext()) {
//			System.out.println(ie1.next().text()+"\t"+ie2.next().text());
			result += ie1.next().text();
			//System.out.println(ie1.next().text());
		}
		
		System.out.println("============================================================");
		
		return element.toString();
	}
	
	@RequestMapping(value = "json/randomPocketmon", produces = "application/text;charset=utf8")
	public String searchPocketmon() throws Exception {
		
		System.out.println("json/randomPocketmon");
		Random random = new Random();
		int pocketmonNo = random.nextInt(30)+1;
		System.out.println("�˻��� ���ϸ� ��ȣ�� ==> "+pocketmonNo);
		String pocketmonName = productService.getPocketmon(pocketmonNo);
		System.out.println("�˻��� ���ϸ� �̸��� ==> "+pocketmonName);
		
		String url = "https://namu.wiki/w/"+pocketmonName; //ũ�Ѹ��� url����
		Document doc = null;        //Document���� �������� ��ü �ҽ��� ����ȴ�

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		//select�� �̿��Ͽ� ���ϴ� �±׸� �����Ѵ�. select�� ���ϴ� ���� �������� ���� �߿��� ����̴�.
		Elements element = doc.select("div.wiki-heading-content");    
		System.out.println(element.toString());
		System.out.println("============================================================");

//		//Iterator�� ����Ͽ� �ϳ��� �� ��������
//		Iterator<Element> ie1 = element.select("div.wiki-paragraph").iterator();
////		Iterator<Element> ie2 = element.select("strong.title").iterator();
//		String result ="";
//		while (ie1.hasNext()) {
////			System.out.println(ie1.next().text()+"\t"+ie2.next().text());
//			result += ie1.next().text();
//			//System.out.println(ie1.next().text());
//		}
//		
//		System.out.println("============================================================");
		
		return element.toString();
	}
	
	@RequestMapping(value="/uploadImage", method = RequestMethod.POST, produces="text/plain;Charset=UTF-8")
    @ResponseBody
    public List<String> uploadContent(@RequestParam(required = false)MultipartFile[] file) throws Exception{
		List<String> saveName = new ArrayList<String> ();
		
		if( file != null ) {
			for (int i = 0 ; i <file.length ; i++) {
				saveName.add(saveFile(file[i]));
			}
		}
		
		return saveName;
	}
	
	
	@RequestMapping( value="json/listBest", method=RequestMethod.POST )
	public List<Product> listBest( ) throws Exception{

		System.out.println("json/listBest.do POST");
		
		Map<String , Object> map=productService.getBestProduct();
		System.out.println("map�� ���� �� : "+map.get("list"));
		
		return (List<Product>)map.get("list");
	}
	
	private static final String UPLOAD_PATH = "C:\\workspace\\11.Model2MVCShop\\WebContent\\images\\uploadFiles";
	
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