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
	<script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
	
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
				        //WebSocketEx는 프로젝트 이름
				        //websocket 클래스 이름
				        var chatAddr = "ws://192.168.0.82:8080/websocket/${user.userId}";
				        var webSocket = new WebSocket(chatAddr);
				        var messageTextArea = document.getElementById("messageTextArea");
				        //웹 소켓이 연결되었을 때 호출되는 이벤트
				        webSocket.onopen = function(message){
				            
				          //웹 소켓에서 메시지가 날라왔을 때 호출되는 이벤트
					        webSocket.onmessage = function(message){
					            messageTextArea.value += message.data+"\n";
					        };
				            
				        };
				        //웹 소켓이 닫혔을 때 호출되는 이벤트
				        webSocket.onclose = function(message){
				            messageTextArea.value += "접속이 끊어졌습니다.\n";
				        };
				        //웹 소켓이 에러가 났을 때 호출되는 이벤트
				        webSocket.onerror = function(message){
				            messageTextArea.value += "에러가 발생했습니다.\n";
				        };
				        
				        //Send 버튼을 누르면 실행되는 함수
				        function sendMessage(){
				        	var user = "${user.userId} : ";
				            var message = document.getElementById("textMessage");
				            //messageTextArea.value += "Send to Server => "+message.value+"\n";
				            //웹소켓으로 textMessage객체의 값을 보낸다.
				            webSocket.send(user+message.value);
				            //textMessage객체의 값 초기화
				            message.value = "";
				        }
				        //웹소켓 종료
				        function disconnect(){
				            webSocket.close();
				        }
				    </script>
       	</div>
       	<div class=three>
       		<ul class="chat_box">
		    </ul>
		    <input name="message">
		    <button class="send">보내기</button>
		    <script>
    $(function () {
        var chatBox = $('.chat_box');
        var messageInput = $('input[name="message"]');
        var sendBtn = $('.send');
        var roomId = $('.content').data('room-id');
        var member = $('.content').data('member');

        var sock = new SockJS("/stomp-chat");
        var client = Stomp.over(sock); // 1. SockJS를 내부에 들고 있는 client를 내어준다.

        // 2. connection이 맺어지면 실행된다.
        client.connect({}, function () {
            // 3. send(path, header, message)로 메시지를 보낼 수 있다.
            client.send('/publish/chat/join', {}, JSON.stringify({chatRoomId: roomId, writer: member})); 
            // 4. subscribe(path, callback)로 메시지를 받을 수 있다. callback 첫번째 파라미터의 body로 메시지의 내용이 들어온다.
            client.subscribe('/subscribe/chat/room/' + roomId, function (chat) {
                var content = JSON.parse(chat.body);
                chatBox.append('<li>' + content.message + '(' + content.writer + ')</li>')
            });
        });

        sendBtn.click(function () {
            var message = messageInput.val();
            client.send('/publish/chat/message', {}, JSON.stringify({chatRoomId: roomId, message: message, writer: member}));
            messageInput.val('');
        });
    });
</script>
       	</div>
    </div>
</div>

</form>

</body>
</html>