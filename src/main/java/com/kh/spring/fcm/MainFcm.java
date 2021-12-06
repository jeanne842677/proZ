package com.kh.spring.fcm;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.kh.spring.chat.model.dto.Chat;

// 서버에 접속한 뒤 데이터 정리 (실시간 처리 안되서 코드 수정 필요)
public class MainFcm   {

	private FirebaseOptions option;
	private Firestore db;
	private final static String PATH = "C:\\CODE\\finalProz\\final\\src\\main\\webapp\\resources\\fcm\\proz-78541-firebase-json.json";
	private final static String COLLECTION_NAME="chat";
	
	public Chat returnChat(Chat chat) {
			MainFcm mainFcm = new MainFcm();
			try {
				mainFcm.init();
				mainFcm.makeDatabaseConn();
				mainFcm.select(chat);
				mainFcm.insert(chat);
				//mainFcm.update();
				//mainFcm.delete();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		
		return chat;
	}

	private void init() throws Exception {
		FileInputStream refreshToken = new FileInputStream(PATH);
		option = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(refreshToken))
				.setDatabaseUrl("https://proz-78641.firebaseio.com")
				.build();
		FirebaseApp.initializeApp(option);
	}

	private void makeDatabaseConn() {
		//Firestore 인스터스 생성
		db = FirestoreClient.getFirestore();
	}

	private void select(Chat chat)  {
		db.collection(COLLECTION_NAME).addSnapshotListener((target, exception)->{ //addSnapshotListener의 콜백
			System.out.println("--------select------------");
			target.forEach(item -> {
				System.out.println("primary id : " + item.getId() + " || value : " + item.getData());
			});
			System.out.println("--------select end-----------");
		});
	}

	private void insert(Chat chat) {//기록 남기면 되겠다.
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("ch_idx", "11111");
		item.put("ws_idx", "111111");
		item.put("content", chat.getContent());
		item.put("chName", chat.getChName());
		
		db.collection(COLLECTION_NAME).document("chat").set(item);
	}

	
}
