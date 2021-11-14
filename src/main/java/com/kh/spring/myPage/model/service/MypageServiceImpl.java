package com.kh.spring.myPage.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.myPage.model.repository.MypageRepository;

@Service
public class MypageServiceImpl implements MypageService{

	@Autowired
	MypageRepository mypageRepository; 
	
	@Override
	public int updateMypageMemberByProfileColor(Member member) {
		return mypageRepository.updateMypageMemberByProfileColor(member);
	}
	

}
