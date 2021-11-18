package com.kh.spring.myPage.model.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.kh.spring.common.util.file.FileDTO;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.validator.JoinForm;

@Mapper
public interface MypageRepository {

	@Update("update proz_user set profile_color = #{profileColor} where user_idx = #{userIdx}")
	int updateMypageMemberByProfileColor(Member member);
	
	@Insert("insert into file_dto(FL_IDX, TYPE_IDX, ORIGIN_FILE_NAME, RENAME_FILE_NAME, SAVE_PATH)"
			+ "values(sc_file_idx.nextval, 11111, #{originFileName}, #{renameFileName}, #{savePath})")
	int insertMemberProfileImg(FileDTO fileUploaded);

	@Insert("insert into proz_user(user_Idx,email,password,nickname,git,social_Id) values(sc_proz_Idx.nextval,#{email},#{password},#{nickname},#{git},#{socialId})")
	void insertSocialMember(JoinForm form);

	@Update("update proz_user set nickname = #{nickname} where user_idx = #{userIdx}")
	int updateMypageMemberByNickname(Member member);

	
	@Update("update proz_user set git = #{git} where user_idx = #{userIdx}")
	int updateMypageMemberByGit(Member member);

	@Update("update proz_user set is_leave = 1 where user_idx = #{userIdx}")
	int memberIsleave(Member member);
}
