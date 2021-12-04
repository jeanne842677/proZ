package com.kh.spring.chat.model.repository;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Repository;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.kh.spring.chat.model.dto.Chat;

@Repository
public class ChatRepository {
	
	private Firestore db;
	
	
	public void insertChat(Chat chat) {
		db = FirestoreClient.getFirestore();
		
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("chName", chat.getChName());
		item.put("content", chat.getContent());
		item.put("regDate", chat.getRegDate());
		item.put("nickname", chat.getNickname());
		item.put("pmIdx", chat.getPmIdx());
		item.put("chatIdx", chat.getChIdx());
		item.put("dataUrl", chat.getDataUrl());
		item.put("fileType", chat.getFileType());
		item.put("isFile", chat.getIsFile());
		item.put("filePath", chat.getFilePath());
		
		//기적을 꿈꾸며...기적이 이루어졌다..11월30일..오후11시9분..
		db.collection(chat.getWsIdx()).document(String.valueOf(chat.getChIdx())).set(item);
	}


	public List<Chat> selectAllMeassage(String wsIdx)   {
		db = FirestoreClient.getFirestore();
		
		ApiFuture<QuerySnapshot> future =
			    db.collection(wsIdx).orderBy("chatIdx").get();
		List<QueryDocumentSnapshot> documents = null;
		List<Chat> chatList = new ArrayList<Chat>();
		
		try {
			documents = future.get().getDocuments();
			for (DocumentSnapshot document : documents) {
				chatList.add(document.toObject(Chat.class));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return chatList;
	}

}
