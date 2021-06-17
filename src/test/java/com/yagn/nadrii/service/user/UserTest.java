package com.yagn.nadrii.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.User;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })



public class UserTest {
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	//@Test
	public void addUserTest()throws Exception {
		User user = new User();
		user.setUserId("testermamuri");
		user.setEmail("questsolve@hanmail.net");
		user.setPassword("1234");
		userService.addUser(user);
		
	}
	
	//@Test	
	public void getUserTest() throws Exception{
		User user = userService.getUser("questsolve");
		System.out.println(user);
		Assert.assertEquals(user.getUserId(), "questsolve");
	}
	
	//@Test
	public void updateUserTest()throws Exception{
		User user = userService.getUser("postget");
		System.out.println(user);
		user.setUserName("해결사");
		user.setPhone("123-456-789");
		user.setGender("진성");
		user.setProfileImageFile("이미지없음");
		user.setChildren("1");
		user.setBirth("88/04/28");
		userService.updateUser(user);
		user = userService.getUser("questsolve");
		System.out.println(user);
	}
	
	//@Test
	public void listUser() throws Exception{
		Search search = new Search();
		search.setPageSize(20);
		search.setCurrentPage(1);
		
		Map map = userService.listUser(search);
		System.out.println(map.get("list"));
		System.out.println(map.get("totalCount"));
						
	}
	
	//@Test
	public void checkDuplicationTest() throws Exception{
		System.out.println("questsolve의 가입 가능 여부  =>"+userService.checkDuplication("questsolve"));
		System.out.println("dupli의 가입 가능 여부  =>"+userService.checkDuplication("dupli"));
		
	}
	
	//@Test
	public void getUserByEmail()throws Exception{
		User user = userService.getUserByEmail("test03@naver.com");
		System.out.println(user);
	}
	
	//@Test
	public void getPasswordTest()throws Exception{
		User user = new User();
		user.setUserId("test03");
		user.setEmail("test03@naver.com");
		System.out.println(user);
		user = userService.getPassword(user);
		System.out.println(user);
		
	}
	

}
