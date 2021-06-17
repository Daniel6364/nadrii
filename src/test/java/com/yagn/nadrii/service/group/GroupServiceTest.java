package com.yagn.nadrii.service.group;

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
import com.yagn.nadrii.service.domain.Group;
import com.yagn.nadrii.service.domain.Join;
import com.yagn.nadrii.service.join.JoinService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {	"classpath:config/context-common.xml",
															"classpath:config/context-aspect.xml",
															"classpath:config/context-mybatis.xml",
															"classpath:config/context-transaction.xml" })
public class GroupServiceTest {

	//==>@RunWith,@ContextConfiguration 占싱울옙 Wiring, Test 占쏙옙 instance DI
	@Autowired
	@Qualifier("groupServiceImpl")
	private GroupService groupService;
	
	@Autowired
	@Qualifier("joinServiceImpl")
	private JoinService joinService;

	//@Test
	public void testAddGroup() throws Exception {
		
		Group group = new Group();
		Join join = new Join();
		
		group.setTitle("모임 참여하기111");
		group.setText("내용13");
		
		int groupNo = groupService.addGroup(group);

		System.out.println("groupNo : "+groupNo);

		join.setGroupNo(groupNo);
		join.setGroupRole(1);
		//join.setUserId("test01");
		
		group.setJoin(join);
		
		joinService.addJoin(join);
		
		System.out.println(group);
		
	}
	
	//@Test
	public void testGetGroup() throws Exception {
		
		Group group = new Group();
		
		group = groupService.getGroup(60033);
		
		System.out.println(group);
		
	}
	
	//@Test
	public void testUpdateGroup() throws Exception{
		 
		Group group = new Group();
		Join join = new Join();
		
		group = groupService.getGroup(60033);
		
		System.out.println(group);
		
		join.setGroupNo(60033);
		group.setJoin(join);
		
		groupService.updateGroup(group);
				
		System.out.println(group);
	 }
	
	@Test
	public void testDeleteGroup() throws Exception {
			
		Group group = new Group();
		Join join = new Join();
		
		join.setGroupNo(60124);
		//join.setUserId("test01");
		
		joinService.deleteJoin(join);
		
		group.setJoin(join);
		
		groupService.deleteGroup(group);
	}
	 
	
	//@Test
	public void testGetGroupListAll() throws Exception{
		 
		Search search = new Search();
		search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = groupService.listGroup(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확占쏙옙
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = groupService.listGroup(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console 확占쏙옙
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }

	//@Test
	public void testGetThumbnail() throws Exception{
		 
		System.out.println(groupService.getThumbNail("경복궁"));
				
	 }
}