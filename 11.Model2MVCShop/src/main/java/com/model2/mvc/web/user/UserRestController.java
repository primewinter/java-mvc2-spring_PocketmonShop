package com.model2.mvc.web.user;


import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.util.TaskTestService;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;

//==> 회원관리 RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {

	/// Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	// setter Method 구현 않음

	public UserRestController() {
		System.out.println(this.getClass());
	}

	@RequestMapping(value = "json/getUser/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId) throws Exception {

		System.out.println("/user/json/getUser : GET");

		// Business Logic
		return userService.getUser(userId);
	}

	@RequestMapping(value = "json/login", method = RequestMethod.POST)
	public User login(@RequestBody Map<String, Object> map, HttpSession session) throws Exception {

		System.out.println("/user/json/login : POST");
		String userId = (String)map.get("userId");
		String password = (String)map.get("password");
		
		User user = new User();
		user.setUserId(userId);
		user.setPassword(password);
		
		System.out.println("입력한 id&pw::" + user);
		
		User dbUser = userService.getUser(user.getUserId());
		
		System.out.println("입력한 password :: "+user.getPassword());
		System.out.println("password ::: "+dbUser.getPassword());
		
		if (user.getPassword().equals(dbUser.getPassword())) {
			session.setAttribute("user", dbUser);
			System.out.println("로그인 성공했다~~");
		}
		
		return dbUser;

	}
	
	@RequestMapping(value = "json/kakaoLogin", method = RequestMethod.POST)
	public String login(@RequestBody Map<String, Object> map ) throws Exception {

		System.out.println("/user/json/kakaoLogin : POST");
		System.out.println("넘어온 Map : "+map);
		
		String accessToken = (String)map.get("access_token");
		System.out.println("json으로 받은 accessToken :: "+accessToken);
		
		
		/*
		 * // HttpClient : Http Protocol 의 client 추상화 HttpClient httpClient = new
		 * DefaultHttpClient();
		 * 
		 * String url= "http://127.0.0.1:8080/user/json/getUser/test01";
		 * 
		 * // HttpGet : Http Protocol 의 GET 방식 Request HttpGet httpGet = new
		 * HttpGet(url); httpGet.setHeader("Accept", "application/json");
		 * httpGet.setHeader("Content-Type", "application/json");
		 * 
		 * // HttpResponse : Http Protocol 응답 Message 추상화 HttpResponse httpResponse =
		 * httpClient.execute(httpGet);
		 * 
		 * //==> Response 확인 System.out.println(httpResponse); System.out.println();
		 * 
		 * //==> Response 중 entity(DATA) 확인 HttpEntity httpEntity =
		 * httpResponse.getEntity();
		 * 
		 * //==> InputStream 생성 InputStream is = httpEntity.getContent(); BufferedReader
		 * br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		 * 
		 * //==> 다른 방법으로 serverData 처리
		 * //System.out.println("[ Server 에서 받은 Data 확인 ] "); //String serverData =
		 * br.readLine(); //System.out.println(serverData);
		 * 
		 * //==> API 확인 : Stream 객체를 직접 전달 JSONObject jsonobj =
		 * (JSONObject)JSONValue.parse(br); System.out.println(jsonobj);
		 * 
		 * ObjectMapper objectMapper = new ObjectMapper(); User user =
		 * objectMapper.readValue(jsonobj.toString(), User.class);
		 * System.out.println(user);
		 */
				 
				 
		
		return accessToken;
	}

		    

}