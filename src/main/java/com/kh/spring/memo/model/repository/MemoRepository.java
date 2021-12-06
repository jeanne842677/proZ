package com.kh.spring.memo.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.memo.model.dto.Memo;
import com.kh.spring.project.model.dto.ProjectMember;

@Mapper
public interface MemoRepository {
	
	
	@Select("select * from project_member where user_idx=#{userIdx} "
			+ "and project_idx = (select project_idx from workspace where ws_idx = #{wsIdx})")
	ProjectMember selectProjectMember(@Param("userIdx") String userIdx,@Param("wsIdx") String wsIdx);
	
	int insertMemo(Memo memo);
	
	@Select("select * from memo where ws_idx = #{wsIdx} order by memo_idx desc") //내림차순
	List<Memo> selectMemoByWsIdx(String wsIdx);
	
	@Select("select * from memo where ws_idx = #{wsIdx} order by memo_idx asc") //오름차순
	List<Memo> selectMemoByWsIdxAsc(String wsIdx);

	@Delete("delete from memo where memo_idx = #{memoIdx}")
	void deleteMemoByMemoIdx(String memoIdx);
	
	@Update("update memo set CONTENT = #{content}, BG_COLOR = #{bgColor}, REG_DATE = current_date where MEMO_IDX = #{memoIdx}")
	void updateMemoByMemoIdx(Memo memo);

	@Select("	  	select MEMO_IDX,  WS_IDX,  PM_IDX, BG_COLOR ,memo.reg_date,content,member.NICKNAME"
			+ "		from memo memo"
			+ "		inner join project_member member USING (PM_IDX)"
			+ "		where ws_idx = #{wsIdx} and (content like '%'||#{keyword}||'%' or member.NICKNAME like '%'||#{keyword}||'%')"
			+ "     order by reg_date desc, memo_idx desc")
	List<Map<String, Object>> selectMemoBySearch(@Param("wsIdx") String wsIdx, @Param("keyword") String keyword);

	
	List<Map<String, Object>> selectMemoAndWriterByWsIdxAsc(String wsIdx);

	List<Map<String, Object>> selectMemoAndWriterByWsIdxDesc(String wsIdx);

	@Select("select * from"
			+ " (select * from memo "
			+ " join project_member pm using(pm_idx)"
			+ " left join file_dto f on (user_idx = f.type_idx)"
			+ " where ws_idx in (select ws_idx from workspace where ws_type='ME' and project_idx=#{projectIdx})"
			+ " order by memo_idx desc)"
			+ " where rownum <=6")
	List<Map<String, Object>> selectMemoByTop(String projectIdx);
	
	

	/* List<Map<String, String>> selectMemoAndWriterByWsIdx(String wsIdx); */

	
}
