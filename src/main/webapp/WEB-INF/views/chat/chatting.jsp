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

채팅방 번호: <input type="text" id="room" value="${param.room}"><button class="connect">연결</button><br>
보낼 내용: <input type="text" id="content">
<button id="send">메세지 보내기</button><br>
<div class="chat"></div>

<script>


var stompClient = Stomp.over(new SockJS("/endpoint"));



	//연결
	stompClient.connect({}, function (frame) {
		
	    console.log("connected: " + frame);

		 let room = $('#room').val();
		 stompClient.subscribe('/subscribe/message/'+ room,function(chat) {
				
				var content = JSON.parse(chat.body);	
				$('.chat').append('<div>' + content.content +'</div>')
		});

	});
	
	


/* stompClient.send("/chat/room", {}, JSON.stringify({
    content: "안녕",
    name : "지영"
}));

 */
 $('#send').on('click' , function() {
	 
	 let room = $('#room').val();
	 let content = $('#content').val();

	 stompClient.send("/app/message/" + room , {}, JSON.stringify({
		    content: content,
		    name : "지영"
		}));
	 
	 
 })
 


</script>

</body>
</html>