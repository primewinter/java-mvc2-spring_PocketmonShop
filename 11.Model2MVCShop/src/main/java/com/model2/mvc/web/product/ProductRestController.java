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


//==> 회원관리 Controller
@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
		
	public ProductRestController(){
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
	
	
	@RequestMapping( value = "json/addProduct", method=RequestMethod.POST)
	public void addProduct( @RequestBody Product product ) throws Exception {

		System.out.println("/json/addProduct : POST");
		System.out.println("@RequestBody PRODUCT : "+product);
		
		productService.addProduct(product);
		
	}
	
	@RequestMapping( value = "json/getProduct/{prodNo}", method=RequestMethod.GET)
	public Map<String, Object> getProduct( @PathVariable int prodNo ) throws Exception {
		
		System.out.println("/getProduct.do");
		System.out.println("보고 싶은 상품 번호 : "+prodNo);
		//Business Logic
		Product product = productService.getProduct(prodNo);
		// Model 과 View 연결
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product", product);
		
		return map;
	}
	
	@RequestMapping( value="json/updateProduct/{prodNo}", method=RequestMethod.GET)
	public  Map<String, Object> updateProduct( @PathVariable int prodNo ) throws Exception{

		System.out.println("/updateUserView.do");
		//Business Logic
		// Model 과 View 연결
		Product product = productService.getProduct(prodNo);
		
		// Model 과 View 연결
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
		System.out.println("페이지 냠");
		// Business logic 수행
		Map<String , Object> map=productService.getProductList(search);
		System.out.println("productService 다녀옴");
//		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
//		
		// Model 과 View 연결
		System.out.println("map에 담은 것 : "+map.get("list"));
		
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
		System.out.println("페이지 냠"+pageSize);
		// Business logic 수행
		Map<String , Object> map=productService.getProductList(search);
		System.out.println("productService 다녀옴");
		
//		result.put("resultPage", resultPage);
//		result.put("search", search);
		System.out.println("map에 담은 것 : "+map.get("list"));
		
		return (List<Product>)map.get("list");
	}
	
	@RequestMapping( value="json/deleteProduct" )
	public Map<String, Object> deleteProduct( @RequestBody Map<String, Object> mama ) throws Exception {
		System.out.println("json/deleteProduct 시작");

		ObjectMapper objectmapper = new ObjectMapper();
		String[] prodNoArr = objectmapper.convertValue(mama.get("list"), String[].class);
		Search search = objectmapper.convertValue(mama.get("search"), Search.class);
		
		System.out.println("뽑았다");
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		
		search.setPageSize(pageSize);

		Map<String , Object> map=productService.getProductList(search);
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("만들었다");
		productService.deleteProduct(prodNoArr);
		System.out.println("갔따왔다");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", map.get("list"));
		result.put("resultPage", resultPage);
		result.put("search", search);
		System.out.println("json/deleteProduct 완료"+resultPage);
		
		return result;
		
	}
	
	@RequestMapping(value = "json/searchPocketmon", produces = "application/text;charset=utf8")
	public String searchPocketmon(@RequestParam(value="libSearch") String libSearch , HttpServletRequest request ) throws Exception {
		
		request.setCharacterEncoding("euc-kr");
		System.out.println("json/searchPocketmon");
//		ObjectMapper objectmapper = new ObjectMapper();
//		Search search = objectmapper.convertValue(map.get("search"), Search.class);
//		String searchKeyword = search.getSearchKeyword();
		System.out.println("검색 단어 : "+libSearch);
		System.out.println("request에서 libSearch : "+request.getParameter("libSearch"));

		String url = "https://namu.wiki/w/"+libSearch; //크롤링할 url지정
		Document doc = null;        //Document에는 페이지의 전체 소스가 저장된다

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		//select를 이용하여 원하는 태그를 선택한다. select는 원하는 값을 가져오기 위한 중요한 기능이다.
		Elements element = doc.select("div.wiki-heading-content");    
		System.out.println(element.toString());
		System.out.println("============================================================");

		//Iterator을 사용하여 하나씩 값 가져오기
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
		System.out.println("검색할 포켓몬 번호는 ==> "+pocketmonNo);
		String pocketmonName = productService.getPocketmon(pocketmonNo);
		System.out.println("검색한 포켓몬 이름은 ==> "+pocketmonName);
		
		String url = "https://namu.wiki/w/"+pocketmonName; //크롤링할 url지정
		Document doc = null;        //Document에는 페이지의 전체 소스가 저장된다

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		//select를 이용하여 원하는 태그를 선택한다. select는 원하는 값을 가져오기 위한 중요한 기능이다.
		Elements element = doc.select("div.wiki-heading-content");    
		System.out.println(element.toString());
		System.out.println("============================================================");

//		//Iterator을 사용하여 하나씩 값 가져오기
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
		System.out.println("map에 담은 것 : "+map.get("list"));
		
		return (List<Product>)map.get("list");
	}
	
	private static final String UPLOAD_PATH = "C:\\workspace\\11.Model2MVCShop\\WebContent\\images\\uploadFiles";
	
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