package com.kh.spring.member.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kh.spring.common.code.ErrorCode;
import com.kh.spring.member.model.dto.Member;

@Component
public class MypageValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return MypageForm.class.equals(clazz); 
	}

	@Override
	public void validate(Object target, Errors errors) { 

		MypageForm mypageForm = (MypageForm) target; 
		
		if(mypageForm.getProfileColor() != null) {
			boolean valid = Pattern.matches("^([a-fA-F0-9]{8})$", mypageForm.getProfileColor());
			if(!valid) {
				errors.rejectValue("profileColor", "error-profileColor");
			}
		}
		
		if(mypageForm.getNickname() != null) {
			boolean valid = Pattern.matches("^[a-zA-Z0-9ㄱ-힣]{1,8}$", mypageForm.getNickname());
			if(!valid) {
				errors.rejectValue("profileColor", "error-profileColor");
			}
		}
		
		if(mypageForm.getGit() != null) {
			boolean valid = mypageForm.getGit().isBlank();
			valid = mypageForm.getGit().contains(" ");
			if(valid) {
				errors.rejectValue("profileColor", "error-profileColor");
			}
		}
		
		if(mypageForm.getPassword() != null) {
			// 먼저 중복검사 
			String password = mypageForm.getPassword(); 
			String checkedPassword = mypageForm.getCheckedPassword(); 
			boolean valid = password.equals(checkedPassword);  
			valid = Pattern.matches("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9]).{8,70}", password);
			if(!valid) {
				errors.rejectValue("password", "error-profilePassword");
			}
		}
		
	}

}
