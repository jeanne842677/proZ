<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


<head>
   <%@ include file="/WEB-INF/views/include/head.jsp" %>
   <link type="text/css" rel="stylesheet" href="/resources/css/nav.css?ver=1">
    <link type="text/css" rel="stylesheet" href="/resources/css/setting/role_management.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/modal/modal.css">
    <script type="text/javascript" src="/resources/js/modal/modal.js"></script>
</head>

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
            
            <div class="member-manage-top">
                <div class="title-img"><img src="/resources/img/premium-icon-genius-2112729.png"></div>
                <div class="title">역할관리</div>
            </div>


            <div class="member-manage-main">
                <div class="main-wrapper">
                        
                        <div class="save-button-wrapper">
                            <p style="color :slategray; margin : 30px 0 0 0">버튼을 눌러 on/off 권한관리를 부여한 후 설정을 저장하세요!</p>
                            <button type="button" class="btn btn-gradi1 save" style="margin-left: 20px;" >설정 저장</button>
                        </div>

                        <div class="role-list-wrapper">

                            <div class="form-group">
                                <input class="form-control" id="new-role-input" type="text" placeholder="새 역할을 입력해주세요." value="" >
                                <div class="button-wrapper">
                                  <button type="button" class="btn btn-gradi1 add-role">역할 추가</button>
                                </div>
                            </div>

                            <div class="hr-wrapper">
                                <hr>
                            </div>
                            
                            <div class="role-list-content1">
                            
                            
                           <c:forEach var="role" items="${roleList}" varStatus="idx">
                            
                              <c:choose>
                                 <c:when test="${idx.index == 0}">
                                 <div class="one-role-total-wrapper" data-authIdx="${role.authIdx}" data-state="none" data-auth-name="${role.authName}">
                                    <div id="role-wrapper">
                                       <div class="role-title-wrapper">
                                           <input class="form-control" id="role-title" type="text" value="${role.authName}" >
                                       </div>
                                       <div id="role" class="role">
                                           <button type="button" class="btn btn-info on default"  data-bs-toggle="tooltip" data-bs-placement="top" title="프로젝트 의 설정할수있는 권한을 부여합니다.">프로젝트권한</button>
                                           <button type="button" class="btn btn-info on default"  data-bs-toggle="tooltip" data-bs-placement="top" title="채팅방 개설할수있는 권한을 부여합니다.">채팅방개설</button>
                                           <button type="button" class="btn btn-info on default"  data-bs-toggle="tooltip" data-bs-placement="top" title="프로젝트로 맴버를 초대할 수 있는 권한을 부여합니다.">맴버 초대</button>
                                           <button type="button" class="btn btn-danger delete-role default default-del">삭제</button>
                                       </div>
                                   </div>
                                   <div class="hr-wrapper">
                                       <hr>
                                   </div>
                                 </div>
                                 </c:when>
                                 
                                 <c:when test="${idx.index == 1}">
                                 <div class="one-role-total-wrapper" data-authIdx="${role.authIdx}" data-state="none" data-auth-name="${role.authName}">
                                    <div id="role-wrapper">
                                      <div class="role-title-wrapper">
                                          <input class="form-control" id="role-title" type="text" value="${role.authName}" >
                                      </div>
                                      <div id="role" class="role">
                                          <button type="button" class="btn  off btn-secondary default" data-bs-toggle="tooltip" data-bs-placement="top" title="프로젝트 의 설정할수있는 권한을 부여합니다.">프로젝트권한</button>
                                          <button type="button" class="btn  off btn-secondary default" data-bs-toggle="tooltip" data-bs-placement="top" title="채팅방 개설할수있는 권한을 부여합니다.">채팅방개설</button>
                                          <button type="button" class="btn off btn-secondary default" data-bs-toggle="tooltip" data-bs-placement="top" title="프로젝트로 맴버를 초대할 수 있는 권한을 부여합니다.">맴버 초대</button>
                                          <button type="button" class="btn btn-danger delete-role default default-del">삭제</button>
                                      </div>
                                  </div>
                                  <div class="hr-wrapper">
                                      <hr>
                                  </div>
                              </div>
                                 
                                 </c:when>
                                 
                                  <c:otherwise>
                               <div class="one-role-total-wrapper" data-authIdx="${role.authIdx}" data-state="none" data-auth-name="${role.authName}">
                                    <div id="role-wrapper">
                                      <div class="role-title-wrapper">
                                          <input class="form-control" id="role-title" type="text" value="${role.authName}" >
                                      </div>
                                      <div id="role" class="role">
                                         <c:choose >
                                            <c:when test="${role.projectAuth == 1}">
                                               <button type="button" class="btn btn-info on" data-bs-toggle="tooltip" data-bs-placement="top" title="프로젝트 의 설정할수있는 권한을 부여합니다.">프로젝트권한</button>
                                            </c:when>
                                            <c:otherwise>
                                               <button type="button" class="btn off btn-secondary" data-bs-toggle="tooltip" data-bs-placement="top" title="프로젝트 의 설정할수있는 권한을 부여합니다.">프로젝트권한</button>
                                            </c:otherwise>
                                         </c:choose>
                                         
                                           <c:choose>
                                            <c:when test="${role.createAuth == 1}">
                                               <button type="button" class="btn btn-info on" data-bs-toggle="tooltip" data-bs-placement="top" title="채팅방 개설할수있는 권한을 부여합니다.">채팅방개설</button>
                                            </c:when>
                                            <c:otherwise>
                                       <button type="button" class="btn off btn-secondary" data-bs-toggle="tooltip" data-bs-placement="top" title="채팅방 개설할수있는 권한을 부여합니다.">채팅방개설</button>                                            
                                            </c:otherwise>
                                         </c:choose>
                                         
                                         <c:choose>
                                            <c:when test="${role.memberAuth == 1}">
                                                <button type="button" class="btn btn-info on" data-bs-toggle="tooltip" data-bs-placement="top" title="프로젝트로 맴버를 초대할 수 있는 권한을 부여합니다.">맴버 초대</button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="button" class="btn off btn-secondary" data-bs-toggle="tooltip" data-bs-placement="top" title="프로젝트로 맴버를 초대할 수 있는 권한을 부여합니다.">맴버 초대</button>
                                            </c:otherwise>
                                         </c:choose>
                                          <button type="button" class="btn btn-danger delete-role">삭제</button>
                                          
                                             </div>
                                        </div>
                                        <div class="hr-wrapper">
                                            <hr>
                                        </div>
                              </div>
                                 </c:otherwise>
                              </c:choose>
                           </c:forEach>
                            </div>
                            
                                

                               
                          


                       


                        <!-- 새로운 역할 -->
                        <div class="role-list-content-new"  style="display: none;">
                          <div class="one-role-total-wrapper" data-authIdx='' data-state="new" data-auth-name="">
                               <div id="role-wrapper" class="role-wrapper">
                                   <div class="role-title-wrapper">
                                       <input class="form-control" id="role-title" type="text" value="" >
                                   </div>
                                   <div id="role" class="role">
                                       <button type="button" class="btn off btn-secondary" data-bs-toggle="tooltip" data-bs-placement="top" title="프로젝트 의 설정할수있는 권한을 부여합니다.">프로젝트권한</button>
                                       <button type="button" class="btn off btn-secondary" data-bs-toggle="tooltip" data-bs-placement="top" title="채팅방 개설할수있는 권한을 부여합니다.">채팅방개설</button>
                                       <button type="button" class="btn off btn-secondary" data-bs-toggle="tooltip" data-bs-placement="top" title="프로젝트로 맴버를 초대할 수 있는 권한을 부여합니다.">맴버 초대</button>
                                       <button type="button" class="btn btn-danger delete-role">삭제</button>
                                   </div>
                               </div>
                               <div class="hr-wrapper">
                                     <hr>
                               </div>
                           </div>
                        </div>

                        

                            

                            
                        </div>
                        
                        <div class="role-list-wrapper-bottom">
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

