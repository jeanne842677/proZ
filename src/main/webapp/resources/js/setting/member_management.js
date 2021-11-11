

/* 
역할 삭제 
*/
$(".delete-role").click(function() {
    $(this).parent("div").parent("div").parent("div").remove();
})

/* 
맴버 추가
*/
$(".insert-member").click(function() {
var input = $("#invite-member-input").val();
if(input != ""){
/* 
이메일 발송
*/
alert("초대할 맴버에게 이메일이 발송되었습니다 수락을 요청하세요!");
$("#invite-member-input").val("");
}else{
   alert("초대할 맴버의 닉네임 또는 이메일을 입력해주세요");
}




})

/* 초대 링크 복사 */
$('.copy-url').click(
function() {
$("#link-input").select();
document.execCommand('Copy');
alert( '링크가 복사 되었습니다.' );

});


/* 프로젝트 제거 모달창 */
var firstSaveModal = new Modal("맴버 추방","XXX 맴버를 추방하시겠습니까?");

var secondSaveModal = new Modal("맴버 추방","XXX 맴버가 추방되었습니다");

firstSaveModal.createTwoButtonModal("추방","취소"); //버튼 2개생성 first-button : 저장 second-button : 취소
firstSaveModal.makeModalBtn($(".exile"))
.find(".modal-popUp-message").css("color","red");   //요소를 리턴받아 css에 빨간색입힘

secondSaveModal.createAlertModal(); //두번쨰모달 생성
secondSaveModal.makeModalBtn($(".first-button"))

