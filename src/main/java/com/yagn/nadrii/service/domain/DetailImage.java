package com.yagn.nadrii.service.domain;

public class DetailImage {
	
	/// Field
	private int contentid;
	private String imagename;
	private String imgname;
	private String originimgurl;
	private String serialnum;
	private String smallimageurl;
	
	/// Constructor
	public DetailImage() {
		
	}

	public int getContentid() {
		return contentid;
	}

	public void setContentid(int contentid) {
		this.contentid = contentid;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	public String getOriginimgurl() {
		return originimgurl;
	}

	public void setOriginimgurl(String originimgurl) {
		this.originimgurl = originimgurl;
	}

	public String getSerialnum() {
		return serialnum;
	}

	public void setSerialnum(String serialnum) {
		this.serialnum = serialnum;
	}

	public String getSmallimageurl() {
		return smallimageurl;
	}

	public void setSmallimageurl(String smallimageurl) {
		this.smallimageurl = smallimageurl;
	}

	@Override
	public String toString() {
		return "DetailImage [contentid=" + contentid + ", imagename=" + imagename + ", imgname=" + imgname
				+ ", originimgurl=" + originimgurl + ", serialnum=" + serialnum + ", smallimageurl=" + smallimageurl
				+ "]";
	}

	

}
