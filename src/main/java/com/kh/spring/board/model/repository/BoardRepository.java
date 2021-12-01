package com.kh.spring.board.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.kh.spring.board.model.dto.Board;
import com.kh.spring.board.model.dto.Post;
import com.kh.spring.memo.model.dto.Memo;
import com.kh.spring.project.model.dto.Workspace;

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

	void insertPost(Map<String, String> map);

	List<Post> selectPostListByWsIdx(String wsIdx);

	@Select("select * from board where bd_idx = #{bdIdx} ")
	Board selectBoardByBdIdx(String bdIdx);

	@Select("select * from post where post_idx =#{postIdx}")
	Post selectPostByPostIdx(String postIdx);

	void updatePostSortMinus(Map<String, String> map);

	void updatePostSortPlus(Map<String, String> map);
	
	@Update("update post set sort= #{changeSort} , bd_idx=#{bdIdx} where post_idx = #{postIdx} ")
	void updatePostSort(Map<String, String> map);

	@Select("select post_idx , bd_idx , pm_idx , post_title , post_color from(select * from post where bd_idx in (select bd_idx from board where ws_idx in (select ws_idx from workspace where project_idx = #{projectIdx} and ws_type='BO'))order by post_idx desc) where rownum <= 3")
	List<Post> selectBoardByTop(String projectIdx);


}
