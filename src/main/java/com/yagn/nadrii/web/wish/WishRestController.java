package com.yagn.nadrii.web.wish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yagn.nadrii.service.comm.CommService;
import com.yagn.nadrii.service.domain.Community;
import com.yagn.nadrii.service.domain.Ticket;
import com.yagn.nadrii.service.domain.Trip;
import com.yagn.nadrii.service.domain.User;
import com.yagn.nadrii.service.domain.Wish;
import com.yagn.nadrii.service.ticket.TicketService;
import com.yagn.nadrii.service.trip.TripService;
import com.yagn.nadrii.service.wish.WishService;

@RestController
@RequestMapping("/wish/*")
public class WishRestController {
	
	@Autowired
	@Qualifier("wishServiceImpl")
	private WishService wishService;
	
	@Autowired
	@Qualifier("commServiceImpl")
	private CommService CommService;
	
	@Autowired
	@Qualifier("tripServiceImpl")
	private TripService tripService;
	
	@Autowired
	@Qualifier("ticketServiceImpl")
	private TicketService ticketService;
		
	public WishRestController() {
		System.out.println(this.getClass());
	}

	@RequestMapping("json/addWishFromTrip/{contentId}")
	public Map addWishFromTrip(HttpServletRequest request, @PathVariable("contentId") String contentId) throws Exception{
		
		
		System.out.println("RestController addWishFromTrip");
		Map map = new HashMap();

		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("loginUser");
		System.out.println(user);
		
		Thread.sleep(1000);
		
		Trip trip = tripService.getTripFromDB(contentId);
		System.out.println(trip);
		
		Wish wish = wishService.checkDuplication(trip.getPostNo());
		
		if(wish==null) {
			wish = new Wish();
			wish.setUserId(user.getUserId());
			wish.setTripNo(trip);
			System.out.println(wish);
			
			wishService.addWishListFromTrip(wish);
			map.put("message", "ok");
		}else {
			map.put("message", "fail");
		}
		return map;
	}
	
	@RequestMapping("json/addWishFromTicket/{contentId}")
	public Map addWishFromTicket(
			HttpServletRequest request, 
			@PathVariable("contentId") String contentId
			) throws Exception{
		
		
		System.out.println("RestController addWishFromTicket");
		Map map = new HashMap();

		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("loginUser");
		System.out.println(user);
		
		Thread.sleep(1000);
		
		System.out.println("\n[contentId Check]==>" + contentId);
		
		Ticket ticket = ticketService.getTicketFromDB(contentId);
		System.out.println(ticket);
		
		Wish wish = wishService.checkDuplication(ticket.getPostNo());
		
		if(wish==null) {
			wish = new Wish();
			wish.setUserId(user.getUserId());
			wish.setTicketNo(ticket);
			System.out.println(wish);
			
			wishService.addWishListFromTicket(wish);
			map.put("message", "ok");
		}else {
			map.put("message", "fail");
		}
		return map;
	}
	
	@RequestMapping("/json/deleteWish/{postNo}")
	public Map deleteWish(@PathVariable("postNo") int postNo, HttpSession session) throws Exception{
		
		System.out.println("/wish/json/deleteWish");
		
		Map map = new HashMap();
		Wish wish = new Wish();
		
		wish.setPostNo(postNo);
		wish.setUserId(((User)session.getAttribute("loginUser")).getUserId());
		
		wishService.deleteWish(wish);
		map.put("message", "ok");
		
		return map;
	}
	
	@RequestMapping("json/addWishFromPost/{postNo}")
	public void addWishFromPost(@PathVariable("postNo")int postNo, HttpServletRequest request) throws Exception{
		
		System.out.println("RestController addWishFromPost");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		
		
		System.out.println(user);
		Thread.sleep(1000);
		Community community = CommService.getComm(postNo);
		System.out.println(community);
		Wish wish = new Wish();
		wish.setUserId(user.getUserId());
		wish.setPostNo(community.getPostNo());
		System.out.println(wish);
		
		wishService.addWishListFromPost(wish);
		
		
	}

	private Wish getWishByTripNo(HttpSession session,HttpServletRequest request,@PathVariable("tripNo") int tripNo) throws Exception {
		session =request.getSession(true);
		User user = (User)session.getAttribute("loginUser");
		Map map = new HashMap();
				
		
		Wish wish = wishService.getWishByTripNo(user.getUserId(), tripNo);
		return wish;
	}
	
	@RequestMapping("/json/listWish/{userId}")
	public Map listWish(HttpSession session, HttpServletRequest request) throws Exception {
		System.out.println("listWish");
		session = request.getSession(true);
				
		String userId = (String)session.getAttribute("loginUser");
		
		Map wishMap = wishService.listWish(userId);
		
		Map map = new HashMap();
		map.put("wishMap", wishMap.get("list"));
		
		return map;
	}
		
	@RequestMapping("json/listTripFromWish/{userId}")
	public Map listTripFromWish(@PathVariable("userId") String userId) throws Exception{
		System.out.println(this.getClass()+".json/listTripFromWish()");
		System.out.println(userId);
		Map map = new HashMap();
		Map wishMap = wishService.listWish(userId);
		
		List tempList = (List) wishMap.get("list");
		List list = new ArrayList();
		
		for (int i = 0; i < tempList.size(); i++) {
			//System.out.println(list.get(i));
			Wish wish = (Wish)tempList.get(i);
			
			Trip trip = tripService.getTrip(wish.getTripNo().getPostNo());
			
			wish.setTripNo(trip);
			System.out.println(wish);
			list.add(wish);
		}
		
		
		map.put("list", list);
		
		return map;
	}
	
	@RequestMapping(value="json/listWishByPost/{postNo}")
	public Map listWishByPost(@PathVariable int postNo) throws Exception{
		
		System.out.println("json/listWishByPost");
		
		Wish wish = new Wish();
		wish.setPostNo(postNo);
		
		Map<String , Object> map=wishService.listWishByPost(wish);
		
		return map;
	}

}
