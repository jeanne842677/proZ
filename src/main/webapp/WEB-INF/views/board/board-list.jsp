<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    
<%@ include file="/WEB-INF/views/include/head.jsp"%>
    <title>Document</title>
    
<link type="text/css" rel="stylesheet" href="/resources/css/nav.css?ver=4">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <link type="text/css" rel="stylesheet" href="https://bootswatch.com/5/flatly/bootstrap.min.css">
    <script src="https://bootswatch.com/_vendor/bootstrap/dist/js/bootstrap.bundle.min.js"></script>   <!-- 부트스트랩 3.x를 사용한다. -->

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
            align-items: stretch;
            flex: 1 auto;


        }

        header {
            height: 60px;
            background: rgb(143, 122, 229);
            background: radial-gradient(circle, rgba(143, 122, 229, 1) 0%, rgba(241, 112, 170, 1) 100%);
            min-height: 60px;

        }

        .con {

            height: 100%;
            display: flex;
            justify-content: space-between;
            overflow-x: auto;

        }

        nav {
            background: rgb(19, 23, 34);
            background: linear-gradient(0deg, rgba(19, 23, 34, 1) 0%, rgba(26, 17, 47, 1) 22%, rgba(26, 30, 41, 1) 83%, rgba(19, 23, 34, 1) 100%);


            width: 220px;
            height: 100%;
            flex-shrink: 0;
            overflow: auto;



        }

        section {

            flex: 1;
            width: 100%;
            background: RGB(245, 246, 247);
            display: flex;
            justify-content: center;
            overflow-y: auto;
            min-width: 1300px;
            overflow-x: hidden;




        }


        aside {

            background: rgb(19, 23, 34);
            background: linear-gradient(0deg, rgba(19, 23, 34, 1) 0%, rgba(26, 17, 47, 1) 22%, rgba(26, 30, 41, 1) 83%, rgba(19, 23, 34, 1) 100%);

            height: 100%;
            width: 200px;
            flex-shrink: 0;

            overflow: auto;


        }


        /*섹션 영역////////////////////////////////////////////////////////////*/

        .section-wrap {
            margin: 0 50px;
            border: solid red(128, 123, 123);
            height: auto;
            width: 90%;
            display: flex;
            flex-direction: column;
            align-items: center;

        }

        .cat-subject {
            font-size: 25px;
            margin: 20px 0px;
            font-weight: 900;

            width: 1200px;

        }

        .content-wrap {

            width: auto;
        }

        .button-wrap {

            margin: 10px;
            display: flex;
            justify-content: space-between;


        }


        .board-wrap {

            margin: 10px;
            display: flex;
            justify-content: flex-start;
            width: 1200px;

        }


        /*패딩 10*/
        .small-board-wrap {

            padding: 10px;
            width: 25%;

        }

        .medium-board-wrap {

            padding: 10px;
            width: 50%;

        }


        .large-board-wrap {

            padding: 10px;
            width: 100%;

        }



        .small-board {

            width: 100%;
            height: auto;
            background-color: RGB(227, 228, 231);
            border-radius: 5px;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding-bottom: 10px;

            min-height: 50px;




        }

        #board-add-btn {

            margin: 0 10px;

        }

        .board-subject-wrap {
            display: flex;
            width: 100%;
            justify-content: space-between;
            padding: 15px 15px 3px 15px;


        }


        .board-subject {

            font-size: 18px;
            font-weight: bold;
            background-color: transparent;
            border-color: transparent;

        }


        .board-change-btn {
            width: 28px;
            font-weight: bold;
            background-color: transparent;
            font-size: 18px;
            padding: 0;
            border: none;

        }

        a {
            text-decoration-line: none;
            color: black;
            padding: 0 7px;
        }

        a:hover {
            color: black;
        }

        .board-change-btn:hover {
            background-color: RGB(187, 188, 191);
            border-radius: 5px;
            ;

        }

        .card-wrap {

            display: flex;
            flex-direction: column;
            width: 100%;
            min-height: 10px;

        }

        .card,
        .card-shadow {

            width: auto;
            background: RGB(245, 246, 247);
            margin: 10px 10px 0 10px;
            border-radius: 5px;
            box-shadow: 0px 1px 0px 0px RGB(192, 198, 208);
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            position: relative;
            padding-bottom: 40px;
            height: auto;


        }



        .card-subject {

            margin: 8px 12px;


        }

        .post-btn,
        .save-btn {

            margin: 10px 15px 0px 15px;
            align-self: flex-end;
            font-size: 15px;

        }




        .profile-img {

            width: 25px;
            height: 25px;
            background-color: blueviolet;
            border-radius: 25px;
            position: absolute;
            bottom: 10px;
            right: 5px;



        }

        .profile-img2 {

            width: 25px;
            height: 25px;
            background-color: pink;
            border-radius: 25px;
            position: absolute;
            bottom: 10px;
            left: 5px;




        }

        .profile-img3 {

            width: 25px;
            height: 25px;
            background-color: RGB(85, 86, 87);
            border-radius: 25px;
            position: absolute;
            bottom: 10px;
            left: 20px;



        }

        .profile-img4 {

            width: 25px;
            height: 25px;
            background-color: lightblue;
            border-radius: 25px;
            position: absolute;
            bottom: 10px;
            left: 35px;



        }

        .dropdown-item:hover {
            cursor: pointer;
        }




        .card-shadow {
            background-color: RGB(185, 186, 187);

        }
    </style>

