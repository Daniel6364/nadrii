package com.yagn.nadrii.service.purchase;

import java.util.List;
import java.util.Map;

import com.yagn.nadrii.common.OpenApiSearch;
import com.yagn.nadrii.service.domain.KakaoPayRequest;
import com.yagn.nadrii.service.domain.KakaoPayResponse;
import com.yagn.nadrii.service.domain.Purchase;

public interface PurchaseService {

	public void addPurchase(Purchase purchase) throws Exception;
	
	public Map<String, Object> getBasketList(OpenApiSearch openApiSearch, String buyerId) throws Exception;
	
	public Map<String, Object> getPurchaseList(OpenApiSearch openApiSearch, String buyerId) throws Exception;

	public Map<String, Object> getPurchasedList(OpenApiSearch openApiSearch, String buyerId) throws Exception;

	public List<Purchase> addBasketTicket(Purchase purchase) throws Exception;
	
	public void updateBasketPurchase(Purchase purchase) throws Exception; 

	public void updatePurchaseQR(int postNo) throws Exception; 
	
	public void deleteBasketList(Purchase purchase) throws Exception;
	
	public String getQRCode(Purchase purchase) throws Exception;
	
	/// KakaoPay API
	public KakaoPayResponse addKakaoPayment(KakaoPayRequest kakaoPayRequest) throws Exception;
	
	public KakaoPayResponse addKakaoPayComplete(KakaoPayRequest kakaoPayRequest) throws Exception;
	
}
