package com.yagn.nadrii.service.domain;

import java.sql.Date;

public class Planner {
	
	private int postNo;
	private User user;
	private String title;
	private String lat;
	private String lng;
	private Date regDate;
	private int viewCount;
	private String flag;
	private String photo;
	private String text;
	
	
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	

	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return "Planner [postNo=" + postNo + ", user=" + user + ", title=" + title + ", lat=" + lat
				+ ", lng=" + lng + ", regDate=" + regDate + ", viewCount=" + viewCount + ", flag=" + flag + ", photo="
				+ photo + ", text=" + text + "]";
	}
	
}

