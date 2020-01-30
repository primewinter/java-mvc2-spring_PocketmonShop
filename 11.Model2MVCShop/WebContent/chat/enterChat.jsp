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

<form name = "detailForm" action="/chat/getPlan" method="POST">
<div class="container">
    <div class="wrapper">
        <div class="one">
        		<h3>ä�ù� ����(�÷��� ����)</h3>
     	</div>
        <div class="two">
        	<input type="text" name="planId">
        	<input type="submit" value="�÷�������">
       	</div>
       	<div class=three>
       	</div>
    </div>
</div>

</form>

</body>
</html>