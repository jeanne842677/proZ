package com.kh.spring.myPage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.common.util.file.FileDTO;
import com.kh.spring.common.util.file.FileUtil;
import com.kh.spring.common.util.json.JsonMaker;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.myPage.model.service.MypageService;

@Controller
@RequestMapping("mypage")
public class MypageController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MypageService mypageService; 
	
	@GetMapping("mypage") 
	public String mypage(HttpSession session) {
		
		Member dummyMember = new Member(); 
		dummyMember.setEmail("111111");
		dummyMember.setUserIdx("100021");
		dummyMember.setProfileColor("color");
		
		session.setAttribute("certifiedUser", dummyMember);
	
		return "member/mypage"; 
	}
		//GetMapping의 경우 경로설정, mypage/mypage로 설정 
	
	@PostMapping("profileColor")
	@ResponseBody 
	// return value가 responseBody에 바인딩되도록 설정 
	// DTO / MAP 일 경우에는 JSON으로 바꾸어서 보낸다. 
	public String changeProfileColor(Member member, HttpSession session) {
	// *** 속성명과 dtosetter를 맞추면, 자동으로 Databinding 처리해준다. 
		
		// 1. 색 값 추출 
		String profileColor = "#" + member.getProfileColor();
		
		// 2. SESSION의 맴버 추출 후 색 주입하여 DB저장 
		Member tempMember = (Member) session.getAttribute("certifiedUser");
		tempMember.setProfileColor(profileColor); 
		int res = mypageService.updateMypageMemberByProfileColor(tempMember);
		
		// 3. 예외처리 
		if(res != 1) {
			return "failed"; 
		}
		
		// 4. 반환 시 SESSION_UPDATE 후 값 반환 
		session.setAttribute("certifiedUser", tempMember);

		return tempMember.getProfileColor(); 
	}
	
	@PostMapping("profileImg")
	@ResponseBody
	public String changeProfileImg(@RequestParam List<MultipartFile> files) {
		
		FileUtil fileUtil = new FileUtil(); 
		FileDTO fileUploaded = fileUtil.fileUpload(files.get(0)); 
		
		int res = mypageService.insertMemberProfileImg(fileUploaded); 
		
		if(res != 1) {
			return "failed"; 
		}
		
		return fileUploaded.getSavePath() +fileUploaded.getRenameFileName(); 
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
