$(".modify").click(function () {
    $(this).css("display","none")
    $(".save").css("display","flex")
    $(".title").removeAttr("readonly")
});

$(".save").click(function () {
    $(this).css("display","none")
    $(".modify").css("display","flex")
    $(".title").attr("readonly",true)
    alert('이름변경이 완료되었습니다')
   
});


 /* 프로젝트 제거 모달창 */
 var firstSaveModal = new Modal("프로젝트 제거","프로젝트를 제거하시겠습니까?");
 
 var secondSaveModal = new Modal("제거 완료","프로젝트가 제거되었습니다.");

 firstSaveModal.createTwoButtonModal("제거","취소"); //버튼 2개생성 first-button : 저장 second-button : 취소
 firstSaveModal.makeModalBtn($(".prject-delete"))
 .find(".modal-popUp-message").css("color","red");   //요소를 리턴받아 css에 빨간색입힘

 secondSaveModal.createAlertModal(); //두번쨰모달 생성
 secondSaveModal.makeModalBtn($(".first-button"))
 