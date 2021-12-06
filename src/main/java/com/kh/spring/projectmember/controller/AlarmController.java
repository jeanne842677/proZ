package com.kh.spring.projectmember.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.spring.projectmember.dto.Alarm;
import com.kh.spring.projectmember.model.service.ProjectMemberService;

@Controller
@RequestMapping("alarm")
public class AlarmController {

	@Autowired
	ProjectMemberService projectMemberService;
	
	
	@PostMapping("update/look")
	@ResponseBody
	public String updateLook(@RequestBody Alarm alarm) {
		
		
		System.out.println(alarm);
		projectMemberService.updateAlarmIsLook(alarm);
		
		return "complete";
	}
	
	
	
}