<script type="text/javascript" src="/resources/js/setting/role_management.js"></script>

<script type="text/javascript">





//설정 저장버튼 누름
$(".first-button").click(function() {
   
   
   let roleList = [];
   let prjectIdx = ${projectIdx};
   let isSave = 1;
   let authNameDuplicateCheck = []; //역할명 중복검사 할 Array객체
   let DuplicateCheckSet = new Set(); //중복 체크할 Set객체
   
      

   
   
   
   //이치문으로 역할 다찾기
   $(".role-title-wrapper").each(function(index) { 
	   
	   
      let authName = $(this).closest(".one-role-total-wrapper").data('auth-name') == "" ? $(this).find("#role-title").val() : $(this).closest(".one-role-total-wrapper").data('auth-name') ; //이전 역할명
      let newAuthName = $(this).find("#role-title").val(); // 현제입력된 역할명
	
      $(this).closest(".one-role-total-wrapper").removeAttr('data-auth-name');

      
      
      if($(this).closest(".one-role-total-wrapper").css("display") !== "none") {
         authNameDuplicateCheck.push($(this).find("#role-title").val());//중복여부 확인하려 Array에 모든 입력값을 담음.
         DuplicateCheckSet.add($(this).find("#role-title").val());      //중복여부 확인하려 Set에 모든 입력값을 담음.
      }
      
      
      console.log("set : " + DuplicateCheckSet);
      console.log("array : " + authNameDuplicateCheck);
      
      
      
      if(authName != newAuthName) {//이전역할명과 현제입력된 역할명이 다를경우
         if($(this).closest(".one-role-total-wrapper").data('state') == "new" || $(this).closest(".one-role-total-wrapper").data('state') == "delete" ) { //만약 새로운 역할이면 리턴
            
         }else{
            $(this).closest(".one-role-total-wrapper").removeAttr('data-state');
            $(this).closest(".one-role-total-wrapper").data('state','update'); 
         }
         
      }
      
      let projectAuth = 0;
      let createAuth = 0;
      let memberAuth = 0;
      let authIdx = $(this).closest(".one-role-total-wrapper").data('authidx');
      let state = $(this).closest(".one-role-total-wrapper").data('state'); 
      
      
      
      console.log(state);
      
      //이치문으로 어떤권한이 있는지 버튼이 4개라서 4번돔
      $(this).siblings(".role").find(".btn").each(function (index) {
         
         switch ($(this).text()) {
         case '프로젝트권한':
            if($(this).hasClass("on")) {
               projectAuth = 1
            }
            break;
         case '채팅방개설':
            if($(this).hasClass("on")) {
               createAuth = 1
            }
            break;
         case '맴버 초대':
            if($(this).hasClass("on")) {
               memberAuth = 1
            }
            break;

         default:
            break;
         }
         
         
      })
      
      
      
      
      if(index == ${roleCnt}) {//display : none 된 role-list-content-new 때문에 
         
         return;
      }else{
         
         if((newAuthName.replace(/ /g,"")) =="" && $(this).closest(".one-role-total-wrapper").css("display") !== "none" ) {//역할명이 빈칸일시
            secondSaveModal.setTitle("저장 취소");
            secondSaveModal.setMessage("역할명이 공백입니다.<br>역할명을 입력해주세요.");
            console.log("공백은 불가능하다고");
            isSave = 0; //fetch통신 안함
            return false;
            
         }else if(DuplicateCheckSet.size !== authNameDuplicateCheck.length){ //역할명 중복검사
            secondSaveModal.setTitle("저장 취소");
            secondSaveModal.setMessage("중복된 역할명이 있습니다.<br> 확인하고 저장해주세요.");
            console.log("중복은 불가능하다고");
            isSave = 0; //fetch통신 안함
            return false;
            
         }else if(newAuthName.length > 8 && $(this).closest(".one-role-total-wrapper").css("display") !== "none") {
             secondSaveModal.setTitle("저장 취소");
             secondSaveModal.setMessage("역할명은 8글자 이내로 작성해주세요.");
             console.log("8글자 이내로 작성하라고");
             isSave = 0; //fetch통신 안함
             return false;
        	 
        	 
         }else {
             $(this).closest(".one-role-total-wrapper").data('auth-name',newAuthName); //dataSet에 이전 이름 넣기
            secondSaveModal.setTitle("저장 완료");
            secondSaveModal.setMessage("저장이 완료되었습니다.");
            
            roleList.push( {
                 "projectIdx" : prjectIdx
                 ,"authName" : newAuthName
                 ,"projectAuth" : projectAuth   
                 ,"createAuth" : createAuth
                 ,"memberAuth" : memberAuth
                 ,"authIdx" : authIdx
                 ,"state" : state
                 ,"prevAuthName" : authName
                  });
            
         }
      }
      

      
      
   });
   
   
   console.log(roleList);
   
   
   
   
   
   if(isSave == 1){
      
      let projectIdx = "/project/setting/role-management/" + "${projectIdx}";
      
      
      fetch(projectIdx ,
            {method : "POST"
            ,headers : {"Content-type" : "application/json; charset=UTF-8"}
            ,body : JSON.stringify(roleList)
            });
            
      
            
      $(".one-role-total-wrapper").each(function (index) {
          
          if(index == ${roleCnt}) {//display : none 된 role-list-content-new 때문에 
             
             return;
          }else if($(this).data('state') == 'new'   || $(this).data('state') == "update") { //fetch 통신후 state를 none로 변경 
             $(this).removeAttr('data-state');
             $(this).data('state','none'); 
          }else if($(this).data('state') == "delete") { //fetch 통신후 delete인 역할을 삭제
        	  $(this).remove();
          }
       
       
       
       });
            
            
            
            
            
            
            
   }
   
   
   
   

      
   
   
});


$(".one-role-total-wrapper").each(function (index,e) {
	let test = $(this).data("authidx");
	console.log("index=" + index);
	console.log("test = " + test);
})

</script>


</html>