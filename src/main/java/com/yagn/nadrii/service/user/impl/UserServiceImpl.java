package com.yagn.nadrii.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.User;
import com.yagn.nadrii.service.user.UserDao;
import com.yagn.nadrii.service.user.UserService;




@Service("userServiceImpl")
public class UserServiceImpl implements UserService{
	
	///Field
	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	///Constructor
	public UserServiceImpl() {
		System.out.println(this.getClass());
	}

	///Method
	public void addUser(User user) throws Exception {
		userDao.addUser(user);
	}

	public User getUser(String userId) throws Exception {
		System.out.println("userId ==" + userId);
		return userDao.getUser(userId);
	}
	
	public User getUserByEmail(String email) throws Exception{
		return userDao.getUserByEmail(email);
	}
	
	public User getPassword(User user) throws Exception{
		return userDao.getUser(user);
		
	}

	public Map<String , Object > listUser(Search search) throws Exception {
		List<User> list= userDao.listUser(search);
		int totalCount = userDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	public void updateUser(User user) throws Exception {
		user.getBirth().replaceAll("/","").trim();
		
		userDao.updateUser(user);
	}

	public boolean checkDuplication(String userId) throws Exception {
		boolean result=true;
		User user=userDao.getUser(userId);
		if(user != null) {
			result=false;
		}
			
		return result;
	}

	@Override
	public int checkId(String userId) throws Exception {
		return userDao.checkId(userId);
	}
			  
}