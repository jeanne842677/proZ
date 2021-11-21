package com.kh.spring.member.validator;

import java.util.regex.Pattern;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmailValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return EmailForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		EmailForm emailForm = (EmailForm) target; 
		
		if(emailForm != null) {
			System.out.println("1. emailValidator 접근 확인");
			boolean res = Pattern.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$"
					, emailForm.getEmail());
			System.out.println("2. 정규표현식 결과는 : " + res);
			if(res) {
				return; 
			} else {
				errors.rejectValue("email", "email-error");
			}
		}
		
		errors.rejectValue("email", "email-nullError");
	}

}
