package com.yagn.nadrii.service.trip.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yagn.nadrii.service.domain.Trip;
import com.yagn.nadrii.service.trip.TripDao;
import com.yagn.nadrii.service.trip.TripService;
import com.yagn.nadrii.service.trip.domain.TourApiDomain;


@Service("tripServiceImpl")
public class TripServiceImpl implements TripService {
	
	@Autowired
	@Qualifier("tripDaoImpl")
	private TripDao tripDaoTour;
	
	@Autowired
	@Qualifier("tripDaoImpla")
	private TripDao tripDaoDB;
	
	@Autowired
	@Qualifier("tripDaoImplAddress")
	private TripDao tripDaoAddress;
	
	public TripServiceImpl() {
		System.out.println(this.getClass());
		
	}



	@Override
	public Map listTrip(int pageNo, String contentTypeId, String cat1, String cat2, String cat3, String areaCode, String localName) throws Exception{
		
		System.out.println("listTrip SerivceImpl");
		Map map = new HashMap();
		
		List list = tripDaoTour.listTrip(pageNo, contentTypeId,cat1,cat2,cat3,areaCode,localName);
		
		
		/*
		if(list.size()<12) {
			list.addAll(tripDaoTour.listTrip(pageNo, contentTypeId, cat1, cat2, cat3, areaCode, ""));
		}
		//*/
		
		
		map.put("list", list);
		return map;
	}



	@Override
	public TourApiDomain getTrip(String contentId, String contentTypeid) throws Exception {
		
		System.out.println("getTrip SerivceImpl");
		
		TourApiDomain tourApiDomain = tripDaoTour.getTrip(contentId,contentTypeid);
		System.out.println(tourApiDomain.getTitle());
		
		return tourApiDomain;
	}



	@Override
	public TourApiDomain getTripDetail(String contentId, String contentTypeid) throws Exception {
		System.out.println("12111:getTripDetail SerivceImpl");
		System.out.println(contentId);
		System.out.println(contentTypeid);
		
		
		TourApiDomain tourApiDomain = tripDaoTour.getTripDetail(contentId, contentTypeid);
		System.out.println(tourApiDomain.getUsefee());
		return tourApiDomain;
	}



	@Override
	public Trip tripCheckDuplication(String contentid) throws Exception {
		System.out.println("tripCheckDuplication ServiceImpl");
		
		Trip trip = tripDaoDB.getTripFromDB(contentid);
		System.out.println(trip);
		
		return trip;
	}



	@Override
	public void addTriptoDB(Trip trip) throws Exception {
		tripDaoDB.addTrip(trip);		
	}
	
	public Trip getTripFromDB(String contentId)throws Exception{
		System.out.println("TripServiceImpl getTripFromDB");
		return tripDaoDB.getTripFromDB(contentId);
	}
	
	public void updateViewCount(String contentId) throws Exception{
		System.out.println("TripServiceImpl updateViewCount");
		tripDaoDB.updateViewCount(contentId);
	}



	@Override
	public List getClientAddress(String lat, String lng) throws Exception {
		System.out.println("주소 검색시작");
		return tripDaoAddress.getClientAddress(lat, lng);
		
	}



	@Override
	public String getAreaCode(String placeName, String areaCode) throws Exception {
		return tripDaoTour.getAreaCode(placeName, areaCode);
	
	}



	@Override
	public Map listTourBySearch(int pageNo, String keyword) throws Exception {
		System.out.println("listTourBySearch tripServiceIpl");
		Map map = new HashMap();
		List list = tripDaoTour.listTourBySearch(pageNo,keyword);
		map.put("list", list);
		return map;
	}



	@Override
	public Trip getTrip(int postNo) throws Exception {
		System.out.println("getTrip tripServiceImpl");
		Trip trip = tripDaoDB.getTrip(postNo);
		return trip;
	}



	@Override
	public List lsitTripinDB() throws Exception {
		System.out.println("listTrip in the DB");
		return tripDaoDB.listTripinDB();
	}
	
	
	
	
}
