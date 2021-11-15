package com.kh.spring.eunbi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpSession;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.spring.common.util.json.JsonMaker;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.project.model.dto.Project;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*-context.xml" })
public class eunbiTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	// MockMvc : http 요청 및 응답 상황 테스트를 위한 객체

	@Autowired
	WebApplicationContext wac;
	MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	
	@Test
	public void insertProjectImplTest() throws Exception {
		Member member = new Member();
		member.setUserIdx("100021");
		member.setEmail("ebcode2021@gmail.com");
		member.setPassword("ebeb0914*");
		member.setNickname("은비쨩");

		Project project = new Project();
		project.setProName("프로젝트 이름");
		project.setProDescription("프로젝트 상세 설명");
		
		String projectJson = JsonMaker.json(project);
		logger.debug(projectJson);
		
		
		mockMvc.perform(post("/project/project-list")
				.contentType(MediaType.APPLICATION_JSON)
				.sessionAttr("authentication", member)
				.content(projectJson))
				.andExpect(status().isOk())
				.andDo(print());
				
	}
	
	@Test
	public void selectProjectList() throws Exception{
		Member member = new Member();
		member.setUserIdx("100021");
		mockMvc.perform(get("/project/project-list")
		.sessionAttr("authentication", member))		
		.andExpect(status().isOk())
		.andDo(print());
					
				
				
	}

}
