package com.yagn.nadrii.service.domain;

public class Location {
	String lat;
	String lng;
	
	public Location() {
		// TODO Auto-generated constructor stub
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
		return "Location [lat=" + lat + ", lng=" + lng + "]";
	}
	
	

}
