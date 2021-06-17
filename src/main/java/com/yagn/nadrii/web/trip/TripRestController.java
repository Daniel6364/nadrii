package com.yagn.nadrii.web.trip;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yagn.nadrii.service.domain.Trip;
import com.yagn.nadrii.service.domain.User;
import com.yagn.nadrii.service.trip.TripService;
import com.yagn.nadrii.service.trip.domain.TourApiDomain;
import com.yagn.nadrii.service.wish.WishService;


@RestController
@RequestMapping("/trip/*")
public class TripRestController {
	
	@Autowired
	@Qualifier("wishServiceImpl")
	private WishService wishService;

	
	@Autowired
	@Qualifier("tripServiceImpl")
	private TripService tripService;
	
		
	public TripRestController() {
		System.out.println(this.getClass());
	}
	

	///*

	@RequestMapping(value="json/listMuseum/{pageNo}/{areaCode}/{localName}")
	public Map listMuseum(@PathVariable("pageNo")int pageNo,@PathVariable("areaCode")String areaCode, @PathVariable("localName")String localName,HttpServletRequest request,HttpSession session) throws Exception{
		
		System.out.println("/trip/json/listMuseum");
		
		if(areaCode.equals("0")) {
			areaCode="";
		}
				
		if(localName.equals("0")) {
			localName ="";
			
		}
		
		System.out.println(pageNo+"  RestController");
		System.out.println(areaCode +" : "+localName);
		System.out.println("RestController listMuseum");
		
		Map map = new HashMap();
				
		
		User user = (User)session.getAttribute("loginUser");
		
		Map tripMap = tripService.listTrip(pageNo,"14","A02","A0206","A02060100",areaCode, localName);
		List list =(List)tripMap.get("list");
		
		for (int i = 0; i < list.size(); i++) {
			TourApiDomain tourApiDomain = (TourApiDomain)list.get(i);
			System.out.println(tourApiDomain);
			Trip trip = tripService.getTripFromDB(tourApiDomain.getContentid()+"");
			System.out.println(trip);
			tourApiDomain.setTrip(trip);
									 
		}
				
		map.put("trip", "Museum");		
		map.put("areaCode", areaCode);
		map.put("localName", localName);
		map.put("list", tripMap.get("list"));
		
		
		
		return map;
	}
	
	///*

	@RequestMapping(value="json/listExhibit/{pageNo}/{areaCode}/{localName}")
	public Map listExhibit(@PathVariable("pageNo")int pageNo,@PathVariable("areaCode")String areaCode, @PathVariable("localName")String localName) throws Exception{
		
		System.out.println("RestController listExhibit");
		
		if(areaCode.equals("0")) {
			areaCode="";
		}
		
		if(localName.equals("0")) {
			localName ="";
			
		}
		System.out.println(areaCode +" : "+localName);
		
		Map map = new HashMap();
				
		System.out.println("/trip/json/listExhibit");
		
		Map tripMap = tripService.listTrip(pageNo,"14","A02","A0206","A02060300",areaCode, localName);
		List list =(List)tripMap.get("list");
		
		for (int i = 0; i < list.size(); i++) {
			TourApiDomain tourApiDomain = (TourApiDomain)list.get(i);
			System.out.println(tourApiDomain);
			Trip trip = tripService.getTripFromDB(tourApiDomain.getContentid()+"");
			System.out.println(trip);
			tourApiDomain.setTrip(trip);
									 
		}
		
		map.put("trip", "Exhibit");
		map.put("areaCode", areaCode);
		map.put("localName", localName);
		map.put("list", tripMap.get("list"));
		
		
		
		return map;
	}
	
	

