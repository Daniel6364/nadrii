package com.yagn.nadrii.web.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.common.CommentService;
import com.yagn.nadrii.service.domain.Like;
import com.yagn.nadrii.service.domain.User;
import com.yagn.nadrii.service.like.LikeService;

@RestController
@RequestMapping("/like/*")
public class LikeRestController {
	
	///Field
	@Autowired
	@Qualifier("likeServiceImpl")
	private LikeService likeService;
	@Autowired
	@Qualifier("commentServiceImpl")
	private CommentService commentService;
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
		
	public LikeRestController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="json/addLike/{postNo}", method=RequestMethod.POST)
	public Like addPostLike(@PathVariable int postNo, HttpSession session) throws Exception {

		System.out.println("/like/json/addLike");
		
		Like like = new Like();
		
		like.setPostNo(postNo);
		like.setUserId(((User)session.getAttribute("loginUser")).getUserId());
				
		likeService.addLike(like);
		
		return like;
	}
	
	@RequestMapping(value="json/deleteLike/{postNo}", method=RequestMethod.POST)
	public Like deleteLike(@PathVariable int postNo, HttpSession session) throws Exception {

		System.out.println("/like/json/deleteLike");
		
		Like like = new Like();
		
		like.setPostNo(postNo);
		like.setUserId(((User)session.getAttribute("loginUser")).getUserId());
				
		likeService.deleteLike(like);
		
		return like;
	}
	
	@RequestMapping(value="json/listLikeById")
	public Map listLikeById(HttpSession session, @RequestParam int currentPage) throws Exception{
		
		System.out.println("json/listLikeById");

		Search search = new Search();
		search.setCurrentPage(currentPage);
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		
		if(search.getPageSize() == 0) {
			search.setPageSize(pageSize);
		}else {
			pageSize = search.getPageSize();
		}
		
		search.setSearchKeyword(((User)session.getAttribute("loginUser")).getUserId());
		
		Map<String , Object> map=likeService.listLikeById(search);
		List<Like> likes = (List)map.get("list");
		
		List<String> yearNMonth = new ArrayList<>();
		List<String> day = new ArrayList<>();
		List<String> titles = new ArrayList<>();
		for(int i=0;i<likes.size();i++) {
			String[] temp01 = likes.get(i).getRegDate().toString().split(" ");
			String[] temp = temp01[0].split("-");
			yearNMonth.add(temp[0]+"."+temp[1]);
			day.add(temp[2]);
			titles.add(commentService.listTitle(likes.get(i).getPostNo()));
		}
		System.out.println("$볼 것$ : "+likes+yearNMonth+day);
		map.put("title", titles);
		map.put("yearNMonth", yearNMonth);
		map.put("day", day);
		
		return map;
	}
	
	@RequestMapping(value="json/listLikeByPost/{postNo}")
	public Map listLikeByPost(@PathVariable int postNo) throws Exception{
		
		System.out.println("json/listLikeByPost");
		
		Like like = new Like();
		like.setPostNo(postNo);
		
		Map<String , Object> map=likeService.listLikeByPost(like);
		
		return map;
	}
}