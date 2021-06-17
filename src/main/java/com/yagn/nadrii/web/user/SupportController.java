package com.yagn.nadrii.web.user;

import javax.servlet.http.HttpServletRequest;

import com.yagn.nadrii.service.domain.User;

public class SupportController {
	
	public User getLoginUser(HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("loginUser");
		return user;
	}
	
	public boolean isLogin(HttpServletRequest request) {
		if(request.getSession().getAttribute("loginUser") != null) {
			return true;
		}
		return false;
	}
	
	
}
