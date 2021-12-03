package com.kh.spring.calendar.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.repository.BoardRepository;
import com.kh.spring.calendar.model.dto.Calendar;
import com.kh.spring.calendar.model.repository.CalendarRepository;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.project.model.dto.ProjectMember;
import com.kh.spring.project.model.repository.ProjectRepository;

@Service
public class CalendarServiceImpl implements CalendarService {
	
	@Autowired
	CalendarRepository calendarRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	
	
	@Override
	public void insertCalendar(Calendar calendar, Member member) {
		
		Map<String, String> map = new HashMap<>();
		map.put("wsIdx", calendar.getWsIdx());
		map.put("userIdx", member.getUserIdx());
		ProjectMember projectMember = projectRepository.selectProjectMemberByMap(map);
		
		calendar.setPmIdx(projectMember.getPmIdx());
		
		calendarRepository.insertCalendar(calendar);
		
		
		
	}



	@Override
	public List<Calendar> selectCalendarListByWsIdx(String wsIdx) {
		
		
		return calendarRepository.selectCalendarListByWsIdx(wsIdx);
	}



	@Override
	public void updateCalendar(Calendar calendar) {
		
		calendarRepository.updateCalendar(calendar);
		System.out.println("업뎃 완료");
		
	}



	@Override
	public Calendar selectCalendarByCalIdx(String calIdx) {
		
		return calendarRepository.selectCalendarByCalIdx(calIdx);
	}
	
	
	
}
