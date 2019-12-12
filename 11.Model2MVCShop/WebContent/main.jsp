<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
	<meta name="theme-color" content="#4E1EB4">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!--   jQuery , Bootstrap CDN  -->
	<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
	
	<!--  CSS 추가 : 툴바에 화면 가리는 현상 해결 :  주석처리 전, 후 확인-->
	<style>
        body {
            padding-top : 100px;
        }
		.jumbotron {
			background-color: powderblue;
		}
   	</style>
   	
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	 	
	
</head>
	
<body>

	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->

	<!--  아래의 내용은 http://getbootstrap.com/getting-started/  참조 -->	
   	<div class="container ">
   		
      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h2>~인기도 TOP 6 포켓몬들~</h2>
     </div>
    </div>

	<!-- 참조 : http://getbootstrap.com/css/   : container part..... -->
  	 
  	 
<script>
$(document).ready(function() {
	fetchList();
});

function fetchList(){
    
    $.ajax({
        url:"/product/json/listBest" ,
        type: "POST",
        dataType: "json",
        headers : {
			"Accept" : "application/json",
			"Content-Type" : "application/json"
		},
        success: function(result){
            // 컨트롤러에서 가져온 방명록 리스트는 result.data에 담겨오도록 했다.
            // 남은 데이터가 5개 이하일 경우 무한 스크롤 종료
            let length = result.length;
            console.log("result.length() : "+length);
            console.log("내용 result : "+result);

            $.each(result, function(index, vo){
            	console.log("갖고온 vo : "+vo.prodNo+" | renderList(false) ? "+false)
               	renderList(false, vo);
            })
        },
        error: function(request, error) {
        	console.log("에러났습니다...."+error);
        	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

function renderList(mode,vo){
   console.log("renderList 들어왔다");
    
    var html = ""
    	+"<figure style=\"column-break-inside: avoid;width:100%;text-align:center;\">"
    			+"<input type=\"hidden\" name=\"prodNo\" value='"+vo.prodNo+"'>"
       					+ ""
    
       
    html +=					 "<input type=\"hidden\" name=\"prodNo\" value=\""+vo.prodNo+"\"><a href=\"/product/getProduct?prodNo="+vo.prodNo+"\" style=\"text-decoration: none;color:#333;\">"
    								+"<img src=\"../images/uploadFiles/"+vo.fileNameArr[0]+"\" style=\"width: 20%; height: auto;border-bottom: 1px solid #ccc;padding-bottom: 15px;margin-bottom: 5px;\">"

    		
    html	+="<figcaption style=\"text-align:center;\">"
				+"<p align=\"left\"><h4>"+vo.prodName+"</h4>"
				+  vo.prodDetail +"<br/><br/>"
				+"</figcaption>"
		+"</figure>"
		+""
    
    if( mode ) {
    	console.log("prepend ::: \n"+html+"\n\n")
        $(".jumbotron").prepend(html);
    } else {
    	console.log("append :::\n "+html+"\n\n")
        $(".jumbotron").append(html);
    }
		
}
</script>
</body>

</html>