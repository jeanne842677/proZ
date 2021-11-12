package com.kh.spring.project.model.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.project.model.dto.Project;
import com.kh.spring.project.model.dto.ProjectMember;
import com.kh.spring.project.model.dto.ProjectRole;
import com.kh.spring.project.model.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRepository;

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

	@Override
	public List<ProjectRole> selectProjectRoleByIdx(String projectIdx) {

		return projectRepository.selectProjectRoleByIdx(projectIdx);
	}

	
////////////은비가 작성한 코드 시작
	@Override
	public ProjectMember selectProjectMemberByUserIdx(String userIdx) {

		return projectRepository.selectProjectMemberByUserIdx(userIdx);
	}

	@Override
	public Project createProject(String proName, String proDescription) {
		return projectRepository.createProject(proName, proDescription);
	}

	@Override
	public ProjectRole createRole(String projectIdx) {
		return projectRepository.createRole(projectIdx);
	}

	@Override
	public ProjectMember allocateAdmin(String projectIdx, String userIdx, int authIdx) {
		return projectRepository.allocateAdmin(projectIdx, userIdx, authIdx);
	}

//은비가 작성한 코드 끝

}
