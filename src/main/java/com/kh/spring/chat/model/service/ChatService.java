package com.kh.spring.chat.model.service;

import java.util.List;

import com.kh.spring.chat.model.dto.Chat;

public interface ChatService {

	void insertChat(Chat chat);

	List<Chat> selectAllMeassage(String wsIdx);

}
