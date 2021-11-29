package com.kh.spring.chat.model.dto;

import java.sql.Date;

public class Chat {

	
	private String chIdx;
	private String wsIdx;
	private String pmIdx;
	private String chName;
	private String content;
	private String isDel;
	private Date regDate;
	
	public String getChIdx() {
		return chIdx;
	}
	public void setChIdx(String chIdx) {
		this.chIdx = chIdx;
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
	public String getChName() {
		return chName;
	}
	public void setChName(String chName) {
		this.chName = chName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "Chat [chIdx=" + chIdx + ", wsIdx=" + wsIdx + ", pmIdx=" + pmIdx + ", chName=" + chName + ", content="
				+ content + ", isDel=" + isDel + ", regDate=" + regDate + "]";
	}
	
	

}
