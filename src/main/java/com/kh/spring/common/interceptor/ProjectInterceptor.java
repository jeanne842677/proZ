package com.kh.spring.common.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import com.kh.spring.common.interceptor.service.InterceptorService;
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
   
   @Autowired
   InterceptorService interceptorService;
   

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

      
     // Project project = projectService.selectProjectByIdx(projectIdx);
      Member member = (Member) request.getSession().getAttribute("authentication");

      String wsIdx = request.getParameter("wsIdx");
   
      
      Map<String, Object> projectInfo = interceptorService.selectProjectInfo(projectIdx , member ,wsIdx);
      

      for(Entry<String, Object> e : projectInfo.entrySet()) {
    	  
    	  request.setAttribute( e.getKey(), e.getValue());
    	  
      }
      
      

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