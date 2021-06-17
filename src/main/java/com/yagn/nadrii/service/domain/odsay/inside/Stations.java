package com.yagn.nadrii.service.domain.odsay.inside;

public class Stations {

	private int index;
	private int stationID;
	private String stationName;
	private String x;
	private String y;

	public Stations() {
	}

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getStationID() {
		return stationID;
	}
	public void setStationID(int stationID) {
		this.stationID = stationID;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "OdsayStation [index=" + index + ", stationID=" + stationID + ", stationName=" + stationName + ", x=" + x
				+ ", y=" + y + "]";
	}
	
	
}
