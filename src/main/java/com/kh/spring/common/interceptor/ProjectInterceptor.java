package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class ProjectInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler) {

		String[] uri = httpRequest.getRequestURI().split("/");

		if (uri.length != 0) {

			switch (uri[1]) {

			case "project":
				System.out.println("project관련 인터셉터 실행");
				break;

			default:
				break;
			}

		}

		return true;

	}

}
