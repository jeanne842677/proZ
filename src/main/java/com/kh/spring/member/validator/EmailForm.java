package com.kh.spring.member.validator;

public class EmailForm {

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "EmailForm [email=" + email + "]";
	} 
	
	
	
}
