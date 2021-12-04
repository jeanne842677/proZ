<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<meta charset="UTF-8">
	<link type="text/css" rel="stylesheet" href="https://bootswatch.com/5/flatly/bootstrap.min.css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<link rel="stylesheet" href="/resources/css/board/post.css"/>
	<link type="text/css" rel="stylesheet" href="/resources/css/nav.css?ver=4">
	<style type="text/css">
	.commonDiv{
        padding: 5px 5px 5px 12px; 
    }
    .editor-title{
            margin-top: 20px;
            padding-bottom: 20px;
            margin-bottom: 20px;
            font-size : 22px;
            border-bottom: 1px solid lightgray;
    }
    .noContent{
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        align-items: center;
        width: 100%;
        height: 400px;
        font-size: 16px;
        color: gray;
    }
	</style>
</head>
<body>
    <div class="wrap">
        <header><%@ include file="/WEB-INF/views/include/nav/header.jsp" %></header>
        <div class="con">
            <nav><%@ include file="/WEB-INF/views/include/nav/main-nav.jsp" %></nav>
            <section>
                <!--여기서만 작업-->
                <div class="post-session-wrapper">
                    <div class="post-main-title-wrapper">
                        <div class="post-main-title"># 메인 타이틀</div>
                    </div>
                    <div class="post-main-content-wrapper">
                        <div class="editor-minHeight-div" style="min-height: 500px;">
                        </div>
                        <div class="footer-wrapper">
                            <div class="footer-wrapper">
                                <div class="br-line" id="br-line80"></div>
                                <div class="footer-content-wrapper">
                                    <div class="post-footer-content-cover">
                                        <div class="post-content-cover-wrapper">
                                            <div class="post-message">커버</div>
                                            <button class="post-cover" style="background-color:${post.postColor}" disabled="disabled"></button>
                                        </div>
                                        <c:if test="${projectMember.pmIdx eq post.pmIdx}" var="pm">
	                                        <div class="post-btn-wrapper">
		                                        <button type="button" id="post-change-btn" class="btn btn-primary">EDIT</button>
		                                        <button type="submit" id="post-delete-btn" class="btn btn-primary">DELETE</button>
	                                        </div>
                                        </c:if>
                                    </div>     
                                    <div class="post-content-date">
                                        <div class="post-message">작성 날짜</div>
                                        <div>${post.regDate}</div>
                                    </div>
                                    <div class="br-line"></div>
                                </div>
                            </div>
                        </div>
                        <!-- 댓글 기능 DIV, 윤지 수정본 -->
                           
                            <div class="post-comment-textArea-div">
                                <i id="comment-smile" class="fas fa-smile"></i></label>
                                <form class="post-comment-textArea-form">
                                    <div id="comment-textArea" class="comment-textArea" contenteditable="true"></div>
                                    <button class="comment-textArea-submit-btn">등록</button>
                                </form>
                            </div>
                           <c:forEach  items="${replyList}" var="reply">
                            <label class="post-comment-textArea-label"
                               data-reply-idx="${reply.replyIdx}" data-reply-content="${reply.replyContent}"   >
                                <label for="comment-textArea"><i id="comment-smile" class="fas fa-smile"></i></label>
                                <div class="comment-text-wrapper">
                                    <pre class="test">${reply.replyContent}</pre>
                                    <div class="content-btn-wrapper">
                                        <div>${reply.regDate}</div><a class="coment-anchor-btn" href="#">답글달기</a>
                                        <c:if test="${projectMember.pmIdx eq reply.pmIdx}">
                                       <a class="modify-btn" >수정하기</a>
                                       <a class="remove-btn">삭제하기</a>
                                        </c:if>
                                    </div>
                                </div>
                            </label>
                            </c:forEach>
						<!-- 댓글기능 div 윤지 수정본 -->
						
                        <!-- <div class="post-comment-wrapper">
                            <div class="post-comment-title">
                                <div class="title-text">댓글</div><div class="title-number">48</div>
                            </div>
                            
                            
                            <div class="post-comment-textArea-div">
                                <i id="comment-smile" class="fas fa-smile"></i></label>
                                <form class="post-comment-textArea-form">
                                    <div id="comment-textArea" class="comment-textArea" contenteditable="true"></div>
                                    <button class="comment-textArea-submit-btn">등록</button>
                                </form>
                            </div>
                            
                            <label class="post-comment-textArea-label">
                                <label for="comment-textArea"><i id="comment-smile" class="fas fa-smile"></i></label>
                                <div class="comment-text-wrapper">
                                    <pre class="test">댓글 테스트</pre>
                                    <div class="content-btn-wrapper">
                                        <div>3시간 전</div><a class="coment-anchor-btn" href="#">답글달기</a>
                                    </div>
                                </div>
                            </label>
                            <label class="post-comment-textArea-label">
                                <label for="comment-textArea"><i id="comment-smile" class="fas fa-smile"></i></label>
                                <div class="comment-text-wrapper">
                                    <span class="testCopyValue">댓글 테스트 (클릭)</span>
                                    <div class="content-btn-wrapper">
                                        <div>3시간 전</div><a class="coment-anchor-btn" href="#">답글달기</a>
                                    </div>
                                </div>
                            </label>
                            <div class="post-comment-anchor-wrapper">
                                <label class="post-comment-anchor-label">
                                    <label for="comment-textArea"><i id="comment-smile" class="fas fa-smile"></i></label>
                                    <div class="comment-text-wrapper">
                                        <pre class="test">댓글 테스트</pre>
                                        <div class="content-btn-wrapper">
                                            <div>3시간 전</div><a class="coment-anchor-btn" href="#">답글달기</a>
                                        </div>
                                    </div>
                                </label>
                            </div>
                        </div> -->
                    </div>
                </div>
            </section>
            <aside><%@ include file="/WEB-INF/views/include/nav/aside.jsp" %></aside>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <script>
  
    // *** 페이지 기능 (분리 이전) 
    
    // posting에서 DOMPurify 테스트 
    var postContent = `${post.postContent}`;
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
    
    //text만 복사하는 js
    document.querySelector('.comment-textArea').addEventListener('paste' , function(event) {
        event.preventDefault();
        let text =event.clipboardData.getData("text/plain");
        document.execCommand("insertHTML", false,  text);
    });

    // delete 기능 btn 
    $('#post-delete-btn').on('click', function(e){
    	//loading.on(); 
    	fetch('/board/view/remove-post?' + 'postIdx=${post.postIdx}?wsIdx=${wsIdx}', {
    		method : 'POST'
    	})
    	.then(response => response.text())
    	.then(text => {
			location.href = '/board/${projectIdx}';   
    	})
    });
   
    // edit 기능 btn 
    $('#post-change-btn').on('click', function(e){
    	//loading.on(); 
    	let form = document.createElement('form'); 
    	form.action = '/board/view/change-post?bdidx=${post.bdIdx}'; 
    	form.method = 'POST'; 
    	form.innerHTML = '<input name="content" value=${postContent}>';
    	document.body.append(form);
    	form.submit();
    });
    
    //
