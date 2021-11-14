<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

    <title>프로제트 로그인</title>
    
    <link type="text/css" rel="stylesheet" href="/resources/css/member/login.css?ver=1">
    
    



</head>
<style type="text/css">
input {
    -webkit-appearance: none;
       -moz-appearance: none;
            appearance: none;

#email, #password {
	background-color: RGB(62, 66, 77);
	margin: 10px 0;
	border-color: RGB(255, 255, 255, 0.2);
	color: white;
	height: 48px;
}

.emptyemail, .emptypassword, .message{
	font-size:15px;
}

</style>

<body>
<script type="text/javascript" src="/resources/js/member/login.js"></script>
<c:if test="${not empty message}">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>


    <div class="wrap">
        <header>
        </header>

        <div class="con">
            <section>

                    <div class="login-box">
                        <img id="login-logo" src="/resources/img/logo-white.png">
                        <div class="login-content">
                            <div id="sign-text">Log In</div>
                            <form action="/member/login" method="post" onsubmit="return check()">
                            
                            <input type="email" id="email" name="email" class="form-control" placeholder="이메일을 입력하세요" style="background-color: RGB(62, 66, 77);">
                            
                            <span class="emptyemail" style="display:none" >이메일이 입력되지 않았습니다.</span>
                            
                            <input type="password" id="password" name="password" class="form-control"
                                placeholder="비밀번호를 입력하세요" >
                                
                            <span class="emptypassword" style="display:none">비밀번호가 입력되지 않았습니다.</span>
							
                            	<span class="message" style="color:red;">${message}</span>
                            
                            <button id="login-btn" class="btn btn-lg btn-primary" 
                            type="submit" onclick="check();" >로그인</button>
                            
                            </form>
                            
                            <div class="find-wrap"><a href="/member/join">회원가입</a> <a href="#">비밀번호 재설정</a></div>
                            <div class="social">다른 서비스 계정으로 로그인</div>
                            <div class="btn-wrap">
                             	<a href="/member/kakao_login" id="custom-login-btn" >
			                       <img src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg" width="186"  />
			                     </a>
                                <button id="google-btn" class="btn  btn-primary" type="button">
                                	<a href="${google_url}">구글 로그인</a>
    							</button>
    
                            </div>


                        </div>
                        
                    </div>
                    
            </section>
        </div>

        <div class="design-box1"></div>
        <div class="design-box2"></div>
        <div class="design-box3"></div>

    </div>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</body>

</html>