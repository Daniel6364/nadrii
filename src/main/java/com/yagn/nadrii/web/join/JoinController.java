package com.yagn.nadrii.web.join;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yagn.nadrii.common.Page;
import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Join;
import com.yagn.nadrii.service.domain.User;
import com.yagn.nadrii.service.join.JoinService;

@Controller
@RequestMapping("/join/*")
public class JoinController {
	
	@Autowired
	@Qualifier("joinServiceImpl")
	private JoinService joinService;
		
	public JoinController(){
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping(value="addJoin", method=RequestMethod.POST)
	public String addJoin(@ModelAttribute("join") Join join, @RequestParam("groupNo") int groupNo, HttpSession session) throws Exception {

		System.out.println("/addJoin");
	
		join.setGroupNo(groupNo);
		join.setGroupRole(2);
		User user = new User();
		user.setUserId(((User)session.getAttribute("loginUser")).getUserId());
		join.setUser(user);
		
		joinService.addJoin(join);
		
		return "forward:/group/getGroup?groupNo="+groupNo;
	}
	
	@RequestMapping(value="getJoinMemberList", method=RequestMethod.GET)
	public String getJoinMemberList(@ModelAttribute("join") Join join, @ModelAttribute("search") Search search, Model model) throws Exception {
		
		System.out.println("/getJoin");
		
		Map<String , Object> map=joinService.getJoinMemberList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
				
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		//map.put("list", joinService.getJoinMemeberList(groupNo));
	
		return "forward:/join/getJoinMemberList?groupNo=";
		
	}
		
	@RequestMapping(value="listJoin")
	public String listJoin(@ModelAttribute("join") Join join, @ModelAttribute("search") Search search, Model model, HttpSession session) throws Exception{
		
		System.out.println("/listJoin");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		User user = (User)session.getAttribute("loginUser");
		
		join.setUser(user);
		search.setSearchKeyword(join.getUser().getUserId());
		
		Map<String , Object> map=joinService.getJoinList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("resultPage :: "+resultPage);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/join/listJoin.jsp";
	}
		
	@RequestMapping(value="deleteJoin")
	public String deletePurchase(@ModelAttribute("join") Join join, @RequestParam("groupNo") int groupNo, Model model, HttpSession session) throws Exception {
		
		System.out.println("/deleteJoin");
		
		join.setGroupNo(groupNo);
		User user = new User();
		user.setUserId(((User)session.getAttribute("loginUser")).getUserId());
		join.setUser(user);
		
		joinService.deleteJoin(join);
		
		model.addAttribute("join", join);
		
		return "forward:/group/getGroup?groupNo="+groupNo;
	}
	

}