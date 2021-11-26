package com.kh.spring.common.code;

public enum WorkspaceType {

	LD("로드맵"),
	ME("메모장"),
	CH("채팅"),
	BO("게시판"),
	CL("캘린더");
	
	public final String TYPE;

	private WorkspaceType(String TYPE) {
		this.TYPE = TYPE;
	}
	
	
	
	
	
}
