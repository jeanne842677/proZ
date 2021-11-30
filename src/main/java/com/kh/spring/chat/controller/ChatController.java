package com.kh.spring.chat.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
    @MessageMapping("/msg/{wsIdx}") //이 주소로 보낸 애들한테 메세지를 받음. 구독주소가 빠진거?
    @SendTo("/room/msg/{wsIdx}") //이 주소인 애한테 메세지를 보냄
    public Chat sendChatMessage(@DestinationVariable("wsIdx") String wsIdx
    	,Chat chat, SimpMessageHeaderAccessor headerAccessor, Principal principal) {
        
    	System.out.println("룸번호" + wsIdx);
    	System.out.println("채팅 메세지 들어옴");
        System.out.println(chat);
        chatService.insertChat(chat);
        
        return chat; //response BODY 느낌
        

        
    }
    
    @GetMapping("/chat/chat2/{projectIdx}")
    public String chatting(Model model, 
				    		@Param(value = "wsIdx") String wsIdx ,
				    		@SessionAttribute("authentication") Member member,
				    		@PathVariable String projectIdx,
				    		HttpServletRequest request
				    			) {
    	
    	//오케이 받아짐 ㅋ 11-29
    	List<Map<String, Object>> projectMemberList = (List<Map<String, Object>>) request.getAttribute("projectMemberList");
    	//(List<Map<String, Object>>) model.getAttribute("projectMemberList");
    	Map<String, Object> projectMember = new HashMap<String, Object>();
    	
    	for (Map<String, Object> pm : projectMemberList) {
			if(pm.get("userIdx").equals(member.getUserIdx())) {
				projectMember = pm;
			}
		}
    	///리포지토리로 이동하자.
    	List<QueryDocumentSnapshot> comandMap = chatService.selectAllMeassage(wsIdx);
    	List<Chat> chatList = new ArrayList<Chat>();
    	for (QueryDocumentSnapshot queryDocumentSnapshot : comandMap) {
    		chatList.add(queryDocumentSnapshot.toObject(Chat.class));
		}
    	
    	model.addAttribute("chatList", chatList);
    	model.addAttribute("length", chatList.size());
    	model.addAttribute("projectMember", projectMember);
    	
		
    	
    	System.out.println("================ chatList : " + chatList);
    	System.out.println("================ projectMember : " + projectMember);
    	return "/chat/chat2";
    }
    



}
