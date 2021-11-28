package com.kh.spring.calendar.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.spring.calendar.model.dto.Calendar;

@Mapper
public interface CalendarRepository {
	
	//컬러 안넣었음.
	@Insert("insert into Calendar(cal_idx , ws_idx , pm_idx, cal_title, cal_content , start_date, end_date)"
			+ " values(sc_proz_idx.nextval , #{wsIdx} , #{pmIdx} , #{calTitle}, #{calContent} ,#{startDate} , #{endDate})")
	void insertCalendar(Calendar calendar);

	
	@Select("select * from calendar where ws_idx = #{wsIdx}")
	List<Calendar> selectCalendarListByWsIdx(String wsIdx);


	void updateCalendar(Calendar calendar);

}
