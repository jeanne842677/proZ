package com.kh.spring.myPage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.common.util.file.FileDTO;
import com.kh.spring.common.util.file.FileUtil;
import com.kh.spring.common.validator.ValidateResult;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.validator.JoinForm;
import com.kh.spring.member.validator.MypageForm;
import com.kh.spring.member.validator.MypageValidator;
import com.kh.spring.myPage.model.service.MypageService;

//@Controller
//@RequestMapping("mypage")
public class MypageController {
	
//	Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	private MypageValidator mypageValidator; 
//	@Autowired
//	private MypageService mypageService; 
//	
//	public MypageController(MypageValidator mypageValidator) {
//		super(); 
//		this.mypageValidator = mypageValidator; 
//	}
//	
//	//1. Custom_Validator binding 실행 
//	@InitBinder(value = "mypageForm")
//	public void initBinder(WebDataBinder webDataBinder) {
//	   webDataBinder.addValidators(mypageValidator);
//	}
	
//	//2. DummyUser 및 모델에 error 추가 (**validator필수) 
//	// 이 메서드를 여기에서 처리하면 안된다, 무조건 진입로를 member에서 관리하게 만들어야 한다. 
//	@GetMapping("mypage") 
//	public String mypage(HttpSession session, Model model) {
//		
//		Member dummyMember = new Member(); 
//		dummyMember.setEmail("zaxscd95@naver.com");
//		dummyMember.setUserIdx("100021");
//		dummyMember.setProfileColor("#b3cbd0ff");
//		dummyMember.setGit("tempGit@repository.com");
//		dummyMember.setNickname("aramarabara");
//		//프로필 사진 추출
//		FileDTO fileDTO = mypageService.selectProfileImgFilebyMemberIdx(dummyMember);
//		
//		session.setAttribute("certifiedUser", dummyMember);
//		
//		if(fileDTO == null) {
//			System.out.println("1. fileDTO 없음 진입확인");
//			session.setAttribute("profileImg", "person.png");
//		} else {
//			session.setAttribute("profileImg", fileDTO.getSavePath()+fileDTO.getRenameFileName());
//		}
//		
//		// Model에 error 객체 추가 
//		model.addAttribute(new MypageForm()).addAttribute("error",new ValidateResult().getError());
//		return "member/mypage"; 
//	}
	
//	//3. ProfileColor 비동기 변경 
//	@PostMapping("profileColor")
//	@ResponseBody 
//	public String changeProfileColor(@Validated MypageForm mypageForm
//			,Errors errors 
//			,HttpSession session) {
//		
//		try {
//			//1) 값 Validate 검증
//			if(errors.hasErrors()) {
//				return "failed"; 
//			}
//			//2) 색 추출, SESSION_Member에 넣어 DB저장, 이후 SESSION update 
//			String profileColor = "#" + mypageForm.getProfileColor();
//			Member tempMember = (Member) session.getAttribute("authentication");
//			tempMember.setProfileColor(profileColor); 
//			
//			int res = mypageService.updateMypageMemberByProfileColor(tempMember);
//			session.setAttribute("authentication", tempMember);
//		} catch(Exception e) {
//			e.printStackTrace();
//			return "failed";
//		}
//			//3) color값 반환 
//			return "#" + mypageForm.getProfileColor(); 
//	}
//	
//	@PostMapping("profileImg")
//	@ResponseBody
//	public String changeProfileImg(@RequestParam List<MultipartFile> files
//			,HttpSession session) {
//		
//		//1) 파일 추출 및 DB저장 
//		FileUtil fileUtil = new FileUtil(); 
//		FileDTO fileUploaded = fileUtil.fileUpload(files.get(0));
//	
//		try {
//			Member member = (Member) session.getAttribute("authentication");
//			System.out.println(member.getUserIdx());
//			int res = mypageService.insertMemberProfileImg(fileUploaded, member.getUserIdx()); 
//		} catch(Exception e) {
//			e.printStackTrace();
//			
//		}
//		//2) profileImg는 join을 통해 추출, session 업데이트 불필요
//		logger.debug(fileUploaded.getSavePath());
//		logger.debug(fileUploaded.getRenameFileName());
//		return fileUploaded.getSavePath() +fileUploaded.getRenameFileName(); 
//	}
//	
//	@PostMapping(value= "profileNickname", produces = "text/plain;charset=UTF-8")
//	@ResponseBody
//	public String changeProfileNickname(@Validated MypageForm mypageForm
//			,Errors errors
//			,HttpSession session) {
//		
//		try {
//			//1) 값 validate 검증 
//			if(errors.hasErrors()) {
//				return "failed";
//			}
//			//2) 닉네임 추출, SESSION_Member에 넣어 DB저장, 이후 SESSION update
//			String nickname = mypageForm.getNickname();
//			Member member = (Member) session.getAttribute("authentication"); 
//			member.setNickname(nickname);
//			
//			int res = mypageService.updateMypageMemberByNickname(member);
//			session.setAttribute("authentication", member);
//		} catch(Exception e) {
//			e.printStackTrace(); 
//			return "failed";
//		}
//			//3) nickname 반환 
//			return mypageForm.getNickname(); 
//	}
//	
//	@PostMapping(value= "profileGitRepo", produces = "text/plain;charset=UTF-8")
//	@ResponseBody
//	public String changeProfileGitRepo(@Validated MypageForm mypageForm
//			,Errors errors
//			,HttpSession session) {
//		
//		try {
//			//1) 값 validate 검증 
//			if(errors.hasErrors()) {
//				return "failed";
//			} 
//			Member member = (Member) session.getAttribute("authentication"); 
//			member.setGit(mypageForm.getGit());
//			
//			
//			//2) git 추출, SESSION_Member에 넣어 DB저장, 이후 SESSION update
//			int res = mypageService.updateMypageMemberByGit(member); 
//			session.setAttribute("authentication", member);
//		} catch(Exception e) {
//			e.printStackTrace(); 
//			return "failed"; 
//		}
//			//3) git 반환 
//			return mypageForm.getGit(); 
//	}
//	
//	@PostMapping("profilePassword")
//	@ResponseBody
//	public String changeProfilePassword(@Validated MypageForm mypageForm
//			,Errors errors
//			,HttpSession session) {
//		try {
//			//1) 값 validate 검증 
//			if(errors.hasErrors()) {
//				return "failed";
//			} 
//			
//			// 맴버를 꺼낸다.  
//			Member member = (Member) session.getAttribute("authentication");
//			// 맴버를 보낸다 ( service단에서 password 변경까지 처리 ) 
//			String password = mypageForm.getPassword(); 
//			member.setPassword(password);
//			// 변경된 패스워드 추가까지 마무리 
//			Member resMember = mypageService.updateMypageMemberByPassword(member); 
//			member.setPassword(resMember.getPassword());
//			
//			session.setAttribute("authentication", member);
//		} catch(Exception e) {
//			e.printStackTrace(); 
//			return "failed"; 
//		}
//			//3) 성공 반환   
//			return "success";  
//	}
//	
//	
//	@PostMapping("isleave")
//	@ResponseBody
//	public String changeMemberIsleave(HttpSession session) {
//			
//		//1. session에서 member추출, isLeave 변경 
//		try {
//			Member member = (Member) session.getAttribute("authentication"); 
//			int res = mypageService.memberIsleave(member); 
//			session.removeAttribute("authentication");
//		} catch(Exception e) {
//			e.printStackTrace();
//			return "failed"; 
//		}
//		//2. return "success"
//		return "success"; 
//	}
	

	
}
