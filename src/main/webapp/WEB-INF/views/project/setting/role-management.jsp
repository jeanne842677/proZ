<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/resources/css/setting/role_management.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/modal/modal.css">
    <script type="text/javascript" src="/resources/js/modal/modal.js"></script>
</head>

    <div class="wrap">
        <header>
        




        </header>

        <div class="con">
        <nav></nav>
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
	                           	<div class="one-role-total-wrapper" data-authIdx="${role.authIdx}">
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
	                           	<div class="one-role-total-wrapper" data-authIdx="${role.authIdx}">
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
	                            <div class="one-role-total-wrapper" data-authIdx="${role.authIdx}">
	                           		<div id="role-wrapper">
		                                <div class="role-title-wrapper">
		                                    <input class="form-control" id="role-title" type="text" value="${role.authName}" >
		                                </div>
		                                <div id="role" class="role">
		                                	<c:choose >
		                                		<c:when test="${role.projectAuth == 1}">
		                                			<button type="button" class="btn btn-info on " data-bs-toggle="tooltip" data-bs-placement="top" title="프로젝트 의 설정할수있는 권한을 부여합니다.">프로젝트권한</button>
		                                		</c:when>
		                                		<c:otherwise>
		                                			<button type="button" class="btn off btn-secondary " data-bs-toggle="tooltip" data-bs-placement="top" title="프로젝트 의 설정할수있는 권한을 부여합니다.">프로젝트권한</button>
		                                		</c:otherwise>
		                                	</c:choose>
		                                	
		                               		<c:choose>
		                                		<c:when test="${role.createAuth == 1}">
		                                			<button type="button" class="btn btn-info on " data-bs-toggle="tooltip" data-bs-placement="top" title="채팅방 개설할수있는 권한을 부여합니다.">채팅방개설</button>
		                                		</c:when>
		                                		<c:otherwise>
													<button type="button" class="btn off btn-secondary " data-bs-toggle="tooltip" data-bs-placement="top" title="채팅방 개설할수있는 권한을 부여합니다.">채팅방개설</button>		                                		
		                                		</c:otherwise>
		                                	</c:choose>
		                                	
		                                	<c:choose>
		                                		<c:when test="${role.createAuth == 1}">
		                                    		<button type="button" class="btn btn-info on " data-bs-toggle="tooltip" data-bs-placement="top" title="프로젝트로 맴버를 초대할 수 있는 권한을 부여합니다.">맴버 초대</button>
		                                		</c:when>
		                                		<c:otherwise>
		                                    		<button type="button" class="btn off btn-secondary " data-bs-toggle="tooltip" data-bs-placement="top" title="프로젝트로 맴버를 초대할 수 있는 권한을 부여합니다.">맴버 초대</button>
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
                          <div class="one-role-total-wrapper" data-authIdx="new">
	                            <div id="role-wrapper" class="role-wrapper">
	                                <div class="role-title-wrapper">
	                                    <input class="form-control" id="role-title" type="text" value="" >
	                                </div>
	                                <div id="role" class="role">
	                                    <button type="button" class="btn btn-info on" data-bs-toggle="tooltip" data-bs-placement="top" title="프로젝트 의 설정할수있는 권한을 부여합니다.">프로젝트권한</button>
	                                    <button type="button" class="btn btn-info on" data-bs-toggle="tooltip" data-bs-placement="top" title="채팅방 개설할수있는 권한을 부여합니다.">채팅방개설</button>
	                                    <button type="button" class="btn btn-info on" data-bs-toggle="tooltip" data-bs-placement="top" title="프로젝트로 맴버를 초대할 수 있는 권한을 부여합니다.">맴버 초대</button>
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

        
        <aside></aside>
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
	
	$(".one-role-total-wrapper").each(function (index) {
		
	})

	
	
	
	//이치문으로 역할 다찾기
	$(".role-title-wrapper").each(function(index) {	
		let authName = $(this).find("#role-title").val();
		

		
		let projectAuth = 0;
		let createAuth = 0;
		let memberAuth = 0;
		let authIdx = $(this).closest(".one-role-total-wrapper").data('authIdx'); //값이 데이터셋에서 안받아짐
		
		let test = $(".one-role-total-wrapper").data('authIdx');
		
		console.log("test = " + test);
		
		
		
		console.log("authIdx = " + authIdx);
		
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
		
		
		
		
		if(index == 3) {//display : none 된 role-list-content-new 때문에 3번인덱스는 빈값
			return;
		}else{
			
			if(!authName) {//역할명이 빈칸일시
				secondSaveModal.setTitle("저장 취소");
				secondSaveModal.setMessage("역할명이 공백입니다. 역할명을 입력해주세요.");
				console.log("공백은 불가능하다고");
				isSave = 0;
				return false;
				
			}else {
				secondSaveModal.setTitle("저장 완료");
				secondSaveModal.setMessage("저장이 완료되었습니다.");
				
				roleList.push({"projectIdx" : prjectIdx
					  ,"authName" : authName
					  ,"projectAuth" : projectAuth	
					  ,"createAuth" : createAuth
					  ,"memberAuth" : memberAuth
					  ,"authIdx" : authIdx
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
				}).then(res => {
					console.log(res);
				})
		
		
		
	}

		
	
	
});

</script>


</html>