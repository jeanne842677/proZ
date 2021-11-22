package com.kh.spring.cash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.cash.model.service.CashService;

@Controller
@RequestMapping("cash")
public class CashController {

	
	
	
	@GetMapping("{projectIdx}")
	public String cashPage(Model model, @PathVariable String projectIdx) {
		
		return "cash/cash";
	}
	
}
