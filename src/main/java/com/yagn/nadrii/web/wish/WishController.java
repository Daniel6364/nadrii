package com.yagn.nadrii.web.wish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yagn.nadrii.service.comm.CommService;
import com.yagn.nadrii.service.domain.Community;
import com.yagn.nadrii.service.domain.Trip;
import com.yagn.nadrii.service.domain.User;
import com.yagn.nadrii.service.domain.Wish;
import com.yagn.nadrii.service.trip.TripService;
import com.yagn.nadrii.service.wish.WishService;

@Controller
@RequestMapping("/wish/*")
public class WishController {
	
	@Autowired
	@Qualifier("wishServiceImpl")
	private WishService wishService;
	
	@Autowired
	@Qualifier("commServiceImpl")
	private CommService CommService;
	
	@Autowired
	@Qualifier("tripServiceImpl")
	private TripService tripService;
	
		
	public WishController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping("listTripFromWish")
	public String listTripFromWish(@RequestParam("userId")String userId,Map map) throws Exception{
		System.out.println(this.getClass()+".listTripFromWish()");
		System.out.println(userId);
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
		
		return "forward:/wishList/listWishList.jsp";
	}
	

}
