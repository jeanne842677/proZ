<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="/resources/css/member/mypage.css">
<link type="text/css" rel="stylesheet" href="/resources/css/modal/modal.css">
<style type="text/css">
					#profile-change-git-btn{
                    	width: 200px; 
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
                <!--작업 내용-->
                <div class="section-wrapper">
                    <div class="content-wrapper" id="profileImg-wrapper">
                        <button id="profile-banner-btn"></button>
                        <button id="profile-img"><img class="profile" src="/resources/img/cat01.png"></button>
                        <input id="profile-img-input" type="file" accept="image/*" style="display: none;">
                    </div>
                    <div class="content-wrapper" id="profile">
                        <div class="profile-title">#사용자 프로필</div>
                        <div class="profile-subtitle">프로필 사진 변경</div>
                        <div class="profile-message"></div>
                        <div class="proflie-advice">프로필 사진을 클릭하여 최대 2MB 크기의 원하는 프로필 사진을 첨부하세요. </div>
                        <div class="profile-subtitle">개인 색상 지정</div>
                        <div class="proflie-advice">프로필 배너를 클릭하여 개인 색상을 선택하세요.</div>
                        <div class="profile-subtitle">사용자 닉네임</div>
                        <div class="extend-link">당신의 별명은 <b><u class="nickname">${certifiedUser.nickname}</u></b> 입니다. </div> 
                        <button class="btn btn-primary" id="profile-change-nickName-btn">닉네임 변경</button>
                        <div class="br-line"></div>
                    </div>
    
                    <div class="content-wrapper" id="userInfo">
                        <div class="profile-title">#계정 정보</div>
                        <div class="profile-subtitle">가입 이메일</div>
                        <div class="proflie-advice">로그인 시 입력하신 이메일 주소입니다 </div>
                        <div class="info-wrapper">
                            <div class="profile-message" id="profile-email">${certifiedUser.email }</div>
                        </div>
                        <div class="profile-subtitle">GIT_주소</div>
                        <div class="proflie-advice">계정에 연동된 GIT_Repository 주소입니다</div>
                        <div class="info-wrapper">
                            <div class="profile-message">
                                <div class="profile-message-btn-wrapper">
                                    <input class="form-control" id="form-git" type="text" value="${certifiedUser.git }" readonly>
                                    <button class="btn btn-primary" id="profile-change-git-btn">GIT 주소 변경</button> 
                                </div>
                            </div>
                        </div>
                        <div class="profile-subtitle">비밀번호 변경하기</div>
                        <div class="proflie-advice">가입시 입력하신 비밀번호를 변경할 수 있습니다</div>
                        <button class="btn btn-primary" id="profile-change-password-btn">비밀번호 변경</button>
                        <div class="br-line"></div>
                    </div>
                    
                    <div class="content-wrapper" id="leave">
                        <div class="profile-title">#회원 탈퇴</div>
                        <div class="leave-wrapper">
                            <img class="leave-img" src="/resources/img/undraw-space.png"> 
                            <div class="profile-subtitle">정말로 PRO_Z를 탈퇴하시나요?</div>
                            <div class="proflie-advice">회원탈퇴는 7일 이후에 완료되며 추가 인증이 필요합니다.</div>  
                            <div class="br-line"></div>
                            <button class="btn btn-secondary" id="button-isleave"> PROZ 회원 탈퇴 </button>
                            <div class="br-line"></div>
                            <div class="br-line"></div>
                        </div>
                    </div>
                </div>
            </section>
            <aside></aside>
        </div>
    </div>
</body>

