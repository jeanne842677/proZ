package com.kh.spring.common.interceptor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.common.code.CashItem;
import com.kh.spring.common.code.ErrorCode;
import com.kh.spring.common.exception.HandlableException;
import com.kh.spring.common.util.map.CamelMap;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.project.model.dto.Project;
import com.kh.spring.project.model.dto.Workspace;
import com.kh.spring.project.model.repository.ProjectRepository;
import com.kh.spring.projectmember.dto.Alarm;
import com.kh.spring.projectmember.model.repository.AlarmRepository;

@Service
public class InterceptorServiceImpl implements InterceptorService {

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired 
	AlarmRepository alarmRepository;

	@Override
	public Map<String, Object> selectProjectInfo(String projectIdx, Member member , String wsIdx) {
		
		Map<String, Object> modelMap = new HashMap<>();
		List<Workspace> workspaceList = projectRepository.selectWorkspaceByProjectIdx(projectIdx);
		List<Map<String, Object>> projectMemberList = CamelMap.changeListMap(projectRepository.selectProjectMemberByProjectIdx(projectIdx));
		Project project = projectRepository.selectProjectByIdx(projectIdx);
		
		if (project == null) {
			throw new HandlableException(ErrorCode.REDIRECT_MAIN_PAGE);
		}
	    
		  modelMap.put("projectIdx", projectIdx);
	      modelMap.put("workspaceList", workspaceList);
	      modelMap.put("projectMemberList", projectMemberList);
	      modelMap.put("project", project);
	      
	      //////////////은비가 추가
	      modelMap.put("workspaceListCnt", workspaceList.size());
	      modelMap.put("projectMemberListCnt", projectMemberList.size());

	      
	      
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
	        	 modelMap.put("workspace" , workspace);
	            
	         }
	         
	      }     
	      
	      if(member!=null) {
	         
	         Map<String, Object> projectMember = null;
	         for(Map<String, Object> pm :projectMemberList) {
	            
	            if(member.getUserIdx().equals(pm.get("userIdx"))) {
	               
	               projectMember = pm;
	               modelMap.put("projectMember" , projectMember);
	            }
	         }
	         

	         List<Alarm> alarmList = alarmRepository.selectAlramListByUserIdx(member.getUserIdx() , projectIdx);
	         modelMap.put("alarmList" , alarmList);
	         
	         
	      
	      }
	      
	      
	      
	       List<String> cashList = new ArrayList<String>();
	         cashList = projectRepository.selectCashListByProjectIdx(projectIdx);
	         if(!cashList.isEmpty()) {
	        	 modelMap.put("golden", "gold");

	              for(String list : cashList) {
	              if(list.equals(CashItem.MEMBER_COUNT_UPGRADE.getITEM_NAME())) {
	            	  modelMap.put("memberFunction", list); 
	                 }else if(list.equals(CashItem.WORKSPACE_UPGRADE.getITEM_NAME())) {
	                	 modelMap.put("workspaceFunction", list); 
	                 }else if(list.equals(CashItem.WORKSPACE_UPGRADE.getITEM_NAME())) {
	                	 modelMap.put("chatFunction", list); 
	                 }else {//전체 결제 경우
	                	 modelMap.put("allFunction", list); 
	                 }
	              
	              }
	             
	         }
		
		
		
		return modelMap;
	}
	
	@Override
	public void authInterceptor(String projectIdx, Member member ,HttpServletRequest httpRequest) {

		Project project = projectRepository.selectProjectExist(projectIdx);
		
		if (member == null) {
			throw new HandlableException(ErrorCode.NEED_LOGIN);
		} else {
			// 프로젝트가 없을 경우
			if (project == null || projectRepository.projectIsDel(projectIdx) == 1) {
				throw new HandlableException(ErrorCode.PROJECT_URL_ERROR);
			}

			// 프로젝트 멤버가 아닐 경우
			
			List<String> proIdxList = projectRepository.selectProjectIdxByUserIdx(member.getUserIdx());

			boolean flg = true;
			for (String proIdx : proIdxList) {
				if (proIdx.equals(projectIdx)) {
					flg = false;
				}
			}
			try {
				Map<String, Object> projectMember = (Map<String, Object>) httpRequest.getAttribute("projectMember");
				System.out.println("projectMember +++++*****++++" + projectMember);
				if (projectMember.get("isLeave").equals("1")) {// 맴버가 프로젝트에 서 탈퇴상태 일때
					flg = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new HandlableException(ErrorCode.WRONG_ACCESS_ERROR);
			}

			if (flg) {
				throw new HandlableException(ErrorCode.WRONG_ACCESS_ERROR);
			}
			 
		}
		

	}



}
