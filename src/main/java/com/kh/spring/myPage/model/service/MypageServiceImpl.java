package com.kh.spring.myPage.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.common.util.file.FileDTO;
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

	@Override
	public int insertMemberProfileImg(FileDTO fileUploaded) {
		return mypageRepository.insertMemberProfileImg(fileUploaded);
	}

	
	@Override
	public int updateMypageMemberByNickname(Member member) {
		return mypageRepository.updateMypageMemberByNickname(member);
	}

	@Override
	public int updateMypageMemberByGit(Member member) {
		return mypageRepository.updateMypageMemberByGit(member);
	}

	@Override
	public int memberIsleave(Member member) {
		return mypageRepository.memberIsleave(member);
	}


	

}
