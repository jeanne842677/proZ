package com.kh.spring.fcm;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

// 서버에 접속한 뒤 데이터 정리 (실시간 처리 안되서 코드 수정 필요)
public class MainFcm   {

	private FirebaseOptions option;
	private Firestore db;
	private final static String PATH = "/resources/fm/proz-78541-firebase-json.json";
	private final static String COLLECTION_NAME="데이터베이스에서 만든 이름(아직 생성 ㄴㄴ)";
	
	public static void main(String[] args) {
		MainFcm mainFcm = new MainFcm();
		try {
			mainFcm.init();
			mainFcm.makeDatabaseConn();
			mainFcm.select();
			mainFcm.insert();
			mainFcm.update();
			mainFcm.delete();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void init() throws Exception {
		FileInputStream refreshToken = new FileInputStream(PATH);
		option = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(refreshToken))
				.setDatabaseUrl("https://데이터베이스주소.firebaseio.com")
				.build();
		FirebaseApp.initializeApp(option);
	}

	private void makeDatabaseConn() {
		//Firestore 인스터스 생성
		db = FirestoreClient.getFirestore();
	}

	private void select()  {
		db.collection(COLLECTION_NAME).addSnapshotListener((target, exception)->{ //addSnapshotListener의 콜백
			System.out.println("--------select------------");
			target.forEach(item -> {
				System.out.println("primary id : " + item.getId() + " || value : " + item.getData());
			});
			System.out.println("--------select end-----------");
		});
	}

	private void insert() {
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("임의로 값을 넣어줘", 1234);
		item.put("실제로 넣어야 되는 값", "넣으면 되겠다.");
		db.collection(COLLECTION_NAME).add(item);
	}

	private void update() {
		Map<String, Object> update = new HashMap<String, Object>();
		update.put("ㅇㅇㅇ", 1522);
		update.put("채팅에는 필요 없을 것 같은데?", "이게 필요한가..? 아 채팅방 이름");
		db.collection(COLLECTION_NAME).document("문서 키 값").update(update);
	}
	
	private void delete() {
		db.collection(COLLECTION_NAME).document("문서 키 값").delete();
	}
}
