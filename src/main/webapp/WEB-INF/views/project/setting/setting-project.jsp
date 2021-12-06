<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/resources/css/nav.css?ver=2">
    <link type="text/css" rel="stylesheet" href="/resources/css/setting/project.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/modal/modal.css">
    <script type="text/javascript" src="/resources/js/modal/modal.js"></script>
    
    <style type="text/css">
    	 #image_upload {
            display: none;
        }

        #project-description{
            width: 60%;
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
        	<%@ include file="/WEB-INF/views/include/nav/setting-nav.jsp"%>
        </nav>
        <section>
            <!--여기서만 작업-->
            
            <div class="prject-setting-top">
                <div class="title-img"><img src="/resources/img/setting.png"></div>
                <div class="title">프로젝트설정</div>
            </div>

            
                <div class="member-manage-main">
                    <div class="main-frame">
                        <div class="main-wrapper">
                                <div class="project-img-wrapper">
                                    <img id="project-img" 
                                    <c:choose>
	                                    <c:when test="${projectImg != null}">
	                                    src="/file/${projectImg}"
	                                    </c:when>
	                                    <c:otherwise>
	                                    src="/resources/img/no-img.png"
	                                    </c:otherwise>
                                    </c:choose>
                                    >
                                </div>
                                <div class="project-img-button">
                                    <p style="color :slategray;">서버 이미지는 최소 151p x 151p  이상 , 1mb 미만</p>
                                    <div class="form-group">
                                        <div class="button-wrapper">
                                            <label for="image_upload" class="btn btn-gradi1">이미지 업로드</label>
                                            <input type="file" id="image_upload" accept="img/*"  ></button>
                                            <button type="button" id="delete-img" class="btn btn-secondary">이미지 제거</button>
                                        </div>
                                    </div>
                                </div>
                            </div> 

                        

                        <div class="project-info-wrapper">
                            <div class="input-label-wrapper">
                                <label for="new-role-inpu">프로젝트 이름 </label>
                                <input class="form-control title" id="project-title" type="text" data-state="none" value="${project.proName}" data-pre-name="${project.proName}">
                            </div>
                            <div class="input-label-wrapper">
                                <label for="new-role-input code">프로젝트 설명</label>
                                <input class="form-control" id="project-description" type="text" data-state="none" value="${project.proDescription}" data-pre-description="${project.proDescription}" placeholder="프로젝트 간단 설명을 입력해주세요 (20자 이내)"  >
                            </div>
                        </div>

                        <div class="role-list-wrapper-bottom">
                       		<button type="button" class="btn btn-gradi1 save" >설정 저장</button>
                            <button type="button" class="btn btn-secondary prject-delete" style="margin-left: 10px;">프로젝트 제거</button>
                        </div>
                    </div>
                    
                </div>






            
           

        </section>
        <aside>
        
        	<%@ include file="/WEB-INF/views/include/nav/aside.jsp"%>
        
        </aside>
        </div>

    </div>
    


</body>
<script type="text/javascript" src="/resources/js/setting/project.js"></script>
<script type="text/javascript">



var firstSaveModal = new Modal("설정 저장","설정을 저장하시겠습니까?");

var secondSaveModal = new Modal("저장 완료","저장이 완료되었습니다.");



firstSaveModal.createTwoButtonModal("저장","취소"); //버튼 2개생성 first-button : 저장 second-button : 취소
firstSaveModal.makeModalBtn($(".save"));   //버튼에 지정


secondSaveModal.createAlertModal(); //두번쨰모달 생성
secondSaveModal.makeModalBtn(firstSaveModal.modal.find(".first-button")); //first-button : 저장 <--여기에지정

let projectIdx = "${projectIdx}";


//프로젝트 프로필이미지 업로드
var projectImg = $('#image_upload'); 

//만약 현제 이미지가 존재한다면 업데이트

projectImg.on('input', function() {
	 
	var file = document.querySelector('#image_upload').files[0]; 
	var formData = new FormData();  
	var projectImg = $("#project-img").attr('src');
	
	if(file == null){
		alert('파일을 선댁해주세요'); 
		return false; 
	}
	
	formData.append('files', file); 
	
	if(projectImg == "/resources/img/no-img.png" ) {
		projectImgFeth(projectIdx,'insert',formData);
	}else{
		projectImgFeth(projectIdx,'update',formData);
	}
	
		
});

