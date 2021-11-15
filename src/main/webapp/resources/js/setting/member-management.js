




/* 초대 링크 복사 */
$('.copy-url').click(
function() {
$("#link-input").select();
document.execCommand('Copy');
alert( '링크가 복사 되었습니다.' );

});




