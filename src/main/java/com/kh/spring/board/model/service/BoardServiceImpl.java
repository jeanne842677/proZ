package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dto.Board;
import com.kh.spring.board.model.repository.BoardRepository;
import com.kh.spring.workspace.model.dto.Workspace;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardRepository boardRepository;

	@Override
	public int insertBoard(Board board) {
		
		int res = boardRepository.insertBoard(board);
		
		return res;
		
		
	}

	@Override
	public Workspace selectWorkSpaceByWsIdx(String wsIdx) {
		
		return boardRepository.selectWorkSpaceByWsIdx(wsIdx);
		
	}

	@Override
	public List<Board> selectBoardByWsIdx(String wsIdx) {
		
		return boardRepository.selectBoardByWsIdx(wsIdx);
	}

	@Override
	public void deleteBoard(Board board) {
		
		boardRepository.deleteBoard(board);
		boardRepository.updateBoardSortWhenRemoveBoard(board);
		
		
	}

	
	@Override
	public void updateSort(Map<String, String> map) {
		
		String bdIdx = map.get("bdIdx");
		String wsIdx =  map.get("wsIdx");
		int changeSort = Integer.parseInt(map.get("changeSort"));
		int sort = Integer.parseInt(map.get("sort"));
		
	
		
		//바뀐 애가 더 작으면?
		if(changeSort < sort) {
			
			boardRepository.updateBoardSortPlus(map);
			
			
		}else if (changeSort > sort) {
			

			boardRepository.updateBoardSortMinus(map);
			
		}
		
		boardRepository.updateBoardSort(map);
		
	}

	
}