</head>

<body>

    <div class="wrap">
        <header>




			<%@ include file="/WEB-INF/views/include/nav/header.jsp"%>



        </header>

        <div class="con">
            <nav>

				<%@ include file="/WEB-INF/views/include/nav/main-nav.jsp"%>
            

            </nav>
            <section>
                <div class="section-wrap">


                    <div class="cat-subject">
                        #${workspace.wsName}
                        <hr>
                    </div>
                    <div class="content-wrap">
                        <div class="button-wrap">

                            <div class="btn-group" id="board-add-btn" role="group"
                                aria-label="Button group with nested dropdown">
                                <div class="btn-group" role="group">
                                    <button id="btnGroupDrop1" type="button" class="btn btn-primary dropdown-toggle"
                                        data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">게시판
                                        추가</button>
                                    <div class="dropdown-menu" aria-labelledby="btnGroupDrop1" style="">
                                        <div class="dropdown-item" id="add-small-board">작은 애</div>
                                        <div class="dropdown-item" id="add-medium-board">중간 애</div>
                                        <div class="dropdown-item" id="add-large-board">큰 애</div>
                                    </div>
                                </div>
                            </div>

                            <div class="search-wrap">
                                <input type="text" class="form-control" placeholder="검색하기" id="post-seacrh">

                            </div>

                        </div>


                        <div class="board-wrap">

						<c:forEach items="${boardList}" var="board">
							<div class=
							<c:if test="${board.bdSize==1}">
							"<c:out value="small-board-wrap"/> 
							</c:if>
							<c:if test="${board.bdSize==2}">
							"<c:out value="medium-board-wrap"/> 
							</c:if>
							<c:if test="${board.bdSize==4}">
							"<c:out value="large-board-wrap"/> 
							</c:if> board"  
							data-size="${board.bdSize }" data-sort="${board.sort }" id="${board.bdIdx}">
                           
                                <div class="small-board">
                                    <div class="board-subject-wrap">
                                        <input class="board-subject" type="text" value="${board.bdName }" placeholder="제목을 입력하세요" readonly="readonly">
										<button class="nav-item dropdown board-change-btn">
                                            <a data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                                                aria-expanded="false">⋮</a>
                                            <div class="dropdown-menu" style="">
                                                <div class="dropdown-item" href="#">지영</div>
                                                <div class="dropdown-divider"></div>
                                                <div class="dropdown-item" >게시판 추가</div>
                                                <div class="dropdown-item remove-board">게시판 삭제</div>
                                            </div>
                                        </button>
                                    </div>

                                    <div class="card-wrap">
	                                    <c:forEach items="${postList}" var="post">
	                                    	<c:if test="${board.bdIdx == post.bdIdx }">
	                                        <div class="card" id="${post.postIdx }" data-sort="${post.sort}">
	                                            <div class="card-subject">${post.postTitle}</div>
	                                            <div class="profile-img"></div>
	                                        </div>
	                                        </c:if>
	                                    </c:forEach>
                                    </div>
                                    <button class="post-btn btn btn-primary">
                                        추가하기
                                    </button>
                                </div>

                            </div> 
						</c:forEach>
                           
						 
                            <!-- <div class="small-board-wrap">
                                <div class="small-board">
                                    <div class="board-subject-wrap">
                                        <div class="board-subject">지영</div>
                                        <button class="nav-item dropdown board-change-btn">
                                            <a data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                                                aria-expanded="false">⋮</a>
                                            <div class="dropdown-menu" style="">
                                                <div class="dropdown-item" href="#">지영</div>
                                                <div class="dropdown-item" href="#">민협</div>
                                                <div class="dropdown-item" href="#">윤지</div>
                                                <div class="dropdown-item" href="#">채은</div>
                                                <div class="dropdown-divider"></div>
                                                <div class="dropdown-item" href="#">게시판 추가</div>
                                            </div>
                                        </button>
                                    </div>

                                    <div class="card-wrap">
                                        <div class="card">
                                            <div class="card-subject">글글글글글글글글글글글글글글글글글글글글글글글글글글글글글</div>
                                            <div class="profile-img"></div>
                                        </div>
                                        <div class="card">
                                            <div class="card-subject">안녕</div>
                                            <div class="profile-img"></div>
                                        </div>
                                        <div class="card">
                                            <div class="card-subject">안녕안녕</div>
                                            <div class="profile-img"></div>
                                        </div>
                                    </div>
                                    <button class="post-btn btn btn-primary">
                                        추가하기
                                    </button>
                                </div>
                            </div> -->
