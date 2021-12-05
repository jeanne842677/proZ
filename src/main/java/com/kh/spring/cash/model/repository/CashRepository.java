package com.kh.spring.cash.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CashRepository {

	@Select("select pm_idx, nickname from project_member where project_idx = #{projectIdx}")
	List<Map<String,Object>> selectPmIdxByProjectIdx(String projectIdx);

	@Select("select cash_name, to_char(expiration_date, 'yyyy-MM-dd') as expiration_date from cash where project_idx = #{projectIdx} and expiration_date >= sysdate" )
	List<Map<String,Object>> selectCashNameListByProjectIdx(String projectIdx);

	@Insert("insert into cash(cash_idx, project_idx, cash_name, expiration_date) values(sc_proz_idx.nextval, #{projectIdx}, #{cashName}, sysdate+30)")
	void insertCashIdxByCashNameAndProjectIdx(@Param("projectIdx") String projectIdx, @Param("cashName")String cashName);
}
