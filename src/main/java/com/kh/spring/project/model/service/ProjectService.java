package com.kh.spring.project.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring.project.model.dto.Project;
import com.kh.spring.project.model.dto.ProjectMember;
import com.kh.spring.project.model.dto.ProjectRole;

public interface ProjectService {

	String updateProjectInviteCode(String projectIdx);

	Project selectProjectByIdx(String projectIdx);

	List<Map<String , Object>> selectProjectMemberByProjectIdx(String projectIdx);


	List<Map <String , Object>> selectProjectMemberRoleByProjectIdx(String projectIdx);
	
	   /////////////////은비가 작성한 코드 시작
	   ProjectMember selectProjectMemberByUserIdx(String userIdx);

	   Project createProject(String proName, String proDescription);

	   ProjectRole createRole(String projectIdx);

	   ProjectMember allocateAdmin(String projectIdx, String userIdx, int authIdx);
	   
	   
	   ///////////////은비가 작성한 코드 끝
	   
	   List<ProjectRole> selectProjectRoleByIdx(String projectIdx);
	
}
