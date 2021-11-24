package com.kh.spring.cash.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.spring.cash.model.service.CashService;

@Controller
@RequestMapping("cash")
public class CashController {
	//cashController
	
	
	
	@GetMapping("{projectIdx}")
	public String cashPage(Model model, @PathVariable String projectIdx) {
		System.out.println("projectIdx는" + projectIdx);
		model.addAttribute(projectIdx);
		return "cash/cash";
	}
	
	@PostMapping("{projectIdx}")
	@ResponseBody
	public String cashPage(@PathVariable String projectIdx,
							@RequestBody Map<String,String> map) {
		
		System.out.println(map);
		System.out.println("지나가나요?");
		
		return "success";
	}
	
}
