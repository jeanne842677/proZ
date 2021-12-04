package com.kh.spring.board.model.dto;

import java.sql.Date;

public class Reply {

   private String replyIdx;
   private String pmIdx;
   private String replyContent;
   private Date regDate;
   private String postIdx;
   private String parentReplyIdx;
   public String getReplyIdx() {
      return replyIdx;
   }
   public void setReplyIdx(String replyIdx) {
      this.replyIdx = replyIdx;
   }
   public String getPmIdx() {
      return pmIdx;
   }
   public void setPmIdx(String pmIdx) {
      this.pmIdx = pmIdx;
   }
   public String getReplyContent() {
      return replyContent;
   }
   public void setReplyContent(String replyContent) {
      this.replyContent = replyContent;
   }
   public Date getRegDate() {
      return regDate;
   }
   public void setRegDate(Date regDate) {
      this.regDate = regDate;
   }
   public String getPostIdx() {
      return postIdx;
   }
   public void setPostIdx(String postIdx) {
      this.postIdx = postIdx;
   }
   public String getParentReplyIdx() {
      return parentReplyIdx;
   }
   public void setParentReplyIdx(String parentReplyIdx) {
      this.parentReplyIdx = parentReplyIdx;
   }
   @Override
   public String toString() {
      return "Reply [replyIdx=" + replyIdx + ", pmIdx=" + pmIdx + ", replyContent=" + replyContent + ", regDate="
            + regDate + ", postIdx=" + postIdx + ", parentReplyIdx=" + parentReplyIdx + "]";
   }
   
   
}