package com.kh.spring.project.model.dto;

public class ProjectRole {

	private String authIdx; //권한 인덱스
	private String projectIdx; //프로젝트 인덱스
	private String auth_name; //역할명
	private String grant_auth; //역할 관리 권한
	private String create_auth; //카테고리 생성 권한
	private String member_auth; // 멤버 관리 권한
	
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
	public String getAuth_name() {
		return auth_name;
	}
	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}
	public String getGrant_auth() {
		return grant_auth;
	}
	public void setGrant_auth(String grant_auth) {
		this.grant_auth = grant_auth;
	}
	public String getCreate_auth() {
		return create_auth;
	}
	public void setCreate_auth(String create_auth) {
		this.create_auth = create_auth;
	}
	public String getMember_auth() {
		return member_auth;
	}
	public void setMember_auth(String member_auth) {
		this.member_auth = member_auth;
	}
	@Override
	public String toString() {
		return "ProjectRole [authIdx=" + authIdx + ", projectIdx=" + projectIdx + ", auth_name=" + auth_name
				+ ", grant_auth=" + grant_auth + ", create_auth=" + create_auth + ", member_auth=" + member_auth + "]";
	} 
	
	
}
