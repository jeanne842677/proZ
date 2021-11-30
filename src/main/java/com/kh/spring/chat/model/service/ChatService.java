package com.kh.spring.chat.model.service;

import java.util.List;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.kh.spring.chat.model.dto.Chat;

public interface ChatService {

	void insertChat(Chat chat);

	List<QueryDocumentSnapshot> selectAllMeassage(String wsIdx);

}
