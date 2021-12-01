package com.kh.spring.jeeyoung;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.List;
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

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*-context.xml" })
public class JeeyoungLoadmapTest {

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
	public void RoadMapTest() throws Exception {
		mockMvc.perform(get("/roadmap/roadmap2")
				)
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	
	
	
	//로드맵 데이터 저장 테스트
	@Test
	public void changeTest() throws Exception {

		Map<String, Object> map = new HashMap<>();
		map.put("wsIdx", "100974");
		map.put("gitRepo", "https://github.com/sazzeo/proZ");
		map.put("branch", "main");
		map.put("ignore", List.of("css" , "resources" , "test" , "img"));

		String fetchData = JsonMaker.json(map);

		mockMvc.perform(post("/loadmap/git/upload").contentType(MediaType.APPLICATION_JSON).content(fetchData))
				.andExpect(status().isOk()).andDo(print());

	}
	

}
