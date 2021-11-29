package com.kh.spring.common.code;

public enum CashItem {
	
	MEMBER_COUNT_UPGRADE("멤버 수 추가", 8000, 30),
	ALARM_UPGRADE("알림 기능", 8000, 30),
	STORAGE_UPGRADE("용량 추가", 10000, 30),
	ALL_UPGRADE("전체 결제", 25000, 30);
	
	public final String ITEM_NAME;
	public final int CHARGE;
	public final int TERM;
	
	
	
	private CashItem(String ITEM_NAME, int CHARGE, int TERM) {
		this.ITEM_NAME = ITEM_NAME;
		this.CHARGE = CHARGE;
		this.TERM = TERM;
	}
	
	
}
