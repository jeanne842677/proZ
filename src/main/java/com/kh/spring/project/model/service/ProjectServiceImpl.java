package com.kh.spring.project.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.kh.spring.board.model.repository.BoardRepository;
import com.kh.spring.calendar.model.dto.Calendar;
import com.kh.spring.calendar.model.repository.CalendarRepository;
import com.kh.spring.common.code.Config;
import com.kh.spring.common.code.ErrorCode;
import com.kh.spring.common.exception.HandlableException;
import com.kh.spring.common.mail.MailSender;
import com.kh.spring.common.util.file.FileDTO;
import com.kh.spring.common.util.map.CamelMap;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.memo.model.repository.MemoRepository;
import com.kh.spring.project.model.dto.Project;
import com.kh.spring.project.model.dto.ProjectMember;
import com.kh.spring.project.model.dto.ProjectRole;
import com.kh.spring.project.model.dto.Workspace;
import com.kh.spring.project.model.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
	
   @Autowired
   ProjectRepository projectRepository;

   @Autowired
   MemoRepository memoRepository;
   
   @Autowired
   BoardRepository boardRepository;
   
   @Autowired
   CalendarRepository calendarRepository;
   
   @Autowired
   private MailSender mailSender;

   @Autowired
   private RestTemplate http;
   

   @Override
   public String updateProjectInviteCode(String projectIdx) {

      String newUuid = UUID.randomUUID().toString();
      projectRepository.updateProjectInviteCode(newUuid, projectIdx);

      return newUuid;

   }

   @Override
   public Project selectProjectByIdx(String projectIdx) {

      return projectRepository.selectProjectByIdx(projectIdx);
   }

   @Override
   public List<Map<String, Object>> selectProjectMemberByProjectIdx(String projectIdx) {

      return projectRepository.selectProjectMemberByProjectIdx(projectIdx);
   }

   @Override
   public List<Map<String, Object>> selectProjectMemberRoleByProjectIdx(String projectIdx) {
      return projectRepository.selectProjectMemberRoleByProjectIdx(projectIdx);
   }

   // ?????? ?????? ??????

   @Override
   public List<Map<String, Object>> selectUserAndMemberByEmail(String email) {

      return CamelMap.changeListMap(projectRepository.selectUserAndMemberByEmail(email));

   }

   @Override
   public void inviteMemberByEmail(Member newMember, Project project) {

      Map<String, Object> defaultRole = CamelMap
            .changeMap(projectRepository.selectDefaultOfProjectRole(project.getProjectIdx()));
      String authIdx = (String) defaultRole.get("authIdx"); // ?????? idx??? ????????????

      ProjectMember newProjectMember = new ProjectMember();
      newProjectMember.setProjectIdx(project.getProjectIdx());
      newProjectMember.setUserIdx(newMember.getUserIdx());
      newProjectMember.setAuthIdx(authIdx);
      newProjectMember.setNickname(newMember.getNickname());
      newProjectMember.setProfileColor(newMember.getProfileColor());
      newProjectMember.setIsOk(0);

      projectRepository.insertProjectMember(newProjectMember);

      MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
      body.add("mailTemplate", "project-invite-mail");
      body.add("nickname", newMember.getNickname());
      body.add("projectIdx", project.getProjectIdx());
      body.add("userIdx", newMember.getUserIdx());

      // RestTemplate??? ?????? Content-type : application/json
      RequestEntity<MultiValueMap<String, String>> request = RequestEntity.post(Config.DOMAIN.DESC + "/mail")
            .accept(MediaType.APPLICATION_FORM_URLENCODED).body(body);

      String subject = project.getProName() + " ?????????????????? ????????? ???????????????.";
      String htmlTxt = http.exchange(request, String.class).getBody();
      mailSender.send(newMember.getEmail(), subject, htmlTxt);

   }

   // ????????? ?????????
   @Override
   public int inviteMemberByEmailImpl(String projectIdx, String userIdx) {

      return projectRepository.updateProjectMemberStatus(projectIdx, userIdx);

   }

   @Override
   public Project selectProjectByInviteCode(String inviteCode) {
      return projectRepository.selectProjectByInviteCode(inviteCode);

   }

   public void inviteMemberByCode(String inviteCode, Member member) {

      Project project = projectRepository.selectProjectByInviteCode(inviteCode);
      if (project == null) {
         throw new HandlableException(ErrorCode.PROJECT_URL_ERROR);
      }

      List<Map<String, Object>> projectMemberList = projectRepository
            .selectProjectMemberByProjectIdx(project.getProjectIdx());
      System.out.println(projectMemberList);
      for (Map<String, Object> pm : projectMemberList) {

         if (pm.get("USER_IDX").equals(member.getUserIdx())) {
            System.out.println("?????? ???????????? ??????????????????.");
            return;

         }

      }

      Map<String, Object> defaultRole = CamelMap
            .changeMap(projectRepository.selectDefaultOfProjectRole(project.getProjectIdx()));
      String authIdx = (String) defaultRole.get("authIdx"); // ?????? idx??? ????????????

      ProjectMember projectMember = new ProjectMember();
      projectMember.setProjectIdx(project.getProjectIdx());
      projectMember.setUserIdx(member.getUserIdx());
      projectMember.setAuthIdx(authIdx);
      projectMember.setNickname(member.getNickname());
      projectMember.setProfileColor(member.getProfileColor());
      projectMember.setIsOk(1);

      projectRepository.insertProjectMember(projectMember);

   }

   public void deleteProjectMember(Map<String, String> map) {

      projectRepository.deleteProjectMember(map);

   };

   public void updateProjectMemberAuth(Map<String, String> map) {

      projectRepository.updateProjectMemberAuth(map);

   };

   // ?????? ???????????? ???

   // ?????? ?????? ??????
   @Override
   public List<ProjectRole> selectProjectRoleByIdx(String projectIdx) {

      return projectRepository.selectProjectRoleByIdx(projectIdx);
   }
   
   @Override
   public void updateRoleByPrevAuthName(Map<String, String> map) {
      projectRepository.updateRoleByPrevAuthName(map);
      
   }


   @Override
   public void insertNewRole(ProjectRole role) {
      projectRepository.insertNewRole(role);

   }

   @Override
   public void deleteRoleByAuthIdx(String authIdx) {
      projectRepository.deleteRoleByAuthIdx(authIdx);

   }

   @Override
   public void deleteRoleByProjectIdxAndAuthName(ProjectRole role) {
      projectRepository.deleteRoleByProjectIdxAndAuthName(role);
   }
   
   //11/17 ??????
   @Override
   public String updateProjectByProjectIdx(Map<String, String> project) {
      System.out.println("service ::" + project);
      if(project.get("nameState").equals("update") || project.get("descriptionState").equals("update") ) {
         projectRepository.updateProjectByProjectIdx(project);
         return "??????";
      }else {
         return "????????? ??????.";      
         }
   }
   //11/18 ??????
   @Override
   public void updateIsDelProjectByProjectIdx(String projectIdx) {
      projectRepository.updateIsDelProjectByProjectIdx(projectIdx);
   }
   
   @Override
   public int projectIsDel(String projectIdx) {
      
      
      return projectRepository.projectIsDel(projectIdx);
   }
   
   @Override
   public ProjectRole selectProjectRoleByProjectIdxAndUserIdx(String projectIdx, String userIdx) {
      return projectRepository.selectProjectRoleByProjectIdxAndUserIdx(projectIdx,userIdx);
   }

   @Override
   public void insertProjectImg(FileDTO fileUploaded, String projectIdx) {
      projectRepository.insertProjectImg(fileUploaded,projectIdx);
      
   }

   @Override
   public FileDTO selectProjectImgSavePath(String projectIdx) {
      return projectRepository.selectProjectImgSavePath(projectIdx);
   }

   @Override
   public FileDTO updateProjectImg(FileDTO fileUploaded,String projectIdx) {
      projectRepository.updateProjectImg(fileUploaded,projectIdx);
      return fileUploaded;
   }

   @Override
   public void deleteProjectImg(String projectIdx) {
      projectRepository.deleteProjectImg(projectIdx);
   }

   @Override
   public List<Map<String, Object>> selectProjectAndProjectImgByUserIdx(String userIdx) {
      return projectRepository.selectProjectAndProjectImgByUserIdx(userIdx);
   }

   // ?????? ?????? ???
