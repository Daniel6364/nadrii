package com.yagn.nadrii.service.comm.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.comm.CommDao;
import com.yagn.nadrii.service.domain.Community;

@Repository("commDaoImpl")
public class CommDaoImpl implements CommDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	@Override
	public int addComm(Community community) {
		sqlSession.insert("CommMapper.addComm", community);
		int postNo = sqlSession.selectOne("CommMapper.getPostNo", community);
		return postNo;
	}

	@Override
	public Community getComm(int postNo) {
		return sqlSession.selectOne("CommMapper.getComm", postNo);
	}

	@Override
	public List<Community> listComm(Search search) {
		return sqlSession.selectList("CommMapper.listComm", search);
	}

	@Override
	public void updateComm(Community community) {
		sqlSession.update("CommMapper.updateComm",community);
	}

	@Override
	public void deleteComm(int postNo) {
		sqlSession.delete("CommMapper.deleteComm", postNo);
	}

	@Override
	public int getTotalCount(Search search) {
		return sqlSession.selectOne("CommMapper.getTotalCount", search);
	}

	@Override
	public List<String> getHashtags(String keyword) {
		return sqlSession.selectList("CommMapper.getHashtags", keyword);
	}

	@Override
	public void updateViewCount(Community community) {
		sqlSession.update("CommMapper.updateViewCount", community);
	}
	
	
}
