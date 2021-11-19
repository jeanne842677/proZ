<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link type="text/css" rel="stylesheet" href="https://bootswatch.com/5/flatly/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="/resources/css/member/searchPassword.css">    
</head>
<head>
<body>
    <div class="wrap">
        <header>
        </header>
        <div class="con">
            <section>
            <!-- 작업 공간 -->
                <form action="/member/searchPassword" method="post">
                    <div class="login-box">
                        <img id="login-logo" src="/resources/img/logo-white.png">
                        <div class="login-content">
                            <div id="sign-title">비밀번호 찾기</div>
                            <div id="sign-text">가입한 이메일주소를 입력하여 임시주소를<br> 발급받아 새로운 비밀번호를 설정하세요.</div>
                            <input type="text" id="userId" class="form-control" placeholder="이메일을 입력하세요">
                            <div class="btn-wrap">
                                <button id="back-btn" class="btn  btn-primary" type="button" onclick="location.href='/';">뒤로가기</button>
                                <button id="progress-btn" class="btn  btn-primary" type="submit" >진행하기</button>    
                            </div>
                        </div>  
                    </div>
                </form>
            </section>
        </div>
        <div class="design-box1"></div>
        <div class="design-box2"></div>
        <div class="design-box3"></div>
    </div>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="validateMachine1.1.js"></script>
<script>
    //jquery 사용불가 
    var userId = document.querySelector('#userId'); 
    var v = new ValidateMachine(); 
    v.addValidator(userId); 
    v.addReverseRegExp({
        "\\s" : "이메일에 빈칸은 포함하실 수 없습니다"
    })
    v.addRegExp({
        "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$" :
        "적합한 이메일 형식이 아닙니다."
    })
</script>
</html>