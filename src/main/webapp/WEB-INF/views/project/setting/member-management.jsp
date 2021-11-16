<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">


<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<link type="text/css" rel="stylesheet" href="/resources/css/nav.css?ver=4">
<link type="text/css" rel="stylesheet" href="/resources/css/modal/modal.css" />
<link type="text/css" rel="stylesheet"
	href="/resources/css/setting/member-management.css?ver=2" />
<script type="text/javascript" src="/resources/js/modal/modal.js?ver=2"></script>
<title>멤버 설정</title>
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
				<div class="content-wrap">
					<div class="member-manage-top">
						<div class="title-img">
							<img src="/resources/img/premium-icon-people-5322431.png">
						</div>
						<div class="title">멤버관리</div>
					</div>
					<div class="member-manage-main">
						<div class="invite-link">
							<div>
								<p class="category-title">초대링크</p>
								<p style="color: slategray;">초대 링크를 공유하여 워크스페이스에 사람들을 초대하세요.</p>
								<div class="form-group">
									<input class="form-control" id="link-input" type="text"
										value="http://localhost:9090/project/invite/${project.inviteCode}"
										readonly>
									<div class="button-wrapper">
										<button type="button" class="btn btn-gradi1 copy-url">링크
											복사</button>
										<button type="button" id="link-reset"
											class="btn btn-secondary">링크 재설정</button>
									</div>
								</div>
							</div>
						</div>



						<hr style="margin: 50px 0 50px 0;">


						<div class="invite-wrap">
							<p class="category-title">프로젝트 초대하기</p>
							<div class="form-group">
								<input class="form-control" id="invite-member-input"
									type="email" placeholder="이메일로 초대하기">
								<div class="button-wrapper">
									<button type="button" id="invite-btn"
										class="btn btn-gradi1 insert-member">멤버 추가</button>

								</div>
							</div>
						</div>



						<div class="member-list-wrapper">
							<div class="search-member-wrapper">
								<div class="search-member-content">
									<div
										style="font-weight: bolder; margin: 2px 10px 0 10px; font-size: 20px;">멤버</div>
									<input class="form-control" id="search-member" type="text"
										placeholder="검색하기"> <a href=""><img
										class="search-icon" src="/resources/img/search.png"></a>
								</div>

								<div class="category-member">

									<div id="role-text">역할</div>
									<div id="exile-text">강제추방</div>
								</div>
							</div>


							<!--시작-->

							<c:forEach items="${projectMember}" var="member">
								<div class="member-wrap" id="${ member.pmIdx }">
									<hr style="margin: 20px 0 20px 0;">

									<div class="member">
										<div class="member-profile-wrapper">
											<img class="search-icon"
												src="/resources/img/profile-user.png">
											<div class="member-profile">
												<p class="user-name">${ member.nickname }</p>
												<p class="user-email">${member.email }</p>
											</div>
										</div>
										<div style="width: 200px;"></div>
										<div class="category-member-info">
										
										   <c:if test="${member.isOk == 1 }">
											<div class="role-wrap">
												<select class="role">
													<c:forEach items="${projectRole}" var="role">
														<option id="${role.authIdx}"
															<c:if test="${ member.authIdx == role.authIdx}"> selected</c:if>>${role.authName}</option>
													</c:forEach>
												</select>
											</div>

											
											
									
											<button type="button" class="btn btn-primary auth-save">저장</button>
											<button type="button" class="btn btn btn-danger exile">추방하기</button>
											</c:if>
											<c:if test="${member.isOk == 0 }">
											<div class="role-wrap">
												<select class="role">
														<option selected disabled>수락대기중</option>
												
												</select>
											</div>
											
											
											<button type="button" class="btn btn-primary auth-save disabled">저장</button>
											<button type="button" class="btn btn btn-danger invite-cancel">초대취소</button>
											</c:if>

										</div>




									</div>
								</div>


							</c:forEach>

							<!--끝-->
						</div>
					</div>


				</div>


			</section>
			<aside>


				<%@ include file="/WEB-INF/views/include/nav/aside.jsp"%>
			</aside>
		</div>

	</div>



	<script type="text/javascript"
		src="/resources/js/setting/member-management.js?ver=3"></script>
	<script type="text/javascript">
	
	$(function() {
		
		
		//버튼 비활성화 함수
		let btnOff = function(btn) {
			btn.css('opacity' , '0.7');
			btn.addClass('disabled');
		} 
		
		//버튼 활성화
		let btnOn = function(btn) {
			btn.css('opacity' , '1');
			btn.removeClass('disabled');	
		} 
		
		
		//모달들 모아놓음
		let noMemberModal = new Modal("친구요청 실패" , "사용자 이메일을 확인해주세요.");
		noMemberModal.createAlertModal();
		let memberExistsModal = new Modal("친구요청 실패", "이미 존재하는 사용자입니다.")
		memberExistsModal.createAlertModal();
		let invateCompleteModal = new Modal("친구 요청 성공" , "사용자에게 초대 이메일이 발송되었습니다.");
		invateCompleteModal.createAlertModal();
		let inviteCancelModal = new Modal("초대 취소" , "초대가 취소되었습니다.");
		inviteCancelModal.createAlertModal();
		
		let modal = new Modal("링크 재설정" , "확인 클릭시 링크가 재설정 됩니다. <br> 링크를 재설정하시겠습니까?");
	 	modal.createTwoButtonModal("재설정" ,"취소");
	 	modal.makeModalBtn($('#link-reset'));
	 	$('.first-button').css("background" , "RGB(235, 87, 87)")
	 	$('.first-button').css("borderColor" , "RGB(235, 87, 87)")
	 	
	 	
	 	modal.modal.find('.first-button').on('click' , function() {
			
			fetch("/project/reset-invite-link" , {
				
				method : "POST",
				headers :  {"Content-type" : "application/json; charset=UTF-8"},
				body : JSON.stringify({projectIdx : "${project.projectIdx}"})
				
			}).then(res => res.text())
			.then(text => {
				$('#link-input').attr('value' , "https://localhost:9090/project/invite/"+text);
			})
			
		})
		
		btnOff($('#invite-btn'));
		
		$('#invite-member-input').on("change keyup paste", function() {
		    if($(this).val()) {

		    	btnOn($('#invite-btn'));
		    	
		    }else {
		    	btnOff($('#invite-btn'));
		    }
			
		});
		
		
		//이메일로 추가하기 버튼
		$('#invite-btn').on('click' , function() {
			
			let memberEmail = $('#invite-member-input').val();
			fetch("/project/invite-member" ,{
				
				method : "POST",
				headers :  {"Content-type" : "application/json; charset=UTF-8"},
				body : JSON.stringify({email : memberEmail , projectIdx: "${project.projectIdx}"})
				
			}).then(res => res.text())
			.then(text => {
				alert(text);

				$('.invite-msg').remove();
				if(text=="no-member") {
					
					noMemberModal.viewModal();
					
				}else if(text=="member-exists") {
					
					memberExistsModal.viewModal();
				
				}else {
						let member = JSON.parse(text);
						console.dir(member);
						$('#invite-member-input').val('');
				    	btnOff($('#invite-btn'));
						let inviteMsg = $('<div class="invite-msg">멤버가 초대되었습니다!</div>');
						$('.invite-wrap').append(inviteMsg);
						invateCompleteModal.viewModal();
						
						let memberDiv = $('<div class="member-wrap"> <hr style="margin: 20px 0 20px 0;"> <div class="member" > <div class="member-profile-wrapper"> <img class="search-icon" src="/resources/img/profile-user.png"> <div class="member-profile"> <p class="user-name">랄라</p> <p class="user-email">zo4870ddd@naver.com</p> </div> </div> <div style="width: 200px;"> </div> <div class="category-member-info"> <div class="role-wrap"> <select class="role"> <option selected disabled>수락대기중</option> </select> </div> <button type="button" class="btn btn-primary auth-save">저장</button> <button type="button" class="btn btn btn-danger invite-cancel">초대취소</button> </div> </div> </div>');
						memberDiv.find('.user-name').text(member.nickname);
						memberDiv.find('.user-email').text(member.email);
						memberDiv.find('.auth-save').addClass('disabled')
						$('.member-list-wrapper').append(memberDiv);
						
						memberDiv.find('.invite-cancel').on('click' , function() {
							fetch("/project/invite-cancel-by-userIdx",{
								
								method : "POST",
								headers :  {"Content-type" : "application/json; charset=UTF-8"},
								body : JSON.stringify({userIdx : member.userIdx,
									projectIdx : "${project.projectIdx}"})
								
							}).then(res=> {
								memberDiv.remove();
							})
							
							
						});
					
					
				}
				
				
				
			})
			
			
			
			
		});
		
		
		
		//저장버튼 눌렀을떄
		$(".auth-save").each(function() {
			
			
			let member= $(this).parents('.member-wrap');
			let pmIdx = member.attr('id');
		
			$(this).on('click' , function() {
				
			let authIdx = member.find('.role option:selected').attr('id');
			alert(pmIdx);
			fetch('/project/change-member-auth' , {
				
				method : "POST",
				headers :  {"Content-type" : "application/json; charset=UTF-8"},
				body : JSON.stringify({
					pmIdx : pmIdx,
					authIdx : authIdx
					})
				
			}).then(res=>res.text())
			.then(text=>{
				alert("업뎃 완료");
				
			})
			
			
				
			})
			
			
			
			
			
			
			
			
		})
		
		
	})
	
	


	
	/* 프로젝트 제거 모달창 */
	
	$('.member-wrap').each(function() {
		var nickname = $(this).find('.user-name').text();
		var firstSaveModal = new Modal("멤버 추방", nickname + "님을 추방하시겠습니까?");
		firstSaveModal.createTwoButtonModal("추방","취소");
		firstSaveModal.makeModalBtn($(this).find('.exile'));
		firstSaveModal.modal.find(".modal-popUp-message").css("color","red"); 
		
		
		var secondSaveModal = new Modal("멤버 추방",nickname +"님이 추방되었습니다");
		secondSaveModal.createAlertModal();
		secondSaveModal.makeModalBtn(firstSaveModal.modal.find('.first-button'));
		
		let member = $(this);
		let pmIdx = $(this).attr('id');
		//추방 확인 버튼 클릭시
		firstSaveModal.modal.find('.first-button').on('click' , function() {
			
			fetch("/project/exile" , {
				
				method : "POST",
				headers :  {"Content-type" : "application/json; charset=UTF-8"},
				body : JSON.stringify({
					pmIdx : pmIdx
					})
				
			}).then(res=>res.text())
			.then(text => {
				
				if(text="complete") {
					
					member.remove();
					
				}
				
				
			})
		})

		
	});
	
	$('.invite-cancel').each(function() {
		
		$(this).on('click' , function() {
			
			let memberWrap = $(this).parents('.member-wrap')
			let pmIdx = memberWrap.attr('id');
			
			alert(pmIdx);
			
		 	fetch("/project/invite-cancel" , {
				
				method : "POST",
				headers :  {"Content-type" : "application/json; charset=UTF-8"},
				body : JSON.stringify({
					pmIdx : pmIdx
					})
				
			}).then(res=>res.text())
			.then(text => {
				
				if(text=="complete") {
					
					memberWrap.remove();
					
				}
				
				
				
				
			}) 
			
		})
		
		
	})
	
	
	
	
	

	</script>
</body>




</html>