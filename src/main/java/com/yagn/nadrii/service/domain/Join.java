package com.yagn.nadrii.service.domain;

public class Join {

	private int joinNo;
	private int groupNo;
	private User user;
	private int groupRole;	// 1 - �۾���, 2 - ������
	private String regDate;
	
	public Join(){
	}

	public int getJoinNo() {
		return joinNo;
	}

	public void setJoinNo(int joinNo) {
		this.joinNo = joinNo;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getGroupRole() {
		return groupRole;
	}

	public void setGroupRole(int groupRole) {
		this.groupRole = groupRole;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Join [joinNo=" + joinNo + ", groupNo=" + groupNo + ", user=" + user + ", groupRole=" + groupRole
				+ "]";
	}

	
}