package com.kh.spring.member.model.service;

import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.repository.MemberRepository;
import com.kh.spring.member.validator.JoinForm;


public interface MemberService {
	

	Member selectMemberByEmail(String email);

	void insertMember(JoinForm form);

	void authenticateByEmail(JoinForm form, String token);
	
	Member selectMemberByEmailAndPassword(Member member);
	




}
