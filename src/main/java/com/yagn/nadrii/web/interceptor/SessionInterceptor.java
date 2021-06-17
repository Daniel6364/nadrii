package com.yagn.nadrii.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Service
public class SessionInterceptor  extends HandlerInterceptorAdapter{
    
	 String requestURL = "";
	
	 public boolean preHandle(HttpServletRequest request, 
	    		HttpServletResponse response,Object handler) throws Exception{
		 
		 requestURL = request.getRequestURI();
		 System.out.println("юнем╪аем " + requestURL);
		 if(requestURL.equals("/main")) {
			 return false;
		 }else {
			 return false;
		 }
		 
	    }
	
	    public void postHandle(HttpServletRequest request, 
	    		HttpServletResponse response, Object handler) throws Exception{
	    	
	    }
	    
	   
}
