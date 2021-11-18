package com.kh.spring.myPage.validator;

public class MypageForm {
	
	private String nickname; 
	private String password; 
	private String checkedPassword; 
	private String git;
	private String profileColor;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
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
	public String getGit() {
		return git;
	}
	public void setGit(String git) {
		this.git = git;
	}
	public String getProfileColor() {
		return profileColor;
	}
	public void setProfileColor(String profileColor) {
		this.profileColor = profileColor;
	}
	@Override
	public String toString() {
		return "MypageForm [nickname=" + nickname + ", password=" + password + ", checkedPassword=" + checkedPassword
				+ ", git=" + git + ", profileColor=" + profileColor + "]";
	}
	
}
