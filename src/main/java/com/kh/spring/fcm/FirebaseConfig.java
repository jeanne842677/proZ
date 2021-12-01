package com.kh.spring.fcm;

import java.io.File;
import java.io.FileInputStream;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import io.grpc.internal.SharedResourceHolder.Resource;

@Configuration
public class FirebaseConfig {
	private FirebaseOptions option;
	private String PATH = "resources\\fcm\\proz-78541-firebase-json.json";
	
	
	@PostConstruct
	private void init() throws Exception {
		
		
		
		  String rootPath = this.getClass().getResource("/").getPath()+ "../../" +PATH;
		  
		  
		  FileInputStream refreshToken = new FileInputStream(rootPath); option = new
		  FirebaseOptions.Builder()
		  .setCredentials(GoogleCredentials.fromStream(refreshToken))
		  .setDatabaseUrl("https://proz-78641.firebaseio.com") .build();
		  FirebaseApp.initializeApp(option);
		 
	}
	
	
	
}
