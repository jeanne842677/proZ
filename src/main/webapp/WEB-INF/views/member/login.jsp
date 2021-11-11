<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

    <title>프로제트 로그인</title>
    
    <link type="text/css" rel="stylesheet" href="/resources/css/member/login.css?ver=1">



</head>

<body>

    <div class="wrap">
        <header>
        </header>

        <div class="con">
            <section>

                    <div class="login-box">
                        <img id="login-logo" src="/resources/img/logo-white.png">
                        <div class="login-content">
                            <div id="sign-text">Log In</div>
                            <form action="/member/login" method="post">
                            <input type="email" id="userId" name="userId" class="form-control" placeholder="이메일을 입력하세요">
                            <input type="password" id="password" name="password" class="form-control"
                                placeholder="비밀번호를 입력하세요">

                            <button id="login-btn" class="btn btn-lg btn-primary" type="button">로그인</button>
                            </form>
                            <div class="find-wrap"><a href="/member/join">회원가입</a> <a href="#">비밀번호 재설정</a></div>
                            <div class="social">다른 서비스 계정으로 로그인</div>
                            <div class="btn-wrap">
                                <button id="kakao-btn" class="btn  btn-primary" type="button">카카오 로그인</button>
                                <button id="google-btn" class="btn  btn-primary" type="button">구글 로그인</button>
    
                            </div>


                        </div>
                        
                    </div>
                    
            </section>
        </div>

        <div class="design-box1"></div>
        <div class="design-box2"></div>
        <div class="design-box3"></div>

    </div>



</body>

</html>