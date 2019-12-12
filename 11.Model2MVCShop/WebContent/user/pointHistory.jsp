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
function fncGetList(){
	var a = arguments;
	switch (a.length){
     case 1: //-- 매개변수가 하나일 때
        var currentPage = a[0];
        $("#currentPage").val(currentPage)
        $("form[name='pointForm']").attr("method" , "POST").attr("action" , "/user/pointHistory").submit();
		break;
	case 2: //-- 매개변수가 두 개 일 때
     	var currentPage = a[0];
    	var sort = a[1];
    	$("#sort").val(sort)
		$("#currentPage").val(currentPage)
		$("form[name='pointForm']").attr("method" , "POST").attr("action" , "/user/pointHistory").submit();
		break;
	}
}
$(function() {
	 
	$($("form[name='pointForm'] .ct_list_pop").parent()).find('tr:nth-child(n+3)').mouseout( function() {
 		$(this).css({'font-weight':'normal','background-color':"white"});
 	}).mouseover( function(){
 		$(this).css({'font-weight':'bold', 'background-color':'#DED0FB'});
 	});
	
	
	 $( "td.ct_btn01:contains('검색')" ).on("click" , function() {
		fncGetList(1);
	});
	 
	 $("a:contains('3일')").on("click", function() {
		 fncGetList(1, 'lately3days');
	 });
	 
	 $("a:contains('일주일')").on("click", function() {
		 fncGetList(1, 'lately1week');
	 });
	 
	 $(document).on('click', 'a[href="#"]', function(e) {
			e.preventDefault();
	});
	 
});

</script>
</head>

<body>

<jsp:include page="/layout/toolbar.jsp" />

<form name="pointForm" method="post">
<input type = "hidden" name="list" value="purchase">
<input type="hidden" id="sort" name="sort" value="">
<input type="hidden" id="tranNo" name="tranNo" value="">
<input type="hidden" id="currentPage" name="currentPage" value=""/>

<div class="container">
	
		<div class="page-header text-info">
	       <h3>나의 포인트 내역</h3>
	       <hr>
	       <p>나의 포인트 ${user.point}</p>
	    </div>
	    
	    <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		현재 ${resultPage.currentPage}  페이지
		    	</p>
		    </div>
		</div>



<table class="table table-hover table-striped" >
      
        <thead>
          <tr>
            <th class="text-center">일자</th>
            <th class="text-center">내용</th>
            <th class="text-center">포인트</th>
            <th class="text-center">주문번호</th>
          </tr>
        </thead>
       
		<tbody>
		<c:set var="i" value="0" />
		  <c:forEach var="pointHistory" items="${list}">
			<c:set var="i" value="${ i+1 }" />
			<tr>
			  <td align="center">${pointHistory.regDate}</td>
			  <td align="center" >${pointHistory.contents}</td>
			  <td align="center">${pointHistory.point} ${pointHistory.action}</td>
			  <td align="center">${pointHistory.tranNo} </td>
			</tr>
          </c:forEach>
        
        </tbody>
      
      </table>
		
		

</div>

		<jsp:include page="../common/pageNavigator.jsp"/>

</form>

</body>
</html>