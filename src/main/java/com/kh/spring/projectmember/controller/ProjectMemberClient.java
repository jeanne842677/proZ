package com.kh.spring.projectmember.controller;

import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class ProjectMemberClient {
	
	public void connect() {
		
		WebSocketClient webSocketClient = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
		stompClient.setMessageConverter(new StringMessageConverter());
		
		
		String url = "ws://localhost:9090/ws-stomp";
		StompSessionHandler sessionHandler = new MyStompSessionHandler();
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.afterPropertiesSet();
		stompClient.setTaskScheduler(taskScheduler);
		stompClient.connect(url, sessionHandler);
		
		System.out.println("아무일..");
		
	}
	
	public static void main(String[] args) {
		
		ProjectMemberClient p = new ProjectMemberClient();
		System.out.println("실행");
		//p.connect();
	}
	

}
