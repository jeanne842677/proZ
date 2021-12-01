<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<script src="https://kit.fontawesome.com/485bb3ceac.js" crossorigin="anonymous"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<link rel="stylesheet" href="/resources/css/nav.css">
<script type="text/javascript" src="/resources/js/nav.js"></script>
<style type="text/css">
html, body {
   width: 100%;
   height: 100%;
   min-width: 1300px;
}

.wrap {
   height: 100%;
   display: flex;
   flex-direction: column;
}

header {
   height: 60px;
   background: rgb(143, 122, 229);
   background: radial-gradient(circle, rgba(143, 122, 229, 1) 0%,
      rgba(241, 112, 170, 1) 100%);
   min-height: 60px;
}

.con {
   height: 100%;
   display: flex;
   justify-content: space-between;
   overflow: auto;
}

nav {
   background: rgb(19, 23, 34);
   background: linear-gradient(0deg, rgba(19, 23, 34, 1) 0%,
      rgba(26, 17, 47, 1) 22%, rgba(26, 30, 41, 1) 83%, rgba(19, 23, 34, 1)
      100%);
   width: 220px;
   height: 100%;
   flex-shrink: 0;
}
/* section ******************************* */
section {
   max-height: 100%;
   width: 100%;
   display: flex;
   flex-direction: column;
   height: 100%;
   overflow: hidden;
   background: RGB(245, 246, 247);
}

article {
   padding-left: 10px;
   padding-right: 10px;
}

/* article 간격 */
.title-section {
   padding-top: 15px;
   height: 70px;
}

.chat-section {
   flex: 1;
   overflow-y: auto;
   display: flex;
   flex-direction: column;
}

.chat-input-section {
   height: auto;
   background: linear-gradient(0deg, rgba(19, 23, 34, 1) 0%,
      rgba(26, 17, 47, 1) 22%, rgba(26, 30, 41, 1) 83%, rgba(19, 23, 34, 1)
      100%);
   padding-top: 4px;
   display: flex;
   width: 100%;
}

/* article -> title-section css */
#room-title {
   font-size: x-large;
   font-weight: bolder;
}

.title-section>hr {
   margin: 0;
   height: 2.5px;
   margin-top: 10px;
}

.today {
   display: flex;
   flex-basis: 100%;
   align-items: center;
   color: rgba(0, 0, 0, 0.35);
   font-size: 12px;
   margin: 8px 0px;
}

.today::before, .today::after {
   content: "";
   flex-grow: 1;
   background: rgba(0, 0, 0, 0.35);
   height: 1px;
   font-size: 0px;
   line-height: 0px;
   margin: 0px 16px;
}

/* article -> chat-section css */
.chat-other, .chat-me {
   display: flex;
   padding-top: 5px;
   padding-left: 5px;
}

.chat-me {
   justify-content: flex-end;
}

.chat-content {
   display: flex;
   justify-content: flex-end;
}

.chat-content {
   box-shadow: 1px 1px 1px 1px rgba(95, 62, 62, 0.2);
   background: white;
   padding: 12px 5px 12px 5px;
   margin: 2px 10px 2px 10px;
   border-radius: 5px;
   margin-bottom: 7px;
   width: max-content;
   max-width: 59vw;
}

.talk-me {
   display: flex;
   flex-direction: column;
   align-items: flex-end;
}

.user {
   display: flex;
}

.user-nickname {
   font-size: 15px;
   font-weight: bolder;
   margin-left: 8px;
}

.currentTime {
   padding-top: 5px;
   padding-left: 20px;
   padding-right: 20px;
   font-size: 10px;
}

/* article -> input-chat-section css */
.chat-input-wrap {
   display: flex;
   padding-bottom: 5px;
   width: 100%;
   height: auto;
   flex-direction: row;
}

#textarea {
   background-color: white;
   margin-top: 1%;
   padding: 5px;
   overflow: auto;
   width: 83%;
   border: none;
   outline: none;
   resize: none;
   border-radius: 3px;
   min-height: 60px;
   max-height: 152px;
   height: auto;
}

#textarea:empty::before {
   content: attr(placeholder);
   color: gray;
}

.textarea-icons {
   width: 17%;
   background-color: white;
   margin-top: 1%;
   overflow-y: hidden;
   display: flex;
   justify-content: space-around;
   align-items: center;
   border-radius: 3px;
}

/* aside 부분****************************** */
aside {
   background: rgb(19, 23, 34);
   background: linear-gradient(0deg, rgba(19, 23, 34, 1) 0%,
      rgba(26, 17, 47, 1) 22%, rgba(26, 30, 41, 1) 83%, rgba(19, 23, 34, 1)
      100%);
   height: 100%;
   width: 200px;
   flex-shrink: 0;
}

