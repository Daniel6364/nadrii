package com.yagn.nadrii.service.trip.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Trip;
import com.yagn.nadrii.service.trip.TripDao;
import com.yagn.nadrii.service.trip.domain.TourApiDomain;
import com.yagn.nadrii.service.trip.urlmanage.TourAPIGetDetailUrlManage;
import com.yagn.nadrii.service.trip.urlmanage.TourAPIGetUrlManage;
import com.yagn.nadrii.service.trip.urlmanage.TourAPlListUrlManage;

@Repository("tripDaoImpla")
public class TripDaoImpl implements TripDao {

	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	
	public TripDaoImpl() {
	}

	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
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
	public String naverImageSearch(String target) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void addTrip(Trip trip) throws Exception {
		System.out.println( trip.toString() );
		sqlSession.insert("TripMapper.addTrip", trip);
	}


	@Override
	public Trip getTrip(int postNo) throws Exception {
		return sqlSession.selectOne("TripMapper.getTrip", postNo);
	}


	@Override
	public List<Trip> listTrip(Search search) throws Exception {
		//
		return sqlSession.selectList("TripMapper.listTrip",search);
	}


	@Override
	public void updateViewCount(String contentId) throws Exception {
		sqlSession.update("TripMapper.updateViewCount",contentId);
		
	}


	@Override
	public Trip getTripFromDB(String contentid) throws Exception {

		return sqlSession.selectOne("TripMapper.tripCheckDuplication",contentid);
		
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

		return sqlSession.selectList("TripMapper.listTripinDB");
	}


	
	
	

}
