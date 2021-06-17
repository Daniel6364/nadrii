package com.yagn.nadrii.service.domain.odsay.inside;

public class Path {
	
	private int pathType;
	private Object info;
	private Object subPath;

	public Path() {
	}
	
	public int getPathType() {
		return pathType;
	}
	public void setPathType(int pathType) {
		this.pathType = pathType;
	}
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
	public Object getSubPath() {
		return subPath;
	}
	public void setSubPath(Object subPath) {
		this.subPath = subPath;
	}
	
	@Override
	public String toString() {
		return "OdsayPath [pathType=" + pathType + ", info=" + info + ", subPath=" + subPath + "]";
	}

}
