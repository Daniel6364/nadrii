package com.yagn.nadrii.service.trip.urlmanage;

public class TourAPlListUrlManage {

	private String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/";
	private String type= "areaBasedList?";
	
	private String serviceKeyName = "ServiceKey=";
	private String contentTypeIdName = "&contentTypeId=";
	private String areaCodeName = "&areaCode=";
	private String sigunguCodeName = "&sigunguCode=";
	private String cat1Name = "&cat1=";
	private String cat2Name ="&cat2=";
	private String cat3Name ="&cat3=";
	private String listYNName = "&listYN=";
	private String MobileOSName = "&MobileOS=";
	private String MobileAppName = "&MobileApp=";
	private String arrangeName = "&arrange=";
	private String numOfRowsName = "&numOfRows=";
	private String pageNoName ="&pageNo=";
	private String contentIdName ="&contentId=";
	private String kewordName ="&keyword=";
	
	//private String serviceKey = "ay3zIymuP5LX%2BGZhKC44TDdl68jrGAk5sMJ2Ry5GkBV0TvUP14kU13EG1mkNneM4GQOTPDsVuj2%2BCKLpcwcvfg%3D%3D";
	private String serviceKey = "elArz5Uco6Vjf5eFlDFY225L72%2FKxulUVoQ6ucNpvUkwvvoOZw4Pri%2B3tFJHQXU4CxNyVGp%2BNRzup9HNp4XsMg%3D%3D";
	private String contentTypeId = "12"; //""
	private String contentId ="";
	private String areaCode = "";
	private String sigunguCode = "";
	private String cat1="A02";
	private String cat2="A0201";
	private String cat3 ="";
	private String listYN="Y";
	private String MoblieOS = "ETC";
	private String MobileApp = "TourAPI3.0_Guide";
	private String arrange = "B";
	private int numOfRows=12;
	private int pageNo =1;
	private String keyword ="";
	


	public TourAPlListUrlManage() {
		
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getServiceKey() {
		return serviceKey;
	}

	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}

	public String getContentTypeId() {
		return contentTypeId;
	}

	public void setContentTypeId(String contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getSigunguCode() {
		return sigunguCode;
	}

	public void setSigunguCode(String sigunguCode) {
		this.sigunguCode = sigunguCode;
	}

	public String getCat1() {
		return cat1;
	}

	public void setCat1(String cat1) {
		this.cat1 = cat1;
	}

	public String getCat2() {
		return cat2;
	}

	public void setCat2(String cat2) {
		this.cat2 = cat2;
	}

	public String getCat3() {
		return cat3;
	}

	public void setCat3(String cat3) {
		this.cat3 = cat3;
	}

	public String getListYN() {
		return listYN;
	}

	public void setListYN(String listYN) {
		this.listYN = listYN;
	}

	public String getMoblieOS() {
		return MoblieOS;
	}

	public void setMoblieOS(String moblieOS) {
		MoblieOS = moblieOS;
	}

	public String getMobileApp() {
		return MobileApp;
	}

	public void setMobileApp(String mobileApp) {
		MobileApp = mobileApp;
	}

	public String getArrange() {
		return arrange;
	}

	public void setArrange(String arrange) {
		this.arrange = arrange;
	}

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
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String urlMaking() {
		String urlcomplete = url+type+serviceKeyName+serviceKey+contentTypeIdName+contentTypeId+
									contentIdName+contentId+kewordName+keyword+
									areaCodeName+areaCode+sigunguCodeName+sigunguCode+
									cat1Name+cat1+cat2Name+cat2+cat3Name+cat3+listYNName+listYN+
									MobileOSName+MoblieOS+MobileAppName+MobileApp+arrangeName+arrange+
									numOfRowsName+numOfRows+pageNoName+pageNo;
		return urlcomplete;
	}
		
	public void urlClean() {
		String cleaner = "";
		this.setType(cleaner);
		this.setContentId(cleaner);
		this.setContentTypeId(cleaner);
		this.setCat1(cleaner);
		this.setCat2(cleaner);
		this.setCat3(cleaner);
		this.setSigunguCode(cleaner);
	}
	
	
	
	
}
