package com.kh.spring.calendar.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.spring.calendar.model.dto.Calendar;

@Mapper
public interface CalendarRepository {
	
	
	@Insert("insert into Calendar(cal_idx , ws_idx , pm_idx, cal_title, cal_content , start_date, end_date , cal_color)"
			+ " values(sc_proz_idx.nextval , #{wsIdx} , #{pmIdx} , #{calTitle}, #{calContent} ,#{startDate} , #{endDate} , #{calColor})")
	void insertCalendar(Calendar calendar);

	
	@Select("select cal_idx , ws_idx , pm_idx , cal_title , cal_color , reg_date , start_date , end_date from calendar where ws_idx = #{wsIdx}")
	List<Calendar> selectCalendarListByWsIdx(String wsIdx);


	void updateCalendar(Calendar calendar);

	@Select("select * from calendar where cal_idx= #{calIdx}")
	Calendar selectCalendarByCalIdx(String calIdx);

}
