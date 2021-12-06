package com.kh.spring.board.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dto.Board;
import com.kh.spring.board.model.dto.Post;
import com.kh.spring.board.model.dto.Reply;
import com.kh.spring.board.model.repository.BoardRepository;
import com.kh.spring.common.util.file.FileDTO;
import com.kh.spring.common.util.map.CamelMap;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.memo.model.dto.Memo;
import com.kh.spring.project.model.dto.ProjectMember;
import com.kh.spring.project.model.dto.Workspace;
import com.kh.spring.project.model.repository.ProjectRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ProjectRepository projectRepository;

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

	@Override // 지영이 수정!!!!!!!!!!! 12/5
	public void deleteBoard(Board board) {

		System.out.println("삭제된 보드 :" + board);

		boardRepository.deleteBoard(board);

		// boardRepository.selectBoardAndBoardLeafList(board.getBdIdx());

		boardRepository.updateBoardSortWhenRemoveBoard(board);

	}

	@Override // 지영 수정 파트~~~~~~~~~~
	public void updateSort(Map<String, String> map) {

		String bdIdx = map.get("bdIdx");
		String wsIdx = map.get("wsIdx");
		int changeSort = Integer.parseInt(map.get("changeSort"));

		List<Board> boardList = boardRepository.selectBoardAndBoardLeafList(bdIdx);
		int sort = boardList.get(0).getSort();

		List<String> bdIdxList = new ArrayList<>();
		for (Board b : boardList) {
			bdIdxList.add(b.getBdIdx());
		}

		// 바뀐 애가 더 작으면?
		if (changeSort < sort) {

			boardRepository.updateBoardSortPlus(map);

		} else if (changeSort > sort) {

			boardRepository.updateBoardSortMinus(map);

		}

		boardRepository.updateBoardSort(bdIdxList, changeSort);

	}

	@Override
	public void insertPost(Map<String, String> map, Member member) {

		map.put("userIdx", member.getUserIdx());
		ProjectMember projectMember = projectRepository.selectProjectMemberByMap(map);
		map.put("pmIdx", projectMember.getPmIdx());

		boardRepository.insertPost(map);
	}

	@Override
	public List<Map<String, Object>> selectPostListByWsIdx(String wsIdx) {

		List<Map<String, Object>> postList = CamelMap.changeListMap(boardRepository.selectPostListByWsIdx(wsIdx));

		return postList;
	}

	@Override
	public Board selectBoardByBdIdx(String bdIdx) {

		Board board = boardRepository.selectBoardByBdIdx(bdIdx);
		return board;
	}

	@Override
	public void updatePostSort(Map<String, String> map) {

		String postIdx = map.get("postIdx");
		Post post = boardRepository.selectPostByPostIdx(postIdx);

		// 빠진쪽 애들을 마이너스한다.
		boardRepository.updatePostSortMinus(map);
		// 들어간쪽 애들은 플러스한다.
		boardRepository.updatePostSortPlus(map);
		// 본인은 플러스한다.
		boardRepository.updatePostSort(map);

	}

	@Override
	public Post selectPostByPostIdx(String pmIdx) {
		return boardRepository.selectPostByPostIdx(pmIdx);
	}

	@Override
	public List<Post> selectBoardByTop(String projectIdx) {
		// TODO Auto-generated method stub
		return boardRepository.selectBoardByTop(projectIdx);
	}

	// 유송 추가
	@Override
	public void deletePost(String postIdx) {
		boardRepository.deletePost(postIdx);
	}

	@Override
	public int insertPostFile(FileDTO fileUploaded, String bdIdx) {
		return boardRepository.insertPostFile(fileUploaded, bdIdx);
	}

	@Override
	public String selectWsIdxByPostIdx(String postIdx) {
		return boardRepository.selectWsIdxByPostIdx(postIdx);
	}

	@Override
	public void updatePostByPostIdx(Map<String, String> map) {
		boardRepository.updatePostByPostIdx(map);

	}

	// 윤지 추가

	@Override
	public List<Reply> selectReplyByTop(String projectIdx) {
		// TODO Auto-generated method stub
		return boardRepository.selectReplyByTop(projectIdx);
	}

	@Override
	public void updateReplyByReplyIdx(Reply reply) {

		boardRepository.updateReplyByReplyIdx(reply);
	}

	@Override
	public List<Map<String, Object>> selectReplyByProjectMember(String postIdx) {
		// TODO Auto-generated method stub
		return boardRepository.selectReplyByProjectMember(postIdx);
	}

	@Override
	public Reply insertReply(Reply reply) {
		// TODO Auto-generated method stub
		boardRepository.insertReply(reply);
		return reply;
	}

	@Override
	public void deleteReplyByReplyIdx(String replyIdx) {

		boardRepository.deleteReplyByReplyIdx(replyIdx);
	}

	// 지영 추가

	@Override
	public void insertLeafBoard(Board board) {

		Board parentsBoard = boardRepository.selectBoardByBdIdx(board.getBdIdx());

		board.setParent(parentsBoard.getBdIdx());
		board.setBdSize(parentsBoard.getBdSize());
		board.setSort(parentsBoard.getSort());
		board.setWsIdx(parentsBoard.getWsIdx());

		boardRepository.insertLeafBoard(board);

	}

	@Override
	public List<Board> selectLeafBoardByWsIdx(String wsIdx) {

		return boardRepository.selectLeafBoardByWsIdx(wsIdx);
	}

	@Override
	public List<Post> selectPostListByBdIdx(String bdIdx) {
		return boardRepository.selectPostListByBdIdx(bdIdx);
	}

}
