<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/index.css?ver=1">
<link rel="stylesheet" href="/resources/css/modal/modal.css">
<style type="text/css">

	.clone{
	display: none;
	}

</style>
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
                        <div class="login" ><a href="/member/logout.do">logout</a></div>
                    </div>
                </div>
            </header>

                <div id="tem-contents">
                    
                <div class="temtit">
                    <div class="line"><hr></div>
                    <div class="line2"><hr></div>
                    <div class="thistit">Projects</div>
                    <div class="thisdes">All of these templates are<br>provided for free widthin Proz.
                        <div class="addnew">+ new project</div></div>
                </div>
                
                
			<section id="temsection">
					<div class="tem-wrap">
						<c:if test="${not empty projectList}">
							<c:forEach items="${projectList}" var="project" varStatus="i">
								<div class="con1">
									<div class="conimg">
										<a href="${project.projectIdx}">
											<img src="/resources/img/no-img.png" > 
										</a>
									</div>
									<div class="con1title">
										<div class="projecttit">${project.proName}</div>
										<div class="summary">${project.proDescription}</div>
									</div>
								</div>
							</c:forEach>
						</c:if>
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

	<div class="con1 clone">
		<div class="conimg">
			<a href="${project.projectIdx}"> <img
				src="/resources/img/no-img.png">
			</a>
		</div>
		<div class="con1title">
			<div class="projecttit">${project.proName}</div>
			<div class="summary">${project.proDescription}</div>
		</div>
	</div>

</body>



<script type="text/javascript" src="/resources/js/modal/modal.js"></script>
<script type="text/javascript">
	var newProject = new Modal("새 프로젝트", "새 프로젝트 이름 (15자 이내)");
	var secondModal = new Modal("생성 완료", "새 프로젝트가 생성되었습니다!!");

	
	
	newProject.createInputModal(); //버튼 2개생성 first-button : 저장 second-button : 취소
	newProject.makeModalBtn($(".addnew")) //버튼에 지정
	
	//인풋태그 추가
	let test = $("<div class='modal-popUp-message'>간단한 프로젝트 설명 (20자 이내)</div><div class='modal-popUp-input'><input class='form-control' id='input-summary' type='text' ></div>");
	test.find
	test.appendTo(".content-wrap");
	
	
	
	secondModal.createAlertModal(); //두번쨰모달 생성
	secondModal.makeModalBtn($(".second-button"));
	
	secondModal.setConfirmFnc((e) => {
		
		
		var projectTitle = newProject.getInputValue(); //제목 input value
		var projectSummary = $("#input-summary").val(); // 설명 input value

		let projectData = {
				method : 'POST',
				headers : {
					'Content-Type' : 'application/json; charset=UTF-8'
				},
				body : JSON.stringify({proName : projectTitle, proDescription : projectSummary})
		};
		
		fetch('/project/project-list', projectData)
			.then(response => response.json())
			.then((data) => {
				console.log(data);
			});
		
		if(projectTitle == "") {
			projectTitle = "프로젝트 이름을 설정해주세요";
		}
		

		newProject.modal.find('#input').val('');
		test.find("#input-summary").val('');
		//여기서 데이터 보내기
		
		let clone = $(".clone").clone();
		
		clone.find(".projecttit").html(projectTitle);
		clone.find(".summary").html(projectSummary);
		clone.toggleClass("clone")
		
		clone.appendTo(".tem-wrap");
		
		
		
		});
	

</script>
</html>