package com.kh.spring.cash.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.cash.model.repository.CashRepository;
import com.kh.spring.common.code.CashItem;

@Service
public class CashServiceImpl implements CashService {
	
	@Autowired
	CashRepository cashRepository;
	

	@Override
	public List<Map<String,Object>> selectPmIdxByProjectIdx(String projectIdx) {
		return cashRepository.selectPmIdxByProjectIdx(projectIdx);
	}

	@Override
	public List<String> selectCashNameListByProjectIdx(String projectIdx) {
		return cashRepository.selectCashNameListByProjectIdx(projectIdx);
	}

	@Override
	public void insertCashIdxByCashNameAndProjectIdx(String projectIdx, String cashName) {
		cashRepository.insertCashIdxByCashNameAndProjectIdx(projectIdx, cashName);
		
	}
	

}
