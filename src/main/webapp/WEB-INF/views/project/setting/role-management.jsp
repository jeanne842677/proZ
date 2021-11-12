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
	                           	
	                           	</c:when>
	                           	
	                           	<c:when test="${idx.index == 1}">
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
	                           	
	                           	</c:when>
	                           	
	                            	<c:otherwise>
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
	                           	</c:otherwise>
	                           </c:choose>
                           </c:forEach>
                            </div>
                            
                                

                               
                          


                       


                        <!-- 새로운 역할 -->
                        <div class="role-list-content-new"  style="display: none;">
                            <div id="role-wrapper">
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




$(".first-button").click(function() {
	
	
	let roleList = [];
	

	
	
	
	//이치문으로 역할 다찾기
	$(".role-title-wrapper").each(function(index) {	
		let authName = $(this).find("#role-title").val();
		
		//이치문으로 어떤권한이 있는지
		$(".role").each(function (index) {
			
			switch ($(this).find(".btn").text();) {
			case '프로젝트권한':
				
				break;
			case '채팅방개설':
				
				break;
			case '맴버 초대':
				
				break;

			default:
				break;
			}
			
			console.log(index)
		})
		
		
		
		
		
		
		console.log("auth = " + auth)
		
		if(index == 3) {
			return;
		}
		
		roleList.push({"autName" : authName
					  ,"projectAuth" : 		
					  ,"autcreateAuthhIdx" : 
					  ,"memberAuth" :
		
		})
		
		
		console.log(roleList);
		
	});
	
	
	
	
	
	
	

		
	let projectIdx = "/project/setting/role-management/" + "${projectIdx}";
	
	
	fetch(projectIdx ,
			{method : "POST"
			,headers : {"Content-type" : "application/json; charset=UTF-8"}
			,body : JSON.stringify(role)
			}).then(res => {
				console.log(res);
			})
	
});

</script>


</html>