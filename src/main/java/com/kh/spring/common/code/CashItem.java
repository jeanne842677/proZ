package com.kh.spring.common.code;

public enum CashItem {
	
	MEMBER_COUNT_UPGRADE("멤버 수 추가", 5000, 100), //기존은 10명
	WORKSPACE_UPGRADE("워크스페이스 추가", 5000, 100), //기존은 10개
	CHAT_UPGRADE("채팅 기능", 3000, 1),
	ALL_UPGRADE("전체 결제", 12000, 1);
	
	public final String ITEM_NAME;
	public final int CHARGE;
	public final int COUNT;
	
	
	
	public String getITEM_NAME() {
		return ITEM_NAME;
	}

	public int getCOUNT() {
		return COUNT;
	}



	private CashItem(String ITEM_NAME, int CHARGE, int COUNT) {
		this.ITEM_NAME = ITEM_NAME;
		this.CHARGE = CHARGE;
		this.COUNT = COUNT;
	}
	
	
}
