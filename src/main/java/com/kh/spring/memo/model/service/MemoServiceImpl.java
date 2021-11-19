package com.kh.spring.memo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.memo.model.dto.Memo;
import com.kh.spring.memo.model.repository.MemoRepository;
import com.kh.spring.project.model.dto.ProjectMember;

@Service
public class MemoServiceImpl implements MemoService{

	@Autowired
	private  MemoRepository memoRepository;
	
	@Override
	public void insertMemo(Memo memo, Member member) {
		
		
		ProjectMember projectmember = memoRepository.selectProjectMember(member.getUserIdx() , memo.getWsIdx());
		memo.setPmIdx(projectmember.getPmIdx());
		
		memoRepository.insertMemo(memo);
		
		
		
		System.out.println(projectmember);
		
		
		
	}

	@Override
	public List<Memo> selectMemoByWsIdx(String wsIdx) {
		

		return memoRepository.selectMemoByWsIdx(wsIdx);
	}

}
