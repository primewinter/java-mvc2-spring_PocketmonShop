<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
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
        
        #chat_box {
		    width: 500px;
		    min-width: 500px;
		    height: 500px;
		    min-height: 500px;
		    border: 1px solid black;
		}
		#msg {
			width: 500px;
		}
		#msg_process {
	  		 width: 90px;
		}

     </style>
	
</head>

<body>

	<jsp:include page="/layout/toolbar.jsp" />

<form name = "detailForm">
<div class="container">
    <div class="wrapper">
        <div class="one">
        		<h3>����ä��</h3>
     	</div>
        <div class="two">
         <!-- �۽� �޽��� �ۼ��ϴ� â -->
			        <input id="textMessage" type="text">
			        <!-- �۽� ��ư -->
			        <input onclick="sendMessage()" value="Send" type="button">
			
			        <!-- ���� ��ư -->
			        <input onclick="disconnect()" value="Disconnect" type="button">
				    <br />
				    <!-- ��� �޽��� �����ִ� â -->
				    <textarea id="messageTextArea" rows="10" cols="50"></textarea>
				    <script type="text/javascript">
				    
					  	var roomId = "1000/";
				    	var userId = "${user.userId}";
				        var chatAddr = "ws://192.168.0.82:8080/chatSocket/1000/"+userId;
				        var chatSocket = new WebSocket(chatAddr);
				        var messageTextArea = document.getElementById("messageTextArea");
				        //�� ������ ����Ǿ��� �� ȣ��Ǵ� �̺�Ʈ
				        chatSocket.onopen = function(message){
				            console.log("���� ä�� ����")
				         	 //�� ���Ͽ��� �޽����� ������� �� ȣ��Ǵ� �̺�Ʈ
					        chatSocket.onmessage = function(message){
					            console.log("chat �޽��� �Դ�.")
				            	var chat = message.data;
					            messageTextArea.value += chat.chatContent+"\n";
					        };
				            
				        };
				        //�� ������ ������ �� ȣ��Ǵ� �̺�Ʈ
				        chatSocket.onclose = function(message){
				        	console.log("���� ������")
				            messageTextArea.value += "chat ������ ���������ϴ�.\n";
				        };
				        //�� ������ ������ ���� �� ȣ��Ǵ� �̺�Ʈ
				        chatSocket.onerror = function(message){
				        	console.log("���� ����")
				            messageTextArea.value += "chat ������ �߻��߽��ϴ�.\n";
				        };
				        
				        //Send ��ư�� ������ ����Ǵ� �Լ�
				        function sendMessage(){
				        	var chat = new Object();
				        	chat.senderId = "${user.userId}";
				        	chat.chatContent = document.getElementById("textMessage").value;
				        	console.log("���� �޽��� : "+chat.chatContent);
				        	chat.chatRoomId = '1000';
				        	
				            //messageTextArea.value += "Send to Server => "+message.value+"\n";
				            //���������� textMessage��ü�� ���� ������.
				            chatSocket.send(JSON.stringify({chat}));
				            //textMessage��ü�� �� �ʱ�ȭ
				            $("#textMessage").val('');
				        }
				        //������ ����
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