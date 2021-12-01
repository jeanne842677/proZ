package com.kh.spring.chat.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.kh.spring.chat.model.dto.Chat;
import com.kh.spring.chat.model.repository.ChatRepository;

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

}
