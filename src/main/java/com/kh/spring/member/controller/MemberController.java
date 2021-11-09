package com.kh.spring.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
public class MemberController {
	

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("login")
	public void login() {}; 
	
	
	@GetMapping("join")
	public void join() {}; 

}
