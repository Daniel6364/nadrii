package com.yagn.nadrii.service.domain;

public class Trip {
	
	private int postNo;
	private String lat;
	private String lng;
	private String address;
	private String thumbnailImageFile;
	private int viewCount;
	private String description;
	private String tripName;
	private String createdTime;
	private String contentId;
	private String contentTypeId;
	
	
	
	public Trip() {
	}
	
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getThumbnailImageFile() {
		return thumbnailImageFile;
	}
	public void setThumbnailImageFile(String thumbnailImageFile) {
		this.thumbnailImageFile = thumbnailImageFile;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTripName() {
		return tripName;
	}
	public void setTripName(String tripName) {
		this.tripName = tripName;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(String contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	@Override
	public String toString() {
		return "Trip [postNo=" + postNo + ", lat=" + lat + ", lng=" + lng + ", address=" + address
				+ ", thumbnailImageFile=" + thumbnailImageFile + ", viewCount=" + viewCount + ", description="
				+ description + ", tripName=" + tripName + ", createdTime=" + createdTime + ", contentId=" + contentId
				+ ", contentTypeId=" + contentTypeId + "]";
	}
	
	
	
	
}
