package com.kh.spring.common.interceptor;

import java.util.ArrayList;
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
import com.kh.spring.common.code.CashItem;
import com.kh.spring.common.code.ErrorCode;
import com.kh.spring.common.exception.HandlableException;
import com.kh.spring.common.util.map.CamelMap;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.project.model.dto.Project;
import com.kh.spring.project.model.dto.ProjectMember;
import com.kh.spring.project.model.dto.Workspace;
import com.kh.spring.project.model.service.ProjectService;
import com.kh.spring.projectmember.dto.Alarm;
import com.kh.spring.projectmember.model.repository.AlarmRepository;


//url이 projectIdx로 끝나는 모든 친구들 interceptor 
public class ProjectInterceptor implements HandlerInterceptor {

   @Autowired
   ProjectService projectService;

   @Autowired
   BoardService boardService;
   
   @Autowired
   AlarmRepository alarmRepository;
   

   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
      
      String[] uri = request.getRequestURI().split("/");
      
      
      if (uri.length != 0) {
         
         String projectIdx = uri[uri.length-1];
         boolean match = Pattern.matches("\\d{6}" , projectIdx );
         
         if(match) {
            addNavAtrribute(request, response , projectIdx);
            cashAttribute(request, response , projectIdx);
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
      ///////////은비 추가
      request.setAttribute("workspaceListCnt", workspaceList.size());
      request.setAttribute("projectMemberListCnt", projectMemberList.size());

      
      
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
         
         Map<String, Object> projectMember = null;
         for(Map<String, Object> pm :projectMemberList) {
            
            if(member.getUserIdx().equals(pm.get("userIdx"))) {
               
               projectMember = pm;
               request.setAttribute("projectMember" , projectMember);
            }
         }
         

         List<Alarm> alarmList = alarmRepository.selectAlramListByUserIdx(member.getUserIdx());
         request.setAttribute("alarmList" , alarmList);
         
      
      }
      //유저 정보 담기
      
      

   }
   
   private void cashAttribute(HttpServletRequest request, HttpServletResponse response, String projectIdx) {
      
       List<String> cashList = new ArrayList<String>();
         cashList = projectService.selectCashListByProjectIdx(projectIdx);
         if(!cashList.isEmpty()) {
            request.setAttribute("golden", "gold");

              for(String list : cashList) {
              if(list.equals(CashItem.MEMBER_COUNT_UPGRADE.getITEM_NAME())) {
                 request.setAttribute("memberFunction", list); 
                 }else if(list.equals(CashItem.WORKSPACE_UPGRADE.getITEM_NAME())) {
                 request.setAttribute("workspaceFunction", list); 
                 }else if(list.equals(CashItem.WORKSPACE_UPGRADE.getITEM_NAME())) {
                 request.setAttribute("chatFunction", list); 
                 }else {//전체 결제 경우
                    request.setAttribute("allFunction", list); 
                 }
              
              }
             
         }
   }
}