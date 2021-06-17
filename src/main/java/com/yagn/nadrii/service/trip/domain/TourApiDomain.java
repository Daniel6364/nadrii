package com.yagn.nadrii.service.trip.domain;


import java.util.Map;

import com.yagn.nadrii.service.domain.Trip;


public class TourApiDomain {
	
	/// Field
	// header value
	private String resultCode;
	private String resultMsg;
	
	// body value
	private String title;
	private String addr1;
	private String addr2;
	private int areacode;
	private String cat1;
	private String cat2;
	private String cat3;
	private int contentid;
	private int contenttypeid;
	private String firstimage;
	private String firstimage2;
	private String mapx;
	private String mapy;
	private int mlevel;
	private String overview;
	private int sigungucode;
	private String zipcode;
	private int numOfRows;
	private int pageNo;
	private int totalCount;
	private String readcount;
	private String createdtime;
	private String modifiedtime;
	private String tel;
	private String booktour;
	private String telname;
	private String homepage;
	
	//������� ���� JSON
	
	private String restdateculture;
	private String spendtime;
	private String scale;
	private String parkingculture;
	private String usefee;
	private String parkingfee;
	private String chkcreditcardculture;
	private String chkbabycarriageculture;
	private String infocenterculture;
	private String usetimeculture;
	private String chkpetculture;
	private String accomcountculture;
	private String discountinfo;
	
	
	//ü��� �ʵ�
	private String chkpet;
	private String expagerange;
	private String parking;
	private String infocenter;
	private String restdate;
	private String accomcount;
	private String opendate;
	private String heritage3;
	private String heritage2;
	private String heritage1;
	private String expguide;
	private String chkcreditcard;
	private String usetime;
	private String chkbabycarriage;
	private Trip trip;
	
	////
	private int flag;
	
	////
	private String sponser2tel;
	
	public String getSponser2tel() {
		return sponser2tel;
	}


	public void setSponser2tel(String sponser2tel) {
		this.sponser2tel = sponser2tel;
	}

