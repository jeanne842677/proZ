/* 
	1. 이미 존재하는 아이디로 사용자가 가입하려는 경우 
	2. 비밀번호는 영어, 숫자, 특수문자 조합의 8자리 이상의 문자열
	3. 전화번호는 숫자로만 입력 */
	(()=>{
		let confirmEmail = '';
   		document.querySelector("#btnEmailCheck").addEventListener('click', e => {

   			//id속성값이 지정되어있으면 id값으로 해당 요소객체 호출 가능
   			let id = email.value;
   			if(id){
   				fetch('/member/email-check?email=' + id)
   				.then(response => response.text())
   				.then(text => {
					console.dir(text);
					
   					if(text == 'available'){
   						document.querySelector('#emailCheck').innerHTML ='사용 가능한 이메일 입니다.';   	
   						confirmEmail = id;
   					}else if(text == 'disable'){
   						document.querySelector('#emailCheck').innerHTML ='사용 불가능한 이메일 입니다.';   						
   					}else{
						document.querySelector('#emailCheck').innerHTML ='시스템장애입니다.'; 
					}
   				})
   			}
   		})
   		
   		document.querySelector('#frm_join').addEventListener('submit', e => {
   			
   			let pwReg = /(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9])(?=.{8,})/;
   			let tellReg = /^\d{9,11}$/;
   			
   			if(confirmEmail != email.value){
   				e.preventDefault();
   				document.querySelector('#emailCheck').innerHTML ='이메일 중복 검사를 통과하지 않았습니다.';   	
   			}
   			
   			/*if(!pwReg.test(password.value)){
   				e.preventDefault();
   				document.querySelector('#pwCheck').innerHTML ='비밀번호는 숫자,영문자,특수문자 조합의 8자리 이상 문자열입니다.'; 
   			}
   			
   			if(!tellReg.test(tell.value)){
   				e.preventDefault();
   				document.querySelector('#tellCheck').innerHTML ='휴대폰 번호는 9~11자리의 숫자입니다.'; 
   			}*/
   		})
	})();
	
	
	
	
	
	
	
	
	
	