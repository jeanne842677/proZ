package com.kh.spring.common.interceptor.service;

import java.util.Map;

import com.kh.spring.member.model.dto.Member;

public interface InterceptorService {

	Map<String, Object> selectProjectInfo(String projectIdx, Member member, String wsIdx);

}
