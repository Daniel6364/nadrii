package com.yagn.nadrii.service.domain;

import java.util.Calendar;

public class Community {
	private int postNo;
	private User user;
	private String title;
	private String thumbNailFileName;
	private String text;
	private String hashtag;
	private int viewCount;
	private String lat;
	private String lng;
	private String regDate;
	
	public Community() {
		this.regDate = Community.parser(Calendar.getInstance().getTime().toString());
		System.out.println("++++++"+this.regDate);
	}
	
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumbNailFileName() {
		return thumbNailFileName;
	}
	public void setThumbNailFileName(String thumbNailFileName) {
		this.thumbNailFileName = thumbNailFileName;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
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
	
	@Override
	public String toString() {
		return "Community [postNo=" + postNo + ", title=" + title + ", thumbNailFileName="
				+ thumbNailFileName + ", text=" + text + ", hashtag=" + hashtag + ", viewCount=" + viewCount + ", lat="
				+ lat + ", lng=" + lng + ", regDate=" + regDate + "]";
	}
	
	private static String parser(String date) {
		String[] temp = date.split(" ");
		switch(temp[1]) {
			case "Jan" :
				temp[1]="01";
				break;
			case "Feb" :
				temp[1]="02";
				break;
			case "Mar" :
				temp[1]="03";
				break;
			case "Apr" :
				temp[1]="04";
				break;
			case "May" :
				temp[1]="05";
				break;
			case "Jun" :
				temp[1]="06";
				break;
			case "Jul" :
				temp[1]="07";
				break;
			case "Aug" :
				temp[1]="08";
				break;
			case "Sep" :
				temp[1]="09";
				break;
			case "Oct" :
				temp[1]="10";
				break;
			case "Nov" :
				temp[1]="11";
				break;
			case "Dec" :
				temp[1]="12";
				break;
		}
		
		date = temp[5]+"-"+temp[1]+"-"+temp[2]+" "+temp[3];
		return date;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
