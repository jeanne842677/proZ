package com.kh.spring.project.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.spring.common.code.ErrorCode;
import com.kh.spring.common.exception.HandlableException;
import com.kh.spring.common.util.json.JsonMaker;
import com.kh.spring.common.util.map.CamelMap;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.project.model.dto.Project;
import com.kh.spring.project.model.dto.ProjectMember;
import com.kh.spring.project.model.dto.ProjectRole;
import com.kh.spring.project.model.service.ProjectService;

@Controller
@RequestMapping("project")
public class ProjectController {
   

	@Autowired
	ProjectService projectService;

	Logger logger = LoggerFactory.getLogger(this.getClass());


   @GetMapping("project-list")
   public void list() {}; 
   
   @GetMapping("welcome")
   public void welcome() {};
   
   //==========================================지영 작업start=====================
   
   
   @GetMapping("setting/member-management/{projectIdx}")
   public String memberManagement(Model model , @PathVariable String projectIdx) {
      
      System.out.println("내가보낸 idx"+ projectIdx );
      
      //프로젝트Idx가 유효한지 확인한다
      
      Project project = projectService.selectProjectByIdx(projectIdx);
      System.out.println("제이슨 메이커: " + JsonMaker.json(project));
  
      
      
      //만약 프로젝트가 없으면?
      if(project==null) {
         
         throw new HandlableException(ErrorCode.PROJECT_URL_ERROR);
         
      }
      
      //멤버 리스트 불러오기
      List<Map<String, Object>> projectMemberList = projectService.selectProjectMemberByProjectIdx(projectIdx);
      List<Map<String, Object>> projectMemberList2 = projectService.selectProjectMemberRoleByProjectIdx(projectIdx);
  
      //권한 리스트 불러오기
      System.out.println(projectMemberList);
      System.out.println("리스트2:" + CamelMap.changeListMap(projectMemberList2));
      model.addAttribute(project);
      
      return "project/setting/member-management";
      
      
   }
   
   
   @PostMapping("reset-invite-link")
   @ResponseBody
   public String resetInviteLink(@RequestBody Project project) {
	   
	   logger.debug("프로젝트:" + project.toString());
	   String newUuid = projectService.updateProjectInviteCode(project.getProjectIdx()) ;
	   System.out.println(newUuid);
	   
	   return newUuid;
	   
   }
   
   
   
   
   
   @PostMapping("invite-member")
   public String inviteMember(Model model) {
      
      
      
      return "";
   }
   
   
   
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
                                 @RequestBody List<ProjectRole> roleList) {
      
      //List<ProjectRole> roleList = 
      System.out.println(roleList.toString());
      
      
      return "통신 성공?";
   
   }
   
   
   
   
   
   

   //============================================민협 작업 end=================================================================
   
   
   
 //========================================은비 작업=========================
   
   @PostMapping("project-list")
   public String createProject(@RequestBody Project project,
                           @SessionAttribute("authentication") Member member, 
                           HttpSession session) {
      
      logger.debug(project.getProName());
      logger.debug(project.getProDescription());
      
      String proName = project.getProName();
      String proDescription = project.getProDescription();
      String userIdx = member.getUserIdx();
      
      //새로운 프로젝트 생성
      Project newProject = projectService.createProject(proName, proDescription);
      String projectIdx = newProject.getProjectIdx();
      
      //관리자, 일반 생성
      ProjectRole newRole = projectService.createRole(projectIdx);
      int authIdx = Integer.parseInt(newRole.getAuthIdx());
      
      //만든 사람을 프로젝트 관리자로 등록 
      ProjectMember allocateAdmin = projectService.allocateAdmin(projectIdx, userIdx, authIdx);
     
      System.out.println(allocateAdmin.toString());
      
      //생성 되고나도 원래 페이지에 있도록
      return "proejct/project-list";
   };
   
   
   @GetMapping("{projectIdx}") //페이지 이동은 get, 요청은 post
   public void enterProjectMain() {};
   
   //프로젝트 상세로 이동
   @PostMapping("{projectIdx}")
   public String enterProjectMain(@PathVariable String projectIdx
                        ,@SessionAttribute(value="userIdx", required=false) String userIdx
                        ,HttpSession session) {
      
      //프로젝트가 없을 경우
      if(projectIdx == null) {
         throw new HandlableException(ErrorCode.PROJECT_URL_ERROR);
      }
      
      //프로젝트 멤버가 아닐 경우
      ProjectMember projectMember = projectService.selectProjectMemberByUserIdx(userIdx);
      if(projectMember == null) {
         throw new HandlableException(ErrorCode.AUTHENTICATION_FAILED_ERROR);
      }
      
      //세션에 권한 정보를 받아와서 입장하게끔
      session.setAttribute("authentication", projectMember);
      logger.debug(projectMember.toString());
      

      return "project/project-main";
   }
   
   //프로젝트 처음 생성할 때, 버튼 누르면 생성되던데
   // 관리자랑 일반이 생기고. 처음 가입한 사람은 관리자.
   
   
  // ========================================은비 작업 끝=========================
   
   
   
   
   
   
   
   
   
   
   
}
