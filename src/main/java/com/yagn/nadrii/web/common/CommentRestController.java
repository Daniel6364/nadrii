package com.yagn.nadrii.web.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.common.CommentService;
import com.yagn.nadrii.service.domain.Comments;
import com.yagn.nadrii.service.domain.User;

@RestController
@RequestMapping("/common/*")
public class CommentRestController {
	
	@Autowired
	@Qualifier("commentServiceImpl")
	private CommentService commentService;
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping("addComment")
	public Map<String, Object> addComment(@ModelAttribute Comments comments, HttpSession session) throws Exception {
		User user = (User)session.getAttribute("loginUser");
		comments.setUser(user);
		commentService.addComment(comments);
		return commentService.listCommentByPost(comments.getPostNo());
	}
	
	@RequestMapping("listCommentByPost")
	public Map<String, Object> listCommentByPost(@RequestParam int postNo)throws Exception {
		return commentService.listCommentByPost(postNo);
	}
	
	@RequestMapping("listCommentById")
	public Map<String, Object> listCommentById(@ModelAttribute Search search)throws Exception {
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		if(search.getPageSize() == 0) {
			search.setPageSize(pageSize);
		}else {
			pageSize = search.getPageSize();
		}
		Map<String, Object> map = commentService.listCommentById(search);
		List<Comments> comments = (List)map.get("comments");
		List<String> yearNMonth = new ArrayList<>();
		List<String> day = new ArrayList<>();
		List<String> titles = new ArrayList<>();
		for(int i=0;i<comments.size();i++) {
			String[] temp01 = comments.get(i).getRegDate().split(" ");
			String[] temp = temp01[0].split("-");
			yearNMonth.add(temp[0]+"."+temp[1]);
			day.add(temp[2]);
			titles.add(commentService.listTitle(comments.get(i).getPostNo()));
		}
		System.out.println("$볼 것$ : "+comments+yearNMonth+day);
		map.put("title", titles);
		map.put("yearNMonth", yearNMonth);
		map.put("day", day);
		return map;
	}
	
	@RequestMapping("updateComment")
	public void updateComment(@RequestParam int commentNo,
								@RequestParam String text)throws Exception {
		Comments comments = commentService.getComment(commentNo);
		comments.setText(text);
		commentService.updateComment(comments);
	}
	
	@RequestMapping("deleteComment")
	public Map<String, Object> deleteComment(@RequestParam int commentNo,
												@RequestParam int postNo) throws Exception {
		commentService.deleteComment(commentNo);
		return commentService.listCommentByPost(postNo);
	}
}
