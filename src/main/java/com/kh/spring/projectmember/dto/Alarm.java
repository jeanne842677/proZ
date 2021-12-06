package com.kh.spring.projectmember.dto;

import java.sql.Date;

public class Alarm {

	private String alIdx;
	private String userIdx;
	private String projectIdx;
	private String alType;
	private String alContent;
	private Date regDate;
	private String isLook;
	private String link;

	public Alarm() {
		// TODO Auto-generated constructor stub
	}

	public String getAlIdx() {
		return alIdx;
	}

	public void setAlIdx(String alIdx) {
		this.alIdx = alIdx;
	}

	public String getUserIdx() {
		return userIdx;
	}

	public void setUserIdx(String userIdx) {
		this.userIdx = userIdx;
	}

	public String getProjectIdx() {
		return projectIdx;
	}

	public void setProjectIdx(String projectIdx) {
		this.projectIdx = projectIdx;
	}

	public String getAlType() {
		return alType;
	}

	public void setAlType(String alType) {
		this.alType = alType;
	}

	public String getAlContent() {
		return alContent;
	}

	public void setAlContent(String alContent) {
		this.alContent = alContent;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getIsLook() {
		return isLook;
	}

	public void setIsLook(String isLook) {
		this.isLook = isLook;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Alarm [alIdx=" + alIdx + ", userIdx=" + userIdx + ", projectIdx=" + projectIdx + ", alType=" + alType
				+ ", alContent=" + alContent + ", regDate=" + regDate + ", isLook=" + isLook + ", link=" + link + "]";
	}

	

}
