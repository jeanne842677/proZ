package com.kh.spring.projectmember.dto;

public class ProjectMemberSession {

	private String userIdx;
	private String session;

	public ProjectMemberSession() {
	}

	public ProjectMemberSession(String userIdx, String session) {
		super();
		this.userIdx = userIdx;
		this.session = session;
	}

	public String getUserIdx() {
		return userIdx;
	}

	public void setUserIdx(String userIdx) {
		this.userIdx = userIdx;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	@Override
	public String toString() {
		return "ProjectMemberSession [userIdx=" + userIdx + ", session=" + session + "]";
	}

}
