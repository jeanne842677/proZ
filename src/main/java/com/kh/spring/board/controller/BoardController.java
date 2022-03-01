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
public class BoardController  {

	@Autowired
	BoardService boardService;
	@Autowired
	ProjectService projectService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	// �뿬湲� 吏��쁺�씠 �닔�젙
	@GetMapping("{projectIdx}")
	public String board(@PathVariable String projectIdx, Model model, @RequestParam String wsIdx) {
		
		
		Map<String , Object> modelInfo = boardService.selectBoardAndPost(wsIdx);
		model.addAllAttributes(modelInfo);
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

	// 3)board_changeSort //吏��쁺 �닔�젙
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

		//logger.debug(map.get("postColor"));
		//logger.debug(map.toString());
		// �씪�떒, addPost�뿉 �깋�씠 �젣��濡� database�뿉 �룄�떖�븯�뒗吏� �솗�씤, �룄�떖�븯吏� �븡�쓬. �닔�젙�븘�슂

		// 寃뚯떆湲��쓣 ���옣 �떆 trim泥섎━瑜� �넻�빐 ���옣, �씪�떒 trim泥섎━ �셿猷�
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

	// 2. Postring_Post 愿��젴 硫붿꽌�뱶
	// **Posting
	// 1) Posting_getMapping
	@GetMapping("posting/{projectIdx}")
	public String postForm(@RequestParam(required = false) String bdidx, Model model, @PathVariable String projectIdx) {

		Board board = boardService.selectBoardByBdIdx(bdidx);
		if (board == null) {
			// 議댁옱�븯吏� �븡嫄곕굹, �궘�젣�맂 蹂대뱶�엫 > �븣�엺泥섎━ �븘�슂
			// addFlashAttribute濡� 泥섎━�븘�슂
		}
		// ++++ �닔�젙 遺�遺�, board濡� 蹂�寃�
		model.addAttribute("board", board);
		return "/board/posting";
	}

	// 2) Posting file 鍮꾨룞湲곗쟾�넚 Code
	@PostMapping(value = "posting/fileIo", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String insertFileAsync(HttpSession session, @RequestParam(required = false) String bdIdx,
			@RequestParam(required = false) List<MultipartFile> files) {

		// 1. �뙆�씪 異붿텧 諛� DB���옣
		FileUtil fileUtil = new FileUtil();
		FileDTO fileUploaded = fileUtil.fileUpload(files.get(0));
		boardService.insertPostFile(fileUploaded, bdIdx);

		// 2. DTO瑜� JSON�삎�떇�쑝濡� �쟾�떖
		return JsonMaker.json(fileUploaded);
	}

	// **Post 愿��젴 硫붿꽌�뱶
	// 1) Post_getMapping
	@GetMapping("view/{projectIdx}")
	public String getBoardInfo(@PathVariable String projectIdx, @RequestParam(required = false) String postIdx,
			@RequestParam(required = false) String wsIdx, @RequestParam(required = false) String bdIdx, Model model) {
		
		Map<String, Object> mapInfo = boardService.selectPostInfo(postIdx);
		
		model.addAllAttributes(mapInfo);
		
		
		// 3. post濡� return�븳�떎.
		return "board/post";
	}

	// 2) remove_Post
	@PostMapping("view/remove-post")
	@ResponseBody
	public String removePost(@RequestParam String postIdx) {
		// ++++ �닔�젙遺�遺�
		// 1. postIdx > boardIdx > wsIdx
		String wsIdx = boardService.selectWsIdxByPostIdx(postIdx);

		// delete_repository瑜� �옉�룞�떆�궓�떎.
		boardService.deletePost(postIdx);

		return wsIdx;
	}

	// ++++ �닔�젙
	// 3) change_Post
	@PostMapping("view/change-post/{projectIdx}")
	public String changePost(Model model, @PathVariable String projectIdx, @RequestParam String content,
			@RequestParam String bdidx, @RequestParam String postIdx) {

		// 1. posting �젒洹� �떆 �븘�슂�븳 wsIdx瑜� 議고쉶�빐�꽌 遺덈윭�삩�떎.
		Board board = boardService.selectBoardByBdIdx(bdidx);
		if (board == null) {
			// *addFlashAttribute �븘�슂 :
			logger.debug("2. 留뚯씪 null�씠�씪硫�, return泥섎━");
			return "redirect:/board/" + projectIdx + "?wsIdx=" + board.getWsIdx();
		}

		Post post = boardService.selectPostByPostIdx(postIdx);
		// 2. posting �닔�젙 �떆 �븘�슂�븳 data瑜� �쟾�떖
		model.addAttribute("board", board);
		model.addAttribute("content", content);
		model.addAttribute("post", post);
		logger.debug("2. �젙�긽 �옉�룞, model媛앹껜�뿉 addAttribute");
		// selection

		// �씠�썑 紐⑤뜽 媛앹껜瑜� �젏寃�, content �뿬遺��뿉 �뵲�씪 InnerHTML 異붽�
		logger.debug("editing 異쒕컻");
		return "board/editing";
	}

	// ++++ �닔�젙
	// 4) editPost
	@PostMapping("view/edit-post")
	@ResponseBody
	public String editPost(@RequestBody Map<String, String> map,
			@SessionAttribute(required = false, value = "authentication") Member member, Model model) {

		// 1. �쁽�젣 Date瑜� map�뿉 異붽�
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		map.put("regDate", date.toString());

		// 2. boardService瑜� �넻�빐 update
		boardService.updatePostByPostIdx(map);

		return "complete";
	}

	// <理쒖쑄吏� 肄붾뱶釉붾줉>
	// -----------------------------------------------------

	// 由ы뵆 異붽�
	@PostMapping("post/add/reply")
	@ResponseBody
	public Reply addReply(@RequestBody Reply reply, @SessionAttribute("authentication") Member member, Model model) {

		boardService.insertReply(reply);

		System.out.println("�씤�꽌�듃 由ы뵆::::::::" + reply);

		return reply;
	}

	@PostMapping("post/delete/reply")
	public String deleteReply(@SessionAttribute("authentication") Member member, @RequestBody Reply reply) {

		String replyIdx = reply.getReplyIdx();
		boardService.deleteReplyByReplyIdx(replyIdx);
		return "board/post";
	}

	@PostMapping("post/update/reply")
	public String updateReply(@SessionAttribute("authentication") Member member, @RequestBody Reply reply) {

		String replyIdx = reply.getReplyIdx();

		boardService.updateReplyByReplyIdx(reply);

		return "board/post";
	}

	// <吏��쁺 肄붾뱶釉붾줉>

	// -----------------------------------------------------

	@PostMapping("add/leaf-board")
	@ResponseBody
	public String addLeafBoard(@RequestBody Board board) {


		boardService.insertLeafBoard(board);

		return board.getBdIdx();
	}

	@PostMapping("select/leaf-board-post")
	@ResponseBody
	public List<Map<String, Object>> selectLeafBoardPost(@RequestBody Board board) {

		List<Map<String, Object>> postList = CamelMap.changeListMap(boardService.selectPostListByBdIdx(board.getBdIdx()));

		return postList;
	}

}
