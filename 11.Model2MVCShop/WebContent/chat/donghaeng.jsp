<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

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
	 <script src="http://localhost:82/socket.io/socket.io.js"></script>

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
			       <div id="chat_box"></div>
				    <input type="text" id="msg">
				    <button id="msg_process">����</button>
				     <script>
				     $(document).ready(function(){
			                var chatSocket = io("http://localhost:82");
			                
			                //msg���� Ű�� ������
			                $("#msg").keydown(function(key){
			                    //�ش��ϴ� Ű�� ����Ű(13) �϶�
			                    if(key.keyCode == 13){
			                        //msg_process�� Ŭ�����ش�.
			                        msg_process.click();
			                    }
			                });
			                
			                //msg_process�� Ŭ���� ��
			                $("#msg_process").click(function(){
			                    //���Ͽ� send_msg��� �̺�Ʈ�� input�� #msg�� value�� ��� �����ش�.
			                     chatSocket.emit("send_msg", $("#msg").val());
			                    //#msg�� �������� ����ش�.
			                    $("#msg").val("");
			                });
			                
			                //���� ������ ���� send_msg�� ���� �̺�Ʈ�� ���� ��� 
			                chatSocket.on('send_msg', function(msg) {
			                    //div �±׸� ����� �ؽ�Ʈ�� msg�� ������ �ѵ� #chat_box�� �߰��� �����ش�.
			                    $('<div></div>').text(msg).appendTo("#chat_box");
			                });


			            });


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