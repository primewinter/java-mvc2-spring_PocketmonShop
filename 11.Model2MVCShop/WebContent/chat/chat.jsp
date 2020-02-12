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
	<!-- ä�� node js Server	 -->
	 <!-- <script src="http://localhost:82/socket.io/socket.io.js"></script> -->
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

<form name = "detailForm">
<div class="container">
    <div class="wrapper">
        <div class="one">
        		<h3>ä��</h3>
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
				    
				        var chatAddr = "ws://192.168.0.82:8080/chatSocket/${plan.planId}/${user.userId}";
				        var chatSocket = new WebSocket(chatAddr);
				        var messageTextArea = document.getElementById("messageTextArea");
				        //�� ������ ����Ǿ��� �� ȣ��Ǵ� �̺�Ʈ
				        chatSocket.onopen = function(message){
				            
				          //�� ���Ͽ��� �޽����� ������� �� ȣ��Ǵ� �̺�Ʈ
					        chatSocket.onmessage = function(message){
					            messageTextArea.value += message.data+"\n";
					            console.log("chat �޽��� �Դ�.")
					        };
				            
				        };
				        //�� ������ ������ �� ȣ��Ǵ� �̺�Ʈ
				        chatSocket.onclose = function(message){
				            messageTextArea.value += "chat ������ ���������ϴ�.\n";
				        };
				        //�� ������ ������ ���� �� ȣ��Ǵ� �̺�Ʈ
				        chatSocket.onerror = function(message){
				            messageTextArea.value += "chat ������ �߻��߽��ϴ�.\n";
				        };
				        
				        //Send ��ư�� ������ ����Ǵ� �Լ�
				        function sendMessage(){
				        	var user = "${user.userId} : ";
				            var message = document.getElementById("textMessage");
				            //messageTextArea.value += "Send to Server => "+message.value+"\n";
				            //���������� textMessage��ü�� ���� ������.
				            chatSocket.send(user+message.value);
				            //textMessage��ü�� �� �ʱ�ȭ
				            message.value = "";
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