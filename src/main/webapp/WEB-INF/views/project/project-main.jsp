<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/nav.css">
<script type="text/javascript" src="/resources/js/nav.js"></script>
<meta charset="UTF-8">
<title>Welcome</title>
<style>

    @font-face {
    font-family: 'NanumSquareRound';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumSquareRound.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'SpoqaHanSansNeo-Medium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/SpoqaHanSansNeo-Medium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}



        html,body{
            background-color: #F5F6F7;
            font-family: 'NanumSquareRound';
            overflow:hidden;
        }
        /* div{
            border:solid 1px;
        } */
        section{
            width:100%;
            min-width:800px;
            height:100%;
            background-color: #F5F6F7;
        }

        #top{
            width:100%;
            height:20%;
            display:flex;
            justify-content: space-around;
            align-items: center;
        }

        #top1{
            width:20%;
            height:80%;
            display:flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            background-color: #fff;
            border-radius: 10px;
            box-shadow : 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);
        }

        #my{
            width: 100%;
            height:50%;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .myprofile{
            width:60px;
            height:60px;
            background-color: RGB(143, 122, 229);
            border-radius: 100px;
            display:flex;
        }

        .myname{
            width:100%;
            height:15%;
            display:flex;
            justify-content: center;
            align-items: center;
            color:#292929;
            font-weight:900;
        }

        .myrole{
            width:100%;
            height:15%;
            display:flex;
            justify-content: center;
            align-items: center;
            color:#888;
            font-size:15px;
            font-weight:900;
        }

        #top2{
            width:70%;
            height:80%;
            display:flex;
            background-color: #fff;
            border-radius: 10px;
            box-shadow : 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);
        }

        #bottom{
            width:100%;
            height:80%;
            display:flex;
        }

        #left{
            width:35%;
            height:100%;
            display:flex;
            flex-direction: column;
            align-items: center;
            justify-content: space-around;
        }

        #left1{
            width:90%;
            height: 40%;
            display:flex;
            flex-direction: column;
            justify-content: space-around;
            
        }

        .clock{
            width:100%;
            height:30%;
            display:flex;
            justify-content: center;
            align-items: center;
            color:#292929;
            font-size: 30px;
            font-family: 'SpoqaHanSansNeo-Medium';
            font-weight: 900;
            border-radius: 10px;
            box-shadow : 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);
            background-color: #fff;
        }

        .weather{
            width:100%;
            height:60%;
            border-radius: 10px;
            box-shadow : 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);
            background-color: #fff;
        }

        #left2{
            width:90%;
            height: 50%;
            display:flex;
            background-color:#fff;
            border-radius: 10px;
            box-shadow : 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);
        }

        #right{
            width:70%;
            height:100%;
            display:flex;
            flex-direction: column;
            align-items: center;
            justify-content: space-around;
        }

        #right1{
            width:90%;
            height: 45%;
            display:flex;
            justify-content: space-between;
            
        }

        .board{
            width:45%;
            height:100%;
            background-color:#fff;
            border-radius: 10px;
            box-shadow : 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);
            display:flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .boardarea{
            width:96%;
            height:10%;
            display:flex;
            justify-content: flex-end;
            align-items: center;
            color:#bbb;  
            font-weight: 900;  
            cursor:pointer;
        }

        .boardwrap{
            width:98%;
            height:85%;
            display: flex;
            flex-direction: column;
            justify-content: space-evenly;
            align-items: center;

        }

        .boardcon{
            width:95%;
            height:25%;
            border-radius: 10px;
            border:solid 1px #ccc;
        }

        

        .comment{
            width:45%;
            height:100%;
            background-color:#fff;
            border-radius: 10px;
            box-shadow : 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .commentarea{
            width:96%;
            height:10%;
            display:flex;
            justify-content: flex-end;
            align-items: center;
            color:#bbb;  
            font-weight: 900; 
            cursor:pointer;
        }

        .commentwrap{
            width:98%;
            height:85%;
            display:flex;
            flex-direction: column;
            justify-content: space-evenly;
            align-items: center;
        }

        .commentcon{
            width:95%;
            height:15%;
            border-radius: 10px;
            border:solid 1px #ccc;
        }

        

        #right2{
            width:90%;
            height: 45%;
            display:flex;
            background-color:#fff;
            border-radius: 10px;
            box-shadow : 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);
            justify-content: center;
            flex-direction: column;
            align-items: center;
        }

        .memoarea{
            width:96%;
            height:10%;
            display:flex;
            justify-content: flex-end;
            align-items: center;
            color:#bbb;  
            font-weight: 900; 
            cursor:pointer;
        }

        .memocon{
            width:98%;
            height:85%;
        }

        .memowrap{
            width:100%;
            height:100%;
            display:flex;
            align-items: center;
            justify-content: space-around;
            overflow-x: auto;       
            overflow-y:hidden;
            flex-wrap: wrap;
            flex-direction: column;
        }

        .memowrap::-webkit-scrollbar {
            width: 6px;
            height: 6px;
            
        }
        .memowrap::-webkit-scrollbar-thumb {
            background-color: RGB(143, 122, 229);
            border-radius: 10px;
        }
        .memowrap::-webkit-scrollbar-track {
            background-color: #ddd;
            border-radius: 10px;
        }

        .memo{
            width:230px;
            height: 230px;
            margin:10px;
            background-color: #fff3cd;
            box-shadow : 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);
        }

    </style>
