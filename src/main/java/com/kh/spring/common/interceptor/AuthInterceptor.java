package com.kh.spring.common.interceptor;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring.common.code.ErrorCode;
import com.kh.spring.common.exception.HandlableException;
import com.kh.spring.common.interceptor.service.InterceptorService;
import com.kh.spring.common.util.map.CamelMap;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.project.model.dto.Project;
import com.kh.spring.project.model.dto.ProjectRole;
import com.kh.spring.project.model.service.ProjectService;

//servlet-context에서 빈으로 등록해 사용함
public class AuthInterceptor implements HandlerInterceptor {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	InterceptorService interceptorService;

	@Override
	public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler) {

		String[] uri = httpRequest.getRequestURI().split("/");
		String projectIdx = "";

		if (uri.length != 0) {
			
			projectIdx = uri[uri.length-1];
			
			switch (uri[1]) {

			case "project":
				projectAuthorize(httpRequest, httpResponse, uri,projectIdx);
				break;
				
			case "chat":
				chattingAuthorize(httpRequest, httpResponse, uri,projectIdx);
				break;

			default:
				break;
			}

		}

		return true;

	}

	private void chattingAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uri ,String projectIdx) {
		HttpSession session = httpRequest.getSession();
		Member member = (Member) session.getAttribute("authentication");
		
		//인터셉터
		interceptorService.authInterceptor(projectIdx,member,httpRequest);
		
		
		
	}

	private void projectAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uri, String projectIdx) {
		HttpSession session = httpRequest.getSession();
		Member member = (Member) session.getAttribute("authentication");

		if (uri.length != 0) {

			switch (uri[2]) {

			case "setting":
				//인터셉터
				interceptorService.authInterceptor(projectIdx,member,httpRequest);
				
				ProjectRole role = projectService.selectProjectRoleByProjectIdxAndUserIdx(projectIdx,member.getUserIdx());
				System.out.println("인터셉터 롤 : " + role);
				switch (uri[3]) {
				case "role-management"://역할관리권한체크
					if(role.getProjectAuth() == 0) {
						throw new HandlableException(ErrorCode.UNAUTHORIZE_PAGE);
					}
					break;
				case "member-management"://맴버관리권한체크
					if(role.getMemberAuth() == 0) {
						throw new HandlableException(ErrorCode.UNAUTHORIZE_PAGE);
					}
					break;

				default:
					break;
				}
				

				break;

			default:
				break;
			}

		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
