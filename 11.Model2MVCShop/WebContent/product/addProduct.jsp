<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="ko">


<head>

<meta charset="EUC-KR">
	<!-- 복사본 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500&display=swap" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    
    <!-- 복사본 -->
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
         .wrapper {
            display: grid;
            grid-template-columns: repeat(5, 200px);
            grid-gap: 50px;
            grid-auto-rows: minmax(100px, auto);
            margin: 30px;
        }

        .one {
            grid-column: 1;
            grid-row: 1;
        }

        .two {
            grid-column: 2 / 4;
            grid-row: 1 / 3;
            background-color: powderblue;
            padding: 20%;
        }

        .three {
            grid-column: 4;
            grid-row: 1 / 2 ;
            padding-top: 30%;
            text-align: left;
        }

        .four {
            grid-column: 4;
            grid-row: 2 / 3;
            padding-top: 10%;
            text-align: left;
        }

        .five {
            grid-column: 5;
            grid-row: 1;
        }
        
        .btn_buy { 
        	border: 1px solid #333; 
        	background-color: #333; 
        	width: 250px;
        	color: white; 
        	padding: 5px; 
        }


		body {
            padding-top : 100px;
             font-family:  'Noto Sans KR', sans-serif;
        }
     </style>
	<script type="text/javascript">
	 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			//==> 1 과 3 방법 조합 : $("tagName.className:filter함수") 사용함.	
			 $( "td.ct_btn01:contains('확인')" ).on("click" , function() {
				//Debug..
				//alert(  $( "td.ct_btn01:contains('가입')" ).html() );
				self.location = "/product/listProduct"
			});
			
			
			 $( "td.ct_btn01:contains('추가등록')" ).on("click" , function() {
					//Debug..
					//alert(  $( "td.ct_btn01:contains('가입')" ).html() );
					self.location = "/product/addProductView.jsp"
				});
				
			
		});	
	 
	</script>

</head>

<body>

	<jsp:include page="/layout/toolbar.jsp" />

<div class="container">
    <div class="wrapper">
        <div class="one"></div>
        <div class="two">
        
        		<br/>
		        	<c:forEach var="item" items="${product.fileNameArr}">
					<img src ="/images/uploadFiles/${item}" width=200><br/>
		       		</c:forEach>
       	
       	</div>
        <div class="three">
         <div class="affix">
	          	<h4>${product.prodName}</h4>
	        	<p>${product.prodDetail}</p>
		        <p>birth/ ${product.manuDate}</p>
		        <hr>
	        	<p>&#8361; ${product.price}</p>
	        	<br>
	          	<p><input type='text' class="ct_input_g" name='quantity' style = "text-align:center;" id="quantity" size='1' value='1' readonly><a href='#' class="bt_up">&ensp;<i class="glyphicon glyphicon-chevron-up"></i></a>  &ensp; <a href='#' class="bt_down" ><i class="glyphicon glyphicon-chevron-down"></i></a></p>
      			<button type="button" class="btn_">확인</button>
      			<p><br/> <a href="#" class="addBasket"><i class="glyphicon glyphicon-shopping-cart"></i> 장바구니</a></p>
      			<p><a href="#"><i class="glyphicon glyphicon-pencil"></i> 추가등록</a></p>
        </div>
	
	 </div>
		        
        <div class="four">
		</div>      
        <div class="five"><!-- 공백 --></div>
    </div>
</div>		



</body>
</html>

