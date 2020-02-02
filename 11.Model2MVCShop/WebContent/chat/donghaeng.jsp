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
							 // events 모듈 사용
						    var events = require('events');
		
						    // EventEmitter 객체 생성
						    var eventEmitter = new events.EventEmitter();
		
						    // EventHandler 함수 생성
						    var connectHandler = function connected(){
						        console.log("Connection Successful");
						        
						        // data_recevied 이벤트를 발생시키기
						        eventEmitter.emit("data_received");
						    }
		
						    // connection 이벤트와 connectHandler 이벤트 핸들러를 연동
						    eventEmitter.on('connection', connectHandler);
		
						    // data_received 이벤트와 익명 함수와 연동
						    // 함수를 변수안에 담는 대신에, .on() 메소드의 인자로 직접 함수를 전달
						    eventEmitter.on('data_received', function(){
						        console.log("Data Received");
						    });
		
						    // connection 이벤트 발생시키기
						    eventEmitter.emit('connection');
		
						    console.log("Program has ended");
				    </script>
       	</div>
       	<div class=three>
       		<ul class="chat_box">
		    </ul>
       	</div>
    </div>
</div>

</form>

</body>
</html>