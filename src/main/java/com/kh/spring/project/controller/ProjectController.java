package com.kh.spring.project.controller;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.spring.common.code.ErrorCode;
import com.kh.spring.common.exception.HandlableException;
import com.kh.spring.common.util.json.JsonMaker;
import com.kh.spring.common.util.map.CamelMap;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.project.model.dto.Project;
import com.kh.spring.project.model.dto.ProjectMember;
import com.kh.spring.project.model.dto.ProjectRole;
import com.kh.spring.project.model.service.ProjectService;

@Controller
@RequestMapping("project")
public class ProjectController {
   

	@Autowired
	ProjectService projectService;
	
	@Autowired
	MemberService memberSerivce;

	Logger logger = LoggerFactory.getLogger(this.getClass());


   
   @GetMapping("welcome")
   public void welcome() {};
   
   //==========================================지영 작업start=====================
   
   
   @GetMapping("setting/member-management/{projectIdx}")
   public String memberManagement(Model model , @PathVariable String projectIdx) {
      
      
      //프로젝트Idx가 유효한지 확인한다
      
      Project project = projectService.selectProjectByIdx(projectIdx); 
      //만약 프로젝트가 없으면?
      if(project==null) {
         
         throw new HandlableException(ErrorCode.PROJECT_URL_ERROR);
         
      }
      
      //멤버 권한 불러오기
      List<ProjectRole> projectRoleList =  projectService.selectProjectRoleByIdx(projectIdx); 
      //멤버 리스트 불러오기
      List<Map<String, Object>> projectMemberList = projectService.selectProjectMemberRoleByProjectIdx(projectIdx);
      
      
      model.addAttribute(project);
      model.addAttribute("projectRole",projectRoleList); 
      model.addAttribute("projectMember" , CamelMap.changeListMap(projectMemberList));

      return "project/setting/member-management";
      
      
   }
   
   //프로젝트 코드로 초대
   @GetMapping("invite/{inviteCode}")
   public String memberinviteByCode(@PathVariable String inviteCode
		 //  ,@SessionAttribute("authentication") Member member
		   ) {
	   
	   System.out.println(inviteCode);
	   Project project = projectService.selectProjectByInviteCode(inviteCode);
	   Member member = new Member();
	   member.setEmail("zo4870@naver.com");
	   member.setNickname("간지0영");
	   member.setProfileColor("#99B5CF");
	   member.setUserIdx("100023");

	   projectService.inviteMemberByCode(inviteCode,  member);
	   
	   
	   if(project == null) {
		   
		   throw new HandlableException(ErrorCode.PROJECT_URL_ERROR);
		   
	   }
	   
	   
	   
	   
	   return "redirect:/";
   }
   
   
   
   
   @PostMapping("reset-invite-link")
   @ResponseBody
   public String resetInviteLink(@RequestBody Project project) {
	   
	   logger.debug("프로젝트:" + project.toString());
	   String newUuid = projectService.updateProjectInviteCode(project.getProjectIdx()) ;
	   System.out.println(newUuid);
	   
	   return newUuid;
	   
   }
   
   
   
   
   
   @PostMapping(value="invite-member", produces = "application/text; charset=UTF-8")
   @ResponseBody
   public String inviteMember(@RequestBody Map<String , String> map
		   ,HttpSession session
		) {
      	   logger.debug(map.toString()); 

      
	  List<Map<String, Object>> memberList = projectService.selectUserAndMemberByEmail(map.get("email"));
	  System.out.println("멤버리스트: " + memberList);
      
      //아예 멤버가 없음
      if(memberList.size()==0) {
    	  return "no-member";
      }
      
      //멤버가 프로젝트에 이미 추가 돼 있음
      for(Map<String, Object> member : memberList) {
    	  if( map.get("projectIdx").equals(member.get("projectIdx"))) {
    		  return "member-exists";
    	  }
      }
      
  
      Member newMember = memberSerivce.selectMemberByEmail(map.get("email"));
      Project project = projectService.selectProjectByIdx(map.get("projectIdx"));
      
      projectService.inviteMemberByEmail(newMember , project);
      
     // projectService.insertProjectMember(newMember , project);
      
      logger.debug(memberList.toString()); 
      

      
      return JsonMaker.json(newMember);
   }
   
   
   
   @GetMapping("join-by-email/{userIdx}/{projectIdx}")
   public String JoinByEmail(@PathVariable String projectIdx ,
		   @PathVariable String userIdx) {
	   
	   

	   int res = projectService.inviteMemberByEmailImpl(projectIdx, userIdx);
	   
	   if(res==0) {
		   throw new HandlableException(ErrorCode.PROJECT_INVITATION_REJECTED);
	   }
	   
	   return "redirect:/";
   }
   
   
   @GetMapping("fcm-test")
   public void fcmText() {

	   
   }
   
   
   //멤버 추방
   @PostMapping("exile") 
   @ResponseBody
   public String memberExile(@RequestBody  Map<String , String> map) {

	   
	   projectService.deleteProjectMember(map);
	   
	   
	   return "complete";
	   
   }
   
   
   //멤버 초대 취소
   @PostMapping("invite-cancel")
   @ResponseBody
   public String inviteCancel(@RequestBody  Map<String , String> map) {
	   
	   System.out.println(map);
	   projectService.deleteProjectMember(map);
	   
	   return "complete";
   };
   
   //멤버 초대 취소
   @PostMapping("invite-cancel-by-userIdx")
   @ResponseBody
   public String inviteCancelByUserIdx(@RequestBody  Map<String , String> map) {
	   
	   System.out.println(map);
	   projectService.deleteProjectMember(map);
	   
	   return "complete";
   };
   
   
   //멤버 초대 취소
   @PostMapping("change-member-auth")
   @ResponseBody
   public String changeMemberAuth(@RequestBody  Map<String , String> map) {
	   
	   System.out.println(map);
	   projectService.updateProjectMemberAuth(map);
	   
	   
	   return "complete";
   };
   
   

   
   
