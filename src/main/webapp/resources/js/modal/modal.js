    class Modal {
    
    title;
    message;
    placehoder;
    modal;
    inputValue;
    
    
    //생성자
    constructor(title, message) {
    
        this.title = title;
        this.message = message;
        
    
    }
    
    //input있는 모달 만들기
    createInputModal = function () {
    
        this.modal = $("<div class='Modal'><div class='popUp' ><div class='content-wrap'><button class='xbutton'><i class='fas fa-times'></i></button><div class='modal-popUp-title'>" + this.title + "</div><div class='modal-popUp-message'>" + this.message + "</div><div class='modal-popUp-input'><input class='form-control' id='input' type='text' ></div></div><div class='modal-popUp-button-wrapper'><button id='modal-btn-cancel' class='btn btn-secondary first-button'>취소</button><button id='modal-btn-confirm' class='btn btn-primary second-button'>확인</button></div></div></div>")
    
        let modal = this.modal;
        modal.find('.xbutton').on('click', function () {
    
            modal.hide();
            modal.find('#input').val('');
    
        });
        modal.find('#modal-btn-cancel').on('click', function () {
            modal.hide();
            modal.find('#input').val('');
        })
        modal.find('.second-button').on('click', function () {
            modal.hide();
        })
        $('body').append(modal);
    
    
    }
    
    //알림용 모달 만들기
    createAlertModal = function () {
    
        this.modal = $("<div class='Modal'><div class='popUp' ><div class='content-wrap'><button class='xbutton'><i class='fas fa-times'></i></button><div class='modal-popUp-title'>"+this.title+"</div><div class='modal-popUp-message' style='margin-bottom:30px;'>"+this.message+"</div></div><div class='modal-popUp-button-wrapper'><button id='modal-btn-confirm' class='btn btn-primary'>확인</button></div></div></div> ");
        
        let modal = this.modal;
        modal.find('.xbutton').on('click', function () {
    
            modal.hide();
    
        });
        modal.find('#modal-btn-confirm').on('click', function () {
            modal.hide();
        })
    
        $('body').append(modal);
    
        
    
    
    
    }

    //버튼 2개있는 모달 매개변수로 버튼 내용을 받음
    createTwoButtonModal = function (firstContent,secondContent) {
    
    this.modal = $("<div class='Modal'><div class='popUp' ><div class='content-wrap'><button class='xbutton'><i class='fas fa-times'></i></button><div class='modal-popUp-title'>"+this.title+"</div><div class='modal-popUp-message' style='margin-bottom:30px;'>"+this.message+"</div></div><div class='modal-popUp-button-wrapper'><button id='modal-btn-confirm' class='btn btn-primary first-button'>"+ firstContent+ "</button><button id='modal-btn-confirm' class='btn btn-primary second-button'>"+secondContent+"</button></div></div></div> ");
   
    
    let modal = this.modal;
    modal.find('.xbutton').on('click', function () {

        modal.hide();

    });
    modal.find('#modal-btn-confirm').on('click', function () {
        modal.hide();
    })


    modal.find(".first-button").css('margin-right','10px');
    modal.find(".second-button").css({
        'background-color':'slategray' ,
        'border' : 'slategray'
});

    $('body').append(modal);


}


  //패스워드 모달 만들기
createpasswordModal = function () {

    this.modal = $("<div class='Modal'><div class='popUp' ><div class='content-wrap'><button class='xbutton'><i class='fas fa-times'></i></button><div class='modal-popUp-title'>" + this.title + "</div><div class='modal-popUp-message'>" + this.message + "</div><div class='modal-popUp-input'><input class='form-control' id='input1' type='password' ><input class='form-control' id='input2' type='password' style='margin-top:10px'></div></div><div class='modal-popUp-button-wrapper'><button id='modal-btn-cancel' class='btn btn-secondary'>취소</button><button id='modal-btn-confirm' class='btn btn-primary'>확인</button></div></div></div>")

    let modal = this.modal;
    modal.find('.xbutton').on('click', function () {

        modal.hide();
        modal.find('input').val('');

    });
    modal.find('#modal-btn-cancel').on('click', function () {
        modal.hide();
        modal.find('input').val('');
    })
    $('body').append(modal);


}
    
    
    
    getInputValue = function () {
        return this.modal.find('#input').val();
    }
    
    makeModalBtn = function (element) {
        let modal = this.modal;
        element.on('click', () => {
            modal.css('display','flex');
        })
        return this.modal;
    }
    
    
    
    setTitle(title) {
        this.title = title;
        this.modal.find('.modal-popUp-title').html(title);
    }
    
    
    setMessage(body) {
        this.message = body;
        this.modal.find('.modal-popUp-message').html(body);
    }
    
    setPlacehoder(placehoder) {
        this.modal.find('#input').attr('placeholder', placehoder);
    };
    
    setConfirmFnc(fuc) {
        let modal = this.modal;
        
        modal.find('#modal-btn-confirm').on('click', function () {
            fuc();
            modal.hide();

            /*modal.find('#input').val('');*/
    		
        });
    
    }
    
    
    
    
    
    
    }
    

    
    
    