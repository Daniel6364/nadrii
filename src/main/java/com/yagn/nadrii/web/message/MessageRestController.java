package com.yagn.nadrii.web.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yagn.nadrii.service.domain.Message;
import com.yagn.nadrii.service.domain.User;
import com.yagn.nadrii.service.message.MessageService;

@RestController
@RequestMapping("/message/")
public class MessageRestController {

	@Autowired
	@Qualifier("messageServiceImpl")
	private MessageService messageService;
	
	public Map addMessage() {
		Map map = new HashMap();
		
		
		
		return map; 
	}
	
	@RequestMapping("json/deleteMessage/{messageNo}")
	public Map deleteMessage(@PathVariable("messageNo") String messageNoList) throws Exception{
		System.out.println(this.getClass()+"  json/deleteMessage");
		int count =0;
		String[] messageNos = ( (messageNoList.trim() ).split(",")); 
		
		for (int i = 0; i < messageNos.length; i++) {
			int messageNo = Integer.parseInt(messageNos[i]);
			System.out.println(messageNo);
			messageService.deleteMessage(messageNo);
			++count;
		}
		
		Map map = new HashMap();
		map.put("count", count);
		
		
		return map;
		
	}
	
	@RequestMapping("json/getMessage/{messageNo}")
	public Map getMessage(@PathVariable("messageNo") int messageNo)throws Exception {
		System.out.println(this.getClass()+"/message/getMessage/");
		Map map = new HashMap();
		Message message = messageService.getMessage(messageNo);
		this.updateReadFlag(messageNo);
		
		map.put("message", message);
		return map;
	}
	
		
	@RequestMapping("json/listMessage/{userId}")
	public Map listMessage(@PathVariable("userId") String userId) throws Exception{
		System.out.println(this.getClass()+".listMessage()");
		Map map = new HashMap();
		
		System.out.println(userId);
		
		List<Message> list = messageService.listMessage(userId);
		
		System.out.println(list.size());
		map.put("list", list);
		
		return map;
	}
	

	private void updateReadFlag(int messageNo) throws Exception{
		System.out.println(this.getClass()+"  updateReadFlag");
		
		messageService.updateReadFlag(messageNo);		
	}
	
	@RequestMapping("/json/listMessageToRead/{userId}")
	public Map listMessageToRead(@PathVariable("userId") String userId) throws Exception{
		System.out.println(this.getClass()+"  listMessageToRead");
		Map map = new HashMap();
		
			
		List list = messageService.listMessageToRead(userId);
		
		
		map.put("list",list);
		
		return map;
	}
	
	@RequestMapping("/json/listSendMessage/{userId}")
	public Map listSendMessage(@PathVariable("userId") String userId) throws Exception{
		System.out.println(this.getClass()+"  listSendMessage");
		Map map = new HashMap();		
				
		List list = messageService.listSendMessage(userId);
				
		map.put("list",list);
	
		return map;
	}
}
