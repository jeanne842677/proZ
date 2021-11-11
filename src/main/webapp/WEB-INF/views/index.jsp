<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/index.css">
<title>proz index</title>
</head>
<body>

<div id="wrap">
        
            <header>
                <div class="middleheader-wrap">
                    <div class="leftheader">
                            <div class="project" onclick="location.href='/'">home</div>
                            <div class="templates" onclick="location.href='/project/project-list'">projects</div>
                    </div>
                    <div class="logo"><img src="/resources/img/LOGO0000w.png"></div>
                    
                    <div class="rightheader">
                            <div class="signup" onclick="location.href='/member/join'">sign-up</div>
                            <div class="login" onclick="location.href='/member/login'">login</div>
                    </div>
                    
                </div>
            </header>

                <div id="contents">
                <section id="beforelogin">
                    <div class="mainimg"><img src="/resources/img/index.png"></div>
                    <div class="maintext">
                        <div class="textlogo"><img src="/resources/img/logo-white.png"></div>
                        <div class="des1">Proz is used for better project work.</div>
                        <div class="des2"> click the button to join us.</div>
                        <div class="startbtn" onclick="location.href='/member/login'">start now!</div>
                    </div>
                </section>

            <footer>
                <div class="footleft">
                    <div class="footdes1">project by DragonBall.</div>
                    <div class="footdes2">Lim Ji-young , Kang Min-hyup ,  Gil Ye-jin , Son Eun-bi , Lee Yoo-song , Cho Chae-eun , Choi Yoon-ji</div>
                </div>
                <div class="footright">Email_ lucky007proz@gmail.com</div>

            </footer>
        </div>
    </div>


</body>
</html>