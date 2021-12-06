package com.kh.spring.projectmember.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dto.Post;
import com.kh.spring.board.model.repository.BoardRepository;
import com.kh.spring.common.util.map.CamelMap;
import com.kh.spring.projectmember.dto.Alarm;
import com.kh.spring.projectmember.model.repository.AlarmRepository;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {

	@Autowired
	AlarmRepository alarmRepository;
	
	@Autowired 
	BoardRepository boardRepository;
	
	@Override
	public Alarm updateReplyAlarm(Map<String, String> reply) {
		
		String replyUserIdx = reply.get("userIdx");
		String replyNickName = reply.get("nickname");
		String replyIdx = reply.get("replyIdx");
		String postIdx = reply.get("postIdx");
		
		
		
		Map<String, Object> data = CamelMap.changeMap(alarmRepository.selectPostAndReplyJoin(replyIdx));
		if(replyUserIdx.equals((String)data.get("userIdx"))) {
			
			return null;
			
		}

			
		String postTitle = (String )data.get("postTitle");
		if(postTitle.length() >=11 ) {
			postTitle = postTitle.substring(0, 10) + "...";
			
		}
		Alarm alarm = new Alarm();
		
		alarm.setAlType("[댓글알림]");
		alarm.setAlContent("\"" + postTitle+"\" 게시글에 댓글이 달렸습니다.");
		alarm.setUserIdx((String)data.get("userIdx"));
		alarm.setLink(reply.get("link"));
		alarm.setProjectIdx(reply.get("projectIdx"));
		
		System.out.println(alarm);
		alarmRepository.insetAlarm(alarm);
		
		
		System.out.println(alarm);
		
		
		
		return alarm;
	}

	@Override
	public void updateAlarmIsLook(Alarm alarm) {
		alarmRepository.updateAlarmIsLook(alarm);
		
	}

}
