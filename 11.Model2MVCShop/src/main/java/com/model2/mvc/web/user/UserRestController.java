package com.model2.mvc.web.user;


import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		
		System.out.println("::" + user);
		User dbUser = userService.getUser(user.getUserId());

		if (user.getPassword().equals(dbUser.getPassword())) {
			session.setAttribute("user", dbUser);
		}

		return dbUser;
	}
	
	@RequestMapping(value = "json/kakaoLogin", method = RequestMethod.POST)
	public String login(@RequestBody Map<String, Object> map ) throws Exception {

		System.out.println("/user/json/kakaoLogin : POST");
		System.out.println("�Ѿ�� Map : "+map);
		
		String accessToken = (String)map.get("access_token");
		System.out.println("json���� ���� accessToken :: "+accessToken);
		
		return accessToken;
	}

		    

}