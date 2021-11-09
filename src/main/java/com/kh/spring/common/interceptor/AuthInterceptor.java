package com.kh.spring.common.interceptor;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;





//servlet-context에서 빈으로 등록해 사용함
public class AuthInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler) {

		String[] uri = httpRequest.getRequestURI().split("/");

		if (uri.length != 0) {

			
		}

		return true;

	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
