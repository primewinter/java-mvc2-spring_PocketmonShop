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
        	background-color: #F2F2F2;
            grid-column: 2 / 4;
            grid-row: 1 ;
            width: 500px;
            padding: 40px;
        }
        .three {
        	grid-column: 2 / 4;
            grid-row: 2 ;
            width: 500px;
        	padding: 40px;
        	background-color: powderblue;
        }

        .four {
            grid-column: 1 / 4;
            grid-row: 3;
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
	var boardNo = $("#boardNo").val();
	
	$(document).ready(function() {
		getCmtList(boardNo);
	});
	
	//댓글 등록
	$( "input:button").on("click" , function() {
		/* var $form = $("form[name=detailForm]");
		var data = getFormData($form); */
		
		var cmt = new Object();
		cmt.userId = '${user.userId}';
		cmt.content = $("#content").val();
		cmt.boardNo = boardNo;
		
		console.log(cmt);
		$.ajax({
			url : "/board/json/addCmt",
			type : "POST",
			data : JSON.stringify({
				cmt
			}) ,
			headers : {
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},
			success : function() {
				console.log("성공");
				//var reply = result.userId+" : "+result.content;
				//$(".three .reply").html(reply);
				getCmtList(boardNo);
				$("#content").val('');
				
				
				//글 작성자에게 push 하기
				var writer = '${board.userId}';
			  	webSocket.send("PUSH"+boardNo+","+writer);
				console.log("push 보냈음")
			},
			error: function(err) {
				console.log("댓글 등록 실패");
				console.log(err);
			}
			
		})
			
		
		//$("form").attr("method" , "POST").attr("action" , "/board/addBoard").submit();
	});
	
	function getCmtList(boardNo) {
		$.ajax({
			url: "/board/json/getCmtList/"+boardNo,
			type: "GET",
			headers: {
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},
			success: function(result) {
				var cmt ="";
				for(var i=0 in result) {
					cmt += result[i].userId+" : "+result[i].content+" "+result[i].regDate+"<br>";
				}
				$(".three .reply").html("<br>"+cmt);
				
			},
			error: function(err) {
				console.log("댓글 목록 못 불러옴");
				console.log(err);
			}
		})
	}
	
	function getFormData($form){
	    var unindexed_array = $form.serializeArray();
	    var indexed_array = {};

	    $.map(unindexed_array, function(n, i){
	        indexed_array[n['name']] = n['value'];
	    });

	    return indexed_array;
	}
	
});
</script>
</head>
<body>

	<jsp:include page="/layout/toolbar.jsp" />
	
<form name="detailForm" accept-charset="UTF-8">

<div class="container">
		    <div class="wrapper">
				    <div class="one"></div>
				    <div class="two">
				    			<input type="hidden" name="boardNo" id="boardNo" value="${board.boardNo }">
					  			<p>No. ${board.boardNo}</p>
					  			<h4>제목 : ${board.title}</h4>
								<hr>
					  			<h4>${board.content }</h4>
					  			<hr>
   								<p>By. ${board.userId}</p>
					  			<p>${board.regDate }</p> 
				    </div>
				    <div class="three">
				    			<div class="reply">
				    			
				    			</div>
				    			<div class="cmt">
				    				//댓글
				    				<br>
				    				<input type="hidden" name="userId" value="${user.userId }">
				    				<input type="text" name="content" id="content">
				    				<input type="button" value="등록" >
				    			</div>
				    			
				    </div>
				    <div class="four"></div>
    </div>
    
   
</div>

</form>
</body>
</html>