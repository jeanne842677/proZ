package com.kh.spring.member.model.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.spring.member.model.dto.Member;

@Mapper
public interface MemberRepository {

	@Select("select * from member where email =#{email} and password = #{password}")
	Member selectMemberByEmailAndPassword(Member member);

}
