package com.kh.spring.loadmap.model.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.spring.loadmap.model.dto.Loadmap;

@Mapper
public interface LoadmapRepository {

	
	@Insert("insert into loadmap(lm_idx , ws_idx , git_repo , git_tree , branch , ignore)"
			+ " values(sc_proz_idx.nextval , #{wsIdx} , #{gitRepo} , #{gitTree} , #{branch} , #{ignore})")
	public void insertGit(Loadmap loadmap) ;

	
	@Select("select * from loadmap where ws_idx = #{wsIdx} ")
	public Loadmap selectLoadmapByWsIdx(String wsIdx);


	@Delete("delete from loadmap where ws_idx=#{wsIdx}")
	public void deleteLoadmapByWsIdx(String wsIdx);
	

}