</script>
<script type="text/javascript">
/* 댓글 입력 삭제 수정 javascript 윤지   */
//댓글 입력
$(".comment-textArea-submit-btn").off().on('click',function () {
    let replyIdx = "";
     let reply = $('.comment-textArea').html().trim();
     console.dir(reply);

 
    fetch("/board/post/add/reply" , {
         method : "POST" ,
         headers : {"Content-type" : "application/json; charset=UTF-8"} ,
         body : JSON.stringify({
            replyContent : reply,
            postIdx : "${param.postIdx}",
            pmIdx: "${projectMember.pmIdx}"
               })
    }).then(res=>{res.text()})
     .then(reply=>{
        
         location.reload();
     })
 })
 //댓글 삭제하기
$(".remove-btn").off().on('click',function () {
let replyIdx = $(this).closest(".post-comment-textArea-label").data("reply-idx");
console.dir(replyIdx);
    fetch("/board/post/delete/reply" , {
         method : "POST" ,
         headers : {"Content-type" : "application/json; charset=UTF-8"} ,
         body : JSON.stringify({
           replyIdx :replyIdx
           
               })
    }).then(res=>{res.text()})
     .then(reply=>{
        
        location.reload();
     })
 })
 
  //댓글 수정하기
$(".modify-btn").off().on('click',function () {
let replyIdx = $(this).closest(".post-comment-textArea-label").data("reply-idx");
let reply = $(this).closest(".post-comment-textArea-label").data("reply-content");
console.dir(reply);
console.dir(replyIdx);
    fetch("/board/post/update/reply" , {
         method : "POST" ,
         headers : {"Content-type" : "application/json; charset=UTF-8"} ,
         body : JSON.stringify({
         replyContent : reply,
         replyIdx :replyIdx,
         postIdx : "${param.postIdx}",
         pmIdx: "${projectMember.pmIdx}"
           
               })
    }).then(res=>{res.text()})
     .then(reply=>{
        
        location.reload();
     })
 })
</script>
</body>
</html>