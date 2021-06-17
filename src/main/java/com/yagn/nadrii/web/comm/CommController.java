 package com.yagn.nadrii.web.comm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yagn.nadrii.common.Page;
import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.comm.CommService;
import com.yagn.nadrii.service.common.CommentService;
import com.yagn.nadrii.service.domain.Community;
import com.yagn.nadrii.service.domain.User;

@Controller
@RequestMapping("/comm/*")
public class CommController {
	
	@Autowired
	@Qualifier("commServiceImpl")
	private CommService commService;
	@Autowired
	@Qualifier("commentServiceImpl")
	private CommentService commentService;
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping(value = "addComm", method=RequestMethod.GET)
	public String addComm() {

		System.out.println("/addCommView");
		
		return "forward:/comm/addCommView.jsp";
	}
	
	@RequestMapping(value = "addComm", method=RequestMethod.POST)
	public String addComm(@ModelAttribute Community community, HttpSession session) {
		System.out.println("--------"+community);
		User user = (User)session.getAttribute("loginUser");
		community.setUser(user);
		int postNo = commService.addComm(community);
		return "redirect:/comm/getComm?postNo="+postNo;
	}
	
	@RequestMapping("getComm")
	public String getComm(@RequestParam int postNo, 
							HttpServletRequest request,
							HttpSession session) {
		if(session.getAttribute("loginUser") == null) {
			return "/user/loginView.jsp";
		}
		Community community = commService.getComm(postNo);
		String[] temps = community.getHashtag().split(",");
		String temp ="";
		for(int i=1;i<temps.length;i++) {
			if(i == 1) {
				temp = "#"+temps[i];
			}else {
				temp += " #"+temps[i];
			}
		}
		System.out.println(community.getRegDate());
		community.setHashtag(temp);
		request.setAttribute("community", community);
		return "forward:/comm/getComm.jsp";
	}
	
	@RequestMapping("listComm")
	public String listComm(@ModelAttribute("search") Search search,
							HttpServletRequest request) {
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		
		System.out.println(search);
		System.out.println("pagesize : "+pageSize);
		System.out.println("pageUnit :: "+pageUnit);
		if(search.getPageSize() == 0) {
			search.setPageSize(pageSize);
		}else {
			pageSize = search.getPageSize();
		}
		
		Map<String , Object> map=commService.listComm(search);
		List<Community> list = (List<Community>)map.get("listComm");
		for(int i=0;i<list.size();i++) {
			String[] temps = list.get(i).getHashtag().split(",");
			String temp ="";
			for(int j=1;j<temps.length;j++) {
				if(i == 1) {
					temp = "#"+temps[j];
				}else {
					temp += " #"+temps[j];
				}
			}
			list.get(i).setHashtag(temp);
		}
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		List arrayList = (List) map.get("listComm");
		request.setAttribute("list", arrayList);
		request.setAttribute("resultSize", arrayList.size());
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		return "forward:/comm/listComm.jsp";
	}
	
	@RequestMapping("updateCommView")
	public String updateCommView(@RequestParam int postNo, HttpServletRequest request) {
		Community community = commService.getComm(postNo);
		String[] temp = community.getHashtag().split(",");
		List<String> hashtags = new ArrayList<>();
		for(int i=1;i<temp.length;i++) {
			hashtags.add(temp[i]);
		}
		request.setAttribute("community", community);
		request.setAttribute("hashtags", hashtags);
		request.setAttribute("menu", "update");
		return "forward:/comm/addCommView.jsp";
	}
	
	@RequestMapping("updateComm")
	public String updateComm(@ModelAttribute Community community) {
		commService.updateComm(community);
		return "forward:/comm/getComm?postNo="+community.getPostNo();
	}
	
	@RequestMapping("deleteComm")
	public String deleteComm(@RequestParam int postNo) throws Exception {
		commentService.deleteCommentByPost(postNo);
		commService.deleteComm(postNo);
		return "forward:/comm/listComm";
	}
}