<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Document</title>

<%@ include file="/WEB-INF/views/include/head.jsp"%>


<link type="text/css" rel="stylesheet" href="/resources/css/cash/cash.css?ver=5">
<link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.css">
<script type="text/javascript" src="https://bootswatch.com/_vendor/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="https://bootswatch.com/_vendor/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
<link type="text/css" rel="stylesheet" href="/resources/css/nav.css?ver=7">

<style>
section{
    height: 100%;
    width: 100%;
    overflow-y: auto;
    background: RGB(245, 246, 247);
    }
</style>

</head>
<body>

    <div class="wrap">
       <header>
         <%@ include file="/WEB-INF/views/include/nav/header.jsp"%>
      </header>

      <div class="con">
         <nav>
            <%@ include file="/WEB-INF/views/include/nav/main-nav.jsp"%>
         </nav>
        <section>
            <!--여기서만 작업-->
            <div class="cash-top">
                <div id="title-img"><img src="/resources/img/salary.png"></div>
                <div id="title">결제</div>
            </div>
            <div id="explain">
                <div id="cash-explain">
                    proZ 서비스에 기능, 콘텐츠가 새로 추가됨에 따라 향후 변경될 수 있습니다. 
                </div>
            </div>
            <div id="cash-main">
               <div id="cash">
                <div id="payment-memo" class="sell-item" data-item="1">
                    <div id="memo-icon"><img src="/resources/img/memo.png"></div>
                    <div class="cash-title"  style="color: #041e7d;">멤버 수 추가</div>
                    <div id="content">
                        프로젝트 멤버 수를 100명까지 가능하게 해줍니다.
                    </div>
                    <div class="price">8000￦</div>
                    <button type="button" class="price-btn">결제</button>
                </div>
                <div id="payment-notice" class="sell-item" data-item="2">
                    <div id="bell"><img src="/resources/img/bell.png"></div>
                    <div  class="cash-title"  style="color: rgb(14, 136, 14);">알림 기능</div>
                    <div id="content">
                        proZ에서 알림기능을 이용할 수 있습니다
                    </div>
                    <div class="price">8000￦</div>
                    <button type="button" class="price-btn">결제</button>
                </div>
                <div id="payment-facetime" class="sell-item" data-item="3">
                    <div id="face-icon"><img src="/resources/img/face.png"></div>
                    <div  class="cash-title" style="color: rgb(173, 24, 153);">용량 추가</div>
                    <div id="content">
                    	용량을 무제한으로 업그레이드 시켜줍니다.
                    </div>
                    <div class="price">10000￦</div>
                    <button type="button" class="price-btn">결제</button>
                </div>
                <div id="payment-all" class="sell-item" data-item="4">
                    <div id="all-icon"><img src="/resources/img/all.png"></div>
                    <div  class="cash-title" style="color: #494949;">전체 결제</div>
                    <div id="content">
                       모든 기능을 평생 이용할 수 있습니다.
                    </div>
                    <div class="price">25000￦</div>
                    <button type="button" class="price-btn">결제</button>
                </div>
              
                
        
                

               </div> 






            </div>
         

        </section>
        <aside></aside>
        </div>

    </div>
    


</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript">

	$('.price-btn').on('click',function(){
		let item = $(this).closest('.sell-item').data("item");
		let charge = $(this).siblings(".price").text().slice(0,-1);
		let item_name = $(this).closest('.sell-item').find(".cash-title").text();
		
		console.log(item);
		console.log(charge);
		console.log(item_name);
		console.log(typeof(item_name));
		IMP.init('imp28553074');
		
		IMP.request_pay({
			pg : "inicis",
			pay_method : "card",
			merchant_uid : "merchant_" + new Date().getTime(),
			name : item_name,
			amount : charge,
			buyer_tel : '010-1111-1111',
			buyer_addr : '서울시 강남구',
			buyer_postcode : '123-456'
		}, function(rsp){
			console.log(rsp);
			if(rsp.success){		
				$.ajax({
					url : "http://localhost:9090/cash/" + ${projectIdx},
					method : "POST",
					headers: {"Content-Type": "application/json"},
					dataType : "json",
					data : JSON.stringify({
						"item_name" : item_name
					})
				}).done(function(data){
					alert("결제가 성공적으로 진행되었습니다.");
				})
			}else{
				alert("결제에 실패하였습니다.");
			}
		});
	});
	
	
</script>           

</html>