/////////////////????????? ????????? ?????? ??????
   @Override
   public List<Project> selectProjectByUserIdx(String userIdx) {

      return projectRepository.selectProjectByUserIdx(userIdx);
   }

   @Override
   public String insertProject(String proName, String proDescription, String inviteCode, String userIdx) {
      Project project = new Project();
      project.setProName(proName);
      project.setProDescription(proDescription);
      project.setInviteCode(inviteCode);
      
      projectRepository.insertProject(project);
      projectRepository.insertRole();
      projectRepository.insertAdmin(userIdx);
      
      //????????????..????????????.
      System.out.println("000000000000" + project.getProjectIdx());
      return project.getProjectIdx();
      
      
   }

   @Override
   public List<String> selectProjectIdxByUserIdx(String userIdx) {
      return projectRepository.selectProjectIdxByUserIdx(userIdx);
   }

   @Override
   public List<Project> selectProjectByProjectIdx(String projectIdx) {
      return projectRepository.selectProjectByProjectIdx(projectIdx);
   }

   @Override
   public Project selectProjectExist(String projectIdx) {
      return projectRepository.selectProjectExist(projectIdx);
   }

   @Override
   public List<Workspace> selectWorkspaceByProjectIdx(String projectIdx) {
      return projectRepository.selectWorkspaceByProjectIdx(projectIdx);
   }


   //11??? 24??? ?????? ??????
   @Override
   public void updateWorkspace(List<Map<String, Object>> workspaceList, String projectIdx) {//???????????? ??????(open,close)
      int sort = 1;

      if(workspaceList.isEmpty()) {
    	  return;
      }
      for (Map<String, Object> map : workspaceList) {
    	  String wsIdx = map.get("workWsIdx").toString();
          String wsType = map.get("workOption").toString();
          String wsName = map.get("workWrite").toString();
          String wsState = map.get("workState").toString();

          if (wsState.equals("none")) {// ????????? ????????? ?????????, ??????. (UPDATE)
              projectRepository.updateWorkspace(wsIdx, wsName,sort);
              sort++;
           } else if (wsState.equals("hide")) {// ???????????? hide?????? ?????????, (DELETE)
              projectRepository.deleteWorkspace(wsIdx);
           } else if (wsState.equals("insert")) {// ???????????? ?????? ???????????? ????????? (INSERT)
              projectRepository.insertWorkspace(wsIdx,wsType, wsName, sort, projectIdx);
              sort++;
           }
        }
        // ?????? data ?????? ??????
      	projectRepository.deleteNonWorkspace(sort, projectIdx);
      	  
     }

		@Override
		public Map<String, Object> selectProjectMainData(String projectIdx) {
			Map<String, Object> mainData = new HashMap<String, Object>();

			List<Map<String, Object>> workspace = CamelMap.changeListMap(projectRepository.selectWorkspaceListByProjectIdx(projectIdx));
			List<Map<String,Object>> mainMemoList = CamelMap.changeListMap(memoRepository.selectMemoByTop(projectIdx));
	         List<Map<String,Object>> postList = CamelMap.changeListMap(boardRepository.selectBoardByTop(projectIdx));
			List<Map<String, Object>> replyList =CamelMap.changeListMap(boardRepository.selectReplyByTop(projectIdx));
			List<Calendar> calendarList = calendarRepository.selectCalendarListByProjectIdx(projectIdx);

			
			mainData.put("workspace", workspace);
			mainData.put("mainMemoList", mainMemoList);
			mainData.put("postList", postList);
			mainData.put("replyList", replyList);
			mainData.put("calendarList", calendarList);
			
			return mainData;
		}

      @Override
      public List<Map<String, Object>> selectWorkspaceListByProjectIdx(String projectIdx) {

         return projectRepository.selectWorkspaceListByProjectIdx(projectIdx);
      }
      
      @Override
  	public List<String> selectCashListByProjectIdx(String projectIdx) {
  		return projectRepository.selectCashListByProjectIdx(projectIdx);
  	}

