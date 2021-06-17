package com.yagn.nadrii.service.wish.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yagn.nadrii.service.domain.Trip;
import com.yagn.nadrii.service.domain.Wish;
import com.yagn.nadrii.service.trip.TripDao;
import com.yagn.nadrii.service.wish.WishDao;
import com.yagn.nadrii.service.wish.WishService;


@Service("wishServiceImpl")
public class WishServiceImpl implements WishService{

	
	@Autowired
	@Qualifier("wishDaoImpl")
	private WishDao wishDao;
	
	@Autowired
	@Qualifier("tripDaoImpla")
	private TripDao tripDao;
	
	
	public WishServiceImpl() {
		System.out.println(this.getClass());
	}
	
	@Override
	public void addWishListFromTrip(Wish wish) throws Exception {
		wishDao.addWishListFromTrip(wish);
	}
	
	@Override
	public void addWishListFromTicket(Wish wish) throws Exception {
		wishDao.addWishListFromTicket(wish);
	}
	
	@Override
	public void deleteWish(Wish wish) throws Exception {
		wishDao.deleteWish(wish);		
	}
	
	@Override
	public void addWishListFromPost(Wish wish) throws Exception {
		wishDao.addWishListFromPost(wish);
	}

	@Override
	public Wish getWish(int wishNo) throws Exception {
		return wishDao.getWish(wishNo)	;
	}
	
	@Override
	public Map listWish(String userId) throws Exception {
		Map map = new HashMap();
		List list = wishDao.listWish(userId);
		map.put("list", list);
		
		return map;
	}

	@Override
	public Wish getWishByTripNo(String userId,int tripNo) throws Exception {
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("tripNo", tripNo);
		System.out.println("Confirm :  " + wishDao.getWishByTripNo(map));
		return wishDao.getWishByTripNo(map);
		
	}

	//플래너에서 사용할 위시리스트 내용 
	@Override
	public List listTripFromWish(String userId) throws Exception {
		
		List wishList = new ArrayList();
		
		List list = wishDao.listWish(userId);
		
		for (int i = 0; i < list.size(); i++) {
			Wish wish = (Wish)list.get(i);
			Trip trip = tripDao.getTrip(wish.getTripNo().getPostNo());
			
			wish.setTripNo(trip);
			wishList.add(wish);
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(wishList.get(i));
		}
		return wishList;
	}

	@Override
	public Wish checkDuplication(int postNo) throws Exception {
		return wishDao.checkDuplication(postNo);
	}

	public Map<String , Object > listWishByPost(Wish wish) throws Exception {

		List<Wish> list= wishDao.listWishByPost(wish);
		int totalCount = wishDao.getTotalWishByPost(wish);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
	
		return map;
	}
	
}
