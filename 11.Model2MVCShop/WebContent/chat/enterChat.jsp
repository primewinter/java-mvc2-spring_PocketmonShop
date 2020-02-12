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

<form name = "detailForm" action="/chat/getPlan" method="GET">
<div class="container">
    <div class="wrapper">
        <div class="one">
        		<h3>채팅방 선택(플래너 선택)</h3>
     	</div>
        <div class="two">
        	<input type="text" name="planId">
        	<input type="submit" value="플래너입장">
        	<br/>
        	<input type="text" name="refId" placeholder="${user.userId }">
        	<input type="button" name="createRoom" value="채팅방생성">
        	<input type="button" name="checkRoom" value="채팅방 목록 확인">
        	<input type="button" name="joinRoom" value="채팅방 참여">
        	<input type="button" name="quitRoom" value="채팅방 강퇴">
        	<br/>
        	<input type="text" name="roomId" placeholder="입장할 채팅방 번호">
        	<input type="button" name="enterRoom" value="채팅방 입장">
        	
        	<script>
        	$("input[name='createRoom']").on("click", function(){
        		var refId = $("input[name='refId']").val();
        		console.log("채팅방 생성 시작");
        		
        		var chatRoom = new Object();
        		chatRoom.creator = '${user.userId}';
        		chatRoom.chatMems = [
       					'admin', 'user01'
        		];
        		chatRoom.chatRoomName = "테스트용 챗방ㅋ";
        		chatRoom.chatRoomId = "10013";
        		console.log(chatRoom);
        		console.log("");
        		 var formData = JSON.stringify(chatRoom);
        		 console.log(formData);

        		$.ajax({
        		url : "/chat/json/createRoom/",
				type : "POST",
				data : formData,
				headers : {
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				success : function() {
					console.log("채팅방 생성 성공");
				},
				error : function(error) {
					console.log("채팅방 생성 실패");
					console.log(error);
				}
			 });
        		
        	})
        	
        	$("input[name='checkRoom']").on("click", function(){
        		alert("채팅방 목록 확인");
        		var userId = '${user.userId}';
        		$.ajax({
            		url : "/chat/json/getChatRoomList/"+userId ,
    				type : "GET",
    				dataType : "json",
    				headers : {
    					"Accept" : "application/json",
    					"Content-Type" : "application/json"
    				},
    				success : function(result) {
    					console.log("채팅방 조회 성공");
    					console.log(result);
    				},
    				error : function(error) {
    					console.log("채팅방 조회 실패");
    					console.log(error);
    				}
    			 });
        	})
        	
        	$("input[name='joinRoom']").on("click", function(){
        		var refId = $("input[name='refId']").val();
        		console.log("채팅방 초대(참여) 시작");
        		
        		var chatRoom = new Object();
        		chatRoom.chatMems = [
       					'newFace'
        		];
        		chatRoom.chatRoomId = "10013";
        		
        		console.log(chatRoom);
        		console.log("");
        		 var formData = JSON.stringify(chatRoom);
        		 console.log(formData);

        		$.ajax({
        		url : "/chat/json/joinChatRoom/",
				type : "POST",
				data : formData,
				headers : {
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				success : function() {
					console.log("채팅방 초대(참여) 성공");
				},
				error : function(error) {
					console.log("채팅방 초대(참여) 실패");
					console.log(error);
				}
			 });
        		
        	})
        	
        	$("input[name='quitRoom']").on("click", function(){
        		var refId = $("input[name='refId']").val();
        		console.log("채팅방 강퇴 시작");
        		
        		var chatRoom = new Object();
        		chatRoom.chatMems = [
       					'newFace'
        		];
        		chatRoom.chatRoomId = "10013";
        		
        		console.log(chatRoom);
        		console.log("");
        		 var formData = JSON.stringify(chatRoom);
        		 console.log(formData);

        		$.ajax({
        		url : "/chat/json/quitChatRoom/",
				type : "POST",
				data : formData,
				headers : {
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				success : function() {
					console.log("채팅방 강퇴 성공");
				},
				error : function(error) {
					console.log("채팅방 강퇴 실패");
					console.log(error);
				}
			 });
        		
        	})
        	
        	$("input[name='enterRoom']").on("click", function(){
        		var roomId = $("input[name='roomId']").val();
        		console.log("채팅방 입장 : "+roomId);
        		
        		 	var roomChatAddr = "ws://192.168.0.82:8080/accSocket/"+roomId+"/{user.userId}";
			        var roomChatSocket = new WebSocket(roomChatAddr);
			        //웹 소켓이 연결되었을 때 호출되는 이벤트
			        accChatSocket.onopen = function(message){
			              console.log('[roomChat] : connection opened.')
			          	  //웹 소켓에서 메시지가 날라왔을 때 호출되는 이벤트
				          accChatSocket.onmessage = function(message){
			            	  console.log("roomChat 왔다 ::: "+message.data);
				        };
			            
			        };
			        //웹 소켓이 닫혔을 때 호출되는 이벤트
			        roomChatSocket.onclose = function(message){
			        	console.log("roomChat 접속이 끊어졌습니다.\n");
			        };
			        //웹 소켓이 에러가 났을 때 호출되는 이벤트
			        roomChatSocket.onerror = function(message){
			        	console.log("roomChat 에러가 발생했습니다.\n");
			        };
			        
			        //Send 버튼을 누르면 실행되는 함수
			        function sendRoomMessage(){
						var chat = new Object();
						chat.chatRoomId = '1000';
						chat.senderId = userId;
						chat.chatContent = document.getElementById("textAccMessage").value;
						console.log("[sendAccMessage] chatRoomId : "+chat.chatRoomId+" || senderId : "+chat.senderId+" || chatContent : "+chat.chatContent);
			        	
			            //웹소켓으로 textMessage객체의 값을 보낸다.
			            accChatSocket.send(JSON.stringify({chat}));
			            //textMessage객체의 값 초기화
			        }
			        //웹소켓 종료
			        function disconnectAcc(){
			        	roomChatSocket.close();
			        }
        		
        	})
        	
        	
        	</script>
       	</div>
       	<div class=three>
       	</div>
    </div>
</div>
</form>

</body>
</html>