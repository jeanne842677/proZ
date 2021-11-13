package com.kh.spring.member.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.common.code.ErrorCode;
import com.kh.spring.common.exception.HandlableException;
import com.kh.spring.common.validator.ValidateResult;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.validator.JoinForm;
import com.kh.spring.member.validator.JoinFormValidator;

@Controller
@RequestMapping("member")
public class MemberController {
   
   Logger logger = LoggerFactory.getLogger(this.getClass());
   
   private MemberService memberService;
   private JoinFormValidator joinFormValidator;
   @Autowired
   private MemberService memberServiceImpl;
   
   public MemberController(MemberService memberService, JoinFormValidator joinFormValidator) {
      super();
      this.memberService = memberService;
      this.joinFormValidator = joinFormValidator;
   }
   
 
   
   @InitBinder(value = "joinForm") //model의 속성 중 속성명이 joinForm인 속성이 있는 경우 initBinder 메서드 실행
   public void initBinder(WebDataBinder webDataBinder) {
      webDataBinder.addValidators(joinFormValidator);
   }


   @GetMapping("join")
   public void joinForm(Model model) {
      model.addAttribute(new JoinForm()).addAttribute("error",new ValidateResult().getError());
   }

   @PostMapping("join")
   public String join(@Validated JoinForm form
         , Errors errors //반드시 검증될 객체 바로 뒤에 작성
         , Model model
         , HttpSession session
         , RedirectAttributes redirectAttr
         ) {
      
      ValidateResult vr = new ValidateResult();
      model.addAttribute("error",vr.getError());
      
      if(errors.hasErrors()) {
         vr.addError(errors);
         return "member/join";
      }
  	
		//token 생성
		String token  = UUID.randomUUID().toString();
		session.setAttribute("persistUser", form);
		session.setAttribute("persistToken", token);
		
		memberService.authenticateByEmail	(form,token);
		redirectAttr.addFlashAttribute("message", "이메일이 발송되었습니다.");
      
      return "redirect:/";
   }

   @GetMapping("join-impl/{token}")
	public String joinImpl(@PathVariable String token
							,@SessionAttribute(value = "persistToken", required = false) String persistToken
							,@SessionAttribute(value = "persistUser", required = false) JoinForm form
							,HttpSession session
							,RedirectAttributes redirectAttrs) {
		
		if(!token.equals(persistToken)) {
			throw new HandlableException(ErrorCode.AUTHENTICATION_FAILED_ERROR);
		}
		
		memberService.insertMember(form);
		redirectAttrs.addFlashAttribute("message", "회원가입을 환영합니다. 로그인 해주세요");
		session.removeAttribute("persistToken");
		session.removeAttribute("persistUser");
		return "redirect:/member/login";
	}
	
  
   @GetMapping("email-check")
   @ResponseBody
   public String emailCheck(String email) {
      Member member = memberService.selectMemberByEmail(email);
      
      if(member == null) {
         return "available";
      }else {
         return "disable";
      }
   }
	@PostMapping("join-json")
	public String joinWithJson(@RequestBody Member member) {
		logger.debug(member.toString());
		return "index";
	}
   
   @GetMapping("login")
   public void login() {}; 
   
   @PostMapping("login")
   public String loginImpl(Member member, HttpSession session, RedirectAttributes redirctAttr) {
      
      System.out.println(member.toString());
      
      Member certifiedUser = memberServiceImpl.selectMemberByEmailAndPassword(member);
      
      
      if(certifiedUser == null) {
    	  redirctAttr.addFlashAttribute("message","아이디나 비밀번호가 정확하지 않습니다.");
         return "redirect:/member/login";
      }
      
      session.setAttribute("authentication", certifiedUser);
      logger.debug(certifiedUser.toString());
      return "redirect:/member/join";
      
      
   }
   
//   로그아웃
   @GetMapping("logout.do")
   public String logout (HttpServletRequest request) throws Exception{
      logger.info("logout메서드 진입");
      
      HttpSession session = request.getSession();
      
      return "redirect:/";
      
   }
   
   
   

}

