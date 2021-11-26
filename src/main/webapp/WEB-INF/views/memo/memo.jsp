<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head.jsp"%>
<title>Insert title here</title>
<link type="text/css" rel="stylesheet"
	href="/resources/css/bootstrap.css">
<link type="text/css" rel="stylesheet"
	href="/resources/css/memo/memo.css">
<script type="text/javascript"
	src="https://bootswatch.com/_vendor/jquery/dist/jquery.min.js"></script>
<script type="text/javascript"
	src="https://bootswatch.com/_vendor/bootstrap/dist/js/bootstrap.bundle.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/485bb3ceac.js"
	crossorigin="anonymous"></script>
<link type="text/css" rel="stylesheet" href="/resources/css/nav.css?ver=4">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<link type="text/css" rel="stylesheet"
	href="https://bootswatch.com/5/flatly/bootstrap.min.css">
<script src="https://kit.fontawesome.com/485bb3ceac.js"
	crossorigin="anonymous"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<!-- include libraries(jQuery, bootstrap) -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<style type="text/css">
html, body {
	width: 100%;
	height: 100%;
	min-width: 1450px;
}

a {
	color: #A4A4A4;
	text-decoration: none;
}

a:hover {
	color: #8F7AE5;
	text-decoration: none;
}

a:active {
	color: #8F7AE5;
	text-decoration: none;
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
}

.con {
	flex: 1;
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

section {
	height: 100%;
	width: 100%;
	overflow-y: auto;
	background: RGB(245, 246, 247);
	display: flex;
	align-items: center;
	flex-direction: column;
}

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

#summernote {
	
}

.note-editable {
	background-color: #fff3cd;
	width: 300px;
}

.note-status-output {
	background-color: #fff3cd;
}

.note-resizebar {
	display: none;
}

.note-editor.note-airframe, .note-editor.note-frame {
	border: none;
}

.note-editor.note-airframe .note-statusbar, .note-editor.note-frame .note-statusbar
	{
	border: none;
	box-shadow: none;
}

.note-editor .note-toolbar {
	background-color: #fff3cd;
}

.panel-default>.panel-heading {
	border: none;
}

.btn-group>.btn-group:not(:first-child)>.btn, .btn-group>.btn:nth-child(n+3),
	.btn-group>:not(.btn-check)+.btn {
	border-top-left-radius: 0;
	border-bottom-left-radius: 0;
	border: none;
	background: transparent;
}

.btn-group-vertical>.btn, .btn-group>.btn {
	position: relative;
	float: left;
	border: none;
	background: transparent;
}

.user-aut-editor {
	width: 100%;
	display: flex;
	justify-content: space-around;
	padding: 10px;
}

#trash, #modify {
	margin: 5px 10px 5px 5px;
	cursor: pointer;
}

hr {
	margin: 0px 10px 25px 10px;
}

.on {
	color: #8F7AE5;
}

.modify-text {
	height: 100%;
}

.writer-info{
	display: flex;
    font-size: 12px;
    width: 100%;
    flex-direction: column;
    justify-content: center;
    margin-left: 30px;
    padding-top: 34px;
    color: #777;
}

.writer-info > hr {
	width: 100%;
    margin: 5px 0 5px 0;
}

.user-aut-editor-wrapper{
	width: 100%;
    height: 18%;
}


</style>


