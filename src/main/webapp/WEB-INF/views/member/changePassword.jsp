<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link type="text/css" rel="stylesheet" href="https://bootswatch.com/5/flatly/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="/resources/css/member/searchPassword.css">    
</head>
<body>
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
                        <form class="login-content" action="/member/changePassword" method="post">
                            <div id="sign-title">비밀번호 변경</div>
                            <div id="sign-text">변경하려는 비밀번호를 변경해 주세요.</div>
                            <input type="password" id="password" name="password" class="form-control" placeholder="새 비밀번호를 입력해주세요.">
                            <input type="password" id="checkedPassword" name="checkedPassword" class="form-control" placeholder="새 비밀번호 한번 더 입력해주세요.">
                                    <button id="progress-btn" class="btn btn-lg btn-primary" type="submit">진행하기</button>
                        </form>
                    </div>
            </section>
        </div>
        <div class="design-box1"></div>
        <div class="design-box2"></div>
        <div class="design-box3"></div>
    </div>
</body>
<script src="/resources/js/validator/validateMachine1.1.js"></script>
<script>
    var password = document.querySelector('#password');
    var passwordCheck = document.querySelector('#checkedPassword'); 
    
    var v = new ValidateMachine(); 
    v.addValidator(password);
    v.addReverseRegExp({
        "\\s" : "비밀번호에 빈칸을 포함하실 수 없습니다. "
    })
    v.addRegExp({
        "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9]).{8,70}$" : "비밀번호는 8-70자리 영문,숫자,특수문자 조합입니다."
    })

    password = document.querySelector('#password');
    var duplicateV = new ValidateMachine(); 
    duplicateV.addDuplicateValidator(passwordCheck, password); 
</script> 
</html>