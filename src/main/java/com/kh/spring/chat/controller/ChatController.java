package com.kh.spring.chat.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.chat.model.dto.Chat;
import com.kh.spring.chat.model.service.ChatService;
import com.kh.spring.common.util.file.FileDTO;
import com.kh.spring.common.util.file.FileUtil;
import com.kh.spring.common.util.json.JsonMaker;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.project.model.dto.Workspace;

@Controller
@RequestMapping("chat")
public class ChatController {
   
   @Autowired
   private ChatService chatService;
   
    @MessageMapping("/msg/{wsIdx}") //이 주소로 보낸 애들한테 메세지를 받음. 구독주소가 빠진거?
    @SendTo("/room/msg/{wsIdx}") //이 주소인 애한테 메세지를 보냄
    public Chat sendChatMessage(@DestinationVariable("wsIdx") String wsIdx
       ,Chat chat, SimpMessageHeaderAccessor headerAccessor, Principal principal) {
        
    	
        chatService.insertChat(chat);
        
        return chat; //response BODY 느낌
        

        
    }
    
    @GetMapping("/chatting/{projectIdx}")
    public String chatting(Model model, @Param(value = "wsIdx") String wsIdx , @SessionAttribute("authentication") Member member,
                      @PathVariable String projectIdx, HttpServletRequest request) {
       
    	chatService.showChattingList(request,member,wsIdx,model);
       
       return "/chat/chat";
    }

    @PostMapping("file/{projectIdx}")
    @ResponseBody
    public String chatFileUpload(@RequestParam(required=false) List<MultipartFile> files,
          HttpSession session, @PathVariable String projectIdx) {
       
       //1) 파일 추출 및 DB저장 
       FileUtil fileUtil = new FileUtil(); 
       FileDTO fileUploaded = fileUtil.fileUpload(files.get(0));
       System.out.println("fileUploaded : " + fileUploaded);
       return JsonMaker.json(fileUploaded); 
    }

}