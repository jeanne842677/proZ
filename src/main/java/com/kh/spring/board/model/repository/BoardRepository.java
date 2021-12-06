package com.kh.spring.board.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.kh.spring.board.model.dto.Board;
import com.kh.spring.board.model.dto.Post;
import com.kh.spring.board.model.dto.Reply;
import com.kh.spring.common.util.file.FileDTO;
import com.kh.spring.memo.model.dto.Memo;
import com.kh.spring.project.model.dto.Workspace;

@Mapper
public interface BoardRepository {

	int insertBoard(Board board);

	@Select("select * from workspace where ws_idx = #{wsIdx}")
	Workspace selectWorkSpaceByWsIdx(String wsIdx);

	List<Board> selectBoardByWsIdx(String wsIdx);

	@Delete("delete from board where bd_idx = #{bdIdx}")
	void deleteBoard(Board board);

	@Update("update board set sort=sort-1 where  (select count(sort) from board where ws_idx=#{wsIdx} and sort=#{sort})=0 and ws_idx =#{wsIdx} and sort > #{sort}")
	void updateBoardSortWhenRemoveBoard(Board board);

	void updateBoardSort(@Param("bdIdxList") List<String> bdIdxList, @Param("changeSort") int changeSort);

	@Update("update board set sort=sort+1 where bd_idx in (select bd_idx from board where ws_idx = #{wsIdx}"
			+ " and sort between #{changeSort} and (select sort from board where bd_idx=#{bdIdx} )-1)")
	void updateBoardSortPlus(Map<String, String> map);

	@Update("update board set sort=sort-1 where bd_idx in (select bd_idx from board where ws_idx = #{wsIdx}"
			+ " and sort between (select sort from board where bd_idx=#{bdIdx} )+1 and #{changeSort})")
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

	// 유송 추가

	@Delete("delete from post where post_Idx = #{postIdx}")
	void deletePost(String postIdx);

	@Insert("insert into file_dto(FL_IDX, TYPE_IDX, ORIGIN_FILE_NAME, RENAME_FILE_NAME, SAVE_PATH)"
			+ "values(sc_file_idx.nextval, #{bdIdx}, #{FileDTO.originFileName}, #{FileDTO.renameFileName}, #{FileDTO.savePath})")
	int insertPostFile(@Param("FileDTO") FileDTO fileUploaded, @Param("bdIdx") String bdIdx);

	@Select("select w.ws_idx from post p, board b, workspace w where p.bd_idx = b.bd_idx"
			+ " and b.ws_idx = w.ws_idx and p.post_idx = #{postIdx}")
	String selectWsIdxByPostIdx(String postIdx);

	@Update("update post set post_title=#{postTitle}, post_content=#{postContent}, "
			+ "reg_date=#{regDate}, post_color=#{postColor} where post_idx=#{postIdx}")
	void updatePostByPostIdx(Map<String, String> map);

	// 윤지 추가
	@Update("update reply set reply_content= #{reply.replyContent} where reply_idx=#{reply.replyIdx}")
	void updateReplyByReplyIdx(@Param("reply") Reply reply);

	@Select("select * from (select * from reply where post_idx in (select post_idx from post where bd_idx in (select bd_idx from board where ws_idx in (select ws_idx from workspace where project_idx = #{projectIdx} and ws_type='BO'))) order by reply_idx desc)where rownum <=5")
	List<Reply> selectReplyByTop(String projectIdx);

	@Select("select * from reply join project_member using(pm_idx) where post_idx= #{postIdx} order by reply_idx")
	List<Map<String, Object>> selectReplyByProjectMember(String postIdx);

	int insertReply(Reply reply);

	@Delete("delete from reply where reply_idx = #{replyIdx}")
	void deleteReplyByReplyIdx(String replyIdx);

	// 지영 추가

	void insertLeafBoard(Board board);

	@Select("select * from board where ws_idx = #{wsIdx} and parent is not null")
	List<Board> selectLeafBoardByWsIdx(String wsIdx);

	@Select("select * from post where bd_idx = #{bdIdx} order by sort")
	List<Post> selectPostListByBdIdx(String bdIdx);

	// 12월 5일 지영 추가
	@Select("select * from board where bd_idx = (select nvl(parent , bd_idx) from board where (parent is null and bd_Idx= #{bdIdx}) or (bd_idx=#{bdIdx}))")
	Board selectParentBoardByBdIdx(String bdIdx);

	@Select("select * from board where sort = (select sort from board where bd_idx = #{bdIdx})")
	List<Board> selectBoardAndBoardLeafList(String bdIdx);

}
