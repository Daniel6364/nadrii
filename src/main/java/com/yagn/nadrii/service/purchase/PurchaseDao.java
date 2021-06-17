package com.yagn.nadrii.service.purchase;

import java.util.List;
import java.util.Map;

import com.yagn.nadrii.service.domain.KakaoPayRequest;
import com.yagn.nadrii.service.domain.KakaoPayResponse;
import com.yagn.nadrii.service.domain.Purchase;

public interface PurchaseDao {
	
	public void addPurchase(Purchase purchase) throws Exception ;

	public String getQRCode(Purchase purchase) throws Exception ;

	public List<Purchase> getBasketList(Map<String, Object> map) throws Exception ; 
	
	public List<Purchase> getPurchaseList(Map<String, Object> map) throws Exception ;

	public List<Purchase> getPurchasedList(Map<String, Object> map) throws Exception ;
	
	public int getTotalCount(String buyerId) throws Exception ;
	
	public List<Purchase> addBasketTicket(List<Integer> sendPostNo) throws Exception ;
	
	public void updateBasketPurchase(List<Integer> postNo) throws Exception ;

	public void updatePurchaseQR(int postNo) throws Exception ;
	
	public void deleteBasketList(List<Integer> postNo) throws Exception ;
	
	/// KakaoPay API
	public KakaoPayResponse addKakaoPayment(KakaoPayRequest kakaoPayRequest) throws Exception;
	
	public KakaoPayResponse addKakaoPayComplete(KakaoPayRequest kakaoPayRequest) throws Exception;
}
