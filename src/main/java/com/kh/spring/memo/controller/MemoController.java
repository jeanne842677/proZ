package com.kh.spring.memo.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.spring.common.util.json.JsonMaker;
import com.kh.spring.common.util.map.CamelMap;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.memo.model.dto.Memo;
import com.kh.spring.memo.model.service.MemoService;
import com.kh.spring.project.model.dto.ProjectMember;

@Controller
@RequestMapping("memo")
public class MemoController {

   
   Logger logger = LoggerFactory.getLogger(this.getClass());
   
   @Autowired
   private MemoService memoService;
   
   
   //첫 진입했을 때 뿌려주는곳 
   @GetMapping("{projectIdx}")
   public String boardForm(@PathVariable String projectIdx , Model model,
		   				   @RequestParam(value = "wsIdx") String wsIdx,
		   				   @SessionAttribute("authentication") Member member,
		   				   @RequestParam(value = "order") int order
		   
		   				   
		   				) {
	   
	   List<Map<String,Object>> memoList =  new ArrayList<Map<String,Object>>();
		 
	   if(order == 0) { //desc 내림차순
		  memoList = memoService.selectMemoAndWriterByWsIdxDesc(wsIdx);
	   }else{  //asc 오름차순
		  memoList = memoService.selectMemoAndWriterByWsIdxAsc(wsIdx);
	   }
	   
	   memoList = CamelMap.changeListMap(memoList);
	   
	   for (Map<String, Object> map : memoList) {
		  map.replace("regDate", map.get("regDate").toString().substring(0,16));
	}
	   System.out.println("memoList -------------------"+memoList);
	   ProjectMember projectMember = memoService.selectProjectMember(member.getUserIdx(),wsIdx);
	   
	   
	   
	   
	   model.addAttribute("memoList",memoList);
	   model.addAttribute("wsIdx",wsIdx);
	   model.addAttribute("userPmIdx",projectMember.getPmIdx());
	   model.addAttribute("projectIdx",projectIdx);
	   model.addAttribute("order",order);
	   
	 
	   
	   return "/memo/memo" ;
	   
	   
   };
   
   
   //검색할 때 
   @GetMapping("{projectIdx}/{search}")
   public String searchForm(@PathVariable String projectIdx , Model model,
		   				   @RequestParam(value = "wsIdx") String wsIdx,
		   				   @SessionAttribute("authentication") Member member,
		   				   @PathVariable String  search
		   				   
		   				) {
	   List<Memo> memoList = memoService.selectMemoBySearch(wsIdx,search);
	 
	   System.out.println(wsIdx);
	   ProjectMember projectMember = memoService.selectProjectMember(member.getUserIdx(),wsIdx);
	   System.out.println("projectMember : " + projectMember);
	   System.out.println(memoList);
	   
	   model.addAttribute(memoList);
	   model.addAttribute("wsIdx",wsIdx);
	   model.addAttribute("userPmIdx",projectMember.getPmIdx());
	   
	   return "/memo/memo" ;
	   
	   
   };
   
   
   
   //패치로 요청할 메소드입니다.
   @PostMapping("add/memo")
   @ResponseBody
   public String addMemo(@RequestBody Memo memo , @SessionAttribute("authentication") Member member) {
	   
	   System.out.println("오기 전" + memo);
	   Memo insertedMemo = memoService.insertMemo(memo, member);
	   
	 
	   
	   return JsonMaker.json(memo) ;
	   
	   
	   
   }
   
   @PostMapping("delete/memo")
   public String deleteMemo(@RequestBody Map<String,String> commandMap , @SessionAttribute("authentication") Member member) {
	   
	   //인터셉터 추후 작성해야됨 같은 유저가 삭제할 수 있도록
	   String memoIdx = commandMap.get("memoIdx");
	   
	   memoService.deleteMemoByMemoIdx(memoIdx);
	   
	   return "complete" ;
   }
   
   
   @PostMapping("modify/memo")
   public String modifyMemo(@RequestBody Memo memo , @SessionAttribute("authentication") Member member) {
      
      memoService.updateMemoByMemoIdx(memo);
      
      return "complete" ;
   }
   
   
}