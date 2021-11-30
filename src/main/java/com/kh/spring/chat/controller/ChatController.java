package com.kh.spring.chat.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.kh.spring.chat.model.dto.Chat;
import com.kh.spring.chat.model.service.ChatService;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.project.model.dto.ProjectMember;

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
        System.out.println(chat);
        chatService.insertChat(chat);
        
        return chat; //response BODY 느낌
        

        
    }
    
    @GetMapping("/chat/chat2")
    public String chatting(Model model, 
				    		@Param(value = "wsIdx") String wsIdx ,
				    		@SessionAttribute("authentication") Member member
				    			) {
    	
    	//오케이 받아짐 ㅋ 11-29
    	ProjectMember projectMember = (ProjectMember) model.getAttribute("projectMember");
    	
    	List<QueryDocumentSnapshot> comandMap = chatService.selectAllMeassage(wsIdx);
    	List<Chat> chatList = new ArrayList<Chat>();
    	for (QueryDocumentSnapshot queryDocumentSnapshot : comandMap) {
    		chatList.add(queryDocumentSnapshot.toObject(Chat.class));
		}
    	
    	model.addAttribute("chatList", chatList);
    	model.addAttribute("length", chatList.size());
    	model.addAttribute(projectMember);
    	
    	
    	System.out.println("================ chatList : " + chatList);
    	return "/chat/chat2";
    }
    



}
