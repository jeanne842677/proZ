<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>

<%@ include file="/WEB-INF/views/include/head.jsp"%>
    <title>Insert title here</title>
     <link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.css"> 
     <link type="text/css" rel="stylesheet" href="/resources/css/memo/memo.css">
        <script type="text/javascript" src="https://bootswatch.com/_vendor/jquery/dist/jquery.min.js"></script>
        <script type="text/javascript" src="https://bootswatch.com/_vendor/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://kit.fontawesome.com/485bb3ceac.js" crossorigin="anonymous"></script>




        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <link type="text/css" rel="stylesheet" href="https://bootswatch.com/5/flatly/bootstrap.min.css">
    <script src="https://kit.fontawesome.com/485bb3ceac.js" crossorigin="anonymous"></script>
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    <!-- include libraries(jQuery, bootstrap) -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <!-- include summernote css/js -->
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
    
        <style type="text/css">
            html,
            body {
    
                width: 100%;
                height: 100%;
                min-width: 1000px;
    
            }
        
            .wrap {
                height: 100%;
                display: flex;
                flex-direction: column;
    
            }
    
            header {
                height: 60px;
                background: rgb(143, 122, 229);
                background: radial-gradient(circle, rgba(143, 122, 229, 1) 0%, rgba(241, 112, 170, 1) 100%);
    
            }
    
            .con {
                flex:1;
                height: 100%;
                display: flex;
                justify-content: space-between;
                overflow: auto;
    
            }
    
            nav {
                background: rgb(19, 23, 34);
                background: linear-gradient(0deg, rgba(19, 23, 34, 1) 0%, rgba(26, 17, 47, 1) 22%, rgba(26, 30, 41, 1) 83%, rgba(19, 23, 34, 1) 100%);
    
    
                width: 220px;
                height: 100%;
                flex-shrink: 0;
    
            }
    
            section {
    
                height: 100%;
                width: 100%;
                overflow-y: auto;
    
                background: RGB(245, 246, 247);
    
    
    
            }
    
    
            aside {
    
                background: rgb(19, 23, 34);
                background: linear-gradient(0deg, rgba(19, 23, 34, 1) 0%, rgba(26, 17, 47, 1) 22%, rgba(26, 30, 41, 1) 83%, rgba(19, 23, 34, 1) 100%);
    
                height: 100%;
                width: 200px;
                flex-shrink: 0;
    
            }
    
            #login-btn {
                width: 200px;
                height: 100px;
            
    
            }

            #summernote{
                
            }
            
            .note-editable{
                background-color: #fff3cd;
                
            }
            
            .note-status-output{
                background-color: #fff3cd;
            }
            
            .note-resizebar{
                display:none;
            }
            
            .note-editor.note-airframe, .note-editor.note-frame {
                border:none;
            }
            .note-editor.note-airframe .note-statusbar, .note-editor.note-frame .note-statusbar{
                border:none;
                box-shadow:none;
            }

            .note-editor .note-toolbar{
                background-color: #fff3cd;
            }

            .panel-default>.panel-heading{
                border:none;
            }
            
            .btn-group>.btn-group:not(:first-child)>.btn, .btn-group>.btn:nth-child(n+3), .btn-group>:not(.btn-check)+.btn {
		       border-top-left-radius: 0;
		       border-bottom-left-radius: 0;
		       border: none;
		       background:transparent;
			}
            
            .btn-group-vertical>.btn, .btn-group>.btn {
			    position: relative;
			    float: left;
			    border: none;
			    background:transparent;
			}


            
        </style>
       
    
    </head>
    <body>
    
        <div class="wrap">
            <header>
            
    
    
    
    
            </header>
    
            <div class="con">
            <nav></nav>
            <section>
                <!--여기서만 작업-->
                <div id="title-control">
                <div id="title">
                   # <i class="fas fa-user-edit"></i> proZ 메모 
                </div>
            </div>
            <div style="display: flex;
            justify-content:center;">
            <hr width="90%" ></div>
            
    
            <div id="top">
                <div id="range">
                    <button id="new" class="newBtn">최신순</button>
                    <button id="old" class="oldBtn">오래된순</button>
                </div>

                <div id="search-form">
                    <img class="search-icon" src="/resources/img/search.png">
                    <input class="form-control" id="search" type="text" placeholder="검색하기" >  
                </div>
                <button type="button"  id="memo-btn" class="memo-btn">메모 작성</button>
            </div>
        <div id="memo">        
        
         <c:forEach items="${ memoList }" var="memo" > 
         <!-- 여기있는 애들 싹다 클래스로 바꾸고  -->
        <div id="memo-yellow" >
            <div id="content">
                <div class="textvalue"> ${ memo.content }</div>
            </div>
            <div id="profile"><i class="fas fa-user-circle fa-2x"></i></div>
        </div>
         </c:forEach>

               
         </div>
         <div id="modal">
            <div id = "write-memo">
                <!--메모지 모달 창 띄우면 보여지는 거-->
                <div class="memocloser" style="display:flex;justify-content:flex-end;">
                <div id="close" class="close"><i class="fas fa-times "></i></div></div>
                <div id="text"><div id="summernote"></div></div>
                
            
                <div id="editor">
                    <div id="back-color"><i class="fas fa-palette "></i></i></div>
                   
                    <div id="save"><i class="fas fa-save "></i></div>
                </div> 
            </div>
            <div id="memocolor" style="display:none;" >
                <div class="yellow" style="background-color: #fff3cd;"></div>
                <div class="pink" style="background-color: #f8d7da;"></div>
                <div class="green" style="background-color: #d4edda;"></div>
                <div class="blue" style="background-color: #d1ecf1;"></div>
                <div class="black" style="background-color: #dddddd;"></div>
            </div>
        </div>
    
        <div id="modal-yellow">
            <div id = "write-memo">
                <!--메모지 모달 창 띄우면 보여지는 거-->
                <div id="close" class="close"><i class="fas fa-times "></i></div>
                <div id="text"><textarea style="resize: none; border: none;"></textarea>
                </div>
            </div>
           
        </div>
    
            </section>
            <aside></aside>
            </div>
        </div>
        
        <script type="text/javascript">
        
      //div 추가
        $("#save").click(function () {
        
        	   	let markupStr = $('#summernote').summernote('code');
              	console.dir(markupStr);
              	let color = $("#write-memo").css('background-color');
              	
              	
              	fetch("/memo/add/memo" , {
              		method : "POST" ,
              		headers : {"Content-type" : "application/json; charset=UTF-8"} ,
              		body : JSON.stringify({
              			content : markupStr ,
              			wsIdx : "${wsIdx}" ,
              			bgColor : color
              		})
              	}).then(res=>res.text())
              	.then(text=>{
              		
              		console.dir(text);
              		
              	})
              	
              	
              	
              	
              	
              	
              	
              	
              	
                  $("#modal").hide();
                  let newMemo = $('<div id = "memo-yellow"><div id="content"><div class="textvalue"></div></div><div id="profile"><i class="fas fa-user-circle fa-2x"></i></div></div>');
                  $("#memo").prepend(newMemo);
                  newMemo.find('.textvalue').text($(".note-editable").text());
                  $("#memo-yellow").css("background-color", $("#write-memo").css("background-color"));
                  $(".note-editable").text("");
                  

               

               
              })

        
        
        
        
        
            $(document).ready(function (){
                $(".memo-btn").click(function(){
                    $("#modal").css('display','flex');
                });
                $(".close").click(function(){
                    $("#modal").hide();
                });
            })
            $(document).ready(function (){
                $("#memo-yellow").click(function(){
                    $("#modal-yellow").css('display','flex');
                });
                $(".close").click(function(){
                    $("#modal-yellow").hide();
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
            
            




            
        </script>
    </body>
    </html>