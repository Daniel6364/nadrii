package com.yagn.nadrii.service.domain;

public class Wish {
	
	private int wishNo;
	private String userId;
	private Trip trip;
	private int postNo;
	private String imageFile;
		
	
	private Ticket ticket;
	
	
	public Wish() {
		
	}
	
	public int getWishNo() {
		return wishNo;
	}
	public void setWishNo(int wishNo) {
		this.wishNo = wishNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Trip getTripNo() {
		return trip;
	}
	public void setTripNo(Trip trip) {
		this.trip = trip;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	
	public Ticket getTicketNo() {
		return ticket;
	}

	public void setTicketNo(Ticket ticket) {
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		return "Wish [wishNo=" + wishNo + ", userId=" + userId + ", trip=" + trip + ", postNo=" + postNo
				+ ", imageFile=" + imageFile + ", ticket=" + ticket + "]";
	}

	

}
