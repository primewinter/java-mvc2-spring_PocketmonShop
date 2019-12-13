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
				$(".addBasket").on("click", function() {
					
					var prodNo = '${product.prodNo}';
					var quantity = $("#quantity").val();
					var userId = '${user.userId}';
					if (userId != null && userId != "" ) {
						//alert("1:"+userId +"  prodNo : "+prodNo+" quantity:"+quantity);
					} else if (userId == "") { 
						userId = $('input[name="ip"]').val();
						//alert("2:"+userId+"  prodNo : "+prodNo+" quantity:"+quantity);
					}
					
					$.ajax (
							{
								url : "/basket/json/addBasket/" ,
								method : "POST" ,
								dataType : "json" ,
								data : JSON.stringify({"quantity": quantity, "user": userId, "prodNo":prodNo}),
								headers : {
									"Accept" : "application/json" ,
									"Content-Type" : "application/json"
								},
								success : function(data, status) {
									if( data == true) {
										var con_test = confirm('장바구니에 담았습니다. 바로 확인하시겠습니까?');
										if(con_test == true) {
											location.replace('/basket/listBasket');
										} else {
										}
									}
								}
							});
					
				});
			
				$("a[href='#']:contains('수정')").on("click", function() {
					var prodNo = '${product.prodNo}';
					self.location = "/product/updateProduct?prodNo="+prodNo.trim();
				});
				 
				 $(".bt_up").on('click', function(){
					 var num = $("#quantity").val();
					 num = Number(num)+1;
					 $("#quantity").val(num);
					 
			     });
				 
				 $(".btn_buy").on("click", function() {
					var quantity = $("#quantity").val();
					var url = '/purchase/addPurchase?prodNo=${product.prodNo}&quantity='+quantity;
					location.replace(url);
				 });
		
			 	$(".bt_down").on('click', function() {
					 var num = $("#quantity").val();
					 num = Number(num) - 1;
					 if( num < 1) {
						 num = 1;
					 } 
					 $("#quantity").val(num);
				 });
				
				$(document).on('click', 'a[href="#"]', function(e) {
					e.preventDefault();
				});
			
		});
	
		
		 function addBasket() {
			$('form').attr("target", "iframe").attr("method", "GET").attr("action", "/basket/addBasket").submit();
		
			var con_test = confirm('장바구니에 담았습니다. 바로 확인하시겠습니까?');
			if(con_test == true) {
				location.replace('/basket/listBasket');
			} else if (con_test == false) {

			}
			
		}
		 
		 
		 $(function() {
				 $( "td.ct_btn01:contains('목록으로')" ).on("click" , function() {
					history.go(-1);
				});
		});
		
		$(function() {
				 $( "div.row>div.col-xs-8 col-md-4:contains('장바구니')" ).on("click" , function() {
					 alert("ㅇㅇ");
					addBasket();
				});
		});
		
		$(function() {
				 $( "td.ct_btn01:contains('구매')" ).on("click" , function() {
					var quantity = $("#quantity").val();
					alert(quantity);
					var url = '/purchase/addPurchase?prodNo=${product.prodNo}&quantity='+quantity;
					location.replace(url);
				});
		});
		 
		 
 </script>
</head>

<body>

	<jsp:include page="/layout/toolbar.jsp" />

<form name = "detailForm">
<input type="hidden" name="prodNo" value="${product.prodNo }">
<c:if test="${sessionScope.user == null }">
<input type="hidden" name="ip" value=" <%=request.getRemoteAddr()%>">
</c:if>

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
      			<button type="button" class="btn_buy">B U Y</button>
      			<p><br/> <a href="#" class="addBasket"><i class="glyphicon glyphicon-shopping-cart"></i> 장바구니</a></p>
      			<c:if test="${user.role=='admin' }">
      			<p><a href="#"><i class="glyphicon glyphicon-pencil"></i> 수정</a></p>
      			</c:if>
        </div>
        		
       </div>
		        
        <div class="four">
		</div>      
        <div class="five"><!-- 공백 --></div>
    </div>
		

</div>

</form>

</body>
</html>