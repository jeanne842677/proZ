package com.kh.spring.myPage.model.service;

import com.kh.spring.common.util.file.FileDTO;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.validator.MypageForm;

public interface MypageService {

	int updateMypageMemberByProfileColor(Member member);

	int insertMemberProfileImg(FileDTO fileUploaded, String userIdx);

	int updateMypageMemberByNickname(Member member);

	int updateMypageMemberByGit(Member member);

	int memberIsleave(Member member);
	
	Member updateMypageMemberByPassword(Member member);

	FileDTO selectProfileImgFilebyMemberIdx(Member dummyMember); 
}
