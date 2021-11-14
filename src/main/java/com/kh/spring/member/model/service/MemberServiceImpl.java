package com.kh.spring.member.model.service;


import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kh.spring.common.code.Config;
import com.kh.spring.common.mail.MailSender;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.repository.MemberRepository;
import com.kh.spring.member.validator.JoinForm;

import lombok.RequiredArgsConstructor;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.repository.MemberRepository;

@Service

public class MemberServiceImpl implements MemberService{

   @Autowired
   private MemberRepository memberRepository;
   
   @Autowired
   private  MailSender mailSender;
   @Autowired
   private  RestTemplate http;
   @Autowired
   private  PasswordEncoder passwordEncoder;
   
   public Member selectMemberByEmail(String email) {
      return memberRepository.selectMemberByEmail(email);
   }
   
   public void insertMember(JoinForm form) {
      form.setPassword(passwordEncoder.encode(form.getPassword()));
        memberRepository.insertMember(form); }
   
   public void authenticateByEmail(JoinForm form, String token) {
      MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
      body.add("mailTemplate", "join-auth-mail");
      body.add("nickname", form.getNickname());
      body.add("persistToken", token);
      
      //RestTemplate의 기본 Content-type : application/json
      RequestEntity<MultiValueMap<String, String>> request =
            RequestEntity.post(Config.DOMAIN.DESC+"/mail")
            .accept(MediaType.APPLICATION_FORM_URLENCODED)
            .body(body);
      
      String htmlTxt = http.exchange(request, String.class).getBody();
      mailSender.send(form.getEmail(), "회원가입을 축하합니다.", htmlTxt);
   }

   public Member selectMemberByEmailAndPassword(Member member) {
	   Member storedMember = memberRepository.selectMemberByEmail(member.getEmail());
		
		if(storedMember != null && passwordEncoder.matches(member.getPassword(), storedMember.getPassword())) {
			return storedMember;
		}
		
		return null;
      }

   @Override
   public String getReturnAccessToken(String code) {
        String access_token = "";
        String refresh_token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";
       
       try {
           URL url = new URL(reqURL);
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           
            //HttpURLConnection 설정 값 셋팅
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            
            // buffer 스트림 객체 값 셋팅 후 요청
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=664641");  //앱 KEY VALUE
            sb.append("&redirect_uri=http://localhost:9090/member/test"); // 앱 CALLBACK 경로
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();
            
            //  RETURN 값 result 변수에 저장
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String br_line = "";
            String result = "";

            while ((br_line = br.readLine()) != null) {
                result += br_line;
            }

        //    JsonParser parser = new JsonParser(); 
         //   JsonElement element =	 parser.parse(result);
            
            // 토큰 값 저장 및 리턴
           // access_token = element.getAsJsonObject().get("access_token").getAsString();
           // refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_token;
    }


   @Override
   public Map<String,Object> getUserInfo(String access_token) {
       Map<String,Object> resultMap =new HashMap<>();
       String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

           //요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String br_line = "";
            String result = "";

            while ((br_line = br.readLine()) != null) {
                result += br_line;
            }
           System.out.println("response:" + result);

			
			
           
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultMap;
    }


}