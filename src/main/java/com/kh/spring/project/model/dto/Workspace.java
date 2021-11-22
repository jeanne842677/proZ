package com.kh.spring.project.model.dto;

import java.sql.Date;

public class Workspace {

	private String wsIdx;
	private String projectIdx;
	private String wsType;
	private String wsName;
	private int Sort;
	private String parent;
	private Date regDate;

	public Workspace() {
		// TODO Auto-generated constructor stub
	}

	public String getWsIdx() {
		return wsIdx;
	}

	public void setWsIdx(String wsIdx) {
		this.wsIdx = wsIdx;
	}

	public String getProjectIdx() {
		return projectIdx;
	}

	public void setProjectIdx(String projectIdx) {
		this.projectIdx = projectIdx;
	}

	public String getWsType() {
		return wsType;
	}

	public void setWsType(String wsType) {
		this.wsType = wsType;
	}

	public String getWsName() {
		return wsName;
	}

	public void setWsName(String wsName) {
		this.wsName = wsName;
	}

	public int getSort() {
		return Sort;
	}

	public void setSort(int sort) {
		Sort = sort;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Workspace [wsIdx=" + wsIdx + ", projectIdx=" + projectIdx + ", wsType=" + wsType + ", wsName=" + wsName
				+ ", Sort=" + Sort + ", parent=" + parent + ", regDate=" + regDate + "]";
	}

}