</head>
<body>

    <div class="wrap">
        <header><%@ include file="/WEB-INF/views/include/nav/header.jsp" %></header>

        <div class="con">
        <nav><%@ include file="/WEB-INF/views/include/nav/main-nav.jsp" %></nav>
        <section>
            <!--여기서만 작업-->
            <div id="top">
                <div id="top1">
                    <div id="my"><div class="myprofile"></div></div>
                    <div class="myname">예진ㅇ_<</div>
                    <div class="myrole">노예</div>
                </div>
                <div id="top2">팀정보</div>
            </div>
            <div id="bottom">
                <div id="left">
                    <div id="left1">
                        <div class="clock">
                            <p id="usingFunction">2021. 11. 2. 오전 1:55:27</p>
                        </div>
                        <div class="weather">
                            
                        </div>     
                    </div>
                    
                    <div id="left2">캘린더</div>
                </div>
                <div id="right">
                    <div id="right1">
                        <div class="board">
                            <div class="boardarea" onclick="location.href='#';">board > </div>
                            <div class="boardwrap">
                                <div class="boardcon"></div>
                                <div class="boardcon"></div>
                                <div class="boardcon"></div>
                            </div>
                        </div>
                        <div class="comment">
                            <div class="commentarea" onclick="location.href='#';">comment > </div>
                            <div class="commentwrap">
                                <div class="commentcon"></div>
                                <div class="commentcon"></div>
                                <div class="commentcon"></div>
                                <div class="commentcon"></div>
                                <div class="commentcon"></div>
                            </div>
                        </div>
                    </div>
                    <div id="right2">
                        <div class="memoarea" onclick="location.href='#';">memo > </div>
                        <div class="memocon">
                            <div class="memowrap">
                                <div class="memo"></div>
                                <div class="memo"></div>
                                <div class="memo"></div>
                                <div class="memo"></div>
                                <div class="memo"></div>
                                <div class="memo"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            



        </section>
        <aside><%@ include file="/WEB-INF/views/include/nav/aside.jsp" %></aside>
        </div>

    </div>
    
    <script type="text/javascript">
    //시계
    function locale (){ 	 return new Date().toLocaleString(); 	 } 
    document.getElementById( 'usingFunction' ).innerHTML = locale(); 
    setInterval ( function() { document.getElementById("usingFunction").innerHTML = locale(); } , 1000 );
    </script>
</body>
</html>