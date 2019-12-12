<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="ko">


<head>

<meta charset="EUC-KR">
	<!-- ���纻 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500&display=swap" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    
    <!-- ���纻 -->
	
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
        
        .row {
        }

    </style>
<script type="text/javascript">
$(function() {

	$( "p:contains('���')" ).on("click" , function() {
		var cancel = confirm("���� �ֹ��� ����Ͻðڽ��ϱ�?");
		if( cancel == true ) {
			$("form").attr("method", "get").attr("action", "/purchase/cancelPurchase").submit();
		} else if ( cancel == false ) {
			
		}
	});
	
	$("p").mouseout( function() {
 		$(this).css({'background-color':"white"});
 	}).mouseover( function(){
 		$(this).css({'background-color':'powderblue'});
 	});
	
	
	$( "p:contains('ȯ��')" ).on("click" , function() {
		var cancel = confirm("���� ȯ���� ��û�Ͻðڽ��ϱ�?");
		if( cancel == true ) {
			$("form").attr("method", "get").attr("action", "/purchase/requestRefund").submit();
		} else if ( cancel == false ) {
			
		}
	});
	
	$( "p:contains('����')" ).on("click" , function() {
		 $("form").attr("method", "get").attr("action", "/purchase/updatePurchase").submit();
	});
	
	 $( "p:contains('Ȯ��')" ).on("click" , function() {
		history.go(-1);
	});


	
});
</script>
</head>

<body>

	<jsp:include page="/layout/toolbar.jsp" />

<form name="detailForm" >
<input type="hidden" name="tranNo" value="${purchase.tranNo}">


<div class="container">
    <div class="wrapper">
        <div class="one"></div>
        <div class="two">

        		<div class="row">
						  <div class="col-md-6"><span class="align-left">
						  ${purchase.purchaseProd[0].prodName}  <c:if test="${purchase.purchaseProd.size()>1}">�� ${purchase.purchaseProd.size()-1} ��</c:if></span>
						  <br/><br/>
						  <h5 style="color:red;">${ purchase.tranCode=='�ֹ����' || purchase.tranCode=='ȯ�ҿϷ�' || purchase.tranCode=='ȯ�� ��û' || purchase.tranCode=='ó����' ? purchase.tranCode  : '' }</h5>
						  ${purchase.payment.paymentOption}
						  </div>
						  <div class="col-md-6"><span class="align-right"> &#8361; ${purchase.payment.actualAmount}</span></div>
				</div>
				<hr>
				<div class="row" style="margin-bottom: 15px" >
						  <div class="col-md-3">�ֹ���ȣ</div>
						  <div class="col-md-9">${purchase.tranNo}</div>
				</div>
				<div class="row" >
						  <div class="col-md-3">�����Ͻ�</div>
						  <div class="col-md-9">${purchase.payment.orderDate}</div>
				</div>
				<hr>
				<div class="row" style="margin-top: 20px;margin-bottom: 10px;">
						  <div class="col-md-2">����</div>
						  <div class="col-md-6"  style="text-align:center">�׸�</div>
						  <div class="col-md-4" style="text-align:right">����</div>
				</div>
				<hr>
				<c:forEach var="purchaseProd" items="${purchase.purchaseProd}">
				<div class="row" style="margin-top: 5px;margin-bottom: 5px;">
						  <div class="col-md-2">${purchaseProd.quantity } </div>
						  <div class="col-md-3"  style="text-align:right"><c:forEach var="item" items="${purchaseProd.fileNameArr}">
							<img src ="/images/uploadFiles/${item}" height=30>
		        			</c:forEach></div>
						  <div class="col-md-3"  style="text-align:left">${purchaseProd.prodName}</div>
						  <div class="col-md-4"  style="text-align:right">&#8361; ${purchaseProd.price*purchaseProd.quantity}</div>
				</div>
       		 	</c:forEach>
       		 	<hr>
				<div class="row" style="margin-top: 10px;margin-bottom: 10px;">
						  <div class="col-md-6"></div>
						  <div class="col-md-3"  style="text-align:right">�ֹ� �հ�</div>
						  <div class="col-md-3" style="text-align:right">&#8361; ${purchase.payment.totalAmount}</div>
				</div>
				
				<div class="row" style="margin-top: 10px;margin-bottom: 10px;">
						  <div class="col-md-6"></div>
						  <div class="col-md-3"  style="text-align:right">( - ) ���� �ݾ�</div>
						  <div class="col-md-3" style="text-align:right">&#8361; ${purchase.payment.pointPay}</div>
				</div>
				<hr>
				<div class="row"  style="margin-top: 5px;margin-bottom: 10px;">
						  <div class="col-md-6"></div>
						  <div class="col-md-3"  style="text-align:right">�� ���� �ݾ� </div>
						  <div class="col-md-3" style="text-align:right">&#8361; ${purchase.payment.actualAmount}</div>
				</div>
				<hr>
				<div class="row" style="margin-bottom: 15px">
						  <div class="col-md-3">�޴� ��</div>
						  <div class="col-md-9">${purchase.dlvyInfo.receiverName}</div>
				</div>
				
				<div class="row" style="margin-top: 10px;margin-bottom: 15px">
						  <div class="col-md-3">�ּ�</div>
						  <div class="col-md-9">${purchase.dlvyInfo.address}</div>
				</div>
				
				<div class="row" style="margin-top: 10px;margin-bottom: 15px">
						  <div class="col-md-3">�޴� �� ����ó</div>
						  <div class="col-md-9">${purchase.dlvyInfo.receiverPhone}</div>
				</div>
				
				<div class="row" style="margin-top: 10px;margin-bottom: 15px">
						  <div class="col-md-3">��� �� ��û����</div>
						  <div class="col-md-9">${purchase.dlvyInfo.request}</div>
				</div>
				<hr>
        </div>
        <div class="five">
        	<p>Ȯ��</p>
			<c:if test="${purchase.tranCode eq '�ֹ��Ϸ�'}">
						<p>��� ���� ����</p>
			</c:if>
        	
        	<c:if test="${purchase.tranCode eq '�ֹ��Ϸ�'}">
						<p>�ֹ� ���</p>
			</c:if>
      		<c:if test="${purchase.tranCode eq '�����' || purchase.tranCode eq '��ۿϷ�'}">
						<p>ȯ�� ��û</p>
			</c:if>

			
        </div>

</div>
</div>

</form>

</body>
</html>
