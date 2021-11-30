package com.kh.spring.loadmap.model.dto;

import java.util.List;

public class Loadmap {
	
	
	private String lmIdx;
	private String wsIdx;
	private String gitRepo;
	private String gitTree;
	private String branch;
	private List<String> ignore;
	
	public Loadmap() {
		// TODO Auto-generated constructor stub
	}

	
	public String getLmIdx() {
		return lmIdx;
	}


	public void setLmIdx(String lmIdx) {
		this.lmIdx = lmIdx;
	}


	public String getWsIdx() {
		return wsIdx;
	}


	public void setWsIdx(String wsIdx) {
		this.wsIdx = wsIdx;
	}


	public String getGitRepo() {
		return gitRepo;
	}


	public void setGitRepo(String gitRepo) {
		this.gitRepo = gitRepo;
	}


	public String getGitTree() {
		return gitTree;
	}


	public void setGitTree(String gitTree) {
		this.gitTree = gitTree;
	}


	public String getBranch() {
		return branch;
	}


	public void setBranch(String branch) {
		this.branch = branch;
	}


	public List<String> getIgnore() {
		return ignore;
	}


	public void setIgnore(List<String> ignore) {
		this.ignore = ignore;
	}


	@Override
	public String toString() {
		return "Loadmap [lmIdx=" + lmIdx + ", wsIdx=" + wsIdx + ", gitRepo=" + gitRepo + ", gitTree=" + gitTree
				+ ", branch=" + branch + ", ignore=" + ignore + "]";
	}


	



	
}
