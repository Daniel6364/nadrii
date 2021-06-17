package com.yagn.nadrii.service.ticket.test;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yagn.nadrii.common.OpenApiSearch;
import com.yagn.nadrii.service.domain.DetailImage;
import com.yagn.nadrii.service.domain.DetailIntro;
import com.yagn.nadrii.service.domain.Purchase;
import com.yagn.nadrii.service.domain.TourTicket;
import com.yagn.nadrii.service.domain.User;
import com.yagn.nadrii.service.purchase.PurchaseService;
import com.yagn.nadrii.service.ticket.TicketDao;
import com.yagn.nadrii.service.ticket.TicketService;
import com.yagn.nadrii.service.ticket.impl.NaverApiDaoImpl;
import com.yagn.nadrii.service.user.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml", 
										"classpath:config/context-aspect.xml",
										"classpath:config/context-mybatis.xml", 
										"classpath:config/context-transaction.xml" })
public class TicketServiceTest {

	@Autowired
	@Qualifier("ticketServiceImpl")
	private TicketService ticketService;

	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;


	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	
	@Test
	public void testGetTicketListAll() throws Exception {

		OpenApiSearch openApiSearch = new OpenApiSearch();
		openApiSearch.setPageNo(4);
		openApiSearch.setNumOfRows(10);
		
		Map<String, Object> map = ticketService.getTicketList(openApiSearch);
		
	}
	 
	//@Test
	public void testGetTicket() throws Exception {

//		int contentId = 790124;
//		int contentTypeId = 15;
		int contentId = 2531711;
		int contentTypeId = 15;
		
		DetailIntro detailIntro = new DetailIntro(); 
		detailIntro = ticketService.getTicket(contentId, contentTypeId);
		
		System.out.println("[testGetTicket] : " + detailIntro);

		Assert.assertEquals(2531711, detailIntro.getContentid());
		Assert.assertEquals(15, detailIntro.getContenttypeid());

	}
	
	//@Test
	public void testGetDetailImage() throws Exception {

		String title = null;
		int contentId = 1815964;		
//		int contentId = 737479;			
//		int contentId = 2531711;		

		DetailImage detailImage = new DetailImage();
		detailImage = ticketService.getDetailImage(contentId, title);

		System.out.println("[testGetDetailImage] : " + detailImage);

//		Assert.assertEquals(null, detailImage.getContentid());
	
	}
	
	//@Test
	public void testGetNaverImage() throws Exception {

		String title = "룸넘버 13";			
//		String clientID = "hC9Dwk1KGJiiTZ79onoy";
//		String clientSecret = "cLODYnjh2Y";

		String returnImage = ticketService.getNaverImage(title);

//		System.out.println("[testGetNaverImage] : " + returnImage);

//		Assert.assertEquals(null, detailImage.getContentid());
	
	}
	
	//@Test
	public void testGetKakaoImage() throws Exception {
		
		String title = "룸넘버 13";			
//		String clientID = "hC9Dwk1KGJiiTZ79onoy";
//		String clientSecret = "cLODYnjh2Y";

		String encodeTitle = URLEncoder.encode(title, "UTF-8");
		
		
		String returnImage = ticketService.getKakaoImage(title);

		// ==> console Ȯ��
//		System.out.println("[testGetNaverImage] : " + returnImage);

		// ==> API Ȯ��
//		Assert.assertEquals(null, detailImage.getContentid());
	
	}
	
	@Test
	public void testGetQRCode() throws Exception {

		System.out.println("\n[GetQRCode TEST]");
		
		User user = new User();
		Purchase purchase = new Purchase();
		purchase.setBuyerId("user6364");
//		purchase.setTicketTitle("태안 세계튤립축제");
//		purchase.setBookingDate("2018년 3월 15일"); ;
//		purchase.setBuyerName("김연아 테스트 이름 길게 붙여서 뒤에 값 어떻게 보여지는지 확인중");
		
		String getQRCode = purchaseService.getQRCode(purchase);
		
		System.out.println("\n[testGetQRCode] : " + getQRCode);

		// ==> API Ȯ��
//		Assert.assertEquals(null, detailImage.getContentid());

	}
	
	//@Test
		public void testAddPurchase() throws Exception {

			System.out.println("\n[addPurchase : addBasket TEST]");
			
			Purchase purchase = new Purchase();
			
			
			
			String getQRCode = purchaseService.getQRCode(purchase);
			
			System.out.println("\n[testGetQRCode] : " + getQRCode);


		}
	
	
	
	 
} // end of class