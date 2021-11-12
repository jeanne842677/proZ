<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">


<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/resources/css/modal/modal.css"/>
    <link type="text/css" rel="stylesheet" href="/resources/css/setting/member-management.css"/>
     <script type="text/javascript" src="/resources/js/modal/modal.js"></script>
    <title>멤버 설정</title>
</head>

<body>

    <div class="wrap">
        <header>





        </header>

        <div class="con">
            <nav></nav>
            <section>
                <!--여기서만 작업-->
            <div class="content-wrap">
                <div class="member-manage-top">
                    <div class="title-img"><img src="/resources/img/premium-icon-people-5322431.png"></div>
                    <div class="title">멤버관리</div>
                </div>
                <div class="member-manage-main">
                    <div class="invite-link">
                        <div>
                            <p class="category-title">초대링크</p>
                            <p style="color :slategray;">초대 링크를 공유하여 워크스페이스에 사람들을 초대하세요.</p>
                            <div class="form-group">
                                <input class="form-control" id="link-input" type="text"
                                    value="https://localhost:9090/project/invite/${project.inviteCode}"
                                    readonly>
                                <div class="button-wrapper">
                                    <button type="button" class="btn btn-gradi1 copy-url">링크 복사</button>
                                    <button type="button" id="link-reset" class="btn btn-secondary">링크 재설정</button>
                                </div>
                            </div>
                        </div>
                    </div>



                    <hr style="margin: 50px 0 50px 0;">


                    <div class="invite-wrap">
                        <p class="category-title">프로젝트 초대하기</p>
                        <div class="form-group">
                            <input class="form-control" id="invite-member-input" type="text"
                                placeholder="이메일로 초대하기">
                            <div class="button-wrapper">
                                <button type="button" class="btn btn-gradi1 insert-member">멤버 추가</button>
                                <button type="button" id="member-save" class="btn btn-gradi1">저장하기</button>

                            </div>
                        </div>
                    </div>



                    <div class="member-list-wrapper">
                        <div class="search-member-wrapper">
                        <div class="search-member-content">
                            <div style="font-weight: bolder; margin : 2px 10px 0 10px; font-size:20px;">멤버</div>
                            <input class="form-control" id="search-member" type="text" placeholder="검색하기">
                            <a href=""><img class="search-icon" src="/resources/img/search.png"></a>
                        </div>
                            
                            <div class="category-member">

                                <div id="role-text">역할</div>
                                <div id="exile-text">강제추방</div>
                            </div>
                        </div>


                        <!--시작-->
                        
                    <c:forEach items="projectMember" var="member">
                        <hr style="margin: 20px 0 20px 0;">

                        <div class="member" id="${ member.userIdx }">
                            <div class="member-profile-wrapper">
                                <img class="search-icon" src="/resources/img/profile-user.png">
                                <div class="member-profile">
                                    <p class="user-name">강민협</p>
                                    <p class="user-email">kkmh7511@naver.com</p>
                                </div>
                            </div>
                            <div style="width: 200px;"> </div>
                            <div class="category-member-info">
                                <div class="role-wrap">
                                    <select class="role">
                                        <option>팀장</option>
                                        <option>팀원</option>
                                        <option>게스트</option>
                                    </select>
                                </div>
                                <button type="button" class="btn btn btn-danger exile">추방하기</button>

                            </div>




                        </div>
                        
                    </c:forEach>

                        <!--끝-->
                    </div>
                </div>


            </div>    


            </section>
            <aside></aside>
        </div>

    </div>



    <script type="text/javascript" src="/resources/js/setting/member-management.js"></script>
	<script type="text/javascript">
	
	$(function() {
		let modal = new Modal("링크 재설정" , "확인 클릭시 링크가 재설정 됩니다. <br> 링크를 재설정하시겠습니까?");
	 	modal.createTwoButtonModal("재설정" ,"취소");
	 	modal.makeModalBtn($('#link-reset'));
	 	$('.first-button').css("background" , "RGB(235, 87, 87)")
	 	$('.first-button').css("borderColor" , "RGB(235, 87, 87)")
	 	$('.first-button').on('click' , function() {
			
			fetch("/project/reset-invite-link" , {
				
				method : "POST",
				headers :  {"Content-type" : "application/json; charset=UTF-8"},
				body : JSON.stringify({projectIdx : "${project.projectIdx}"})
				
			}).then(res => res.text())
			.then(text => {
				$('#link-input').attr('value' , "https://localhost:9090/project/invite/"+text);
			})
			
		})
		
		
	})
		
	
	</script>
</body>




</html>