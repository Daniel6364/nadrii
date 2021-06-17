package com.yagn.nadrii.service.domain.odsay.inside;

public class SubPath {
	
	private int trafficType;
	private double distance;
	private int sectionTime;
	private int stationCount;
	private Object lane;
	private String startName;
	private double startX;
	private double startY;
	private String endName;
	private double endX;
	private double endY;
	private String way;
	private int wayCode;
	private String door;
	private int startID;
	private int endID;
	private String startExitNo;
	private double startExitX;
	private double startExitY;	
	private String endExitNo;
	private double endExitX;
	private double endExitY;
	private Object passStopList;

	public SubPath() {
	}
	
	public int getTrafficType() {
		return trafficType;
	}
	public void setTrafficType(int trafficType) {
		this.trafficType = trafficType;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public int getSectionTime() {
		return sectionTime;
	}
	public void setSectionTime(int sectionTime) {
		this.sectionTime = sectionTime;
	}
	public int getStationCount() {
		return stationCount;
	}
	public void setStationCount(int stationCount) {
		this.stationCount = stationCount;
	}
	public String getStartName() {
		return startName;
	}
	public Object getLane() {
		return lane;
	}
	public void setLane(Object lane) {
		this.lane = lane;
	}
	public void setStartName(String startName) {
		this.startName = startName;
	}
	public double getStartX() {
		return startX;
	}
	public void setStartX(double startX) {
		this.startX = startX;
	}
	public double getStartY() {
		return startY;
	}
	public void setStartY(double startY) {
		this.startY = startY;
	}
	public String getEndName() {
		return endName;
	}
	public void setEndName(String endName) {
		this.endName = endName;
	}
	public double getEndX() {
		return endX;
	}
	public void setEndX(double endX) {
		this.endX = endX;
	}
	public double getEndY() {
		return endY;
	}
	public void setEndY(double endY) {
		this.endY = endY;
	}
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
	public int getWayCode() {
		return wayCode;
	}
	public void setWayCode(int wayCode) {
		this.wayCode = wayCode;
	}
	public String getDoor() {
		return door;
	}
	public void setDoor(String door) {
		this.door = door;
	}
	public int getStartID() {
		return startID;
	}
	public void setStartID(int startID) {
		this.startID = startID;
	}
	public int getEndID() {
		return endID;
	}
	public void setEndID(int endID) {
		this.endID = endID;
	}
	public String getStartExitNo() {
		return startExitNo;
	}
	public void setStartExitNo(String startExitNo) {
		this.startExitNo = startExitNo;
	}
	public double getStartExitX() {
		return startExitX;
	}
	public void setStartExitX(double startExitX) {
		this.startExitX = startExitX;
	}
	public double getStartExitY() {
		return startExitY;
	}
	public void setStartExitY(double startExitY) {
		this.startExitY = startExitY;
	}
	public String getEndExitNo() {
		return endExitNo;
	}
	public void setEndExitNo(String endExitNo) {
		this.endExitNo = endExitNo;
	}
	public double getEndExitX() {
		return endExitX;
	}
	public void setEndExitX(double endExitX) {
		this.endExitX = endExitX;
	}
	public double getEndExitY() {
		return endExitY;
	}
	public void setEndExitY(double endExitY) {
		this.endExitY = endExitY;
	}
	public Object getPassStopList() {
		return passStopList;
	}
	public void setPassStopList(Object passStopList) {
		this.passStopList = passStopList;
	}
	
	@Override
	public String toString() {
		return "OdsaySubPath [trafficType=" + trafficType + ", distance=" + distance + ", sectionTime=" + sectionTime
				+ ", stationCount=" + stationCount + ", lane=" + lane + ", startName=" + startName + ", startX="
				+ startX + ", startY=" + startY + ", endName=" + endName + ", endX=" + endX + ", endY=" + endY
				+ ", way=" + way + ", wayCode=" + wayCode + ", door=" + door + ", startID=" + startID + ", endID="
				+ endID + ", startExitNo=" + startExitNo + ", startExitX=" + startExitX + ", startExitY=" + startExitY
				+ ", endExitNo=" + endExitNo + ", endExitX=" + endExitX + ", endExitY=" + endExitY + ", passStopList="
				+ passStopList + "]";
	}

}
