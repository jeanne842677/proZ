package com.kh.spring.project.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.spring.project.model.dto.Project;
import com.kh.spring.project.model.dto.ProjectMember;
import com.kh.spring.project.model.dto.ProjectRole;

@Mapper
public interface ProjectRepository {

	@Update("update project set invite_code = #{newUuid} where project_idx = #{projectIdx}")
	void updateProjectInviteCode(@Param("newUuid") String newUuid, @Param("projectIdx") String projectIdx);

	@Select("select * from project where project_idx = #{projectIdx}")
	Project selectProjectByIdx(String projectIdx);

	@Select("select * from project_member where project_idx = #{projectIdx}")
	List<Map<String, Object>> selectProjectMemberByProjectIdx(String projectIdx);

	List<Map<String, Object>> selectProjectMemberRoleByProjectIdx(String projectIdx);

	@Select("select * from project_role where project_idx = #{projectIdx} order by reg_date")
	List<ProjectRole> selectProjectRoleByIdx(String projectIdx);

	// 은비가 작성한 코드 시작
	@Select("select * from project_member where userIdx = #{userIdx}")
	ProjectMember selectProjectMemberByUserIdx(String userIdx);

	@Insert("insert into project(pro_name, pro_description) values(#{proName}, #{proDescription})")
	Project createProject(String proName, String proDescription);

	@Insert("insert all into project_role values(sc_auth_idx.nextval, #{projectIdx}, '관리자', 1, 1, 1)"
			+ " into project_role(sc_auth_idx.nextval, #{projectIdx}, '일반') select * from dual") // reg_date는 default 값
	ProjectRole createRole(String projectIdx);

	@Insert("insert into project_member"
			+ " select (sc_pm_idx.nextval, #{projectIdx}, #{userIdx}, #{authIdx}-1, nickname, profileColor"
			+ " from project_member left join proZ_user using(#{userIdx})")
	ProjectMember allocateAdmin(String projectIdx, String userIdx, int authIdx);

	// 은비가 작성한 코드 끝

}