	@RequestMapping(value="json/listExperience/{pageNo}/{areaCode}/{localName}")
	public Map listExperience(@PathVariable("pageNo")int pageNo,@PathVariable("areaCode")String areaCode, @PathVariable("localName")String localName) throws Exception{
		
		System.out.println("RestController listExperience");
		if(areaCode.equals("0")) {
			areaCode="";
		}
		
		if(localName.equals("0")) {
			localName ="";
			
		}
		System.out.println(areaCode +" : "+localName);
		
		Map map = new HashMap();
		
		System.out.println("/trip/json/listExperience");
		
		Map tripMap = tripService.listTrip(pageNo,"12","A02","A0203","A02030200",areaCode, localName);
		List list =(List)tripMap.get("list");
		
		System.out.println("size of list ====>"+list.size());
		
		for (int i = 0; i < list.size(); i++) {
			TourApiDomain tourApiDomain = (TourApiDomain)list.get(i);
			System.out.println(tourApiDomain);
			Trip trip = tripService.getTripFromDB(tourApiDomain.getContentid()+"");
			System.out.println(trip);
			tourApiDomain.setTrip(trip);
									 
		}
		
		map.put("trip", "Experience");
		map.put("areaCode", areaCode);
		map.put("localName", localName);
		map.put("list", tripMap.get("list"));
		
		
		
		return map;
	}
	
	

	@RequestMapping(value="json/listTradition/{pageNo}/{areaCode}/{localName}")
	public Map listTradition(@PathVariable("pageNo")int pageNo,@PathVariable("areaCode")String areaCode, @PathVariable("localName")String localName) throws Exception{
		
		System.out.println("RestController listTradition");
		if(areaCode.equals("0")) {
			areaCode="";
		}
		
		if(localName.equals("0")) {
			localName ="";
			
			
		}
		System.out.println(areaCode +" : "+localName);
		Map map = new HashMap();
		
		System.out.println("/trip/json/listTradition");
		
		Map tripMap = tripService.listTrip(pageNo,"12","A02","A0201","A02010600",areaCode, localName);
		List list =(List)tripMap.get("list");
		
		System.out.println("size of list ====>"+list.size());
		
		for (int i = 0; i < list.size(); i++) {
			TourApiDomain tourApiDomain = (TourApiDomain)list.get(i);
			System.out.println(tourApiDomain);
			Trip trip = tripService.getTripFromDB(tourApiDomain.getContentid()+"");
			System.out.println(trip);
			tourApiDomain.setTrip(trip);
									 
		}
		
		
		map.put("trip", "Tradition");
		map.put("areaCode", areaCode);
		map.put("localName", localName);
		map.put("list", tripMap.get("list"));
		
		
		return map;
	}
	
	
	
	@RequestMapping(value="json/listGallery/{pageNo}/{areaCode}/{localName}")
	public Map listGallery(@PathVariable("pageNo")int pageNo,@PathVariable("areaCode")String areaCode, @PathVariable("localName")String localName) throws Exception{
		
		System.out.println("RestController listGallery");
		if(areaCode.equals("0")) {
			areaCode="";
		}
		
		if(localName.equals("0")) {
			localName ="";
		}
		System.out.println(areaCode +" : "+localName);
		Map map = new HashMap();
		
		System.out.println("/trip/json/listGallery");
		
		Map tripMap = tripService.listTrip(pageNo,"14","A02","A0206","A02060500",areaCode, localName);
		List list =(List)tripMap.get("list");
		for (int i = 0; i < list.size(); i++) {
			TourApiDomain tourApiDomain = (TourApiDomain)list.get(i);
			System.out.println(tourApiDomain);
			Trip trip = tripService.getTripFromDB(tourApiDomain.getContentid()+"");
			System.out.println(trip);
			tourApiDomain.setTrip(trip);
									 
		}
		
		map.put("trip", "Gallery");
		map.put("areaCode", areaCode);
		map.put("localName", localName);
		map.put("list", tripMap.get("list"));
		
		
		return map;
	}
	//*/
	
