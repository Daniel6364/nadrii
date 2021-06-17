package com.yagn.nadrii.service.like;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yagn.nadrii.common.Page;
import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Like;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {	"classpath:config/context-common.xml",
															"classpath:config/context-aspect.xml",
															"classpath:config/context-mybatis.xml",
															"classpath:config/context-transaction.xml" })
public class LikeServiceTest {

	@Autowired
	@Qualifier("likeServiceImpl")
	private LikeService likeService;
	
	@Test
	public void testAddLike() throws Exception {

		Like like = new Like();
		
		like.setPostNo(60058);
		like.setUserId("test01");
				
		likeService.addLike(like);
		
		System.out.println(like);
		
	}

	//@Test
	public void testDeleteLike() throws Exception {
		
		Like like = new Like();
		
		like.setPostNo(60065);
		like.setUserId("test01");
		
		likeService.deleteLike(like);
		
	}
			 
	//@Test
	public void testGetLikeListAll() throws Exception{
		 
		Search search = new Search();
		Like like = new Like();
		
		search.setCurrentPage(1);
	 	search.setPageSize(3);
		
		like.setUserId("test01");
		search.setSearchKeyword(like.getUserId());

		Map<String , Object> map=likeService.listLikeById(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), 5, 1);
		System.out.println("resultPage :: "+resultPage);
	
	 	System.out.println(map);
	 	System.out.println(map.get("list"));
	 }

}