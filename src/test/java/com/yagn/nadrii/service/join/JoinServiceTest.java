package com.yagn.nadrii.service.join;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yagn.nadrii.common.Page;
import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Join;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {	"classpath:config/context-common.xml",
															"classpath:config/context-aspect.xml",
															"classpath:config/context-mybatis.xml",
															"classpath:config/context-transaction.xml" })
public class JoinServiceTest {

	@Autowired
	@Qualifier("joinServiceImpl")
	private JoinService joinService;
	
	//@Test
	public void testAddJoin() throws Exception {

		Join join = new Join();
		
		join.setGroupNo(60036);
		join.setGroupRole(2);
		//join.setUserId("test02");
				
		joinService.addJoin(join);
		
		System.out.println(join);
		
	}
	
	//@Test
	public void testAddJoin1() throws Exception {

		Join join = new Join();
		
		join.setGroupNo(60033);
		join.setGroupRole(1);
		//join.setUserId("test01");
				
		joinService.addJoin(join);
		
		System.out.println(join);
		
	}

	//@Test
	public void testDeleteJoin() throws Exception {
		
		Join join = new Join();
		
		join.setGroupNo(60033);
		//join.setUserId("test02");
		
		joinService.deleteJoin(join);
		
	}
			 
	//@Test
	public void testGetJoinListAll() throws Exception{
		 
		Search search = new Search();
		Join join = new Join();
		
		search.setCurrentPage(1);
	 	search.setPageSize(3);
		
		//join.setUserId("test01");
		//search.setSearchKeyword(join.getUserId());
		
		Map<String , Object> map=joinService.getJoinList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), 5, 1);
		System.out.println("resultPage :: "+resultPage);
	
	 	System.out.println(map);
	 	System.out.println(map.get("list"));
	 }
	 
	
}