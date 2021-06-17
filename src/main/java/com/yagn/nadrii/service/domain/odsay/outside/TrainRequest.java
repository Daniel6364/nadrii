package com.yagn.nadrii.service.domain.odsay.outside;

public class TrainRequest {

	private int count;
	private Object OBJ;
	
	public TrainRequest() {
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Object getOBJ() {
		return OBJ;
	}
	public void setOBJ(Object oBJ) {
		OBJ = oBJ;
	}

	@Override
	public String toString() {
		return "TrainRequest [count=" + count + ", OBJ=" + OBJ + "]";
	}	
}
