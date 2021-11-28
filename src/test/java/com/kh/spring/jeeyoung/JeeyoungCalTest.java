package com.kh.spring.jeeyoung;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.kh.spring.common.util.json.JsonMaker;
import com.kh.spring.member.model.dto.Member;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*-context.xml" })
public class JeeyoungCalTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	WebApplicationContext wac;
	MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	// 캘린더 Date 타입 전송테스트
	@Test
	public void BoardTest() throws Exception {
		mockMvc.perform(get("/calendar/posting/999998")
				.param("wsIdx", "100936")
				.param("day", "1636470000000"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	// 포스팅 테스트
	@Test
	public void postTest() throws Exception {
		Member member = new Member();
		member.setUserIdx("100023");
		member.setEmail("zo4870@naver.com");
		member.setNickname("간지영");

		Map<String, String> map = new HashMap<>();
		map.put("wsIdx", "100936");

		String fetchData = JsonMaker.json(map);

		mockMvc.perform(post("/calendar/change/add-cal")
				.contentType(MediaType.APPLICATION_JSON)
				.content(fetchData)
				.sessionAttr("authentication", member))
				.andExpect(status().isOk()).andDo(print());

	}
	
	
	

}
