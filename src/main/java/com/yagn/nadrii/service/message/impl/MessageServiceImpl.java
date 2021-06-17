package com.yagn.nadrii.service.message.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yagn.nadrii.service.domain.Message;
import com.yagn.nadrii.service.message.MessageDao;
import com.yagn.nadrii.service.message.MessageService;


@Service("messageServiceImpl")
public class MessageServiceImpl implements MessageService {
	
	
	@Autowired
	@Qualifier("messageDaoImpl")
	private MessageDao messageDao;

	
	
	public MessageServiceImpl() {
		System.out.println(this.getClass());
	}
	

	@Override
	public void addMessage(Message message) throws Exception {
		System.out.println(this.getClass()+ "  addMessage");
		messageDao.addMessage(message);
			
	}

	@Override
	public Message getMessage(int messageNo) throws Exception {
		System.out.println(this.getClass()+"  getMessage");
		
		Message message = messageDao.getMessage(messageNo);
		return message;
	}

	@Override
	public List listMessage(String receiverId) throws Exception {
		System.out.println(this.getClass()+ "  listMessage");
		List list = messageDao.listMessage(receiverId);
		return list;
	}

	@Override
	public void updateReadFlag(int messageNo) throws Exception {
		System.out.println(this.getClass()+ "  updateReadFlag");
		messageDao.updateReadFlag(messageNo);
		
	}

	@Override
	public List listMessageToRead(String receiverId) throws Exception {
		System.out.println(this.getClass() + "  listMessageToRead");
		List list = messageDao.listMessageToRead(receiverId);
		return list;
	}

	@Override
	public List listSendMessage(String senderId) throws Exception {
		
		System.out.println(this.getClass()+ "  listSendMessage");
		List list = messageDao.listSendMessage(senderId);
		return list;
	}

	@Override
	public void deleteMessage(int messageNo) throws Exception {
		System.out.println(this.getClass()+"  deleteMessage");
		messageDao.deleteMessage(messageNo);
		
	}

}
