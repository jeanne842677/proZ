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
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.project.model.dto.Project;
import com.kh.spring.project.model.dto.ProjectMember;
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
		
		
		//만약 프로젝트가 없으면 바로 넘기기
		if (project == null) {
			throw new HandlableException(ErrorCode.REDIRECT_MAIN_PAGE);
		}
		

		
		
		List<Workspace> workspaceList = projectService.selectWorkspaceByProjectIdx(projectIdx);
		
		
		// 온라인 오프라인 확인용
		List<Map<String, Object>> projectMemberList = CamelMap
				.changeListMap(projectService.selectProjectMemberByProjectIdx(projectIdx));

		request.setAttribute("projectMemberList", projectMemberList);
		request.setAttribute("project", project);
		request.setAttribute("workspaceList" , workspaceList);
		request.setAttribute("projectIdx" , projectIdx);
		

		String wsIdx = request.getParameter("wsIdx");
	
		//만약 파라미터가 존재하면 wsIdx 정보담기
		if(wsIdx !=null) {
			
			Workspace workspace = null;
			for(Workspace ws :workspaceList ) {
				
				if(wsIdx.equals(ws.getWsIdx())) {
					workspace = ws;
					break;
				}
				
			}
			
			if (workspace == null) {
				ErrorCode error = ErrorCode.REDIRECT_MAIN_PAGE;
				error.setURL("/project/" + projectIdx);
				throw new HandlableException(error);
			}else {
				request.setAttribute("workspace" , workspace);
				
			}
			
		}
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		
		if(member!=null) {
			ProjectMember projectMember = projectService.selectProjectMemberByProjectIdxAndUserIdx(projectIdx, member.getUserIdx());
			request.setAttribute("projectMember" , projectMember);
			System.out.println(projectMember);
		}
		//유저 정보 담기
		
		

	}

}
