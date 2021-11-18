package com.kh.spring.board.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.spring.board.model.dto.Board;
import com.kh.spring.workspace.model.dto.Workspace;

@Mapper
public interface BoardRepository {

	int insertBoard(Board board) ;

	@Select("select * from workspace where ws_idx = #{wsIdx}")
	Workspace selectWorkSpaceByWsIdx(String wsIdx);

	
	List<Board> selectBoardByWsIdx(String wsIdx);
	
	@Delete("delete from board where bd_idx = #{bdIdx}")
	void deleteBoard(Board board);
	
	@Update("update board set sort=sort-1 where sort > #{sort}")
	void updateBoardSortWhenRemoveBoard(Board board);

	
	@Update("update board set sort=#{changeSort} where bd_idx = #{bdIdx}")
	void updateBoardSort(Map<String, String> map);
	
	@Update("update board set sort=sort+1 where bd_idx in (select bd_idx from board where ws_idx = #{wsIdx}"
			+ " and sort between #{changeSort} and #{sort}-1)")
	void updateBoardSortPlus(Map<String, String> map);
	
	@Update("update board set sort=sort-1 where bd_idx in (select bd_idx from board where ws_idx = #{wsIdx}"
			+ " and sort between #{sort}+1 and #{changeSort})")
	void updateBoardSortMinus(Map<String, String> map);

}
