package com.kh.spring.common.code;

public enum ErrorCode {
	
	
	DATABASE_ACCESS_ERROR("데이터베이스와 통신 중 에러가 발생하였습니다." ),
	VALIDATOR_FAIL_ERROR("부적절한 양식의 데이터입니다."),
	MAIL_SEND_FAIL_ERROR("이메일 발송 중 에러가 발생했습니다."),
	HTTP_CONNECT_ERROR("HTTP 통신 중 에러가 발생하였습니다."),
	AUTHENTICATION_FAILED_ERROR("유효하지 않은 인증입니다."),
	UNAUTHORIZE_PAGE("접근 권한이 없는 페이지입니다."),
	REDIRECT_LOGIN_PAGE("","/member/login-form"), //알림창 없애고 싶으면 ""로 입력
	FAILED_FILE_UPLOAD_ERROR("파일 업로드 에러가 발생했습니다.."),
	PROJECT_URL_ERROR("존재하지 않는 프로젝트입니다."),
	PROJECT_INVITATION_REJECTED("초대가 거부되었습니다."),
	NEED_LOGIN("로그인을 먼저 해주세요."),
	REDIRECT_MAIN_PAGE("","/");
	
	public final String MESSAGE;
	public String url;
	
	
	
	private ErrorCode(String msg) {
		
		this.MESSAGE = msg;
		this.url = "/";
		
	}
	
	
	
	private ErrorCode(String msg , String url) {
		this.MESSAGE = msg;
		this.url = url;
		
	}



	public String getURL() {
		return url;
	}



	public ErrorCode setURL(String url) {
		this.url = url;
		return this;
	}
	
	
	
	
	
	
}
