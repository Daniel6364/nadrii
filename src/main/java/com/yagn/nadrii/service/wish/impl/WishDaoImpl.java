package com.yagn.nadrii.service.wish.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.yagn.nadrii.service.domain.Wish;
import com.yagn.nadrii.service.wish.WishDao;

@Repository("wishDaoImpl")
public class WishDaoImpl implements WishDao {
	
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	public WishDaoImpl() {
		System.out.println(this.getClass());
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void addWishListFromTrip(Wish wish) throws Exception {
		sqlSession.insert("WishMapper.addWishFromTrip",wish);
	}
	
	@Override
	public void addWishListFromTicket(Wish wish) throws Exception {
		sqlSession.insert("WishMapper.addWishFromTicket",wish);
	}

	@Override
	public void deleteWish(Wish wish) throws Exception {
		sqlSession.delete("WishMapper.deleteWish", wish);
	}
	
	@Override
	public void addWishListFromPost(Wish wish) throws Exception {
		sqlSession.insert("WishMapper.addWishFromPost",wish);
	}

	@Override
	public Wish getWish(int wishNo) throws Exception {
		return sqlSession.selectOne("WishMapper.getWish",wishNo);
	}

	@Override
	public List<Wish> listWish(String userId) throws Exception {
		return sqlSession.selectList("WishMapper.listWish",userId);
	}

	@Override
	public Wish getWishByTripNo(Map map) throws Exception {
		return sqlSession.selectOne("WishMapper.getWishByTripNo",map);
	}

	@Override
	public Wish checkDuplication(int postNo) throws Exception {
		return sqlSession.selectOne("WishMapper.checkDuplication",postNo);	
	}
	
	public List<Wish> listWishByPost(Wish wish) throws Exception{
		return sqlSession.selectList("WishMapper.listWishByPost", wish);		
	}
	
	public int getTotalWishByPost(Wish wish) throws Exception{
		return sqlSession.selectOne("WishMapper.getTotalWishByPost", wish);
	}
		
	
}
