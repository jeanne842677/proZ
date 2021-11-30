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
			projectMap.put(projectIdx , members);
			
		}else {
			
			System.out.println("멤버가 이미 있을 때");
			for(ProjectMemberSession member : members) {
				
				if(member.getUserIdx().equals(pms.getUserIdx())) {
					return;
				}
				
			}
			
			
		}
		
		
		
		members.add(pms);
		System.out.println("현재 프로젝트 리스트: " + projectMap);
		
	}



	public Map<String, List<ProjectMemberSession>> getProjectMap() {
		return projectMap;
	}
	
	public List<ProjectMemberSession> getProjectMember(String projectIdx) {
		
		return projectMap.get(projectIdx);
	}
	
	public ProjectMemberSession removeList(String projectIdx , String userIdx ) {
		
		
		
		List<ProjectMemberSession> pmsList = projectMap.get(projectIdx);
		ProjectMemberSession thisPms = null;
		
		System.out.println("지금 확인하는 리무브 단: " + pmsList);
		
		for(ProjectMemberSession pms:pmsList) {
			
			if(pms.getUserIdx().equals(userIdx)) {
				
				thisPms = pms;
				
			}
			
			
			
		}
		
		pmsList.remove(thisPms);
		
		return thisPms;
		
		
	}
	
	
	
	
}
