package com.kh.spring.board.model.dto;

import java.sql.Date;

public class Post {

	private String postIdx;
	private String bdIdx;
	private String pmIdx;
	private String postTitle;
	private String postContent;
	private int sort;
	private Date regDate;
	private String postColor;

	public String getPostIdx() {
		return postIdx;
	}

	public void setPostIdx(String postIdx) {
		this.postIdx = postIdx;
	}

	public String getBdIdx() {
		return bdIdx;
	}

	public void setBdIdx(String bdIdx) {
		this.bdIdx = bdIdx;
	}

	public String getPmIdx() {
		return pmIdx;
	}

	public void setPmIdx(String pmIdx) {
		this.pmIdx = pmIdx;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getPostColor() {
		return postColor;
	}

	public void setPostColor(String postColor) {
		this.postColor = postColor;
	}

	@Override
	public String toString() {
		return "Post [postIdx=" + postIdx + ", bdIdx=" + bdIdx + ", pmIdx=" + pmIdx + ", postTitle=" + postTitle
				+ ", postContent=" + postContent + ", sort=" + sort + ", regDate=" + regDate + ", postColor="
				+ postColor + "]";
	}

}
