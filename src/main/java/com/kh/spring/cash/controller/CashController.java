package com.kh.spring.cash.controller;

import java.util.ArrayList;
import java.util.List;
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
import com.kh.spring.common.util.map.CamelMap;

@Controller
@RequestMapping("cash")
public class CashController {
	//cashController
	
	@Autowired
	CashService cashService;
	
	
	@GetMapping("{projectIdx}")
	public String cashPage(Model model, @PathVariable String projectIdx) {

		model.addAttribute(projectIdx);
		
		//결제 할 예정인 사람의 nickname, pmIdx 저장
		List<Map<String,Object>>  buyerInfo = CamelMap.changeListMap(cashService.selectPmIdxByProjectIdx(projectIdx));
		model.addAttribute("buyerInfo", buyerInfo.get(0));
		
		//결제 유무 확인
		List<Map<String,Object>> cashNameList = new ArrayList<Map<String,Object>>();
		cashNameList = CamelMap.changeListMap(cashService.selectCashNameListByProjectIdx(projectIdx));
		
		for(Map<String,Object> cashName : cashNameList) {
			String item = (String) cashName.get("cashName");
		
			switch(item) {
			case  "멤버 수 추가":
				model.addAttribute("memberFunction", cashName);
				break;
			case "워크스페이스 추가" :
				model.addAttribute("workspaceFunction", cashName);
				break;
			case "채팅 기능" :
				model.addAttribute("chatFunction", cashName);
				break;
			case "전체 결제" :
				model.addAttribute("allFunction", cashName);
				break;
			}
		}
		
		
		return "cash/cash";
	}
	
	
	@PostMapping("{projectIdx}")
	@ResponseBody
	public String cashPage(@PathVariable String projectIdx,
							@RequestBody Map<String,String> map) {
		
		String cashName = map.get("item");
		System.out.println(cashName);
		
		cashService.insertCashIdxByCashNameAndProjectIdx(projectIdx, cashName);
		
		return "success";
	}
	
}
