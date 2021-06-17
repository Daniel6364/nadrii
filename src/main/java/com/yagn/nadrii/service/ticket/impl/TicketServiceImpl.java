package com.yagn.nadrii.service.ticket.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yagn.nadrii.common.OpenApiSearch;
import com.yagn.nadrii.service.domain.DetailImage;
import com.yagn.nadrii.service.domain.DetailIntro;
import com.yagn.nadrii.service.domain.Purchase;
import com.yagn.nadrii.service.domain.SearchFestival;
import com.yagn.nadrii.service.domain.Ticket;
import com.yagn.nadrii.service.domain.Trip;
import com.yagn.nadrii.service.ticket.TicketDao;
import com.yagn.nadrii.service.ticket.TicketService;

@Service("ticketServiceImpl")
public class TicketServiceImpl implements TicketService {
	
	/// Field
	@Autowired
	@Qualifier("tourApiDaoImpl")
	private TicketDao tourApiDao;
	
	public void setTourApiDao(TicketDao tourApiDao) {
		this.tourApiDao = tourApiDao;
	}
	
	@Autowired
	@Qualifier("naverApiDaoImpl")
	private TicketDao naverApiDao;
	
	public void setNaverApiDao(TicketDao naverApiDao) {
		this.naverApiDao = naverApiDao;
	}
	
	@Autowired
	@Qualifier("kakaoApiDaoImpl")
	private TicketDao kakaoApiDao;
	
	public void setKakaoApiDao(TicketDao kakaoApiDao) {
		this.kakaoApiDao = kakaoApiDao;
	}
	
	@Autowired
	@Qualifier("ticketDaoImpl")
	private TicketDao ticketDao;
	
	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}
	
	/// Constructor
	public TicketServiceImpl() {
		System.out.println(this.getClass());
	}
	
	@Override
	public void addTicketLog(Ticket ticket) throws Exception {
		System.out.println("\n[ticketServiceImpl.java / addTicketLog]");
		ticketDao.addTicketLog(ticket);
	}

	@Override
	public Map<String, Object> getTicketList(OpenApiSearch openApiSearch) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (openApiSearch.getSearchKeyword() == null || openApiSearch.getSearchKeyword() == "") {
			map = tourApiDao.getTicketList(openApiSearch);
		} else {
			map = tourApiDao.getSearchTicketList(openApiSearch);
		}
		
		return map;
	}
	
	@Override
	public Map<String, Object> getSearchTicket(OpenApiSearch openApiSearch) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map = tourApiDao.getSearchTicket(openApiSearch);
		
		return map;
	}
	
	@Override
	public DetailIntro getTicket(int contentId, int contentTypeId) throws Exception {
		return tourApiDao.getDetailIntro(contentId, contentTypeId);
	}
	
	@Override
	public DetailImage getDetailImage(int contentId, String title) throws Exception {
		return tourApiDao.getDetailImage(contentId, title);
	}
	
	@Override
	public String getNaverImage(String title) throws Exception {
		return naverApiDao.getNaverImage(title);
	}

	@Override
	public String getKakaoImage(String title) throws Exception {
		return kakaoApiDao.getKakaoImage(title);
	}
	
	public List<String> getTicketPrice(String priceInfo) throws Exception {
		return tourApiDao.getTicketPrice(priceInfo);
	}
	
	public Ticket getTicketFromDB(String contentId)throws Exception {
		return ticketDao.getTicketFromDB(contentId);
	}
	
}
