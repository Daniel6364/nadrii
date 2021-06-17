package com.yagn.nadrii.web.trip;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yagn.nadrii.service.domain.Trip;
import com.yagn.nadrii.service.domain.User;
import com.yagn.nadrii.service.trip.TripService;
import com.yagn.nadrii.service.trip.domain.TourApiDomain;
import com.yagn.nadrii.service.wish.WishService;


@Controller
@RequestMapping("/trip/*")
public class TripController {
	
	@Autowired
	@Qualifier("tripServiceImpl")
	private TripService tripService;
	
	@Autowired
	@Qualifier("wishServiceImpl")
	private WishService wishService;
	
		
	public TripController() {
		System.out.println(this.getClass());
	}


	@RequestMapping(value="listMuseum")	
	public String listMuseum(Map map, @RequestParam("pageNo")int pageNo,
										@RequestParam("area")String area, HttpSession session
										) throws Exception{
				
		System.out.println("/trip/listMuseum");
				
		User user = (User)session.getAttribute("loginUser");
		String areaCode = (String)session.getAttribute("areaCode");
		String localName = (String)session.getAttribute("localName");		
		
		if(areaCode==null ||areaCode=="") {
			areaCode="1";
		}
		
		if(localName ==null || localName=="") {
			localName ="23";
		}
		
		//
		if(area.equals("federal")) {
			localName="";
		}else if(area.equals("national")) {
			areaCode ="";
			localName="";
		}
				
		System.out.println(areaCode +":" +localName);
		
		Map tripMap = tripService.listTrip(pageNo,"14","A02","A0206","A02060100",areaCode,localName); 
		
		List list =(List)tripMap.get("list");
		
		System.out.println("size of list ====>"+list.size());
		
		for (int i = 0; i < list.size(); i++) {
			TourApiDomain tourApiDomain = (TourApiDomain)list.get(i);
			System.out.println(tourApiDomain);
			Trip trip = tripService.getTripFromDB(tourApiDomain.getContentid()+"");
			System.out.println(trip);
			tourApiDomain.setTrip(trip);
									 
		}
				
		map.put("trip", "Museum");
		map.put("list", tripMap.get("list"));
		map.put("pageNo", pageNo);		
		map.put("areaCode", areaCode);
		map.put("localName", localName);
		
		Thread.sleep(1000);
		

		return "forward:/trip/listTrip.jsp";
	}
	
	@RequestMapping(value="getTrip")
	public String getMuseum(Map map, @RequestParam("contentId") String contentId, @RequestParam("contentTypeId") String contentTypeId) throws Exception{
		System.out.println("/trip/getMuseum");
		
		
		TourApiDomain tourApiDomain = tripService.getTrip(contentId,contentTypeId);
		TourApiDomain feeDomain = tripService.getTripDetail(contentId,contentTypeId);
		
		map.put("getTrip", tourApiDomain);
		map.put("getDetail",feeDomain );			
		return "forward:/trip/getTrip.jsp";
	}
	
	
	@RequestMapping(value="/listExhibit")
	public String listExhibit(Map map, @RequestParam("pageNo")int pageNo, @RequestParam("area")String area, HttpSession session) throws Exception{
				
		System.out.println("/trip/listExhibit");
		
		String areaCode = (String)session.getAttribute("areaCode");
		String localName = (String)session.getAttribute("localName");		
		
		if(area.equals("federal")) {
			localName="";
		}else if(area.equals("national")) {
			areaCode ="";
			localName="";
		}
		
		
		System.out.println(areaCode +":" +localName);
		
		Map tripMap = tripService.listTrip(pageNo,"14","A02","A0206","A02060300",areaCode,localName);
		
		
		List list =(List)tripMap.get("list");
		
		System.out.println("size of list ====>"+list.size());
		
		for (int i = 0; i < list.size(); i++) {
			TourApiDomain tourApiDomain = (TourApiDomain)list.get(i);
			System.out.println(tourApiDomain);
			Trip trip = tripService.getTripFromDB(tourApiDomain.getContentid()+"");
			System.out.println(trip);
			tourApiDomain.setTrip(trip);
									 
		}
		
		map.put("trip", "Exhibit");
		map.put("list", tripMap.get("list"));
		map.put("pageNo", pageNo);		
		map.put("areaCode", areaCode);
		map.put("localName", localName);
		
		Thread.sleep(1000);		

		return "forward:/trip/listTrip.jsp";

	}
	
	
	@RequestMapping(value="/listGallery")
	public String listGallery(Map map, @RequestParam("pageNo")int pageNo, @RequestParam("area")String area, HttpSession session) throws Exception{
		
		System.out.println("/trip/listGallery");
		
		String areaCode = (String)session.getAttribute("areaCode");
		String localName = (String)session.getAttribute("localName");		
		
		if(area.equals("federal")) {
			localName="";
		}else if(area.equals("national")) {
			areaCode ="";
			localName="";
		}
		
		
		System.out.println(areaCode +":" +localName);
		
		Map tripMap = tripService.listTrip(pageNo,"14","A02","A0206","A02060500",areaCode,localName);
		
		List list =(List)tripMap.get("list");
		
		System.out.println("size of list ====>"+list.size());
		
		for (int i = 0; i < list.size(); i++) {
			TourApiDomain tourApiDomain = (TourApiDomain)list.get(i);
			System.out.println(tourApiDomain);
			Trip trip = tripService.getTripFromDB(tourApiDomain.getContentid()+"");
			System.out.println(trip);
			tourApiDomain.setTrip(trip);
									 
		}
		
		map.put("trip", "Gallery");
		map.put("list", tripMap.get("list"));
		map.put("pageNo", pageNo);		
		map.put("areaCode", areaCode);
		map.put("localName", localName);
		
		Thread.sleep(1000);		

		return "forward:/trip/listTrip.jsp";
	}
	
