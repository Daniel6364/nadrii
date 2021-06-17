package com.yagn.nadrii.web.purchase;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yagn.nadrii.service.domain.Purchase;
import com.yagn.nadrii.service.purchase.PurchaseService;
import com.yagn.nadrii.service.user.UserService;

@RestController
@RequestMapping("/purchase/*")
public class PurchaseRestController {

	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	/// Constructor
	public PurchaseRestController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="json/addBasket", method=RequestMethod.POST)
	public void addPurchase(
			@RequestBody JSONObject basketData
			) {

		System.out.println("\n/purchase/json/addBasket : POST");
		
		Purchase purchase = new Purchase();
		
		try {
			
				JSONObject jsonObj = (JSONObject) JSONValue.parse(basketData.toJSONString());
				System.out.println("\n[JSONObject jsonObj] ==> " + jsonObj.toString());

				ObjectMapper objectMapper = new ObjectMapper();
				purchase = new Purchase();
				purchase = objectMapper.readValue(jsonObj.toJSONString(), Purchase.class);

				// cancelDate making algorithm
				DateFormat df = new SimpleDateFormat("yyyyMMdd");
				Date bDate = df.parse(purchase.getBookingDate().replaceAll("[^0-9]", ""));
				Calendar cal = Calendar.getInstance();
				cal.setTime(bDate);
				cal.add(Calendar.DATE, -10);

				String cancelDate = df.format(cal.getTime()).substring(0, 4) + " 년"
						+ df.format(cal.getTime()).substring(4, 6) + " 월" + df.format(cal.getTime()).substring(6) + " 일";

				// cancelDate set
				purchase.setCancelDate(cancelDate);
				purchase.setBuyer(userService.getUser(purchase.getBuyerId()));
				purchase.setQrCode("StepOfBasket");

				System.out.println("\n[purchase.buyer check] ==> " + purchase.getBuyer() );
				System.out.println("\n[Purchase Domain check] ==> " + purchase.toString());
				System.out.println("\n[Purchase Domain buyer check] ==> " + purchase.getBuyer());
				System.out.println("\n");

				if (purchase.getBuyer().getUserName() == null) {
					purchase.getBuyer().setUserName(purchase.getBuyerName());
				}
				if (purchase.getBuyer().getPhone() == null) {
					purchase.getBuyer().setPhone(purchase.getBuyerPhone());
				}
				
				purchaseService.addPurchase(purchase);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println("[addBasket Complete]");
	
	} // end of method

	
	@RequestMapping(value="json/deleteBasketList", method=RequestMethod.POST) 
	public void deleteBasketList(
			@RequestBody JSONObject basketData
			) {
		
		System.out.println("\n /purchase/json/deleteBasketList : POST");
		
		Purchase purchase = new Purchase();
		
		try {
			
			JSONObject jsonObj = (JSONObject) JSONValue.parse(basketData.toJSONString());
			
			ObjectMapper objectMapper = new ObjectMapper();
			purchase = new Purchase();
			purchase = objectMapper.readValue(jsonObj.toJSONString(), Purchase.class);
			
			purchaseService.deleteBasketList(purchase);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	@RequestMapping(value="json/updatePurchaseList", method=RequestMethod.POST) 
	public void updatePurchaseList(
			@RequestBody String postNo
			) {
		
		System.out.println("\n /purchase/json/updatePurchaseList : POST");
		
		System.out.println("\n[check]==>" + postNo);
		
		Purchase purchase = new Purchase();
		
		try {
			
			purchaseService.updatePurchaseQR( Integer.parseInt(postNo) );
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	
} // end of class
