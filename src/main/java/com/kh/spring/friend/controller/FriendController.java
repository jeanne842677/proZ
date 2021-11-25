package com.kh.spring.friend.controller;

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
public class FriendController {

    @MessageMapping("/msg/{room}") //이 주소로 보낸 애들한테 메세지를 받음
    @SendTo("/room/msg/{room}") //이 주소인 애한테 메세지를 보냄
    public Chat sendChatMessage(@DestinationVariable("room") String room
    	,Chat chat, SimpMessageHeaderAccessor headerAccessor, Principal principal) {
        
    	
    	System.out.println("룸번호" + room);
    	System.out.println("채팅 메세지 들어옴");
    	chat.setContent("서버: "+chat.getContent());
        System.out.println(chat);
        return chat;
        
        
    }
    
    @GetMapping
    public String chatting() {
    	return "/chat/chatting";
    }
    


}
