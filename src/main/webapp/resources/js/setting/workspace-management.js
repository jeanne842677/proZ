/**
 * 
 */
 
 // 모달 생성 및 설정
var deleteModal = new Modal("삭제 확인","해당 항목을 삭제하시겠습니까?");

var beforeSaveModal = new Modal("저장 확인", "저장하시겠습니까?");

var saveModal = new Modal("저장 완료", "저장이 완료되었습니다.");

var needNameModal = new Modal("카테고리 이름 설정", "카테고리 명을 입력해주세요.");

deleteModal.createTwoButtonModal("삭제", "취소");

deleteModal.makeModalBtn($(".category-delete-button"));

beforeSaveModal.createTwoButtonModal("저장", "취소");

beforeSaveModal.makeModalBtn($(".category-save-button"));

saveModal.createAlertModal();

saveModal.makeModalBtn(beforeSaveModal.modal.find(".first-button"));

needNameModal.createAlertModal();


// 1. 삭제 버튼을 누르면 -> 모달 '삭제하시겠습니까?' -> 비동기로 반영
$(".category-delete-button").click(function(){
  deleteModal.modal.find('.first-button').on('click', () => { 
    $(this).closest(".category-wrapper").hide(); 
  })
})


// 2. 수정된 카테고리 추가 및 삭제 -> 비동기로 반영

// 삭제하기
let thisCategory;
let input;

$(deleteModal.modal).find('.first-button').on('click' , function() {
  if(thisCategory==undefined){
    return
  }
  thisCategory.hide();
 
});

// 추가하기
$(".category-add-button").click(function addCategory(){
  input = $(".category-name-input").val();
 
  // 워크스페이스 이름을 입력했을 때만 삭제
  if(input!=""){
  window.alert(input);
  newCategory = $(".new-category").clone();
  newCategory.find(".category-input-text").attr('value',input);
  
  newCategory.appendTo(".category-list-wrapper");
  newCategory.removeClass("new-category");

  deleteModal.makeModalBtn(newCategory.find(".category-delete-button"));
  
  newCategory.find(".category-delete-button").on('click' , function() {
    alert($(this).parents('.category-wrapper').find('input').val());
    thisCategory = $(this).parents('.category-wrapper');
  })
  
  }else{ //워크 스페이스 이름을 미입력시 알림
    needNameModal.viewModal();
  }
})

$(".select-input").on("keyup", function(key){
  if(key.keyCode==13){}
})


// 3. 저장 버튼을 누르면 -> 모달 '저장하시겠습니까?' -> 비동기 POST 요청
beforeSaveModal.modal.find(".first-button").on('click', function() {
  let workspaceList = [];
  let workspaceNameDuplicateCheck = [];
  let projectUri = "/project/setting/workspace-management/" // + ${projectIdx} 

  fetch("/project/setting/workspace-setting", {
    method : "POST",
    headers : {"Content-type" : "application/json; charset=UTF-8"},
    body : JSON.stringify(workspaceList)
  });
});


// 4. jQuey로 이동 가능하게끔 만들기
$('.category-list-wrapper').sortable({
  connectWith : ".category-wrapper",
  cancel : ".category-subheading, .category-input-text, .category-hr",
  start : (e, ui) => {
    let height = ui.item.css('height');
    $(".category-wrapper").css('height', height);
  },
  // plcaeholder : "category-shadow"
  // end : (e,ui) => {alert("안돼요ㅠㅠ");}
}).disableSelection();

// 5. jQuery로 같은 워크스페이스 타입일 경우에, parent 위에 드래그 하면 child 속성으로 들어가서 나타나게끔
