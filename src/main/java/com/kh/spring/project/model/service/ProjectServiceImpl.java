package com.kh.spring.project.model.service;

import java.util.ArrayList;
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

import com.kh.spring.common.code.Config;
import com.kh.spring.common.code.ErrorCode;
import com.kh.spring.common.code.WorkspaceType;
import com.kh.spring.common.exception.HandlableException;
import com.kh.spring.common.mail.MailSender;
import com.kh.spring.common.util.file.FileDTO;
import com.kh.spring.common.util.map.CamelMap;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.repository.MemberRepository;
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

   // 지영 추가 코드

   @Override
   public List<Map<String, Object>> selectUserAndMemberByEmail(String email) {

      return CamelMap.changeListMap(projectRepository.selectUserAndMemberByEmail(email));

   }

   @Override
   public void inviteMemberByEmail(Member newMember, Project project) {

      Map<String, Object> defaultRole = CamelMap
            .changeMap(projectRepository.selectDefaultOfProjectRole(project.getProjectIdx()));
      String authIdx = (String) defaultRole.get("authIdx"); // 일반 idx가 들어있음

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

      // RestTemplate의 기본 Content-type : application/json
      RequestEntity<MultiValueMap<String, String>> request = RequestEntity.post(Config.DOMAIN.DESC + "/mail")
            .accept(MediaType.APPLICATION_FORM_URLENCODED).body(body);

      String subject = project.getProName() + " 프로젝트에서 당신을 초대합니다.";
      String htmlTxt = http.exchange(request, String.class).getBody();
      mailSender.send(newMember.getEmail(), subject, htmlTxt);

   }

   // 이메일 클릭시
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
            System.out.println("이미 존재하는 사용자입니다.");
            return;

         }

      }

      Map<String, Object> defaultRole = CamelMap
            .changeMap(projectRepository.selectDefaultOfProjectRole(project.getProjectIdx()));
      String authIdx = (String) defaultRole.get("authIdx"); // 일반 idx가 들어있음

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

   // 지영 추가코드 끝

   // 민협 코드 시작
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
   
   //11/17 민협
   @Override
   public String updateProjectByProjectIdx(Map<String, String> project) {
      System.out.println("service ::" + project);
      if(project.get("nameState").equals("update") || project.get("descriptionState").equals("update") ) {
         projectRepository.updateProjectByProjectIdx(project);
         return "바뀜";
      }else {
         return "바뀐게 없음.";      
         }
   }
   //11/18 민협
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

   // 민협 코드 끝
////////////은비가 작성한 코드 시작
   @Override
   public List<Project> selectProjectByUserIdx(String userIdx) {

      return projectRepository.selectProjectByUserIdx(userIdx);
   }

   @Override
   public int insertProject(String proName, String proDescription, String inviteCode, String userIdx) {
      Project project = new Project();
      project.setProName(proName);
      project.setProDescription(proDescription);
      project.setInviteCode(inviteCode);
      int res = projectRepository.insertProject(project);
      projectRepository.insertRole();
      projectRepository.insertAdmin(userIdx);
      
      
      System.out.println("서비스 : 제발 되어라" + project.getProjectIdx());
      return res;
      
      
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


   //11월 24일 은비 추가
   @Override
   public void settingWorkspace(List<Map<String, Object>> workspaceList, String projectIdx) {//트랜잭션 문제(open,close)
      int sort = 1;
      System.out.println("projectIdx" + projectIdx);
      System.out.println("workspaceList"+workspaceList);
      List<Map<String,Object>> updateList = new ArrayList<Map<String,Object>>();
      List<Map<String,Object>> deleteList = new ArrayList<Map<String,Object>>();
      List<Map<String,Object>> insertList = new ArrayList<Map<String,Object>>();
      
      for (Map<String, Object> map : workspaceList) {
         
         String wsState = (String) map.get("workState");
      
         
         if (wsState.equals("none")) {// 변경된 내역이 있다면, 변경. (UPDATE)
        	 map.put("sort", sort);
        	 updateList.add(map);
             sort++;
            } else if (wsState.equals("hide")) {// 리스트가 hide된게 있으면, (DELETE)
             deleteList.add(map);
            } else if (wsState.equals("insert")) {// 리스트가 새로 생성됬을 경우에 (INSERT)
             map.put("sort",sort);
             insertList.add(map);
             sort++;
            }
         }
         // 더미 data 전부 삭제
			projectRepository.insertWorkspace(insertList);
			projectRepository.deleteWorkspace(deleteList);
			projectRepository.updateWorkspace(updateList);
			
			projectRepository.deleteNonWorkspace(sort);

		}

      @Override
      public List<Map<String, Object>> selectWorkspaceListByProjectIdx(String projectIdx) {

         return projectRepository.selectWorkspaceListByProjectIdx(projectIdx);
      }
      
      @Override
  	public List<String> selectCashListByProjectIdx(String projectIdx) {
  		return projectRepository.selectCashListByProjectIdx(projectIdx);
  	}

//은비가 작성한 코드 끝

/////윤지+예진
      @Override
      public List<Map<String, String>> selectProjectNickname(String projectIdx, String userIdx) {
         return projectRepository.selectProjectNickname(projectIdx, userIdx);
      }
      
      //윤지코드 시작

	
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

	
	//지영코드
	@Override
	public Workspace selectWorkspaceByWsIdx(String wsIdx) {
		// TODO Auto-generated method stub
		return projectRepository.selectWorkspaceByWsIdx(wsIdx);
	}

	@Override
	public ProjectMember selectProjectMemberByProjectIdxAndUserIdx(String projectIdx, String userIdx) {
		
		return projectRepository.selectProjectMemberByProjectIdxAndUserIdx(projectIdx , userIdx);
	}

	



	
     
	
   }