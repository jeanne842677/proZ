package com.kh.spring.fcm;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseConfig {
	private FirebaseOptions option;
	private final static String PATH = "C:\\CODE\\z_final_Project\\src\\main\\webapp\\resources\\fcm\\proz-78541-firebase-json.json";
	
	@PostConstruct
	private void init() throws Exception {
		FileInputStream refreshToken = new FileInputStream(PATH);
		option = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(refreshToken))
				.setDatabaseUrl("https://proz-78641.firebaseio.com")
				.build();
		FirebaseApp.initializeApp(option);
	}
}
