package com.kh.spring.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.common.code.ErrorCode;
import com.kh.spring.common.exception.HandlableException;
import com.kh.spring.project.model.dto.Project;
import com.kh.spring.project.model.service.ProjectService;

@Controller
@RequestMapping("project")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	   

	
	@GetMapping("project-list")
	public void list() {}; 
	

	@GetMapping("welcome")
	public void welcome() {}; 

	//==========================================내가 작업=====================
	
	
	@GetMapping("setting/member-management/{projectIdx}")
	public String memberManagement(Model model , @PathVariable String projectIdx) {
		
		System.out.println("내가보낸 idx"+ projectIdx );
		//프로젝트Idx가 유효한지 확인한다
		Project project = projectService.selectProjectByIdx(projectIdx);
		//유효하면 requestAttribute에 넣는다. 
		
		System.out.println(project);
		
		//만약 프로젝트가 없으면?
		if(project==null) {
			
			System.out.println("프로젝트가 널값입니다.");
			throw new HandlableException(ErrorCode.PROJECT_URL_ERROR);
			
		}
		
		
		model.addAttribute(project);
		
		return "project/setting/member-management";
		
		
	}	
	
	
	@PostMapping("/invite-member")
	public String inviteMember(Model model) {
		
		
		
		return "";
	}
	
	@GetMapping("setting/role-management")
	public void settingRoleManagement() {};

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//==========================================내가 작업=====================

}
