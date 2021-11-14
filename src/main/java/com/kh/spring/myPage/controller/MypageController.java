package com.kh.spring.myPage.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String mypage() { return "member/mypage"; }
		//GetMapping의 경우 경로설정, mypage/mypage로 설정 
	
	@PostMapping("profileColor")
	@ResponseBody 
	// return value가 responseBody에 바인딩되도록 설정 
	// DTO / MAP 일 경우에는 JSON으로 바꾸어서 보낸다. 
	public String changeProfileColor(Member member, HttpSession session) {
		// *** 속성명과 dtosetter를 맞추면, 자동으로 Databinding 처리해준다. 
		
		// 1. DB에서 받아 인증된 certifiedUser값이 Session에 저장되어 있다. 
		// *** 이를 통해서 Mypage의 정보를 구성한다. 
		// 단, MypageController의 경우는 더미를 Session에 넣어주어 구성한다.
		Member dummyMember = new Member(); 
		dummyMember.setEmail("111111");
		dummyMember.setUserIdx("100021");
		dummyMember.setProfileColor("color");
		
		
		
		// 2. 색 전송을 확인한다. 
		logger.debug(member.toString());
		
		// 3. 전송된 색을 Database에 다시 넣어준다, 이때 값의 반환처리는 Member
		// (다시 쓸 일이 있을수도 있기 때문에)
		// profileColor와 함께 현 맴버의 이메일 역시 같이 보낸다. 
		int res = mypageService.updateMypageMemberByProfileColor(dummyMember);
		
		// 4. 문제가 생기면 (res값이 1이 아니라면) return failed
		if(res != 1) {
			return "failed"; 
		}
		
		System.out.println(res);

		
		// 5. 반환된 값을 통해서 certifiedUser 값을 변경해준다. (완료)  
		// sessoion에 DTO 넣으면 자동 toString 변환해서 날려준다. 
		session.setAttribute("certifiedUser", dummyMember);
		// JSON으로 수정한 다음 바꾸기 
		String resJson = JsonMaker.json(dummyMember);
		logger.debug(resJson);
		return resJson; 
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
