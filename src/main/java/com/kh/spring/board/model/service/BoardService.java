package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring.board.model.dto.Board;
import com.kh.spring.workspace.model.dto.Workspace;

public interface BoardService {

	int insertBoard(Board board);

	Workspace selectWorkSpaceByWsIdx(String wsIdx);

	List<Board> selectBoardByWsIdx(String wsIdx);

	void deleteBoard(Board board);

	void updateSort(Map<String, String> map);

}
