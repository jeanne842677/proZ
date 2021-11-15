package com.kh.spring.member.controller;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
   
   @Autowired
   RestTemplate http;

   public MemberController(MemberService memberService, JoinFormValidator joinFormValidator) {
      super();
      this.memberService = memberService;
      this.joinFormValidator = joinFormValidator;
   }

   @InitBinder(value = "joinForm") // model의 속성 중 속성명이 joinForm인 속성이 있는 경우 initBinder 메서드 실행
   public void initBinder(WebDataBinder webDataBinder) {
      webDataBinder.addValidators(joinFormValidator);
   }

   @GetMapping("join")
   public void joinForm(Model model) {
      model.addAttribute(new JoinForm()).addAttribute("error", new ValidateResult().getError());
   }

   @PostMapping("join")
   public String join(@Validated JoinForm form, Errors errors // 반드시 검증될 객체 바로 뒤에 작성
         , Model model, HttpSession session, RedirectAttributes redirectAttr) {

      ValidateResult vr = new ValidateResult();
      model.addAttribute("error", vr.getError());

      if (errors.hasErrors()) {
         vr.addError(errors);
         return "member/join";
      }

      // token 생성
      String token = UUID.randomUUID().toString();
      session.setAttribute("persistUser", form);
      session.setAttribute("persistToken", token);

      memberService.authenticateByEmail(form, token);
      redirectAttr.addFlashAttribute("message", "이메일이 발송되었습니다.");

      return "redirect:/";
   }

   @GetMapping("join-impl/{token}")
   public String joinImpl(@PathVariable String token,
         @SessionAttribute(value = "persistToken", required = false) String persistToken,
         @SessionAttribute(value = "persistUser", required = false) JoinForm form, HttpSession session,
         RedirectAttributes redirectAttrs) {

      if (!token.equals(persistToken)) {
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

      if (member == null) {
         return "available";
      } else {
         return "disable";
      }
   }

   @PostMapping("join-json")
   public String joinWithJson(@RequestBody Member member) {
      logger.debug(member.toString());
      return "index";
   }

//   @GetMapping("login")
//   public void login() {}; 
//   
//   @PostMapping("login")
//   public String loginImpl(Member member, HttpSession session, RedirectAttributes redirctAttr) {
//      
//      System.out.println(member.toString());
//      
//      Member certifiedUser = memberServiceImpl.selectMemberByEmailAndPassword(member);
//      
//      
//      if(certifiedUser == null) {
//         redirctAttr.addFlashAttribute("message","아이디나 비밀번호가 정확하지 않습니다.");
//         return "redirect:/member/login";
//      }
//      
//      session.setAttribute("authentication", certifiedUser);
//      logger.debug(certifiedUser.toString());
//      return "redirect:/member/join";
//      
//      
//   }

//   로그아웃
   @GetMapping("logout.do")
   public String logout(HttpServletRequest request) throws Exception {
      logger.info("logout메서드 진입");

      HttpSession session = request.getSession();

      return "redirect:/";

   }

// 구글

   @Autowired
   private GoogleConnectionFactory googleConnectionFactory;

   @Autowired
   private OAuth2Parameters googleOAuth2Parameters;

   // 로그인 페이지로 이동하는 컨트롤러
   @RequestMapping("login")
   public String initLogin(Model model, HttpSession session) throws Exception {

      /* 구글code 발행 */
      OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();

      /* 로그인페이지 이동 url생성 */
      String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);

      model.addAttribute("google_url", url);

      System.out.println("인증코드 넘기기 : " + googleOAuth2Parameters);

      /* 생성한 인증 URL을 Model에 담아서 전달 */
      return "/member/login";

   }

   // 구글 Callback호출 메소드
   @RequestMapping(value = "oauth2callback", method = { RequestMethod.GET, RequestMethod.POST })
   public String googleCallback(Model model, @RequestParam String code) throws IOException {

      System.out.println("Google login success");
//   System.out.println(code);
      // 저는 성공하면 메인페이지로 리다이렉트합니다.
      return "redirect:/member/join";
   }

   // 카카오 로그인
   @RequestMapping(value = "kakao_login")
   public String kakaoLogin() {
      StringBuffer loginUrl = new StringBuffer();
      loginUrl.append("https://kauth.kakao.com/oauth/authorize?client_id=");
      loginUrl.append("448c3d7ccd2aea13e02cfe7121e656dc");
      loginUrl.append("&redirect_uri=");
      loginUrl.append("http://localhost:9090/member/kakao_callback");
      loginUrl.append("&response_type=code");

      return "redirect:" + loginUrl.toString();
   }

   @RequestMapping(value ="kakao_callback", method = RequestMethod.GET)
   public String redirectkakao(@RequestParam String code, HttpSession session) throws IOException {
   // 접속토큰 get
      System.out.println("code:"+code);
      
   
      MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
      map.add("grant_type", "authorization_code");
      map.add("client_id", "448c3d7ccd2aea13e02cfe7121e656dc");
      map.add("redirect_uri", "http://localhost:9090/member/kakao_callback");
      map.add("code", code);

      String kakao = http.postForObject("https://kauth.kakao.com/oauth/token", map, String.class);
      
      System.out.println("카카오 토큰: " + kakao);

      ObjectMapper mapper = new ObjectMapper();
      Map<String , String> kakaoMap = mapper.readValue(kakao, Map.class);
      System.out.println(kakaoMap);
      String accessToken = kakaoMap.get("access_token");
      
      
      
      String uri = "https://kapi.kakao.com/v2/user/me";
      
      RequestEntity<Void> request = RequestEntity.post(uri)
            .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            .header("Authorization", "Bearer " + accessToken)
            .build();
         
      
      ResponseEntity<String> res = http.exchange(request, String.class); 
      String resBody = res.getBody();
      System.out.println(resBody);


      
      
      
      
      
      
      /*
      
      String kakaoToken = memberService.getReturnAccessToken(code);
      
       //접속자 정보 get
      Map<String,Object> result = memberService.getUserInfo(kakaoToken);
       
        Member member =new Member();
        member.setNickname((String)result.get("nickname"));
        member.setEmail((String)result.get("email"));
        
       session.setAttribute("Member", member); 
       //로그아웃 처리 시, 사용할 토큰 값
       session.setAttribute("kakaoToken", kakaoToken);
       System.out.println("컨트롤러 출력:"+result.get("nickname"));
       System.out.println("컨트롤러 출력 :"+result.get("email"));
         // 저는 성공하면 메인페이지로 리다이렉트합니다.
         //return "redirect:/member/join";
       */
       
       return  "redirect:/";
             
   }
   
   
   @RequestMapping("test")
   public String test() {
      System.out.println("실");
      return "redirect:/";
   }
}