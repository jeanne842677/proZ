package com.kh.spring.member.model.service;


import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.kh.spring.common.code.Config;
import com.kh.spring.common.mail.MailSender;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.repository.MemberRepository;
import com.kh.spring.member.validator.JoinForm;

import lombok.RequiredArgsConstructor;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberRepository memberRepository;

	private final  MailSender mailSender;
	private final  RestTemplate http;
	private final  PasswordEncoder passwordEncoder;
	
	public Member selectMemberByEmail(String email) {
		return memberRepository.selectMemberByEmail(email);
	}
	
	public void insertMember(JoinForm form) {
		//  form.setPassword(passwordEncoder.encode(form.getPassword()));
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
	      return memberRepository.selectMemberByEmailAndPassword(member);
	   }
	
}
