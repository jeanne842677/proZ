package com.kh.spring.project.model.dto;

import java.sql.Date;

public class ProjectRole {

	private String authIdx; // 권한 인덱스
	private String projectIdx; // 프로젝트 인덱스
	private String authName; // 역할명
	private int projectAuth; // 프로젝트 관리 권한
	private int createAuth; // 채팅방 개설권한
	private int memberAuth; // 멤버 초대 권한
	private Date regDate; //생성일
	
	
	
	public String getAuthIdx() {
		return authIdx;
	}
	public void setAuthIdx(String authIdx) {
		this.authIdx = authIdx;
	}
	public String getProjectIdx() {
		return projectIdx;
	}
	public void setProjectIdx(String projectIdx) {
		this.projectIdx = projectIdx;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	public int getProjectAuth() {
		return projectAuth;
	}
	public void setProjectAuth(int projectAuth) {
		this.projectAuth = projectAuth;
	}
	public int getCreateAuth() {
		return createAuth;
	}
	public void setCreateAuth(int createAuth) {
		this.createAuth = createAuth;
	}
	public int getMemberAuth() {
		return memberAuth;
	}
	public void setMemberAuth(int memberAuth) {
		this.memberAuth = memberAuth;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "ProjectRole [authIdx=" + authIdx + ", projectIdx=" + projectIdx + ", authName=" + authName
				+ ", projectAuth=" + projectAuth + ", createAuth=" + createAuth + ", memberAuth=" + memberAuth
				+ ", regDate=" + regDate + "]";
	}
	
	
	
	

	
	

	
}
