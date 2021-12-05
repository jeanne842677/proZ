package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring.board.model.dto.Board;
import com.kh.spring.board.model.dto.Post;
import com.kh.spring.board.model.dto.Reply;
import com.kh.spring.common.util.file.FileDTO;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.memo.model.dto.Memo;
import com.kh.spring.project.model.dto.Workspace;

public interface BoardService {

	int insertBoard(Board board);

	Workspace selectWorkSpaceByWsIdx(String wsIdx);

	List<Board> selectBoardByWsIdx(String wsIdx);

	void deleteBoard(Board board);

	void updateSort(Map<String, String> map);

	void insertPost(Map<String, String> map, Member member);

	List<Post> selectPostListByWsIdx(String wsIdx);

	Board selectBoardByBdIdx(String bdidx);

	void updatePostSort(Map<String, String> map);

	Post selectPostByPostIdx(String pmIdx);

	List<Post> selectBoardByTop(String projectIdx);

	// 유송추가
	void deletePost(String postIdx);

	int insertPostFile(FileDTO fileUploaded, String bdIdx);

	String selectWsIdxByPostIdx(String postIdx);

	void updatePostByPostIdx(Map<String, String> map);

	// 윤지 추가
	Reply insertReply(Reply reply);

	void deleteReplyByReplyIdx(String replyIdx);

	List<Reply> selectReplyByTop(String projectIdx);

	void updateReplyByReplyIdx(Reply reply);

	List<Map<String, Object>> selectReplyByProjectMember(String postIdx);

	// 지영 추가
	void insertLeafBoard(Board board);

	List<Board> selectLeafBoardByWsIdx(String wsIdx);

	List<Post> selectPostListByBdIdx(String bdIdx);

}
