package com.yagn.nadrii.service.domain.kakaoLogin;

public class TokenRequest {
	private final static String grant_type = "authorization_code";
	private final static String client_id="ffbb3cfd77a7b485daca0958078eb74a";
	private final static String redirect_uri = "http://192.168.0.39:8080/user/kakaoLogin";
	private String code;
	private final static String client_secret="yMMDvwpl9M355IhZSl82ZmPQ1GLW5GtE";
	
	public String getClient_id() {
		return client_id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public static String getGrantType() {
		return grant_type;
	}
	public static String getRedirectUri() {
		return redirect_uri;
	}
	public static String getClientSecret() {
		return client_secret;
	}
	@Override
	public String toString() {
		return "code=" + code + "&grant_type="+grant_type+"&client_id="+client_id+"&redirect_uri="+redirect_uri+"&client_secret="+client_secret;
	}

}
