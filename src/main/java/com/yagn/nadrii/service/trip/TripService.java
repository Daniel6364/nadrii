package com.yagn.nadrii.service.trip;

import java.util.List;
import java.util.Map;

import com.yagn.nadrii.service.domain.Trip;
import com.yagn.nadrii.service.trip.domain.TourApiDomain;

public interface TripService {
	
	public Map listTrip(int pageNo, String contentTypeId, String cat1, String cat2, String cat3,String areaCode, String localName) throws Exception;
	
	public TourApiDomain getTrip(String contentId, String contentTypeid) throws Exception;
	
	public TourApiDomain getTripDetail(String contentId, String contentTypeid)throws Exception;

	public Trip tripCheckDuplication(String contentid) throws Exception;
	
	public void addTriptoDB(Trip trip) throws Exception;
	
	public Trip getTrip(int postNo) throws Exception;
	
	public Trip getTripFromDB(String contentId)throws Exception;
	
	public void updateViewCount(String contentId) throws Exception;
	
	public List getClientAddress(String lat, String lng)throws Exception;
	
	public String getAreaCode(String placeName, String areaCode) throws Exception;
	
	public Map listTourBySearch(int pageNo, String keyword) throws Exception;
	
	public List lsitTripinDB() throws Exception;
}
