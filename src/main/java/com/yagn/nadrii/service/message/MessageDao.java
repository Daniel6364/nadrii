package com.yagn.nadrii.service.message;

import java.util.List;

import com.yagn.nadrii.service.domain.Message;

public interface MessageDao {
	
	public void addMessage(Message message)throws Exception;
	
	public Message getMessage(int messageNo)throws Exception;
	
	public List listMessage(String receiverId)throws Exception;
	
	public void updateReadFlag(int messageNo) throws Exception;
	
	public List listMessageToRead(String receiverId) throws Exception;
	
	public List listSendMessage(String senderId)throws Exception;
	
	public void deleteMessage(int messageNo)throws Exception;

}
