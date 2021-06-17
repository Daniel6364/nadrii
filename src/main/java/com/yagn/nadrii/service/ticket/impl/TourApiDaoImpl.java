package com.yagn.nadrii.service.ticket.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yagn.nadrii.common.OpenApiPage;
import com.yagn.nadrii.common.OpenApiSearch;
import com.yagn.nadrii.service.domain.DetailImage;
import com.yagn.nadrii.service.domain.DetailIntro;
import com.yagn.nadrii.service.domain.SearchFestival;
import com.yagn.nadrii.service.domain.Ticket;
import com.yagn.nadrii.service.domain.TourTicket;
import com.yagn.nadrii.service.ticket.TicketDao;
import com.yagn.nadrii.service.ticket.TicketService;

@Repository("tourApiDaoImpl")
public class TourApiDaoImpl implements TicketDao {
	
	/// Field
	@Autowired
	@Qualifier("ticketServiceImpl")
	private TicketService ticketService;
	
	private SearchFestival searchFestival;
	private DetailIntro	 detailIntro;
	private DetailImage detailImgae;
	private TourTicket tourTicket;
	private Ticket ticket;
	
	/// TourAPI properties
	@Value("#{tourApiProperties['searchFestivalURL']}")
	private String searchFestivalURL;
	@Value("#{tourApiProperties['essentialURL']}")
	private String essentialURL;
	@Value("#{tourApiProperties['detailIntroURL']}")
	private String detailIntroURL;
	@Value("#{tourApiProperties['detailImageURL']}")
	private String detailImageURL;
	@Value("#{tourApiProperties['keywordSearchURL']}")
	private String keywordSearchURL;
	
	/// Constructor
	public TourApiDaoImpl() {
		System.out.println(this.getClass());
	}
	
