<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
	<!-- ���� : http://getbootstrap.com/css/   ���� -->
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
   
   
   <!-- jQuery UI toolTip ��� CSS-->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <!-- jQuery UI toolTip ��� JS-->
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
        		<h3>ä�ù� ����(�÷��� ����)</h3>
     	</div>
        <div class="two">
        	<input type="text" name="planId">
        	<input type="submit" value="�÷�������">
        	<br/>
        	<input type="text" name="refId" placeholder="${user.userId }">
        	<input type="button" name="createRoom" value="ä�ù����">
        	<input type="button" name="checkRoom" value="ä�ù� ��� Ȯ��">
        	<input type="button" name="joinRoom" value="ä�ù� ����">
        	<input type="button" name="quitRoom" value="ä�ù� ����">
        	<br/>
        	<input type="text" name="roomId" placeholder="������ ä�ù� ��ȣ">
        	<input type="button" name="enterRoom" value="ä�ù� ����">
        	
        	<script>
        	$("input[name='createRoom']").on("click", function(){
        		var refId = $("input[name='refId']").val();
        		console.log("ä�ù� ���� ����");
        		
        		var chatRoom = new Object();
        		chatRoom.creator = '${user.userId}';
        		chatRoom.chatMems = [
       					'admin', 'user01'
        		];
        		chatRoom.chatRoomName = "�׽�Ʈ�� ê�椻";
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
					console.log("ä�ù� ���� ����");
				},
				error : function(error) {
					console.log("ä�ù� ���� ����");
					console.log(error);
				}
			 });
        		
        	})
        	
        	$("input[name='checkRoom']").on("click", function(){
        		alert("ä�ù� ��� Ȯ��");
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
    					console.log("ä�ù� ��ȸ ����");
    					console.log(result);
    				},
    				error : function(error) {
    					console.log("ä�ù� ��ȸ ����");
    					console.log(error);
    				}
    			 });
        	})
        	
        	$("input[name='joinRoom']").on("click", function(){
        		var refId = $("input[name='refId']").val();
        		console.log("ä�ù� �ʴ�(����) ����");
        		
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
					console.log("ä�ù� �ʴ�(����) ����");
				},
				error : function(error) {
					console.log("ä�ù� �ʴ�(����) ����");
					console.log(error);
				}
			 });
        		
        	})
        	
        	$("input[name='quitRoom']").on("click", function(){
        		var refId = $("input[name='refId']").val();
        		console.log("ä�ù� ���� ����");
        		
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
					console.log("ä�ù� ���� ����");
				},
				error : function(error) {
					console.log("ä�ù� ���� ����");
					console.log(error);
				}
			 });
        		
        	})
        	
        	$("input[name='enterRoom']").on("click", function(){
        		var roomId = $("input[name='roomId']").val();
        		console.log("ä�ù� ���� : "+roomId);
        		
        		 	var roomChatAddr = "ws://192.168.0.82:8080/accSocket/"+roomId+"/{user.userId}";
			        var roomChatSocket = new WebSocket(roomChatAddr);
			        //�� ������ ����Ǿ��� �� ȣ��Ǵ� �̺�Ʈ
			        accChatSocket.onopen = function(message){
			              console.log('[roomChat] : connection opened.')
			          	  //�� ���Ͽ��� �޽����� ������� �� ȣ��Ǵ� �̺�Ʈ
				          accChatSocket.onmessage = function(message){
			            	  console.log("roomChat �Դ� ::: "+message.data);
				        };
			            
			        };
			        //�� ������ ������ �� ȣ��Ǵ� �̺�Ʈ
			        roomChatSocket.onclose = function(message){
			        	console.log("roomChat ������ ���������ϴ�.\n");
			        };
			        //�� ������ ������ ���� �� ȣ��Ǵ� �̺�Ʈ
			        roomChatSocket.onerror = function(message){
			        	console.log("roomChat ������ �߻��߽��ϴ�.\n");
			        };
			        
			        //Send ��ư�� ������ ����Ǵ� �Լ�
			        function sendRoomMessage(){
						var chat = new Object();
						chat.chatRoomId = '1000';
						chat.senderId = userId;
						chat.chatContent = document.getElementById("textAccMessage").value;
						console.log("[sendAccMessage] chatRoomId : "+chat.chatRoomId+" || senderId : "+chat.senderId+" || chatContent : "+chat.chatContent);
			        	
			            //���������� textMessage��ü�� ���� ������.
			            accChatSocket.send(JSON.stringify({chat}));
			            //textMessage��ü�� �� �ʱ�ȭ
			        }
			        //������ ����
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