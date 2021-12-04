package com.kh.spring.common.code;

public enum CashItem {
	
	MEMBER_COUNT_UPGRADE("멤버 수 추가", 5000, 30),
	WORKSPACE_UPGRADE("워크스페이스 추가", 5000, 30),
	CHAT_UPGRADE("채팅 기능", 3000, 30),
	ALL_UPGRADE("전체 결제", 12000, 30);
	
	public final String ITEM_NAME;
	public final int CHARGE;
	public final int TERM;
	
	
	
	private CashItem(String ITEM_NAME, int CHARGE, int TERM) {
		this.ITEM_NAME = ITEM_NAME;
		this.CHARGE = CHARGE;
		this.TERM = TERM;
	}
	
	
}
