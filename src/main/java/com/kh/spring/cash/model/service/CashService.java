package com.kh.spring.cash.model.service;

import java.util.List;
import java.util.Map;


public interface CashService {

	List<Map<String,Object>> selectPmIdxByProjectIdx(String projectIdx);

	List<String> selectCashNameListByProjectIdx(String projectIdx);

	void insertCashIdxByCashNameAndProjectIdx(String projectIdx, String cashName);

	

}
