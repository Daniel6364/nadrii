package com.yagn.nadrii.service.comm;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Community;


/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)

//==> Meta-Data �� �پ��ϰ� Wiring ����...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
										"classpath:config/context-aspect.xml",
										"classpath:config/context-mybatis.xml",
										"classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class CommServiceTest{
	
	@Autowired
	@Qualifier("commServiceImpl")
	private CommService commService;

	@Test
	public void testAddUser() throws Exception {
		
		Community community = new Community();
		community.setTitle("����Ÿ�� �����Ƿ���?");
		community.setText("<h3>����־����ϴ�~</h3><img src=\"#\">");
		//community.setUser(user);("test01");
		community.setLat("35.8294096");
		community.setLng("129.2251832");
		community.setThumbNailFileName("abc.jpg");
		community.setHashtag(",����,���帶ũ,����,nŸ��,�ް�,");

		commService.addComm(community);

		//==> console Ȯ��
		//System.out.println(community);
		
		//==> API Ȯ��
//		Assert.assertEquals("testUserId", community.getUserId());
//		Assert.assertEquals("testUserName", community.getUserName());
//		Assert.assertEquals("testPasswd", community.getPassword());
//		Assert.assertEquals("111-2222-3333", community.getPhone());
//		Assert.assertEquals("��⵵", community.getAddr());
//		Assert.assertEquals("test@test.com", community.getEmail());
	}
	
	//@Test
	public void testGetUser() throws Exception {
		
		Community community = new Community();
		//==> �ʿ��ϴٸ�...
//			community.setUserId("testUserId");
//			community.setUserName("testUserName");
//			community.setPassword("testPasswd");
//			community.setSsn("1111112222222");
//			community.setPhone("111-2222-3333");
//			community.setAddr("��⵵");
//			community.setEmail("test@test.com");
		
		community = commService.getComm(30003);

		//==> console Ȯ��
		//System.out.println(community);
		
		//==> API Ȯ��
//		Assert.assertEquals("testUserId", community.getUserId());
//		Assert.assertEquals("testUserName", community.getUserName());
//		Assert.assertEquals("testPasswd", community.getPassword());
//		Assert.assertEquals("111-2222-3333", community.getPhone());
//		Assert.assertEquals("��⵵", community.getAddr());
//		Assert.assertEquals("test@test.com", community.getEmail());

		System.out.println(community);
	}
	
	//@Test
	 public void testUpdateUser() throws Exception{
		 
		Community community = commService.getComm(30003);
		community.setThumbNailFileName("efg.jpg");
		
//		Assert.assertEquals("testUserName", community.getUserName());
//		Assert.assertEquals("111-2222-3333", community.getPhone());
//		Assert.assertEquals("��⵵", community.getAddr());
//		Assert.assertEquals("test@test.com", community.getEmail());

//		community.setUserName("change");
//		community.setPhone("777-7777-7777");
//		community.setAddr("change");
//		community.setEmail("change@change.com");
		
		commService.updateComm(community);
		
		community = commService.getComm(30003);
		
		//==> console Ȯ��
		//System.out.println(community);
			
		//==> API Ȯ��
//		Assert.assertEquals("change", community.getUserName());
//		Assert.assertEquals("777-7777-7777", community.getPhone());
//		Assert.assertEquals("change", community.getAddr());
//		Assert.assertEquals("change@change.com", community.getEmail());
	 }
	 
	 //==>  �ּ��� Ǯ�� �����ϸ�....
	 //@Test
	 public void testGetUserListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = commService.listComm(search);
	 	
	 	List<Object> list = (List<Object>)map.get("listComm");
	 	Assert.assertEquals(2, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("2");
	 	search.setSearchKeyword(",����,");
	 	map = commService.listComm(search);
	 	
	 	list = (List<Object>)map.get("listComm");
	 	Assert.assertEquals(1, list.size());
	 	
	 	//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetUserListByUserId() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("admin");
	 	Map<String,Object> map = commService.listComm(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = commService.listComm(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetUserListByUserName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("SCOTT");
	 	Map<String,Object> map = commService.listComm(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = commService.listComm(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
	 
	 //@Test
	 public void testListHashTag() throws Exception{
		
		String filePath = "C:\\\\Users\\\\Yuri\\\\git\\\\NadriiProject\\\\nadrii\\\\WebContent\\\\resources\\\\images\\\\6700442525_688f48b58b_b.jpg";

		List<String> results = commService.listHasgTag(filePath);
		for(String result : results) {
			System.out.println(result);
		}
	 }
}