<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="/resources/js/modal/modal.js"></script>
<script src="https://unpkg.com/vanilla-picker@2"></script>
<script>

	
	//**ColorPicker 설정 
	var parent = document.querySelector('#profile-banner-btn');
    var picker = new Picker(parent);
    picker.setOptions({
        popup:'bottom'
    })
    var parentJquery = $('#profile-banner-btn'); 
    parentJquery.click( ()=>{
    	parentJquery.children().css('position','relative'); 
    	parentJquery.children().css('left', '100px');
    });  
     
   	//**ColorPicker 통한 프로필컬러 변경 
   	picker.onDone = function(color) {
    	//color.hex로 hexColor를 불러오기, 전송을 위해 # 제거  
    	profileColorHex = color.hex.slice(1);
    	
    	fetch('/mypage/profileColor?profileColor='+ profileColorHex, 
    			{
    			method: 'POST', 	
    			credentials : 'include'
    		}) 
    	.then(response => response.text())
    	.then(text=>{
    		if(text != 'failed'){
    			parentJquery.css('background-color', text);
    		} else {
    			alert('색 변경이 실패하였습니다. 다시 시도하세요'); 
    		}
    	})
    	.catch( ()=>{
    		alert('색 변경이 실패하였습니다. 다시 시도하세요'); 
    	});	
	};
    
	//**프로필 이미지 비동기변경 
	var profileImgInputJquery = $('#profile-img-input'); 
	
	$('#profile-img').click( ()=>{
		profileImgInputJquery.click(); 
	});
	
	profileImgInputJquery.on('input', function() {
		 
		var file = document.querySelector('#profile-img-input').files[0]; 
		var formData = new FormData();  
		
		if(file == null){
			alert('파일을 선댁해주세요'); 
			return false; 
		}
		
		formData.append('files', file); 
		
		fetch('/mypage/profileImg', {
			method: 'POST', 	
			body : formData
		})
		.then(response => response.text())
    	.then(text=>{
    		if(text != 'failed'){
    			$('.profile').attr('src', 'http://localhost:9090/resources/upload/' + text);
    		} else {
    			alert('프로필 사진 변경이 실패하였습니다. 다시 시도하세요'); 
    		}
    	})
    	.catch( ()=>{
    		alert('프로필 사진 변경이 실패하였습니다. 다시 시도하세요'); 
    	});	
	});

    //**닉네임 변경 Modal 코드
    let nickNameModal = new Modal('닉네임 변경하기', '변경하실 닉네임을 입력하세요'); 
    nickNameModal.createInputModal(); 
    let nickNamePopUp = nickNameModal.modal; 
    nickNameModal.makeModalBtn($('#profile-change-nickName-btn')); 
    nickNameModal.setPlaceholder('닉네임 입력 (최대 8글자)'); 
    nickNamePopUp.find('#input').attr('maxlength',8);
    nickNameModal.setConfirmFnc( function(){

    	var changedNickname = nickNamePopUp.find('#input').val(); 
    	fetch('/mypage/profileNickname?nickname='+changedNickname, {
    		method : 'POST',
    	})
    	.then(response => response.text())
    	.then(text=>{
    		if(text != 'failed'){
    			// text가 오류메세지가 송출된다. (?) 
    			$('.nickname').text(text);  
    		} else {
    			alert('닉네임 변경이 실패하였습니다. 다시 시도하세요'); 
    		}
    	})
    	.catch( ()=>{
    		alert('닉네임 변경이 실패하였습니다. 다시 시도하세요'); 
    	})
    	
    	nickNamePopUp.find('#input').val("");  
    	
    }); 


    //**GIT 주소변경 Modal 코드
    let gitModal = new Modal('GIT주소 변경하기', '변경하실 GIT 주소를 입력하세요'); 
    gitModal.createInputModal(); 
    let gitPopUp = gitModal.modal; 
    gitModal.makeModalBtn($('#profile-change-git-btn')); 
    gitModal.setPlaceholder('GIT주소 입력'); 
    gitModal.setConfirmFnc( function(){
    	
    	var changedGit = gitPopUp.find('#input').val();
    	fetch('/mypage/profileGitRepo?git='+changedGit, {
    		method : 'POST',
    	})
    	.then(response => response.text())
    	.then(text=>{
    		if(text != 'failed'){
    			$('#form-git').val(text); 
    		} else {
    			alert('닉네임 변경이 실패하였습니다. 다시 시도하세요'); 
    		}
    	})
    	.catch( ()=>{
    		alert('닉네임 변경이 실패하였습니다. 다시 시도하세요'); 
    	})
    	
    	gitPopUp.find('#input').val("");  
    }); 

    //**패스워드 Modal 코드
    let passwordModal = new Modal('비밀번호 변경하기', '변경하실 비밀번호를 입력하세요.'); 
    passwordModal.createpasswordModal(); 
    let passwordPopUp = passwordModal.modal; 
    passwordModal.makeModalBtn($('#profile-change-password-btn')); 
    passwordPopUp.find('#input1').attr('placeholder', '비밀번호(8자이상 숫자,영문,특수문자 조합)');
    passwordPopUp.find('#input2').attr('placeholder', '비밀번호 다시 입력')
    passwordModal.setConfirmFnc( function(){
        alert(passwordPopUp.find('#input1').val() + '= 비밀번호 값<br>'
        + passwordPopUp.find('#input2').val() + '= 비밀번호 중복확인 값');  
    }); 
    
    //isLeave 버튼 
    let isleaveModal = new Modal('회원 탈퇴', '정말로 PROZ를 탈퇴하시겠습니까?'); 
    isleaveModal.createAlertModal(); 
    isleaveModal.makeModalBtn($('#button-isleave')); 
    isleaveModal.setConfirmFnc( function() {
    	fetch('/mypage/isleave', {
    		method : 'POST',
    	})
    	.then(response => response.text())
    	.then(text=>{
    		if(text == 'success'){
    		  alert('회원 탈퇴에 성공하였습니다.');
    		  location.href = "/"; 
    		} else if (text == 'failed'){
    			alert('회원 탈퇴에 실패하였습니다. 다시 시도하세요'); 
    		} else {
    			alert('회원 탈퇴에 실패하였습니다. 다시 시도하세요'); 
    		}
    	})
    	.catch( ()=>{
    		alert('회원 탈퇴에 실패하였습니다. 다시 시도하세요'); 
    	})
    }); 
</script>
</html>