package com.kh.spring.myPage.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.spring.common.util.file.FileDTO;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.validator.JoinForm;
import com.kh.spring.myPage.model.repository.MypageRepository;
import com.kh.spring.myPage.validator.MypageForm;

@Service
public class MypageServiceImpl implements MypageService{

	@Autowired
	MypageRepository mypageRepository; 
	@Autowired
	PasswordEncoder passwordEncoder; 
	
	public Member convertPassword(Member member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		System.out.println("convert 된 Password :  " + member.getPassword());
		return member;
	}
	
	@Override
	public int updateMypageMemberByProfileColor(Member member) {
		return mypageRepository.updateMypageMemberByProfileColor(member);
	}

	@Override
	public int insertMemberProfileImg(FileDTO fileUploaded, String userIdx) {
		return mypageRepository.insertMemberProfileImg(fileUploaded, userIdx);
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

	@Override
	public Member updateMypageMemberByPassword(Member member) {
		member = convertPassword(member);
		mypageRepository.updatePassword(member);
		return	member = mypageRepository.selectMemeberPassword(member); 
	}

	@Override
	public FileDTO selectProfileImgFilebyMemberIdx(Member dummyMember) {
		return mypageRepository.selectProfileImgFilebyMemberIdx(dummyMember);
	}

	


	

}
