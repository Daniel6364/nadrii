package com.yagn.nadrii.service.ticket.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yagn.nadrii.common.OpenApiSearch;
import com.yagn.nadrii.service.domain.DetailImage;
import com.yagn.nadrii.service.domain.DetailIntro;
import com.yagn.nadrii.service.domain.KakaoImage;
import com.yagn.nadrii.service.domain.NaverImage;
import com.yagn.nadrii.service.domain.SearchFestival;
import com.yagn.nadrii.service.domain.Ticket;
import com.yagn.nadrii.service.ticket.TicketDao;

@Repository("kakaoApiDaoImpl")
public class KakaoApiDaoImpl implements TicketDao {
	
	/// Field
	private KakaoImage kakaoImage;

	@Autowired
	@Value("#{kakaoApiProperties['kakaoKey']}")
	private String kakaoKey;

	@Autowired
	@Value("#{kakaoApiProperties['searchImageURL']}")
	private String searchImageURL;

	/// Constructor
	public KakaoApiDaoImpl() {
		System.out.println(this.getClass());
	}
	
	public static final StringBuilder sendGetKakaoURL(StringBuilder urlBuilder, String kakaoKey) throws Exception {
		
		System.out.println("\n[KakaoApiDaoImpl.java]::sendGetKakaoURL");
		
		URL url = new URL(urlBuilder.toString());
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", kakaoKey);
		
		System.out.println("\nResponse code: " + conn.getResponseCode());
		
		BufferedReader br;
		if (conn.getResponseCode() == 200) {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		} else {
			br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		
		br.close();
		
		conn.disconnect();

		return sb;

	}
	
	public String getKakaoImage(String title) {

		System.out.println("\n[KakaoApiDaoImpl.java]::getKakaoImage");
		System.out.println("[getKakaoImage title check]==>" + title);
		System.out.println("[getKakaoImage title.replaceAll check]==>" + title.replaceAll(" 2018", ""));
		
		int minImage = 200;
		String kakaoReturnImage = "";

		try {
			
			String encodeTitle = URLEncoder.encode(title.replaceAll(" 2018", ""), "UTF-8");
			
			StringBuilder kakaoImageSB = KakaoApiDaoImpl.sendGetKakaoURL
					(new StringBuilder(searchImageURL 
//							+ "&sort=recency"
							+ encodeTitle),	kakaoKey);

			JSONObject kiJsonObj = (JSONObject) JSONValue.parse(kakaoImageSB.toString());
			JSONArray kiItems = (JSONArray) kiJsonObj.get("documents");

//			System.out.println("\n[kiJsonObj] ==>" + kiJsonObj);
//			System.out.println("\n[kiItems] ==>" + kiItems);

			for (int i = 0; i < kiItems.size(); i++) {
				JSONObject value = (JSONObject) kiItems.get(i);

				ObjectMapper objectMapper = new ObjectMapper();
				KakaoImage kakaoImage = new KakaoImage();
				kakaoImage = objectMapper.readValue(value.toString(), KakaoImage.class);
				if (Integer.parseInt(kakaoImage.getHeight()) > minImage) {

					minImage = Integer.parseInt(kakaoImage.getHeight());
					kakaoReturnImage = kakaoImage.getImage_url();
//					System.out.println("\n[kakaoImage]==>" + kakaoImage.getImage_url());
//					System.out.println("\n[kakaoImage height]==>" +kakaoImage.getHeight());
				}		
			}
			System.out.println("\n[getKakaoImage :: selected image url]==>" + kakaoReturnImage);

		} catch (Exception e) {
			System.out.println(e);
		}

		
		return kakaoReturnImage;
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	public Map<String, Object> getTicketList(OpenApiSearch openApiSearch) throws Exception {
		return null;
	}
	public DetailIntro getDetailIntro(int contentId, int contentTypeId) throws Exception {
		return null;
	}
	public DetailImage getDetailImage(int contentId, String title) throws Exception {
		return null;
	}
	public String getNaverImage(String title) throws Exception {
		return null;
	}
	public List<String> getTicketPrice(String priceInfo) throws Exception {
		return null;
	}
	public Map<String, Object> getSearchTicketList(OpenApiSearch openApiSearch) {
		return null;
	}
	public void addTicketLog(Ticket ticket) {
	}
	public Map<String, Object> getSearchTicket(OpenApiSearch openApiSearch) {
		return null;
	}
	public Ticket getTicketFromDB(String contentId) {
		return null;
	}
	
} // end of class
