
 /* 프로젝트 제거 모달창 */
 var firstDelModal = new Modal("프로젝트 제거","프로젝트를 제거하시겠습니까?");
 
 var secondDelModal = new Modal("제거 완료","프로젝트가 제거되었습니다.");

 firstDelModal.createTwoButtonModal("제거","취소"); //버튼 2개생성 first-button : 저장 second-button : 취소
 firstDelModal.makeModalBtn($(".prject-delete"))
 .find(".modal-popUp-message").css("color","red");   //요소를 리턴받아 css에 빨간색입힘

 secondDelModal.createAlertModal(); //두번쨰모달 생성
 secondDelModal.makeModalBtn(firstDelModal.modal.find(".first-button"))
