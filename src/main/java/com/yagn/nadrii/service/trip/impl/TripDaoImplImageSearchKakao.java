package com.yagn.nadrii.service.trip.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.KakaoImage;
import com.yagn.nadrii.service.domain.Trip;
import com.yagn.nadrii.service.trip.TripDao;
import com.yagn.nadrii.service.trip.domain.TourApiDomain;
import com.yagn.nadrii.service.trip.urlmanage.TourAPIGetDetailUrlManage;
import com.yagn.nadrii.service.trip.urlmanage.TourAPIGetUrlManage;
import com.yagn.nadrii.service.trip.urlmanage.TourAPlListUrlManage;

public class TripDaoImplImageSearchKakao implements TripDao{


	@Override
	public String naverImageSearch(String target) throws Exception {
		System.out.println("카카오 이미지 서치");
		List list = new ArrayList();
		int display = 100;
        String kakaoRestKey = "KakaoAK 61ac37b7b651475f235157189e22f998";
        
        try {
            String text = URLEncoder.encode(target, "UTF-8");
            String apiURL = "https://dapi.kakao.com/v2/search/image?query="+ text+"&page=1&size=10";  

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", kakaoRestKey);
            
            
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
                                    
            JSONObject jsonobj = (JSONObject) JSONValue.parse(br);
            System.out.println("JsonObject : "+jsonobj);
            JSONArray items = (JSONArray)jsonobj.get("documents");
            System.out.println("documents : "+ items);
            
            
            for (int i = 0; i < items.size(); i++) {
            	JSONObject tempObj = (JSONObject)items.get(i);
            	//System.out.println(tempObj);
            	ObjectMapper objectMapper = new ObjectMapper();
				KakaoImage kakaoImage = new KakaoImage();
				kakaoImage = objectMapper.readValue(tempObj.toString(), KakaoImage.class);
				list.add(kakaoImage);
				System.out.println(kakaoImage.getImage_url());
			}
            	
            
        } catch (Exception e) {
            System.out.println(e);
        }
        if(list.size()!=0) {
        	return ( (KakaoImage)list.get(0) ).getImage_url();
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
