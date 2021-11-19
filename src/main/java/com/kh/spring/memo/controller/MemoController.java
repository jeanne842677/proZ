package com.kh.spring.memo.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.memo.model.dto.Memo;
import com.kh.spring.memo.model.service.MemoService;

@Controller
@RequestMapping("memo")
public class MemoController {

   
   Logger logger = LoggerFactory.getLogger(this.getClass());
   
   @Autowired
   private MemoService memoService;
   
   
   //첫 진입했을 때 뿌려주는곳 
   @GetMapping("{wsIdx}")
   public String boardForm(@PathVariable String wsIdx , Model model) {
	
	   model.addAttribute(wsIdx);
	   
	   List<Memo> memoList = memoService.selectMemoByWsIdx(wsIdx);
	   System.out.println(memoList);
	   
	   model.addAttribute(memoList);
	   
	   
	   return "/memo/memo" ;
	   
	   
   };
   
   
   
   //패치로 요청할 메소드입니다.
   @PostMapping("add/memo")
   @ResponseBody
   public String addMemo(@RequestBody Memo memo , @SessionAttribute("authentication") Member member) {
	   
	   
	   memoService.insertMemo(memo, member);
	   System.out.println(memo);
	   
	   
	   return "complete" ;
	   
   }
   
   
   
   
}