package com.kh.spring.projectmember.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.spring.projectmember.dto.Alarm;

@Mapper
public interface AlarmRepository {
	
	
	@Select("select post_title , post_idx , r.pm_idx as reply_member ,  pm.user_idx from reply r join post p using(post_idx) join project_member pm on(p.pm_idx = pm.pm_idx) where reply_idx = #{replyIdx}")
	Map<String, Object> selectPostAndReplyJoin(String replyIdx);

	@Insert("insert into alarm(al_idx , user_idx , project_idx ,al_type , al_content , link)"
			+ " values(sc_proz_idx.nextval , #{userIdx} , #{projectIdx}, #{alType} , #{alContent} , #{link} )")
	void insetAlarm(Alarm alarm);
	
	@Select("select * from (select * from alarm where user_Idx =#{userIdx} order by al_idx desc) where  project_idx = #{projectIdx} and rownum < 10")
	List<Alarm> selectAlramListByUserIdx(@Param("userIdx") String userIdx , @Param("projectIdx") String projectIdx);

	
	@Update("update alarm set is_look = 1 where user_idx = #{userIdx} and project_idx = #{projectIdx}")
	void updateAlarmIsLook(Alarm alarm);

}
