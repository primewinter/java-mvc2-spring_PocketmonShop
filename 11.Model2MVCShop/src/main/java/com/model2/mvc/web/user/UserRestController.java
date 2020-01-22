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

//==> ȸ������ RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {

	/// Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	// setter Method ���� ����

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
		
		System.out.println("�Է��� id&pw::" + user);
		
		User dbUser = userService.getUser(user.getUserId());
		
		System.out.println("�Է��� password :: "+user.getPassword());
		System.out.println("password ::: "+dbUser.getPassword());
		
		if (user.getPassword().equals(dbUser.getPassword())) {
			session.setAttribute("user", dbUser);
			System.out.println("�α��� �����ߴ�~~");
		}
		
		return dbUser;

	}
	
	@RequestMapping(value = "json/kakaoLogin", method = RequestMethod.POST)
	public String login(@RequestBody Map<String, Object> map ) throws Exception {

		System.out.println("/user/json/kakaoLogin : POST");
		System.out.println("�Ѿ�� Map : "+map);
		
		String accessToken = (String)map.get("access_token");
		System.out.println("json���� ���� accessToken :: "+accessToken);
		
		
		/*
		 * // HttpClient : Http Protocol �� client �߻�ȭ HttpClient httpClient = new
		 * DefaultHttpClient();
		 * 
		 * String url= "http://127.0.0.1:8080/user/json/getUser/test01";
		 * 
		 * // HttpGet : Http Protocol �� GET ��� Request HttpGet httpGet = new
		 * HttpGet(url); httpGet.setHeader("Accept", "application/json");
		 * httpGet.setHeader("Content-Type", "application/json");
		 * 
		 * // HttpResponse : Http Protocol ���� Message �߻�ȭ HttpResponse httpResponse =
		 * httpClient.execute(httpGet);
		 * 
		 * //==> Response Ȯ�� System.out.println(httpResponse); System.out.println();
		 * 
		 * //==> Response �� entity(DATA) Ȯ�� HttpEntity httpEntity =
		 * httpResponse.getEntity();
		 * 
		 * //==> InputStream ���� InputStream is = httpEntity.getContent(); BufferedReader
		 * br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		 * 
		 * //==> �ٸ� ������� serverData ó��
		 * //System.out.println("[ Server ���� ���� Data Ȯ�� ] "); //String serverData =
		 * br.readLine(); //System.out.println(serverData);
		 * 
		 * //==> API Ȯ�� : Stream ��ü�� ���� ���� JSONObject jsonobj =
		 * (JSONObject)JSONValue.parse(br); System.out.println(jsonobj);
		 * 
		 * ObjectMapper objectMapper = new ObjectMapper(); User user =
		 * objectMapper.readValue(jsonobj.toString(), User.class);
		 * System.out.println(user);
		 */
				 
				 
		
		return accessToken;
	}

		    

}