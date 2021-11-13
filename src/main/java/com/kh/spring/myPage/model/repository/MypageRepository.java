package com.kh.spring.myPage.model.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.kh.spring.member.model.dto.Member;

@Mapper
public interface MypageRepository {

	@Update("update proz_user set profile_color = #{profileColor} where user_idx = #{userIdx}")
	int updateMypageMemberByProfileColor(Member member);
	
	@Update("update project set invite_code = #{newUuid} where project_idx = #{projectIdx}")
	void updateProjectInviteCode(@Param("newUuid") String newUuid, @Param("projectIdx") String projectIdx);

}
