package com.yagn.nadrii.service.domain.odsay.inside;

public class Info {

	private double trafficDistance;
	private int totalWalk;
	private int totalTime;
	private int payment;
	private int busTransitCount;	
	private int subwayTransitCount;
	private String mapObj;
	private String firstStartStation;	
	private String lastEndStation;
	private int totalStationCount;
	private int busStationCount;
	private int subwayStationCount;
	private double totalDistance;
	private int totalWalkTime;

	public Info() {
	}
	
	public int getTotalStationCount() {
		return totalStationCount;
	}

	public void setTotalStationCount(int totalStationCount) {
		this.totalStationCount = totalStationCount;
	}

	public int getTotalWalkTime() {
		return totalWalkTime;
	}

	public void setTotalWalkTime(int totalWalkTime) {
		this.totalWalkTime = totalWalkTime;
	}

	public double getTrafficDistance() {
		return trafficDistance;
	}
	public void setTrafficDistance(double trafficDistance) {
		this.trafficDistance = trafficDistance;
	}
	public int getTotalWalk() {
		return totalWalk;
	}
	public void setTotalWalk(int totalWalk) {
		this.totalWalk = totalWalk;
	}
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public int getBusTransitCount() {
		return busTransitCount;
	}
	public void setBusTransitCount(int busTransitCount) {
		this.busTransitCount = busTransitCount;
	}
	public int getSubwayTransitCount() {
		return subwayTransitCount;
	}
	public void setSubwayTransitCount(int subwayTransitCount) {
		this.subwayTransitCount = subwayTransitCount;
	}
	public String getMapObj() {
		return mapObj;
	}
	public void setMapObj(String mapObj) {
		this.mapObj = mapObj;
	}
	public String getFirstStartStation() {
		return firstStartStation;
	}
	public void setFirstStartStation(String firstStartStation) {
		this.firstStartStation = firstStartStation;
	}
	public String getLastEndStation() {
		return lastEndStation;
	}
	public void setLastEndStation(String lastEndStation) {
		this.lastEndStation = lastEndStation;
	}
	public int getBusStationCount() {
		return busStationCount;
	}
	public void setBusStationCount(int busStationCount) {
		this.busStationCount = busStationCount;
	}
	public int getSubwayStationCount() {
		return subwayStationCount;
	}
	public void setSubwayStationCount(int subwayStationCount) {
		this.subwayStationCount = subwayStationCount;
	}
	public double getTotalDistance() {
		return totalDistance;
	}
	public void setTotalDistance(double totalDistance) {
		this.totalDistance = totalDistance;
	}
	@Override
	public String toString() {
		return "OdsayInfo [trafficDistance=" + trafficDistance + ", totalWalk=" + totalWalk + ", totalTime=" + totalTime
				+ ", payment=" + payment + ", busTransitCount=" + busTransitCount + ", subwayTransitCount="
				+ subwayTransitCount + ", mapObj=" + mapObj + ", firstStartStation=" + firstStartStation
				+ ", lastEndStation=" + lastEndStation + ", totalStationCount=" + totalStationCount
				+ ", busStationCount=" + busStationCount + ", subwayStationCount=" + subwayStationCount
				+ ", totalDistance=" + totalDistance + ", totalWalkTime=" + totalWalkTime + "]";
	}
}
