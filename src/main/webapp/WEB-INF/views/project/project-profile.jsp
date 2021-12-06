<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="/resources/css/member/mypage.css">
<link type="text/css" rel="stylesheet" href="/resources/css/modal/modal.css">
<link type="text/css" rel="stylesheet" href="/resources/css/nav.css">
<style type="text/css">
               #profile-change-git-btn{
                       width: 200px; 
                    }
                    a{
                    text-decoration: none; 
                    color: inherit;
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
               <%@ include file="/WEB-INF/views/include/nav/profile-nav.jsp"%>
            </nav>
            <section>
                <!--작업 내용-->
                <div class="section-wrapper">
                    <div class="content-wrapper" id="profileImg-wrapper">
                       <a name="userProfile"></a>
                        <button id="profile-banner-btn" style="background-color: ${member.profileColor}"></button>
                        <button id="profile-img"><img class="profileImg" src="<spring:url value='/file/${profileImg}'/>"></button>
                        <input id="profile-img-input" type="file" accept="image/*" style="display: none;">
                    </div>
                    <div class="content-wrapper" id="profile">
                       
                        <div class="profile-message"></div>
                      
                        <div class="profile-subtitle">개인 색상 지정</div>
                        <div class="proflie-advice">프로필 배너를 클릭하여 개인 색상을 선택하세요.</div>
                        <div class="profile-subtitle">사용자 닉네임</div>
                        <div class="extend-link">당신의 별명은 <b><u class="nickname">${member.nickname}</u></b> 입니다. </div> 
                        <button class="btn btn-primary" id="profile-change-nickName-btn">닉네임 변경</button>
                        <div class="br-line"></div>
                    </div>
    
                   
                    <div class="content-wrapper" id="leave">
                        <div class="profile-title"><a name="userIsleave">#프로젝트 탈퇴</a></div>
                        <div class="leave-wrapper">
                            <img class="leave-img" src="/resources/img/undraw-space.png"> 
                            <div class="profile-subtitle">정말 프로젝트를 탈퇴하시나요?</div>
         
                            <div class="br-line"></div>
                            <button class="btn btn-secondary" id="button-isleave"> 프로젝트 탈퇴 </button>
                            <div class="br-line"></div>
                            <div class="br-line"></div>
                        </div>
                    </div>
                </div>
            </section>
            
        </div>
    </div>
</body>

<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="/resources/js/modal/modal.js"></script>
<script src="https://unpkg.com/vanilla-picker@2"></script>
<script src="/resources/js/validator/validateMachine1.1.js"></script>
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
       
       fetch('/project/profile/update/Color/${projectIdx}?profileColor='+ profileColorHex, 
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
      
      fetch('/project/profile/update/Img/${projectIdx}', {
         method: 'POST',    
         body : formData
      })
      .then(response => response.text())
       .then(text=>{
          if(text != 'failed'){ 
             $('.profileImg').attr('src', '/file/' + text);
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
    //nickNamePopUp.find('#input').attr('maxlength',8);
    nickNameModal.setConfirmFnc( function(){

       
       var changedNickname = nickNamePopUp.find('#input').val(); 
       fetch('/project/profile/update/Nickname/${projectIdx}?nickname='+changedNickname, {
          method : 'POST'
       })
       .then(response => response.text())
       .then(text=>{
          if(text != 'failed'){
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

    
    //isLeave 버튼 
    let isleaveModal = new Modal('프로젝트 탈퇴', '정말로 프로젝트를 탈퇴하시겠습니까?'); 
    isleaveModal.createAlertModal(); 
    isleaveModal.makeModalBtn($('#button-isleave')); 
    isleaveModal.setConfirmFnc( function() {
       
       fetch('/project/profile/update/isLeave/${projectIdx}', {
          method : 'POST'
          
       })
       .then(response => response.text())
       .then(text=>{
          if(text == 'success'){
            alert('프로젝트 탈퇴에 성공하였습니다.');
            location.href = "/"; 
          } else if (text == 'failed'){
             alert('프로젝트 탈퇴에 실패하였습니다. 다시 시도하세요'); 
          } else {
             alert('프로젝트 탈퇴에 실패하였습니다. 다시 시도하세요'); 
          }
       })
       .catch( ()=>{
          alert('프로젝트 탈퇴에 실패하였습니다. 다시 시도하세요'); 
       })
    }); 
    
    
    //Validator 적용을 위한 Jquery 객체 추출, 적용 
    let inputTag = nickNamePopUp.find('#input').get(); 
    var realNicknameInput = inputTag[0]; 
    var v = new ValidateMachine();
    v.addValidator(realNicknameInput); 
    v.addReverseRegExp({
        '\\s' : '닉네임에 공백을 포함할 수 없습니다'
    });
    v.addReverseRegExp({
        '\[~!@#$%^&*()_+|<>?:{}]' : '특수문자를 포함할 수 없습니다'
    });
    v.addRegExp({
        '\^[a-zA-Zㄱ-힣0-9]{1,8}$' : '닉네임은 8글자를 넘을 수 없습니다.'
    });
    
    let inputTag2 = gitPopUp.find('#input').get(); 
    var realGitInput = inputTag2[0];
    var v2 = new ValidateMachine();
    v2.addValidator(realGitInput); 
    v2.addReverseRegExp({
        '\\s' : 'GIT주소에 공백을 포함할 수 없습니다'
    });
    
   let inputTag3 = passwordPopUp.find('#input1').get(); 
   
   var realPasswordInput1 = inputTag3[0];
   
   var v3 = new ValidateMachine();
   v3.addValidator(realPasswordInput1); 
   v3.addReverseRegExp({
       '\\s' : '패스워드에 공백을 포함할 수 없습니다'
   });
   v3.addRegExp({
      '\^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9]).{8,70}$' : '8-70자의 영문,숫자,특수문자가 아닙니다.'
    })
   // 중복검사를 위한 코드
   
   // 문제점, querySelector로 넣을때는 되는데, 그 외에는 안된다. 
   // dom이 부모태그를 추가하는 과정에서 삭제되었다 다시 추가됨으로인해 발생하는 오류 
   let inputTag4 = passwordPopUp.find('#input2').get(); 
   let inputTag5 = passwordPopUp.find('#input1').get(); 
   var v4 = new ValidateMachine(); 
   var realPasswordInput2 = inputTag4[0];
   v4.addDuplicateValidator(realPasswordInput2, inputTag5[0]);
   
   // 문제점, 다시 킬 경우 border와 Msg가 그대로이다. input으로 없에는 것이 아니라 
   // 그냥 없에는 것이기 때문에 생긴다. 
   // modal을 다시 킬 경우 clickevent의 이전에 그 안의 inputTag들을 inputEvent를 
   // 통해 초기화시켜야 한다. 
   // 모든 모달에 이벤트핸들러를 초기회시킨 후 다시 적용시킨다.. 
   var event = new Event('input', {
       bubbles: true,
       cancelable: true,
   });
   
   $('#profile-change-nickName-btn').on('click', ()=>{
       let nicknameDOM = nickNamePopUp.find('#input').get();
       nicknameDOM[0].dispatchEvent(event); 
   })
 
    
</script>
</html>