#login-btn {
   width: 200px;
   height: 100px;
}

.clone-me, .clone-other {
   display: none;
}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

   <div class="wrap">
        <header><%@ include file="/WEB-INF/views/include/nav/header.jsp" %> </header>

        <div class="con">
            <nav><%@ include file="/WEB-INF/views/include/nav/main-nav.jsp" %></nav>
            <section>
                <article class="title-section">
                  <div id="room-title">#&nbsp;&nbsp;${workspace.wsName}</div>
                  <hr>
                </article>

            <article class="chat-section">
               <c:forEach var="chat" items="${chatList}">
                  <c:choose>
                     <c:when test="${chat.pmIdx==projectMember.pmIdx}">
                        <div class="chat-block chat-me">
                           <div class="chat-information">
                              <div class="user">
                                 <div class="currentTime">${chat.regDate}</div>
                                 <div class="user-nickname">${chat.nickname}</div>
                              </div>
                              <div class="talk-me">
                                 <div class="chat-content">${chat.content}</div>
                              </div>
                           </div>
                           <div class="user-picture">
                              <i class="fas fa-user-circle fa-3x"
                                 style="color: lightskyblue"></i>
                           </div>
                        </div>
                     </c:when>
                     <c:otherwise>
                        <div class="chat-block chat-other">
                        <div class="user-picture">
                           <i class="fas fa-user-circle fa-3x" style="color: lightskyblue"></i>
                           <!-- 이미지로 나중에 변경  -->
                        </div>
                        <div class="chat-information">
                           <div class="user">
                              <div class="user-nickname">${chat.nickname}</div>
                              <div class="currentTime">${chat.regDate}</div>
                           </div>
                           <div class="talk-other">
                              <div class="chat-content">${chat.content}</div>
                           </div>
                        </div>
                     </div>
                     </c:otherwise>
                     



                  </c:choose>

               </c:forEach>



            </article>

            <article class="chat-input-section">
                  <div class="chat-input-wrap">
                    <div id="textarea" contenteditable="true"></div> 
                    <div class="textarea-icons">
                      <div id="lock" data-flg='true'><i class="fas fa-unlock fa-2x" cursor="pointer"></i></div>
                      <div id="file"><i class="fas fa-paperclip fa-2x" cursor="pointer"></i></div>
                      <div id="send"><i class="far fa-paper-plane fa-2x" cursor="pointer"></i></div>
                    </div>
                  </div>
                  
                </article>
                
                  <div class="clone-other"> <!-- 복제할코드 -->
                    <div class ="user-picture">
                      <i class="fas fa-user-circle fa-3x" style="color:lightskyblue"></i>
                    </div>
                    <div class ="chat-information">
                      <div class="user">
                        <div class="user-nickname"></div>
                        <div class="currentTime"></div>
                      </div>
                      <div class="talk-other">
                      </div>
                    </div>  
                  </div>
                  

                  <div class="clone-me"> <!-- 복제할코드 -->
                    <div class ="chat-information">
                      <div class="user">
                        <div class="currentTime"></div>
                        <div class="user-nickname"></div>
                      </div>
                      <div class="talk-me">
                      </div>
                    </div>
                    <div class ="user-picture">
                      <i class="fas fa-user-circle fa-3x" style="color:lightskyblue"></i>
                    </div>
                  </div>
                

            </section>
            <aside><%@ include file="/WEB-INF/views/include/nav/aside.jsp" %></aside>
        </div>

    </div>
    
    
    
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"`integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
   
  
    <script>
    
    var stompClient = Stomp.over(new SockJS("/ws-stomp"));
    var chIdx = ${length};
    let wsIdx = "${param.wsIdx}";
   	let nickname = "${projectMember.nickname}";
   	let pmIdx = "${projectMember.pmIdx}";
   
   
       //연결 + 파이어베이스에 있는 정보 불러오기
       stompClient.connect({}, function (frame) {
          
           console.log("connected: " + frame);

           
           stompClient.subscribe('/room/msg/'+wsIdx,function(chat) {
                
                var content = JSON.parse(chat.body); //sendig한 객체를 받아옴.
                
                
                
                if(content.pmIdx == pmIdx) { //내가 입력할 때
                   let newChat = $(".clone-me").clone();
                      newChat.removeClass("clone-me").addClass("chat-block").addClass("chat-me");
                      newChat.find(".talk-me").append("<div class='chat-content'>"+ content.content +"</div>");
                      newChat.find(".currentTime").html(content.regDate);
                      newChat.find(".user-nickname").html(content.nickname);
                      $(".chat-section").append(newChat);
                      
                   
                }else{//다른사람이 입력할 때
                   let newChat = $(".clone-other").clone();
                      newChat.removeClass("clone-other").addClass("chat-block").addClass("chat-other");
                      newChat.find(".talk-other").append("<div class='chat-content'>"+ content.content +"</div>");
                      newChat.find(".currentTime").html(content.regDate);
                      newChat.find(".user-nickname").html(content.nickname);
                      $(".chat-section").append(newChat);
                }
                chIdx = content.chIdx;
                scrollFixedBottom();
          });
           scrollFixedBottom();
       });
       
   
     
     
    //연결 해제

    function disconnect() {
        if(stompClient !== null) {
           stompClient.send("/app/out") , {} , usersessionid.value +"is out chatroom";
           stompClient.disconnect();
           
        }
        
        
     }

    //스크롤 설정
     function scrollFixedBottom(){
       $('.chat-section').scrollTop($('.chat-section')[0].scrollHeight);
    } 
    
    
    
    
    
    
    

    let flg = $("#lock").data('flg');

     document.querySelector('#textarea').addEventListener('paste' , function(event) {
        event.preventDefault();
        let text =event.clipboardData.getData("text/plain");
        
       
        document.execCommand("insertHTML", false,  text);
    })

      // 엔터키를 누르면, #textarea에 입력한 내용이 db에 올라가고 채팅창에도 보이게
      $('#textarea').on('keydown', function(e) {
       
        var today = new Date();
        var year = today.getFullYear();
        var month = ('0' + (today.getMonth() + 1)).slice(-2);
        var day = ('0' + today.getDate()).slice(-2);
        var hours = ('0' + today.getHours()).slice(-2); 
        var minutes = ('0' + today.getMinutes()).slice(-2);
        var dateString = year + '-' + month  + '-' + day + " " + hours + ":" + minutes;
        let inputText = $(this).html();
       
         
         if(e.keyCode==13 && e.shiftKey){
            $(this).append("</br>")
         }
         
         
        
        
         
         
         if (e.which == 13 && !e.shiftKey) {
            
            let lastChatBlock = $(".chat-block").last();
            chIdx++;
            if(lastChatBlock.hasClass("chat-me")) { //마지막채팅이 나 일때
     
                  stompClient.send("/app/msg/" + wsIdx , {}, JSON.stringify({
                           chIdx : chIdx,
                           wsIdx : wsIdx,
                           chName : "채팅방1",
                           content : inputText,
                           regDate : dateString,
                           nickname : nickname,
                           pmIdx : pmIdx
                    }));
                 
               
            }else{ //마지막채팅이 다른사람일때
               
              stompClient.send("/app/msg/" + wsIdx , {}, JSON.stringify({
                   chIdx : chIdx,
                   wsIdx : wsIdx,
                   chName : "채팅방1",
                   content : inputText,
                   regDate : dateString,
                   nickname : nickname,
                   pmIdx : pmIdx
                   
            }));   
            
            }    
           $('#textarea').empty();
          return false;
        }
         
         
         
      });

      //기본적으로 true이면 비잠금 상태
    $("#lock").on('click', function() {
        if(flg){
          $(this).html('<i class="fas fa-lock fa-2x" cursor="pointer"></i>');
          $('#textarea').attr('placeholder', '잠금 상태입니다.');
          $('#textarea').attr('contenteditable', 'false');
          flg = false;
        }else{
          $(this).html('<i class="fas fa-unlock fa-2x" cursor="pointer"></i>');
          $('#textarea').removeAttr('placeholder');
          $('#textarea').attr('contenteditable', 'true');
          window.alert('채팅창 잠금이 해제되었습니다.');
          flg=true;
        }
      });

      
      // 파일 전송 버튼도, 일단은 클릭 이벤트만..
      $('#file').on('click', function(){
       if(flg){
        window.alert("파일 전송 로직 어케될까");
       }else{
        window.alert("잠금을 해제해주세요.");
       }
        
        
      })

      // 전송 버튼을 누르면, #textarea에 입력한 내용이 db에 올라가고 채팅창에도 보이게
      $('#send').on('click', function(){
        if(flg){
          $('#textarea').empty();
          window.alert("전송 버튼 눌렸다?");
          return false;
        }else{
          window.alert("잠금을 해제해주세요.");
        }
      })
    

    
    </script>







</body>
</html>