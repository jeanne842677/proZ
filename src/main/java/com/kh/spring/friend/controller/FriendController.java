package com.kh.spring.friend.controller;

import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.spring.chat.model.dto.Chat;
import com.kh.spring.fcm.MainFcm;

@Controller
public class FriendController {

//	@Autowired
//	MainFcm mainFcm;
	MainFcm mainFcm = new MainFcm();
	
    @MessageMapping("/msg/{room}") //이 주소로 보낸 애들한테 메세지를 받음. 구독주소가 빠진거?
    @SendTo("/room/msg/{room}") //이 주소인 애한테 메세지를 보냄
    public Chat sendChatMessage(@DestinationVariable("room") String room
    	,Chat chat, SimpMessageHeaderAccessor headerAccessor, Principal principal) {
        
    	System.out.println("룸번호" + room);
    	System.out.println("채팅 메세지 들어옴");
    	chat.setContent("서버: "+chat.getContent()); //DTO 안의 컨텐츠 꺼내기
        System.out.println(chat);
        
        mainFcm.returnChat(chat);
        
        return chat; //response BODY 느낌
        
        
    }
    
    @GetMapping("/chat/chatting")
    public String chatting() {
    	return "/chat/chatting";
    }
    
    ///민협
    
    @GetMapping("/chat/chat2")
    public String chatting2() {
    	return "/chat/chat2";
    }
    

    /*
     * public static void main(String[] args) {
		MainFcm mainFcm = new MainFcm();
		try {
			mainFcm.init();
			mainFcm.makeDatabaseConn();
			mainFcm.select();
			mainFcm.insert();
			mainFcm.update();
			mainFcm.delete();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
     */

}
