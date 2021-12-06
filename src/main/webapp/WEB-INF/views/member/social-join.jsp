<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

    <title>프로 제트 회원가입</title>

    <style type="text/css">
               html,
        body {

            width: 100%;
            height: 100%;
            min-width: 1000px;
            color: white;

        }

        .wrap {
            display: flex;
            flex-direction: column;

            background: rgb(40, 146, 186);
            background: linear-gradient(128deg, rgba(40, 146, 186, 1) 0%, rgba(120, 118, 177, 1) 33%, rgba(196, 93, 161, 1) 68%, rgba(200, 129, 94, 1) 100%);
            position: relative;
            overflow: hidden;

        }

        header {
            height: 60px;
            background-color: transparent;

        }

        .content-wrap {
            display: flex;
            justify-content: center;

            width: 100%;

        }


        section {
            width: 500px;
            display: flex;
            flex-direction: column;


        }

        .logo-wrap {

            height: 150px;
            display: flex;
            justify-content: center;

        }

        .join-wrap {

            background: RGB(22, 26, 37);
            display: flex;
            flex-direction: column;
            align-items: center;
            border-radius: 5px;
            font-size: 17px;
            padding: 50px 0;
            margin-bottom: 50px;

            box-shadow: 0px 0px 30px 0px rgba(0, 0, 0, 0.4);
            z-index: 100;
            
        }

        label {
            margin: 30px 0 15px  0;
            width:392px;
            text-align: left;
        }


        .email-wrap {
            display: flex;

        }


        #email {
            width: 296px;
            margin-right: 6px;
            background-color: RGB(62, 66, 77);
            height: 48px;
            color: white;
            border-color: RGB(255, 255, 255, 0.2);

        }

        
        #email-btn {
            width: 90px;
            background-color: RGB(143, 122, 229);

        }

        #password-check-label {

            margin: 10px 0;
        }

        #password,
        #password-check,
        #nickname,
        #git {
            width: 392px;
            margin-bottom: 5px;
            height: 48px;
            color: white;
            border-color: RGB(255, 255, 255, 0.2);
            background-color: RGB(62, 66, 77);

        }

        .terms-box {

            width: 392px;
            height: 100px;
            overflow-y: auto;
            background-color: white;
            padding: 10px;
            font-size: 15px;
        }
        
        .terms-box::-webkit-scrollbar {
		    width: 6px;
		}
		.terms-box::-webkit-scrollbar-thumb {
		    background-color: #000000;
		}
		.terms-box::-webkit-scrollbar-track {
		    background-color: grey;
		}

        .terms-wrap {
            width: 392px;
            display: flex;
            justify-content: flex-end;
            margin: 10px 0;
        }

        .form-check-label {
            margin: 0px 5px;

        }


/*가입하기 버튼*/
        #join-impl {
            margin: 30px 0;
            width: 392px;
            height: 50px;
            font-size: 20px;
            background: rgb(117,55,181);
            background: linear-gradient(128deg, rgba(117,55,181,1) 0%, rgba(190,68,172,1) 100%);
   

        }

        .design-box1 {

            position: absolute;
            width: 500px;
            height: 650px;
            transform: rotate(-30deg);
            background: rgba(0, 0, 0, 0.1);
            top: 50px;
            right: -320px;
            border-radius: 2px;
            overflow: hidden;


        }



        .design-box2 {

            position: absolute;
            width: 500px;
            height: 500px;
            transform: rotate(-30deg);
            background: rgba(255, 255, 255, 0.1);
            top: -200px;
            left: -300px;
            border-radius: 2px;
            overflow: hidden;


        }



        .design-box3 {

            position: absolute;
            width: 500px;
            height: 500px;
            transform: rotate(-30deg);
            background: rgba(255, 255, 255, 0.1);
            top: 900px;
            left: 200px;
            border-radius: 2px;
            overflow: hidden;


        }

        .terms-box{

            
            background-color: RGB(62, 66, 77);
            padding: 10px;
        }
        
      .valid-msg{
         display:block;
         color:red;   
         font-size:15px;
      }


    </style>

</head>

