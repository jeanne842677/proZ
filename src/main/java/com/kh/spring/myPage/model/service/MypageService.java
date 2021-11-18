package com.kh.spring.myPage.model.service;

import com.kh.spring.common.util.file.FileDTO;
import com.kh.spring.member.model.dto.Member;

public interface MypageService {

	int updateMypageMemberByProfileColor(Member member);

	int insertMemberProfileImg(FileDTO fileUploaded);

	int updateMypageMemberByNickname(Member member);

	int updateMypageMemberByGit(Member member);

	int memberIsleave(Member member); 
}
