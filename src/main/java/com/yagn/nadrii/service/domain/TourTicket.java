package com.yagn.nadrii.service.domain;

import java.util.List;

public class TourTicket {
	
	/// Field
	// Basic response
	private int contentid;
	private int contenttypeid;
	
	private int eventstartdate;
	private int eventenddate;	
	
	private String firstimage;
	private String firstimage2;
	
	private int readcount;
	
	private String title;
	private String tel;
	
	private int areacode;
	private int sigungucode;

	private String agelimit;
	private String bookingplace;
	private String discountinfofestival;
	private String eventhomepage;
	private String eventplace;
	
	private String festivalgrade;
	private String placeinfo;
	private String playtime;
	private String program;
	private String spendtimefestival;
	
	private String sponsor1tel;
	private String sponsor2tel;
	private String sponsor1;
	private String sponsor2;
	
	private String subevent;
	private List<String> usetimefestival;
	
	public TourTicket() {
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
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getAreacode() {
		return areacode;
	}
	public void setAreacode(int areacode) {
		this.areacode = areacode;
	}
	public int getSigungucode() {
		return sigungucode;
	}
	public void setSigungucode(int sigungucode) {
		this.sigungucode = sigungucode;
	}
	public String getAgelimit() {
		return agelimit;
	}
	public void setAgelimit(String agelimit) {
		this.agelimit = agelimit;
	}
	public String getBookingplace() {
		return bookingplace;
	}
	public void setBookingplace(String bookingplace) {
		this.bookingplace = bookingplace;
	}
	public String getDiscountinfofestival() {
		return discountinfofestival;
	}
	public void setDiscountinfofestival(String discountinfofestival) {
		this.discountinfofestival = discountinfofestival;
	}
	public String getEventhomepage() {
		return eventhomepage;
	}
	public void setEventhomepage(String eventhomepage) {
		this.eventhomepage = eventhomepage;
	}
	public String getEventplace() {
		return eventplace;
	}
	public void setEventplace(String eventplace) {
		this.eventplace = eventplace;
	}
	public String getFestivalgrade() {
		return festivalgrade;
	}
	public void setFestivalgrade(String festivalgrade) {
		this.festivalgrade = festivalgrade;
	}
	public String getPlaceinfo() {
		return placeinfo;
	}
	public void setPlaceinfo(String placeinfo) {
		this.placeinfo = placeinfo;
	}
	public String getPlaytime() {
		return playtime;
	}
	public void setPlaytime(String playtime) {
		this.playtime = playtime;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getSpendtimefestival() {
		return spendtimefestival;
	}
	public void setSpendtimefestival(String spendtimefestival) {
		this.spendtimefestival = spendtimefestival;
	}
	public String getSponsor1tel() {
		return sponsor1tel;
	}
	public void setSponsor1tel(String sponsor1tel) {
		this.sponsor1tel = sponsor1tel;
	}
	public String getSponsor2tel() {
		return sponsor2tel;
	}
	public void setSponsor2tel(String sponsor2tel) {
		this.sponsor2tel = sponsor2tel;
	}
	public String getSponsor1() {
		return sponsor1;
	}
	public void setSponsor1(String sponsor1) {
		this.sponsor1 = sponsor1;
	}
	public String getSponsor2() {
		return sponsor2;
	}
	public void setSponsor2(String sponsor2) {
		this.sponsor2 = sponsor2;
	}
	public String getSubevent() {
		return subevent;
	}
	public void setSubevent(String subevent) {
		this.subevent = subevent;
	}
	public List<String> getUsetimefestival() {
		return usetimefestival;
	}
	public void setUsetimefestival(List<String> usetimefestival) {
		this.usetimefestival = usetimefestival;
	}
	@Override
	public String toString() {
		return "TourTicket [contentid=" + contentid + ", contenttypeid=" + contenttypeid + ", eventstartdate="
				+ eventstartdate + ", eventenddate=" + eventenddate + ", firstimage=" + firstimage + ", firstimage2="
				+ firstimage2 + ", readcount=" + readcount + ", title=" + title + ", tel=" + tel + ", areacode="
				+ areacode + ", sigungucode=" + sigungucode + ", agelimit=" + agelimit + ", bookingplace="
				+ bookingplace + ", discountinfofestival=" + discountinfofestival + ", eventhomepage=" + eventhomepage
				+ ", eventplace=" + eventplace + ", festivalgrade=" + festivalgrade + ", placeinfo=" + placeinfo
				+ ", playtime=" + playtime + ", program=" + program + ", spendtimefestival=" + spendtimefestival
				+ ", sponsor1tel=" + sponsor1tel + ", sponsor2tel=" + sponsor2tel + ", sponsor1=" + sponsor1
				+ ", sponsor2=" + sponsor2 + ", subevent=" + subevent + ", usetimefestival=" + usetimefestival + "]";
	}
	
	
	
	
}
