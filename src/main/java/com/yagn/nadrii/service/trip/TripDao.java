package com.yagn.nadrii.service.trip;

import java.util.List;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Trip;
import com.yagn.nadrii.service.trip.domain.TourApiDomain;
import com.yagn.nadrii.service.trip.urlmanage.TourAPIGetDetailUrlManage;
import com.yagn.nadrii.service.trip.urlmanage.TourAPIGetUrlManage;
import com.yagn.nadrii.service.trip.urlmanage.TourAPlListUrlManage;

public interface TripDao {
	
	public List listTrip(int pageNo,String contentTypeId, String cat1, String cat2, String cat3, String areaCode, String localName) throws Exception;
	
	public TourApiDomain getTrip(String contentid, String contentTypeid)throws Exception;
	
	public TourApiDomain getTripDetail(String contentid, String contentTypeid)throws Exception;
	
	public String naverImageSearch(String target) throws Exception;
	
	public void addTrip(Trip trip)throws Exception;
	
	public Trip getTrip(int postNo) throws Exception;
	
	public List<Trip> listTrip(Search search) throws Exception;
	
	public void updateViewCount(String contentId) throws Exception;
	
	public Trip getTripFromDB(String contentId) throws Exception;
	
	public List getClientAddress(String lat, String lng) throws Exception;
	
	public String getAreaCode(String placeName, String areaCode) throws Exception;
	
	public List listTourBySearch(int pageNo,String keyword) throws Exception ;
	
	public List listTripinDB() throws Exception;
	
}
