package com.kh.spring.project.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring.common.util.file.FileDTO;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.project.model.dto.Project;
import com.kh.spring.project.model.dto.ProjectMember;
import com.kh.spring.project.model.dto.ProjectRole;
import com.kh.spring.project.model.dto.Workspace;

public interface ProjectService {

   String updateProjectInviteCode(String projectIdx);

   Project selectProjectByIdx(String projectIdx);

   List<Map<String, Object>> selectProjectMemberByProjectIdx(String projectIdx);

   List<Map<String, Object>> selectProjectMemberRoleByProjectIdx(String projectIdx);

   // 지영 추가코드

   List<Map<String, Object>> selectUserAndMemberByEmail(String email);

   void inviteMemberByEmail(Member newMember, Project project);

   int inviteMemberByEmailImpl(String projectIdx, String userIdx);

   List<ProjectRole> selectProjectRoleByIdx(String projectIdx);

   Project selectProjectByInviteCode(String inviteCode);

   public void inviteMemberByCode(String inviteCode, Member member);

   void deleteProjectMember(Map<String, String> map);

   void updateProjectMemberAuth(Map<String, String> map);

   // 지영 추가코드 끝

   // 민협 코드 시작

   void updateRoleByPrevAuthName(Map<String, String> map);

   void insertNewRole(ProjectRole role);

   void deleteRoleByAuthIdx(String authIdx);

   void deleteRoleByProjectIdxAndAuthName(ProjectRole role);
   
   //11-18민협
   void updateIsDelProjectByProjectIdx(String projectIdx);
   
   int projectIsDel(String projectIdx);

   // 민협 코드 끝

/////////////////은비가 작성한 코드 시작
   List<Project> selectProjectByUserIdx(String userIdx);

   int insertProject(String proName, String proDescription, String inviteCode, String userIdx);

   List<String> selectProjectIdxByUserIdx(String userIdx);

   List<Project> selectProjectByProjectIdx(String projectIdx);

   Project selectProjectExist(String projectIdx);

   String updateProjectByProjectIdx(Map<String, String> project);

   ProjectRole selectProjectRoleByProjectIdxAndUserIdx(String projectIdx, String userIdx);

   List<Workspace> selectWorkspaceByProjectIdx(String projectIdx);

   void settingWorkspace(List<Map<String, String>> workspaceList, String projectIdx);

   List<Map<String, Object>> selectWorkspaceListByProjectIdx(String projectIdx);

   
/////////////윤지 + 예진
   
   List<Map<String, String>> selectProjectNickname(String projectIdx, String userIdx);
   
 //윤지코드
   



	
	FileDTO selectProfileImgFilebyMemberIdx(Member member);

	int insertProfileImg(FileDTO fileUploaded, String userIdx);

	int updateMemberByProfileColor(Member tempMember);


	Member selectProjectMemberByUserIdx(String userIdx, String projectIdx);

	int updateMemberByNickname(Member member, String projectIdx);

	

	
	
   


   





///////////////은비가 작성한 코드 끝

}