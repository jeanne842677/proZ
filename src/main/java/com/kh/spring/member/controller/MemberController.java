package com.kh.spring.member.controller;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
   private GoogleConnectionFactory googleConnectionFactory;
   @Autowired
   RestTemplate http;
   @Autowired
   private OAuth2Parameters googleOAuth2Parameters;
   
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
      
      memberService.authenticateByEmail   (form,token);
      redirectAttr.addFlashAttribute("message", "이메일이 발송되었습니다.");
      
      return "redirect:/";
   }
   
   

   @GetMapping("join-impl/{token}")
   public String joinImpl(@PathVariable String token
                     ,@SessionAttribute(value = "persistToken", required = false) String persistToken
                     ,@SessionAttribute(value = "persistUser", required = false) JoinForm form
                     ,HttpSession session
                     ,RedirectAttributes redirectAttrs) {
      System.out.println(form);
      if(!token.equals(persistToken)) {
         throw new HandlableException(ErrorCode.AUTHENTICATION_FAILED_ERROR);
      }
      
//      memberService.insertSocialMember(form);
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
   public String loginImpl(Model model, Member member, HttpSession session, RedirectAttributes redirctAttr) {
      

      System.out.println(member.toString());
      
      Member certifiedUser = memberServiceImpl.selectMemberByEmailAndPassword(member);
      
      
      if(certifiedUser == null) {
         redirctAttr.addFlashAttribute("message","아이디나 비밀번호가 정확하지 않습니다.");
         return "redirect:/member/login";
      }
      
      session.setAttribute("authentication", certifiedUser);
      logger.debug(certifiedUser.toString());
      return "redirect:/project/project-list";
      
      
   }

   
//   로그아웃
   @GetMapping("logout.do")
   public String logout (HttpServletRequest request) throws Exception{
      logger.info("logout메서드 진입");
      
      HttpSession session = request.getSession();
      
      return "redirect:/";
      
   }
   
 

   
// 구글
 //로그인 페이지로 이동하는 컨트롤러
 @RequestMapping(value="google_login")
 public String initLogin(Model model, HttpSession session ) throws Exception {

    /* 구글code 발행 */
    OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();

   /* 로그인페이지 이동 url생성 */
    String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);

    model.addAttribute("google_url", url);

    System.out.println("인증코드 넘기기 : " + googleOAuth2Parameters);
    
    /* 생성한 인증 URL을 Model에 담아서 전달 */
    return "redirect: " + url;
    
    

 }
 
 
 // 구글 Callback호출 메소드
 @RequestMapping("oauth2callback")
 public String googleCallback(Member member,RedirectAttributes redirectAttr, @RequestParam String code, HttpSession session) throws Exception {

   //로그인성공문구, 코드 확인 
   System.out.println("Google login success");
   System.out.println("code: "+ code);

   
   //////////////////////////////////////////
// Access Token 발급

   Map<String, String> userJson= memberService.GoogleCallback(code);
   
// 사용자정보 추출
   System.out.println("map으로 바꾸기" + userJson);
   String googleId = userJson.get("id");
   String googleName = userJson.get("name");
   System.out.println("access_token : " + googleId);
   System.out.println("access_token : " + googleName);
   session.setAttribute("googleId", googleId);

   
   redirectAttr.addFlashAttribute("name", googleName);
   //////////////////////////////////////////
   Member GoogleUser = memberServiceImpl.selectGoogleId(googleId);
   System.out.println(GoogleUser);
      if(GoogleUser == null) {
         System.out.println("아이디가 존재하지 않으면 소셜로그인폼으로 ");
         return "redirect:/member/social-join";
      }
   
      
   //id가 존재하지 않아 소셜조인폼으로 보냅니다.
   return "redirect:/project/template";
 }

 
// 소셜회원가입폼
 @GetMapping("social-join")
 public void socialJoinForm(Model model) {
    model.addAttribute(new JoinForm()).addAttribute("error",new ValidateResult().getError());
 }; 
 
 @PostMapping("social-join")
 public String socialJoin(Member member, @Validated JoinForm form, Errors errors, Model model, HttpSession session, RedirectAttributes redirectAttr)  { ValidateResult vr = new ValidateResult();
         model.addAttribute("error",vr.getError());
         
         if(errors.hasErrors()) {
            vr.addError(errors);
            return "member/social-join";
         }
        
         //token 생성
         String token  = UUID.randomUUID().toString();
         
      
         
         form.setSocialId((String)session.getAttribute("googleId"));
         session.setAttribute("persistToken", token);
         session.setAttribute("persistUser", form);
         memberService.authenticateByEmail(form,token);
         redirectAttr.addFlashAttribute("message", "이메일이 발송되었습니다.");
         
         return "redirect:/member/login";
    
    
//    memberService.insertSocialMember(member);
//    System.out.println(member);
    
   
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
    
    String url = "https://kapi.kakao.com/v1/user/update_profile";
    
    RequestEntity<Void> requestUrl = RequestEntity.post(url)
          .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
          .header("Authorization", "Bearer   " + accessToken)
          .build();

     return  "redirect:/";
           
 }
 
 
 @RequestMapping("kakao_logout")
 public String kakaoLogout() {
 
    StringBuffer logoutUrl = new StringBuffer();
    logoutUrl.append("https://kauth.kakao.com/oauth/logout?client_id=");
    logoutUrl.append("448c3d7ccd2aea13e02cfe7121e656dc");
    logoutUrl.append("&logout_redirect_uri=");
    logoutUrl.append("http://localhost:9090");

       System.out.println("로그아웃했습니다.");
       System.out.println("카카오 " + logoutUrl);
       return "redirect:/";
 }

 
 
 
 
 
 }
   
   
   


