package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dto.Board;
import com.kh.spring.board.model.dto.Post;
import com.kh.spring.board.model.repository.BoardRepository;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.project.model.dto.ProjectMember;
import com.kh.spring.project.model.repository.ProjectRepository;
import com.kh.spring.workspace.model.dto.Workspace;

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

	@Override
	public void deleteBoard(Board board) {

		boardRepository.deleteBoard(board);
		boardRepository.updateBoardSortWhenRemoveBoard(board);

	}

	@Override
	public void updateSort(Map<String, String> map) {

		String bdIdx = map.get("bdIdx");
		String wsIdx = map.get("wsIdx");
		int changeSort = Integer.parseInt(map.get("changeSort"));
		int sort = Integer.parseInt(map.get("sort"));

		// 바뀐 애가 더 작으면?
		if (changeSort < sort) {

			boardRepository.updateBoardSortPlus(map);

		} else if (changeSort > sort) {

			boardRepository.updateBoardSortMinus(map);

		}

		boardRepository.updateBoardSort(map);

	}

	@Override
	public void insertPost(Map<String, String> map, Member member) {

		map.put("userIdx", member.getUserIdx());

		ProjectMember projectMember = projectRepository.selectProjectMemberByMap(map);
		map.put("pmIdx", projectMember.getPmIdx());

		System.out.println(projectMember);
		boardRepository.insertPost(map);

	}

	@Override
	public List<Post> selectPostListByWsIdx(String wsIdx) {

		List<Post> postList = boardRepository.selectPostListByWsIdx(wsIdx);

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

	};

}
