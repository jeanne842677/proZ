package com.kh.spring.board.model.dto;

import java.sql.Date;

public class Board {

	private String bdIdx;
	private String wsIdx;
	private String bdName;
	private int sort;
	private int bdSize;
	private String parent;
	private Date regDate;

	public Board() {
		// TODO Auto-generated constructor stub
	}

	public String getBdIdx() {
		return bdIdx;
	}

	public void setBdIdx(String bdIDx) {
		this.bdIdx = bdIDx;
	}

	public String getWsIdx() {
		return wsIdx;
	}

	public void setWsIdx(String wsIdx) {
		this.wsIdx = wsIdx;
	}

	public String getBdName() {
		return bdName;
	}

	public void setBdName(String bdName) {
		this.bdName = bdName;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getBdSize() {
		return bdSize;
	}

	public void setBdSize(int bdSize) {
		this.bdSize = bdSize;
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
		return "Board [bdIdx=" + bdIdx + ", wsIdx=" + wsIdx + ", bdName=" + bdName + ", sort=" + sort + ", bdSize="
				+ bdSize + ", parent=" + parent + ", regDate=" + regDate + "]";
	}

}
