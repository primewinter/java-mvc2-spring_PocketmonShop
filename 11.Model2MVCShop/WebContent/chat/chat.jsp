<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
	<!-- 참조 : http://getbootstrap.com/css/   참조 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500&display=swap" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	<!-- 채팅 node js Server	 -->
	 <!-- <script src="http://localhost:82/socket.io/socket.io.js"></script> -->
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
   
   
   <!-- jQuery UI toolTip 사용 CSS-->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <!-- jQuery UI toolTip 사용 JS-->
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
         .wrapper {
            display: grid;
            grid-template-columns: repeat(5, 250px);
            grid-gap: 10px;
            grid-auto-rows: minmax(100px, auto);
            margin: 20px;
        }

        .one {
            grid-column: 2 / 4;
            grid-row: 1;
            text-align:center;
        }

        .two {
            grid-column: 2 / 4;
            grid-row: 2 ;
            background-color: powderblue;
        }
        

		body {
            padding-top : 90px;
            font-family:  'Noto Sans KR', sans-serif;
        }
     </style>
	
</head>

<body>

	<jsp:include page="/layout/toolbar.jsp" />

<form name = "detailForm">
<div class="container">
    <div class="wrapper">
        <div class="one">
        		<h3>채팅</h3>
     	</div>
        <div class="two">
			        <!-- 송신 메시지 작성하는 창 -->
			        <input id="textMessage" type="text">
			        <!-- 송신 버튼 -->
			        <input onclick="sendMessage()" value="Send" type="button">
			
			        <!-- 종료 버튼 -->
			        <input onclick="disconnect()" value="Disconnect" type="button">
				    <br />
				    <!-- 결과 메시지 보여주는 창 -->
				    <textarea id="messageTextArea" rows="10" cols="50"></textarea>
				    <script type="text/javascript">
				    
				        var chatAddr = "ws://192.168.0.82:8080/chatSocket/${plan.planId}/${user.userId}";
				        var chatSocket = new WebSocket(chatAddr);
				        var messageTextArea = document.getElementById("messageTextArea");
				        //웹 소켓이 연결되었을 때 호출되는 이벤트
				        chatSocket.onopen = function(message){
				            
				          //웹 소켓에서 메시지가 날라왔을 때 호출되는 이벤트
					        chatSocket.onmessage = function(message){
					            messageTextArea.value += message.data+"\n";
					            console.log("chat 메시지 왔다.")
					        };
				            
				        };
				        //웹 소켓이 닫혔을 때 호출되는 이벤트
				        chatSocket.onclose = function(message){
				            messageTextArea.value += "chat 접속이 끊어졌습니다.\n";
				        };
				        //웹 소켓이 에러가 났을 때 호출되는 이벤트
				        chatSocket.onerror = function(message){
				            messageTextArea.value += "chat 에러가 발생했습니다.\n";
				        };
				        
				        //Send 버튼을 누르면 실행되는 함수
				        function sendMessage(){
				        	var user = "${user.userId} : ";
				            var message = document.getElementById("textMessage");
				            //messageTextArea.value += "Send to Server => "+message.value+"\n";
				            //웹소켓으로 textMessage객체의 값을 보낸다.
				            chatSocket.send(user+message.value);
				            //textMessage객체의 값 초기화
				            message.value = "";
				        }
				        //웹소켓 종료
				        function disconnect(){
				        	chatSocket.close();
				        }
				    </script>
       	</div>
       	<div class=three>
       	</div>
    </div>
</div>

</form>

</body>
</html>