package com.yagn.nadrii.service.domain.naverLogin;

public class NaverLoginRequest {
	private String grant_type;
	private String client_id;
	private String client_secret;
	private String code;
	private String state;
	private String refresh_token;
	private String access_token;
	private String sercive_provider;
	
	public String getGrant_type() {
		return grant_type;
	}
	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getClient_secret() {
		return client_secret;
	}
	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getSercive_provider() {
		return sercive_provider;
	}
	public void setSercive_provider(String sercive_provider) {
		this.sercive_provider = sercive_provider;
	}
	@Override
	public String toString() {
		return "grant_type=" + grant_type + "&client_id=" + client_id + "&client_secret="
				+ client_secret + "&code=" + code + "&state=" + state + "&refresh_token=" + refresh_token
				+ "&access_token=" + access_token + "&sercive_provider=" + sercive_provider + "]";
	}
	
	
}
