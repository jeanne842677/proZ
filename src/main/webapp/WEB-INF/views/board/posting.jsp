<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>

<%@ include file="/WEB-INF/views/include/head.jsp"%>
    <title>임시</title>
    <style type="text/css">
    
        .submit{
        
            margin: 5px;
        }
        
        
        </style>
</head>

<body>

<div>제목:<input type="text" class="subject"></div>
<div>내용:<input type="text" class="content"></div>
<button class="submit">전송</button>
<script type="text/javascript">
	

	$('.submit').on('click' , function() {
			let subject = $('.subject').val();
			let content = $('.content').val();
		
		fetch("/board/add-post" , {
			
			
			method : "POST",
			headers :  {"Content-type" : "application/json; charset=UTF-8"},
			body : JSON.stringify({
				postTitle : subject,
				postContent : content,
				bdIdx : "${param.bdidx}"

				})
			
		}).then(res=>{res.text()})
		.then(text=>{

			location.href="/board/${wsIdx}";
			
		})
		
	})



</script>
</body>
</html>