package com.kh.spring.project.model.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.project.model.dto.Project;
import com.kh.spring.project.model.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	ProjectRepository projectRepository;

	@Override
	public void updateProjectInviteCode(String projectIdx) {
		
		String newUuid = UUID.randomUUID().toString();
		projectRepository.updateProjectInviteCode( newUuid , projectIdx);
		
		
	}

	@Override
	public Project selectProjectByIdx(String projectIdx) {
		
		
		return projectRepository.selectProjectByIdx(projectIdx);
	}
	

}
