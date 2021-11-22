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


let workspaceCnt =3; //sts에서는 db에 저장된 값을 받아온다.

// 1. 삭제 버튼을 누르면 -> 모달 '삭제하시겠습니까?' -> 비동기로 반영
$(".category-delete-button").click(function(){
  deleteModal.modal.find('.first-button').on('click', () => { 
    $(this).closest(".category-wrapper").hide(); 
    workspaceCnt--;
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
  workspaceCnt--;
});

//카테고리 추가 메서드
function addCategory(){
  input = $(".category-name-input").val();
 
  if(input!=""){// 워크스페이스 이름을 입력
  workspaceCnt++;
  newCategory = $(".new-category").clone();
  newCategory.find(".category-input-text").attr('value',input);
  
  newCategory.appendTo(".all-category-list");
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
  let workspaceNameDuplicateCheck = [];
  let projectUri = "/project/setting/workspace-management/" // + ${projectIdx} 

  fetch("/project/setting/workspace-setting", {
    method : "POST",
    headers : {"Content-type" : "application/json; charset=UTF-8"},
    body : JSON.stringify(workspaceList)
  });
});



// 워크스페이스의 영역 구하기 -> 만들어진 수 만큼 배열로 저장하기
const startDivX = $(".my").offset().left;
const endDivX = startDivX + $(".my").width();

let startDivY = $(".my").offset().top;
let endDivY = startDivY + $(".my").height();
let divArr = [];

for(let i=0; i<workspaceCnt; i++){  
  let startDivXY = [startDivX, startDivY];
  let endDivXY = [endDivX, endDivY];
  let divXY = [startDivXY, endDivXY];
  startDivY += ($(".my").height() + 10); //margin-bottom 값
  endDivY += ($(".my").height() + 10);
  divArr.push(divXY);
}

//마우스가 올라가 있는 상태에서 -> 해당 영역으로 들어가게되면 -> 그것의 div 밑으로 놓이면서 .hovering으로 addClass
// $('.category-wrapper').hover(function(){
//    for(let i=0; i<workspaceCnt; i++){
//      if($(this).offset().left >=startDivX && $(this).offset().left <= endDivX){
//       if( $(this).offset().top >= divArr[i][0][1] && $(this).offset().top <= divArr[i][1][1]){
//         $(this).addClass('hovering');
//       }
//      } 
//     }
// })


//forEach로 시작x,y 좌표와 끝 x,y 좌표를 구함

// 4. jQuey로 이동 가능하게끔 만들기
$('.all-category-list').sortable({
  connectWith : ".clone-category-list, .origin-category-list",
  cancel : ".category-subheading, .category-input-text, .category-hr",
  //드래그 시작됬을 때 발생
  start : (e, ui) => {
    let height = ui.item.css('height');
    $(".category-wrapper").css('height', height);
  },
  //드래그 끝나면 발생
  stop : (e,ui) => {
    window.alert("드래그 한번 이후에 발생하는 alert")
    $(".category-wrapper").on('mouseover', function(){
      for(let i=0; i<workspaceCnt; i++){
        if(ui.offset().left >=startDivX && ui.offset().left <= endDivX){
         if( ui.offset().top >= divArr[i][0][1] && ui.offset().top <= divArr[i][1][1]){
          window.alert("ㅎㅇ");
          $(this).addClass('hovering');
         }
        } 
       }
   })
  }
}).disableSelection();

// 5. jQuery로 같은 워크스페이스 타입일 경우에, parent 위에 드래그 하면 child 속성으로 들어가서 나타나게끔



