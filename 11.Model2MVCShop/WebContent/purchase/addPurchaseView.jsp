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
    <style>
        body {
            padding-top: 100px;
        }

        .wrapper {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            grid-gap: 20px;
            grid-auto-rows: minmax(100px, auto);
            text-align: left;
        }

        .one {
            grid-column: 1;
            grid-row: 1 ;
        }

        .two {
            grid-column: 2 / 3;
            grid-row: 1 ;
            width: 500px;
            padding-top: 50px;
        }
        .three {
        	grid-column: 4;
            grid-row: 1/2 ;
            width: 400px;
        	padding: 40px;
        	background-color: powderblue;
        }

        .four {
            grid-column: 4;
            grid-row: 4;
        }
        
        .btn_ { 
        	border: 1px solid #333; 
        	background-color: #333; 
        	width: 100px;
        	color: white; 
        	padding: 5px; 
        }
        
        .align-left{
        	 float: left;
        }
        
        .align-right{
        	 float: right;
        }
        
        .align-center{
        	 text-align: center;
        }
        
        .table_menu{
        	width: 120px;
        	height: 30px;
        }
        .row {
        }

    </style>
 <script language='javascript'>
 
 function onlyNumber(event){
	 
	    event = event || window.event;
	    var keyID = (event.which) ? event.which : event.keyCode;
	    if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
	        return;
	    else
	        return false;
	}
	 
	function removeChar(event) {
	    event = event || window.event;
	    var keyID = (event.which) ? event.which : event.keyCode;
	    if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
	        return;
	    else
	        event.target.value = event.target.value.replace(/[^0-9]/g, "");
	}
 
	function call(){
		var totalAmount = 0;
		var price = 0;
		var quantity = 0;
		
		$('input[name="price"]').each(function(i, pp) {
			$('input[name="quantity"]').each(function(j, pq) {
				if(i == j) {
					totalAmount += $(pp).val()*$(pq).val();
					/* alert("금액 : "+$(pp).val() +"\n수량 : "+ $(pq).val() +"\n총액 : "+totalAmount); */		
				};
			});
		});
		
		var pointPay = $("#pointPay").val();
		var actualAmount = totalAmount-pointPay;
		$('.actualAmount').html("<h4><span class='align-left'>합계</span> <span class='align-right'>&#8361; "+actualAmount+"</span></h4>");
		$('#totalAmount').val(totalAmount);
		$('#actualAmount').val(actualAmount);
	}

</script>
<script type="text/javascript">

$(function() {
	/* 결제 금액 default 값 주는 로직 */
	var totalAmount = 0;
		var price = 0;
		var quantity = 0;
		
		$('input[name="price"]').each(function(i, pp) {
			$('input[name="quantity"]').each(function(j, pq) {
				if(i == j) {
					totalAmount += $(pp).val()*$(pq).val();
					/* alert("금액 : "+$(pp).val() +"\n수량 : "+ $(pq).val() +"\n총액 : "+totalAmount); */		
				};
			});
		});
		
		var pointPay = $("#pointPay").val();
		var actualAmount = totalAmount-pointPay;
		$('.actualAmount').html("<h4><span class='align-left'>합계</span> <span class='align-right'>&#8361; "+actualAmount+"</span></h4>");
		$('#totalAmount').val(totalAmount);
		$('#actualAmount').val(actualAmount);
	/* 결제 금액 default 값 주는 로직 끝*/
	
	$( "button:contains('B U Y')").on("click" , function() {
		if( $('#paymentOption').val() == 'credit' ){
				fncKakaoPay();
		} else {
		$("form").attr("method" , "POST").attr("action" , "/purchase/addPurchase").submit();
		}
	});
	
	$( "button:contains('R E S E T')" ).on("click" , function() {
		$("form")[0].reset();
	});
	
	$(document).on('click', 'a[href="#"]', function(e) {
		e.preventDefault();
	});
	
	$("input:button").on("click", function() {
		call();
	});
	
	$("#price").on("keyup", function() {
		call();
	});
	
	
	$("#pointPay").on("keydown", function() {
		var val= $(this).val();
		 if( val > '${user.point}' ) {
		        $(this).val('${user.point}');
		 }
	})
	.on("keydown", event, function() {
		 event = event || window.event;
		    var keyID = (event.which) ? event.which : event.keyCode;
		    if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
		        return;
		    else
		        return false;
	/* 	this.value = this.value.replace(/\D/g, '');
		if (this.value > ${user.point}) this.value = ${user.point};
		
		return onlyNumber(event); */
	});
	
	
});
</script>
</head>
<body>

	<jsp:include page="/layout/toolbar.jsp" />
	
