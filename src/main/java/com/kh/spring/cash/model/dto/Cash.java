package com.kh.spring.cash.model.dto;

import java.sql.Date;

public class Cash {

	private String cashIdx;
	private String cashName;
	private String projectIdx;
	private Date regDate;
	public String getCashIdx() {
		return cashIdx;
	}
	public void setCashIdx(String cashIdx) {
		this.cashIdx = cashIdx;
	}
	public String getCashName() {
		return cashName;
	}
	public void setCashName(String cashName) {
		this.cashName = cashName;
	}
	public String getProjectIdx() {
		return projectIdx;
	}
	public void setProjectIdx(String projectIdx) {
		this.projectIdx = projectIdx;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "Cash [cashIdx=" + cashIdx + ", cashName=" + cashName + ", projectIdx=" + projectIdx + ", regDate="
				+ regDate + "]";
	}
	
	
	
	
	
}
