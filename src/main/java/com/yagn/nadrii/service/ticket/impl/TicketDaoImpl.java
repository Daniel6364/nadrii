package com.yagn.nadrii.service.ticket.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.yagn.nadrii.common.OpenApiSearch;
import com.yagn.nadrii.service.domain.DetailImage;
import com.yagn.nadrii.service.domain.DetailIntro;
import com.yagn.nadrii.service.domain.Ticket;
import com.yagn.nadrii.service.domain.Trip;
import com.yagn.nadrii.service.ticket.TicketDao;

@Repository("ticketDaoImpl")
public class TicketDaoImpl implements TicketDao {
	
	/// Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	/// Constructor
	public TicketDaoImpl() {
		System.out.println(this.getClass());
	}

	public void addTicketLog(Ticket ticket) {
		sqlSession.insert("TicketMapper.addTicketLog", ticket);
	}
	
	public Ticket getTicketFromDB(String contentId) {
		return sqlSession.selectOne("TicketMapper.ticketCheckDuplication",contentId);
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	public String getKakaoImage(String title) {
		return null;
	}
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
	public Map<String, Object> getSearchTicket(OpenApiSearch openApiSearch) {
		return null;
	}
	
} // end of class
