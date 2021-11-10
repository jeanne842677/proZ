<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/index.css">
<title>my projects</title>
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

                <div id="tem-contents">
                    
                <div class="temtit">
                    <div class="line"><hr></div>
                    <div class="line2"><hr></div>
                    <div class="thistit">Projects</div>
                    <div class="thisdes">All of these templates are<br>provided for free widthin Proz.
                        <div class="addnew" onclick="location.href='#'">+ new project</div></div>
                    
                </div>
                <section id="temsection">
                    <div class="tem-wrap">
                        <div class="con1">
                            <div class="conimg"><img src="/resources/img/dragonball.jpg"></div>
                            <div class="con1title">
                                <div class="projecttit">Proz</div>
                                <div class="teamtit">드래곤볼z</div>
                            </div>
                        </div>

                        <div class="con1">
                            <div class="conimg"><img src="/resources/img/dragonball.jpg"></div>
                            <div class="con1title">
                                <div class="projecttit">Proz</div>
                                <div class="teamtit">드래곤볼z</div>
                            </div>
                        </div>

                        <div class="con1">
                            <div class="conimg"><img src="/resources/img/dragonball.jpg"></div>
                            <div class="con1title">
                                <div class="projecttit">Proz</div>
                                <div class="teamtit">드래곤볼z</div>
                            </div>
                        </div>

                        <div class="con1">
                            <div class="conimg"><img src="/resources/img/dragonball.jpg"></div>
                            <div class="con1title">
                                <div class="projecttit">Proz</div>
                                <div class="teamtit">드래곤볼z</div>
                            </div>
                        </div>

                        
                        
                        


                        
                    </div>
                    <div class="design-box1"></div>
                    <div class="design-box2"></div>
                    <!-- <div class="design-box3"></div> -->
                </section>
                
            
            <footer>
                <div class="footleft">
                    <div class="footdes1">project by DragonBall.</div>
                    <div class="footdes2">Lim Ji-young , Kang Min-hyeop ,  Gil Ye-jin , Son Eun-bi , Lee Yoo-song , Cho Chae-eun , Choi Yoon-ji</div>
                </div>
                <div class="footright">Email_ lucky007proz@gmail.com</div>

            </footer>
        </div>
    </div>

</body>
</html>