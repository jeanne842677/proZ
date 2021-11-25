package com.kh.spring.friend.controller;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.chat.model.dto.Chat;

@Controller
public class FriendController {

    @MessageMapping("/message/{room}")
    @SendTo("/projectIdx/online/{room}")
    public Chat sendChatMessage(@DestinationVariable("room") String room
    	,Chat message, SimpMessageHeaderAccessor headerAccessor, Principal principal) {
        
    	
    	System.out.println("룸번호" + room);
    	System.out.println("채팅 메세지 들어옴");
        message.setContent("서버: "+message.getContent());
        System.out.println(message);
        return message;
        
    }
    


}
