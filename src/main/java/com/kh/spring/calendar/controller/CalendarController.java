package com.kh.spring.calendar.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.spring.calendar.model.dto.Calendar;
import com.kh.spring.calendar.model.service.CalendarService;
import com.kh.spring.member.model.dto.Member;

@Controller
@RequestMapping("calendar")
public class CalendarController {
	
	
	@Autowired
	private CalendarService calendarService;
	
	
	@GetMapping("{projectIdx}")
	public String calendar(@PathVariable String projectIdx 
			, @RequestParam(required = false) String wsIdx
			, Model model) {
		
		List<Calendar> calendarList= calendarService.selectCalendarListByWsIdx(wsIdx);
		
		model.addAttribute("calendarList" , calendarList);
		
		return "/calendar/calendar";
	}
	
	

	
	
	@GetMapping("posting/{projectIdx}")
	public String posting(@PathVariable String projectIdx , 
			@RequestParam(required = false) String wsIdx) {
		
		System.out.println(wsIdx);
		
		
		
		return "/calendar/cal-posting" ; 
		
	}
	
	@GetMapping("view/{projectIdx}")
	public String viewPost(@PathVariable String projectIdx ,
			@RequestParam(required = false) String calIdx ,
			Model model
			) {
		
		
		Calendar calendar = calendarService.selectCalendarByCalIdx(calIdx);
		model.addAttribute("calendar" , calendar);
		
		return "/calendar/cal-post";
	}
	
	
	
	
	@PostMapping("change/add-cal")
	public String addCal(@RequestBody Calendar calendar,
			@SessionAttribute(required = false , value="authentication") Member member) {
		
		
		
		System.out.println("캘린더 추가: " + calendar);
		calendarService.insertCalendar(calendar, member);
		
		return "redirect:/";
	}

	
	
	@PostMapping("change/update-date")
	@ResponseBody
	public String updateCalDate(@RequestBody Calendar calendar) {
		
		System.out.println(calendar);
		calendarService.updateCalendar(calendar);
		
		
		
		return "";
		
	}
	
	

}
