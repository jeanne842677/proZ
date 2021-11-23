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
	public Memo insertMemo(Memo memo, Member member) {
		
		
		ProjectMember projectmember = memoRepository.selectProjectMember(member.getUserIdx() , memo.getWsIdx());
		memo.setPmIdx(projectmember.getPmIdx());
		
		
		 memoRepository.insertMemo(memo);
		
		
		System.out.println(projectmember);
		
		return memo;
		
	}

	@Override
	public List<Memo> selectMemoByWsIdx(String wsIdx) {
		

		return memoRepository.selectMemoByWsIdx(wsIdx);
	}

	@Override
	public ProjectMember selectProjectMember(String userIdx, String wsIdx) {
		return memoRepository.selectProjectMember(userIdx, wsIdx);
	}

	@Override
	public void deleteMemoByMemoIdx(String memoIdx) {
		memoRepository.deleteMemoByMemoIdx(memoIdx);
		
	}

	@Override
	public List<Memo> selectMemoByWsIdxAsc(String wsIdx) {
		// TODO Auto-generated method stub
		return memoRepository.selectMemoByWsIdxAsc(wsIdx);
	}
	
	   @Override
	   public void updateMemoByMemoIdx(Memo memo) {
	      memoRepository.updateMemoByMemoIdx(memo);
	   }

	@Override
	public List<Memo> selectMemoBySearch(String wsIdx, String search) {
		// TODO Auto-generated method stub
		return memoRepository.selectMemoBySearch(wsIdx,search);
	}

}
