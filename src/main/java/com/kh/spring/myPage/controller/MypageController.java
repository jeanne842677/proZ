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
		dummyMember.setEmail("zaxscd95@naver.com");
		dummyMember.setUserIdx("100021");
		dummyMember.setProfileColor("#b3cbd0ff");
		dummyMember.setGit("tempGit@repository.com");
		dummyMember.setNickname("aramarabara");
		
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
		
		//1. 파일 추출 및 업로드 
		FileUtil fileUtil = new FileUtil(); 
		FileDTO fileUploaded = fileUtil.fileUpload(files.get(0)); 
		
		//2. 파일 데이터베이스 저장 
		int res = mypageService.insertMemberProfileImg(fileUploaded); 
		
		if(res != 1) {
			return "failed"; 
		}
		
		// 3. profileImg는 join을 통해 추출, session 업데이트 불필요 
		return fileUploaded.getSavePath() +fileUploaded.getRenameFileName(); 
	}
	
	@PostMapping(value= "profileNickname", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String changeProfileNickname(@RequestParam String nickname
			,HttpSession session) {
		
		//session에서 member추출 
		Member member = (Member) session.getAttribute("certifiedUser"); 
		member.setNickname(nickname);
		
		// 에러 보류, try-catch말고 HandlableException없나? 
		// 12자리 이하 수정필요 
		int res = mypageService.updateMypageMemberByNickname(member); 
		
		// res값이 아예 오지를 않는다, failed가 작동할 수가 없음, 그리고 
		// 반환값이 그대로 db오류로 들어간다 
		if(res != 1) {
			return "failed"; 
		}
		
		session.setAttribute("certifiedUser", member);
		return nickname; 
	}
	
	@PostMapping(value= "profileGitRepo", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String changeProfileGitRepo(@RequestParam String git
			,HttpSession session) {
		
		//session에서 member추출 
		Member member = (Member) session.getAttribute("certifiedUser"); 
		member.setGit(git);
		
		int res = mypageService.updateMypageMemberByGit(member); 
		
		if(res != 1) {
			return "failed"; 
		}
		
		session.setAttribute("certifiedUser", member);
		return git; 
	}
	
	@PostMapping("profilePassword")
	public String changeProfilePasswordr() {
		return ""; 
	}
	
	
	@PostMapping("isleave")
	@ResponseBody
	public String changeMemberIsleave(HttpSession session) {
		
		// 7일의 유예기간을 어떻게 줄 것인가? 
		// 7일 후에 탈퇴하는 것이 아니라, 당장에 ISLEAVE와 함께 날짜를 저장하나?
		// 회원탈퇴 7일 기능은 DB변경 및 다른 조건이 너무 많다, 일단 뺄 생각 
		
		//session에서 member추출 
		Member member = (Member) session.getAttribute("certifiedUser"); 
		
		int res = mypageService.memberIsleave(member); 
		
		if(res != 1) {
			return "failed"; 
		}
		
		session.removeAttribute("certifiedUser");
		
		return "success"; 
	}
	

	
}
