package com.yagn.nadrii.service.domain;

public class DetailIntro {

	/// Field
	// Basic response
	private int contentid;
	private int contenttypeid;
	
	// 행사/공연/축제 (contentTypeId = 15)
	
	private String agelimit;
	private String bookingplace;
	private String discountinfofestival;
	private String eventhomepage;
	private String eventplace;
	private int eventenddate;
	private int eventstartdate;
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
	private String usetimefestival;
	
	
	/// Constructor
	public DetailIntro() {
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


	public int getEventenddate() {
		return eventenddate;
	}


	public void setEventenddate(int eventenddate) {
		this.eventenddate = eventenddate;
	}


	public int getEventstartdate() {
		return eventstartdate;
	}


	public void setEventstartdate(int eventstartdate) {
		this.eventstartdate = eventstartdate;
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


	public String getUsetimefestival() {
		return usetimefestival;
	}


	public void setUsetimefestival(String usetimefestival) {
		this.usetimefestival = usetimefestival;
	}


	@Override
	public String toString() {
		return "DetailIntro [contentid=" + contentid + ", contenttypeid=" + contenttypeid + ", agelimit=" + agelimit
				+ ", bookingplace=" + bookingplace + ", discountinfofestival=" + discountinfofestival
				+ ", eventhomepage=" + eventhomepage + ", eventplace=" + eventplace + ", eventenddate=" + eventenddate
				+ ", eventstartdate=" + eventstartdate + ", festivalgrade=" + festivalgrade + ", placeinfo=" + placeinfo
				+ ", playtime=" + playtime + ", program=" + program + ", spendtimefestival=" + spendtimefestival
				+ ", sponsor1tel=" + sponsor1tel + ", sponsor2tel=" + sponsor2tel + ", sponsor1=" + sponsor1
				+ ", sponsor2=" + sponsor2 + ", subevent=" + subevent + ", usetimefestival=" + usetimefestival + "]";
	}

}
