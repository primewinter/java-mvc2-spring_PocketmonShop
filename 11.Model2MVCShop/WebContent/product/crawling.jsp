<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
	<!-- ���� : http://getbootstrap.com/css/   ���� -->
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
   
   
   <!-- jQuery UI toolTip ��� CSS-->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <!-- jQuery UI toolTip ��� JS-->
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
        		<img src="/images/uploadFiles/obaksaMini.png"> <input type="text" name="libSearch" id="libSearch" placeholder="���ϸ��� �˻��غ���" style="width:200px;">  <input type="button" class="btn btn-default" id="libSubmit" value="�˻�"/>
     	</div>
        <div class="two">
        		<h4>������ ���ϸ��� �����?</h4>
        			<img src="/images/uploadFiles/obaksa.png" width="80%" id="random">	
        		<h3>��- ��ī��-!</h3>
       	</div>
    </div>
</div>
	<script type="text/javascript">

		 
		 $(function() {
				 $("#libSubmit").on("click" , function() {
					 
					 var libSearch = $("#libSearch").val();
					 if(	libSearch == "" || libSearch.length == 0) {
						 var html =	"<h4>���ϸ��� �Է��ؾ��� �ʰڴ�?</h4>";
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
				                console.log("���� result : "+result);
				                var html = result;
				                
				                $(".two").html('');
			                	$(".two").append(html);

				            },
				            error: function(request, error) {
				            	console.log("���������ϴ�...."+error);
				            	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
							        
				                $(".two").html('');
				                $(".two").append(html);

				            }
				        });
				 });
		});
		
		
		function fetchList(){
	       console.log("�˻� �����Ѵ�.")
	        var libSearch = $("#libSearch").val();
	        var params ={ 
	        		libSearch : libSearch
	        	};
	        console.log("#libSearch.val() ===> "+$("#libSearch").val());
	        console.log("data�� ������ jsonData ===> libSearch : "+libSearch);
	        
	        // ���� ����Ʈ�� ������ �� ���� ��ȣ
	        // renderList �Լ����� html �ڵ带 ���� <li> �±׿� data-no �Ӽ��� �ִ� ���� �� �� �ִ�.
	        // ajax������ data- �Ӽ��� ���� �������� ���� data() �Լ��� ����.
	        $.ajax({
	            url:"/product/json/searchPocketmon",
	            type: "GET",
	            data : { "libSearch" : libSearch },
	            dataType: "text",
	            contentType: "application/json;charset=EUC-KR",
	            success: function(result){
	                // ��Ʈ�ѷ����� ������ ���� ����Ʈ�� result.data�� ��ܿ����� �ߴ�.
	                // ���� �����Ͱ� 5�� ������ ��� ���� ��ũ�� ����
	                let length = result.length;
	                console.log("result.length() : "+length);
	                console.log("���� result : "+result);
	                if( result.length == 0 ) {
	                	 var html =	"<h4>�׷� ���ϸ��� ���ٴ±���</h4>";
					        html += 		"<img src=\"/images/uploadFiles/obaksa3.jpg\"><br/>";
					        html +=		"<h3>�ٸ� ���ϸ��� �˻��غ���?</h3>"
	                } else {
	                	var html = result;
	                }
	                $(".two").html('');
                	$(".two").append(html);

	            },
	            error: function(request, error) {
	            	console.log("���������ϴ�...."+error);
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