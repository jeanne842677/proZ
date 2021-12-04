package com.kh.spring.board.controller;

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

	
	//여기 지영이 수정
	@GetMapping("{projectIdx}")
	public String board(@PathVariable String projectIdx, Model model,
			@RequestParam String wsIdx) {

		List<Board> boardList = boardService.selectBoardByWsIdx(wsIdx);
		List<Post> postList = boardService.selectPostListByWsIdx(wsIdx);
		List<Board> LeafBoardList = boardService.selectLeafBoardByWsIdx(wsIdx);
		
		
		System.out.println("리프 보드 리스트: "  + LeafBoardList);
		
		model.addAttribute("boardList" , boardList);
		model.addAttribute("postList" , postList);
		model.addAttribute("LeafBoardList" , LeafBoardList);
		
		
		
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
	
	// 3)board_changeSort  //지영 수정 
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
	   
	   // 2) Posting file 비동기전송 Code 
	   @PostMapping(value = "posting/fileIo", produces = "application/json; charset=utf-8")
	   @ResponseBody
	   public String insertFileAsync(HttpSession session, 
	         @RequestParam(required = false) String projectIdx, 
	         @RequestParam(required = false) List<MultipartFile> files) {

	      //1. 파일 추출 및 DB저장 
	      FileUtil fileUtil = new FileUtil(); 
	      FileDTO fileUploaded = fileUtil.fileUpload(files.get(0));
	      boardService.insertPostFile(fileUploaded, projectIdx);
	      
	      //2. DTO를 JSON형식으로 전달 
	      return JsonMaker.json(fileUploaded); 
	   }
	   
	   // **Post 관련 메서드 
	   // 1) Post_getMapping 
	   @GetMapping("view/{projectIdx}")
	   public String getBoardInfo(@PathVariable String projectIdx,
	         @RequestParam(required = false) String postIdx,
	         @RequestParam(required = false) String wsIdx,
	         Model model) {
	      
	      // 1. postIdx로 공개할 post를 받아 model에 넣는다. 
	      Post newPost = boardService.selectPostByPostIdx(postIdx); 
	      
	      model.addAttribute("post",newPost);
	      model.addAttribute("isSamePM", newPost.getPmIdx()); 
	      // 2. 사용자의 pmIdx = post.pmIdx가 같은지를 검증, 수정-삭제버튼 활성화 여부를 결정한다.  
	      
	      List<Map<String,Object>> replyList = boardService.selectReplyByPostIdx(postIdx);
	      System.out.println("replyList 출력 : "+replyList);
	      replyList = CamelMap.changeListMap(replyList);
	      model.addAttribute("replyList",replyList);
	      
	      // 3. post로 return한다. 
	      return "board/post"; 
	   }
	   
	   // 2) remove_Post 
	   @PostMapping("view/remove-post")
	   @ResponseBody
	   public String removePost(@RequestParam String postIdx) {
	      // delete_repository를 작동시킨다. 
	      boardService.deletePost(postIdx); 
	      return "success";
	   }

	   // 3) change_Post 
	   @PostMapping("view/change-post")
	   public String changePost(@RequestParam String content,
	         @RequestParam String bdidx,
	         Model model) {
	   
	      // 1. posting 접근 시 필요한 wsIdx를 조회해서 불러온다. 
	      Board board = boardService.selectBoardByBdIdx(bdidx);
	      if(board == null) {
	         // 존재하지 않거나, 삭제된 보드임 > 알람처리 필요 
	         // 이때 projectIdx를 받아 다시 보내야 한다.  
	         // wsIdx 역시 필요하다. 
	      }
	      model.addAttribute("wsIdx" , board.getWsIdx());
	      // 버튼 검증은 이미 끝났으므로, change-POST는 redirect한다. 
	      model.addAttribute("content", content);
	      
	      // selection 
	      
	      // 이후 모델 객체를 점검, content 여부에 따라 InnerHTML 추가  
	      
	      return "/board/posting";
	   }

	
	// <최윤지 코드블록> 
	// -----------------------------------------------------
	
	

	   @PostMapping("post/add/reply")
	   @ResponseBody
	   public String addReply(@RequestBody Reply reply , 
	         @SessionAttribute("authentication") Member member,
	         Model model
	         )  {
	      
	      System.out.println("리플 :::"  + reply);
	      //insert 작성 
	      
	      boardService.insertReply(reply);
	      
	      System.out.println(reply);
	      
	      
	      return  "board/post"; 
	   }
	   
	   @PostMapping("post/delete/reply")
	   public String deleteReply(
	         @SessionAttribute("authentication") Member member,
	         @RequestBody Reply reply) {
	      
	      String replyIdx = reply.getReplyIdx();
	      System.out.println("replyIdx"+replyIdx);
	      boardService.deleteReplyByReplyIdx(replyIdx);
	      return "board/post";
	   }
	   
	   @PostMapping("post/update/reply")
	   public String updateReply(
	         @SessionAttribute("authentication") Member member,
	         @RequestBody Reply reply) {
	      
	      String replyIdx = reply.getReplyIdx();
	      
	      System.out.println("replyIdx"+replyIdx);
	      System.out.println("수정할 reply"+reply);
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
