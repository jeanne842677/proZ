package com.kh.spring.board.model.repository;

import org.apache.ibatis.annotations.Mapper;

import com.kh.spring.board.model.dto.Board;

@Mapper
public interface BoardRepository {

	public int insertBoard(Board board) ;

}
