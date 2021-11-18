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

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class JeeyoungBoardTest {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	 
	   @Autowired
	   WebApplicationContext wac;
	   MockMvc mockMvc;
	      
	   @Before
	   public void setup() {
	      this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	   }
	   

	   //게시판 추가테스트
	   @Test
	   public void addBoardTest() throws Exception{
		   
		   Map<String , String> map = new HashMap<>();
		   map.put("bdName" , "게시판제목입니다");
		   map.put("wsIdx" , "999990");
		   map.put("bdSize" , "1");
		   
		   String fetchData = JsonMaker.json(map);
		   
		   mockMvc.perform(post("/board/add-board")
				   .contentType(MediaType.APPLICATION_JSON)
				   .content(fetchData))
		   .andExpect(status().isOk())
		   .andDo(print());
		   
		   
		   
	   }
	   

	   
	   //게시판 진입 테스트
	   @Test
	   public void BoardTest() throws Exception{
		   mockMvc.perform(get("/board/999990"))
		   .andExpect(status().isOk())
		   .andDo(print());
	   }
	   
	   
	   

	   //게시판 자리체인지
	   @Test
	   public void changeTest() throws Exception{
		   
		   Map<String , Object> map = new HashMap<>();
		   map.put("bdIdx" , "100361");
		   map.put("sort" , 2);
		   map.put("changeSort" , 4);
		   map.put("wsIdx" , "999990");
		   
		   String fetchData = JsonMaker.json(map);
		   
		   mockMvc.perform(post("/board/change/sort")
				   .contentType(MediaType.APPLICATION_JSON)
				   .content(fetchData))
		   .andExpect(status().isOk())
		   .andDo(print());
		   
		   
		   
	   }
	   
	   
	   

}
