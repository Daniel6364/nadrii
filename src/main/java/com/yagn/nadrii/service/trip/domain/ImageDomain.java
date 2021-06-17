package com.yagn.nadrii.service.trip.domain;

public class ImageDomain {

	String thumbnail;
	String sizeheight;
	String link;
	String title;
	String sizewidth;
	
	
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getSizeheight() {
		return sizeheight;
	}
	public void setSizeheight(String sizeheight) {
		this.sizeheight = sizeheight;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSizewidth() {
		return sizewidth;
	}
	public void setSizewidth(String sizewidth) {
		this.sizewidth = sizewidth;
	}
	@Override
	public String toString() {
		return "ImageDomain [thumbnail=" + thumbnail + ", sizeheight=" + sizeheight + ", link=" + link + ", title="
				+ title + ", sizewidth=" + sizewidth + "]";
	}
	
	
	
}