	//
	@RequestMapping(value="json/getTrip/{contentId}/{contentTypeId}")
	public Map getTrip(@PathVariable("contentId") String contentId, @PathVariable("contentTypeId") String contentTypeId) throws Exception{
		
		System.out.println("RestController/trip/getTrip");
		
		System.out.println(contentId+", "+ contentTypeId);
			
		TourApiDomain tourApiDomain = tripService.getTrip(contentId,contentTypeId);
		TourApiDomain feeDomain = tripService.getTripDetail(contentId,contentTypeId);
		System.out.println(tourApiDomain);
		Map map = new HashMap();
		
		map.put("getTrip", tourApiDomain);
		map.put("getDetail",feeDomain );			
		
		
		

		Trip trip = tripService.getTripFromDB(contentId);
		
		
		if(trip==null) {
			trip = new Trip();
			trip.setAddress(tourApiDomain.getAddr1());
			trip.setLat(tourApiDomain.getMapy());
			trip.setLng(tourApiDomain.getMapx());
			trip.setThumbnailImageFile(tourApiDomain.getFirstimage2());
			trip.setDescription(tourApiDomain.getOverview());
			trip.setTripName(tourApiDomain.getTitle());
			trip.setCreatedTime((tourApiDomain.getCreatedtime()).substring(0, 8));
			System.out.println(trip.getCreatedTime());
			trip.setContentId(tourApiDomain.getContentid()+"");
			trip.setContentTypeId(tourApiDomain.getContenttypeid()+"");
			tripService.addTriptoDB(trip);
			
			
		}else {
			tripService.updateViewCount(contentId);
			
		}	
		
		Thread.sleep(1000);
		
		map.put("result", tripService.getTripFromDB(contentId)	);
		
		return map;
	}
	

	@RequestMapping(value="json/addTripToDB/{contentId}/{contentTypeId}")
	public Map addTripToDB(@PathVariable("contentId")String contentId, @PathVariable("contentTypeId")String contentTypeId)throws Exception {
		Map map = new HashMap();
		
		Trip trip = tripService.getTripFromDB(contentId);
		System.out.println(trip);
		if(trip ==null) {
		
			TourApiDomain tourApiDomain = tripService.getTrip(contentId, contentTypeId);
			trip = new Trip();
			trip.setAddress(tourApiDomain.getAddr1());
			trip.setLat(tourApiDomain.getMapy());
			trip.setLng(tourApiDomain.getMapx());
			trip.setThumbnailImageFile(tourApiDomain.getFirstimage2());
			trip.setDescription(tourApiDomain.getOverview());
			trip.setTripName(tourApiDomain.getTitle());
			trip.setCreatedTime((tourApiDomain.getCreatedtime()).substring(0, 8));
			System.out.println(trip.getCreatedTime());
			trip.setContentId(tourApiDomain.getContentid()+"");
			trip.setContentTypeId(tourApiDomain.getContenttypeid()+"");
			tripService.addTriptoDB(trip);
		}
		
		System.out.println(tripService.getTripFromDB(contentId));
		map.put("result", tripService.getTripFromDB(contentId));
		return map;
	}
	
	@RequestMapping(value="json/getClientAddress")
	public void getClientAddress(@RequestBody JSONObject location, HttpServletRequest request)throws Exception {
		String lat = (Double)location.get("lat")+"";
		String lng = (Double)location.get("lng")+"";
		System.out.println("Location :   " +location);
		System.out.println("Latitude : " + lat +" , Longitude : "+ lng);
		
		List list = tripService.getClientAddress(lat, lng);
		System.out.println(list);
		String placeName =((String)list.get(1)).trim();
		System.out.println("Location::"+placeName);
		if(placeName.equals("서울특별시")) {
			placeName = "서울";
		}
		
		String areaCode = tripService.getAreaCode(placeName, "");

		placeName = ((String)list.get(2)).trim();
		String localName = tripService.getAreaCode(placeName, areaCode);

		
		HttpSession session = request.getSession(true);
		session.setAttribute("areaCode", areaCode);
		session.setAttribute("localName", localName);
		
	
		
	}
	@RequestMapping(value="json/listSearch/")
	public Map listSearch(@RequestBody JSONObject parameter)throws Exception{
		System.out.println(parameter);
		
		Map map = new HashMap();
		
		return map;
	}
	
}