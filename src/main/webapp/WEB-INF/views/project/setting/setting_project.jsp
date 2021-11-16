<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/resources/css/nav.css?ver=1">
    
    <link type="text/css" rel="stylesheet" href="resources/css/setting/project.css">
    <link type="text/css" rel="stylesheet" href="resources/css/modal/modal.css">
    <script type="text/javascript" src="resources/js/modal/modal/modal.js"></script>
</head>
<body>

    <div class="wrap">
        <header>
        




        </header>

        <div class="con">
        <nav></nav>
        <section>
            <!--여기서만 작업-->
            
            <div class="prject-setting-top">
                <div class="title-img"><img src="resource/img/setting.png"></div>
                <div class="title">프로젝트설정</div>
            </div>

            
                <div class="member-manage-main">
                    <div class="main-frame">
                        <div class="main-wrapper">
                                <div class="project-img-wrapper">
                                    <img src="resource/img/profile-user.png">
                                </div>
                                <div class="project-img-button">
                                    <p style="color :slategray;">서버 이미지는 최소 151p x 151p  이상 , 1mb 미만</p>
                                    <div class="form-group">
                                        <div class="button-wrapper">
                                            <button type="button" class="btn btn-gradi1">이미지 업로드</button>
                                            <button type="button" class="btn btn-secondary">이미지 제거</button>
                                        </div>
                                    </div>
                                </div>
                            </div> 

                        

                        <div class="project-info-wrapper">
                            <div class="input-label-wrapper">
                                <label for="new-role-inpu">프로젝트 이름</label>
                                <input class="form-control title" id="project-title" type="text"  value="프로Z" readonly>
                                <button type="button" class="btn btn-secondary modify" style="margin-left: 10px; ">수정</button> 
                                <!-- 수정을 누르면 수정을 display: none  
                                    저장 display 설정
                                    project-title readonly 옵션 삭제 -->
                                <button type="button" class="btn btn-secondary save" style="margin-left: 10px; display: none;">저장</button>
                            </div>
                            <div class="input-label-wrapper">
                                <label for="new-role-input code">프로젝트 코드</label>
                                <input class="form-control" id="project-title" type="text" value="515684asd156dasd2" readonly>
                            </div>
                        </div>

                        <div class="role-list-wrapper-bottom">
                            <button type="button" class="btn btn-secondary prject-delete" style="margin-left: 10px;">프로젝트 제거</button>
                        </div>
                    </div>
                    
                </div>






            
           

        </section>
        <aside></aside>
        </div>

    </div>
    


</body>
<script type="text/javascript" src="resources/js/setting/project.js"></script>

</html>