<!-- 
                            <div class="medium-board-wrap board" data-size="2">
                                <div class="small-board">
                                    <div class="board-subject-wrap">
                                        <div class="board-subject">지영</div>
                                        <button class="nav-item dropdown board-change-btn">
                                            <a data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                                                aria-expanded="false">⋮</a>
                                            <div class="dropdown-menu" style="">
                                                <div class="dropdown-item" href="#">지영</div>
                                                <div class="dropdown-item" href="#">민협</div>
                                                <div class="dropdown-item" href="#">윤지</div>
                                                <div class="dropdown-item" href="#">채은</div>
                                                <div class="dropdown-divider"></div>
                                                <div class="dropdown-item" href="#">게시판 추가</div>
                                                <div class="dropdown-item" href="#">게시판 삭제</div>
                                            </div>
                                        </button>
                                    </div>

                                    <div class="card-wrap">
                                        <div class="card">
                                            <div class="card-subject">글글글글글글글글글글글글글글글글글글글글글글글글글글글글글</div>
                                            <div class="profile-img"></div>
                                        </div>
                                        <div class="card">
                                            <div class="card-subject">안녕</div>
                                            <div class="profile-img"></div>
                                        </div>
                                        <div class="card">
                                            <div class="card-subject">안녕안녕</div>
                                            <div class="profile-img"></div>
                                        </div>
                                    </div>
                                    <button class="post-btn btn btn-primary">
                                        추가하기
                                    </button>
                                </div>
                            </div> -->




                        </div>


                    </div>

                </div>




            </section>
            <aside>
            

				<%@ include file="/WEB-INF/views/include/nav/aside.jsp"%>
            
            
            </aside>
        </div>

    </div>

    <script type="text/javascript">
		
    let test;
	//카드끼리 소팅하기
        $('.card-wrap').sortable({

            connectWith: ".card-wrap",
            cancel: ".board-subject-wrap, .post-btn",
            start: (e, ui) => {
                let height = ui.item.css('height');

                $(".card-shadow").css('height', height);
            },
            update : (e , ui) => {
            	
            	
            	//이벤트 두번 등록 방지
				if(ui.sender == null) { 

	            	let board = ui.item.parents('.board');
	            	let bdIdx = board.attr('id');
	            	let postIdx = ui.item.attr('id');
					
					let changeSort;
	            	
	             	board.find('.card').each(function(i){
						let thisPostIdx = $(this).attr('id');
	            		if( thisPostIdx== postIdx) {
	            			changeSort = i+1;
	            	
	            		
	            			return;
	            		}
	
	            	})
	            	
	            	fetch("/board/post/change-sort" , {
	    				
	    				method : "POST",
	    				headers :  {"Content-type" : "application/json; charset=UTF-8"},
	    				body : JSON.stringify({
	    					bdIdx : bdIdx,
	    					changeSort : changeSort ,
	    					postIdx : postIdx
	    				
	    					})
	    				
	    			})
	            	
					
				            		
				 }
            	
            	
            },
            placeholder: "card-shadow" 



        }).disableSelection();

        //   $('.card').disableSelection();
        //   $('.card-subject').disableSelection();

        $('.card').on('click', e => {

            alert('dd');
        }).disableSelection();
	
        let u;
        $('.board-wrap').sortable({
            
            update : function(event ,ui) {
              	
              	let board = ui.item;
            	let bdIdx = board.attr('id');
              	let sort = board.data('sort');
              	let changeSort ;
          
              	
              	
              	$('.board').each( function(i , b) {
              		console.dir("현재 i: " + i);
              		console.dir("현재 IDX: "+$(b).attr('id'));
              		console.dir("bdIdx " + bdIdx);
              		if($(b).attr('id')==bdIdx) {
              			
              			changeSort = i+1;
              			return;
              		}
              	})
              	
              	
              	
            	fetch("/board/change/sort" ,{
    				
    				method : "POST",
    				headers :  {"Content-type" : "application/json; charset=UTF-8"},
    				body : JSON.stringify({
    					bdIdx : bdIdx,
    					sort : sort ,
    					changeSort : changeSort ,
    					wsIdx : ${workspace.wsIdx}
    				
    					
 
    					})
    				
    			})
            	
            }

        }).disableSelection();


	


		let jj;

        $('#add-small-board').on('click', function () {

            let size = sizeCal(1);
            console.dir(size);
            if (size > 4) {
                alert('사이즈 초과에러');
                return;
            }


            let smallBoardDiv = makeBoard('small');
            $('.board-wrap').append(smallBoardDiv);
            smallBoardDiv.attr('data-size', '1');

        })

        $('#add-medium-board').on('click', function () {

            let size = sizeCal(2);
            console.dir(size);
            if (size > 4) {
                alert('사이즈 초과에러');
                return;
            }

            let smallBoardDiv = makeBoard('medium');
            $('.board-wrap').append(smallBoardDiv);
            smallBoardDiv.attr('data-size', '2');

        })

        $('#add-large-board').on('click', function () {

            let size = sizeCal(4);
            console.dir(size);
            if (size > 4) {
                alert('사이즈 초과에러');
                return;
            }

            let smallBoardDiv = makeBoard('large');
            $('.board-wrap').append(smallBoardDiv);
            smallBoardDiv.attr('data-size', '4');


        })


        let sizeCal = function (size) {

            let data = 0;
            $('.board').each(function () {
                data += $(this).data('size');

            })

            return size + data;

        }



        let makeBoard = function (size) {
            let boardDiv = $('<div class="board"></div>');
            switch (size) {
                case "small":
                    boardDiv.addClass('small-board-wrap');
                    break;

                case "medium":
                    boardDiv.addClass('medium-board-wrap');
                    break;

                case "large":
                    boardDiv.addClass('large-board-wrap');
                    break;


            }

            boardDiv.html('<div class="small-board"><div class="board-subject-wrap"><input class="board-subject" type="text" placeholder="게시판 이름을 입력하세요"></div><div class="card-wrap"></div><button class="save-btn btn btn-primary">저장하기</button></div>')


            let input = boardDiv.find('.board-subject')
            input.css('backgroundColor', 'white');
            input.css('width', '100%');
            input.attr('autofocus', 'autofocus');
			
            
            //저장 버튼 눌렀을 때 
            boardDiv.find('.save-btn').on('click', function () {
            	 

                let bdName = input.val();
                if(!bdName) {
                	alert("게시판 이름을 입력하세요");
                	return;
                }
                
                let bdSize = boardDiv.data("size");
                 
                let toggle = $('<button class="nav-item dropdown board-change-btn"><a data-bs-toggle="dropdown" role="button" aria-haspopup="true"aria-expanded="false">⋮</a><div class="dropdown-menu" style=""><div class="dropdown-item" href="#">게시판 추가</div><div class="dropdown-item" id="remove-board">게시판 삭제</div></div></button>');
                boardDiv.find('.board-subject-wrap').append(toggle);

                $(this).remove();

                let addBtn = $('<button class="post-btn btn btn-primary">추가하기</button>');
                boardDiv.find('.small-board').append(addBtn);
                input.css('backgroundColor', 'transparent');
                input.attr('readonly', 'readonly');
	
                
                addBtn.on('click' , addPost);



                boardDiv.find('#remove-board').on('click' , removeEvent)
                
                
                fetch("/board/change/add-board" ,{
    				
    				method : "POST",
    				headers :  {"Content-type" : "application/json; charset=UTF-8"},
    				body : JSON.stringify({
    					wsIdx : "${wsIdx}",
    					bdName : bdName,
    					bdSize : bdSize
    					
 
    					})
    				
    			}).then(res=>res.json())
    			.then(board=> {
    				
    				boardDiv.attr('id' , board.bdIdx);
    				boardDiv.data('sort' , $('.board').length);
    			})


            })

            return boardDiv;

        }

    	
		let removeEvent = function() {

			

				alert("삭제");
				let board = $(this).parents('.board')
				board.remove();
				
				let bdIdx = board.attr('id');
				let sort = board.data('sort');
				fetch("/board/change/remove-board" , {
    				
    				method : "POST",
    				headers :  {"Content-type" : "application/json; charset=UTF-8"},
    				body : JSON.stringify({
    					bdIdx : bdIdx ,
    					sort : sort
 
    					})
    				
    			}).then(res=>{})
			
				
			
		
		}
		
   
	//게시판 삭제 버튼을 눌렀을 때
		$('.remove-board').each(function() {
			
			$(this).on('click' , removeEvent)
			
			
			
			
		});
	
	
	let addPost = function () {
			
			let bdidx = $(this).parents('.board').attr('id');
			location.href="/board/posting/${project.projectIdx}?bdidx="+ bdidx;
			
			
			
			
	}
			
	
	//추가하기 버튼 눌렀을 때 
	$('.post-btn').each(function() {
		
		$(this).on('click' , addPost)
		
	})
	

	



    </script>



</body>

</html>