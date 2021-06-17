package com.yagn.nadrii.service.domain;

public class SearchFestival {

	/// Field
	// Basic response
	private int contentid;
	private int contenttypeid;
		
	private String addr1;
	private String addr2;
	private int areacode;
	private int booktour;
	private String cat1;
	private String cat2;
	private String cat3;
	private String createdtime;
	private int eventstartdate;
	private int eventenddate;
	private String firstimage;
	private String firstimage2;
	private String mapx;
	private String mapy;
	private int mlevel;
	private String modifiedtime;
	private int readcount;
	private int sigungucode;
	private String tel;
	private String title;
	
	
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
	public int getBooktour() {
		return booktour;
	}
	public void setBooktour(int booktour) {
		this.booktour = booktour;
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
	public String getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}
	public int getEventstartdate() {
		return eventstartdate;
	}
	public void setEventstartdate(int eventstartdate) {
		this.eventstartdate = eventstartdate;
	}
	public int getEventenddate() {
		return eventenddate;
	}
	public void setEventenddate(int eventenddate) {
		this.eventenddate = eventenddate;
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
	public String getModifiedtime() {
		return modifiedtime;
	}
	public void setModifiedtime(String modifiedtime) {
		this.modifiedtime = modifiedtime;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getSigungucode() {
		return sigungucode;
	}
	public void setSigungucode(int sigungucode) {
		this.sigungucode = sigungucode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "SearchFestival [addr1=" + addr1 + ", addr2=" + addr2 + ", areacode=" + areacode + ", booktour="
				+ booktour + ", cat1=" + cat1 + ", cat2=" + cat2 + ", cat3=" + cat3 + ", contentid=" + contentid
				+ ", contenttypeid=" + contenttypeid + ", createdtime=" + createdtime + ", eventstartdate="
				+ eventstartdate + ", eventenddate=" + eventenddate + ", firstimage=" + firstimage + ", firstimage2="
				+ firstimage2 + ", mapx=" + mapx + ", mapy=" + mapy + ", mlevel=" + mlevel + ", modifiedtime="
				+ modifiedtime + ", readcount=" + readcount + ", sigungucode=" + sigungucode + ", tel=" + tel
				+ ", title=" + title + "]";
	}
	
	
	
}