<form name="detailForm">
<c:forEach var="basketNo" items="${basketNoArr}">
<input type="hidden" name="basketNo" value="${basketNo}">
</c:forEach>


<div class="container">
		    <div class="wrapper">
				    <div class="one"></div>
				    <div class="two">
				    			<hr>
				    			<c:forEach var="product" items="${productList}">
				    			<input type="hidden" name="prodNo" value="${product.prodNo}">
				    			<input type="hidden" name="quantity" value="${product.quantity}">
				    			<input type="hidden" name="price" value="${product.price}">
				    			<div class="row">
									  <div class="col-md-12" >
									  		<table style="margin:auto;width:100%">
									  		<tr>
									  			<td width="20%">
												<img src ="/images/uploadFiles/${product.fileNameArr[0]}" width=50>
												</td>
												<td width="60%">
										  			<h4>${product.prodName}</h4>
										  			<p>${product.prodDetail}</p>
										  			<p>수량 ${product.quantity}<input type="hidden" name="quantity" value="${product.quantity}"></p>
							  					<td>
							  					<td width="20%" style="text-align:right;">
										 &#8361; ${product.price*product.quantity}
										 		</td>
										 	</tr>
											</table>
											<hr>
										</div>
								</div>
								</c:forEach>
				    </div>
				    <div class="three">
				    			<div class="row">
				    				
				    				<div class="row">
				    					<div class="col-md-12"> 
				    						<h4>배송정보</h4>
				    						<hr>
				    						<table>
				    							<tr>
				    								<td class="table_menu">수령인 이름</td>
				    								<td><input type="text" name="receiverName" value="${user.userName}" class="ct_input_g"></td>
				    							</tr>
				    							<tr>
				    								<td class="table_menu">수령인 연락처</td>
				    								<td><input type="text" name="receiverPhone" value="${user.phone}" class="ct_input_g"></td>
				    							</tr>
				    							<tr>
				    								<td class="table_menu">수령인 주소</td>
				    								<td><input type="text" name="address" value="${user.addr}" class="ct_input_g"></td>
				    							</tr>
				    							<tr>
				    								<td class="table_menu">배송 요청 사항</td>
				    								<td><input type="text" name="request" class="ct_input_g"style="width:100px; height:19px" /></td>
				    							</tr>
				    						</table>
				    					</div>
				    				</div>
				    				
				    			<div class="row" style="margin-top:20px">
				    					<div class="col-md-12"> 
				    						<h4>결제정보</h4>
				    						<hr>
				    						<table>
				    							<tr>
				    								<td class="table_menu">결제 방법</td>
				    								<td>
					    								<select 	name="paymentOption" class="ct_input_g" id="paymentOption">
															<option value="cash" >현금구매</option>
															<option value="credit" >신용카드</option>
															<option value="banking" >무통장입금</option>
														</select>
													</td>
				    							</tr>
				    							<tr>
				    								<td class="table_menu">포인트 사용</td>
				    								<td><input type="text" name="pointPay" id="pointPay" value='0' class="ct_input_g" style="text-align:right; width:80px;"> <input type='button' value='적용' class="ct_input_g"/></td>
				    							</tr>
				    							<tr>
				    								<td class="table_menu"></td>
				    								<td><font size="small">(사용 가능 포인트 : ${user.point})</font></td>
				    						</table>
				    						<p>
				    						<input type="hidden" name="actualAmount" id="actualAmount" value=""readonly>
				    						<input type="hidden" name="totalAmount" id="totalAmount" value="">
										<hr>
											<p class="actualAmount" style="text-align:right;"></p>
											<br/><br/><br/><br/>
											<p class="align-center"><button type="button" class="btn_">B U Y</button> <button type="button" class="btn_">R E S E T</button></p>
				    					</div>
				    			</div>
				    			
				    </div>
				    <div class="four"></div>
    </div>
    
   </div>
   
</div>

</form>
</body>
</html>