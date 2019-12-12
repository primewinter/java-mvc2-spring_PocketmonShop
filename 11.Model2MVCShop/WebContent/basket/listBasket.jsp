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
	  body {
            padding-top : 100px;
            font-family:  'Noto Sans KR', sans-serif;
        }
      button { 
        	border: 1px solid #333; 
        	background-color: #333; 
        	color: white; 
        	padding: 5px; 
        }
        
        .row {
        	vertical-align: middle;
        }
        input:button { 
        	border: 1px solid #333; 
        	background-color: #333; 
        	color: white; 
        	padding: 5px; 
        }
    </style>
<script type="text/javascript">


function CheckAll(chkBasketNo){
	
	if( document.detailForm.chkAll.checked == true ) {
		for (i = 0; i < chkBasketNo.length; i++){
			chkBasketNo[i].checked = true ;
		}
	} else {
		for (i = 0; i < chkBasketNo.length; i++){
			chkBasketNo[i].checked = false;
		}
	}
}

function fncGetList(currentPage) {
		$("#currentPage").val(currentPage);
		$("form").attr("method" , "POST").attr("action" , "/basket/listBasket").submit();
}

function BuyBasket(){
	$("#currentPage").val('${resultPage.currentPage}');
	$("form").attr("method" , "POST").attr("action" , "/basket/buyBasket").submit();
}

function DeleteBasket(currentPage) {
	$("#currentPage").val(currentPage);
	$("form").attr("method" , "POST").attr("action" , "/basket/deleteBasket").submit();
}

$(function() {
	//==> 검색 Event 연결처리부분
	 $( "input[name='buy']" ).on("click" , function() {
		var chkbox = $(	$($($(this).parent()).parent()).parent()).children().find('[name=chkBasketNo]');
		chkbox.prop('checked', true);
		BuyBasket();
	});
	
	$(".link>img").on("click", function() {
		var box = $(this).parent().find("[name=chkBasketNo]");
		if( box.checked == false ) {
			box.prop('checked', true);
		} else {
			box.prop('checked', false);
		}
	});
	
	 $( "input[name='delete']" ).on("click" , function() {
			var currentPage = '${resultPage.currentPage}';
			var chkbox = $(	$($($(this).parent()).parent()).parent()).children().find('[name=chkBasketNo]');
			chkbox.prop('checked', true);
			DeleteBasket(currentPage);
	});

	//==> 검색 Event 연결처리부분
	 $( ".ct_btn01" ).on("click" , function() {
		$("#currentPage").val("1")
		$("form").attr("method" , "POST").attr("action" , "/basket/listBasket").submit();
	});
	
		$(".basket").mouseout( function() {
	 		$(this).css({'background-color':"white"});
	 	}).mouseover( function(){
	 		$(this).css({'background-color':'powderblue'});
	 	});
	
});


</script>


</head>

<body>

<jsp:include page="/layout/toolbar.jsp" />

<form name="detailForm" method="post">
<input type="hidden" id="sort" name="sort" value="">
<input type="hidden" id="tranNo" name="tranNo" value="">
<input type="hidden" name="list" value="sale">
 
<div class="container">
	
		<div class="page-header text-info">
	       <h4>${user.userName } 님께서 선택하신 포켓몬은 총 ${resultPage.totalCount }마리입니다.</h4>
	    </div>
		
		
		<div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		현재 ${resultPage.currentPage}  페이지
		    	</p>
		    </div>
		    
		    <div class="col-md-6 text-right">
	    	</div>
	    	
		</div>

<div class="container-fluid">
      
        <div class="row" style="text-align:right;">
          	선택한 포켓몬을  
            <a href="javascript:BuyBasket();" style="font-color:powderblue;"><button type="button" class="btn_buy">주문</button></a>
            <a href="javascript:DeleteBasket( ${resultPage.currentPage});" style="text-decoration: none"><button type="button" class="btn_buy">삭제</button></a>        
        </div>
		<hr>
		<div class="row" style="text-align:center;padding:0.5em">
							<div class="col-md-1" style="text-align:left;">
										<input type="checkbox" name="chkAll" onclick="CheckAll(document.detailForm.chkBasketNo)"/>
							</div>
							<div class="col-md-2" style="text-align:center;">
										포켓몬
							</div>
							<div class="col-md-2" style="text-align:center;">
										이름
							</div>
							<div class="col-md-1" style="text-align:center;">
										가격
							</div>
							<div class="col-md-1" style="text-align:center;">
										수량
							</div>
							<div class="col-md-2" style="text-align:center;">
										합계
							</div>
							<div class="col-md-3" style="text-align:center;">
							</div>
		</div>
		<hr>
		<c:set var="i" value="0" />
		<c:forEach var="basket" items="${list}">
		<c:set var="i" value="${ i+1 }" />
				 <div class="row basket" style="padding:0.5em">	
							<div class="col-md-1" style="text-align:left;">
										<input type="checkbox" name="chkBasketNo" value="${basket.basketNo}">
							</div>
							<div class="col-md-2" style="text-align:center;">
										<img src="../images/uploadFiles/${basket.product.fileNameArr[0]}" width ="auto" height="150"><br/>
							</div>
							<div class="col-md-2" style="text-align:left;">
										<h4>${basket.product.prodName}</h4>
							</div>
							<div class="col-md-1" style="text-align:center;">
										${basket.product.price}원
							</div>
							<div class="col-md-1" style="text-align:center;">
										${basket.quantity}개
							</div>
							<div class="col-md-2" style="text-align:center;">
										&#8361; ${basket.product.price*basket.quantity}
							</div>
							<div class="col-md-3" style="text-align:center;">
										<p><input type="button" name="buy" value="주문" style="background-color:white;font-size:9pt">
										<input type="button" name="delete" value="삭제" style="background-color:white;font-size:9pt"></p>
							</div>			
							
					</div>
		</c:forEach>

</div>


</div>
	<jsp:include page="../common/pageNavigator.jsp"/>
	</form>
</body>
</html>