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
<script type="text/javascript"src="/resources/js/file/download.js"></script>

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

.chat-other .user{
   display: flex;
   display: flex;
   justify-content: flex-start;
}

.chat-me .user {
   display: flex;
   justify-content: flex-end;
   margin-right: 10px;
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

.img-section {

	width: 250px;
	height: 200px;
	cursor : pointer;
	
}

/*  */

ul>li>svg{ 
	margin-right: 10px;
}

.find>svg, .setting>svg, .projectProfile>svg ,.mypage>svg, .cash>svg, .reload>svg{ 
	margin-right: 15px;
}

.online>#sortable>.member>svg, .offline>#sortable2>.member>svg{
	width:25px;
	height:25px;
}

.onlinetit, .offlinetit, .teamstit{
	font-size:16px;
}

.folder {
    color: #ffffff;
    list-style: none;
    font-size: 15px;
    margin-bottom: 5px;
    padding-left: 30px;
}

nav, aside{
font-family: 'NanumSquareRound';
} 

.alram{
display:flex;
justify-content: center;
align-items: center;
cursor: pointer;
}

.alram>svg{
    color:white;
    font-size: 30px;
}

.profile{
    display:flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
}

.profile>svg{
    color:white;
    font-size: 30px;
}

.projects{
	display: flex;
    justify-content: center;
    align-items: center;
    color: #Fff;
    font-weight: 800;
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
                              <c:choose>
                              	<c:when test="${chat.isFile==1}">
                              		<div class='chat-content'data-file-name='${chat.content}' data-file-path='${chat.filePath}' data-file-type='${chat.fileType}'><img class='img-section' ></div>
                              	</c:when>
                              	
                              	<c:otherwise>
                              		<div class="chat-content">${chat.content}</div>
                              	</c:otherwise>
                              </c:choose>
                              </div>
                           </div>
                           <div class="user-picture">
                              <i class="fas fa-user-circle fa-3x"
                                 style="color: #7B68EE;"></i>
                           </div>
                        </div>
                     </c:when>
                     <c:otherwise>
                        <div class="chat-block chat-other">
                        <div class="user-picture">
                           <i class="fas fa-user-circle fa-3x" style="color: #ccc"></i>
                           <!-- 이미지로 나중에 변경  -->
                        </div>
                        <div class="chat-information">
                           <div class="user">
                              <div class="user-nickname">${chat.nickname}</div>
                              <div class="currentTime">${chat.regDate}</div>
                           </div>
                           <div class="talk-other">
                            <c:choose>
                              	<c:when test="${chat.isFile==1}">
                              		<div class='chat-content'data-file-name='${chat.content}' data-file-path='${chat.filePath}' data-file-type='${chat.fileType}'><img class='img-section' ></div>
                              	</c:when>
                              	
                              	<c:otherwise>
                              		<div class="chat-content">${chat.content}</div>
                              	</c:otherwise>
                             </c:choose>
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
                      <div id="file"><i class="fas fa-image fa-2x" cursor="pointer"></i></div>
                      <input id="input-file" type="file" style="display: none;" accept="image/*">
                      <div id="send"><i class="far fa-paper-plane fa-2x" cursor="pointer"></i></div>
                    </div>
                  </div>
                  
                </article>
                
                  <div class="clone-other"> <!-- 복제할코드 -->
                    <div class ="user-picture">
                      <i class="fas fa-user-circle fa-3x" style="color:#ccc"></i>
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
                      <i class="fas fa-user-circle fa-3x" style="color:#7B68EE"></i>
                    </div>
                  </div>
                

            </section>
            <aside>
<div class="teams1">
	<div class="teamstit">프로젝트 참여 멤버</div>
	<div id="onoff">
		<div class="onlinetit">온라인</div>
		<div class="online">
			<ul id="sortable">
				<!-- <li class="member"><i class="fas fa-user-circle" style="color:#ffc107"></i>3조_길예진</li>
                                    <li class="member"><i class="fas fa-user-circle" style="color:#17a2b8"></i>3조_길예진</li>
                                    <li class="member"><i class="fas fa-user-circle" style="color:#dc3538"></i>3조_길예진</li>
 -->
			</ul>

		</div>

		<div class="offlinetit">오프라인</div>
		<div class="offline">
			<ul id="sortable2">
				<c:forEach items="${ projectMemberList }" var="pm">
					<li class="member" id="${pm.userIdx}" data-auth-idx="${pm.authIdx}"
						data-auth-name="${pm.authName}" data-git="${ pm.git }"
						style="color:${ pm.profileColor }"><div class="onoffprofile">
							<c:if test="${!empty pm.savePath}">
								<img class="profile-img"
									style="height: 100%; width: 100%; border-radius: 30px;"
									src="/file/${pm.savePath}${pm.renameFileName}">
							</c:if>
						</div> ${ pm.nickname }</li>
				</c:forEach>

			</ul>


		</div>
	</div>
</div>

<div class="member-info"
   style="width: 300px; height: 200px; z-index: 1000; position: absolute; background-color: white; border-radius: 5px; box-shadow: 0 3px 6px rgba(0, 0, 0, 0.5); display: none; flex-direction: column; align-items: center;">
   <div class="profile-info"
      style="width: 100%; height: 30%; background-color: red; border-radius: 5px 5px 0 0;"></div>
   <div class="profile-info-img"
      style="width: 60px; height: 60px; border-radius: 50px; background-color: blue; position: relative; top: -30px">
      <img class="inner-profile-info-img" style="width: 100%; height:100%; border-radius: 50px;">
      </div>
   <div class="profile-info-content"
      style="display: flex; flex-direction: column; align-items: center; position: relative; top: -20px">
      <div class="profile-info-name" style="font-size: 20px">임지영</div>
      <div class="profile-info-auth">권한</div>
      <input class="profile-info-git" value="없음" disabled="disabled">
   </div>

</div>

<div class="alert-popup"
	style="width: 400px; min-height: 50px; max-height: 300px; z-index: 4; position: absolute; display: none; background-color: white; border-radius: 5px; box-shadow: 0 3px 6px rgba(0, 0, 0, 0.5); right: 8px; top: 5px; flex-direction: column; align-items: center; overflow: auto;">
	<div class="alarm-text"
		style="width: 95%; margin: 5px; padding: 10px; height: 30px; display: flex; color: #292929;">
		🔔알림내역</div>
	<c:set var="length" value="0">
	</c:set>


	<c:forEach items="${alarmList }" var="al" varStatus="status">
		<c:if test="${ al.isLook == 0 }">
			<c:set var="length" value="${ length+1 }"></c:set>
		</c:if>
		<a class="alram-a" href="${ al.link }"
			style="text-decoration: none; width: 95%; height: 100px; background-color: #eee; border-radius: 5px; display: flex; margin: 5px; padding: 1px"><div
				class="alarm"
				<c:if test="${ al.isLook == 0 }"> style="color:black; font-weight:bold" </c:if><c:if test="${ al.isLook == 1 }"> style="color:#292929;" </c:if>
			">
				<div class="alarm-content" style="margin: 5px;">
					<b style="color: #666; font-size: 13px;">${al.alType}</b>
					<div class="al-content">${al.alContent }</div>
				</div>
			</div> </a>
	</c:forEach>

</div>

            
            </aside>
        </div>

    </div>
    
    
    
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"`integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>

<script src="https://www.gstatic.com/firebasejs/7.5.0/firebase.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.5.0/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.5.0/firebase-firestore.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.5.0/firebase-storage.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.5.0/firebase-auth.js"></script>


<script>


$('.alram').on('click' , function() {
	$('.alert-popup').css('display' , 'flex');
	
})


$('aside').css('position', 'relative');

$('.member').each(function() {
	
	$(this).on('click' , function() {
		
		let mem = $(this);
		var divX = mem.offset().left;
		var divY = mem.offset().top-60;
		
		
		$('.member-info').css('left' , '-310px')
		.css('top' , divY).css('display' , 'flex')
		
		$('.profile-info')
		.css('backgroundColor' , mem.css('color') );
		
		if(mem.find(".profile-img").attr('src')) {
			$('.inner-profile-info-img').attr('src', mem.find(".profile-img").attr('src'))
		}else{
			$('.inner-profile-info-img').attr('src', '')
		}

		
		
		$('.profile-info-name').text( mem.text() ).css('color' , mem.css('color'))
		$('.profile-info-auth').text( mem.data('auth-name'))
		
		let git = mem.data('git') 
		if(git) {
		$('.profile-info-git').val(mem.data('git'));
			
		}else {
			
			
		$('.profile-info-git').val('없음');
		}
		
	})
	
})


$('body').on('click', function(e){

	
let b = $(e.target);
bParents = b.parents('.member-info').attr("class");
var popUp = $('.member-info').attr("class");
var teams = $('.teams1');


if( b.attr("class") == popUp || bParents == popUp || $($(b).parents('.teams1'))[0] == teams[0]) {
	
}else {

   $('.member-info').css('display' , 'none');

	
}

if(b.attr("class")=='alert-popup' || $($(b).parents('.alram')).attr('class')=='alram') {
	
}else {
	
	if($('.alert-popup').css('display')=='flex' ) {

    	$('.new').text("0");
		$('.alarm').css('fontWeight' , '100');
		$('.alarm').css('color' , 'RGB(41, 41, 41)');
	}
	 
	
	
	$('.alert-popup').css('display' , 'none');
	

}



});






</script>
   

  
    <script>
    
    const firebaseConfig = {
    	    apiKey: "AIzaSyDdvbEhAml2uk5K8fm8DBGKU0z9y_ie0xE",
    	    authDomain: "proz-78541.firebaseapp.com",
    	    projectId: "proz-78541",
    	    storageBucket: "proz-78541.appspot.com",
    	    messagingSenderId: "363657337849",
    	    appId: "1:363657337849:web:974c2a6a5afa31845d3b28"
    	  };

    	  // Initialize Firebase
    	  firebase.initializeApp(firebaseConfig);
    

    
   
   	
   	$('.img-section').off().on('click', function () {
   		let fileName = $(this).parent().data('file-name');
   		let fileUrl = $(this).attr('src');
   		let fileType = $(this).parent().data('file-type');
   		
   		var x=new XMLHttpRequest();
   		x.open("GET","https://proz-cors.herokuapp.com/"+ fileUrl , true);
   		x.responseType = 'blob';
   		x.onload=function(e){download(x.response, fileName, fileType ); }
   		x.send();
   		
   		
   	});
   	

   	
   	$(document).ready(function () {//채팅 컨텐츠가 이미지 파일일때
   		
   		$(".chat-content").each(function (index) {
   			let imgSection = $(this).find(".img-section");
   			let path = $(this).data('file-path');
   			if(path) {
   				console.log("path 제발제발!!!" + path);
   				//이미지 띄우기부터..
   				var pathReference = firebase.storage().ref(path);
                pathReference.getDownloadURL().then(function(url) {
                	console.log(url);
                	imgSection.attr('src' , url);
                }).catch( function(error) {//나중에
                	alert("데이터 통신중 오류가 발생했습니다.");
                	console.log(error);
                	});
   			}
		})
   	})
    

    
    
    
    
    
    
    
    	  
    
    
    
    
    var stompClient = Stomp.over(new SockJS("/ws-stomp"));
    var chIdx = ${length};
    let wsIdx = "${param.wsIdx}";
   	let nickname = "${projectMember.nickname}";
   	let pmIdx = "${projectMember.pmIdx}";
   	let projectIdx = "${projectMember.projectIdx}";
   	let chatName = "${workspace.wsName}";
   	let date = "";
   	
   	
   	
       stompClient.connect({}, function (frame) {
          
           console.log("connected: " + frame);

		
			let msg = function() {

		           stompClient.subscribe('/room/msg/'+wsIdx,function(chat) {
		                
		                var content = JSON.parse(chat.body); //sendig한 객체를 받아옴.
		                
		                if(content.pmIdx == pmIdx) { //내가 입력할 때
		                   let newChat = $(".clone-me").clone();
		                   newChat.removeClass("clone-me").addClass("chat-block").addClass("chat-me");
		                   
		                   if(content.isFile == "1") {//보낸 메세지가 파일일 경우
		                      var pathReference = firebase.storage().ref(content.filePath);
		                      var urlStirng = "";
		                      pathReference.getDownloadURL().then(function(url) {
		                      console.log("지나가?");
		                          
		                          urlStirng = url;
		                          console.log("urlllllllllllllll" + url);
		                          
		                          newChat.find(".talk-me").append("<div class='chat-content' data-file-name='"+ content.content +"' data-file-type='"+ content.fileType +"'><img class='img-section' src='" + url + "'></div>");
		                          scrollFixedBottom();
		                          newChat.find('.img-section').off().on('click', function () {
		                           		var x=new XMLHttpRequest();
		                           		x.open("GET", "https://proz-cors.herokuapp.com/" + url , true);
		                           		x.responseType = 'blob';
		                           		x.onload=function(e){download(x.response, content.content, content.fileType ); }
		                           		
		                           		x.send();
		                           		
		                           	});
		                        }).catch(function(error) {//나중에
		                        	alert("데이터 통신중 오류가 발생했습니다.");
		                        	console.log(error);
		                        });

		                   }else {
		                	   newChat.find(".talk-me").append("<div class='chat-content'>" + content.content + "</div>");
		                   }
		                   newChat.find(".currentTime").html(content.regDate);
		                   newChat.find(".user-nickname").html(content.nickname);
		                   $(".chat-section").append(newChat);
		                   
		                }else{//다른사람이 입력할 때
		                   let newChat = $(".clone-other").clone();
		                   newChat.removeClass("clone-other").addClass("chat-block").addClass("chat-other");
		                   if(content.isFile == "1") {//보낸 매세지가 파일일때
		                	   var pathReference = firebase.storage().ref(content.filePath);
		                       var urlStirng = "";
		                       pathReference.getDownloadURL().then(function(url) {
		                       console.log("지나가?");
		                           
		                           urlStirng = url;
		                           console.log("urlllllllllllllll" + url);
		                           
		                           newChat.find(".talk-other").append("<div class='chat-content' data-file-name='"+ content.content +"' data-file-type='"+ content.fileType +"'><img class='img-section' src='" + url + "'></div>");
		                           scrollFixedBottom();
		                           newChat.find('.img-section').off().on('click', function () {
		                            		var x=new XMLHttpRequest();
		                            		x.open("GET", "https://proz-cors.herokuapp.com/" + url , true);
		                            		x.responseType = 'blob';
		                            		x.onload=function(e){download(x.response, content.content, content.fileType ); }
		                            		
		                            		x.send();
		                            		
		                            	});
		                         }).catch(function(error) {//나중에
		                         	alert("데이터 통신중 오류가 발생했습니다.");
		                         	console.log(error);
		                         });
		                   }else {
		                	   newChat.find(".talk-other").append("<div class='chat-content'>" + content.content + "</div>");
		                   }
		                   newChat.find(".currentTime").html(content.regDate);
		                   newChat.find(".user-nickname").html(content.nickname);
		                   $(".chat-section").append(newChat);
		                }
		                
		                chIdx = content.chIdx;
		                scrollFixedBottom();
		          })
				
				
				
			}
           
           console.log("빠져나와?");
           scrollFixedBottom();
           
        	
           
           let on = function() {
        	   
              	stompClient.subscribe('/online/project/${project.projectIdx}',
        				function(chat) {

        					var content = JSON.parse(chat.body);
        					console.dir(content);
        					
        					
        					if(content.status=="online") {
        						
        						content.members.forEach(function(e) {
        							var userIdx = e.userIdx;
        							let user = $('#' + userIdx);
        							$('.online ul').append(user);

        						})
         
        						
        					}else if(content.status=="offline") {
        						userIdx = content.member.userIdx;

        						let user = $('#' + userIdx);

        						$('.offline ul').append(user);
        					}
        			
        				 	

        		});
              	

				stompClient.send("/app/project/${project.projectIdx}", {}, JSON
						.stringify({
							userIdx : "${authentication.userIdx}",
							nickname : "${authentication.nickname}"
						}));
				
               
           }
           
          
           
         setTimeout(function() {
        	 
        	 msg();
        	 on();
        	 
        	 
         } , 50);
       	
         

       
       
       
       
       
       });
       
   
     
     
    //연결 해제

    function disconnect() {
        if(stompClient !== null) {
          
           stompClient.send("/app/remove/${project.projectIdx}", {}, JSON
					.stringify({
						userIdx : "${authentication.userIdx}",
						nickname : "${authentication.nickname}"
					}));
			
           
           stompClient.disconnect();
           
        }
        
        
     }
    
    window.addEventListener('beforeunload', (e) => {
		disconnect();
	})

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
       
        
    	date = nowDate();
        
        let inputText = $(this).html();
       
         
         if(e.keyCode==13 && e.shiftKey){
            $(this).append("</br>")
         }
         
         
         if (e.which == 13 && !e.shiftKey) {
            
            let lastChatBlock = $(".chat-block").last();
            chIdx++;
     
               stompClient.send("/app/msg/" + wsIdx , {}, JSON.stringify({
                        chIdx : chIdx,
                        wsIdx : wsIdx,
                        chName : chatName,
                        content : inputText,
                        regDate : date,
                        nickname : nickname,
                        pmIdx : pmIdx,
                        isFile : 0
                 }));
                 
               
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

      var fileInput = $('#input-file');
      
      $('#file').click( () => {
       if(flg){
    	   fileInput.click();
       }else{
        window.alert("잠금을 해제해주세요.");
       }
        
        
      });
      
    //multipart -> 경로찾고. stomp에 보내고. -> firebase에 저장하고?
  	
    
    let storage = firebase.app().storage("gs://proz-78541.appspot.com"); 
    		
  	$('#input-file').on('input', function(){
  		
  				
    	  		var file = document.querySelector('#input-file').files[0];
    	  		let fileReader = new FileReader();
    	  		fileReader.readAsDataURL(file);
    	  		
    	  		
    	  		fileReader.onload = (e) => {
    	  			chIdx++;
    	  			let dataUrl = fileReader.result;
    	  		  	
      	  		
	      	  		let filePath = wsIdx + '/' + chIdx + '/' + file.name;
	      	  		let storageRef = firebase.storage().ref(filePath);
	      	  		let fileName = file.name;
	      	  		date = nowDate();
	      	  		
	      	  		let fileType = file.type;
	      	  		
	      	  		
	      	  		let saveStorageFile = storageRef.child(fileName);
	      	  		let saveStorageFileRef = storageRef.child(wsIdx+'/'+ 'dd /' + fileName);
	      	  		let path = saveStorageFile.fullPath
	      	  		let name = saveStorageFile.name
	      	  		
	      	  		storageRef.put(file).then(function (snapshot) {
	  				console.log(snapshot);
	  				});
	      	  		
	                  stompClient.send("/app/msg/" + wsIdx , {}, JSON.stringify({
	                      chIdx : chIdx,
	                      wsIdx : wsIdx,
	                      chName : chatName,
	                      content : fileName,
	                      regDate : date,
	                      nickname : nickname,
	                      pmIdx : pmIdx,
	                      isFile : 1,
	                      filePath : filePath,
	                      fileType : fileType
	               }));  
    	  		};

    	  		
    	  		
    	  		if(file == null){
    	  			//나 모달 띄울래 나중에
    	  			alert("파일 선택을 취소하였습니다.");
    	  			return false;
    	  		}
    	  		
    	  		console.dir("------------file : " + file);
    	  		console.log(file);
    	  		
    	});
    	  				
    	  		

  
  	

      // 전송 버튼을 누르면, #textarea에 입력한 내용이 db에 올라가고 채팅창에도 보이게
      $('#send').on('click', function(){
        if(flg){
        	
        	date = nowDate();
            
            let inputText =  $('#textarea').html();
        	
        	let lastChatBlock = $(".chat-block").last();
            chIdx++;
     
               stompClient.send("/app/msg/" + wsIdx , {}, JSON.stringify({
                        chIdx : chIdx,
                        wsIdx : wsIdx,
                        chName : chatName,
                        content : inputText,
                        regDate : date,
                        nickname : nickname,
                        pmIdx : pmIdx,
                        isFile : 0
                 }));
                 
        	
          $('#textarea').empty();
          
          return false;
        }else{
          window.alert("잠금을 해제해주세요.");
        }
      });
    

     function nowDate() {
    	var today = new Date();
        var year = today.getFullYear();
        var month = ('0' + (today.getMonth() + 1)).slice(-2);
        var day = ('0' + today.getDate()).slice(-2);
        var hours = ('0' + today.getHours()).slice(-2); 
        var minutes = ('0' + today.getMinutes()).slice(-2);
        var dateString = year + '-' + month  + '-' + day + " " + hours + ":" + minutes;
        return dateString;
	}
      
    
    </script>
    <script type="text/javascript"src="/resources/js/file/download.js"></script>







</body>
</html>