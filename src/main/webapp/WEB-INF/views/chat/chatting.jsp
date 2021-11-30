<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<title>Insert title here</title>
<style type="text/css">
.chat {

	width:800px;
	border: solid black;
	min-height: 200px;

}

</style>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.6.0.js"`integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>

채팅방 번호: <input type="text" id="room" value="${param.wsIdx}"><button class="connect">연결</button><br>
보낼 내용: <input type="text" id="content">
<button id="send">메세지 보내기</button><br>
<div class="chat">
<c:forEach var="chat" items="${chatList}">
	<div>${chat.nickname} : ${chat.content} /// ${chat.regDate}</div>


</c:forEach>

</div>

<script>


var stompClient = Stomp.over(new SockJS("/ws-stomp"));
var chIdx = ${length};


	//연결
	stompClient.connect({}, function (frame) {
		
	    console.log("connected: " + frame);

		 let room = $('#room').val();
		 stompClient.subscribe('/room/msg/'+ room,function(chat) {
				
				var content = JSON.parse(chat.body);	
				$('.chat').append('<div>' + content.content +'</div>')
		});
		 
	});
	
	


 $('#send').on('click' , function() { // 빈 객체는 헤더.
	chIdx += 1;
	var today = new Date();
  	var year = today.getFullYear();
  	var month = ('0' + (today.getMonth() + 1)).slice(-2);
 	var day = ('0' + today.getDate()).slice(-2);
  	var hours = ('0' + today.getHours()).slice(-2); 
  	var minutes = ('0' + today.getMinutes()).slice(-2);
	var dateString = year + '-' + month  + '-' + day + " " + hours + ":" + minutes;


	 
	 let room = $('#room').val();
	 let content = $('#content').val();

	 stompClient.send("/app/msg/" + room , {}, JSON.stringify({
		 		chIdx : chIdx,
		 		wsIdx : room,
		 		chName : room,
		 		content : content,
		 		regDate : dateString,
		 		nickname : "성공하자"
		 		
		}));
	 
	 
 })
 
 
//연결 해제

function disconnect() {
	 if(stompClient !== null) {
		 stompClient.send("/app/out") , {} , usersessionid.value +"is out chatroom";
		 stompClient.disconnect();
		 
	 }
	 
	 
 }
 


</script>

</body>
</html>