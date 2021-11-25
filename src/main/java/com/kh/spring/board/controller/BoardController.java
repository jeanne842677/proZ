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
		
		Project project = projectService.selectProjectByIdx(projectIdx);
		Workspace workspace = boardService.selectWorkSpaceByWsIdx(wsIdx);
		
		
		if(workspace==null) {
			return "redirect:/project/" + projectIdx;
		}else if(project == null)
			throw new HandlableException(ErrorCode.PROJECT_URL_ERROR);
		else if(!workspace.getProjectIdx().equals(projectIdx)) {
			return "/project/" + projectIdx;
		}
		
		
		List<Map<String, Object>> projectMember = 
				CamelMap.changeListMap(projectService.selectProjectMemberByProjectIdx(projectIdx));

		
		List<Board> boardList = boardService.selectBoardByWsIdx(wsIdx);
		List<Post> postList = boardService.selectPostListByWsIdx(wsIdx);
		System.out.println("프로젝트 멤버: "  + projectMember);
		
		model.addAttribute("projectMember" , projectMember);
		model.addAttribute(workspace);
		model.addAttribute("boardList" , boardList);
		model.addAttribute("postList" , postList);
		model.addAttribute(project);
		

		
		System.out.println(boardList);
		System.out.println("포스트 리스트: " + postList);
		return "/board/board-list";
		
	}
	
	
	@PostMapping("change/add-board")
	@ResponseBody
	public String addBoard(@RequestBody Board board) {
		
		
		boardService.insertBoard(board);
		
		return JsonMaker.json(board);
				
		
	}
	
	@PostMapping("change/remove-board")
	@ResponseBody
	public String removeBoard(@RequestBody Board board) {
		
		
		System.out.println(board);
		boardService.deleteBoard(board);
		
		return "complete";
	}
	
	
	@PostMapping("change/sort")
	public String changeSort(@RequestBody Map<String, String> map) {
		
		System.out.println(map);
		boardService.updateSort(map);
		
		return "complete";
	}
	
	
	@GetMapping("posting/{projectIdx}")
	public String postForm(@RequestParam(required = false) String bdidx ,
			Model model,
			@PathVariable String projectIdx) {
		
		// 임시 bdidx, 100437
		Board board = boardService.selectBoardByBdIdx("100437");
		model.addAttribute("wsIdx" , board.getWsIdx())
		.addAttribute(projectIdx); 
		 
		return "/board/posting";
	} 
	
	
	@PostMapping("add-post")
	@ResponseBody
	public String addPost(@RequestBody Map<String, String> map,
			@SessionAttribute(required = false , value="authentication") Member member
			,Model model) {
		
		System.out.println(member);
		System.out.println(map);
		
		boardService.insertPost(map , member);
		
		
		
		return "complete";
		
	}
	
	@PostMapping("post/change-sort") 
	@ResponseBody
	public String changePostSort(@RequestBody Map<String, String> map) {
		
		
		System.out.println(map);
		boardService.updatePostSort(map);
		
		
		
		return "complete";
		
	}
	
	

}
