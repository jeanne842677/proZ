package com.kh.spring.projectmember.controller;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import com.kh.spring.member.model.dto.Member;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		
		
		System.out.println("MyStompSessionHandler실행");
		Member member = new Member();
		member.setUserIdx("100021");
		session.send("/online/project/999998", member);
		System.out.println("My Stomp 어쩌구 샌딩함");
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
			Throwable exception) {
		System.out.println("예외발생");
		}


	
	
	
	
	
	
}
