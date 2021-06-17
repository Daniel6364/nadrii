package com.yagn.nadrii.service.user;

import java.util.Map;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.User;


public interface UserService {
	

	public void addUser(User user) throws Exception;

	public User getUser(String userId) throws Exception;
	
	public Map<String , Object> listUser(Search search) throws Exception;

	public void updateUser(User user) throws Exception;
	
	public boolean checkDuplication(String userId) throws Exception;
	
	public User getUserByEmail(String email) throws Exception;
	
	public User getPassword(User user) throws Exception;
	
	public int checkId(String userId) throws Exception;
	
	
}