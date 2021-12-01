package com.kh.spring.common.interceptor;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring.board.model.dto.Board;
import com.kh.spring.board.model.dto.Post;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.common.code.ErrorCode;
import com.kh.spring.common.exception.HandlableException;
import com.kh.spring.common.util.map.CamelMap;
import com.kh.spring.project.model.dto.Project;
import com.kh.spring.project.model.dto.Workspace;
import com.kh.spring.project.model.service.ProjectService;


//url이 projectIdx로 끝나는 모든 친구들 interceptor 
public class ProjectInterceptor implements HandlerInterceptor {

	@Autowired
	ProjectService projectService;

	@Autowired
	BoardService boardService;
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		
		String[] uri = request.getRequestURI().split("/");
		
		
		if (uri.length != 0) {
			
			String projectIdx = uri[uri.length-1];
			boolean match = Pattern.matches("\\d{6}" , projectIdx );
			
			if(match) {
				addNavAtrribute(request, response , projectIdx);
			}



		}

		return true;

	}

	private void addNavAtrribute(HttpServletRequest request, HttpServletResponse response, String projectIdx) {

		
		Project project = projectService.selectProjectByIdx(projectIdx);

		if (project == null) {
			throw new HandlableException(ErrorCode.REDIRECT_MAIN_PAGE);
		}
		
		List<Workspace> workspaceList = projectService.selectWorkspaceByProjectIdx(projectIdx);
		
		
		// 온라인 오프라인 확인용
		List<Map<String, Object>> projectMember = CamelMap
				.changeListMap(projectService.selectProjectMemberByProjectIdx(projectIdx));

		request.setAttribute("projectMemberList", projectMember);
		request.setAttribute("project", project);
		request.setAttribute("workspaceList" , workspaceList);
		request.setAttribute("projectIdx" , projectIdx);

	}

}
