package com.kh.spring.member.model.dto;


import java.sql.Date;


public class Member {
   
   private String userIdx;
   private String email;
   private String password;
   private String nickname;
   private String git;
   private Date regDate;
   private String profileColor;
   private int isLeave;
   
   
		public String getUserIdx() {
			return userIdx;
		}
		public void setUserIdx(String userIdx) {
			this.userIdx = userIdx;
		}
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
		public Date getRegDate() {
			return regDate;
		}
		public void setRegDate(Date regDate) {
			this.regDate = regDate;
		}
		public String getProfileColor() {
			return profileColor;
		}
		public void setProfileColor(String profileColor) {
			this.profileColor = profileColor;
		}
		public int getIsLeave() {
			return isLeave;
		}
		public void setIsLeave(int isLeave) {
			this.isLeave = isLeave;
		}
		@Override
		public String toString() {
			return "Member [userIdx=" + userIdx + ", email=" + email + ", password=" + password + ", nickname=" + nickname
					+ ", git=" + git + ", regDate=" + regDate + ", profileColor=" + profileColor + ", isLeave=" + isLeave + "]";
		}
		   
		   
		   
  


   
   
   
   
}