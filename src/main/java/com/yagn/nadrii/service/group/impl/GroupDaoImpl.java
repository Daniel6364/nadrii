package com.yagn.nadrii.service.group.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Group;
import com.yagn.nadrii.service.group.GroupDao;

@Repository("groupDaoImpl")
public class GroupDaoImpl implements GroupDao{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public GroupDaoImpl() {
		System.out.println(this.getClass());
	}

	///Method
	public int addGroup(Group group) throws Exception {
		sqlSession.insert("GroupMapper.addGroup", group);
		int groupNo = sqlSession.selectOne("GroupMapper.getGroupNo", group);
		return groupNo;
	}
	
	public Group getGroup(int groupNo) throws Exception {
		return sqlSession.selectOne("GroupMapper.getGroup", groupNo);
	}
	
	public void updateViewCount(Group group) throws Exception {
		sqlSession.update("GroupMapper.updateViewCount", group);
	}

	public List<Group> listGroup(Search search) throws Exception {
		return sqlSession.selectList("GroupMapper.listGroup", search);
	}
	
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("GroupMapper.getTotalCount", search);
	}
	
	public void updateGroup(Group group) throws Exception {
		sqlSession.update("GroupMapper.updateGroup", group);
	}

	public void deleteGroup(Group group) throws Exception {
		sqlSession.delete("GroupMapper.deleteGroup", group);
	}
	
}