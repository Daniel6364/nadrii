package com.yagn.nadrii.service.message;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yagn.nadrii.common.Search;
import com.yagn.nadrii.service.domain.Message;
import com.yagn.nadrii.service.domain.Trip;
import com.yagn.nadrii.service.trip.impl.TripDaoImplgetAddress;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })


public class Messagetest {
	
	private Message message;
	
	@Autowired
	@Qualifier("messageServiceImpl")
	private MessageService messageService;
	
	
	@Test
	public void addMessageTest() throws Exception {
		message = new Message();
		message.setReceiverId("test01");
		message.setSenderId("questsolve");
		message.setText("ssdsdsdssstest¿‘¥œ¥Ÿ");
		System.out.println(message);
		messageService.addMessage(message);

		Thread.sleep(1000);
		
		//System.out.println(messageService.get);
		
	}
	//@Test
	public void getMessageTest() throws Exception{
		message = messageService.getMessage(10020);
		System.out.println(message);
	}
	
	//@Test
	public void listMessageTest() throws Exception{
		List list = messageService.listMessage("test01");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
			
	}
	
	//@Test
	public void updateReadFlag() throws Exception{
		message = messageService.getMessage(10021);
		messageService.updateReadFlag(message.getMessageNo());
		Thread.sleep(1000);
		System.out.println(message);
	}
	
	//@Test
	public void listMessageToRead() throws Exception{
		List list = messageService.listMessageToRead("test01");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	//@Test
	public void listSendMessageTest() throws Exception{
		List list = messageService.listSendMessage("questsolve");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	//@Test
	public void deleteMessageTest() throws Exception{
		messageService.deleteMessage(10021);
		System.out.println(messageService.getMessage(10021));
				
	}
	
	
}
