package com.kh.spring.member.model.service;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.validator.JoinForm;


public interface MemberService {
   

   Member selectMemberByEmail(String email);

   void insertMember(JoinForm form);

   void authenticateByEmail(JoinForm form, String token);
   
   Member selectMemberByEmailAndPassword(Member member);
   
   String getReturnAccessToken(String code);

   Map<String, Object> getUserInfo(String kakaoToken);

   Map<String, String> GoogleCallback(String code) throws IOException;

   void insertSocialMember(JoinForm form);

   Member selectGoogleId(String googleId);
   




   

   
   




}