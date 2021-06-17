package com.yagn.nadrii.web.join;

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
import com.yagn.nadrii.service.domain.Join;
import com.yagn.nadrii.service.domain.User;
import com.yagn.nadrii.service.join.JoinService;

@RestController
@RequestMapping("/join/*")
public class JoinRestController {
	
	///Field
	@Autowired
	@Qualifier("joinServiceImpl")
	private JoinService joinService;
		
	@Autowired
	@Qualifier("commentServiceImpl")
	private CommentService commentService;
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	public JoinRestController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="json/addJoin/{groupNo}", method=RequestMethod.POST)
	public Join addJoin(@PathVariable int groupNo, HttpSession session) throws Exception {

		System.out.println("/join/json/addJoin :: POST");
		
		Join join = new Join();
		
		join.setGroupNo(groupNo);
		join.setGroupRole(2);
		User user = new User();
		user.setUserId(((User)session.getAttribute("loginUser")).getUserId());
		join.setUser(user);
				
		joinService.addJoin(join);
		
		return join;
	}
	
	@RequestMapping(value="json/deleteJoin/{groupNo}", method=RequestMethod.POST)
	public Join deleteJoin(@PathVariable int groupNo, HttpSession session) throws Exception {

		System.out.println("/join/json/deleteJoin");
		
		Join join = new Join();
		
		join.setGroupNo(groupNo);
		User user = new User();
		user.setUserId(((User)session.getAttribute("loginUser")).getUserId());
		join.setUser(user);
				
		joinService.deleteJoin(join);
		
		return join;
	}
	
	@RequestMapping(value="json/getJoinMemberList/{groupNo}" , method=RequestMethod.GET)
	public Map getJoinMemeberList(@PathVariable int groupNo) throws Exception {
		
		System.out.println("/join/json/getJoinMemberList");
		
		Search search = new Search();
		search.setSearchKeyword(Integer.toString(groupNo));
		
		return joinService.getJoinMemberList(search);
	}
		
	@RequestMapping(value="json/listJoinById")
	public Map listJoin(HttpSession session, @RequestParam int currentPage) throws Exception{
		
		System.out.println("/join/json/listJoinById");

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
		
		Map<String , Object> map=joinService.getJoinList(search);
		List<Join> joins = (List)map.get("list");
		
		List<String> yearNMonth = new ArrayList<>();
		List<String> day = new ArrayList<>();
		List<String> titles = new ArrayList<>();
		for(int i=0;i<joins.size();i++) {
			String[] temp01 = joins.get(i).getRegDate().toString().split(" ");
			String[] temp = temp01[0].split("-");
			yearNMonth.add(temp[0]+"."+temp[1]);
			day.add(temp[2]);
			titles.add(commentService.listTitle(joins.get(i).getGroupNo()));
		}
		System.out.println("$볼 것$ : "+joins+yearNMonth+day);
		map.put("title", titles);
		map.put("yearNMonth", yearNMonth);
		map.put("day", day);
		
		return map;
	}
}