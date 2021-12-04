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
	<!-- css for Navigation bar -->
	<link type="text/css" rel="stylesheet" href="/resources/css/nav.css?ver=4">
<style>
    .commonDiv{
        padding: 5px 5px 5px 12px; 
    }
    .commonDiv:hover{
        background-color: #EEEEEE;
    }
    .commonDiv::before{
    	position : relative; 
    	right:20px; 
    	content : "::"
    }
    .immutableDiv:hover{
        background-color: #EEEEEE;
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
   

    /* 클래스 이름 변경은 원래 border를 줄수 없는 문제발생 */
    .dropleaved {
        border-color: #d8d8d8;
        border-style: dashed;
	    width:  200px;
	    height: 100px;
    }

    .dragOver {
        border-color: slategray; 
        border-style: dashed;
	    width:  200px;
	    height: 100px;
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
            <aside><%@ include file="/WEB-INF/views/include/nav/aside.jsp" %></aside>
        </div>
    </div>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/dompurify/2.3.3/purify.min.js"></script>
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
      
      // inputTag가 아닌 상태에서 file객체를 생성할 수 있는 방법은 없을까..? 
      //var file = document.querySelector('#profile-img-input').files[0]; 
	  //var formData = new FormData();
      var img = null; 
      
      fetch('/posting/fileIo', {
    	  method : "POST",
          headers :  {"Content-type" : "application/json; charset=UTF-8"},
          body : JSON.stringify({
              postTitle : subject,
              postContent : img,
              postColor : postColor, 
              bdIdx : "${param.bdidx}"
              })
      })
      .then(response => response.text())
      .then(text => function(text){
         // 받은 값에 따라 다르게 작동한다. 
         console.log('결과값은 : ' + text); 
         // switch_Case로 모듈화 피한다.  

         // file이라면, originFileName / 새로운 링크를 받아 랜더링 

         // img라면 이름 필요없이 링크만 

         // audio 역시 이름 필요없이 링크만 
       })
       .catch();
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
    // DOMPurify로 입력 검증 
    content = DOMPurify.sanitize(content);
    // TRIM 및 LineBreak, Quot escape처리 
    content.replace(/(\r\n|\n|\r)/gm, ""); 
    
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
        location.href="/board/${projectIdx}?wsIdx=${wsIdx}";
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
<script type="text/javascript">


// ----------------------드래그 앤 드롭 비동기 테스트 -----------------------

//*** 드랍이벤트 Handler 
function dropHandler(ev) {
	
  // 1. 파일 드랍 이벤트 시작 
  console.log('File(s) dropped');
  // 2. 파일을 담아 보낼 formData 객체 만들기 
  var formdata = new FormData(); 
  // 3. 브라우저 기본 드랍이벤트 (파일오픈) 방지 
  ev.preventDefault();
  // 4. 파일-파일아님 검증 (후 파일은 append)
  if (ev.dataTransfer.items) {
      // Use DataTransferItemList interface to access the file(s)
      for (var i = 0; i < ev.dataTransfer.items.length; i++) {
      // If dropped items aren't files, reject them
          if (ev.dataTransfer.items[i].kind === 'file') {
              var file = ev.dataTransfer.items[i].getAsFile();
              console.log('... file[' + i + '].name = ' + file.name);
              formdata.append(`files`,file);
              // 시간상의 문제로 기능은 파일 하나를 받는 것으로 구현 
          }
      }
  } else {
      // Use DataTransfer interface to access the file(s)
      for (var i = 0; i < ev.dataTransfer.files.length; i++) {
      console.log('... file[' + i + '].name = ' + ev.dataTransfer.files[i].name);
      }
  }
  // ** formData 검증 ( nullCheck 필요 )
  console.log('formData의 내용은 : ');
  console.log(formdata.get('file0')); 
  
  // 5. Hovering 을 className 변경으로 임시 활성화 
  ev.target.className = 'dropleaved'; 
  
  // 6. 로딩화면 작동 
  ev.target.innerHTML = '<div class="loader"></div>'; 
  
  // 7. 최종 fetch 
  	
  	fetch(`/board/posting/fileIo?projectIdx=${projectIdx}` , {
	  method : 'POST',
	  body : formdata
	})
	.then(response => response.text())
	.then(text => {
	 
	  // 부모 div와 받아온 json객체를 가져온다. 
	  let immutableDiv = ev.target.parentElement;
	  let res = JSON.parse(text); 
	  
	  // data-* 속성을 이용하여 보여줄 fetch를 구한다.
	  
	  switch(ev.target.getAttribute('data-fetchResult')){
		  case 'img' : 
			  // img일 경우 경로첨부 및 immutableDiv에 append 처리 
			  let img = document.createElement('img');
			  // 최대 크기제한, 500px 
			  img.style.maxWidth = '500px';
			  img.style.margin = '20px 10px 20px 10px';
			  img.setAttribute('src', '/file/' + res.savePath + res.renameFileName);
			  immutableDiv.append(img); 
			  break; 
		  case 'file' :
			  // file일 경우 a tag 추가 및 이벤트 부여 
			  let file = document.createElement('a');
			  file.setAttribute('download', '/file/' + res.savePath + res.renameFileName); 
			  file.setAttribute('href', '/file/' + res.savePath + res.renameFileName);
			  
			  // a tag에 event 부여  
			  file.addEventListener('click', function(){
				  // 1. 파일 다운로드 
				  let link = document.createElement('a'); 
				  link.href = file.getAttribute('href'); 
				  link.download = file.getAttribute('href').substr('1111');
				  link.click(); 
			  }); 
			 
			  let icon = document.createElement('i'); 
			  icon.className = 'far fa-file'; 
			  file.append(icon); 
			  let text = document.createTextNode(" " + res.originFileName); 
			  file.append(text);
			  immutableDiv.style.cssText = "width:200px; height:25px; display:flex; align-items:center; padding:5px 5px 5px 12px; width:100%; font-size:14px; caret-color: transparent;";
			  immutableDiv.append(file); 
			  break; 
		  case 'audio' : 
			  // audio일 경우 경로첨부 및 immutableDiv에 append 처리 
			  let audio = document.createElement('audio');
			  audio.setAttribute('controls', ''); 
			  audio.style.margin = '20px 10px 20px 10px';
			  audio.setAttribute('src', '/file/' + res.savePath + res.renameFileName);
			  immutableDiv.append(audio);
			  break; 
		  default : 
			  break; 
	  };
		
	  // 2. dropBox 제거
	  ev.target.remove(); 
	   
	})

}

//*** 드래그Over이벤트 Handler 
function dragOverHandler(ev) {

  // 1. file이 dropZone에 존재하고 있음 
  console.log('File(s) in drop zone');
  
  // 2. ClassName을 통해 dropEvent를 가시화 
  ev.target.className = 'dragOver'; 

  // 3. 기본 dragOverEvent 방지 
  ev.preventDefault();
}

function dragleaveHandler(ev) {

  ev.target.className = 'dropleaved'; 
  ev.preventDefault(); 
}



</script>

</body>
</html>