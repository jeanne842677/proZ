package com.kh.spring.projectmember.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.projectmember.dto.ProjectMemberSession;
import com.kh.spring.projectmember.model.repository.ProjectMemberRepository;

@Controller
public class ProjectMemberController {
	
	
	@Autowired
	ProjectMemberRepository pm;
	

    @MessageMapping("/project/{projectIdx}") //이 주소로 보낸 애들한테 메세지를 받음
    @SendTo("/online/project/{projectIdx}") //이 주소인 애한테 메세지를 보냄
    public Map<String, Object> sendChatMessage(@DestinationVariable("projectIdx") String projectIdx
    	,Member member, SimpMessageHeaderAccessor headerAccessor, Principal principal) {
        
    	ProjectMemberSession pms = new ProjectMemberSession( member.getUserIdx(), headerAccessor.getSessionId());
    	
    	Map<String, Object> res = new HashMap<>();
    	res.put("status", "online");
    	
    	
    	pm.putProject(projectIdx, pms);
    	
    	//System.out.println("================================");
    	
    	//System.out.println("projectIdx: " + projectIdx);
    	//System.out.println("인간 들어옴");
        //System.out.println(member);
        //System.out.println("===============================");
        
        //System.out.println(pm.getProjectMember(projectIdx));
        
        
        res.put("members" , pm.getProjectMember(projectIdx));
        return res;
        
        
    }
    
    
    @MessageMapping("/remove/{projectIdx}")
    @SendTo("/online/project/{projectIdx}") //이 주소인 애한테 메세지를 보냄
    public Map<String, Object>  disconnectMessage(@DestinationVariable("projectIdx") String projectIdx , Member member) {
    	

    	Map<String, Object> res = new HashMap<>();
    	res.put("status", "offline");
    	ProjectMemberSession pms = pm.removeList(projectIdx, member.getUserIdx());
    	res.put("member" , pms);
    	
    	//System.out.println("커넥션 끊어짐");
    	
    	//System.out.println(pm.getProjectMember(projectIdx));
    	
    	
    	return res;
    }
    
    
    

    
    

    


}
