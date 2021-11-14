 /* 
        권한관련 on/off 
        on : btn-info
        off : btn-secondary
    */


        $(".btn-info").click(function() {
			if( $(this).hasClass("default")){
				return;
			}
            $(this).toggleClass("btn-info").toggleClass("btn-secondary").toggleClass("on")
         })
     
         $(".btn-secondary").click(function() {
			if( $(this).hasClass("default")){
				return;
			}
            $(this).toggleClass("btn-info").toggleClass("btn-secondary")
            
         })
     
         /* 
             역할 삭제 
         */
         $(".delete-role").click(function() {
			if( $(this).hasClass("default")){
				return;
			}
			
	
             $(this).parent("div").parent("div").parent("div").remove();
         })
     
         /* 
             역할 추가
         */
     
         let a;
     
        $(".add-role").click(function() {
            var input = $("#new-role-input").val();
            if(input != ""){
             console.dir(input);
             var i = 1;
             
     
             let b = $(".role-list-content-new").children().clone();
             b.find(".role-title-wrapper").children().attr('value' , input);
             b.appendTo(".role-list-wrapper");
             
     
             b.find("#role").children('.delete-role').on('click', e=> {
                 b.remove();
             })
     
     
     
             b.find("#role").children('.btn-info').click(function () {
                 $(this).toggleClass("btn-info").toggleClass("btn-secondary").toggleClass("on");
             })
     
     
     
             $("#new-role-input").val("");
     
                 i++;
            }else{
                alert("역할명을 입력해주세요");
            }
     
            
          
         
      
        })
     
         /* 설정 저장 모달창 */
         var firstSaveModal = new Modal("설정 저장","설정을 저장하시겠습니까?");
          
         var secondSaveModal = new Modal("저장 완료","저장이 완료되었습니다.");
         
         
     
         firstSaveModal.createTwoButtonModal("저장","취소"); //버튼 2개생성 first-button : 저장 second-button : 취소
         firstSaveModal.makeModalBtn($(".save"));   //버튼에 지정
     
     
         secondSaveModal.createAlertModal(); //두번쨰모달 생성
         secondSaveModal.makeModalBtn($(".first-button")); //first-button : 저장 <--여기에지정
         
         
         
         
     
     
         /* 역할 추가  */
     
     
     
     
     
     
        
        