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
             font-family:  'Noto Sans KR', sans-serif;
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

    </style>
<script type="text/javascript">

$(function() {
	
	$( "button:contains('등록')").on("click" , function() {
		$("form").attr("method" , "POST").attr("action" , "/board/addBoard").submit();
	});
	
	$( "button:contains('리셋')" ).on("click" , function() {
		$("form")[0].reset();
	});
	
	$(document).on('click', 'a[href="#"]', function(e) {
		e.preventDefault();
	});
	
	
});
</script>
</head>
<body>

	<jsp:include page="/layout/toolbar.jsp" />
	
<form name="detailForm">

<div class="container">
		    <div class="wrapper">
				    <div class="one"></div>
				    <div class="two">
				    			<hr>
				    			<input type="hidden" name="userId" value="${user.userId}">
				    			<div class="row">
									  <div class="col-md-12" >
									  		<table style="margin:auto;width:100%">
									  		<tr>
									  			<td width="20%">
												</td>
												<td width="60%">
												<h4>글쓰기</h4>
							  					<td>
							  					<td width="20%" style="text-align:right;">
										 		</td>
										 	</tr>
											</table>
											<hr>
										</div>
								</div>
				    </div>
				    <div class="three">
				    			<div class="row">
				    				
				    				<div class="row">
				    					<div class="col-md-12"> 
				    								<p>작성자 : ${user.userId}</p>
										  			<p>제목 : <input type="text" name="title"></p>
										  			<p>내용 : <input type="text" name="content"></p>
										  			<p></p>
										  			<button type ="button" value="등록">등록</button>
				    								<button type ="button" value="리셋">리셋</button>
				    					</div>
				    				</div>
				    				
				    			<div class="row" style="margin-top:20px">
				    					<div class="col-md-12"> 
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