	public static final StringBuilder sendGetTourURL(StringBuilder urlBuilder) throws Exception {
		
		System.out.println("\n[TourApiDaoImpl.java]::sendGetTourURL");

		URL url = new URL(urlBuilder.toString());
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");

		System.out.println("Response code: " + conn.getResponseCode());
		
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
	
	public Map<String, Object> getSearchTicket(OpenApiSearch openApiSearch) {
		
		System.out.println("\n[tourApiDaoImpl.java]::getSearchTicket");

		Map<String, Object> map = new HashMap<String, Object>();
		
		tourTicket = new TourTicket();
		
		OpenApiPage openApiPage = new OpenApiPage();
		
		try {
			
			String encodeKeyword = URLEncoder.encode(openApiSearch.getSearchKeyword(), "UTF-8");
			
			StringBuilder keywordSearchSB = TourApiDaoImpl.sendGetTourURL
					(new StringBuilder(keywordSearchURL + essentialURL 
							+ "&listYN=Y" 
//							+ "&pageNo=" + openApiSearch.getPageNo() 
//							+ "&contentTypeId=15" 
							+ "&keyword=" + encodeKeyword ));
			
			System.out.println("\n[keywordSearchSB]==>" + keywordSearchSB);

			JSONObject ksJsonObj = (JSONObject) JSONValue.parse(keywordSearchSB.toString());
			System.out.println("\n[ksJsonObj Check]==>" + ksJsonObj.toString());
			
			
			JSONObject ksResponse = (JSONObject) ksJsonObj.get("response");
			System.out.println("\n[ksResponse Check]==>" + ksResponse.toString());
			
			JSONObject ksHeader = (JSONObject) ksResponse.get("header");
			System.out.println("\n[ksHeader Check]==>" + ksHeader.toString());
			
			JSONObject ksBody = (JSONObject) ksResponse.get("body");
			System.out.println("\n[ksBody Check]==>" + ksBody.toString());
			
			JSONObject ksItems = (JSONObject) ksBody.get("items");
			System.out.println("\n[ksItems]==> " + ksItems.get("item").toString());

			if (ksItems.get("item") instanceof JSONObject) {

				System.out.println("\n [JSONObject]");

				JSONObject ksItem = (JSONObject) ksItems.get("item");
				System.out.println("\n[ksItem Check]==>" + ksItem.toString());

				List<Ticket> searchTicket = new ArrayList<Ticket>();

				ObjectMapper objectMapper = new ObjectMapper();
				ticket = new Ticket();
				ticket = objectMapper.readValue(ksItem.toJSONString(), Ticket.class);

				ticket.setLat(ticket.getMapy());
				ticket.setLng(ticket.getMapx());
				ticket.setTicketTitle(ticket.getTitle().replaceAll(" 2018", ""));
				ticket.setTicketImage(ticket.getFirstimage());

				System.out.println("\n[ticket damain check]==>" + ticket.toString());

				searchTicket.add(ticket);

				map.put("TicketDomain", ticket);
				map.put("searchTicket", searchTicket);
				
			} else if (ksItems.get("item") instanceof JSONArray){

				System.out.println("\n [JSONArray]");

//				JSONArray ksItems = (JSONArray) ksBody.get("items");
//				System.out.println("\n[ksItems]==> " + ksItems.toString());

				JSONArray ksItem = (JSONArray) ksItems.get("item");
				System.out.println("\n[ksItem Check]==>" + ksItem.toString());

				List<Ticket> searchTicket = new ArrayList<Ticket>();

				for (int i = 0; i < ksItem.size(); i++) {

					JSONObject itemValue = (JSONObject) ksItem.get(i);

					ObjectMapper objectMapper = new ObjectMapper();
					ticket = new Ticket();
					ticket = objectMapper.readValue(itemValue.toJSONString(), Ticket.class);

					ticket.setLat(ticket.getMapy());
					ticket.setLng(ticket.getMapx());
					ticket.setTicketTitle(ticket.getTitle().replaceAll(" 2018", ""));
					ticket.setTicketImage(ticket.getFirstimage());

					System.out.println("\n[ticket damain check]==>" + ticket.toString());

					searchTicket.add(ticket);

					map.put("TicketDomain", ticket);
					map.put("searchTicket", searchTicket);
				}
				
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		map.put("totalCount", openApiPage.getTotalCount());
		
		return map;
	}
	
	
	
	public Map<String, Object> getSearchTicketList(OpenApiSearch openApiSearch) {
		
		System.out.println("\n[tourApiDaoImpl.java]::getSearchTicketList");

		Map<String, Object> map = new HashMap<String, Object>();
		
		tourTicket = new TourTicket();
		
		OpenApiPage openApiPage = new OpenApiPage();
		
		try {
			
			// Date currentDate = new Date();
	         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
	         // System.out.println("current date check :: " + simpleDateFormat.format(new Date()));
	         
	         String encodeKeyword = URLEncoder.encode(openApiSearch.getSearchKeyword(), "UTF-8");
	         
	         StringBuilder keywordSearchSB = TourApiDaoImpl.sendGetTourURL
	               (new StringBuilder(keywordSearchURL + essentialURL 
	                     + "&listYN=Y" 
//	                     + "&pageNo=" + openApiSearch.getPageNo() 
	                     + "&eventStartDate=" + simpleDateFormat.format(new Date())
	                     + "&contentTypeId=15" 
	                     + "&keyword=" + encodeKeyword ));

			JSONObject ksJsonObj = (JSONObject) JSONValue.parse(keywordSearchSB.toString());
//			JSONArray ksJsonObj = (JSONArray) JSONValue.parse(keywordSearchSB.toString());
			System.out.println("\n[ksJsonObj Check]==>" + ksJsonObj.toString());
			
			JSONObject ksResponse = (JSONObject) ksJsonObj.get("response");
			System.out.println("\n[ksResponse Check]==>" + ksResponse.toString());
			
			JSONObject ksHeader = (JSONObject) ksResponse.get("header");
			System.out.println("\n[ksHeader Check]==>" + ksHeader.toString());
			
			JSONObject ksBody = (JSONObject) ksResponse.get("body");
			ObjectMapper ksBodyMapper = new ObjectMapper();
			openApiPage = ksBodyMapper.readValue(ksBody.toJSONString(), OpenApiPage.class);
			System.out.println("\n[ksBody Check]==>" + ksBody.toString());
			
			JSONObject ksItems = (JSONObject) ksBody.get("items");
			System.out.println("\n[ksItems]==> " + ksItems.get("item").toString());
			
			JSONArray ksItem = (JSONArray) ksItems.get("item");
			
			System.out.println("\n[ksItem Check]==>" + ksItem.toString());

			List<TourTicket> tourTicketList = new ArrayList<TourTicket>();

			for (int i = 0; i < ksItem.size(); i++) {

				JSONObject itemValue = (JSONObject) ksItem.get(i);

				ObjectMapper objectMapper = new ObjectMapper();
				searchFestival = new SearchFestival();
				searchFestival = objectMapper.readValue(itemValue.toJSONString(), SearchFestival.class);

				detailIntro = new DetailIntro();
				detailIntro = this.getDetailIntro(searchFestival.getContentid(), searchFestival.getContenttypeid());

				tourTicket = new TourTicket();

				tourTicket.setTitle(searchFestival.getTitle().replaceAll(" 2018", ""));
				tourTicket.setContentid(searchFestival.getContentid());
				tourTicket.setContenttypeid(searchFestival.getContenttypeid());
				
				System.out.println("\n[1. Title Check] ==> " + searchFestival.getTitle());
				System.out.println("\n[2. searchFestival.getFirstimage() Check] ==> " + searchFestival.getFirstimage());
				if (searchFestival.getFirstimage() == null || searchFestival.getFirstimage() == "") {
					String image = ticketService.getNaverImage(searchFestival.getTitle() + " 2018");
					tourTicket.setFirstimage(image);
				} else {
					tourTicket.setFirstimage(searchFestival.getFirstimage());
				}
				
				tourTicket.setEventstartdate(detailIntro.getEventstartdate());
				tourTicket.setEventenddate(detailIntro.getEventenddate());
				tourTicket.setReadcount(searchFestival.getReadcount());
				tourTicket.setEventplace(detailIntro.getEventplace());
				tourTicket.setUsetimefestival(ticketService.getTicketPrice(detailIntro.getUsetimefestival()));
				
				System.out.println("\n[searchFestival damain check]==>" + searchFestival.toString());
				System.out.println("\n[detailIntro damain check]==>" + detailIntro.toString());
				System.out.println("\n[tourTicket damain check]==>" + tourTicket.toString());
				
				tourTicketList.add(tourTicket);

				map.put("tourTicketList", tourTicketList);
//				map.put("totalCount", openApiPage.getTotalCount());
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		map.put("totalCount", openApiPage.getTotalCount());
//		map.put("tourTicketList", tourTicketList);
		
		return map;
	}
	
	public Map<String, Object> getTicketList(OpenApiSearch openApiSearch) {
		
		System.out.println("\n[tourApiDaoImpl.java]::getTicketList");

		Map<String, Object> map = new HashMap<String, Object>();
		
		tourTicket = new TourTicket();
		
		OpenApiPage openApiPage = new OpenApiPage();
		
		try {
			// Date currentDate = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			// System.out.println("current date check :: " + simpleDateFormat.format(new Date()));

			StringBuilder searchFestivalSB = TourApiDaoImpl.sendGetTourURL
					(new StringBuilder(searchFestivalURL + essentialURL + "&eventStartDate=" + simpleDateFormat.format(new Date())
							+ "&pageNo=" + openApiSearch.getPageNo() + "&numOfRows=" + openApiSearch.getNumOfRows()
							+ "&arrange=" + openApiSearch.getSearchCondition() ));

			JSONObject sfJsonObj = (JSONObject) JSONValue.parse(searchFestivalSB.toString());
			JSONObject sfResponse = (JSONObject) sfJsonObj.get("response");
			JSONObject sfHeader = (JSONObject) sfResponse.get("header");

			JSONObject sfBody = (JSONObject) sfResponse.get("body");
			ObjectMapper sfBodyMapper = new ObjectMapper();
			openApiPage = sfBodyMapper.readValue(sfBody.toJSONString(), OpenApiPage.class);

//			map.put("totalCount", openApiPage.getTotalCount());

			JSONObject sfItems = (JSONObject) sfBody.get("items");
			JSONArray sfItem = (JSONArray) sfItems.get("item");

			List<TourTicket> tourTicketList = new ArrayList<TourTicket>();

			for (int i = 0; i < sfItem.size(); i++) {

				JSONObject itemValue = (JSONObject) sfItem.get(i);

				ObjectMapper objectMapper = new ObjectMapper();
				searchFestival = new SearchFestival();
				searchFestival = objectMapper.readValue(itemValue.toJSONString(), SearchFestival.class);

				detailIntro = new DetailIntro();
				detailIntro = this.getDetailIntro(searchFestival.getContentid(), searchFestival.getContenttypeid());

				tourTicket = new TourTicket();

				tourTicket.setTitle(searchFestival.getTitle().replaceAll(" 2018", ""));
				tourTicket.setContentid(searchFestival.getContentid());
				tourTicket.setContenttypeid(searchFestival.getContenttypeid());
				
				System.out.println("\n[1. Title Check] ==> " + searchFestival.getTitle());
				System.out.println("\n[2. searchFestival.getFirstimage() Check] ==> " + searchFestival.getFirstimage());
				if (searchFestival.getFirstimage() == null || searchFestival.getFirstimage() == "") {
					String image = ticketService.getNaverImage(searchFestival.getTitle() + " 2018");
					tourTicket.setFirstimage(image);
				} else {
					tourTicket.setFirstimage(searchFestival.getFirstimage());
				}
				
				tourTicket.setEventstartdate(searchFestival.getEventstartdate());
				tourTicket.setEventenddate(searchFestival.getEventenddate());
				tourTicket.setReadcount(searchFestival.getReadcount());
				tourTicket.setEventplace(detailIntro.getEventplace());
				tourTicket.setUsetimefestival(ticketService.getTicketPrice(detailIntro.getUsetimefestival()));
				
				tourTicketList.add(tourTicket);

				map.put("tourTicketList", tourTicketList);
//				map.put("totalCount", openApiPage.getTotalCount());
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		map.put("totalCount", openApiPage.getTotalCount());
//		map.put("tourTicketList", tourTicketList);
		
		return map;
	}

	public DetailIntro getDetailIntro(int contentId, int contentTypeId) {

		System.out.println("//======");
		System.out.println("[tourApiDaoImpl.java]::getDetailIntro");

		try {

			StringBuilder detailIntroSB = TourApiDaoImpl.sendGetTourURL(new StringBuilder(detailIntroURL + essentialURL
					+ "&introYN=Y" + "&contentId=" + contentId + "&contentTypeId=" + contentTypeId));

			JSONObject diJsonObj = (JSONObject) JSONValue.parse(detailIntroSB.toString());

			JSONObject diResponse = (JSONObject) diJsonObj.get("response");
			JSONObject diHeader = (JSONObject) diResponse.get("header");
			JSONObject diBody = (JSONObject) diResponse.get("body");
			JSONObject diItems = (JSONObject) diBody.get("items");
			JSONObject diItem = (JSONObject) diItems.get("item");

			ObjectMapper objectMapper = new ObjectMapper();
			detailIntro = new DetailIntro();
			detailIntro = objectMapper.readValue(diItem.toJSONString(), DetailIntro.class);

			if (detailIntro.getAgelimit() == "" || detailIntro.getAgelimit() == null) {
				detailIntro.setAgelimit("정보 제공 없음");
			}
			if (detailIntro.getBookingplace() == "" || detailIntro.getBookingplace() == null) {
				detailIntro.setBookingplace("정보 제공 없음");
			}
			if (detailIntro.getDiscountinfofestival() == "" || detailIntro.getDiscountinfofestival() == null) {
				detailIntro.setDiscountinfofestival("정보 제공 없음");
			}
			if (detailIntro.getEventhomepage() == "" || detailIntro.getEventhomepage() == null) {
				detailIntro.setEventhomepage("정보 제공 없음");
			}
			if (detailIntro.getEventplace() == "" || detailIntro.getEventplace() == null) {
				detailIntro.setEventplace("정보 제공 없음");
			}
			if (detailIntro.getFestivalgrade() == "" || detailIntro.getFestivalgrade() == null) {
				detailIntro.setFestivalgrade("정보 제공 없음");
			}
			if (detailIntro.getPlaceinfo() == "" || detailIntro.getPlaceinfo() == null) {
				detailIntro.setPlaceinfo("정보 제공 없음");
			}
			if (detailIntro.getPlaytime() == "" || detailIntro.getPlaytime() == null) {
				detailIntro.setPlaytime("정보 제공 없음");
			}
			if (detailIntro.getProgram() == "" || detailIntro.getProgram() == null) {
				detailIntro.setProgram("정보 제공 없음");
			}
			if (detailIntro.getSpendtimefestival() == "" || detailIntro.getSpendtimefestival() == null) {
				detailIntro.setSpendtimefestival("정보 제공 없음");
			}
			if (detailIntro.getSponsor1tel() == "" || detailIntro.getSponsor1tel() == null) {
				detailIntro.setSponsor1tel("정보 제공 없음");
			}
			if (detailIntro.getSponsor2tel() == "" || detailIntro.getSponsor2tel() == null) {
				detailIntro.setSponsor2tel("정보 제공 없음");
			}
			if (detailIntro.getSponsor1() == "" || detailIntro.getSponsor1() == null) {
				detailIntro.setSponsor1("정보 제공 없음");
			}
			if (detailIntro.getSponsor2() == "" || detailIntro.getSponsor2() == null) {
				detailIntro.setSponsor2("정보 제공 없음");
			}
			if (detailIntro.getSubevent() == "" || detailIntro.getSubevent() == null) {
				detailIntro.setSubevent("정보 제공 없음");
			}
			
			System.out.println("\n[detailIntro.getUsetimefestival()]==> " + detailIntro.getUsetimefestival());
			
			if (detailIntro.getUsetimefestival() == "" || detailIntro.getUsetimefestival() == null) {
				detailIntro.setUsetimefestival("무료");
			} else if ( detailIntro.getUsetimefestival().contains("000") == false || detailIntro.getUsetimefestival().contains("00") == false ) {
				detailIntro.setUsetimefestival("무료");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return detailIntro;
	}
	
	public DetailImage getDetailImage(int contentId, String title) throws Exception {

		System.out.println("\n[tourApiDaoImpl.java]::getDetailImage");
		
		DetailImage detailImage = new DetailImage();

		StringBuilder detailImageSB = TourApiDaoImpl.sendGetTourURL(new StringBuilder(
				detailImageURL + essentialURL + "&contentId=" + contentId + "&imageYN=Y" + "&subImageYN=Y"));

		JSONObject diJsonObj = (JSONObject) JSONValue.parse(detailImageSB.toString());
		JSONObject diResponse = (JSONObject) diJsonObj.get("response");
		JSONObject diHeader = (JSONObject) diResponse.get("header");
		JSONObject diBody = (JSONObject) diResponse.get("body");

		if (diBody.get("items").toString().equals("")) {
			
			String image = ticketService.getNaverImage(title);
			detailImage.setOriginimgurl(image);	
			
			
		} else {
			
			JSONObject diItems = (JSONObject) diBody.get("items");

			if (diItems.get("item") instanceof JSONObject) {

				System.out.println("[response] :: diItems instanceof = JSONObject");

				JSONObject item = (JSONObject) diItems.get("item");

				ObjectMapper objectMapper = new ObjectMapper();
				detailImage = new DetailImage();
				detailImage = objectMapper.readValue(item.toJSONString(), DetailImage.class);

			}
			
			if (diItems.get("item") instanceof JSONArray) {
				
				System.out.println("[response] :: diItems instanceof = JSONArray");

				JSONArray diItem = (JSONArray) diItems.get("item");
				
				for (int i = 0; i < diItem.size(); i++) {
					
					JSONObject value = (JSONObject) diItem.get(i);
					
					ObjectMapper objectMapper = new ObjectMapper();
					detailImage = new DetailImage();
					detailImage = objectMapper.readValue(value.toJSONString(), DetailImage.class);
				}
			}
		}
		return detailImage;
	}
	
	public List<String> getTicketPrice(String priceInfo) {

		System.out.println("\n[tourApiDaoImpl.java]::getTicketPrice");

		List<String> priceList = new ArrayList<String>();
		List<String> entranceFee = new ArrayList<String>();

		System.out.println("\n[2] ==> " + priceInfo);
		
		try {
			
			if (priceInfo.contains("000") || priceInfo.contains("00")) {

				System.out.println("\n[3] ==> " + priceInfo);

				String[] priceSplit = priceInfo.split(" |/|:");
				
				System.out.println("\n[4]==>"+priceSplit.length);
				
				for (int k = 0; k < priceSplit.length; k++) {
					System.out.println("\n[4] ==>" + priceSplit[k]);

					if (priceSplit[k].contains("000") || priceSplit[k].contains("00")) {

						System.out.println("\n[5] ==> " + priceSplit[k]);
						String priceValue = priceSplit[k].replaceAll("[^0-9]", "");
						System.out.println("\n[6] ==> " + priceValue);

						priceList.add(priceValue);
						System.out.println("\n[7]==>" + priceList.size());						
						
						for (int j = 0; j < priceList.size(); j++) {
							System.out.println("		[8] ==>" + priceList.get(j));
							System.out.println("");
//							tourTicket.setUsetimefestival(priceList);
						}
					}
				}

			} else if (priceInfo.equals("")) {
				System.out.println("[9. if it's Null]");
				entranceFee.add("무료");
				return entranceFee;
//				tourTicket.setUsetimefestival(entranceFee);

			} else {
				System.out.println("[10. if it's not Null]");
				entranceFee.add(priceInfo);
				return entranceFee;
//				tourTicket.setUsetimefestival(entranceFee);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return priceList;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////
	public String getNaverImage(String title) throws Exception {
		return null;
	}
	
	public String getKakaoImage(String title) throws Exception {
		return null;
	}
	
	public void addTicketLog(Ticket ticket) {
	}
	
	public Ticket getTicketFromDB(String contentId) {
		return null;
	}
	
} // end of class
