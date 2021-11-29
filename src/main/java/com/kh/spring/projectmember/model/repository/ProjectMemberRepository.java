package com.kh.spring.projectmember.model.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.project.model.dto.ProjectMember;
import com.kh.spring.projectmember.dto.ProjectMemberSession;

@Repository
public class ProjectMemberRepository {
	
	private Map<String , List<ProjectMemberSession>> projectMap;
	
	
	@PostConstruct
	private void init() {
		
		projectMap = new HashMap<>();
		
	}
	
	
	
	public void putProject(String projectIdx , ProjectMemberSession pms) {
		
		List<ProjectMemberSession> members =projectMap.get(projectIdx);
		
		if(members==null) {
			
			System.out.println("멤버가 널일 때");
			members = new ArrayList<>();
			members.add(pms);
			projectMap.put(projectIdx , members);
			
		}else {
			
			System.out.println("멤버가 이미 있을 때");
			members.add(pms);
			
		}
		
		System.out.println("현재 프로젝트 리스트: " + projectMap);
		
	}



	public Map<String, List<ProjectMemberSession>> getProjectMap() {
		return projectMap;
	}
	
	public List<ProjectMemberSession> getProjectMember(String projectIdx) {
		
		return projectMap.get(projectIdx);
	}
	
	public void removeList(String projectIdx , String session ) {
		List<ProjectMemberSession> pmsList = projectMap.get(projectIdx);
		for(ProjectMemberSession pms:pmsList) {
			if(pms.getSession().equals(session)) {
				pmsList.remove(pms);
			}
			
			
			
		}
		
	}
	
	
	
	
}
