package com.yagn.nadrii.common;


public class OpenApiPage {
	
	///Field                                                 
	private int pageNo;			                 
	private int numOfRows;			  
	private int totalCount;		                   
	private Object items;			
	
	private int pageUnit;			  
	private int maxPage;			     
	private int beginUnitPage;		   
	private int endUnitPage;	     
	
	
	///Constructor
	public OpenApiPage() {
	}
	
	public OpenApiPage(int pageNo, int totalCount, int pageUnit, int numOfRows) {
		this.totalCount = totalCount;
		this.pageUnit = pageUnit;
		this.numOfRows = numOfRows;

		this.maxPage = (numOfRows == 0) ? totalCount : (totalCount - 1) / numOfRows + 1;
		this.pageNo = (pageNo > maxPage) ? maxPage : pageNo;

		this.beginUnitPage = ((pageNo - 1) / pageUnit) * pageUnit + 1;

		if (maxPage <= pageUnit) {
			this.endUnitPage = maxPage;
		} else {
			this.endUnitPage = beginUnitPage + (pageUnit - 1);
			if (maxPage <= endUnitPage) {
				this.endUnitPage = maxPage;
			}
		}
	}
	
	///Mehtod
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getBeginUnitPage() {
		return beginUnitPage;
	}
	public void setBeginUnitPage(int beginUnitPage) {
		this.beginUnitPage = beginUnitPage;
	}
	public int getEndUnitPage() {
		return endUnitPage;
	}
	public void setEndUnitPage(int endUnitPage) {
		this.endUnitPage = endUnitPage;
	}
	public Object getItems() {
		return items;
	}
	public void setItems(Object items) {
		this.items = items;
	}
	public int getNumOfRows() {
		return numOfRows;
	}
	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}

	@Override
	public String toString() {
		return "OpenApiPage [pageNo=" + pageNo + ", totalCount=" + totalCount + ", pageUnit=" + pageUnit
				+ ", numOfRows=" + numOfRows + ", maxPage=" + maxPage + ", beginUnitPage=" + beginUnitPage
				+ ", endUnitPage=" + endUnitPage 
//				+ ", items=" + items 
				+ "]";
	}
	
	
	
	
	
}