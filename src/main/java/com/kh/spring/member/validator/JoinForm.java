package com.kh.spring.member.validator;

public class JoinForm {
	
	private String email;
	private String password;
	private String nickname;
	private String git;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGit() {
		return git;
	}
	public void setGit(String git) {
		this.git = git;
	}
	@Override
	public String toString() {
		return "JoinForm [email=" + email + ", password=" + password + ", nickname=" + nickname + ", git=" + git + "]";
	}
	
	
	
	
	
}
