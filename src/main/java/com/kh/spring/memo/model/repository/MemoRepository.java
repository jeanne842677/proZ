package com.kh.spring.memo.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.memo.model.dto.Memo;
import com.kh.spring.project.model.dto.ProjectMember;

@Mapper
public interface MemoRepository {
	
	
	@Select("select * from project_member where user_idx=#{userIdx} "
			+ "and project_idx = (select project_idx from workspace where ws_idx = #{wsIdx})")
	ProjectMember selectProjectMember(@Param("userIdx") String userIdx,@Param("wsIdx") String wsIdx);
	
	@Insert("insert into memo(memo_idx , ws_idx , pm_idx , content , bg_color) "
			+ "values(sc_proz_idx.nextval , #{wsIdx} , #{pmIdx} , #{content} , #{bgColor})")
	void insertMemo(Memo memo);
	
	@Select("select * from memo where ws_idx = #{wsIdx} order by memo_idx desc")
	List<Memo> selectMemoByWsIdx(String wsIdx);

	
}
