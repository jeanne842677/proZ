package com.kh.spring.board.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.dto.Board;
import com.kh.spring.board.model.dto.Post;
import com.kh.spring.board.model.dto.Reply;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.common.code.ErrorCode;
import com.kh.spring.common.exception.HandlableException;
import com.kh.spring.common.util.file.FileDTO;
import com.kh.spring.common.util.file.FileUtil;
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

	// 여기 지영이 수정
	@GetMapping("{projectIdx}")
	public String board(@PathVariable String projectIdx, Model model, @RequestParam String wsIdx) {

		List<Board> boardList = boardService.selectBoardByWsIdx(wsIdx);
		List<Map<String, Object>> postList = boardService.selectPostListByWsIdx(wsIdx);

		List<Board> parentBoard = new ArrayList<>();
		List<Board> leafBoardList = new ArrayList<>();

		for (int i = 0; i < boardList.size(); i++) {

			if (i != 0 && boardList.get(i - 1).getSort() == boardList.get(i).getSort()) {
				leafBoardList.add(boardList.get(i));
			} else {
				parentBoard.add(boardList.get(i));

			}

		}

		model.addAttribute("boardList", parentBoard);
		model.addAttribute("postList", postList);
		model.addAttribute("leafBoardList", leafBoardList);

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

	// 3)board_changeSort //지영 수정
	@PostMapping("change/sort")
	public String changeSort(@RequestBody Map<String, String> map) {

		System.out.println(map);
		boardService.updateSort(map);

		return "complete";
	}

	// 4) board_addPost Method
	@PostMapping("add-post")
	@ResponseBody
	public String addPost(@RequestBody Map<String, String> map,
			@SessionAttribute(required = false, value = "authentication") Member member, Model model) {

		logger.debug(map.get("postColor"));
		logger.debug(map.toString());
		// 일단, addPost에 색이 제대로 database에 도달하는지 확인, 도달하지 않음. 수정필요

		// 게시글을 저장 시 trim처리를 통해 저장, 일단 trim처리 완료
		boardService.insertPost(map, member);

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
	public String postForm(@RequestParam(required = false) String bdidx, Model model, @PathVariable String projectIdx) {

		Board board = boardService.selectBoardByBdIdx(bdidx);
		if (board == null) {
			// 존재하지 않거나, 삭제된 보드임 > 알람처리 필요
			// addFlashAttribute로 처리필요
		}
		// ++++ 수정 부분, board로 변경
		model.addAttribute("board", board);
		return "/board/posting";
	}

	// 2) Posting file 비동기전송 Code
	@PostMapping(value = "posting/fileIo", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String insertFileAsync(HttpSession session, @RequestParam(required = false) String bdIdx,
			@RequestParam(required = false) List<MultipartFile> files) {

		// 1. 파일 추출 및 DB저장
		FileUtil fileUtil = new FileUtil();
		FileDTO fileUploaded = fileUtil.fileUpload(files.get(0));
		boardService.insertPostFile(fileUploaded, bdIdx);

		// 2. DTO를 JSON형식으로 전달
		return JsonMaker.json(fileUploaded);
	}

	// **Post 관련 메서드
	// 1) Post_getMapping
	@GetMapping("view/{projectIdx}")
	public String getBoardInfo(@PathVariable String projectIdx, @RequestParam(required = false) String postIdx,
			@RequestParam(required = false) String wsIdx, @RequestParam(required = false) String bdIdx, Model model) {

		// 1. postIdx로 공개할 post를 받아 model에 넣는다.
		Post newPost = boardService.selectPostByPostIdx(postIdx);

		// ++++ 수정, 모델에 boardName 정보를 넣어야 한다.
		model.addAttribute("post", newPost); 
		model.addAttribute("isSamePM", newPost.getBdIdx());

		Board board = boardService.selectBoardByBdIdx(newPost.getBdIdx());
		model.addAttribute("board", board);
		// 2. 댓글 기능을 위해 댓글 목록을 불러온다.
		List<Map<String, Object>> replyMember = CamelMap
				.changeListMap(boardService.selectReplyByProjectMember(postIdx));
		System.out.println("replyMember :" + replyMember);
		model.addAttribute("replyMember", replyMember);

		// 3. post로 return한다.
		return "board/post";
	}

	// 2) remove_Post
	@PostMapping("view/remove-post")
	@ResponseBody
	public String removePost(@RequestParam String postIdx) {
		// ++++ 수정부분
		// 1. postIdx > boardIdx > wsIdx
		String wsIdx = boardService.selectWsIdxByPostIdx(postIdx);

		// delete_repository를 작동시킨다.
		boardService.deletePost(postIdx);

		return wsIdx;
	}

	// ++++ 수정
	// 3) change_Post
	@PostMapping("view/change-post/{projectIdx}")
	public String changePost(Model model, @PathVariable String projectIdx, @RequestParam String content,
			@RequestParam String bdidx, @RequestParam String postIdx) {

		// 1. posting 접근 시 필요한 wsIdx를 조회해서 불러온다.
		Board board = boardService.selectBoardByBdIdx(bdidx);
		if (board == null) {
			// *addFlashAttribute 필요 :
			logger.debug("2. 만일 null이라면, return처리");
			return "redirect:/board/" + projectIdx + "?wsIdx=" + board.getWsIdx();
		}

		Post post = boardService.selectPostByPostIdx(postIdx);
		// 2. posting 수정 시 필요한 data를 전달
		model.addAttribute("board", board);
		model.addAttribute("content", content);
		model.addAttribute("post", post);
		logger.debug("2. 정상 작동, model객체에 addAttribute");
		// selection

		// 이후 모델 객체를 점검, content 여부에 따라 InnerHTML 추가
		logger.debug("editing 출발");
		return "board/editing";
	}

	// ++++ 수정
	// 4) editPost
	@PostMapping("view/edit-post")
	@ResponseBody
	public String editPost(@RequestBody Map<String, String> map,
			@SessionAttribute(required = false, value = "authentication") Member member, Model model) {

		// 1. 현제 Date를 map에 추가
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		map.put("regDate", date.toString());

		// 2. boardService를 통해 update
		boardService.updatePostByPostIdx(map);

		return "complete";
	}

	// <최윤지 코드블록>
	// -----------------------------------------------------

	// 리플 추가
	@PostMapping("post/add/reply")
	@ResponseBody
	public Reply addReply(@RequestBody Reply reply, @SessionAttribute("authentication") Member member, Model model) {

		boardService.insertReply(reply);

		System.out.println("인서트 리플::::::::" + reply);

		return reply;
	}

	@PostMapping("post/delete/reply")
	public String deleteReply(@SessionAttribute("authentication") Member member, @RequestBody Reply reply) {

		String replyIdx = reply.getReplyIdx();
		System.out.println("replyIdx" + replyIdx);
		boardService.deleteReplyByReplyIdx(replyIdx);
		return "board/post";
	}

	@PostMapping("post/update/reply")
	public String updateReply(@SessionAttribute("authentication") Member member, @RequestBody Reply reply) {

		String replyIdx = reply.getReplyIdx();

		System.out.println("replyIdx" + replyIdx);
		System.out.println("수정할 reply" + reply);
		boardService.updateReplyByReplyIdx(reply);

		return "board/post";
	}

	// <지영 코드블록>

	// -----------------------------------------------------

	@PostMapping("add/leaf-board")
	@ResponseBody
	public String addLeafBoard(@RequestBody Board board) {

		System.out.println(board);

		boardService.insertLeafBoard(board);

		return board.getBdIdx();
	}

	@PostMapping("select/leaf-board-post")
	@ResponseBody
	public List<Post> selectLeafBoardPost(@RequestBody Board board) {

		System.out.println("보드이름: " + board.getBdName());
		List<Post> postList = boardService.selectPostListByBdIdx(board.getBdIdx());
		System.out.println(postList);

		return postList;
	}

}
