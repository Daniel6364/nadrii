package com.yagn.nadrii.service.message.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.yagn.nadrii.service.domain.Message;
import com.yagn.nadrii.service.message.MessageDao;

@Repository("messageDaoImpl")
public class MessageDaoImpl implements MessageDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
			
	

	public MessageDaoImpl() {
		System.out.println(this.getClass());
	}
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	
	@Override
	public void addMessage(Message message) throws Exception {
		System.out.println(this.getClass() + "  addMessage");
				sqlSession.insert("MessageMapper.addMessage",message);
	}

	
	@Override
	public Message getMessage(int messageNo) throws Exception {
		System.out.println(this.getClass()+"  getMessage");
		Message message = sqlSession.selectOne("MessageMapper.getMessage", messageNo);
		
		return message;
	}

	
	@Override
	public List listMessage(String receiverId) throws Exception {
		System.out.println(this.getClass() + "  listMessage");
		List list = sqlSession.selectList("MessageMapper.listMessage", receiverId);
		return list;
	}

	
	@Override
	public void updateReadFlag(int messageNo) throws Exception {
		System.out.println(this.getClass()+ "  updateReadFlag");
		sqlSession.update("MessageMapper.updateReadFlag", messageNo);
		
	}

	
	@Override
	public List listMessageToRead(String receiverId) throws Exception {
		System.out.println(this.getClass()+"  listMessageToRead");
		List list = sqlSession.selectList("MessageMapper.listMessageToRead",receiverId);
		return list;
	}

	
	@Override
	public List listSendMessage(String senderId) throws Exception {
		System.out.println(this.getClass()+ "  listSendMessage");
		List list = sqlSession.selectList("MessageMapper.listSendMessage", senderId);
		return list;
	}

	
	@Override
	public void deleteMessage(int messageNo) throws Exception {
		System.out.println(this.getClass()+"  deleteMessage");
		sqlSession.delete("MessageMapper.deleteMessage", messageNo);		
	}

}
