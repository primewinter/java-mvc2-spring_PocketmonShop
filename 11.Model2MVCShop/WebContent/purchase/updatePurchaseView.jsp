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
            grid-column: 2 / 4;
            grid-row: 1 ;
            border: 1px;
            border-color: powderblue;
             /* background-color: powderblue; */
        }

        .five {
            grid-column: 4;
            grid-row: 1;
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
        	 font-size:14pt;
        }
        
        .align-right{
        	 float: right;
        	 font-size:14pt;
        }
        
        .btn_  { 
        	border: 1px solid #333; 
        	background-color: #333; 
        	width: 100px;
        	color: white; 
        	padding: 5px; 
        }

    </style>
<script type="text/javascript">


$(function() {
	 $( ".btn_:contains('수정')" ).on("click" , function() {
		 $("form").attr("method" , "POST").attr("action" , "/purchase/updatePurchase").submit();
	 });
	
	 $( ".btn_:contains('리셋')" ).on("click" , function() {
			 $("form")[0].reset();
	 });
});
</script>
</head>

<body>

	<jsp:include page="/layout/toolbar.jsp" />
	

<form name="detailForm">
<input type="hidden" name="tranNo" value="${purchase.tranNo }">

<div class="container">
    <div class="wrapper">
        <div class="one"></div>
        <div class="two">

        		<div class="row" style="background:powderblue;padding:15px;">
						
						<h4 style="margin-left:20px;margin-top: 25px">배송정보 변경</h4>			
					
						<div class="row" style="margin-left: 15px;margin-right: 15px;">
							<hr>			
								  <div class="col-md-3">받는 분</div>
								  <div class="col-md-9"><input type="text" name="receiverName" value="${purchase.dlvyInfo.receiverName}" class="ct_input_g" 
								style="width:100px; height:19px"  maxLength="50" ></div>
						</div>
						
						<div class="row" style="margin: 15px;">
								  <div class="col-md-3">주소</div>
								  <div class="col-md-9"><input type="text" name="address" value="${purchase.dlvyInfo.address }" class="ct_input_g" 
								style="width:300px; height:19px" ></div>
						</div>
						
						<div class="row" style="margin: 15px;">
								  <div class="col-md-3">받는 분 연락처</div>
								  <div class="col-md-9"><input 	type="text" name="receiverPhone" value="${purchase.dlvyInfo.receiverPhone}" class="ct_input_g" 
												style="width:100px; height:19px"></div>
						</div>
						
						<div class="row" style="margin: 15px;">
								  <div class="col-md-3">배송 시 요청사항</div>
								  <div class="col-md-9"><input type="text" name="request" value="${purchase.dlvyInfo.request }" class="ct_input_g" 
								style="width:300px; height:19px" ></div>
						</div>
						
						<div class="row" style="margin-top: 30px;margin-left: 30px;margin-bottom: 20px;">
								<div class="col-md-3"></div>
								<div class="col-md-6">
										<button type="button" class="btn_">수정</button>
       									<button type="button" class="btn_">리셋</button>
     							</div>
						</div>
					
						<h4 style="margin-left: 20px;margin-top: 30px">결제 상품</h4>			
						
						<div class="row" style="margin-left: 15px;margin-right: 15px;">
							<hr>			
								<div class="col-md-3">주문번호</div>
						  		<div class="col-md-9">${purchase.tranNo}</div>
						</div>
						<div class="row" style="margin: 15px;">
								<div class="col-md-3">결제 금액</div>
						  		<div class="col-md-9">&#8361; ${purchase.payment.actualAmount}</div>
						</div>
						<div class="row" style="margin: 15px;">
								<div class="col-md-3">주문상품</div>
						  		<div class="col-md-9">${purchase.purchaseProd[0].prodName}  <c:if test="${purchase.purchaseProd.size()>1}">외 ${purchase.purchaseProd.size()-1} 건</c:if></div>
						</div>
				</div>
				
        </div>
        <div class="five">
        </div>
</div>
</div>


</form>

</body>
</html>