package com.kh.spring.projectmember.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.chat.model.dto.Chat;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.projectmember.dto.ProjectMemberSession;
import com.kh.spring.projectmember.model.repository.ProjectMemberRepository;

@Controller
public class ProjectMemberController {
	
	
	@Autowired
	ProjectMemberRepository pm;

 //   @MessageMapping("/project/{projectIdx}") //이 주소로 보낸 애들한테 메세지를 받음
  //  @SendTo("/online/project/{projectIdx}") //이 주소인 애한테 메세지를 보냄
    public List<ProjectMemberSession> sendChatMessage(@DestinationVariable("projectIdx") String projectIdx
    	,Member member, SimpMessageHeaderAccessor headerAccessor, Principal principal) {
        
    	ProjectMemberSession pms = new ProjectMemberSession( member.getUserIdx(), headerAccessor.getSessionId());
    	
    	
    	if(member.getUserIdx().equals("test")) {
    		
    		System.out.println("이거실행....");
    	}
    	
    	
    	pm.putProject(projectIdx, pms);
    	
    	System.out.println("================================");
    	
    	System.out.println("projectIdx: " + projectIdx);
    	System.out.println("인간 들어옴");
    	System.out.println("컨트롤러 안 세션:" + headerAccessor.getSessionId());
        System.out.println(member);
        System.out.println("===============================");
        
        return pm.getProjectMember(projectIdx);
        
        
    }
    

    
    

    


}
