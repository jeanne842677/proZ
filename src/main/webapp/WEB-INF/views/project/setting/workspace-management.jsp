<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>

<link type="text/css" rel="stylesheet" href="/resources/css/nav.css?ver=6">
<link type="text/css" rel="stylesheet" href="/resources/css/modal/modal.css" />
 <link type="text/css" rel="stylesheet" href="/resources/css/setting/workspace-management.css">
<script type="text/javascript" src="/resources/js/modal/modal.js"></script>
<script type="text/javascript" src="https://bootswatch.com/_vendor/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript"src="https://bootswatch.com/_vendor/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script> 


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
                <div class="need-section-padding">
                    <!-- top -->
                    <div class="member-manage-top">
                        <div class="top-title">
                            <div class="title-img"><img src="/resources/img/maintenance.png"></div>
                            <div class="title">워크스페이스 관리</div>
                        </div>
                        <div class="title-hr">
                            <hr>
                        </div>
                        <div class="top-description">
                            <div class="big-description">워크스페이스 추가</div>
                            <div class="small-description">워크스페이스를 추가/변경 할 수 있습니다.</div>
                        </div>
                    </div>


                    <!-- main -->
                    <div class="member-manage-main">

                        <!-- select 부분 -->
                        <div class="select-wrapper">
                            <div class="selectbox">
                                <select>
                                    <option selected disabled> 카테고리 선택</option>
                                    <option> 메모 </option>
                                    <option> 게시판 </option>
                                    <option> 채팅 </option>
                                    <option> 로드맵 </option>
                                </select>
                            </div>
                            <div class="select-input">
                                <input class="category-name-input form-control" type="text" placeholder="새 카테고리명을 입력해주세요." value="">
                            </div>
                            <div class="select-button">
                                <button type="button" class="btn btn-gradi1 category-add-button">추가하기</button>
                            </div>
                        </div>

                        <!-- 카테고리 추가되는 부분 -->
                        <div class="category-list-wrapper"> 
                            <!-- 목록  -->
                            <div class="no-move-wrapper">
                            <div class="category-subheading">
                                <div id="content-label">항목</div>
                                <div id="name-label">이름</div>
                                <div id="remove-label">삭제</div>
                            </div>
                            <div class="category-hr">
                                <hr>
                            </div>
                        </div>
                        <div class="all-category-list">
                            <div class="origin-category-list">
                            <!-- ********************* 기존 카테고리 -->
                            <div class="category-wrapper my" data-state="none" >
                                
                                <div class="category-name-wrap category-shadow">
                                    <div class="category-imgs">
                                        <!-- 첫번째 div-->
                                        <img class="square-icon" src="/resources/img/square.png">
                                        <img class="search-icon" src="/resources/img/premium-icon-calendar-5175870.png"> <!-- 여기 나중에 c:if test-->
                                    </div>

                                    <div class="category-input">
                                        <!-- 두번째 div -->
                                        <input class="category-input-text form-control" type="text" placeholder="원래값" value="1"> 
                                    </div>
                                </div>
                                <div id="delete-button">
                                    <!-- 세번째 div -->
                                    <button type="button" class="btn btn-danger category-delete-button">삭제</button>
                                </div>
                                
                            </div>
                            </div>

                            <div class="clone-category-list">
                            <!-- ***************** 복제할 카테고리-->
                            <div class="category-wrapper new-category" data-state="none" >

                                <div class="category-name-wrap category-shadow">
                                    <div class="category-imgs">
                                        <!-- 첫번째 div-->
                                        <img class="square-icon" src="/resources/img/square.png">
                                        <img class="search-icon" src="/resources/img/premium-icon-calendar-5175870.png"> <!-- 여기 나중에 c:if test-->
                                    </div>

                                    <div class="category-input">
                                        <!-- 두번째 div -->
                                        <input class="category-input-text form-control" type="text" placeholder="" value=""> 
                                    </div>
                                </div>


                                <div id="delete-button">
                                    <!-- 세번째 div -->
                                    <button type="button" class="btn btn-danger category-delete-button">삭제</button>
                                </div>

                            </div>
                            </div>
                        </div>


                        </div>
                    </div>

                    <!-- bottom 부분  -->
                    <div class="member-manage-bottom">
                        <div class="button-wrapper">
                            <button type="button" class="btn btn-gradi1 category-save-button">저장하기</button>
                        </div>
                    </div>

                </div>
            </section>
            <aside></aside>
        </div>
    </div>
</body>

<script type="text/javascript" src="/resources/js/setting/workspace-management.js"></script>

</html>

