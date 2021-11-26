<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<meta charset="UTF-8">
<title>fcm 테스트</title>
</head>
<body>

안녕

<p id="token"></p>

<script src="https://www.gstatic.com/firebasejs/7.14.6/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.14.6/firebase-messaging.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.14.6/firebase-analytics.js"></script>
<script>
 

  // Your web app's Firebase configuration
  // For Firebase JS SDK v7.20.0 and later, measurementId is optional
  const firebaseConfig = {
    apiKey: "AIzaSyCaf-63jntfXOWPT4W6c70C3J8aMokUPP8",
    authDomain: "kh-final-proz.firebaseapp.com",
    projectId: "kh-final-proz",
    storageBucket: "kh-final-proz.appspot.com",
    messagingSenderId: "818737347858",
    appId: "1:818737347858:web:160ccf5238fa162a0347cb",
    measurementId: "G-9M7GX9PCF5"
  };


firebase.initializeApp(firebaseConfig);
firebase.analytics();

const messaging=firebase.messaging();


function IntitalizeFireBaseMessaging() {
    messaging
        .requestPermission()
        .then(function () {
            console.log("Notification Permission");
            return messaging.getToken();
        })
        .then(function (token) {
            console.log("Token : "+token);
            document.getElementById("token").innerHTML=token;
        })
        .catch(function (reason) {
            console.log(reason);
        });
}

messaging.onMessage(function (payload) {
    console.log(payload);
    const notificationOption={
        body:payload.notification.body,
        icon:payload.notification.icon
    };

    if(Notification.permission==="granted"){
        var notification=new Notification(payload.notification.title,notificationOption);

        notification.onclick=function (ev) {
            ev.preventDefault();
            window.open(payload.notification.click_action,'_blank');
            notification.close();
        }
    }

});
messaging.onTokenRefresh(function () {
    messaging.getToken()
        .then(function (newtoken) {
            console.log("New Token : "+ newtoken);
        })
        .catch(function (reason) {
            console.log(reason);
        })
})
IntitalizeFireBaseMessaging();



  
</script>
</body>

</html>