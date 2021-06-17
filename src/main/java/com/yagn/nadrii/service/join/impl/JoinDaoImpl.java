package com.yagn.nadrii.service.join.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Join;
import com.yagn.nadrii.service.join.JoinDao;

@Repository("joinDaoImpl")
public class JoinDaoImpl implements JoinDao{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public JoinDaoImpl() {
		System.out.println(this.getClass());
	}

	///Method
	public void addJoin(Join join) throws Exception {
		sqlSession.insert("JoinMapper.addJoin", join);
	}
	
	public String getGroupMaster(int groupNo) throws Exception {
		return sqlSession.selectOne("JoinMapper.getGroupMaster", groupNo);
	}
	
	public void deleteJoin(Join join) throws Exception {
		sqlSession.delete("JoinMapper.deleteJoin", join);
	}

	@Override
	public List<Join> getJoinList(Search search) throws Exception {
		return sqlSession.selectList("JoinMapper.getJoinList", search);
	}

	@Override
	public int getTotalJoinCount(Search search) throws Exception {
		return sqlSession.selectOne("JoinMapper.getTotalJoinCount", search);
	}
	
	@Override
	public int getTotalMemberCount(Search search) throws Exception {
		return sqlSession.selectOne("JoinMapper.getTotalMemberCount", search);
	}

	@Override
	public List<Join> getJoinMemberList(Search search) throws Exception {
		return sqlSession.selectList("JoinMapper.getJoinMemberList", search);
	}
	
}