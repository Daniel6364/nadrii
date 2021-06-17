package com.yagn.nadrii.service.trip.urlmanage;


import java.util.Map;


public class TourAPIGetUrlManage {
	
	private String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?";
	
		
	private String serviceKeyName = "ServiceKey=";
	private String contentTypeIdName = "&contentTypeId=";
	private String contentIdName ="&contentId=";
	private String MobileOSName = "&MobileOS=";
	private String MobileAppName = "&MobileApp=";
	private String defaultYNName = "&defaultYN=";
	private String firstImageYNName = "&firstImageYN=";
	private String areaCodeYNName = "&areaCodeYN=";
	private String catcodeYNName = "&catcodeYN=";
	private String addrinfoYNName = "&addrinfoYN=";
	private String mapinfoYNName = "&mapinfoYN=";
	private String overviewYNName = "&overviewYN=";
	private String transGuideYNName = "&transGuideYN=";
	
	//private String serviceKey = "Vq3ddjVdHYzbHG4A6ol%2BlAAlYTGTsmK8WRtUG1v6px9%2BPYYPhypRbhcsUaCx%2Fl6NoNCxjVAZFr56jFVYrJSXXA%3D%3D";
	private String serviceKey = "ay3zIymuP5LX%2BGZhKC44TDdl68jrGAk5sMJ2Ry5GkBV0TvUP14kU13EG1mkNneM4GQOTPDsVuj2%2BCKLpcwcvfg%3D%3D";
	private String contentTypeId = ""; //""
	private String contentId ="";
	private String MoblieOS = "ETC";
	private String MobileApp = "TourAPI3.0_Guide";
	private String areaCodeYN = "Y";
	private String defaultYN="Y";
	private String firstImageYN ="Y";
	private String catcodeYN = "Y";
	private String addrinfoYN ="Y";
	private String mapinfoYN ="Y";
	private String overviewYN ="Y";
	private String transGuideYN ="Y";
	
	
		
	public TourAPIGetUrlManage() {
	
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
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
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
	public String getAreaCodeYN() {
		return areaCodeYN;
	}
	public void setAreaCodeYN(String areaCodeYN) {
		this.areaCodeYN = areaCodeYN;
	}
	public String getDefaultYN() {
		return defaultYN;
	}
	public void setDefaultYN(String defaultYN) {
		this.defaultYN = defaultYN;
	}
	public String getFirstImageYN() {
		return firstImageYN;
	}
	public void setFirstImageYN(String firstImageYN) {
		this.firstImageYN = firstImageYN;
	}
	public String getCatcodeYN() {
		return catcodeYN;
	}
	public void setCatcodeYN(String catcodeYN) {
		this.catcodeYN = catcodeYN;
	}
	public String getAddrinfoYN() {
		return addrinfoYN;
	}
	public void setAddrinfoYN(String addrinfoYN) {
		this.addrinfoYN = addrinfoYN;
	}
	public String getMapinfoYN() {
		return mapinfoYN;
	}
	public void setMapinfoYN(String mapinfoYN) {
		this.mapinfoYN = mapinfoYN;
	}
	public String getOverviewYN() {
		return overviewYN;
	}
	public void setOverviewYN(String overviewYN) {
		this.overviewYN = overviewYN;
	}
	public String getTransGuideYN() {
		return transGuideYN;
	}
	public void setTransGuideYN(String transGuideYN) {
		this.transGuideYN = transGuideYN;
	}
	
	
	public String urlMaking() {
		String completeUrl = url+serviceKeyName+serviceKey
						+contentIdName+contentId+contentTypeIdName+contentTypeId
						+MobileAppName+MobileApp+MobileOSName+MoblieOS+areaCodeYNName+areaCodeYN
						+defaultYNName+defaultYN+firstImageYNName+firstImageYN+catcodeYNName+catcodeYN
						+addrinfoYNName+addrinfoYN+mapinfoYNName+mapinfoYN+overviewYNName+overviewYN
						+transGuideYNName+transGuideYN;
						
		return completeUrl;
	}
	
	public void urlClean() {
		String cleaner = "";
		this.setContentId(cleaner);
		this.setContentTypeId(cleaner);		
	}
	
	
	
		

}
