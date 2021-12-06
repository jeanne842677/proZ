package com.kh.spring.projectmember.controller;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class StompSession implements ApplicationListener<SessionDisconnectEvent>{
	
	
	@Override
	public void onApplicationEvent(SessionDisconnectEvent event) {
		//System.out.println("<<<<연결 끊김>>>>");
    	//template.convertAndSend("/online/project/999998" , "disconnect");
	}
}
