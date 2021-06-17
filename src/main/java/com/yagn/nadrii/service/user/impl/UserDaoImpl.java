package com.yagn.nadrii.service.user.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Purchase;
import com.yagn.nadrii.service.domain.User;
import com.yagn.nadrii.service.user.UserDao;



@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public UserDaoImpl() {
		System.out.println(this.getClass());
	}

	///Method
	public void addUser(User user) throws Exception {
		System.out.println(this.getClass()+"/addUser");
		System.out.println(user);
		sqlSession.insert("UserMapper.addUser", user);
	}

	public User getUser(String userId) throws Exception {
		System.out.println(this.getClass()+"/getUser");
		return sqlSession.selectOne("UserMapper.getUser", userId);
	}
	
	public void updateUser(User user) throws Exception {
		System.out.println(this.getClass()+"/updateUser");
		System.out.println("프로필 사진 >>" + user.getProfileImageFile());
		sqlSession.update("UserMapper.updateUser", user);
	}

	public List<User> listUser(Search search) throws Exception {
		System.out.println(this.getClass()+"/listUser");
		return sqlSession.selectList("UserMapper.listUser", search);
	}


	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("UserMapper.getTotalCount", search);
	}

	
	public User getUserByEmail(String email) throws Exception{
		return sqlSession.selectOne("UserMapper.getUserByEmail",email);
	}
	

	public User getUser(User user) throws Exception{
		return sqlSession.selectOne("UserMapper.getPassword",user);		
	}
	
	@Override
	public int checkId (String userId) throws Exception{
		return sqlSession.selectOne("UserMapper.checkId", userId);
	}
	
}