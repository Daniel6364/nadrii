package com.yagn.nadrii.service.trip.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.NaverImage;
import com.yagn.nadrii.service.domain.Trip;
import com.yagn.nadrii.service.trip.TripDao;
import com.yagn.nadrii.service.trip.domain.TourApiDomain;
import com.yagn.nadrii.service.trip.urlmanage.TourAPIGetDetailUrlManage;
import com.yagn.nadrii.service.trip.urlmanage.TourAPIGetUrlManage;
import com.yagn.nadrii.service.trip.urlmanage.TourAPlListUrlManage;

public class TripDaoImplImageSearch implements TripDao {
	
	

	public TripDaoImplImageSearch() {
	
	}

	
	
	public String naverImageSearch(String target)throws Exception{
		
		
		System.out.println("이미지 검색");

		int display = 10;
		
		
		
		
        String clientId = "YaTN_waxR7h6HvXakGB2";
        String clientSecret = "dHdqCyawjm";

        String text = URLEncoder.encode(target, "UTF-8");
        String apiURL = "https://openapi.naver.com/v1/search/image?query="+ text+"&display="+display
        		+"&filter=large&sort=sim"; // json 
        

        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-Naver-Client-Id", clientId);
        con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
        
        
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
        
        JSONObject jsonobj = (JSONObject) JSONValue.parse(br);
        //System.out.println("JsonObject : "+jsonobj);
        JSONArray items = (JSONArray)jsonobj.get("items");
        //System.out.println("items : "+ items);
        
        List list = new ArrayList();
        for (int i = 0; i < items.size(); i++) {
	        JSONObject tempObj = (JSONObject)items.get(i);
	        //System.out.println(tempObj);
	        ObjectMapper objectMapper = new ObjectMapper();
			NaverImage imageDomain = new NaverImage();
			imageDomain = objectMapper.readValue(tempObj.toString(), NaverImage.class);
			list.add(imageDomain);
			//System.out.println( ( (ImageDomain)list.get(i) ).getLink());
			
		}
                   
        if(list.size()!=0) {
        	return ( (NaverImage)list.get(0) ).getLink();
        }else {

        	return "이미지 없음";

        }
	}

	@Override
	public void addTrip(Trip trip) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Trip getTrip(int postNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trip> listTrip(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateViewCount(String contentId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Trip getTripFromDB(String contentid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List listTrip(int pageNo,String contentTypeId, String cat1, String cat2, String cat3,String areaCode, String localName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public TourApiDomain getTrip(String contentid, String contentTypeid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public TourApiDomain getTripDetail(String contentid, String contentTypeid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List getClientAddress(String lat, String lng) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String getAreaCode(String placeName, String areaCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List listTourBySearch(int pageNo,String keyword) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List listTripinDB() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}






}
