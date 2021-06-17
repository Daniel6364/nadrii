package com.yagn.nadrii.service.user;

import java.util.List;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.User;



public interface UserDao {
	
	// INSERT
	public void addUser(User user) throws Exception ;

	// SELECT ONE
	public User getUser(String userId) throws Exception ;

	// SELECT LIST
	public List<User> listUser(Search search) throws Exception ;

	// UPDATE
	public void updateUser(User user) throws Exception ;
	
	//totalCount of User
	public int getTotalCount(Search search) throws Exception ;
	
	public User getUser(User user) throws Exception;
	
	public User getUserByEmail(String email) throws Exception;
	
	public int checkId(String userId) throws Exception;
}