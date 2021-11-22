package com.kh.spring.cash.model.dto;

import java.sql.Date;

public class Cash {

	private String projectIdx;
	private String memberUpgrade;
	private String storageUpgrade;
	private String alarmUpgrade;
	private Date dateMember;
	private Date dateStorage;
	private Date dateAlarm;
	public String getProjectIdx() {
		return projectIdx;
	}
	public void setProjectIdx(String projectIdx) {
		this.projectIdx = projectIdx;
	}
	public String getMemberUpgrade() {
		return memberUpgrade;
	}
	public void setMemberUpgrade(String memberUpgrade) {
		this.memberUpgrade = memberUpgrade;
	}
	public String getStorageUpgrade() {
		return storageUpgrade;
	}
	public void setStorageUpgrade(String storageUpgrade) {
		this.storageUpgrade = storageUpgrade;
	}
	public String getAlarmUpgrade() {
		return alarmUpgrade;
	}
	public void setAlarmUpgrade(String alarmUpgrade) {
		this.alarmUpgrade = alarmUpgrade;
	}
	public Date getDateMember() {
		return dateMember;
	}
	public void setDateMember(Date dateMember) {
		this.dateMember = dateMember;
	}
	public Date getDateStorage() {
		return dateStorage;
	}
	public void setDateStorage(Date dateStorage) {
		this.dateStorage = dateStorage;
	}
	public Date getDateAlarm() {
		return dateAlarm;
	}
	public void setDateAlarm(Date dateAlarm) {
		this.dateAlarm = dateAlarm;
	}
	@Override
	public String toString() {
		return "Cash [projectIdx=" + projectIdx + ", memberUpgrade=" + memberUpgrade + ", storageUpgrade="
				+ storageUpgrade + ", alarmUpgrade=" + alarmUpgrade + ", dateMember=" + dateMember + ", dateStorage="
				+ dateStorage + ", dateAlarm=" + dateAlarm + "]";
	}
	
	
	
}