	public Trip getTrip() {
		return trip;
	}


	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}


	public String getChkbabycarriage() {
		return chkbabycarriage;
	}


	public void setChkbabycarriage(String chkbabycarriage) {
		this.chkbabycarriage = chkbabycarriage;
	}


	public String getUsetime() {
		return usetime;
	}


	public void setUsetime(String usetime) {
		this.usetime = usetime;
	}


	public String getOpendate() {
		return opendate;
	}


	public void setOpendate(String opendate) {
		this.opendate = opendate;
	}


	public String getHeritage3() {
		return heritage3;
	}


	public void setHeritage3(String heritage3) {
		this.heritage3 = heritage3;
	}


	public String getHeritage2() {
		return heritage2;
	}


	public void setHeritage2(String heritage2) {
		this.heritage2 = heritage2;
	}


	public String getHeritage1() {
		return heritage1;
	}


	public void setHeritage1(String heritage1) {
		this.heritage1 = heritage1;
	}


	public String getExpguide() {
		return expguide;
	}


	public void setExpguide(String expguide) {
		this.expguide = expguide;
	}


	public String getChkcreditcard() {
		return chkcreditcard;
	}


	public void setChkcreditcard(String chkcreditcard) {
		this.chkcreditcard = chkcreditcard;
	}


	public String getAccomcount() {
		return accomcount;
	}


	public void setAccomcount(String accomcount) {
		this.accomcount = accomcount;
	}


	public String getRestdate() {
		return restdate;
	}


	public void setRestdate(String restdate) {
		this.restdate = restdate;
	}


	public String getInfocenter() {
		return infocenter;
	}


	public void setInfocenter(String infocenter) {
		this.infocenter = infocenter;
	}


	public String getParking() {
		return parking;
	}


	public void setParking(String parking) {
		this.parking = parking;
	}


	public String getExpagerange() {
		return expagerange;
	}


	public void setExpagerange(String expagerange) {
		this.expagerange = expagerange;
	}


	public String getChkpet() {
		return chkpet;
	}


	public void setChkpet(String chkpet) {
		this.chkpet = chkpet;
	}


	public String getDiscountinfo() {
		return discountinfo;
	}


	public void setDiscountinfo(String discountinfo) {
		this.discountinfo = discountinfo;
	}


	public String getAccomcountculture() {
		return accomcountculture;
	}


	public void setAccomcountculture(String accomcountculture) {
		this.accomcountculture = accomcountculture;
	}


	/// Constructor
	public TourApiDomain() {
			
	}
	
	
	public String getRestdateculture() {
		return restdateculture;
	}


	public void setRestdateculture(String restdateculture) {
		this.restdateculture = restdateculture;
	}


	public String getSpendtime() {
		return spendtime;
	}


	public void setSpendtime(String spendtime) {
		this.spendtime = spendtime;
	}


	public String getScale() {
		return scale;
	}


	public void setScale(String scale) {
		this.scale = scale;
	}


	public String getParkingculture() {
		return parkingculture;
	}


	public void setParkingculture(String parkingculture) {
		this.parkingculture = parkingculture;
	}


	public String getUsefee() {
		return usefee;
	}


	public void setUsefee(String usefee) {
		this.usefee = usefee;
	}


	public String getParkingfee() {
		return parkingfee;
	}


	public void setParkingfee(String parkingfee) {
		this.parkingfee = parkingfee;
	}


	public String getChkcreditcardculture() {
		return chkcreditcardculture;
	}


	public void setChkcreditcardculture(String chkcreditcardculture) {
		this.chkcreditcardculture = chkcreditcardculture;
	}


	public String getChkbabycarriageculture() {
		return chkbabycarriageculture;
	}


	public void setChkbabycarriageculture(String chkbabycarriageculture) {
		this.chkbabycarriageculture = chkbabycarriageculture;
	}


	public String getInfocenterculture() {
		return infocenterculture;
	}


	public void setInfocenterculture(String infocenterculture) {
		this.infocenterculture = infocenterculture;
	}


	public String getUsetimeculture() {
		return usetimeculture;
	}


	public void setUsetimeculture(String usetimeculture) {
		this.usetimeculture = usetimeculture;
	}


	public String getChkpetculture() {
		return chkpetculture;
	}


	public void setChkpetculture(String chkpetculture) {
		this.chkpetculture = chkpetculture;
	}


	public String getTelname() {
		return telname;
	}


	public void setTelname(String telname) {
		this.telname = telname;
	}


	public String getHomepage() {
		return homepage;
	}


	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}


	public String getBooktour() {
		return booktour;
	}

	public void setBooktour(String booktour) {
		this.booktour = booktour;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getModifiedtime() {
		return modifiedtime;
	}

	public void setModifiedtime(String modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	

	public String getResultCode() {
		return resultCode;
	}

	public String getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public int getAreacode() {
		return areacode;
	}

	public void setAreacode(int areacode) {
		this.areacode = areacode;
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

	public int getContentid() {
		return contentid;
	}

	public void setContentid(int contentid) {
		this.contentid = contentid;
	}

	public int getContenttypeid() {
		return contenttypeid;
	}

	public void setContenttypeid(int contenttypeid) {
		this.contenttypeid = contenttypeid;
	}

	public String getFirstimage() {
		return firstimage;
	}

	public void setFirstimage(String firstimage) {
		this.firstimage = firstimage;
	}

	public String getFirstimage2() {
		return firstimage2;
	}

	public void setFirstimage2(String firstimage2) {
		this.firstimage2 = firstimage2;
	}

	public String getMapx() {
		return mapx;
	}

	public void setMapx(String mapx) {
		this.mapx = mapx;
	}

	public String getMapy() {
		return mapy;
	}

	public void setMapy(String mapy) {
		this.mapy = mapy;
	}

	public int getMlevel() {
		return mlevel;
	}

	public void setMlevel(int mlevel) {
		this.mlevel = mlevel;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public int getSigungucode() {
		return sigungucode;
	}

	public void setSigungucode(int sigungucode) {
		this.sigungucode = sigungucode;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	

	public String getReadcount() {
		return readcount;
	}

	public void setReadcount(String readcount) {
		this.readcount = readcount;
	}
	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public String toString() {
		return "TourApiDomain [resultCode=" + resultCode + ", resultMsg=" + resultMsg + ", title=" + title + ", addr1="
				+ addr1 + ", addr2=" + addr2 + ", areacode=" + areacode + ", cat1=" + cat1 + ", cat2=" + cat2
				+ ", cat3=" + cat3 + ", contentid=" + contentid + ", contenttypeid=" + contenttypeid + ", firstimage="
				+ firstimage + ", firstimage2=" + firstimage2 + ", mapx=" + mapx + ", mapy=" + mapy + ", mlevel="
				+ mlevel + ", overview=" + overview + ", sigungucode=" + sigungucode + ", zipcode=" + zipcode
				+ ", numOfRows=" + numOfRows + ", pageNo=" + pageNo + ", totalCount=" + totalCount + ", readcount="
				+ readcount + ", createdtime=" + createdtime + ", modifiedtime=" + modifiedtime + ", tel=" + tel
				+ ", booktour=" + booktour + ", telname=" + telname + ", homepage=" + homepage + ", restdateculture="
				+ restdateculture + ", spendtime=" + spendtime + ", scale=" + scale + ", parkingculture="
				+ parkingculture + ", usefee=" + usefee + ", parkingfee=" + parkingfee + ", chkcreditcardculture="
				+ chkcreditcardculture + ", chkbabycarriageculture=" + chkbabycarriageculture + ", infocenterculture="
				+ infocenterculture + ", usetimeculture=" + usetimeculture + ", chkpetculture=" + chkpetculture + "]";
	}

	

		

}
