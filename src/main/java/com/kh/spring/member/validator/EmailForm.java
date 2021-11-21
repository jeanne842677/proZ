package com.kh.spring.member.validator;


public class EmailForm {

	private String email;
	private String prozSendDate;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProzSendDate() {
		return prozSendDate;
	}
	public void setProzSendDate(String prozSendDate) {
		this.prozSendDate = prozSendDate;
	}
	@Override
	public String toString() {
		return "EmailForm [email=" + email + ", prozSendDate=" + prozSendDate + "]";
	}
}
