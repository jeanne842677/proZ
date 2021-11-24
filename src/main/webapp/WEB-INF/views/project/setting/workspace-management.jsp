<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>

<link type="text/css" rel="stylesheet" href="/resources/css/nav.css?ver=6">
<link type="text/css" rel="stylesheet" href="/resources/css/modal/modal.css" />
 <link type="text/css" rel="stylesheet" href="/resources/css/setting/workspace-management.css">
<script type="text/javascript" src="/resources/js/modal/modal.js"></script>
<script type="text/javascript" src="https://bootswatch.com/_vendor/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript"src="https://bootswatch.com/_vendor/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script> 


</head>

<body>

    <div class="wrap">
        <header><%@ include file="/WEB-INF/views/include/nav/header.jsp" %> </header>

        <div class="con">
            <nav> <%@ include file="/WEB-INF/views/include/nav/setting-nav.jsp" %> </nav>
            <section>
                <!--여기서만 작업-->
                <div class="need-section-padding">
                    <!-- top -->
                    <div class="member-manage-top">
                        <div class="top-title">
                            <div class="title-img"><img src="/resources/img/maintenance.png"></div>
                            <div class="title">워크스페이스 관리</div>
                        </div>
                        <div class="title-hr">
                            <hr>
                        </div>
                        <div class="top-description">
                            <div class="big-description">워크스페이스 추가</div>
                            <div class="small-description">워크스페이스를 추가/변경 할 수 있습니다.</div>
                        </div>
                    </div>


                    <!-- main -->
                    <div class="member-manage-main">

                        <!-- select 부분 -->
                        <div class="select-wrapper">
                            <div class="selectbox">
                                <select id="chooseSelect">
                                    <option selected disabled> 카테고리 선택</option>
                                    <option> 메모 </option>
                                    <option> 게시판 </option>
                                    <option> 채팅 </option>
                                    <option> 로드맵 </option>
                                </select>
                            </div>
                            <div class="select-input">
                                <input class="category-name-input form-control" type="text" placeholder="새 카테고리명을 입력해주세요." value="">
                            </div>
                            <div class="select-button">
                                <button type="button" class="btn btn-gradi1 category-add-button">추가하기</button>
                            </div>
                        </div>

                        <!-- 카테고리 추가되는 부분 -->
                        <div class="category-list-wrapper"> 
                            <!-- 목록  -->
                            <div class="no-move-wrapper">
                            <div class="category-subheading">
                                <div id="content-label">항목</div>
                                <div id="name-label">이름</div>
                                <div id="remove-label">삭제</div>
                            </div>
                            <div class="category-hr">
                                <hr>
                            </div>
                        </div>
                        <div class="all-category-list">
                            <div class="origin-category-list commonList">
                            <!-- ********************* 기존 카테고리 -->
                            <div class="category-wrapper my" data-state="none" data-option="none" data-wsidx="none">
                                
                                <div class="category-name-wrap category-shadow">
                                    <div class="category-imgs">
                                        <!-- 첫번째 div-->
                                        <img class="square-icon" src="/resources/img/square.png">
                                        <img class="search-icon" src="/resources/img/premium-icon-calendar-5175870.png"> <!-- 여기 나중에 c:if test-->
                                    </div>

                                    <div class="category-input">
                                        <!-- 두번째 div -->
                                        <input class="category-input-text form-control" type="text" placeholder="원래값" value="1"> 
                                    </div>
                                </div>
                                <div id="delete-button">
                                    <!-- 세번째 div -->
                                    <button type="button" class="btn btn-danger category-delete-button">삭제</button>
                                </div>
                                
                            </div>
                            </div>

                            <div class="clone-category-list commonList">
                            <!-- ***************** 복제할 카테고리-->
                            <div class="category-wrapper new-category " data-state="none" data-option="none" data-wsidx="none">

                                <div class="category-name-wrap category-shadow">
                                    <div class="category-imgs">
                                        <!-- 첫번째 div-->
                                        <img class="square-icon" src="/resources/img/square.png">
                                        <img class="search-icon" src="/resources/img/premium-icon-calendar-5175870.png"> <!-- 여기 나중에 c:if test-->
                                    </div>

                                    <div class="category-input">
                                        <!-- 두번째 div -->
                                        <input class="category-input-text form-control" type="text" placeholder="" value=""> 
                                    </div>
                                </div>


                                <div id="delete-button">
                                    <!-- 세번째 div -->
                                    <button type="button" class="btn btn-danger category-delete-button">삭제</button>
                                </div>

                            </div>
                           
                            </div>
                        </div>


                        </div>
                    </div>

                    <!-- bottom 부분  -->
                    <div class="member-manage-bottom">
                        <div class="button-wrapper">
                            <button type="button" class="btn btn-gradi1 category-save-button">저장하기</button>
                        </div>
                    </div>

                </div>
            </section>
            <aside> <%@ include file="/WEB-INF/views/include/nav/aside.jsp" %> </aside>
        </div>
    </div>
