package com.kh.spring.loadmap.model.dto;

import java.sql.Date;
import java.util.List;

import com.kh.spring.common.util.json.JsonMaker;

public class GitCommit {

	private String gcIdx;
	private String lmIdx;
	private String login;
	private String message;
	private Date commitDate;
	private List<String> files;
	private Date regDaate;
	
	public GitCommit() {
		// TODO Auto-generated constructor stub
	}
	
	public String getGcIdx() {
		return gcIdx;
	}



	public void setGcIdx(String gcIdx) {
		this.gcIdx = gcIdx;
	}



	public String getLmIdx() {
		return lmIdx;
	}



	public void setLmIdx(String lmIdx) {
		this.lmIdx = lmIdx;
	}



	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public Date getCommitDate() {
		return commitDate;
	}



	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
	}



	public String getFiles() {
		return JsonMaker.json(files);
	}



	public void setFiles(List<String> files) {
		this.files = files;
	}



	public Date getRegDaate() {
		return regDaate;
	}



	public void setRegDaate(Date regDaate) {
		this.regDaate = regDaate;
	}



	@Override
	public String toString() {
		return "GitCommit [gcIdx=" + gcIdx + ", lmIdx=" + lmIdx + ", login=" + login + ", message=" + message
				+ ", commitDate=" + commitDate + ", files=" + files + ", regDaate=" + regDaate + "]";
	}

	

}
