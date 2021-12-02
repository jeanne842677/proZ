package com.kh.spring.board.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.spring.board.model.dto.Board;
import com.kh.spring.board.model.dto.Post;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.common.code.ErrorCode;
import com.kh.spring.common.exception.HandlableException;
import com.kh.spring.common.util.json.JsonMaker;
import com.kh.spring.common.util.map.CamelMap;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.project.model.dto.Project;
import com.kh.spring.project.model.dto.ProjectMember;
import com.kh.spring.project.model.dto.Workspace;
import com.kh.spring.project.model.service.ProjectService;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	@Autowired
	ProjectService projectService;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("{projectIdx}")
	public String board(@PathVariable String projectIdx, Model model,
			@RequestParam String wsIdx) {
		
		if (wsIdx != null) {
			Workspace workspace = boardService.selectWorkSpaceByWsIdx(wsIdx);
			if (workspace == null) {
				ErrorCode error = ErrorCode.REDIRECT_MAIN_PAGE;
				error.setURL("/project/" + projectIdx);
				throw new HandlableException(error);
			}
			model.addAttribute("wsIdx",wsIdx);
			model.addAttribute("workspace", workspace);
		}
		
		List<Board> boardList = boardService.selectBoardByWsIdx(wsIdx);
		List<Post> postList = boardService.selectPostListByWsIdx(wsIdx);
		model.addAttribute("boardList" , boardList);
		model.addAttribute("postList" , postList);
		return "/board/board-list";
	}
	
	// 1. board_Method 
	// 1) board_add
	@PostMapping("change/add-board")
	@ResponseBody
	public String addBoard(@RequestBody Board board) {

		boardService.insertBoard(board);
		return JsonMaker.json(board);
				
	}

	// 2)board_remove
	@PostMapping("change/remove-board")
	@ResponseBody
	public String removeBoard(@RequestBody Board board) {

		boardService.deleteBoard(board);
		
		return "complete";
	}
	
	// 3)board_changeSort 
	@PostMapping("change/sort")
	public String changeSort(@RequestBody Map<String, String> map) {

		boardService.updateSort(map);

		return "complete";
	}
	
	// 4) board_addPost Method 
	@PostMapping("add-post")
	@ResponseBody
	public String addPost(@RequestBody Map<String, String> map,
			@SessionAttribute(required = false , value="authentication") Member member
			,Model model) {
		
		logger.debug(map.get("postColor"));
		logger.debug(map.toString()); 
		// 일단, addPost에 색이 제대로 database에 도달하는지 확인, 도달하지 않음. 수정필요 
		
		// 게시글을 저장 시 trim처리를 통해 저장, 일단 trim처리 완료  
		boardService.insertPost(map , member);
		
		return "complete";
	}

	// 5)boardPost_changeSort 
	@PostMapping("post/change-sort") 
	@ResponseBody
	public String changePostSort(@RequestBody Map<String, String> map) {
		
		boardService.updatePostSort(map);
		return "complete";
		
	}
	
	// 2. Postring_Post 관련 메서드 
	// **Posting 
	// 1) Posting_getMapping 
	@GetMapping("posting/{projectIdx}")
	public String postForm(@RequestParam(required = false) String bdidx ,
			Model model,
			@PathVariable String projectIdx) {
		
		Board board = boardService.selectBoardByBdIdx(bdidx);
		if(board == null) {
			// 존재하지 않거나, 삭제된 보드임 > 알람처리 필요 
			// addFlashAttribute로 처리필요 
		}
		model.addAttribute("wsIdx" , board.getWsIdx());
		return "/board/posting";
	} 
	
	// **Post 
	// 2) Post_getMapping 
	@GetMapping("view/{projectIdx}")
	public String getBoardInfo(@PathVariable String projectIdx,
			@RequestParam(required = false) String postIdx,
			@RequestParam(required = false) String pmIdx,
			Model model) {
		
		// a. 포스트를 받아온다. 
		Post newPost = boardService.selectPostByPostIdx(postIdx);
		
		// 저장시 : 매핑 포기하고 받는다. 
				
		// if(newPost.pmIdx.equals(pmIdx)) {
		//	model.setAttribute("isSamePM", "true")
		// }
		
		model.addAttribute("post",newPost);
		return "board/post"; 
	}
	
	@PostMapping("view/remove-post")
	@ResponseBody
	public String removePost(@RequestBody Board board) {

		boardService.deleteBoard(board);
		// 메인 화면으로 돌려보낸다. 
		return "/board/boardList";
	}

	@PostMapping("post/change-post")
	@ResponseBody
	public String changePost(@RequestBody Board board) {

		boardService.deleteBoard(board);
	
		return "/board/posting";
	}

	
	// <최윤지 코드블록> 
	// -----------------------------------------------------
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
