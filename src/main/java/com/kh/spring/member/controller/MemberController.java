package com.kh.spring.member.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.common.code.ErrorCode;
import com.kh.spring.common.exception.HandlableException;
import com.kh.spring.common.util.file.FileDTO;
import com.kh.spring.common.util.file.FileUtil;
import com.kh.spring.common.validator.ValidateResult;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.validator.ChangePasswordForm;
import com.kh.spring.member.validator.ChangePasswordValidator;
import com.kh.spring.member.validator.EmailForm;
import com.kh.spring.member.validator.EmailValidator;
import com.kh.spring.member.validator.JoinForm;
import com.kh.spring.member.validator.JoinFormValidator;
import com.kh.spring.member.validator.MypageForm;
import com.kh.spring.member.validator.MypageValidator;
import com.kh.spring.myPage.model.service.MypageService;

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
   @Autowired
   private MypageValidator mypageValidator; 
   @Autowired
   private EmailValidator emailValidator; 
   @Autowired
   private ChangePasswordValidator changePasswordValidator; 
   @Autowired
   ServletContext context; 

   public MemberController(MemberService memberService, JoinFormValidator joinFormValidator) {
      super();
      this.memberService = memberService;
      this.joinFormValidator = joinFormValidator;
   }
   
 
   @InitBinder(value = "joinForm") //model의 속성 중 속성명이 joinForm인 속성이 있는 경우 initBinder 메서드 실행
   public void initBinder(WebDataBinder webDataBinder) {
      webDataBinder.addValidators(joinFormValidator);
   }

   @InitBinder(value = "mypageForm")
	public void initBinderMypage(WebDataBinder webDataBinder) {
	   webDataBinder.addValidators(mypageValidator);
	}
   
   @InitBinder(value = "emailForm")
	public void initBinderSearchPassword(WebDataBinder webDataBinder) {
	   webDataBinder.addValidators(emailValidator);
	}
   
   @InitBinder(value = "changePasswordForm")
   public void initBinderchangePassword(WebDataBinder webDataBinder) {
	   webDataBinder.addValidators(changePasswordValidator);
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
      
      if(form.getSocialId() != null) {
    	  memberService.insertSocialMember(form);
    	  return "redirect:/member/login";
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
   public String loginImpl(Model model, Member member, HttpSession session, RedirectAttributes redirctAttr) {
      

      System.out.println(member.toString());
      
      Member certifiedUser = memberServiceImpl.selectMemberByEmailAndPassword(member);
      
      
      if(certifiedUser == null) {
         redirctAttr.addFlashAttribute("message","아이디나 비밀번호가 정확하지 않습니다.");
         return "redirect:/member/login";
      }
      
      session.setAttribute("authentication", certifiedUser);
      logger.debug("세션 안담겨??!!!"  + certifiedUser.toString());
      return "redirect:/project/project-list";
      
      
   }

   
//   로그아웃
   @GetMapping("logout.do")
   public String logout (HttpServletRequest request) throws Exception{
      logger.info("logout메서드 진입");
      
      HttpSession session = request.getSession();
      session.invalidate();

      
      return "redirect:/member/login";
      
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
   System.out.println("googleId : " + googleId);
   System.out.println("googleName : " + googleName);
   session.setAttribute("googleId", googleId);

   
   redirectAttr.addFlashAttribute("name", googleName);
   //////////////////////////////////////////
   Member GoogleUser = memberServiceImpl.selectGoogleId(googleId);
   
   System.out.println("GoogleUser 서비스로 보낸값 받은거 : "+GoogleUser);
   
   System.out.println("보낸후 googleId : " + googleId);
   System.out.println("보낸후 googleName : " + googleName);
   
      if(GoogleUser == null) {
         System.out.println("아이디가 존재하지 않으면 소셜조인폼으로 ");
         return "redirect:/member/social-join";
      }
   
      session.setAttribute("authentication", GoogleUser);
      
   //id가 존재하여 템플릿으로 이동.
   return "redirect:/project/project-list";
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
         
      if(session.getAttribute("kakaoId") != null) {
         form.setSocialId((String)session.getAttribute("kakaoId"));
      }else {
         form.setSocialId((String)session.getAttribute("googleId"));
      }
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
 public String redirectkakao(@RequestParam String code, HttpSession session ,RedirectAttributes redirectAttr ) throws IOException {
 // 접속토큰 get
    System.out.println("code:"+code);
    
    
    Map<String,Object> kakaoUser = memberService.kakaoCallback(code);
    
    Map<String,String> kakaoProperties =(Map<String,String>) kakaoUser.get("properties");
    String kakaoName = kakaoProperties.get("nickname");
    String kakaoId = String.valueOf(kakaoUser.get("id"));
    System.out.println("kakaoUser"+kakaoUser);
    System.out.println("kakaoId : " + kakaoId);
    System.out.println("kakaoName : " + kakaoName);
    session.setAttribute("kakaoId", kakaoId);
    Member member =  memberService.selectKakaoId(kakaoId);
    session.setAttribute("authentication", member);
    redirectAttr.addFlashAttribute("name", kakaoName);
    
    if(member == null) {
        System.out.println("아이디가 존재하지 않으면 소셜조인폼으로 ");
        return "redirect:/member/social-join";
     }
  

  //id가 존재하여 템플릿으로 이동.
  return "redirect:/project/project-list";
           
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

 	//**********Mypage Code***********
 	@GetMapping("mypage") 
	public String mypage(HttpSession session, Model model) {
		
		Member member = (Member) session.getAttribute("authentication");
		logger.debug(member.toString());
		//프로필 사진 추출
		FileDTO fileDTO = memberService.selectProfileImgFilebyMemberIdx(member);
		
		session.setAttribute("authentication", member);
		
		if(fileDTO == null) {
			System.out.println("1. fileDTO 없음 진입확인");
			session.setAttribute("profileImg", "person.png");
		} else {
			session.setAttribute("profileImg", fileDTO.getSavePath()+fileDTO.getRenameFileName());
		}
		
		// Model에 error 객체 추가 
		model.addAttribute(new MypageForm()).addAttribute("error",new ValidateResult().getError());
		return "member/mypage"; 
	}
 
 	@PostMapping("profileColor")
	@ResponseBody 
	public String changeProfileColor(@Validated MypageForm mypageForm
			,Errors errors 
			,HttpSession session) {
		
		try {
			//1) 값 Validate 검증
			if(errors.hasErrors()) {
				return "failed"; 
			}
			//2) 색 추출, SESSION_Member에 넣어 DB저장, 이후 SESSION update 
			String profileColor = "#" + mypageForm.getProfileColor();
			Member tempMember = (Member) session.getAttribute("authentication");
			tempMember.setProfileColor(profileColor); 
			
			int res = memberService.updateMypageMemberByProfileColor(tempMember);
			session.setAttribute("authentication", tempMember);
		} catch(Exception e) {
			e.printStackTrace();
			return "failed";
		}
			//3) color값 반환 
			return "#" + mypageForm.getProfileColor(); 
	}
	
	@PostMapping("profileImg")
	@ResponseBody
	public String changeProfileImg(@RequestParam List<MultipartFile> files
			,HttpSession session) {
		
		//1) 파일 추출 및 DB저장 
		FileUtil fileUtil = new FileUtil(); 
		FileDTO fileUploaded = fileUtil.fileUpload(files.get(0));
	
		try {
			Member member = (Member) session.getAttribute("authentication");
			System.out.println(member.getUserIdx());
			int res = memberService.insertMemberProfileImg(fileUploaded, member.getUserIdx()); 
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		//2) profileImg는 join을 통해 추출, session 업데이트 불필요
		logger.debug(fileUploaded.getSavePath());
		logger.debug(fileUploaded.getRenameFileName());
		return fileUploaded.getSavePath() +fileUploaded.getRenameFileName(); 
	}
	
	@PostMapping(value= "profileNickname", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String changeProfileNickname(@Validated MypageForm mypageForm
			,Errors errors
			,HttpSession session) {
		
		try {
			//1) 값 validate 검증 
			if(errors.hasErrors()) {
				return "failed";
			}
			//2) 닉네임 추출, SESSION_Member에 넣어 DB저장, 이후 SESSION update
			String nickname = mypageForm.getNickname();
			Member member = (Member) session.getAttribute("authentication"); 
			member.setNickname(nickname);
			
			int res = memberService.updateMypageMemberByNickname(member);
			session.setAttribute("authentication", member);
		} catch(Exception e) {
			e.printStackTrace(); 
			return "failed";
		}
			//3) nickname 반환 
			return mypageForm.getNickname(); 
	}
	
	@PostMapping(value= "profileGitRepo", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String changeProfileGitRepo(@Validated MypageForm mypageForm
			,Errors errors
			,HttpSession session) {
		
		try {
			//1) 값 validate 검증 
			if(errors.hasErrors()) {
				return "failed";
			} 
			Member member = (Member) session.getAttribute("authentication"); 
			member.setGit(mypageForm.getGit());
			
			
			//2) git 추출, SESSION_Member에 넣어 DB저장, 이후 SESSION update
			int res = memberService.updateMypageMemberByGit(member); 
			session.setAttribute("authentication", member);
		} catch(Exception e) {
			e.printStackTrace(); 
			return "failed"; 
		}
			//3) git 반환 
			return mypageForm.getGit(); 
	}
	
	@PostMapping("profilePassword")
	@ResponseBody
	public String changeProfilePassword(@Validated MypageForm mypageForm
			,Errors errors
			,HttpSession session) {
		try {
			//1) 값 validate 검증 
			if(errors.hasErrors()) {
				return "failed";
			} 
			
			// 맴버를 꺼낸다.  
			Member member = (Member) session.getAttribute("authentication");
			// 맴버를 보낸다 ( service단에서 password 변경까지 처리 ) 
			String password = mypageForm.getPassword(); 
			member.setPassword(password);
			// 변경된 패스워드 추가까지 마무리 
			Member resMember = memberService.updateMypageMemberByPassword(member); 
			member.setPassword(resMember.getPassword());
			
			session.setAttribute("authentication", member);
		} catch(Exception e) {
			e.printStackTrace(); 
			return "failed"; 
		}
			//3) 성공 반환   
			return "success";  
	}
	
	
	@PostMapping("isleave")
	@ResponseBody
	public String changeMemberIsleave(HttpSession session) {
			
		//1. session에서 member추출, isLeave 변경 
		try {
			Member member = (Member) session.getAttribute("authentication"); 
			int res = memberService.memberIsleave(member); 
			session.removeAttribute("authentication");
		} catch(Exception e) {
			e.printStackTrace();
			return "failed"; 
		}
		//2. return "success"
		return "success"; 
	}
 
 	
 	//*********아이디, 비밀번호 찾기 페이지*********
	// 1. searchPassword로 forwarding 
 	@GetMapping("searchPassword")
 	public void searchPassword() {}
 	
 	// 2. searchPassword에서 이메일 값 입력, 처리 
 	@PostMapping("searchPassword") 
 	public String searchMemberPassword(@Validated EmailForm emailForm
 			,Errors errors 
 			,HttpSession session
 			,RedirectAttributes redirectAttr 
 			) {
 		
 		try {
 			if(errors.hasErrors()) {
 	 			redirectAttr.addFlashAttribute("message", "잘못된 이메일 값입니다. 다시 입력하세요");
 	 			return "redirect:/member/searchPassword"; 
 	 		}
 			Member member = memberService.selectMemberByEmail(emailForm.getEmail());  
 	 		
 	 		if(member == null) {
 	 			redirectAttr.addFlashAttribute("message", "존재하지 않는 이메일입니다. 다시 입력하세요");
 	 			return "redirect:/member/searchPassword"; 
 	 		}

 	 		String token  = UUID.randomUUID().toString();
 	 	    Date date = new Date(); 
 	 		
 	 		session.setAttribute("persistToken", token); 
 	 		session.setAttribute("emailSendMember", member);
 	 		session.setAttribute("emailSendTime", date.getTime());
 	 	    memberService.sendPasswordChangeURLByEmail(member, token, emailForm.getProzSendDate());
 	 		 
 	 		
 		} catch(Exception e) {
 			e.printStackTrace();
 			return "redirect:/member/searchPassword";
 		}

 		redirectAttr.addFlashAttribute("message", "비밀번호 찾기 메일 발송이 완료되었습니다.");
 		return "redirect:/";
 	}

 	//3. 전송된 email에서 동적 URL 을 활용하여 접근 
 	@GetMapping("change-password/{token}")
    public String changePassword(@PathVariable String token
    				  ,@SessionAttribute(value = "persistToken", required = false) String persistToken
                      ,RedirectAttributes redirectAttr
    				  ,HttpSession session
                      ) {
 		if(!token.equals(persistToken)) {
 			redirectAttr.addFlashAttribute("message", "만료된 페이지입니다. 패스워드 변경 이메일을 다시 보내주세요.");
	 		return "redirect:/"; 
 	    }

       return "redirect:/member/changePassword";
    }
 	
 	//4. Email 발송시각과 접근시각을 비교하여 만료시간 검증 
 	@GetMapping("changePassword")
 	public String changePassword(RedirectAttributes redirectAttr
 			,HttpSession session) {
 		
 		long second = (long) session.getAttribute("emailSendTime")/1000;
 		logger.debug("1. 이메일을 보낸 시각은 : " + second);
 		Date date = new Date(); 
 		long currentSecond = date.getTime()/1000;
 		logger.debug("2. 현재 시각은 : " + currentSecond);
 		long passedTime = currentSecond - second;
 		logger.debug("3. 지난 시각은 :  " + passedTime + "초 입니다.");
 		
 		// 만료되었을 경우 index로 redirect 
 		if(passedTime > 300) {
 			redirectAttr.addFlashAttribute("message", "메일 발송 이후 5분이 지났습니다. 패스워드 변경 이메일을 다시 보내주세요.");
 			return "redirect:/"; 
 		}
 		
 		// 만료 전일 경우 정상적인 changePassword 사이트로 forward 
 		return "member/changePassword"; 

 	}
 	
 	//5. changePassword.jsp에서 발송한 값을 DB에 입력 
 	@PostMapping("changePassword")
 	public String passwordChange(@Validated ChangePasswordForm changePasswordForm
 			,Errors errors
 			,HttpSession session
 			,RedirectAttributes redirectAttr) {
 		
 		try {
 			if(errors.hasErrors()) {
 	 			redirectAttr.addFlashAttribute("message", "잘못된 패스워드 값입니다. 다시 입력하세요");
 	 			return "redirect:/member/changePassword"; 
 	 		}
 			
 			Member member = (Member) session.getAttribute("emailSendMember"); 
 			member.setPassword(changePasswordForm.getPassword());
 			// 자동 protect 전환 및 update, 맴버 반환까지 처리 
 			Member changedMember = memberService.updateMypageMemberByPassword(member);  
 		} catch(Exception e) {
 			e.printStackTrace();
 			redirectAttr.addFlashAttribute("message", "비밀번호 찾기 이메일이 만료되었습니다. 재발급 받아주세요");
 			return "redirect:/member/searchPassword";
 		}

 		redirectAttr.addFlashAttribute("message", "비밀번호 변경이 완료되었습니다.");
 		return "redirect:/member/login";
 		
 	}
 	
 }
   
   
   


