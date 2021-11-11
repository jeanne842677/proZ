<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">


<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/resources/css/modal/modal.css"/>
    <link type="text/css" rel="stylesheet" href="/resources/css/setting/member_management.css"/>
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
            
            <div class="member-manage-top">
                <div class="title-img"><img src="/resources/img/premium-icon-people-5322431.png"></div>
                <div class="title">멤버관리</div>
            </div>
            <div class="member-manage-main">
                <div class="invite-link">
                    <div>
                        <p class="category-title">초대링크</p>
                        <p style="color :slategray;">초대 링크를 공유하여 이 워크스페이스로 사람들을 초대하세요.</p>
                        <div class="form-group">
                              <input class="form-control" id="link-input" type="text" value="https://www.notion.so/invite/ca8302610cc4da6ed479247cfd0715046b4bc540" readonly>
                              <div class="button-wrapper">
                                <button type="button" class="btn btn-gradi1 copy-url">링크 복사</button>
                                
                              </div>
                          </div>
                          <hr style="margin: 50px 0 50px 0;">
                    </div>
                    <div>
                        <p class="category-title">프로젝트 초대하기</p>
                        <div class="form-group">
                            <input class="form-control" id="invite-member-input" type="text" placeholder="닉네임#0000 또는 이메일 입력" >
                            <div class="button-wrapper">
                              <button type="button" class="btn btn-gradi1 insert-member">멤버 추가</button>
                              
                            </div>
                        </div>
                    </div>
                    


                    <div class="member-list-wrapper">
                        <div class="search-member-wrapper">
                            <h4 style="font-weight: bolder; margin : 2px 10px 0 0;">멤버</h4> <input class="form-control" id="search-member" type="text" placeholder="검색하기" >
                            <a href=""><img class="search-icon" src="/resources/img/search.png"></a>
                            <div class="category-member">
                                <div> </div>
                                <div><p>역할</p></div>
                                <div><p>강제추방</p></div>
                            </div>
                        </div>

                        <hr style="margin: 20px 0 20px 0;">

                        <div class="member">
                            <div class="member-img-wrapper">
                                <img class="search-icon" src="/resources/img/profile-user.png">
                            </div>
                            <div class="member-profile">
                                <p class="user-name">강민협</p>
                                <p class="user-email">kkmh7511@naver.com</p>
                            </div>
                            <div style="width: 200px;"> </div>
                            <div class="category-member-info">
                                <div> </div>
                                <div>    
                                    <select class="role">
                                        <option>팀장</option>
                                        <option>팀원</option>
                                        <option>게스트</option>
                                    </select>
                                </div>
                                <div class="exile-button">
                                    <button type="button" class="btn btn btn-danger exile">추방하기</button>
                                </div>
                            </div>

                            
                        </div>
                        <hr style="margin: 20px 0 20px 0;">
                        <div class="member">
                            <div class="member-img-wrapper">
                                <img class="search-icon" src="/resources/img/profile-user.png">
                            </div>
                            <div class="member-profile">
                                <p class="user-name">강민협</p>
                                <p class="user-email">kkmh7511@naver.com</p>
                            </div>
                            <div style="width: 200px;"> </div>
                            <div class="category-member-info">
                                <div> </div>
                                <div>    
                                    <select class="role">
                                        <option>팀장</option>
                                        <option>팀원</option>
                                        <option>게스트</option>
                                    </select>
                                </div>
                                <div class="exile-button">
                                    <button type="button" class="btn btn btn-danger exile">추방하기</button>
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
    


<script type="text/javascript" src="/resources/js/setting/member_management.js"></script>

</body>


</html>