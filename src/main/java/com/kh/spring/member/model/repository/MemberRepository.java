package com.kh.spring.member.model.repository;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.validator.JoinForm;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.spring.member.model.dto.Member;


@Mapper
public interface MemberRepository {


	@Select("select * from member where email = #{email}")
	Member selectMemberByEmail(String email);

	@Insert("insert into member(email,password,nickname,git) values(#{email},#{password},#{nickname},#{git})")
	void insertMember(JoinForm form);
	


}
