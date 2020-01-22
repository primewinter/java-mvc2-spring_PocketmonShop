package com.model2.mvc.common.push;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.User;

public class RestHttpClient {
	///Field
	///Constructor
	public RestHttpClient(){
	}
	///Method
	//1.1 Http Protocol GET Request : JsonSimple 3rd party lib �궗�슜
	public User  getJsonUser01(String userId) throws Exception{

		// HttpClient : Http Protocol �쓽 client 異붿긽�솕
		HttpClient httpClient = new DefaultHttpClient();

		String url= 	"http://192.168.0.82:8080/user/json/getUser/"+userId.trim();

		// HttpGet : Http Protocol �쓽 GET 諛⑹떇 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");

		// HttpResponse : Http Protocol �쓳�떟 Message 異붿긽�솕
		HttpResponse httpResponse = httpClient.execute(httpGet);

		//==> Response 以� entity(DATA) �솗�씤
		HttpEntity httpEntity = httpResponse.getEntity();

		//==> InputStream �깮�꽦
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

		//System.out.println("[ Server �뿉�꽌 諛쏆� Data �솗�씤 ] ");
		String serverData = br.readLine();
		System.out.println("JSON : "+ serverData);

		if( serverData == null){
			return null;
		}

		//==> �궡�슜�씫湲�(JSON Value �솗�씤)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println("JSON Simple Object : " + jsonobj);

		User user = new User();
		user.setUserId( jsonobj.get("userId").toString() );

		return user;
	}


	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib �궗�슜
	public User  getJsonUser02(String userId) throws Exception{

		// HttpClient : Http Protocol �쓽 client 異붿긽�솕
		HttpClient httpClient = new DefaultHttpClient();

		String url= 	"http://192.168.123.185:8080/user/json/getUser/"+userId.trim();

		// HttpGet : Http Protocol �쓽 GET 諛⑹떇 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");

		// HttpResponse : Http Protocol �쓳�떟 Message 異붿긽�솕
		HttpResponse httpResponse = httpClient.execute(httpGet);

		//==> Response 以� entity(DATA) �솗�씤
		HttpEntity httpEntity = httpResponse.getEntity();

		//==> InputStream �깮�꽦
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

		//==> �떎瑜� 諛⑸쾿�쑝濡� serverData 泥섎━
		//System.out.println("[ Server �뿉�꽌 諛쏆� Data �솗�씤 ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);

		//==> API �솗�씤 : Stream 媛앹껜瑜� 吏곸젒 �쟾�떖
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println("JSON Simple Object : " + jsonobj);

		if( jsonobj == null){
			return null;
		}

		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(jsonobj.toString(), User.class);
		System.out.println(user);

		return user;
	}
}