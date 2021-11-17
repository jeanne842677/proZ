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
                action="/member/join" method="post" id="frm_join" class="join-wrap">
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
                    <input type="password" class="form-control" id="password" name="password" placeholder="8글자 이상의 숫자 영문자 특수문자 조합 입니다."
                    	 <c:if test="${empty error.password}">
                            value="${joinForm.password}"   
                        </c:if>  required/>
                        <form:errors path="password" cssClass="valid-msg"/>
                   
                   <%--  <label for="password-check" id="password-check-label">02-2. 비밀번호 재확인</label>
                    <input type="password" class="form-control" id="password-check" 
                    	 <c:if test="${empty error.password}">
                            value="${joinForm.password}"   
                        </c:if> 
                    required/>/ --%>


                    <label for="nickname" id="nickname-label" >03. 사용하실 닉네임을 입력하세요.</label>
                    <input type="text" class="form-control" id="nickname" name="nickname" maxlength="8" required placeholder="8글자 이내로 입력하세요.">

                    <label for="git-hub" id="git-label">04. Git-Hub 주소를 등록해주세요. (선택) </label>
                    <input type="text" class="form-control" id="git" name="git">


                    <label id="terms-label">05. 이용 약관에 동의 후 회원가입 버튼을 클릭하세요. </label>
                    <div class="terms-box">
                        목숨을 미인을 길지 이상 보이는 넣는 우리는 새 없으면, 이것이다. 관현악이며, 같은 청춘에서만 거친 그들의 때까지 이상은 군영과 찾아 것이다. 인류의 우는 하는 전인 못할
                        피는 청춘은 싶이 오직 끓는다. 아니더면, 기쁘며, 사람은 그들에게 같이 청춘의 공자는 싸인 있다. 품으며, 광야에서 시들어 영락과 원질이 운다. 품고 인류의 그들의 온갖
                        피가 작고 황금시대를 것이다. 오아이스도 있을 작고 자신과 가슴에 봄바람이다. 그와 구하기 되려니와, 인도하겠다는 사라지지 열매를 너의 살 있는가? 만천하의 간에 원대하고,
                        새가 되는 꾸며 보라. 이 얼마나 같으며, 이상이 가지에 피고, 이상 목숨을 위하여서.
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



</body>

</html>