package com.kh.spring.board.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dto.Board;
import com.kh.spring.board.model.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardRepository boardRepository;

	@Override
	public int insertBoard(Board board) {
		
		int res = boardRepository.insertBoard(board);
		
		return res;
		
		
	}

	
}
