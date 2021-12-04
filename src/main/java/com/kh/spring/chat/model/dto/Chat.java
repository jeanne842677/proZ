package com.kh.spring.chat.model.dto;

import java.util.Date;

public class Chat {

	
	private int chIdx;	//채팅방 idx
	private String wsIdx;
	private String chName; //채팅방이름
	private String content;
	private String regDate;
	private String nickname;
	private String pmIdx;
	private String isFile;
	private String filePath;
	private String dataUrl;
	private String fileType;
	
	public int getChIdx() {
		return chIdx;
	}
	public void setChIdx(int chIdx) {
		this.chIdx = chIdx;
	}
	public String getWsIdx() {
		return wsIdx;
	}
	public void setWsIdx(String wsIdx) {
		this.wsIdx = wsIdx;
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPmIdx() {
		return pmIdx;
	}
	public void setPmIdx(String pmIdx) {
		this.pmIdx = pmIdx;
	}
	public String getIsFile() {
		return isFile;
	}
	public void setIsFile(String isFile) {
		this.isFile = isFile;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getDataUrl() {
		return dataUrl;
	}
	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	@Override
	public String toString() {
		return "Chat [chIdx=" + chIdx + ", wsIdx=" + wsIdx + ", chName=" + chName + ", content=" + content
				+ ", regDate=" + regDate + ", nickname=" + nickname + ", pmIdx=" + pmIdx + ", isFile=" + isFile
				+ ", filePath=" + filePath + ", dataUrl=" + dataUrl + ", fileType=" + fileType + "]";
	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	

}