//????????? ????????? ?????? ???

/////??????+??????
      @Override
      public List<Map<String, String>> selectProjectNickname(String projectIdx, String userIdx) {
         return projectRepository.selectProjectNickname(projectIdx, userIdx);
      }
      
      //???????????? ??????

	
	@Override
	public int updateMemberByNickname(Member member,String projectIdx) {
		// TODO Auto-generated method stub
		return projectRepository.updateMemberByNickname(member,projectIdx);
	}

	@Override
	public FileDTO selectProfileImgFilebyMemberIdx(Member dummyMember) {
		// TODO Auto-generated method stub
		return projectRepository.selectProfileImgFilebyMemberIdx(dummyMember);
	}


	@Override
	public Member selectProjectMemberByUserIdx(String userIdx, String projectIdx) {
		// TODO Auto-generated method stub
		return projectRepository.selectProjectMemberByUserIdx(userIdx,projectIdx);
	}

	@Override
	public int insertProfileImg(FileDTO fileUploaded, String userIdx, String projectIdx) {
		// TODO Auto-generated method stub
		return projectRepository.insertProfileImg(fileUploaded,userIdx,projectIdx);
	}

	@Override
	public int updateMemberByProfileColor(Member tempMember, String projectIdx) {
		// TODO Auto-generated method stub
		return projectRepository.updateMemberByProfileColor(tempMember,projectIdx);
	}

	@Override
	public int updateProjectIsLeave(Member member, String projectIdx) {
		// TODO Auto-generated method stub
		return projectRepository.updateProjectIsLeave(member,projectIdx);
	}

	
	//????????????
	@Override
	public Workspace selectWorkspaceByWsIdx(String wsIdx) {
		// TODO Auto-generated method stub
		return projectRepository.selectWorkspaceByWsIdx(wsIdx);
	}

	
	
	@Override
	public ProjectMember selectProjectMemberByProjectIdxAndUserIdx(String projectIdx, String userIdx) {
		
		return projectRepository.selectProjectMemberByProjectIdxAndUserIdx(projectIdx , userIdx);
	}

/////////?????? ??????
	@Override
	public Map<String, Object> selectMemberManagementByProjectIdx(String projectIdx) {
			Map<String, Object> memberData = new HashMap<String, Object>();
			
			List<ProjectRole> projectRoleList = projectRepository.selectProjectRoleByIdx(projectIdx);
			List<Map<String, Object>> projectMemberList = CamelMap.changeListMap(projectRepository.selectProjectMemberRoleByProjectIdx(projectIdx));
	      
	      memberData.put("projectRoleList", projectRoleList);
	      memberData.put("projectMemberList", projectMemberList);
	      
	      return memberData;
	}



	



	
     
	
   }