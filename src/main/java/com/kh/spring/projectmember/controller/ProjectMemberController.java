package com.kh.spring.projectmember.controller;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.chat.model.dto.Chat;

@Controller
public class ProjectMemberController {

    @MessageMapping("/project/{projectIdx}") //이 주소로 보낸 애들한테 메세지를 받음
    @SendTo("/online/project/{projectIdx}") //이 주소인 애한테 메세지를 보냄
    public Chat sendChatMessage(@DestinationVariable("projectIdx") String projectIdx
    	,Chat chat, SimpMessageHeaderAccessor headerAccessor, Principal principal) {
        
    	
    	System.out.println("projectIdx" + projectIdx);
    	System.out.println("인간 들어옴");
    	System.out.println("컨트롤러 안 세션:" + headerAccessor.getSessionId());
    	chat.setContent("서버: "+chat.getContent());
        System.out.println(chat);
        
        return chat;
        
        
    }
    

    


}
