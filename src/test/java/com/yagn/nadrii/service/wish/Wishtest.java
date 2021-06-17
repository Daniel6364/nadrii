package com.yagn.nadrii.service.wish;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Around;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.java.swing.plaf.windows.resources.windows_ja;
import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.comm.CommDao;
import com.yagn.nadrii.service.domain.Community;
import com.yagn.nadrii.service.domain.Trip;
import com.yagn.nadrii.service.domain.User;
import com.yagn.nadrii.service.domain.Wish;
import com.yagn.nadrii.service.trip.TripDao;
import com.yagn.nadrii.service.trip.TripService;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })


public class Wishtest {
	
	private Wish wish;
	
	@Autowired
	@Qualifier("wishDaoImpl")
	private WishDao wishDao;
	
	@Autowired
	@Qualifier("tripDaoImpla")
	private TripDao tripDao;
	
	@Autowired
	@Qualifier("commDaoImpl")
	private CommDao commDao;
	
	
	@Autowired
	@Qualifier("wishServiceImpl")
	private WishService wishService;
	
	String userId = "test01";
		
	//@Test
	public void addWishListFromTripTest() throws Exception {
				
		Trip trip = tripDao.getTrip(20000);
		System.out.println(trip);
		wish = new Wish();
		
		wish.setUserId(userId);
				
		//wish.setTripNo(trip.getPostNo());
		wish.setImageFile("testImage");
		//wish.setPostNo(0);
		System.out.println(wish);
		wishDao.addWishListFromTrip(wish);
			
		
	}
	
	//@Test
	public void addWishListFromPostTest() throws Exception{
		String userId= "test01";
		Community community = new Community();
		community = commDao.getComm(30003);
		System.out.println(community);
		
		wish = new Wish();
		wish.setUserId(userId);
		
		wish.setImageFile("testImage");
		wish.setPostNo(community.getPostNo());
				
		wishDao.addWishListFromPost(wish);
		
		System.out.println(wish);
		
	}
	
	//@Test
	public void getWish() throws Exception{
		wish = wishDao.getWish(10001);
		System.out.println(wish);
	}
	
	@Test
	public void listWish()throws Exception{
		List list = wishDao.listWish("questsolve");
		for (int i = 0; i < list.size(); i++) {
			//System.out.println(list.get(i));
			Wish wish = (Wish)list.get(i);
			
			Trip trip = tripDao.getTrip(wish.getTripNo().getPostNo());
			
			wish.setTripNo(trip);
			System.out.println(wish);	
		}
		
	}
	
	
	//@Test
	public void listWishPlusTrip() throws Exception{
		List list = wishService.listTripFromWish("test01");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			
		}
	}
	
		
}
