package com.yagn.nadrii.service.domain;

import java.sql.Date;

public class Group {
	
	private Join join;
	private String title;
	private String text;
	private String meetingDate;
	private int viewCount;
	private String placeName;
	private String placeDetail;
	private String lat;
	private String lng;
	private Date regDate;
		
	public Group(){
	}
		
	public int getPostNo() {
		return join.getGroupNo();
	}

	public Join getJoin() {
		return join;
	}

	public void setJoin(Join join) {
		this.join = join;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceDetail() {
		return placeDetail;	
	}
	
	public void setPlaceDetail(String placeDetail) {
		this.placeDetail = placeDetail;
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

	@Override
	public String toString() {
		return "Group [join=" + join + ", title=" + title + ", text=" + text + ", meetingDate=" + meetingDate
				+ ", viewCount=" + viewCount + ", placeName=" + placeName + ", placeDetail=" + placeDetail + ", lat="
				+ lat + ", lng=" + lng + ", regDate=" + regDate + "]";
	}

	
}