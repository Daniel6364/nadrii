package com.yagn.nadrii.web.message;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yagn.nadrii.service.domain.Message;
import com.yagn.nadrii.service.domain.User;
import com.yagn.nadrii.service.message.MessageService;



@Controller
@RequestMapping("/message/*")
public class MessageController {
	
	@Autowired
	@Qualifier("messageServiceImpl")
	private MessageService messageService;

	public MessageController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="addMessage", method=RequestMethod.GET)
	public String addMessage(HttpSession session, HttpServletRequest request,@RequestParam("receiverId") String receiverId,Map map)throws Exception {
		
		
		System.out.println(this.getClass()+"/message/addMessageView/");
		System.out.println(receiverId);
		
		User sender = (User)session.getAttribute("loginUser");
		Message message = new Message();
		message.setSenderId(sender.getUserId());
		message.setReceiverId(receiverId);
		map.put("message", message);
		
		
		return "forward:/message/addMessageView.jsp";
	}

	@RequestMapping(value="addMessage", method=RequestMethod.POST)
	public String addMessage(@ModelAttribute Message message,Map map)throws Exception {
		System.out.println(this.getClass()+"/message/addMessage/");
		
		System.out.println(message);
		
		
		messageService.addMessage(message);
		
		
		map.put("message", message);
		
		return "forward:/message/addMessage.jsp";
	}
	
	
	@RequestMapping("getMessage")
	public String getMessage(@RequestParam("messageNo") int messageNo,Map map)throws Exception {
		System.out.println(this.getClass()+"/message/getMessage/");
		Message message = messageService.getMessage(messageNo);
		this.updateReadFlag(messageNo);
		
		map.put("message", message);
		return "forward:/message/getMessage.jsp";
	}
	
	

	@RequestMapping(value="listMessage",method=RequestMethod.GET)
	public String listMessage(HttpSession session, HttpServletRequest request, Map map) throws Exception{

		System.out.println(this.getClass()+"  listMessage");
		
		session= request.getSession(true);
		
		User user = (User)session.getAttribute("loginUser");
		
		System.out.println(user);
		
		List<Message> list = messageService.listMessage(user.getUserId());
		System.out.println(list.size());
		/*
		for (int i = 0; i < list.size(); i++) {
			
			if((list.get(i).getText()).length() >10) {
				String text = list.get(i).getText().substring(0, 10);
				text +="...";
				list.get(i).setText(text);			
			}
		}
		*/
		//System.out.println(list.get(0));
		map.put("list", list);
		
		return "forward:/message/listMessage.jsp";
	}
	

	private void updateReadFlag(int messageNo) throws Exception{
		System.out.println(this.getClass()+"  updateReadFlag");
		
		messageService.updateReadFlag(messageNo);		
	}
	
	@RequestMapping("listMessageToRead")
	public String listMessageToRead(HttpSession session, HttpServletRequest request,Map map) throws Exception{
		System.out.println(this.getClass()+"  listMessageToRead");
		
		session= request.getSession(true);
		User user = (User)session.getAttribute("loginUser");
		
		System.out.println(user);
		//System.out.println(listType);
		
		List list = messageService.listMessageToRead(user.getUserId());
		
		//map.put("listType", listType);
		map.put("list",list);
		
		return "forward:/message/listMessage.jsp";
	}
	
	@RequestMapping("listSendMessage")
	public String listSendMessage(HttpSession session, HttpServletRequest request,Map map) throws Exception{
		System.out.println(this.getClass()+"  listSendMessage");
		
		session= request.getSession(true);
		User user = (User)session.getAttribute("loginUser");
		
		System.out.println(user);
		//System.out.println(listType);
		
		List list = messageService.listSendMessage(user.getUserId());
		
		
		//map.put("listType", listType);
		map.put("list",list);
	
		return "forward:/message/listMessage.jsp";
	}
	
	
	
}
