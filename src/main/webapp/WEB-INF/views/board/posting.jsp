<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<meta charset="UTF-8">
	<!-- common css -->
	<link type="text/css" rel="stylesheet" href="https://bootswatch.com/5/flatly/bootstrap.min.css">
	<link type="text/css" rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	<link type="text/css" rel="stylesheet" href="/resources/css/board/posting.css">
	<!-- css for CustomJqueryAutocomplete module  -->
	<link type="text/css" rel="stylesheet" href="/resources/css/board/jquery-ui.css">
	<link type="text/css" rel="stylesheet" href="/resources/css/board/customAuto.css">
	<link type="text/css" rel="stylesheet" href="/resources/css/loading/loading.css">
	<link type="text/css" rel="stylesheet" href="/resources/css/modal/modal.css">
<style>
    .commonDiv{
        padding: 5px 5px 5px 12px; 
    }
    .commonDiv:hover{
        background-color: rgb(214, 211, 255);
    }
    .immutableDiv:hover{
        background-color: rgb(214, 211, 255);
    }
    .immutableDiv:focus{
        background-color: rgb(182, 177, 255);
    }
    *,
    *::before,
    *::after {
    box-sizing: content-box !important; 
    } 
    .initPage{
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
        <header></header>
        <div class="con">
            <nav></nav>
            <section>
                <!--여기서만 작업-->
                <div class="editor-session-wrapper">
                    <div class="editor-main-title-wrapper">
                        <div class="editor-main-title"># 메인 타이틀</div>
                    </div>
                    <div class="editor-main-content-wrapper">
                        <div class="editor-navigation-bar"></div>
                        <div class="editor-minHeight-div" style="min-height: 500px;">
                        <div class="editor-title" contenteditable="true" placeholder="제목을 입력하세요"></div>
                        <div class="initPage">
                            <div>이곳을 클릭하여 새로운 포스트를 작성하세요</div>
                            <img src="/resources/img/undraw_control_panel_re_y3ar.svg" style="height: 200px;">
                        </div>
                        </div>
                        <div class="footer-wrapper">
                            <div class="footer-wrapper">
                                <div class="br-line" id="br-line80"></div>
                                <div class="footer-content-wrapper">
                                    <div class="editor-content-cover">
                                        <div class="editor-content-cover-wrapper">
                                            <div class="editor-message">커버</div>
                                            <button class="editor-cover" style="background-color:#ff3a68;"></button>
                                            <button class="editor-cover" style="background-color:#ff77ee;"></button>
                                            <button class="editor-cover" style="background-color:#7ec1fc;"></button>
                                            <button class="editor-cover" style="background-color:#4dffde;"></button>
                                            <button class="editor-cover" style="background-color:#6cff84;"></button>
                                            <button class="editor-cover" style="background-color:#f0f266;"></button>
                                            <button class="editor-cover" style="background-color:#fcae4ddb;"></button>
                                        </div>
                                        <button type="button" id="editor-submit-btn" class="btn btn-primary">POST</button>
                                    </div>
                                    <div class="editor-content-date">
                                        <div class="editor-message">작성 날짜</div>
                                        <div></div>
                                    </div>
                                    <div class="br-line"></div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </section>
            <aside></aside>
        </div>
    </div>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="/resources/js/posting/popUpMenu.js"></script>
<script src="/resources/js/posting/customJqeuryauto.js"></script>
<script src="/resources/js/posting/customJqueryAutoOption.js"></script>
<script src="/resources/js/loading/loading.js"></script>
<script src="/resources/js/modal/modal.js"></script>
<script>

    // *** 페이지 기능 (분리 이전) 
    
   	// 1) initPage(시작페이지) 추가 및 시작페이지에 enter_click시 사라지는 기능 부여 
    var createInitPageFnc = function(element){
            element.on('click', function(e){
            $(this).parent().append("<div class='commonDiv'contenteditable='true' style='font-size:14px;'></div>");
            $(this).parent().children('.commonDiv').get(0).focus(); 
            createCustomAutocomplete($(this).parent().children('.commonDiv')); 
            $(this).remove(); 
        });
    };
    createInitPageFnc($('.initPage')); 
    
    // 2) Titlediv enterFunction을 customize  
    var createTitleDiv = function(element){
        TitleEnterFnc(element);  
        arrowFnc(element); 
    }
    
	// 3-2) TitleDiv의 arrowFunction costomize
    var TitleEnterFnc = function(element) {
	    element.on('keydown', function(e) { 
	      if(e.keyCode === 13){
	        
	          if($(this).next()[0].className === 'initPage'){
	            $(this).next().remove(); 
	          }
	          if(isSelect === false){
	            e.preventDefault();
	            let afterText; 
	               $(this).after(function(){
	                 let text = $(this).text();
	                 let currentSel = selection.anchorOffset;
	                 let originText = text.slice(0, currentSel); 
	                 $(this).text(originText); 
	                     if(text.length-currentSel !== 0){
	                       afterText = text.slice(-(text.length-currentSel));
	                     } else {
	                       afterText = ""; 
	                     }           
	                 return "<div class='commonDiv'contenteditable='true' style='font-size:14px;'></div>";
	               });
	            //3. 포커싱 바꾸기
	            let sibling = $(this).next();
	            sibling.text(afterText); 
	            createCustomAutocomplete(sibling);
	            sibling.focus(); 
	          } else { 
	            isSelect = false; 
	            $(this).next().focus();
	          };
	      }; 
	    });
	};
 	
	// --------------------- 테스트 중 기능 -------------------------
    
	// 1) 카피 비동기통신 테스트 중... 
    var isCopied = function(element){
    element.children().children('input')
    .on('paste', function(e){
      e.preventDefault();
      let text = event.clipboardData.getData("text/plain");
      $(this).val(text); 
      $(this).parent().html('<div class="loader"></div>');
      // fetch를 통해 바로 값을 보낸다. 
      // fetch('url', {
        
      // })
      // .then(response => response.text())
      // .then(text => function(text){
      //   // 받은 값에 따라 다르게 작동한다. 
      //   // switch_Case로 모듈화 피한다.  

      //   // file이라면, originFileName / 새로운 링크를 받아 랜더링 

      //   // img라면 이름 필요없이 링크만 

      //   // audio 역시 이름 필요없이 링크만 
      // })
      // .catch();
    })
  }

  // 2. 링크 Div를 만드는 function 테스트 중.. 
  var createLinkDiv = function(element){
    isCopied(element); 
  }



</script>
<script type="text/javascript">

// *Editor_Submit Button clicked 
$('#editor-submit-btn').on('click' , function() {
    
	let subject = $('.editor-title').text();
    let content = $('.editor-minHeight-div').html().trim();
    console.log(content); 
    // 1) 제목 NULL 처리 
	if(subject === ""){
		modal.modal.css('display','flex');
		return; 
	}
	
	// 2. 전송시작 및 로딩화면 공개 
    // 로딩화면 on
    loading.on(); 
    fetch("/board/add-post" , {
        method : "POST",
        headers :  {"Content-type" : "application/json; charset=UTF-8"},
        body : JSON.stringify({
            postTitle : subject,
            postContent : content,
            postColor : postColor, 
            bdIdx : "${param.bdidx}"
            })
    }).then(res=>{res.text()})
    .then(text=>{
        //location.href="/board/${projectIdx}?wsIdx=${wsIdx}";
    })
})
</script>
<script type="text/javascript">

	//*** 페이지 위젯 초기화 및 instance 생성 
	
	// 1) init loadingGear(로딩화면) 
	loading = new loadingGear('gear'); 
	loading.createLoadingGear(); 
	
	// 2) init AlertModal(확인모달); 
	modal = new Modal('제목을 적어주세요', '글 제목을 입력해야 글을 등록할 수 있어요');
	modal.createAlertModal(); 
	
	// 3) init custom_autocomplete widget
	initcustomAutocomplete(); 
	createCustomAutocomplete($('.commonDiv')); 
	
	// 4) init title contentEditable div 
	createTitleDiv($('.editor-title'));
	
	// 5) init postBannerColor to default value; 
	var postColor = 'white';
	$('.editor-cover').on('click', function(e){
  	 postColor = $(this).css('background-color'); 
    });
	
	// 6) init post Date 
	const date = new Date();
	const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' }; 
	$('.editor-content-date div:nth-child(2)').html(date.toLocaleDateString(undefined, options));

</script>
</body>
</html>