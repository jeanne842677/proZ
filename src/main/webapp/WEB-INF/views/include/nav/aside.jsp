<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                 <div class="teams1">
                    <div class="teamstit">프로젝트 참여 멤버</div>
                    <div id="onoff">
                        <div class="onlinetit">온라인</div>
                        <div class="online">
                                <ul id="sortable">
                                    <li class="member"><i class="fas fa-user-circle" style="color:#ffc107"></i>3조_길예진</li>
                                    <li class="member"><i class="fas fa-user-circle" style="color:#17a2b8"></i>3조_길예진</li>
                                    <li class="member"><i class="fas fa-user-circle" style="color:#dc3538"></i>3조_길예진</li>
                                </ul>
                            
                        </div>

                        <div class="offlinetit">오프라인</div>
                        <div class="offline">
                                <ul id="sortable2">
                                <c:forEach items="${ projectMember }" var="pm">
                                    <li class="member">
                                    	<i class="fas fa-user-circle" style="color:#aaa;"></i>
                                    	${ pm.nickname }
                                    	
                                    </li>
                                </c:forEach>
                           
                                </ul>
                            
                        
                   		 </div>
                </div>
                </div>
                
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
                <script>


                var stompClient = Stomp.over(new SockJS("/online"));



                	//연결
                	stompClient.connect({}, function (frame) {
                		
                	    console.log("connected: " + frame);

                		 let room = $('#room').val();
                		 stompClient.subscribe('/projectIdx/online/'+ 999998,function(chat) {
                				
                				var content = JSON.parse(chat.body);	
                				alert(content);
                		});

                	});
                	
                	
                
                
                </script>
