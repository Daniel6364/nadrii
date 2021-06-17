package com.yagn.nadrii.service.domain.odsay.outside;

public class OBJ {

	private String startSTN;
	private int startID;
	private double SX;
	private double SY;
	private String endSTN;
	private int endID;
	private double EX;
	private double EY;
	private int time;
	private int payment;
	private String trainType;
	private String trainCode;
	private String mapOBJ;

	public OBJ() {
	}

	public String getStartSTN() {
		return startSTN;
	}

	public void setStartSTN(String startSTN) {
		this.startSTN = startSTN;
	}

	public int getStartID() {
		return startID;
	}

	public void setStartID(int startID) {
		this.startID = startID;
	}

	public double getSX() {
		return SX;
	}

	public void setSX(double sX) {
		SX = sX;
	}

	public double getSY() {
		return SY;
	}

	public void setSY(double sY) {
		SY = sY;
	}

	public String getEndSTN() {
		return endSTN;
	}

	public void setEndSTN(String endSTN) {
		this.endSTN = endSTN;
	}

	public int getEndID() {
		return endID;
	}

	public void setEndID(int endID) {
		this.endID = endID;
	}

	public double getEX() {
		return EX;
	}

	public void setEX(double EX) {
		this.EX = EX;
	}

	public double getEY() {
		return EY;
	}

	public void setEY(double EY) {
		this.EY = EY;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public String getTrainCode() {
		return trainCode;
	}

	public void setTrainCode(String trainCode) {
		this.trainCode = trainCode;
	}

	public String getMapOBJ() {
		return mapOBJ;
	}

	public void setMapOBJ(String mapOBJ) {
		this.mapOBJ = mapOBJ;
	}

	@Override
	public String toString() {
		return "OBJ [startSTN=" + startSTN + ", startID=" + startID + ", SX=" + SX + ", SY=" + SY + ", endSTN=" + endSTN
				+ ", endID=" + endID + ", EX=" + EX + ", EY=" + EY + ", time=" + time + ", payment=" + payment
				+ ", trainType=" + trainType + ", trainCode=" + trainCode + ", mapOBJ=" + mapOBJ + "]";
	}

	
		
	
}
