package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring.board.model.dto.Board;
import com.kh.spring.board.model.dto.Post;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.memo.model.dto.Memo;
import com.kh.spring.project.model.dto.Workspace;

public interface BoardService {

	int insertBoard(Board board);

	Workspace selectWorkSpaceByWsIdx(String wsIdx);

	List<Board> selectBoardByWsIdx(String wsIdx);

	void deleteBoard(Board board);

	void updateSort(Map<String, String> map);

	void insertPost(Map<String, String> map , Member member);

	List<Post> selectPostListByWsIdx(String wsIdx);

	Board selectBoardByBdIdx(String bdidx);

	void updatePostSort(Map<String, String> map);

	Post selectPostByPostIdx(String pmIdx);

	List<Post> selectBoardByTop(String projectIdx);

}
