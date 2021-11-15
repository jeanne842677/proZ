package com.kh.spring.member.model.repository;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.validator.JoinForm;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.spring.member.model.dto.Member;


@Mapper
public interface MemberRepository {


   @Select("select * from proz_user where email = #{email}")
   Member selectMemberByEmail(String email);

   //insert into proz_user(user_Idx,email,password,nickname,git) values(sc_proz_Idx.nextval,'cxyxj@naver.com','dbswl1219!','윤지','www.naver.com');
   @Insert("insert into proz_user(user_Idx,email,password,nickname,git) values(sc_proz_Idx.nextval,#{email},#{password},#{nickname},#{git})")
   void insertMember(JoinForm form);
   
   @Select("select * from proz_user where email = #{email} and password = #{password}")
      Member selectMemberByEmailAndPassword(Member member);
   
   @Insert("insert into proz_user(user_Idx,email,password,nickname,git,social_Id) values(sc_proz_Idx.nextval,#{email},#{password},#{nickname},#{git},#{socialId})")
   void insertSocialMember(JoinForm form);

   @Select("select * from proz_user where social_Id = #{socialId}")
   Member selectGoogleId(String googleId);   
   




}