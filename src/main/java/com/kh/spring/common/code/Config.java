package com.kh.spring.common.code;

public enum Config {
	
	
	DOMAIN("http://localhost:9090"),
	COMPANY_EMAIL("lucky007proz@gmail.com"),
	SMTP_AUTHENTICATION_ID("lucky007proz@gmail.com"),
	SMTP_AUTHENTICATION_PASSWORD("!lucky007proZ"),
	UPLOAD_PATH("C:\\CODE\\upload\\");

	
	
	public final String DESC ;
	
	private Config(String DESC) {
		
		this.DESC = DESC;
		
		
	}
	

}
