package com.kh.spring.projectmember.model.service;

import java.util.Map;

import com.kh.spring.projectmember.dto.Alarm;

public interface ProjectMemberService {

	Alarm updateReplyAlarm(Map<String, String> reply);

	void updateAlarmIsLook(Alarm alarm);

}
