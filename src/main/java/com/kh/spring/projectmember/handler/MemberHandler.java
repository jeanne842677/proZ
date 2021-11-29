package com.kh.spring.projectmember.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.projectmember.dto.ProjectMemberSession;
import com.kh.spring.projectmember.model.repository.ProjectMemberRepository;

public class MemberHandler implements ChannelInterceptor {

	@Autowired
	ProjectMemberRepository pm;

	@Override
	public Message preSend(Message<?> message, MessageChannel channel) {
		
		System.out.println("멤버 핸들러");
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		StompCommand command = accessor.getCommand();
		System.out.println(message.getHeaders());

		System.out.println("유저 세션: " + accessor.getSessionId());

		String destination = accessor.getDestination();

		if (command.compareTo(StompCommand.SUBSCRIBE) == 0) {

			System.out.println("구독 주소 : " + destination);
			System.out.println(message);

		} else if (command.compareTo(StompCommand.CONNECT) == 0) {

			System.out.println("사용자 연결");
			/*
			 * Member member = new Member(); member.setUserIdx("test"); Message<Member> msg
			 * = MessageBuilder.createMessage(member, message.getHeaders());
			 * 
			 * channel.send(msg);
			 */
			 
			/*
			 * Map<String, Object> map = new HashMap(); map.put("simpMessageType",
			 * "MESSAGE"); map.put("stompCommand", "SEND");
			 * 
			 * Map<String, String> nav = new HashMap(); nav.put("destination",
			 * "/app/project/999998");
			 */
			//MessageHeaders mh = new MessageHeaders(map);

		} else if (command.compareTo(StompCommand.DISCONNECT) == 0) {

			String[] url = destination.split("/");
			ProjectMemberSession pmm = new ProjectMemberSession("그냥", "해봄");
			if (url[0].equals("online")) {
				String projectIdx = url[url.length - 1];
				String session = accessor.getSessionId();

				pm.removeList(projectIdx, session);

			}

			System.out.println("사용자 연결 해제");

		}
		return message;
	}

}
