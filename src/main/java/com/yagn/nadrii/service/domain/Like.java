package com.yagn.nadrii.service.domain;

import java.sql.Date;

public class Like {

	private int likeNo;
	private int postNo;
	private String userId;
	private Date regDate;
	
	public Like(){
	}

	public int getLikeNo() {
		return likeNo;
	}

	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Like [likeNo=" + likeNo + ", postNo=" + postNo + ", userId=" + userId + ", regDate=" + regDate + "]";
	}

	
}