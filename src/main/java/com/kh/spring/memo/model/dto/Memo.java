package com.kh.spring.memo.model.dto;

import java.sql.Date;

public class Memo {
   private String memoIdx;
   private String wsIdx;
   private String pmIdx;
   private String content;
   private Date regDate;
   private String bgColor;
   public String getMemoIdx() {
      return memoIdx;
   }
   public void setMemoIdx(String memoIdx) {
      this.memoIdx = memoIdx;
   }
   public String getWsIdx() {
      return wsIdx;
   }
   public void setWsIdx(String wsIdx) {
      this.wsIdx = wsIdx;
   }
   public String getPmIdx() {
      return pmIdx;
   }
   public void setPmIdx(String pmIdx) {
      this.pmIdx = pmIdx;
   }
   public String getContent() {
      return content;
   }
   public void setContent(String content) {
      this.content = content;
   }
   public Date getRegDate() {
      return regDate;
   }
   public void setRegDate(Date regDate) {
      this.regDate = regDate;
   }
   public String getBgColor() {
      return bgColor;
   }
   public void setBgColor(String bgColor) {
      this.bgColor = bgColor;
   }
   @Override
   public String toString() {
      return "Memo [memoIdx=" + memoIdx + ", wsIdx=" + wsIdx + ", pmIdx=" + pmIdx + ", content=" + content
            + ", regDate=" + regDate + ", bgColor=" + bgColor + "]";
   }
   
   
}