package com.kh.spring.chat.model.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.kh.spring.chat.model.dto.Chat;
import com.kh.spring.member.model.dto.Member;

public interface ChatService {

	void insertChat(Chat chat);

	List<Chat> selectAllMeassage(String wsIdx);

	void showChattingList(HttpServletRequest request, Member member, String wsIdx, Model model);


}
