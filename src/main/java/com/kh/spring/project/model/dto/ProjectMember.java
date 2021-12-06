package com.kh.spring.project.model.dto;

import java.sql.Date;

public class ProjectMember {

   private String pmIdx;
   private String projectIdx;
   private String userIdx;     //유저 인덱스
   private String authIdx;     //권한 인덱스
   private String nickname;     //프로젝트에서 개인 닉네임
   private String profileColor; //프로젝트에서 개인 색
   private int isOk;          //프로젝트 초대 수락여부 
   private Date regDate;         //프로젝트 초대일
   private String isLeave;		//프로젝트 탈퇴여부
   
   public String getPmIdx() {
      return pmIdx;
   }
   public void setPmIdx(String pmIdx) {
      this.pmIdx = pmIdx;
   }
   public String getProjectIdx() {
      return projectIdx;
   }
   public void setProjectIdx(String projectIdx) {
      this.projectIdx = projectIdx;
   }
   public String getUserIdx() {
      return userIdx;
   }
   public void setUserIdx(String userIdx) {
      this.userIdx = userIdx;
   }
   public String getAuthIdx() {
      return authIdx;
   }
   public void setAuthIdx(String authIdx) {
      this.authIdx = authIdx;
   }
   public String getNickname() {
      return nickname;
   }
   public void setNickname(String nickname) {
      this.nickname = nickname;
   }
   public String getProfileColor() {
      return profileColor;
   }
   public void setProfileColor(String profileColor) {
      this.profileColor = profileColor;
   }
   public int getIsOk() {
      return isOk;
   }
   public void setIsOk(int isOk) {
      this.isOk = isOk;
   }
   public Date getRegDate() {
      return regDate;
   }
   public void setRegDate(Date regDate) {
      this.regDate = regDate;
   }
   public String getIsLeave() {
      return isLeave;
   }
   public void setIsLeave(String isLeave) {
      this.isLeave = isLeave;
   }
   @Override
   public String toString() {
      return "ProjectMember [pmIdx=" + pmIdx + ", projectIdx=" + projectIdx + ", userIdx=" + userIdx + ", authIdx="
            + authIdx + ", nickname=" + nickname + ", profileColor=" + profileColor + ", isOk=" + isOk
            + ", regDate=" + regDate + ", isLeave=" + isLeave + "]";
   }
   
   
   
   
   
   
   
}