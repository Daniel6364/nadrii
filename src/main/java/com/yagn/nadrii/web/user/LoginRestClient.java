package com.yagn.nadrii.web.user;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yagn.nadrii.service.domain.User;
import com.yagn.nadrii.service.domain.kakaoLogin.TokenRequest;
import com.yagn.nadrii.service.domain.kakaoLogin.TokenResponse;
import com.yagn.nadrii.service.domain.naverLogin.NaverLoginRequest;
import com.yagn.nadrii.service.domain.naverLogin.NaverLoginResponse;
import com.yagn.nadrii.service.domain.naverLogin.UserResponse;

public class LoginRestClient {
	public LoginRestClient() {}
	
	public static TokenResponse loginToken(String code) throws Exception {
		
		HttpClient httpClient = new DefaultHttpClient();
		String url = "https://kauth.kakao.com/oauth/token";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		
		TokenRequest tokenRequest = new TokenRequest();
		tokenRequest.setCode(code);
		
		HttpEntity httpEntity = new ByteArrayEntity(tokenRequest.toString().getBytes("UTF-8"));
		httpPost.setEntity(httpEntity);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		httpEntity = httpResponse.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"));
		JSONObject object = (JSONObject)JSONValue.parse(br);
		ObjectMapper objectMapper = new ObjectMapper();
		
		return objectMapper.readValue(object.toString(), TokenResponse.class);
	}
	
	public static JSONObject getProfile(String token) throws Exception {
		
		HttpClient httpClient = new DefaultHttpClient();
		String url = "https://kapi.kakao.com/v1/user/me";
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Authorization", "Bearer "+token);
		httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		System.out.println(httpResponse);
		
		HttpEntity httpEntity = httpResponse.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"));
		JSONObject object = (JSONObject)JSONValue.parse(br);
		
		return object;
	}
	
	public static void signUp(String token) throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		String url = "https://kapi.kakao.com/v1/user/signup";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Authorization", token);
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		
//		JSONObject properties = new JSONObject();
//		properties.put("age", "23");
//		properties.put("gender", "female");
//		
//		HttpEntity httpEntity01 = new StringEntity(properties.toString(),"UTF-8");
//
//		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		

		System.out.println(httpResponse);
		System.out.println();


		HttpEntity httpEntity = httpResponse.getEntity();
		

		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server Data ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		

		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	public static User getUserInfo(String token) throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		String url = "https://kapi.kakao.com/v1/user/me";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Authorization", "Bearer "+token);
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		
		JSONArray propertyKeys = new JSONArray();
		propertyKeys.add("kaccount_email");
		propertyKeys.add("kaccount_email_verified");
		propertyKeys.add("thumbnail_image");
//		properties.put("gender", "female");
//		
		HttpEntity httpEntity01 = new StringEntity(propertyKeys.toString(),"UTF-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		

		System.out.println(httpResponse);
		System.out.println();


		HttpEntity httpEntity = httpResponse.getEntity();
		

		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server Data  ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		

		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		ObjectMapper mapper = new ObjectMapper();
		
		User user = new User();
		user.setUserId(jsonobj.get("id").toString());
		user.setEmail(jsonobj.get("kaccount_email").toString());

		return user;
	}
	
	public static NaverLoginResponse getNaverToken(String code , String state) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		String url = "https://nid.naver.com/oauth2.0/token";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		
		NaverLoginRequest loginRequest = new NaverLoginRequest();
		loginRequest.setCode(code);
		loginRequest.setGrant_type("authorization_code");
		loginRequest.setClient_id("hkuEu0FBgOT1dsQixJU3");
		loginRequest.setClient_secret("dm1JX0XXqi");
		loginRequest.setState(state);
		
		HttpEntity httpEntity = new ByteArrayEntity(loginRequest.toString().getBytes("UTF-8"));
		httpPost.setEntity(httpEntity);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		httpEntity = httpResponse.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"));
		JSONObject object = (JSONObject)JSONValue.parse(br);
		ObjectMapper objectMapper = new ObjectMapper();
		NaverLoginResponse loginResponse = objectMapper.readValue(object.toString(), NaverLoginResponse.class);
		
		return loginResponse;
	}
	
	public static User getNaverUserInfo(NaverLoginResponse tokenInfo) throws Exception 	{
		HttpClient httpClient = new DefaultHttpClient();
		String url = "https://openapi.naver.com/v1/nid/me";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		httpPost.setHeader("Authorization", tokenInfo.getToken_type()+" "+tokenInfo.getAccess_token());
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"));
		JSONObject object = (JSONObject)JSONValue.parse(br);
		ObjectMapper objectMapper = new ObjectMapper();
		UserResponse response = objectMapper.readValue(object.get("response").toString(), UserResponse.class);
		User user = new User();
		user.setEmail(response.getEmail());
		user.setUserId(response.getId());
		user.setUserName(response.getName());

		return user;
	}
	
	public static String logoutKakao(String token) throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		String url = "https://kapi.kakao.com/v1/user/logout";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		httpPost.setHeader("Authorization", "Bearer "+token);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"));
		JSONObject object = (JSONObject)JSONValue.parse(br);
		ObjectMapper objectMapper = new ObjectMapper();
		String id = object.toString().split(":")[0];
		
		return id;
	}
}

