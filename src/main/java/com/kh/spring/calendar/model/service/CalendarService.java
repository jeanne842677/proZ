package com.kh.spring.calendar.model.service;

import java.util.List;

import com.kh.spring.calendar.model.dto.Calendar;
import com.kh.spring.member.model.dto.Member;

public interface CalendarService {

	void insertCalendar(Calendar calendar, Member member);

	List<Calendar> selectCalendarListByWsIdx(String wsIdx);

	void updateCalendar(Calendar calendar);

	Calendar selectCalendarByCalIdx(String calIdx);

	List<Calendar> selectCalendarListByProjectIdx(String projectIdx);

}
