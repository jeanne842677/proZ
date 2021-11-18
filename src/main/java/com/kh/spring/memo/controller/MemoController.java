package com.kh.spring.memo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.kh.spring.memo.model.service.MemoService;


@Controller
@RequestMapping("memo")
public class MemoController {

   
   Logger logger = LoggerFactory.getLogger(this.getClass());
   private MemoService memoService;
   
   @GetMapping("memo")
   public void boardForm() {};
}