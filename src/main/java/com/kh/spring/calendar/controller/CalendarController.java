package com.kh.spring.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("calendar")
public class CalendarController {
	
	
	@GetMapping("{projectIdx}")
	public String calendar(@PathVariable String projectIdx 
			, @RequestParam String wsIdx) {
		
		
		return "/calendar/calendar";
	}
	

}
