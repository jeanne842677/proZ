package com.kh.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {
	

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("login")
	public void login() {}; 
	
	
	
	
	@GetMapping("join")
	public void join() {}; 
	
	
	@PostMapping("login")
	public void loginImpl(Member member) {
		
		Member mem = memberService.selectMemberByEmailAndPassword(member);
		System.out.println(mem);
		
		
	}
}
