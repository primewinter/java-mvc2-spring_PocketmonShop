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
         .wrapper {
            display: grid;
            grid-template-columns: repeat(5, 150px);
            grid-gap: 50px;
            grid-auto-rows: minmax(100px, auto);
            margin: 30px;
        }

        .one {
            grid-column: 1 / 5;
            grid-row: 1;
            text-align:center;
        }

        .two {
            grid-column: 1 / 5;
            grid-row: 2 / 4;
            background-color: powderblue;
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
	
</head>

<body>

	<jsp:include page="/layout/toolbar.jsp" />

<form name = "detailForm">
<div class="container">
    <div class="wrapper">
        <div class="one">
        		<img src="/images/uploadFiles/obaksaMini.png"> <input type="text" name="libSearch" id="libSearch" placeholder="포켓몬을 검색해보렴" style="width:200px;">  <input type="button" class="btn btn-default" id="libSubmit" value="검색"/>
     	</div>
        <div class="two">
        		<h4>오늘의 포켓몬은 뭘까요?</h4>
        			<img src="/images/uploadFiles/obaksa.png" width="80%" id="random">	
        		<h3>피- 피카츄-!</h3>
       	</div>
    </div>
</div>
	<script type="text/javascript">

		 
		 $(function() {
				 $("#libSubmit").on("click" , function() {
					 
					 var libSearch = $("#libSearch").val();
					 if(	libSearch == "" || libSearch.length == 0) {
						 var html =	"<h4>포켓몬을 입력해야지 않겠니?</h4>";
				        html += 		"<img src=\"/images/uploadFiles/obaksa2.png\" width=\"80%\">";
				        html +=		"<br/><br/><br/>"
				        $(".two").html('');
		                $(".two").append(html);
					 } else {
						fetchList();
					 }
				});
				 
				 $("#random").mouseout( function() {
					 $(this).attr("src", $(this).attr("src").replace("lever.jpg", "obaksa.png")); 
				 	}).mouseover( function(){
				 	    $(this).attr("src", $(this).attr("src").replace("obaksa.png", "lever.jpg")).attr("width","80%"); 
				 	});
				 
				 $("#random").on("click", function() {
					 
					 $.ajax({
				            url:"/product/json/randomPocketmon",
				            type: "GET",
				            dataType: "text",
				            contentType: "application/json;charset=EUC-KR",
				            success: function(result){

				            	let length = result.length;
				                console.log("result.length() : "+length);
				                console.log("내용 result : "+result);
				                var html = result;
				                
				                $(".two").html('');
			                	$(".two").append(html);

				            },
				            error: function(request, error) {
				            	console.log("에러났습니다...."+error);
				            	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
							        
				                $(".two").html('');
				                $(".two").append(html);

				            }
				        });
				 });
		});
		
		
		function fetchList(){
	       console.log("검색 시작한다.")
	        var libSearch = $("#libSearch").val();
	        var params ={ 
	        		libSearch : libSearch
	        	};
	        console.log("#libSearch.val() ===> "+$("#libSearch").val());
	        console.log("data로 가져갈 jsonData ===> libSearch : "+libSearch);
	        
	        // 방명록 리스트를 가져올 때 시작 번호
	        // renderList 함수에서 html 코드를 보면 <li> 태그에 data-no 속성이 있는 것을 알 수 있다.
	        // ajax에서는 data- 속성의 값을 가져오기 위해 data() 함수를 제공.
	        $.ajax({
	            url:"/product/json/searchPocketmon",
	            type: "GET",
	            data : { "libSearch" : libSearch },
	            dataType: "text",
	            contentType: "application/json;charset=EUC-KR",
	            success: function(result){
	                // 컨트롤러에서 가져온 방명록 리스트는 result.data에 담겨오도록 했다.
	                // 남은 데이터가 5개 이하일 경우 무한 스크롤 종료
	                let length = result.length;
	                console.log("result.length() : "+length);
	                console.log("내용 result : "+result);
	                if( result.length == 0 ) {
	                	 var html =	"<h4>그런 포켓몬은 없다는구나</h4>";
					        html += 		"<img src=\"/images/uploadFiles/obaksa3.jpg\"><br/>";
					        html +=		"<h3>다른 포켓몬을 검색해볼까?</h3>"
	                } else {
	                	var html = result;
	                }
	                $(".two").html('');
                	$(".two").append(html);

	            },
	            error: function(request, error) {
	            	console.log("에러났습니다...."+error);
	            	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				        
	                $(".two").html('');
	                $(".two").append(html);

	            }
	        });
	    }
		 
 </script>	
	

</form>

</body>
</html>