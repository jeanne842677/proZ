package com.kh.spring.project.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.spring.project.model.dto.Project;
import com.kh.spring.project.model.dto.ProjectMember;
import com.kh.spring.project.model.dto.ProjectRole;
import com.kh.spring.project.model.dto.Workspace;

@Mapper
public interface ProjectRepository {

	@Update("update project set invite_code = #{newUuid} where project_idx = #{projectIdx}")
	void updateProjectInviteCode(@Param("newUuid") String newUuid, @Param("projectIdx") String projectIdx);

	@Select("select * from project where project_idx = #{projectIdx}")
	Project selectProjectByIdx(String projectIdx);

	@Select("select * from project_member where project_idx = #{projectIdx}  order by is_ok desc , reg_date")
	List<Map<String, Object>> selectProjectMemberByProjectIdx(String projectIdx);

	List<Map<String, Object>> selectProjectMemberRoleByProjectIdx(String projectIdx);

	// 지영 추가코드 시작

	@Select("select * from proz_user left join project_member using(user_idx) where email = #{email}")
	List<Map<String, Object>> selectUserAndMemberByEmail(String email);

	Map<String, Object> selectDefaultOfProjectRole(String projectIdx);

	@Select("select * from project where invite_code = #{inviteCode}")
	Project selectProjectByInviteCode(String inviteCode);

	@Insert("insert into project_member(pm_idx , project_idx, user_idx , auth_idx , nickname , profile_color , is_ok)"
			+ " values(sc_proz_idx.nextval , #{projectIdx} , #{userIdx} , #{authIdx} , #{nickname} , #{profileColor} , #{isOk})")
	void insertProjectMember(ProjectMember projectMember);

	void deleteProjectMember(Map<String, String> map);

	@Update("update project_member set auth_idx = #{authIdx} where pm_idx = #{pmIdx}")
	void updateProjectMemberAuth(Map<String, String> map);

	@Update("update project_member set is_ok = 1 where project_idx = #{projectIdx} and user_idx =#{userIdx}")
	int updateProjectMemberStatus(@Param("projectIdx") String projectIdx, @Param("userIdx") String userIdx);

	ProjectMember selectProjectMemberByMap(Map<String, String> map);

	// 지영 추가 코드 끝

	@Select("select * from project_role where project_idx = #{projectIdx} order by auth_Idx")
	List<ProjectRole> selectProjectRoleByIdx(String projectIdx);

	// 민협 추가 코드 시작

	void updateRoleByPrevAuthName(Map<String, String> map);

	void insertNewRole(ProjectRole role);

	void deleteRoleByAuthIdx(String authIdx);

	void deleteRoleByProjectIdxAndAuthName(ProjectRole role);

	@Update("update project set PRO_NAME = #{proName} , PRO_DESCRIPTION = #{proDescription} where PROJECT_IDX = #{projectIdx}")
	void updateProjectByProjectIdx(Map<String, String> project);

	@Update("update project set IS_DEL = 1 where PROJECT_IDX = #{projectIdx}")
	void updateIsDelProjectByProjectIdx(String projectIdx);

	@Select("select IS_DEL from project where PROJECT_IDX = #{projectIdx}")
	int projectIsDel(String projectIdx);

	ProjectRole selectProjectRoleByProjectIdxAndUserIdx(@Param("projectIdx") String projectIdx,
			@Param("userIdx") String userIdx);

	// 민협 추가 코드 끝

	// 은비가 작성한 코드 시작
	@Select("select * from project where project_idx in(select project_idx from project_member where user_idx = #{userIdx} and is_del = 0) order by project_idx asc")
	List<Project> selectProjectByUserIdx(String userIdx);

//	   @Insert("insert into project(project_idx, pro_name, invite_code, pro_description)"  
//	          + " values(sc_proz_idx.nextval, #{proName}, #{inviteCode}, #{proDescription} )") 
	@Options(useGeneratedKeys = true, keyProperty = "projectIdx")
	int insertProject(Project project);

	@Insert("insert all into project_role(auth_idx, project_idx, auth_name, project_auth, create_auth, member_auth)"
			+ "values(sc_proz_idx.nextval, sc_proz_idx.currval-1, '관리자', 1, 1, 1)"
			+ " into project_role(auth_idx, project_idx, auth_name)"
			+ " values(sc_proz_idx.nextval+1, sc_proz_idx.currval-1, '일반') select * from dual") // reg_date는 default 값
	void insertRole();

	@Insert("insert into project_member(pm_idx, project_idx, user_idx, auth_idx, nickname, profile_color, is_ok)"
			+ "values(sc_proz_idx.nextval, sc_proz_idx.currval-2, #{userIdx}, sc_proz_idx.currval-1, "
			+ "(select nickname from proZ_user where user_idx = #{userIdx}), "
			+ "(select profile_color from proZ_user where user_idx = #{userIdx}), 1)")
	void insertAdmin(String userIdx);

	@Select("select project_idx from project_member where user_idx = #{userIdx} order by project_idx asc")
	List<String> selectProjectIdxByUserIdx(String userIdx);

	@Select("select * from project where project_idx = #{projectIdx}")
	List<Project> selectProjectByProjectIdx(String projectIdx);

	@Select("select * from project where project_idx = #{projectIdx}")
	Project selectProjectExist(String projectIdx);

	//워크스페이스 관련 부분
	@Select("select * from workspace where project_idx = #{projectIdx} order by sort asc")
	List<Workspace> selectWorkspaceByProjectIdx(String projectIdx);

	@Update("update workspace set wsName = #{wsName}, sort = #{sort} where wsIdx=#{wsIdx}")
	void updateWorkspace(String wsIdx, String wsName, int sort);

	@Delete("delete from workspace where wsIdx=#{wsIdx}")
	void deleteWorkspace(String wsIdx);

	@Insert("insert into workspace(ws_idx, project_idx, ws_type, ws_name, sort) "
			+" values(sc_proz_idx.nextval, #{projectIdx}, #{wsType}, #{wsName}, #{sort})")
	void insertWorkspace(String wsIdx, String wsType, String wsName, int sort, String projectIdx);

	@Select("select * from workspace where project_idx = #{projectIdx}")
	List<Map<String, Object>> selectWorkspaceListByProjectIdx(String projectIdx);

	
	
	// 은비가 작성한 코드 끝

}