</body>

<script type="text/javascript" src="/resources/js/setting/workspace-management.js"></script>
<script>
//1. (기존 존재항목) 삭제 버튼을 누르면 -> 모달 '삭제하시겠습니까?' -> 비동기로 반영
$(".category-delete-button").click(function(){
  deleteModal.modal.find('.first-button').on('click', () => { 
    $(this).closest(".category-wrapper").hide(); 
    thisCategory.data('state','hide');
  })
})


// 2. (수정항목) 카테고리 추가 및 삭제 -> 비동기로 반영

let thisCategory;
let input;

$(deleteModal.modal).find('.first-button').on('click' , function() {
  if(thisCategory==undefined){
    return;
  }
  thisCategory.remove();
});

//카테고리 추가 메서드
function addCategory(){
  input = $(".category-name-input").val();
  selectOption = $('#chooseSelect').val();

  console.log(selectOption);
  //카테고리를 미지정할 경우에 추가X
  if(selectOption == null){
    needCategoryModal.viewModal();
    return;
  }

  if(input!=""){// 워크스페이스 이름을 입력
  window.alert(input);
  newCategory = $(".new-category").clone();
  newCategory.find(".category-input-text").attr('value',input);
  newCategory.data('option',selectOption);
  newCategory.data('state','insert');
  
  newCategory.appendTo(".origin-category-list");
  newCategory.removeClass("new-category");


  deleteModal.makeModalBtn(newCategory.find(".category-delete-button"));
  
  newCategory.find(".category-delete-button").on('click' , function() {
    thisCategory = $(this).parents('.category-wrapper');
  })
  
  }else{ //워크 스페이스 이름을 미입력시 모달창
    needNameModal.viewModal();
  }
}

// 카테고리 추가 버튼을 눌렀을 때
$(".category-add-button").click(addCategory);

// 엔터키를 눌렀을 때
$(".select-input").on("keyup", function(key){
  if(key.keyCode==13){
    addCategory();
  }
})


// 3. 저장 버튼을 누르면 -> 모달 '저장하시겠습니까?' -> 비동기 POST 요청
beforeSaveModal.modal.find(".first-button").on('click', function() {
	let workspaceList = [];
	// state가 hide라면 => delete
	// state가 newMade라면 insert + wsIdx도 부여(시퀀스) => insert
	// state가 none이라면 속성 내용 => update
	
	$('.origin-category-list').find('.category-wrapper').each(function() {
		
		let workState = $(this).data("state");
		let workOption = $(this).data("option");
		let workWrite = $(this).children().find(".category-input-text").val();
		let workWsIdx = $(this).data("wsidx");

		console.log(workWsIdx);
		let workData = {"workState": workState, "workOption": workOption, "workWrite": workWrite, "workWsIdx": workWsIdx};
		workspaceList.push(workData);
		console.log(workspaceList);
	});

	let projectUri = "/project/setting/workspace-management/" + "${projectIdx}";
	
	fetch(projectUri, {
		method: "POST",
		headers: { "Content-type": "application/json; charset=UTF-8" },
		body: JSON.stringify(workspaceList)
	});
});



// 4. jQuey로 이동 가능하게끔 만들기
$('.all-category-list').sortable({
  connectWith : ".clone-category-list, .origin-category-list",
  cancel : ".category-subheading, .category-input-text, .category-hr",
  //드래그 시작됬을 때 발생
  start : (e, ui) => {
    let height = ui.item.css('height');
    $(".category-wrapper").css('height', height);
  }
  
}).disableSelection();
</script>
</html>

