package com.kh.spring.calendar.model.dto;

import java.sql.Date;

public class Calendar {

	private String calIdx;
	private String wsIdx;
	private String pmIdx;
	private String calTitle;
	private String calContent;
	private String calColor;
	private Date regDate;
	private Date startDate;
	private Date endDate;

	public Calendar() {
		// TODO Auto-generated constructor stub
	}

	public String getCalIdx() {
		return calIdx;
	}

	public void setCalIdx(String calIdx) {
		this.calIdx = calIdx;
	}

	public String getWsIdx() {
		return wsIdx;
	}

	public void setWsIdx(String wsIdx) {
		this.wsIdx = wsIdx;
	}

	public String getPmIdx() {
		return pmIdx;
	}

	public void setPmIdx(String pmIdx) {
		this.pmIdx = pmIdx;
	}

	public String getCalTitle() {
		return calTitle;
	}

	public void setCalTitle(String calTitle) {
		this.calTitle = calTitle;
	}

	public String getCalContent() {
		return calContent;
	}

	public void setCalContent(String calContent) {
		this.calContent = calContent;
	}

	public String getCalColor() {
		return calColor;
	}

	public void setCalColor(String calColor) {
		this.calColor = calColor;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Calendar [calIdx=" + calIdx + ", wsIdx=" + wsIdx + ", pmIdx=" + pmIdx + ", calTitle=" + calTitle
				+ ", calContent=" + calContent + ", calColor=" + calColor + ", regDate=" + regDate + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}

}
