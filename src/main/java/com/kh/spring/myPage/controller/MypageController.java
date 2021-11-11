package com.kh.spring.myPage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.myPage.model.service.MypageService;

@Controller
@RequestMapping("mypage")
public class MypageController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MypageService mypageService; 
	
	@GetMapping("mypage") 
	public void mypage() {}
		//GetMapping의 경우 경로설정, mypage/mypage로 설정 
	
	@PostMapping("profileImg")
	public String changeProfileImg() {
		// Spring의 경우 비동기통신을 어떻게 처리하는지 복습 필요 
		return "";
	}
	
	@PostMapping("profileNickname")
	public String changeProfileNickname() {
		return ""; 
	}
	
	@PostMapping("profileGitRepo")
	public String changeProfileGitRepo() {
		return ""; 
	}
	
	@PostMapping("profilePassword")
	public String changeProfilePasswordr() {
		return ""; 
	}
}