</head>
<body>

	<div class="wrap">
		<header> 


			<%@ include file="/WEB-INF/views/include/nav/header.jsp" %>
		</header>

		<div class="con">
			<nav>
			<%@ include file="/WEB-INF/views/include/nav/main-nav.jsp" %>
	</nav>
			<section>
				<!--여기서만 작업-->
				<div id="title-control">
					<div id="title">
						# <i class="fas fa-user-edit"></i> proZ 메모
					</div>
				</div>

				<div
					style="width: 90%; margin: 40px 0px 0px 0px; display: flex; justify-content: center;">
					<hr width="90%">
				</div>


				<div id="top">
					<div id="range">
						<button id="new" class="newBtn">
							<c:if test="${order == 0}">
								<a
									href="http://localhost:9090/memo/${projectIdx}?wsIdx=${wsIdx}&order=0"
									style="color: #8F7AE5">최신순</a>
						</button>
						<button id="old" class="oldBtn">
							<a
								href="http://localhost:9090/memo/${projectIdx}?wsIdx=${wsIdx}&order=1"
								style="color: #A4A4A4">오래된순</a>
						</button>
						</c:if>
						<c:if test="${order == 1}">
							<a
								href="http://localhost:9090/memo/${projectIdx}?wsIdx=${wsIdx}&order=0"
								style="color: #A4A4A4">최신순</a>
							</button>
							<button id="old" class="oldBtn">
								<a 
								href="http://localhost:9090/memo/${projectIdx}?wsIdx=${wsIdx}&order=1"
									style="color: #8F7AE5">오래된순</a>
							</button>
						</c:if>
					</div>


					<div id="rightzone">
						<div id="search-form">
							<img class="search-icon" src="/resources/img/search.png">
							<input class="form-control" id="search" type="text" placeholder="검색하기" value="${search}" name="searchKeyword"> 
						</div>

						<button type="button" id="memo-btn" class="memo-btn">메모
							작성</button>
					</div>
				</div>
				<div class="memocon">
					<div id="memo">

						<c:forEach items="${memoList}" var="memo">
							<!-- 여기있는 애들 싹다 클래스로 바꾸고  -->
							<div class="memo-yellow"
								style="background-color : ${memo.bgColor}"
								data-bg-color="${memo.bgColor}" data-pm-idx="${memo.pmIdx}"
								data-memo-idx="${memo.memoIdx}"
								data-memo-writer="${memo.nickname}"
								data-memo-reg-date="${memo.regDate}"
								
								>
								<div id="content">
									<div class="textvalue">${ memo.content }</div>
								</div>
								<div id="profile">
									<i class="fas fa-user-circle fa-2x"></i>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div id="modal">
					<div id="write-memo">
						<!--메모지 모달 창 띄우면 보여지는 거-->
						<div class="memocloser"
							style="display: flex; justify-content: flex-end;">
							<div id="close" class="top-wrapper">
								<i class="fas fa-times close"></i>
							</div>
						</div>
						<div id="text">
							<div id="summernote"></div>
						</div>


						<div id="editor">
							<div id="back-color">
								<i class="fas fa-palette "></i>
							</div>

							<div class="save">
								<i class="fas fa-save "></i>
							</div>
							<div class="modify-save">
								<i class="fas fa-save "></i>
							</div>
						</div>
					</div>
					<div id="memocolor" style="display: none;">
						<div class="yellow" style="background-color: #fff3cd;"></div>
						<div class="pink" style="background-color: #f8d7da;"></div>
						<div class="green" style="background-color: #d4edda;"></div>
						<div class="blue" style="background-color: #d1ecf1;"></div>
						<div class="black" style="background-color: #dddddd;"></div>
					</div>
				</div>

				<div class="modal-yellow">
					<div id="write-memo">
						<!--메모지 모달 창 띄우면 보여지는 거-->
						<div id="close" class="top-wrapper">
							<div class="writer-info">
								<div class="writer">작성자 : </div>
								<div class="reg-date">최근수정일 : </div>
								<hr>
							</div>
						
							<i class="fas fa-times close"></i>
						</div>
						<div id="text" class="modify-text"></div>
						<div class="user-aut-editor-wrapper">
							<div class="user-aut-editor">
								<div id="trash">
									<i class="fas fa-trash-alt"></i>
								</div>
								<div id="modify">
									<i class="fas fa-edit"></i>
								</div>
							</div>
						</div>

					</div>

				</div>

			</section>
			<aside>
				<%@ include file="/WEB-INF/views/include/nav/aside.jsp" %>
			
			</aside>
		</div>
	</div>

	<script type="text/javascript">
	
	function timeNow() {
		var today = new Date();
	  	var year = today.getFullYear();
	  	var month = ('0' + (today.getMonth() + 1)).slice(-2);
	 	var day = ('0' + today.getDate()).slice(-2);
	  	var hours = ('0' + today.getHours()).slice(-2); 
	  	var minutes = ('0' + today.getMinutes()).slice(-2);
		var dateString = year + '-' + month  + '-' + day + " " + hours + ":" + minutes;
		
		return dateString;
	}
	

	
	$(document).keydown(function(event) {//esc키로 상세메모 종료
	       if ( event.keyCode == 27 || event.which == 27 ) {
	           $(".modal-yellow").hide();
	       }
	   });
	
	//검색
    $("input[name='searchKeyword']").keydown(function(e){
       if(e.keyCode == 13){
          var valueBySearch = $('#search').val();
          location.replace("http://localhost:9090/memo/${projectIdx}/"+valueBySearch+"?wsIdx=${wsIdx}&order=0")
       }
    })


      //새 메모 추가
        $(".save").click(function () {
			  
              let memoIdx = "";
              let markupStr = $('#summernote').summernote('code');
              console.dir(markupStr);
              let color = $("#write-memo").css('background-color');
              
              $("#modal").hide();
              fetch("/memo/add/memo" , {
                 method : "POST" ,
                 headers : {"Content-type" : "application/json; charset=UTF-8"} ,
                 body : JSON.stringify({
                    content : markupStr ,
                    wsIdx : "${wsIdx}" ,
                    bgColor : color
                 })
              }).then(res=>res.json())
              .then(memo=>{
                 console.dir(memo);
                 memoIdx = memo.memoIdx;
                    
                    
               let newMemo = $('<div class="memo-yellow" ><div id="content"><div class="textvalue"></div></div><div id="profile"><i class="fas fa-user-circle fa-2x"></i></div></div>');
               $("#memo").prepend(newMemo);
               /* newMemo.find('.textvalue').text($(".note-editable").text()); */
               newMemo.find('.textvalue').html(markupStr);
               newMemo.css("background-color", color);
               $(".note-editable").text("");
               changeColor("#fff3cd");
                     
                    
               newMemo.off().on('click',function(){
            	   
                   $(".modal-yellow").css('display','flex');
				   $(".modal-yellow").find("#write-memo").css('background-color', newMemo.css('background-color'));
                   $(".modal-yellow").find("#text").html($(this).find(".textvalue").html());
                   
                   if(newMemo.data("memo-reg-date")){
                	   $(".modal-yellow").find(".reg-date").html("최근 수정일 : " + newMemo.data("memo-reg-date"));
                   }else {
                	   $(".modal-yellow").find(".reg-date").html("최근 수정일 : " + timeNow());
                   }
                   $(".modal-yellow").find(".writer").html("작성자 : " + "${authentication.nickname}");
                   newMemo.data("memo-idx", memoIdx);
                  
                   $(".user-aut-editor").show();
                   
                   let thisMemoIdx = $(this).data("memo-idx");
                   
                   $("#trash").off().on('click',function () {//메모 삭제
                      deleteMemo(thisMemoIdx,newMemo);
               	 });
                   
                   $("#modify").off().on('click',function () {//수정 처리
                      let thisModify = $(this);
                      modifyMemo(thisModify,thisMemoIdx,newMemo);
    	   
		 });
                   
               });
               $(".close").click(function(){
                   $(".modal-yellow").hide();
               });
               
               
            });
                 
                  
                  
 
              });

        
        
        
        
        
            $(document).ready(function (){
                $(".memo-btn").click(function(){
                	$(".save").show();
                	$(".modify-save").hide();
                    $("#modal").css('display','flex');
                    changeColor("#fff3cd");
                    
                });
                $(".close").click(function(){
                    $("#modal").hide();
                });
            })
            
            $(document).ready(function (){
            	
                $(".memo-yellow").on('click', function(event) {//메모 상세보기
                   
                   let memo = $(this);
                   $(".user-aut-editor").hide();
                    $(".modal-yellow").css('display','flex');
                    $(".modal-yellow").find("#write-memo").css('background-color',$(this).data('bg-color'));
                    $(".modal-yellow").find("#text").html(memo.find(".textvalue").html());
                    $(".modal-yellow").find(".reg-date").html("최근 수정일 : " + memo.data('memo-reg-date'));
                    $(".modal-yellow").find(".writer").html("작성자 : " + memo.data('memo-writer'));
                    
                    if($(this).data("pm-idx") == ${userPmIdx}) {//본인이 만든 메모일시 수정/삭제 띄우기
                       $(".user-aut-editor").show();
                       
                       let thisMemoIdx = $(this).data("memo-idx");
                       
                       $("#trash").off().on('click',function () {//삭제 처리시
                          deleteMemo(thisMemoIdx,memo);
                  	});
                       
				       $("#modify").off().on('click',function () {//수정 처리
				    	   let thisModify = $(this);
                           modifyMemo(thisModify,thisMemoIdx,memo);
				      });
                    }
                    
                    $(".close").click(function(){//닫기
                    	$(".note-editable").text("");
                       	$(".user-aut-editor").hide();
                      	$(".modal-yellow").hide();
                      	event.stopImmediatePropagation();
                    });
                    
                    
                });

            })
        
            
            /* 색 변경 메뉴*/
            $(document).ready(function (){
                $("#back-color").click(function(){
                    $("#memocolor").css('display','flex');
                });
            })
            
            let changeColor = function(color){
                $("#write-memo").css('background-color',color);
                $(".note-editable").css('background-color',color);
                $(".note-status-output").css('background-color',color);
                $(".note-editor").css('background-color',color);
                $(".note-toolbar").css('background-color',color);
                $("#memocolor").hide();
            }

            // 메모지 색상 변경
            $(document).ready(function (){
                $(".yellow").click(function(){
                    changeColor("#fff3cd");
                });

                $(".pink").click(function(){
                    changeColor("#f8d7da");
                });

                $(".green").click(function(){
                    changeColor("#d4edda");
                });

                $(".blue").click(function(){
                    changeColor("#d1ecf1");
                });

                $(".black").click(function(){
                    changeColor("#dddddd");
                });
            })



        // summernote
        
        $("#summernote").on("summernote.enter", function(we, e) {
	     $(this).summernote("pasteHTML", "<br><br>");
	     e.preventDefault();
	});

        $('#summernote').summernote({
            // airMode: true,
            placeholder : '내용을 입력하세여',
        height: 220,                 // set editor height
        width:"auto",
        focus: true,                  // set focus to editable area after initializing summernote
        disableDragAndDrop: true,

        toolbar: [
            // [groupName, [list of button]]
            ['style', ['bold', 'italic', 'underline', 'clear']],
            ['font', ['strikethrough']],
            ['fontsize'],
            ['color', ['color']]
        ]
    });
            
           
            
            
            //
            $(".newBtn").click(function(){
                $(".newBtn").css('color','#8F7AE5');
                $(".oldBtn").css('color','#A4A4A4');
            });
            
            $(".oldBtn").click(function(){
                $(".oldBtn").css('color','#8F7AE5');
                $(".newBtn").css('color','#A4A4A4');
                
            });
            
            
            
         function deleteMemo(thisMemoIdx,newMemo) {//삭제 처리함수
               
               fetch("/memo/delete/memo" , {
                     method : "POST" ,
                     headers : {"Content-type" : "application/json; charset=UTF-8"} ,
                     body : JSON.stringify({
                        "memoIdx" : thisMemoIdx
                     })
                  });
                  
               $(".modal-yellow").hide();
               newMemo.remove();
             }
         
         function modifyMemo(thisModify,thisMemoIdx,newMemo) {//수정 처리함수
        	 
        	   $(".modify-save").show();
	    	   $(".save").hide();
	    	   $(".modal-yellow").hide();
	    	   $("#modal").css("display", "flex");
	    	   
	    	   let content = thisModify.closest(".user-aut-editor-wrapper").siblings(".modify-text")
	           let beforeModify = content.html();
	           let thisColor = thisModify.closest("#write-memo").css("background-color");
	           $('#summernote').summernote('code', beforeModify);
	           changeColor(thisColor);
	            $(".user-aut-editor").hide();
	            
	            
	            $(".modify-save").off().on('click',function () {//수정 fetch
	            	let afterModify = $('#summernote').summernote('code');
	            	let afterColor = $("#modal").find("#write-memo").css("background-color");
	            	console.log(afterColor);
	            	 fetch("/memo/modify/memo" , {
	                     method : "POST" ,
	                     headers : {"Content-type" : "application/json; charset=UTF-8"} ,
	                     body : JSON.stringify({
	                        memoIdx : thisMemoIdx,
	                        bgColor : afterColor,
	                        content : afterModify
	                     })
	                  });
	            	
	            	newMemo.css("background-color",afterColor);
	            	newMemo.data("bg-color",afterColor);
	            	newMemo.find(".textvalue").html(afterModify);
	            	newMemo.data("memo-reg-date",timeNow());
			        $("#modal").hide();
			            	 
						});
			
		}





            
        </script>
</body>
</html>