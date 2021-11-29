package com.kh.spring.chat.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.kh.spring.chat.model.dto.Chat;
import com.kh.spring.chat.model.service.ChatService;

@Controller
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
    @MessageMapping("/msg/{room}") //이 주소로 보낸 애들한테 메세지를 받음. 구독주소가 빠진거?
    @SendTo("/room/msg/{room}") //이 주소인 애한테 메세지를 보냄
    public Chat sendChatMessage(@DestinationVariable("room") String room
    	,Chat chat, SimpMessageHeaderAccessor headerAccessor, Principal principal) {
        
    	System.out.println("룸번호" + room);
    	System.out.println("채팅 메세지 들어옴");
    	chat.setContent("서버: "+chat.getContent()); //DTO 안의 컨텐츠 꺼내기
        System.out.println(chat);
        chatService.insertChat(chat);
        
        return chat; //response BODY 느낌
        

        
    }
    
    @GetMapping("/chat/chatting")
    public String chatting(Model model) {
    	String wsIdx = "100001";
    	
    	//오케이 받아짐 ㅋ 11-29
    	List<QueryDocumentSnapshot> chatList = chatService.selectAllMeassage(wsIdx);
    	List<Chat> pContent = new ArrayList<Chat>();
    	for (QueryDocumentSnapshot queryDocumentSnapshot : chatList) {
    		pContent.add(queryDocumentSnapshot.toObject(Chat.class));
		}
    	
    	

    	System.out.println("================ chatList : " + pContent);
    	return "/chat/chatting";
    }
    
    ///민협
    
    @GetMapping("/chat/chat2")
    public String chatting2() {
    	return "/chat/chat2";
    }
    


}
