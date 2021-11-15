package com.kh.spring.project.model.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.kh.spring.common.code.Config;
import com.kh.spring.common.code.ErrorCode;
import com.kh.spring.common.exception.HandlableException;
import com.kh.spring.common.mail.MailSender;
import com.kh.spring.common.util.map.CamelMap;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.repository.MemberRepository;
import com.kh.spring.project.model.dto.Project;
import com.kh.spring.project.model.dto.ProjectMember;
import com.kh.spring.project.model.dto.ProjectRole;
import com.kh.spring.project.model.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	private MailSender mailSender;

	@Autowired
	private RestTemplate http;

	@Override
	public String updateProjectInviteCode(String projectIdx) {

		String newUuid = UUID.randomUUID().toString();
		projectRepository.updateProjectInviteCode(newUuid, projectIdx);

		return newUuid;

	}

	@Override
	public Project selectProjectByIdx(String projectIdx) {

		return projectRepository.selectProjectByIdx(projectIdx);
	}

	@Override
	public List<Map<String, Object>> selectProjectMemberByProjectIdx(String projectIdx) {

		return projectRepository.selectProjectMemberByProjectIdx(projectIdx);
	}

	@Override
	public List<Map<String, Object>> selectProjectMemberRoleByProjectIdx(String projectIdx) {
		return projectRepository.selectProjectMemberRoleByProjectIdx(projectIdx);
	}

	// 지영 추가 코드

	@Override
	public List<Map<String, Object>> selectUserAndMemberByEmail(String email) {

		return CamelMap.changeListMap(projectRepository.selectUserAndMemberByEmail(email));

	}

	@Override
	public void inviteMemberByEmail(Member newMember, Project project) {

		Map<String, Object> defaultRole = CamelMap
				.changeMap(projectRepository.selectDefaultOfProjectRole(project.getProjectIdx()));
		String authIdx = (String) defaultRole.get("authIdx"); // 일반 idx가 들어있음

		ProjectMember newProjectMember = new ProjectMember();
		newProjectMember.setProjectIdx(project.getProjectIdx());
		newProjectMember.setUserIdx(newMember.getUserIdx());
		newProjectMember.setAuthIdx(authIdx);
		newProjectMember.setNickname(newMember.getNickname());
		newProjectMember.setProfileColor(newMember.getProfileColor());
		newProjectMember.setIsOk(0);

		projectRepository.insertProjectMember(newProjectMember);

		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("mailTemplate", "project-invite-mail");
		body.add("nickname", newMember.getNickname());
		body.add("projectIdx", project.getProjectIdx());
		body.add("userIdx", newMember.getUserIdx());

		// RestTemplate의 기본 Content-type : application/json
		RequestEntity<MultiValueMap<String, String>> request = RequestEntity.post(Config.DOMAIN.DESC + "/mail")
				.accept(MediaType.APPLICATION_FORM_URLENCODED).body(body);

		String subject = project.getProName() + " 프로젝트에서 당신을 초대합니다.";
		String htmlTxt = http.exchange(request, String.class).getBody();
		mailSender.send(newMember.getEmail(), subject, htmlTxt);

	}

	// 이메일 클릭시
	@Override
	public int inviteMemberByEmailImpl(String projectIdx, String userIdx) {

		return projectRepository.updateProjectMemberStatus(projectIdx, userIdx);

	}

	@Override
	public Project selectProjectByInviteCode(String inviteCode) {
		return projectRepository.selectProjectByInviteCode(inviteCode);

	}

	public void inviteMemberByCode(String inviteCode, Member member) {

		Project project = projectRepository.selectProjectByInviteCode(inviteCode);
		if (project == null) {
			throw new HandlableException(ErrorCode.PROJECT_URL_ERROR);
		}

		List<Map<String, Object>> projectMemberList = projectRepository
				.selectProjectMemberByProjectIdx(project.getProjectIdx());
		System.out.println(projectMemberList);
		for (Map<String, Object> pm : projectMemberList) {

			if (pm.get("USER_IDX").equals(member.getUserIdx())) {
				System.out.println("이미 존재하는 사용자입니다.");
				return;

			}

		}

		Map<String, Object> defaultRole = CamelMap
				.changeMap(projectRepository.selectDefaultOfProjectRole(project.getProjectIdx()));
		String authIdx = (String) defaultRole.get("authIdx"); // 일반 idx가 들어있음

		ProjectMember projectMember = new ProjectMember();
		projectMember.setProjectIdx(project.getProjectIdx());
		projectMember.setUserIdx(member.getUserIdx());
		projectMember.setAuthIdx(authIdx);
		projectMember.setNickname(member.getNickname());
		projectMember.setProfileColor(member.getProfileColor());
		projectMember.setIsOk(1);

		projectRepository.insertProjectMember(projectMember);

	}

	public void deleteProjectMember(Map<String, String> map) {

		projectRepository.deleteProjectMember(map);

	};

	public void updateProjectMemberAuth(Map<String, String> map) {

		projectRepository.updateProjectMemberAuth(map);

	};

	// 지영 추가코드 끝

	// 민협 코드 시작
	@Override
	public List<ProjectRole> selectProjectRoleByIdx(String projectIdx) {

		return projectRepository.selectProjectRoleByIdx(projectIdx);
	}

	@Override
	public void updateRoleByAuthNameAndProjectIdx(ProjectRole role) {
		projectRepository.updateRoleByAuthNameAndProjectIdx(role);
	}

	@Override
	public void insertNewRole(ProjectRole role) {
		projectRepository.insertNewRole(role);

	}

	@Override
	public void deleteRoleByAuthIdx(String authIdx) {
		projectRepository.deleteRoleByAuthIdx(authIdx);

	}

	@Override
	public void deleteRoleByProjectIdxAndAuthName(ProjectRole role) {
		projectRepository.deleteRoleByProjectIdxAndAuthName(role);
	}

	// 민협 코드 끝
////////////은비가 작성한 코드 시작
	@Override
	public List<ProjectMember> selectProjectMemberByUserIdx(String userIdx) {

		return projectRepository.selectProjectMemberByUserIdx(userIdx);
	}

	@Override
	public void insertProject(String proName, String proDescription, String inviteCode, String userIdx) {
		projectRepository.insertProject(proName, proDescription, inviteCode);
		projectRepository.insertRole();
		projectRepository.insertAdmin(userIdx);
	}

	@Override
	public List<String> selectProjectIdxByUserIdx(String userIdx) {
		return projectRepository.selectProjectIdxByUserIdx(userIdx);
	}

	@Override
	public List<Project> selectProjectByProjectIdx(String projectIdx) {
		return projectRepository.selectProjectByProjectIdx(projectIdx);
	}

	@Override
	public Project selectProjectExist(String projectIdx) {
		return projectRepository.selectProjectExist(projectIdx);
	}

//은비가 작성한 코드 끝

}
