package com.kh.spring.loadmap.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.spring.loadmap.model.dto.GitCommit;
import com.kh.spring.loadmap.model.dto.Loadmap;

@Mapper
public interface LoadmapRepository {

	
	public void insertGit(Loadmap loadmap) ;

	
	@Select("select * from loadmap where ws_idx = #{wsIdx} ")
	public Loadmap selectLoadmapByWsIdx(String wsIdx);


	@Delete("delete from loadmap where ws_idx=#{wsIdx}")
	public void deleteLoadmapByWsIdx(String wsIdx);
	
	@Insert("insert into git_commit(gc_idx , lm_idx, login , message , commit_date , files)"
			+ " values(sc_proz_idx.nextval , #{lmIdx} , #{login} , #{message} , #{commitDate} , #{files})")
	public void insertGitCommit(GitCommit gitCommit);

	
	@Select("select * from (select * from git_commit where lm_idx = #{lmIdx} order by commit_date desc) where rownum <=5")
	public List<GitCommit> selectGitCommitListByLmIdx(String lmIdx);
	
	

}
