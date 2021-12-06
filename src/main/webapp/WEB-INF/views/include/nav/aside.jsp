<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="teams1">
	<div class="teamstit">프로젝트 참여 멤버</div>
	<div id="onoff">
		<div class="onlinetit">온라인</div>
		<div class="online">
			<ul id="sortable">
				<!-- <li class="member"><i class="fas fa-user-circle" style="color:#ffc107"></i>3조_길예진</li>
                                    <li class="member"><i class="fas fa-user-circle" style="color:#17a2b8"></i>3조_길예진</li>
                                    <li class="member"><i class="fas fa-user-circle" style="color:#dc3538"></i>3조_길예진</li>
 -->
			</ul>

		</div>

		<div class="offlinetit">오프라인</div>
		<div class="offline">
			<ul id="sortable2">
				<c:forEach items="${ projectMemberList }" var="pm">
					<li class="member" id="${pm.userIdx}" data-auth-idx="${pm.authIdx}"
						data-auth-name="${pm.authName}" data-git="${ pm.git }"
						style="color:${ pm.profileColor }"><div class="onoffprofile"></div>
						${ pm.nickname }</li>
				</c:forEach>

			</ul>


		</div>
	</div>
</div>

<div class="member-info"
	style="width: 300px; height: 200px; z-index: 1000; position: absolute; background-color: white; border-radius: 5px; box-shadow: 0 3px 6px rgba(0, 0, 0, 0.5); display: none; flex-direction: column; align-items: center;">
	<div class="profile-info"
		style="width: 100%; height: 30%; background-color: red; border-radius: 5px 5px 0 0;">

	</div>
	<div class="profile-info-img"
		style="width: 60px; height: 60px; border-radius: 50px; background-color: blue; position: relative; top: -30px"></div>
	<div class="profile-info-content"
		style="display: flex; flex-direction: column; align-items: center; position: relative; top: -20px">
		<div class="profile-info-name" style="font-size: 20px">임지영</div>
		<div class="profile-info-auth">권한</div>
		<input class="profile-info-git" value="없음" disabled="disabled">
	</div>

</div>

<div class="alert-popup"
	style="width: 400px; height: 300px; z-index: 4; position: absolute; display: none; background-color: white; border-radius: 5px; box-shadow: 0 3px 6px rgba(0, 0, 0, 0.5); right: 8px; top: 5px; flex-direction: column; align-items: center; overflow: auto;">
	<div class="alarm-text"
		style="width: 95%; margin-top: 5px; height: 30px; border-bottom: solid gray thin; display: flex">
		알림내역</div>
	<c:set var="length" value="0"> </c:set>
	<c:forEach items="${alarmList }" var="al" varStatus="status">
		<c:if test="${ al.isLook == 0 }"> 
			<c:set var="length" value="${ length+1 }"></c:set>
		</c:if>
		<div class="alarm" 
			style="width: 95%; height: 100px; border-bottom: solid gray thin; display: flex;
			<c:if test="${ al.isLook == 1 }"> color:gray; </c:if>
			">
			<div class="alarm-content">
				<b>${al.alType}</b>
				<div class="al-content">${al.alContent }</div>
			</div>
		</div>
	</c:forEach>

</div>



<script>

	$('.alram').on('click' , function() {
		$('.alert-popup').css('display' , 'flex');
		
	})


	$('aside').css('position', 'relative');

	$('.member').each(function() {
		
		$(this).on('click' , function() {
			
			let mem = $(this);
			var divX = mem.offset().left;
			var divY = mem.offset().top-60;
			
			
			$('.member-info').css('left' , '-310px')
			.css('top' , divY).css('display' , 'flex')
			
			$('.profile-info')
			.css('backgroundColor' , mem.css('color') );
			
			$('.profile-info-name').text( mem.text() ).css('color' , mem.css('color'))
			$('.profile-info-auth').text( mem.data('auth-name'))
			
			let git = mem.data('git') 
			if(git) {
			$('.profile-info-git').val(mem.data('git'));
				
			}else {
				
				
			$('.profile-info-git').val('없음');
			}
			
		})
		
	})
	
	
	$('body').on('click', function(e){
    
		
	let b = $(e.target);
    bParents = b.parents('.member-info').attr("class");
    var popUp = $('.member-info').attr("class");
    var teams = $('.teams1');
 
    
    if( b.attr("class") == popUp || bParents == popUp || $($(b).parents('.teams1'))[0] == teams[0]) {
    	
    }else {
    
       $('.member-info').css('display' , 'none');
    
    	
    }
    
    if(b.attr("class")=='alert-popup' || $($(b).parents('.alram')).attr('class')=='alram') {
    	
    }else {
    	
    	$('.alert-popup').css('display' , 'none');
    	$('.new').text("0");
		
		$('.alarm').css('color' , 'gray');
    }
    
  
    
	});

	
	



</script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script>
	var stompClient;

	let con = function() {

		stompClient = Stomp.over(new SockJS("/ws-stomp"));

		stompClient.connect({}, function(frame) {

			stompClient.subscribe('/online/project/${project.projectIdx}',
					function(chat) {

						var content = JSON.parse(chat.body);
						console.dir(content);
						
						
						if(content.status=="online") {
							
							content.members.forEach(function(e) {
								var userIdx = e.userIdx;
								let user = $('#' + userIdx);
								$('.online ul').append(user);

							})
	 
							
						}else if(content.status=="offline") {
							userIdx = content.member.userIdx;

							let user = $('#' + userIdx);

							$('.offline ul').append(user);
						}else if(content.status="alarm") {
							
							let alr = content.alarm;
							
							if(alr.userIdx == "${authentication.userIdx}") {

								let alarmDiv = $('<div class="alarm" style="width:95% ; height: 50px; border-bottom: solid gray thin; display: flex"><div class="alarm-content"><b></b><div class="al-content"></div></div></div>')
								alarmDiv.find('b').text(alr.alType);
								alarmDiv.find('.al-content').text(alr.alContent);
								$(".alarm-text").after(alarmDiv);
								let newInt = parseInt($('.new').text());
								$('.new').text(newInt+1);
								
								
							}
							
							
							
							
						}
				
					 	

			});
			
				stompClient.send("/app/project/${project.projectIdx}", {}, JSON
						.stringify({
							userIdx : "${authentication.userIdx}",
							nickname : "${authentication.nickname}"
						}));
				
				
	

	

	

		});

	}

	con();
	
	
	
	

	function disconnect() {
		if (stompClient !== null) {
			stompClient.send("/app/remove/${project.projectIdx}", {}, JSON
					.stringify({
						userIdx : "${authentication.userIdx}",
						nickname : "${authentication.nickname}"
					}));
			
			
			
			stompClient.disconnect();
		}
	}
	
	
	
	window.addEventListener('beforeunload', (e) => {
		disconnect();
	})
	
	
	$('.new').text("${length}");
	
	$('.bell-btn').on('click' , function() {
		
	 	
    	fetch("/alarm/update/look" , {
			
			method : "POST",
			headers :  {"Content-type" : "application/json; charset=UTF-8"},
			body : JSON.stringify({
				userIdx : "${authentication.userIdx}"
			
				})
			
		}).then(res=> {
			
			
			
		})
    	
		
		
		
	})
	
	
</script>
