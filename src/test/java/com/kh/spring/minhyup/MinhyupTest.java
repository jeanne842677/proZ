package com.kh.spring.minhyup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class MinhyupTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
    
	   //MockMvc : http 요청 및 응답 상황 테스트를 위한 객체
	   @Autowired
	   WebApplicationContext wac;
	   MockMvc mockMvc;
	      
	   @Before
	   public void setup() {
	      this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	   }
	   
	   
	   
	   @Test //프로젝트 생성시 프로젝트 생성자에게 관리자권한 부여, 일반 역할 생성
	   public void createProject() throws Exception {
		   mockMvc.perform(get("/project/setting/role-management/999998"))
		   .andExpect(status().isOk())
		   .andDo(print());
		   
	   }
}
