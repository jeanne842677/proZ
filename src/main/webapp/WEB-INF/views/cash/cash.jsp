<%@ page language='java' contentType='text/html; charset=UTF-8'
   pageEncoding='UTF-8'%>
<!DOCTYPE html>
<html lang='en'>
<head>
<meta charset='UTF-8'>
<title>Document</title>

<%@ include file='/WEB-INF/views/include/head.jsp'%>


<link type='text/css' rel='stylesheet' href='/resources/css/cash/cash.css?ver=5'>
<link type='text/css' rel='stylesheet' href='/resources/css/bootstrap.css'>
<script type='text/javascript' src='https://bootswatch.com/_vendor/jquery/dist/jquery.min.js'></script>
<script type='text/javascript' src='https://bootswatch.com/_vendor/bootstrap/dist/js/bootstrap.bundle.min.js'></script>
<link type='text/css' rel='stylesheet' href='/resources/css/nav.css?ver=7'>
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

    <div class='wrap'>
       <header>
         <%@ include file='/WEB-INF/views/include/nav/header.jsp'%>
      </header>

      <div class='con'>
         <nav>
            <%@ include file='/WEB-INF/views/include/nav/main-nav.jsp'%>
         </nav>
        <section>
            <!--여기서만 작업-->
            <div class='cash-top'>
                <div id='title-img'><img src='/resources/img/salary.png'></div>
                <div id='title'>결제</div>
            </div>
            
            <div id='explain'>
                <div id='cash-explain'>
                    proZ 서비스의 콘텐츠가 추가됨에 따라 향후 변경될 수 있습니다.<br>
                    모든 추가 기능은 결제 후 한 달 동안 이용 가능 합니다.
                </div>
                
            </div>
             <div class="title-hr">
                            <hr>
                        </div>
            <div id='cash-main'>
               <div id='cash'>
                <div id='payment-memo' class='sell-item' data-item='1'>
                    <div id='memo-icon'><img src='/resources/img/memo.png'></div>
                    <div class='cash-title'  style='color: #041e7d;'>멤버 수 추가</div>
                    <div id='content'>
                        멤버 수를 100명까지<br> 가능하게 해줍니다.
                    </div>
                    <div class='price'>5000￦</div>
                    <c:choose>
                    	<c:when test="${empty memberFunction && empty allFunction}">
                    		<button type='button' class='price-btn'>결제</button>
                    	</c:when>
                    	<c:when test="${not empty memberFunction && empty allFunction}"> 
                    		 <button type='button' class='price-btn' disabled='disabled'>결제완료</button>
                   	 		 <div class="expiration-date">만료일<br>${memberFunction.expirationDate}</div>
                    	</c:when>
                    	<c:otherwise>
                    		<button type='button' class='price-btn' disabled='disabled'>결제불가</button>
                    	</c:otherwise>
                    </c:choose>     
                </div>
                <div id='payment-notice' class='sell-item' data-item='2'>
                    <div id='bell'><img src='/resources/img/list.png'></div>
                    <div  class='cash-title'  style='color: rgb(14, 136, 14);'>워크스페이스 추가</div>
                    <div id='content'>
                        워크스페이스를 50개까지 생성 할 수 있습니다
                    </div>
                    <div class='price'>5000￦</div>
                    <c:if test="${empty workspaceFunction && empty allFunction}">
                    <button type='button' class='price-btn'>결제</button>
                    </c:if>
                    <c:if test="${not empty workspaceFunction && empty allFunction}"> 
                    	<button type='button' class='price-btn' disabled='disabled'>결제완료</button>
                    	<div class="expiration-date">만료일<br>${workspaceFunction.expirationDate}</div>
                    </c:if>
                     <c:if test="${not empty allFunction}">
                   	 	<button type='button' class='price-btn' disabled='disabled'>결제불가</button>
                   	 </c:if>
                </div>
                <div id='payment-facetime' class='sell-item' data-item='3'>
                    <div id='face-icon'><img src='/resources/img/chat.png'></div>
                    <div  class='cash-title' style='color: rgb(173, 24, 153);'>채팅 기능</div>
                    <div id='content'>
                    	워크스페이스에 채팅이<br> 추가됩니다.
                    </div>
                    <div class='price'>3000￦</div>
                    <c:if test="${empty chatFunction && empty allFunction}">
                    <button type='button' class='price-btn'>결제</button>
                    </c:if>
                    <c:if test="${not empty chatFunction && empty allFunction}"> 
                    	<button type='button' class='price-btn' disabled='disabled'>결제완료</button>
                    	<div class="expiration-date">만료일<br>${chatFunction.expirationDate}</div>
                    </c:if>
                     <c:if test="${not empty allFunction}">
                   	 	<button type='button' class='price-btn' disabled='disabled'>결제불가</button>
                   	 </c:if>
                </div>
                <div id='payment-all' class='sell-item' data-item='4'>
                    <div id='all-icon'><img src='/resources/img/all.png'></div>
                    <div  class='cash-title' style='color: #494949;'>전체 결제</div>
                    <div id='content'>
                       앞의 세 기능을 모두 이용할 수 있습니다.
                    </div>
                    <div class='price'>12000￦</div>
                    <c:choose>
                    	<c:when test="${empty allFunction && empty memberFunction && empty workspaceFunction && empty chatFunction}">
                    		<button type='button' class='price-btn'>결제</button>
                    	</c:when>
                    	<c:when test="${empty allFunction && (not empty memberFunction || not empty workspaceFunction || not empty chatFunction)}">
                    		<button type='button' class='price-btn'>결제</button>
                    		<div class="expiration-date">* 이미 구매한 상품이<br>존재합니다.</div>
                    	</c:when>
                    	<c:otherwise>
                    		<button type='button' class='price-btn' disabled='disabled'>결제완료</button>
                    		<div class="expiration-date">만료일<br>${allFunction.expirationDate}</div>
                    	</c:otherwise>
                    </c:choose>
                   
                     
                </div>
               </div> 
            </div>
        </section>
        <aside><%@ include file="/WEB-INF/views/include/nav/aside.jsp" %></aside>
        </div>

    </div>
    


</body>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type='text/javascript' src='https://service.iamport.kr/js/iamport.payment-1.1.5.js'></script>
<script type='text/javascript'>

	$('.price-btn').on('click',function(){
		
		let item = $(this).closest('.sell-item').data('item');
		let charge = $(this).siblings('.price').text().slice(0,-1);
		let item_name = $(this).closest('.sell-item').find('.cash-title').text();
		
		IMP.init('imp28553074');
		
		IMP.request_pay({
			pg : 'inicis',
			pay_method : 'card',
			merchant_uid : 'merchant_' + ${projectIdx} + item_name,
			name : item_name,
			amount : 100,
			buyer_name : '닉네임 : ' + '${buyerInfo.nickname}' + ', 멤버 번호 : ' + '${buyerInfo.pmIdx}'
		}, function(rsp){
			console.log(rsp);
			if(rsp.success){
				alert("결제가 성공적으로 완료되었습니다.");
				$.ajax({
					url : 'http://localhost:9090/cash/' + ${projectIdx},
					method : 'POST',
					headers: {'Content-Type': 'application/json'},
					dataType : "json",
					data : JSON.stringify({
						'item' : item_name
					})	
				});
				location.reload();
			}else{
				alert(rsp.error_msg);
			}
			
		});
	});
	
	
	
	
	
</script>           

</html>