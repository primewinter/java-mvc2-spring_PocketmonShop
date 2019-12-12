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
    </style>
<script type="text/javascript">
function fncGetList(currentPage){
	    $("#currentPage").val(currentPage)
		$("form").attr("method" , "POST").attr("action" , "/purchase/listSale").submit();
}
</script>

<script type="text/javascript">
$(function() {
	$( "button:contains('검색')" ).on("click" , function() {
		fncGetList(1);
	});
	
	$( ".ct_list_pop td:nth-child(5)" ).on("click" , function() {
		var tranNo = $($(this).siblings()).html();
		self.location = "/purchase/getSale?tranNo="+tranNo.trim();
	});
	
	$(".ct_list_pop td:nth-child(3)").on("click", function() {
		alert("곧 구현할 거야 기다려");
		/* ajax로 userId 가 주문한 거래내역 보여주기 */
	 }).on("mouseover", function() {
			$(this).css({'font-weight':'bold', 'color':'#4E1EB4'});
		}).on("mouseout", function() {
			$(this).css({'font-weight':'normal','color':"#424242"});
		});
	
	$(".ct_list_pop td:nth-child(5)").on("mouseover", function() {
		$(this).css({'font-weight':'bold', 'color':'#4E1EB4'});
	}).on("mouseout", function() {
		$(this).css({'font-weight':'normal','color':"#424242"});
	});
	
	
	
	$( "input:button" ).on("click" , function() {
		var tranNo = $($($(this).parents()).parents()).children('td').html();
		$("#tranNo").val(tranNo.trim());
		$("#currentPage").val('${resultPage.currentPage}');
		$("form").attr("method" , "POST").attr("action" , "/purchase/updateTranCode").submit();
	});
})

</script>
</head>

<body>
	
	<jsp:include page="/layout/toolbar.jsp" />


<form name="detailForm" method="post">
<input type="hidden" name="list" value="sale">
<input type="hidden" id="sort" name="sort" value="">
<input type="hidden" id="tranNo" name="tranNo" value="">

<div class="container">
	
		<div class="page-header text-info">
	       <h3>주문 관리</h3>
	    </div>


<div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
		    	</p>
		    </div>
		    
		    <div class="col-md-6 text-right">
			    
				  <div class="form-group">
				    <select class="form-control" name="searchCondition" >
						<option value="0" ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : ""}>상품명</option>
						<option value="1" ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : ""}>주문자 ID</option>
						<option value="2" ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : ""}>거래번호</option>
					</select>
				 </div>
					  
					  <div class="form-group">
					    <label class="sr-only" for="searchKeyword">검색어</label>
					    <input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="검색어"
					    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
					  </div>
					  
					  <button type="button" class="btn btn-default">검색</button>
					  
					  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
					  <input type="hidden" id="currentPage" name="currentPage" value=""/>
					  
		    	</div>
		    	
			</div>



<table class="table table-hover table-striped" >
      
        <thead>
          <tr>
            <th class="text-center">주문번호</th>
            <th class="text-center" >주문자 ID</th>
            <th class="text-center">상품명</th>
            <th class="text-center">주문금액</th>
            <th class="text-center">주문일자</th>
             <th class="text-center">현재상태</th>
          </tr>
        </thead>
        <tbody>

	<c:set var="i" value="0" />
	<c:forEach var="purchase" items="${list}">
		<c:set var="i" value="${ i+1 }" />
	<tr class="ct_list_pop">
		<td align="center">${purchase.tranNo} </td>
		<td align="center">${purchase.buyer.userId } </td>
		<td align="center">
		<c:forEach var="purchaseProd" items="${purchase.purchaseProd}">
		${purchaseProd.prodName}
		</c:forEach>
		</td>
		<td align="center">${purchase.payment.totalAmount}</td>
		<td align="center">${purchase.orderDate}
		</td>
		<td align="center">
		${purchase.tranCode}
		<select name="${purchase.tranNo}" class="ct_input_g" style="width:80px">
				<option value="상품준비">상품준비</option>
				<option value="배송중">배송시작</option>
				<option value="처리중">처리중</option>
				<option value="환불완료">환불완료</option>
				<option value="거래완료">거래완료</option>
		</select>
		<input type = "button" value="변경" class="ct_input_g"/>
		</td>	
	</tr>
</c:forEach>
</tbody>
</table>

</div>	
</form>

<jsp:include page="../common/pageNavigator.jsp"/>

</body>
</html>