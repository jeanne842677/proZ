<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet"
	href="https://bootswatch.com/5/flatly/bootstrap.min.css">
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<link rel="stylesheet" href="/resources/css/board/post.css" />
<link type="text/css" rel="stylesheet"
	href="/resources/css/nav.css?ver=4">
<style type="text/css">
.commonDiv {
	padding: 5px 5px 5px 12px;
}

.editor-title {
	margin-top: 20px;
	padding-bottom: 20px;
	margin-bottom: 20px;
	font-size: 22px;
	border-bottom: 1px solid lightgray;
}

.noContent {
	display: flex;
	flex-direction: column;
	justify-content: space-around;
	align-items: center;
	width: 100%;
	height: 400px;
	font-size: 16px;
	color: gray;
}
/* 윤지 12월 5일  */
.nickname, .modify-btn, .remove-btn, .register-btn {
	margin-left: 10px;
}

.modify-btn, .register-btn, .remove-btn {
	color: gray;
	font-weight: bolder;
}

.all-btn {
	display: flex;
}

.nickname {
	display: flex;
	font-weight: bolder;
}

.reply-icon {
	display: flex;
	font-weight: bolder;
}

.test {
	margin-left: 10px;
	width: 100%;
}

.comment-text-wrapper {
	width: 100%;
}

.date {
	display:flex;
	margin-left: 30px;
	
}
</style>
</head>
<body>
	<div class="wrap">
		<header><%@ include
				file="/WEB-INF/views/include/nav/header.jsp"%></header>
		<div class="con">
			<nav><%@ include file="/WEB-INF/views/include/nav/main-nav.jsp"%></nav>
			<section>
				<!--여기서만 작업-->
				<div class="post-session-wrapper">
					<div class="post-main-title-wrapper">
						<!-- ++++ 수정 -->
						<div class="post-main-title">#${workspace.wsName }</div>
					</div>
					<div class="post-main-content-wrapper">
						<!-- ++++ 수정 -->
						<div class="post-navigation-bar"></div>
						<div class="editor-minHeight-div" style="min-height: 500px;">
						</div>
						<div class="footer-wrapper">
							<div class="footer-wrapper">
								<div class="br-line" id="br-line80"></div>
								<div class="footer-content-wrapper">
									<div class="post-footer-content-cover">
										<div class="post-content-cover-wrapper">
											<div class="post-message">커버</div>
											<button class="post-cover"
												style="background-color:${calendar.calColor}"
												disabled="disabled"></button>
										</div>
										<div class="post-btn-wrapper">
											<button type="button" id="post-change-btn"
												class="btn btn-primary">EDIT</button>
											<button type="submit" id="post-delete-btn"
												class="btn btn-primary">DELETE</button>
										</div>
									</div>
									<div class="post-content-date">
										<div class="editor-message">작성 날짜</div>
										<div class="date">
											<div class="start-date">2021년 03월 03일</div>
											&nbsp;~&nbsp;
											<div class="end-date">2021년 11월 13일</div>
										</div>
									</div>
									<div class="br-line"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<aside><%@ include
					file="/WEB-INF/views/include/nav/aside.jsp"%></aside>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.js"
		integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
		crossorigin="anonymous"></script>
	<script>
  
    // *** 페이지 기능 (분리 이전) 
    
    // posting에서 DOMPurify 테스트 
    var postContent = `${calendar.calContent}`;
    $('.editor-minHeight-div').html(postContent); 
    
    //Post 내용 없을 시 수정 
    let mainDiv = $('.editor-minHeight-div');
    if(mainDiv[0].lastElementChild.className === 'initPage'){ 
    	mainDiv.append("<div class='noContent'><div>등록된 포스트 내용이 없습니다.</div>"
    	 +"<img src='/resources/img/undraw_taken_re_yn20.svg' style='height: 200px;'></div>")
    	mainDiv.children('.initPage').remove();
    }
    
    
    //Post의 모든 div들의 contentEditable을 비활성화 
    let allEditorDiv = $('.editor-minHeight-div').children(); 
   	
   	$.each(allEditorDiv, function(i, val){
   		val.setAttribute('contentEditable', 'false'); 
   	})
   	
   	
    //Enter 키 false,  Enter + shift 줄바꿈 js 
    $('.comment-textArea').on('keydown', function(e) {
        if(e.keyCode === 13 && e.shiftKey == false) {
            return false; 
        }
    }); 
    

  
    // delete 기능 btn 
    $('#post-delete-btn').on('click', function(e){
    	
    	

    	fetch('/calendar/change/remove-cal', {
    		
    			method : "POST",
    			headers :  {"Content-type" : "application/json; charset=UTF-8"},
    			body : JSON.stringify({
    				
    				calIdx : ${calendar.calIdx} 

    				})
    	})
    	.then(response => response.text())
    	.then(text => {
			location.href = '/calendar/${projectIdx}?wsIdx=${calendar.wsIdx}';   
    	})
    	
    });

</script>
	<script type="text/javascript">

 
 
  

 
 
 
    $(function() {
        
        let changeDate =function(day) {
            return day.getFullYear()+"년 " + (day.getMonth()+1)+"월 " + day.getDate()+"일";
        }
        
        let start = new Date("${calendar.startDate}");
        let end = new Date("${calendar.endDate}");
        end.setDate(end.getDate()-1);
        
        $('.start-date').text(changeDate(start));
        $('.end-date').text(changeDate(end));
        
    })



    
    

</script>
</body>
</html>