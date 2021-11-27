package com.kh.spring.projectmember.model.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.kh.spring.project.model.dto.ProjectMember;

@Repository
public class ProjectMemberRepository {
	
	private Map<String , List<ProjectMember>> projectMap;
	
	
	@PostConstruct
	private void init() {
		
		projectMap = new HashMap();
		
	}
	
	
	
	private void putProject(String projectIdx , ProjectMember projectMember) {
		
		List<ProjectMember> members =projectMap.get(projectIdx);
		
		if(members==null) {
			
			members = new ArrayList<>();
			members.add(projectMember);
			projectMap.put(projectIdx , members);
			
		}else {
			members.add(projectMember);
		}
		
	}
	
	
	
}
