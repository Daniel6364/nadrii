package com.yagn.nadrii.service.domain.odsay;

public class GraphPos {

	private double x;
	private double y;
	
	public GraphPos() {
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "GraphPos [x=" + x + ", y=" + y + "]";
	}

}