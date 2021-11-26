<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<script src="https://kit.fontawesome.com/485bb3ceac.js"
	crossorigin="anonymous"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

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
        <header> </header>

        <div class="con">
            <nav></nav>
            <section>
                <article class="title-section">
                  <div id="room-title"> # 떡사모 </div>
                  <hr>
                </article>
                <article class="chat-section"> 
                  <div class="chat-other">
                    <div class ="user-picture">
                      <i class="fas fa-user-circle fa-3x" style="color:lightsalmon"></i>
                    </div>
                    <div class ="chat-block chat-information">
                      <div class="user">
                        <div class="user-nickname">엽떡로제</div>
                        <div class="currentTime">오후 1시 16분</div>
                      </div>
                      <div class="talk-other">
                      <div class="chat-content">중국당면먹고싶다</div>
                      <div class="chat-content">배고파</div>
                    </div>
                    </div>
                  </div>
                  <div class="today"> 2021년 11월 5일 </div>
                  
                  <div class="chat-block chat-other">
                    <div class ="user-picture">
                      <i class="fas fa-user-circle fa-3x" style="color:lightskyblue"></i>
                    </div>
                    <div class ="chat-information">
                      <div class="user">
                        <div class="user-nickname">소떡소떡</div>
                        <div class="currentTime">오후 1시 15분</div>
                      </div>
                      <div class="talk-other">
                        <div class="chat-content">길어지면 한 블럭 내에 생기고ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</div>
                        <div class="chat-content">이렇게 한칸 뛰고</div>
                       </div>
                      </div>  
                  </div>

                  <div class="chat-block chat-other">
                    <div class ="user-picture">
                      <i class="fas fa-user-circle fa-3x" style="color:lightskyblue"></i>
                    </div>
                    <div class ="chat-information">
                      <div class="user">
                        <div class="user-nickname">소떡소떡</div>
                        <div class="currentTime">오후 1시 15분</div>
                      </div>
                      <div class="talk-other">
                        <div class="chat-content">길어지면 한 블럭 내에 생기고ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</div>
                        <div class="chat-content">이렇게 한칸 뛰고</div>
                       </div>
                      </div>  
                  </div>
                  

                  <div class="chat-block chat-me">
                    <div class ="chat-information">
                      <div class="user">
                        <div class="currentTime">오후 1시 15분</div>
                        <div class="user-nickname">고기맛</div>
                      </div>
                      <div class="talk-me">
                        <div class="chat-content">이게나</div>
                        <div class="chat-content">이렇게 한칸 뛰고</div>
                      </div>
                    </div>
                    <div class ="user-picture">
                      <i class="fas fa-user-circle fa-3x" style="color:lightskyblue"></i>
                    </div>
                  </div>
                
                  
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
                        <div class="user-nickname">소떡소떡</div>
                        <div class="currentTime">오후 1시 15분</div>
                      </div>
                      <div class="talk-other">
                      </div>
                    </div>  
                  </div>
                  

                  <div class="clone-me"> <!-- 복제할코드 -->
                    <div class ="chat-information">
                      <div class="user">
                        <div class="currentTime">오후 1시 15분</div>
                        <div class="user-nickname">고기맛</div>
                      </div>
                      <div class="talk-me">
                      </div>
                    </div>
                    <div class ="user-picture">
                      <i class="fas fa-user-circle fa-3x" style="color:lightskyblue"></i>
                    </div>
                  </div>
                

            </section>
            <aside></aside>
        </div>

    </div>
    
    
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   
  
    <script>

    let flg = $("#lock").data('flg');

     document.querySelector('#textarea').addEventListener('paste' , function(event) {
        event.preventDefault();
        let text =event.clipboardData.getData("text/plain");
        document.execCommand("insertHTML", false,  text);
    })

      // 엔터키를 누르면, #textarea에 입력한 내용이 db에 올라가고 채팅창에도 보이게
      $('#textarea').on('keydown', function(e) {
    	  let inputText = $(this).text();
    	  
         if (e.which == 13 && e.shiftKey == false) {
        	 
        	 let lastChatBlock = $(".chat-block").last();
        	 
        	 if(lastChatBlock.hasClass("chat-me")) { //마지막채팅이 나 일때
        		 alert("내가 마지막");
        		 lastChatBlock.find(".talk-me").append("<div class='chat-content'>"+ inputText +"</div>");
        	 	
        	 }else{ //마지막채팅이 다른사람일때
        		alert("다른사람이 마지막");
        	 	let newChat = $(".clone-me").clone();
        	 	newChat.removeClass("clone-me").addClass("chat-block").addClass("chat-me");
        	 	newChat.find(".talk-me").append("<div class='chat-content'>"+ inputText +"</div>")
        	 	$(".chat-section").append(newChat);
        	 }
           $('#textarea').empty();
          return false;
        }
         
         
         
      });

      //(+추가할 코드) DB에 boolean으로 flg 값 저장하면, 다음번에 그 상태가 유지되도록 했으면 좋겠다.
      //(+추가할 코드) DB에 flg 값이 잠금이라면, placeholder 값으로 알려주면 좋겠다.
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
    

    // 데이터가 들어왔을 때, 내가 보낸 메시지는 오른쪽에. 남이 보낸 메시지는 왼쪽에.


    // 채팅방 참여자의 출입에 따라 로그 찍히게끔



    // 잠금 기능도 있으면 좋을텐데..너무 투머치인듯
    
    </script>

<!--  파이어 베이스 -->
<script type="module"> 
  // Import the functions you need from the SDKs you need
  import { initializeApp } from "https://www.gstatic.com/firebasejs/9.5.0/firebase-app.js";
  import { getAnalytics } from "https://www.gstatic.com/firebasejs/9.5.0/firebase-analytics.js";
  // TODO: Add SDKs for Firebase products that you want to use
  // https://firebase.google.com/docs/web/setup#available-libraries

  // Your web app's Firebase configuration
  // For Firebase JS SDK v7.20.0 and later, measurementId is optional
  const firebaseConfig = {
    apiKey: "AIzaSyAcLrqOqnHMyRDK-5UM0wkisY531EOpyqc",
    authDomain: "proz-fdac4.firebaseapp.com",
    projectId: "proz-fdac4",
    storageBucket: "proz-fdac4.appspot.com",
    messagingSenderId: "354092959250",
    appId: "1:354092959250:web:f1496206092907aa9cfacb",
    measurementId: "G-609WH2VP1R"
  };

  // Initialize Firebase
  const app = initializeApp(firebaseConfig);
  const analytics = getAnalytics(app);
</script>



</body>
</html>