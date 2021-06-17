package com.yagn.nadrii.service.domain;

public class SearchContent {

	String userId;
	String contentTypeId;
	String contentId;
	
	public SearchContent() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		return "SearchContent [userId=" + userId + ", contentTypeId=" + contentTypeId + ", contentId=" + contentId
				+ "]";
	}
	
	
	
}
