package com.yagn.nadrii.service.domain;

public class Message {

	private int messageNo;
	private String senderId;
	private String receiverId;
	private String text;
	private String regDate;
	private String readFlag;
	
	
	public Message() {
	
	}


	public int getMessageNo() {
		return messageNo;
	}


	public void setMessageNo(int messageNo) {
		this.messageNo = messageNo;
	}


	public String getSenderId() {
		return senderId;
	}


	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}


	public String getReceiverId() {
		return receiverId;
	}


	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getRegDate() {
		return regDate;
	}


	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}


	public String getReadFlag() {
		return readFlag;
	}


	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}


	@Override
	public String toString() {
		return "Message [messageNo=" + messageNo + ", senderId=" + senderId + ", receiverId=" + receiverId + ", text="
				+ text + ", regDate=" + regDate + ", readFlag=" + readFlag + "]";
	}
	
	
	
	
}
