package com.yagn.nadrii.service.domain.odsay.inside;

public class Lane {

	private String name;
	private String busNo;
	private int type;
	private int busID;
	private int subwayCode;
	private int subwayCityCode;
	
	public Lane() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusNo() {
		return busNo;
	}

	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getBusID() {
		return busID;
	}

	public void setBusID(int busID) {
		this.busID = busID;
	}

	public int getSubwayCode() {
		return subwayCode;
	}

	public void setSubwayCode(int subwayCode) {
		this.subwayCode = subwayCode;
	}

	public int getSubwayCityCode() {
		return subwayCityCode;
	}

	public void setSubwayCityCode(int subwayCityCode) {
		this.subwayCityCode = subwayCityCode;
	}

	@Override
	public String toString() {
		return "OdsayLane [name=" + name + ", busNo=" + busNo + ", type=" + type + ", busID=" + busID + ", subwayCode="
				+ subwayCode + ", subwayCityCode=" + subwayCityCode + "]";
	}
	
	
	
}
