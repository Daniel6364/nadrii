package com.yagn.nadrii.common;


public class OpenApiSearch {
	
	///Field
	private int pageNo;

	private String searchCondition;
	private String searchKeyword;
	private int numOfRows;

	private int endRowNum;
	private int startRowNum;
    
	///Constructor
	public OpenApiSearch() {
	}
	
	///Method
	public int getNumOfRows() {
		return numOfRows;
	}
	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
	//==> Select Query �� ROWNUM ������ �� 
	public int getEndRowNum() {
		return getPageNo()*getNumOfRows();
	}
	//==> Select Query �� ROWNUM ���� ��   
	public int getStartRowNum() {
		return (getPageNo()-1)*getNumOfRows()+1;
	}


	@Override
	public String toString() {
		return "OpenApiSearch [pageNo=" + pageNo + ", searchCondition=" + searchCondition + ", searchKeyword="
				+ searchKeyword + ", numOfRows=" + numOfRows + ", endRowNum=" + endRowNum + ", startRowNum="
				+ startRowNum + "]";
	}

	
	
	

}