$(function(check) {

	$('#login-btn').click(function(){
	if($('#email').val()==''){
		$('.emptyemail').css('display','block');
		$('.emptyemail').css('color','red');
		return false;
	}else{
		$('.emptyemail').css('display','none');
	}
});

	$('#login-btn').click(function(){
	if($('#password').val()==''){
		$('.emptypassword').css('display','block');
		$('.emptypassword').css('color','red');
		return false;
	}else{
		$('.emptypassword').css('display','none');
	}
	});
});



$(function() {
	if($('.message').val()==''){
		$('.emptypassword').css('display','none');
		$('.emptyemail').css('display','none');
	}else{
		$('.emptypassword').css('display','block');
		$('.emptyemail').css('display','block');
	}
});
