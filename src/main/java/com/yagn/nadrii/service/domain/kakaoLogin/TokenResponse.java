package com.yagn.nadrii.service.domain.kakaoLogin;

public class TokenResponse {
	private String access_token;
	private String token_type;
	private String refresh_token;
	private long expires_in;
	private String scope;
	private long refresh_token_expires_in;
	private String error_description;
	private String error;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public long getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public long getRefresh_token_expires_in() {
		return refresh_token_expires_in;
	}
	public void setRefresh_token_expires_in(long refresh_token_expires_in) {
		this.refresh_token_expires_in = refresh_token_expires_in;
	}
	@Override
	public String toString() {
		if(error_description != null) {
			return "TokenResponse [access_token=" + access_token + ", token_type=" + token_type + ", refresh_token="
					+ refresh_token + ", expires_in=" + expires_in + ", scope=" + scope + "]";
		}else {
			return "TokenResponseError[error_description="+error_description+", error="+error+"]";
		}
	}
	
	public String getError_description() {
		return error_description;
	}
	public void setError_description(String error_description) {
		this.error_description = error_description;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