	@RequestMapping(value="/listExperience")
	public String listExperience(Map map, @RequestParam("pageNo")int pageNo, @RequestParam("area")String area, HttpSession session) throws Exception{
		
		System.out.println("/trip/listExperience");
		
		String areaCode = (String)session.getAttribute("areaCode");
		String localName = (String)session.getAttribute("localName");		
		
		if(area.equals("federal")) {
			localName="";
		}else if(area.equals("national")) {
			areaCode ="";
			localName="";
		}
		
		System.out.println(areaCode +":" +localName);
		
		Map tripMap = tripService.listTrip(pageNo,"12","A02","A0203","A02030200",areaCode,localName);
		
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
		map.put("list", tripMap.get("list"));
		map.put("pageNo", pageNo);	
		map.put("areaCode", areaCode);
		map.put("localName", localName);
		
		Thread.sleep(1000);

		return "forward:/trip/listTrip.jsp";

	}

	@RequestMapping(value="/listTradition")
	public String listTradition(Map map, @RequestParam("pageNo")int pageNo, @RequestParam("area")String area, HttpSession session) throws Exception{
		
		System.out.println("/trip/listTradition");
		
		String areaCode = (String)session.getAttribute("areaCode");
		String localName = (String)session.getAttribute("localName");		
		
		if(area.equals("federal")) {
			localName="";
		}else if(area.equals("national")) {
			areaCode ="";
			localName="";
		}
				
		System.out.println(areaCode +":" +localName);
		
		Map tripMap = tripService.listTrip(pageNo,"12","A02","A0201","A02010600",areaCode,localName);
		
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
		map.put("list", tripMap.get("list"));
		map.put("pageNo", pageNo);		
		map.put("areaCode", areaCode);
		map.put("localName", localName);
		
		Thread.sleep(1000);

		return "forward:/trip/listTrip.jsp";
	}
	
	@RequestMapping(value="/getTheme")
	public String getTheme(HttpSession session,Map map) throws Exception{
		
		
		Map tripMap = tripService.listTrip(1,"14","A02","A0206","","","");
		
		List list = (List)tripMap.get("list");
		
		//TourApiDomain tourApiDomain = (TourApiDomain)list.get(0);
		
				
		
//		System.out.println(tourApiDomain);
		
				
		map.put("trip", list);
		
		
		Thread.sleep(1000);
		return"forward:/trip/getTheme.jsp";
	}
	
	
	@RequestMapping(value="/listSearch", method=RequestMethod.POST)
	public String listSearch(int pageNo, String keyword, String areaCode, String localName, Map map) throws Exception{
		
		System.out.println("/trip/listSearch");
		System.out.println("전달 메시지 ---->" +keyword);
		
		Map searchMap = tripService.listTourBySearch(pageNo, keyword);
		
		List list =(List)searchMap.get("list");
		
		System.out.println("size of list ====>"+list.size());
		
		for (int i = 0; i < list.size(); i++) {
			TourApiDomain tourApiDomain = (TourApiDomain)list.get(i);
			System.out.println(tourApiDomain);
			Trip trip = tripService.getTripFromDB(tourApiDomain.getContentid()+"");
			System.out.println(trip);
			tourApiDomain.setTrip(trip);
									 
		}
		
		
		map.put("trip", "Search");
		map.put("list", searchMap.get("list"));
		map.put("pageNo", pageNo);
		
		Thread.sleep(1000);

		return "forward:/trip/listTrip.jsp";

	}
	
}
