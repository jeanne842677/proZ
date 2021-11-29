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
                        <div class="editor-minHeight-div" style="min-height: 300px;">
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
                                        <div>2021년 03월 03일 ~ 2021년 11월 13일</div>
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
            <aside></aside>
        </div>
    </div>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="/resources/js/posting/popUpMenu.js"></script>
<script src="/resources/js/posting/customJqeuryAuto.js"></script>
<script src="/resources/js/posting/customJqueryAutoOption.js"></script>
<script>

    // init custom_autocomplete widget
    initcustomAutocomplete(); 
    createCustomAutocomplete($('.commonDiv')); 
    
    // Title과 initPage를 위한 기능들 
    // initPage용 element (다시 추가하는 기능은 고민중)
    var createInitPageFnc = function(element){
            element.on('click', function(e){
            $(this).parent().append("<div class='commonDiv'contenteditable='true' style='font-size:14px;'></div>");
            $(this).parent().children('.commonDiv').get(0).focus(); 
            createCustomAutocomplete($(this).parent().children('.commonDiv')); 
            $(this).remove(); 
        })
    }
    createInitPageFnc($('.initPage')); 

    // 타이틀 div를 위한 기능들 
    var createTitleDiv = function(element){
        TitleEnterFnc(element);  
        arrowFnc(element); 
    }

    var TitleEnterFnc = function(element) {
    element.on('keydown', function(e) { 
      if(e.keyCode === 13){
        
          if($(this).next()[0].className === 'initPage'){
            $(this).next().remove(); 
          }
          if(isSelect === false){
            e.preventDefault();
            let afterText;
              // isSelect를 통해서 일반 enter와 특수한 enter를 구분 
              // ** 주의, selection은 저장되지 않고 계속 바뀌므로 값 저장필요 
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
            // Event trigger Enter일 경우 : 
            isSelect = false; 
            $(this).next().focus();
          };
      }; 
    });
  };

    console.log($('.editor-title')); 
    createTitleDiv($('.editor-title')); 

    // 카피 비동기통신 테스트 중... 
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

  // 링크 Div를 만드는 function 
  var createLinkDiv = function(element){
    isCopied(element); 
  }
</script>
<script>

// initDiv를 클릭하면 

// 제목에서 enter를 치면, div가 만들어지도록 만든다. 
// Title 자체가 arrowFunction + Enter키를 적용시킨 특수 DIV로 작동 
// 삭제는 없다.

// clickEvent를 감지, 만약 자식요소가 ~개 이하이면 삭제 후 initPage 추가 
// backSpace를 감지, 만약 자식요소가 ~개 이하이면 initPage 다시추가 


//Title 관련 Copy방지 (연구중)
// document.querySelector('.editor-title').addEventListener('paste' , function(event) {
//     console.log('1'); 
//     event.preventDefault();
//     let text =event.clipboardData.getData("text/plain");
//     document.execCommand("insertHTML", false,  text);
// });

// innerHTML을 이용하면 통째로 파싱할 수는 있으나 문제가 있다. 
// ****innerHTML을 이용하면 XSS파싱공격에 취약해지고 보안이슈가 발생할 수 있다. 

//Fecth 버튼 이벤트 코드 
</script>
<script type="text/javascript">

// SUBMIT 버튼 
$('#editor-submit-btn').on('click' , function() {
    
    let subject = $('.editor-title').text();
    let content = $('.editor-minHeight-div').html();
        
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
        location.href="/board/${projectIdx}?wsIdx=${wsIdx}";
    })
})
</script>
</body>
</html>