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
public class jeeyoungProjectTest {
      
   Logger logger = LoggerFactory.getLogger(this.getClass());
      
   //MockMvc : http 요청 및 응답 상황 테스트를 위한 객체
   @Autowired
   WebApplicationContext wac;
   MockMvc mockMvc;
      
   @Before
   public void setup() {
      this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
   }
   
   @Test
   public void loginImplTest() throws Exception{
      mockMvc.perform(post("/member/login")
            .param("email", "jeanne8426@naver.com")
            .param("password", "Jinjinjin79!"))
      .andExpect(status().isOk())
      .andDo(print());
   }
   
   
   @Test
   public void memberSettingTest() throws Exception{
	   mockMvc.perform(get("/project/setting/member-management/999998"))
	   .andExpect(status().isOk())
	   .andDo(print());
   }
   
   
   //멤버 초대 테스트
   @Test
   public void memberInvite() throws Exception{
	   
	   Map<String , String> map = new HashMap<>();
	   map.put("email" , "zo4870@naver.com");
	   map.put("projectIdx" , "999998");
	   String fetchData = JsonMaker.json(map);
	   
	   mockMvc.perform(post("/project/invite-member")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(fetchData))
	   .andExpect(status().isOk())
	   .andDo(print());
	   
	   
	   
   }
   
   //멤버 이메일 조인 테스트
   @Test
   public void joinByEmailTest() throws Exception{
      mockMvc.perform(get("/project/join-by-email/100023/999998")
           )
      .andExpect(status().is3xxRedirection())
      .andDo(print());
   }
   
   
   //트렌잭션 테스트
   @Test
   public void transaction() throws Exception{
	   mockMvc.perform(get("/project/jeeyoungTest")
			   )
	   .andExpect(status().is3xxRedirection())
	   .andDo(print());
   }
   
   
   
   //멤버 강퇴 테스트
   @Test
   public void memberExileTest() throws Exception{
	   
	   Map<String , String> map = new HashMap<>();
	   map.put("pmIdx" , "100098");
	   System.out.println(map);
	   
	   String fetchData = JsonMaker.json(map);
	   System.out.println(fetchData);
	   
	   mockMvc.perform(post("/project/exile")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(fetchData))
	   .andExpect(status().isOk())
	   .andDo(print());
	   
	   
	   
   }
   
   
   //멤버 초대 취소
   @Test
   public void memberinviteCancelTest() throws Exception{
	   
	   Map<String , String> map = new HashMap<>();
	   map.put("pmIdx" , "100193");
	   System.out.println(map);
	   
	   String fetchData = JsonMaker.json(map);
	   System.out.println(fetchData);
	   
	   mockMvc.perform(post("/project/invite-cancel")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(fetchData))
	   .andExpect(status().isOk())
	   .andDo(print());
	   
	   
	   
   }
   
   
}

	 
	

