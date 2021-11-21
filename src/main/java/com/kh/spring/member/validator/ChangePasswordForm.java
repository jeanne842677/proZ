package com.kh.spring.member.validator;

public class ChangePasswordForm {

	private String password; 
	private String checkedPassword;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCheckedPassword() {
		return checkedPassword;
	}
	public void setCheckedPassword(String checkedPassword) {
		this.checkedPassword = checkedPassword;
	}
	@Override
	public String toString() {
		return "changePasswordForm [password=" + password + ", checkedPassword=" + checkedPassword + "]";
	}
	
	
	
}
