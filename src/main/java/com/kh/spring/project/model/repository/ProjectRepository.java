package com.kh.spring.project.model.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.spring.project.model.dto.Project;

@Mapper
public interface ProjectRepository {

	@Update("update project set invite_code = #{newUuid} where project_idx = #{projectIdx}")
	void updateProjectInviteCode(@Param("newUuid")String newUuid  , @Param("projectIdx")String projectIdx);
	
	
	@Select("select * from project where project_idx = #{projectIdx}")
	Project selectProjectByIdx(String projectIdx);
	
	
	
}
