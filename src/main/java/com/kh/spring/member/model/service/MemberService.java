package com.kh.spring.member.model.service;

import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.repository.MemberRepository;


public interface MemberService {
	
	Member selectMemberByEmailAndPassword(Member member);
	

}
