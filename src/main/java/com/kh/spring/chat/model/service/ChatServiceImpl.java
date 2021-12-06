package com.kh.spring.chat.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.kh.spring.chat.model.dto.Chat;
import com.kh.spring.chat.model.repository.ChatRepository;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.project.model.dto.Workspace;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	ChatRepository chatRepository;
	
	@Override
	public void insertChat(Chat chat) {
		chatRepository.insertChat(chat);
	}

	@Override
	public List<Chat> selectAllMeassage(String wsIdx) {
		
		return chatRepository.selectAllMeassage(wsIdx);
	}

	@Override
	public void showChattingList(HttpServletRequest request, Member member, String wsIdx, Model model) {
		
		@SuppressWarnings("unchecked")
	      List<Workspace> workspaceList = (List<Workspace>) request.getAttribute("workspaceList");
	       Workspace workspace = new Workspace();
	       for (Workspace ws : workspaceList) {//workspaceList중 현재 접속한 ws찾기
	         if(ws.getWsIdx().equals(wsIdx)) {
	            workspace = ws;
	         }
	      }
	       
	       @SuppressWarnings("unchecked")
	      List<Map<String, Object>> projectMemberList = (List<Map<String, Object>>) request.getAttribute("projectMemberList");
	       
	       Map<String, Object> projectMember = new HashMap<String, Object>();
	       for (Map<String, Object> pm : projectMemberList) {//projectMember중 현재 접속한 pm 찾기
	         if(pm.get("userIdx").equals(member.getUserIdx())) {
	            projectMember = pm;
	         }
	      }
	       
	       List<Chat> chatList = chatRepository.selectAllMeassage(wsIdx);
	       

	       model.addAttribute("workspace",workspace);
	       model.addAttribute("chatList", chatList);
	       model.addAttribute("length", chatList.size());
	       model.addAttribute("projectMember", projectMember);
	       
	      
	       System.out.println("============================workspaceList : " + workspaceList);
	       System.out.println("================ chatList : " + chatList);
	       System.out.println("================ projectMember : " + projectMember);
	}

}