 //==========================================지영 작업 end=====================
 
   
   //============================================민협 작업 start=================================================================
   
   
   @GetMapping("setting/role-management/{projectIdx}")
   public String settingRoleManagement(Model model , @PathVariable String projectIdx) {
      
      List<ProjectRole> roleList = projectService.selectProjectRoleByIdx(projectIdx);
      
      model.addAttribute("roleList",roleList);
      model.addAttribute("roleCnt",roleList.size());
      model.addAttribute("projectIdx",projectIdx);
      
      return "project/setting/role-management";
      
   };
   
   @PostMapping("setting/role-management/{projectIdx}")
   public String RoleManagementSettingSave(Model model,@PathVariable String projectIdx ,
                                 @RequestBody List<Map<String,String>> roleList) {
      
      //List<ProjectRole> roleList = 
      System.out.println(roleList.toString());
      
      for (Map<String, String> map : roleList) {
         ProjectRole role = new ProjectRole();
         role.setProjectIdx(projectIdx);
         role.setAuthName(map.get("authName"));
         role.setProjectAuth(Integer.parseInt(map.get("projectAuth")));
         role.setCreateAuth(Integer.parseInt(map.get("createAuth")));
         role.setMemberAuth(Integer.parseInt(map.get("memberAuth")));
         role.setAuthIdx(map.get("authIdx"));
         
         System.out.println(role.toString());
         
         

         if (map.get("state").equals("none")) {
            System.out.println("안바뀜");

         } else if (map.get("state").equals("update")) {
            System.out.println("바뀜");
            projectService.updateRoleByAuthNameAndProjectIdx(role);
            

            
         } else if (map.get("state").equals("new")) {
            System.out.println("새로생성됨");
            projectService.insertNewRole(role);
            
         } else if (map.get("state").equals("delete")) {
            System.out.println("삭제됨");
            
            if(role.getAuthIdx().equals("")) {
               projectService.deleteRoleByProjectIdxAndAuthName(role);
            }else {
               projectService.deleteRoleByAuthIdx(role.getAuthIdx());
            }

         }

      }
      
      
      return "통신 성공?";
   
   }
   
   
   
   
   
   

   //============================================민협 작업 end=================================================================
   
   
   
 //========================================은비 작업=========================
   
   @GetMapping("project-list")
   public String enterProjectList( @SessionAttribute("authentication") Member member,
                              Model model,
                                HttpSession session) {
      
      //(추후 추가할 코드) 만약 로그인을 하지 않고 접근을 한다면, 로그인 페이지로 or 로그인 페이지로 이동해달라고 하기
      
      System.out.println("project-list접근!");
      
      //member가 속한 프로젝트 list 정보를 보내기
      String userIdx = member.getUserIdx();
      
      //member가 속한 프로젝트 list
      List<String> projectIdxList = projectService.selectProjectIdxByUserIdx(userIdx);
      
      List<Project> projectList = new ArrayList<Project>();
      
      //프로젝트의 상세 정보 저장하기
      for(String projectIdx : projectIdxList) {
         projectList.addAll(projectService.selectProjectByProjectIdx(projectIdx));
      }
      
      model.addAttribute(projectList);
      
      return "project/project-list";
   }
   
   
   @PostMapping("project-list")
   public String createProject(@RequestBody Project project, 
                           @SessionAttribute("authentication") Member member,
                           HttpSession session) {
      System.out.println(member.toString());
     System.out.println("project-list 통신 되나요?");
     
      logger.debug(project.getProName());
      logger.debug(project.getProDescription());
      
      String proName = project.getProName();
      String proDescription = project.getProDescription();
      String userIdx = member.getUserIdx();
      
      System.out.println("userIdx : " + userIdx );
      
      //새로운 프로젝트의 초대코드 생성
      String inviteCode = UUID.randomUUID().toString();
      
      //새로운 프로젝트 생성
      projectService.insertProject(proName, proDescription, inviteCode, userIdx);
     
      //생성 되고나도 원래 페이지에 있도록 유지
      return "proejct/project-list";
   };
   
   
   
   @PostMapping("{projectIdx}") //페이지 이동은 get, 요청은 post
   public String enterProjectMain(@PathVariable String projectIdx,
                           HttpServletResponse response) throws Exception {
      
      return "project/project-main";
   };
   
   //프로젝트 상세로 이동
   @GetMapping("{projectIdx}")
   public String enterProjectMain(@PathVariable String projectIdx
                        ,@SessionAttribute("authentication") Member member
                        ,HttpSession session) {
     
     String userIdx = member.getUserIdx();
     Project project = projectService.selectProjectExist(projectIdx);
     
      //프로젝트가 없을 경우
      if(project == null) {
         throw new HandlableException(ErrorCode.PROJECT_URL_ERROR);
      }
      
      //로그인 하지 않았을 경우
      if(userIdx == null) {
         throw new HandlableException(ErrorCode.NEED_LOGIN);
      }
      
      //프로젝트 멤버가 아닐 경우
      List<ProjectMember> projectMember = new ArrayList<ProjectMember>();
      projectMember = projectService.selectProjectMemberByUserIdx(userIdx);
      if(projectMember == null) {
         throw new HandlableException(ErrorCode.AUTHENTICATION_FAILED_ERROR);
      }
      
      return "project/project-main";
   }
   
   
   
  // ========================================은비 작업 끝=========================
   
   
   
   
   
   
   
   
   
}
