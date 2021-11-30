package com.kh.spring.loadmap.model.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.spring.loadmap.model.dto.Loadmap;

@Mapper
public interface LoadmapRepository {

	
	@Select("")
	public void insertGit(String projectIdx, Loadmap loadmap) ;
	
	

}
