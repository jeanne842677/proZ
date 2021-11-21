package com.kh.spring.member.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ChangePasswordValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ChangePasswordForm.class.equals(clazz); 
	}

	@Override
	public void validate(Object target, Errors errors) { 

		ChangePasswordForm changePasswordForm = (ChangePasswordForm) target; 
		
		if(changePasswordForm.getPassword() != null) {
			// 먼저 중복검사 
			String password = changePasswordForm.getPassword(); 
			String checkedPassword = changePasswordForm.getCheckedPassword(); 
			boolean valid = password.equals(checkedPassword);  
			valid = Pattern.matches("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9]).{8,70}", password);
			if(!valid) {
				errors.rejectValue("password", "error-profilePassword");
			}
		}
		
	}

}
