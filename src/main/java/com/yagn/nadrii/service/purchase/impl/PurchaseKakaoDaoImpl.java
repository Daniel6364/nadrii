package com.yagn.nadrii.service.purchase.impl;

import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yagn.nadrii.service.domain.KakaoPayRequest;
import com.yagn.nadrii.service.domain.KakaoPayResponse;
import com.yagn.nadrii.service.domain.Purchase;
import com.yagn.nadrii.service.purchase.PurchaseDao;

@Repository("purchaseKakaoDaoImpl")
public class PurchaseKakaoDaoImpl implements PurchaseDao {

	/// Field
	
	/// Constructor
	public PurchaseKakaoDaoImpl() {
		System.out.println(this.getClass());
	}
	
	/// KakaoPay API properties
	@Value("#{kakaoApiProperties['kakaoAdminKey']}")
	private String kakaoAdminKey;
	
	@Value("#{kakaoApiProperties['kakaoPayURL']}")
	private String kakaoPayURL;
	
	@Value("#{kakaoApiProperties['kakaoPayCompleteURL']}")
	private String kakaoPayCompleteURL;
	
	
	
	
	public static final KakaoPayResponse sendPostKakaoURL(KakaoPayRequest kakaoPayRequest, String kakaoPayURL, String kakaoAdminKey) throws Exception {
		
		System.out.println("\n[purchaseKakaoDaoImpl.java]::sendPostKakaoURL");
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(kakaoPayURL);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		httpPost.setHeader("Authorization", kakaoAdminKey);
		
		HttpEntity httpEntity = new ByteArrayEntity(kakaoPayRequest.toString().getBytes("UTF-8"));
		httpPost.setEntity(httpEntity);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		KakaoPayResponse kakaoPayResponse = new ObjectMapper().readValue(
				EntityUtils.toString(httpResponse.getEntity()), KakaoPayResponse.class);
		
		return kakaoPayResponse;
		
	}
	
	public static final KakaoPayResponse sendKakaoPayComplete(KakaoPayRequest kakaoPayRequest, String kakaoPayCompleteURL, String kakaoAdminKey) throws Exception {
		
		System.out.println("\n[purchaseKakaoDaoImpl.java]::sendKakaoPayComplete");
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(kakaoPayCompleteURL);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		httpPost.setHeader("Authorization", kakaoAdminKey);
		
		HttpEntity httpEntity = new ByteArrayEntity(kakaoPayRequest.toString().getBytes("UTF-8"));
		httpPost.setEntity(httpEntity);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		KakaoPayResponse kakaoPayResponse = new ObjectMapper().readValue(
				EntityUtils.toString(httpResponse.getEntity()), KakaoPayResponse.class);
		
		return kakaoPayResponse;
		
	}

	@Override
	public KakaoPayResponse addKakaoPayment(KakaoPayRequest kakaoPayRequest) {
		
		System.out.println("\n[purchaseKakaoDaoImpl.java]::addKakaoPayment");
		
		KakaoPayResponse kakaoPayResponse = new KakaoPayResponse();
		
		try {
			kakaoPayResponse = PurchaseKakaoDaoImpl.sendPostKakaoURL(kakaoPayRequest, kakaoPayURL, kakaoAdminKey);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return kakaoPayResponse;
	}
	
	@Override
	public KakaoPayResponse addKakaoPayComplete(KakaoPayRequest kakaoPayRequest) {
		
		System.out.println("\n[purchaseKakaoDaoImpl.java]::addKakaoPayComplete");
		
		KakaoPayResponse kakaoPayResponse = new KakaoPayResponse();
		
		try {
			kakaoPayResponse = PurchaseKakaoDaoImpl.sendKakaoPayComplete(kakaoPayRequest, kakaoPayCompleteURL, kakaoAdminKey);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		return kakaoPayResponse;
	}
	

	////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		
	}
	
	@Override
	public List<Purchase> getBasketList(Map<String, Object> map) throws Exception {
		return null;
	}
	
	@Override
	public int getTotalCount(String buyerId) throws Exception {
		return 0;
	}
	
	@Override
	public List<Purchase> addBasketTicket(List<Integer> sendPostNo) throws Exception {
		return null;
	}
	
	@Override
	public void updateBasketPurchase(List<Integer> postNo) throws Exception {
	}
	
	@Override
	public void deleteBasketList(List<Integer> postNo) throws Exception {
	}
	
	@Override
	public List<Purchase> getPurchaseList(Map<String, Object> map) throws Exception {
		return null;
	}
	
	@Override
	public String getQRCode(Purchase purchase) throws Exception {
		return null;
	}
	
	@Override
	public void updatePurchaseQR(int postNo) throws Exception {
	}
	
	@Override
	public List<Purchase> getPurchasedList(Map<String, Object> map) throws Exception {
		return null;
	}
	
} // end of class
