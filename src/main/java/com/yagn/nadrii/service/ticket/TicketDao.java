package com.yagn.nadrii.service.ticket;

import java.util.List;
import java.util.Map;

import com.yagn.nadrii.common.OpenApiSearch;
import com.yagn.nadrii.service.domain.DetailImage;
import com.yagn.nadrii.service.domain.DetailIntro;
import com.yagn.nadrii.service.domain.Ticket;
import com.yagn.nadrii.service.domain.Trip;

public interface TicketDao {

	/// TourAPI
	public Map<String, Object> getTicketList(OpenApiSearch openApiSearch) throws Exception;

	public Map<String, Object> getSearchTicketList(OpenApiSearch openApiSearch) throws Exception;
	
	public Map<String, Object> getSearchTicket(OpenApiSearch openApiSearch) throws Exception;

	public DetailIntro getDetailIntro(int contentId, int contentTypeId) throws Exception;
	
	public DetailImage getDetailImage(int contentId, String title) throws Exception;
	
	public List<String> getTicketPrice(String priceInfo) throws Exception;
	
	public void addTicketLog(Ticket ticket) throws Exception;
	
	public Ticket getTicketFromDB(String contentId) throws Exception;
	
	
	/// NaverAPI
	public String getNaverImage(String title) throws Exception;
	
	
	/// KakaoAPI
	public String getKakaoImage(String title) throws Exception;
	
}
