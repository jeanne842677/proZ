<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<link type="text/css" rel="stylesheet" href="/resources/css/nav.css?ver=6">
<script type="text/javascript" src="https://bootswatch.com/_vendor/jquery/dist/jquery.min.js"></script>
<script type="text/javascript"src="https://bootswatch.com/_vendor/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
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
            <div class="cash-top">
                <div id="title-img"><img src="resource/img/salary.png"></div>
                <div id="title">결제</div>
            </div>

            <div id="cash-main">

               <div id="cash">
                <div id="payment-memo">
                    <div id="memo-icon"><img src="resource/img/memo.png"></div>
                    <div id="cash-title"  style="color: #041e7d;">메모장 기능</div>
                    <div id="content">
                        메모를 평생 이용할 수 있습니다
                    </div>
                    <div id="price">8000\</div>
                    <button type="button" id="price-btn">결제</button>
                </div>
                <div id="payment-notice">
                    <div id="bell"><img src="resource/img/bell.png"></div>
                    <div  id="cash-title"  style="color: rgb(14, 136, 14);">알림 기능</div>
                    <div id="content">
                        proZ에서 알림기능을 이용할 수 있습니다
                    </div>
                    <div id="price">8000\</div>
                    <button type="button" id="price-btn">결제</button>
                </div>
                <div id="payment-facetime">
                    <div id="face-icon"><img src="resource/img/face.png"></div>
                    <div  id="cash-title" style="color: rgb(173, 24, 153);">여러개 영상 회의</div>
                    <div id="content">
                        영상 회의를 평생 이용할 수 있습니다       
                    </div>
                    <div id="price">10000\</div>
                    <button type="button" id="price-btn">결제</button>
                </div>
                <div id="payment-all">
                    <div id="all-icon"><img src="resource/img/all.png"></div>
                    <div  id="cash-title" style="color: #494949;">전체 결제</div>
                    <div id="content">
                       모든 기능을 평생 이용할 수 있습니다
                    </div>
                    <div id="price">20000\</div>
                    <button type="button" id="price-btn">결제</button>
                </div>
              
                
        
                

               </div> 






            </div>
         

        </section>
        <aside></aside>
        </div>

    </div>
    


</body>
</html>