function projectImgFeth(projectIdx,state,formData) {
	fetch('/project/projectImg/' + projectIdx + '?state=' + state, {
		method: 'POST', 	
		body : formData
	})
	.then(response => response.text())
	.then(text=>{
		if(text != 'failed'){ 
			$('#project-img').attr('src', '/file/' + text);
		}else {
			alert('프로젝트 대표이미지 변경에 실패하였습니다. 다시 시도하세요'); 
		}
	})
	.catch( ()=>{
		alert('프로젝트 대표이미지 변경에 실패하였습니다. 다시 시도하세요'); 
	});
}





$("#delete-img").click(function () {//이미지 제거
	
	fetch('/project/delete/projectImg/' + projectIdx , {
		method: 'POST'
	})
	.then(response => response.text())
	.then(text=>{
		if(text != 'delete'){ 
			$("#project-img").attr("src","/resources/img/no-img.png");
		}else {
			alert('프로젝트 대표이미지 삭제에 실패하였습니다. 다시 시도하세요'); 
		}
	})
	.catch( ()=>{
		alert('프로젝트 대표이미지 삭제에 실패하였습니다. 다시 시도하세요'); 
	});
    
    
});



// 설정저장 ============================================
firstSaveModal.modal.find(".first-button").click(function() {
	secondSaveModal.setTitle("저장 완료"); //모달 저장완료로 변경
    secondSaveModal.setMessage("저장이 완료되었습니다.");
	
	
	let url = "/project/setting/project-setting/" + "${projectIdx}";
	
	
	if($("#project-title").val() != $("#project-title").data("pre-name")) {//현제입력된 값이랑 이전입력값이 다를경우
		$("#project-title").data("state","update");
	}
	if($("#project-description").val() != $("#project-description").data("pre-description")) {//현제입력된 값이랑 이전입력값이 다를경우
		$("#project-description").data("state","update");
	}
	
	
	let proName = $("#project-title").val();
	let proDescription = $("#project-description").val();
	let nameState = $("#project-title").data("state");
	let descriptionState = $("#project-description").data("state");
	
	if(!proName) {//프로젝트 명이 빈칸일시
        secondSaveModal.setTitle("저장 취소");
        secondSaveModal.setMessage("프로젝트명이 공백입니다.<br>프로젝트명을 입력해주세요.");
        console.log("공백은 불가능하다고");
        return false;
        
     }
	if(proName.length > 15) {//프로젝트 명이 15글자 초과일시
        secondSaveModal.setTitle("저장 취소");
        secondSaveModal.setMessage("프로젝트명이 15글자 초과입니다.");
        console.log("프로젝트 명은 15글자 이상은 불가능하다고");
        return false;
        
     }
	
	if(proDescription.length > 20) {//프로젝트 설명이 20글자 초과일시
        secondSaveModal.setTitle("저장 취소");
        secondSaveModal.setMessage("프로젝트설명이 20글자 초과입니다.");
        console.log("프로젝트 설명은 20글자 이상은 불가능하다고");
        return false;
        
     }
	
		
		
		
		let project = {
				"proName" : proName,
				"proDescription" : proDescription,
				"nameState" : nameState,
				"descriptionState" : descriptionState,
				"projectIdx" : ${project.projectIdx}
		};
      
		console.log(project);
      
      
       fetch(url,
            {method : "POST"
            ,headers : {"Content-type" : "application/json; charset=UTF-8"}
            ,body : JSON.stringify(project)
            
            }); 
      
      $("#project-title").data("pre-name",proName); //이전이름 데이터셋에 바뀐값 넣어주기
      $("#project-title").data("state","none");
      $("#project-description").data("pre-description",proDescription); //이전설명 데이터셋에 바뀐값 넣어주기
      $("#project-description").data("state","none");
      
      
      
      
      
      
});

//프로젝트 삭제
firstDelModal.modal.find(".first-button").click(function () {
	alert("프로젝트 삭제");
	let url = "/project/setting/project-delete/" + "${projectIdx}";
    fetch(url,
            {method : "POST"
            ,headers : {"Content-type" : "application/json; charset=UTF-8"}
    		,credentials: "same-origin"
            ,body : JSON.stringify({"projectIdx" : "${project.projectIdx}"})
            	
            
            }); 
    
    secondDelModal.modal.find("#modal-btn-confirm").click(function () {//확인을 누르면 redirect
    	window.location = "http://localhost:9090/project/project-list";
	})
	
});



</script>

</html>