package com.yagn.nadrii.service.trip.urlmanage;


import java.util.Map;


public class TourAPIGetDetailUrlManage {
	
	private String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?";
	
		
	private String serviceKeyName = "ServiceKey=";
	private String contentTypeIdName = "&contentTypeId=";
	private String contentIdName ="&contentId=";
	private String MobileOSName = "&MobileOS=";
	private String MobileAppName = "&MobileApp=";
	private String introYNName = "&introYN=";
	
	private String serviceKey = "ay3zIymuP5LX%2BGZhKC44TDdl68jrGAk5sMJ2Ry5GkBV0TvUP14kU13EG1mkNneM4GQOTPDsVuj2%2BCKLpcwcvfg%3D%3D";
	//private String serviceKey = "Vq3ddjVdHYzbHG4A6ol%2BlAAlYTGTsmK8WRtUG1v6px9%2BPYYPhypRbhcsUaCx%2Fl6NoNCxjVAZFr56jFVYrJSXXA%3D%3D";
	//private String serviceKey = "elArz5Uco6Vjf5eFlDFY225L72%2FKxulUVoQ6ucNpvUkwvvoOZw4Pri%2B3tFJHQXU4CxNyVGp%2BNRzup9HNp4XsMg%3D%3D";
	private String contentTypeId = ""; //""
	private String contentId ="";
	private String MoblieOS = "ETC";
	private String MobileApp = "TourAPI3.0_Guide";
	private String introYN = "Y";
	
	
		
	public TourAPIGetDetailUrlManage() {
	
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
			
	public String getIntroYN() {
		return introYN;
	}

	public void setIntroYN(String introYN) {
		this.introYN = introYN;
	}

	public String urlMaking() {
		String completeUrl = url+serviceKeyName+serviceKey
						+contentIdName+contentId+contentTypeIdName+contentTypeId
						+MobileAppName+MobileApp+MobileOSName+MoblieOS+introYNName+introYN;
						
		return completeUrl;
	}
	
	public void urlClean() {
		String cleaner = "";
		this.setContentId(cleaner);
		this.setContentTypeId(cleaner);		
	}
	
	
	
		

}
