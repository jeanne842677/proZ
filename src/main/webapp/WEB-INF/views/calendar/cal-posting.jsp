<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<head>
    <meta charset="UTF-8">
    <link type="text/css" rel="stylesheet" href="https://bootswatch.com/5/flatly/bootstrap.min.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <link type="text/css" rel="stylesheet" href="/resources/css/board/posting.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/nav.css?ver=4">
	<style type="text/css"> 
		.date{
			display:flex;
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
                <!--여기서만 작업-->
                <div class="editor-session-wrapper">
                    <div class="editor-main-title-wrapper">
                        <div class="editor-main-title"># 메인 타이틀</div>
                    </div>
                    <div class="editor-main-content-wrapper">
                        <div class="editor-navigation-bar"></div>
                        <div class="editor-title" contenteditable="true" placeholder="제목을 입력하세요"></div>
                        <div class="textArea" contenteditable="true">
                        	<div></div>
                        </div>
                        <div class="footer-wrapper">
                            <div class="footer-wrapper">
                                <div class="br-line" id="br-line80"></div>
                                <div class="footer-content-wrapper">
                                    <div class="editor-content-cover">
                                        <div class="editor-content-cover-wrapper">
                                            <div class="editor-message">커버</div>
                                            <div class="editor-cover"></div>
                                            <div class="editor-cover"></div>
                                            <div class="editor-cover"></div>
                                            <div class="editor-cover"></div>
                                            <div class="editor-cover"></div>
                                            <div class="editor-cover"></div>
                                            <div class="editor-cover"></div>
                                        </div>
                                        <button type="button" id="editor-submit-btn" class="btn btn-primary">POST</button>
                                    </div>
                                    <div class="editor-content-date">
                                        <div class="editor-message">작성 날짜</div>
                                        <div class="date">
                                        	<div class="start-date">2021년 03월 03일</div>
                                        	&nbsp;~&nbsp;
                                        	<div class="end-date">2021년 11월 13일</div>
                                        </div>
                                    </div>
                                    <div class="editor-content-hashTag">
                                        <div class="editor-message">#인물 태그</div>
                                        <div>#이유송 #하명도</div>
                                    </div>
                                    <div class="br-line"></div>
                                </div>
                                
                            </div>

                        </div>
                    </div>
                </div>
            </section>
            <aside>
            	<%@ include file="/WEB-INF/views/include/nav/aside.jsp"%>
            </aside>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <script type="text/javascript">

    $(function() {
    	
    	let changeDate =function(day) {
    		return day.getFullYear()+"년 " + (day.getMonth()+1)+"월 " + day.getDate()+"일";
    	}
    	
		let start = new Date(${param.start});
		let end = new Date(${param.end});
		end.setDate(end.getDate()-1);
		
		$('.start-date').text(changeDate(start));
		$('.end-date').text(changeDate(end));
    	
    })
	
	
	
    
    //Title에서 Enter_FocusingChange  
    $('.editor-title').on('keypress', function(e) {
        if(e.keyCode === 13){
            $('.textArea').focus();
            return false;  
        }
    });

    //Title 관련 Copy방지 
    document.querySelector('.editor-title').addEventListener('paste' , function(event) {
        console.log('1'); 
        event.preventDefault();
        let text =event.clipboardData.getData("text/plain");
        document.execCommand("insertHTML", false,  text);
    })
    </script>
    <script type="text/javascript">
    
    // SUBMIT 버튼 
	$('#editor-submit-btn').on('click' , function() {
		
		
		let subject = $('.editor-title').text();
		let content = $('.textArea').html();
			
		fetch("/calendar/change/add-cal" , {
			method : "POST",
			headers :  {"Content-type" : "application/json; charset=UTF-8"},
			body : JSON.stringify({
				startDate: ${param.start},
				endDate : ${param.end},
				wsIdx : ${param.wsIdx},
				calTitle : subject,
				calContent : content	
				
				})
			
		}).then(res=>{res.text()})
		.then(text=>{
			location.href="/calendar/${projectIdx}?wsIdx=${param.wsIdx}";
			
		})
	})
</script>
</body>
</html>

</body>
</html>