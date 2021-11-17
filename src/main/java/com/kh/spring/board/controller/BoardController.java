package com.kh.spring.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.spring.board.model.dto.Board;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.common.util.json.JsonMaker;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	

	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@GetMapping("{wsIdx}")
	public String board(@PathVariable String wsIdx, Model model) {
		
		
		//boardService.selectWorkSpaceByWsIdx(wsIdx);
		
		model.addAttribute(wsIdx);
		
		return "/board/board-list";
	}
	
	
	@PostMapping("add-board")
	@ResponseBody
	public String addBoard(@RequestBody Board board) {
		
		System.out.println("인서트 전 board: "+board);
		
		boardService.insertBoard(board);
		
		System.out.println("인서트 이후 board: " +board);
		
		
		
		return JsonMaker.json(board);
				
		
	}
	

}