<body>

    <div class="wrap">
        <header>




        </header>

        <div class="content-wrap">
            <section>
                <div class="logo-wrap">
                
                    <img src="/resources/img/logo-white.png" height="100%">
                </div>
                <form:form modelAttribute="joinForm" 
                action="/member/social-join" method="post" id="frm_join" class="join-wrap">
                    <label for="email" id="email-label">01. 이메일을 입력하고 인증을 완료하세요. </label>
                    <div class="email-wrap">
                        <input type="email" class="form-control" id="email" name="email"
                        <c:if test="${empty error.email}">
                            value="${joinForm.email}"   
                        </c:if> 
                        required />
                        
                        <button type="button" class="btn btn-primary" id="btnEmailCheck">중복확인</button>
                       
                    </div>
                      <c:if test="${empty error.email}">
                               <span id="emailCheck" class="valid-msg"></span>
                       </c:if>
                    <form:errors path="email" cssClass="valid-msg"/>


                    <label for="password" id="password-label">02-1. 사용하실 비밀번호를 입력하세요. </label>
                    <input type="password" class="pw1 pw form-control" id="password" name="password" placeholder="8글자 이상의 숫자 영문자 특수문자 조합 입니다."
                        <c:if test="${empty error.password}">
                            value="${joinForm.password}"   
                        </c:if>  required/>
                        <form:errors path="password" cssClass="valid-msg"/>
                   
                   <label for="password-check" id="password-check-label">02-2. 비밀번호 재확인</label>
                    <input type="password" class="pw2 pw form-control" id="password-check" placeholder="비밀번호를 다시 입력해주세요."
                        <c:if test="${empty error.password}">
                            value="${joinForm.password}"   
                        </c:if> 
                    required/>                  
                
               <div class="alert-fail" style="display: none;">패스워드가 틀렸습니다.</div>
               <div class="alert" style="display: none;">패스워드가 일치합니다.</div>


                    <label for="nickname" id="nickname-label" >03. 사용하실 닉네임을 입력하세요.</label>
                    <input type="text" class="form-control" id="nickname" name="nickname" maxlength="8" required value="${name}">

                    <label for="git-hub" id="git-label">04. Git-Hub 주소를 등록해주세요. (선택) </label>
                    <input type="text" class="form-control" id="git" name="git">


                    <label id="terms-label">05. 이용 약관에 동의 후 회원가입 버튼을 클릭하세요. </label>
                    <div class="terms-box">
                        제1장 총칙<br>
			제1조 (목적)<br>
			이 약관은 proZ (이하 "프로제트"라 합니다) 홈페이지가 제공하는 통합회원 서비스(이하 "서비스"라 합니다)를 이용함에 있어 이용자와 proZ간의 권리·의무 및 책임사항과 기타 필요한 사항을 규정함을 목적으로 합니다.<br>
			
			제2조 (약관의 효력 및 변경)<br>
			① 이 약관은 서비스 화면에 게시하거나 기타의 방법으로 공지함으로써 이용자에게 공시하고, 이에 동의한 이용자가 서비스에 가입함으로써 효력이 발생합니다.<br>
			② proZ는 필요하다고 인정되는 경우 이 약관의 내용을 변경할 수 있으며, 변경된 약관은 서비스 화면에 공지함으로써 이용자가 직접 확인할 수 있도록 할 것입니다.<br>
			③ 이용자는 변경된 약관에 동의하지 않으실 경우 서비스 이용을 중단하고 본인의 회원등록을 취소할 수 있으며, 계속 사용하시는 경우에는 약관 변경에 동의한 것으로 간주되며 변경된 약관은 전항과 같은 방법으로 효력이 발생합니다.<br>
			④ 이용자가 이 약관의 내용에 동의하는 경우 서울시의 서비스 제공행위 및 이용자의 서비스 이용행위에는 이 약관이 우선적으로 적용될 것입니다. 이 약관에 명시되지 않은 사항에 대해서는 전기통신기본법, 전기통신사업법, 정보통신망이용촉진및정보보호등에관한법률, 방송통신심의위원회 심의규정, 정보통신 윤리강령, 프로그램 보호법 등 기타 대한민국의 관련법령과 상관습에 의합니다.
                    </div>
                    <div class="form-check terms-wrap">
                        <input class="form-check-input" type="checkbox" value="" id="terms-agree" required>
                        <label class="form-check-label" for="terms-agree" >
                            프로Z 이용 약관 동의(필수)
                        </label>
                    </div>
                    <button type="submit" class="btn btn-primary" id="join-impl">가입하기</button>


                </form:form>
                
<script type="text/javascript" src="/resources/js/member/joinForm.js"></script>

            </section>

        </div>


        <div class="design-box1"></div>
        <div class="design-box2"></div>
        <div class="design-box3"></div>


    </div>

<script type="text/javascript">
$('.pw').focusout(function() {
    let pw1 = $('.pw1').val();
    let pw2 = $('.pw2').val();
    
    if(pw1 & pw2) {
        return;
    }
    
    if(pw1 == "" || pw2 =="" ) {
        $('.alert-fail').css('display' , 'none');
        $('.alert').css('display' , 'none');
        $("#join-impl").attr("disabled", "disabled");    
    }


    if(pw1 != pw2 ) {
        $('.alert-fail').css('display' , 'block');
        $('.alert-fail').css('color' , 'red');
        $('.alert').css('display' , 'none');
        $("#join-impl").attr("disabled", "disabled");

       
    }else if(pw1 == pw2){
        $('.alert').css('display' , 'block');
         $('.alert').css('color' , 'red');
         $('.alert-fail').css('display' , 'none');
         $("#join-impl").removeAttr("disabled");

        
    }
    

})
</script>


</body>

</html>