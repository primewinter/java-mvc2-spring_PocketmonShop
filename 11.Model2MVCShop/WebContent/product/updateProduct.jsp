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
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    
    <!-- 복사본 -->
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
   <style>
         .wrapper {
            display: grid;
            grid-template-columns: repeat(5, 200px);
            grid-gap: 50px;
            grid-auto-rows: minmax(100px, auto);
            margin: 30px;
        }

        .one {
            grid-column: 1;
            grid-row: 1;
        }

        .two {
            grid-column: 2 / 4;
            grid-row: 1 / 3;
            background-color: powderblue;
            padding: 20%;
        }

        .three {
            grid-column: 4;
            grid-row: 1 / 2 ;
            padding-top: 30%;
            text-align: left;
        }

        .four {
            grid-column: 4;
            grid-row: 2 / 3;
            padding-top: 10%;
            text-align: left;
        }

        .five {
            grid-column: 5;
            grid-row: 1;
        }
        
        .btn_buy { 
        	border: 1px solid #333; 
        	background-color: #333; 
        	width: 250px;
        	color: white; 
        	padding: 5px; 
        }
        
        .btn_ {
       		border: 1px solid #333; 
        	background-color: #333; 
        	width: 90px;
        	color: white; 
        	padding: 5px; 
        }


		body {
            padding-top : 100px;
        }
     </style>
     
<script type="text/javascript">
<!--
function fncUpdateProduct() {

	var name=document.detailForm.prodName.value;
	var detail=document.detailForm.prodDetail.value;
	var mDate=document.detailForm.manuDate.value;
	var price=document.detailForm.price.value;
	
	if(name == null || name.length <1){
		alert("상품명은  반드시 입력하셔야 합니다.");
		return;
	}
	
	if(detail == null || detail.length <1){
		alert("상품상세정보는  반드시 입력하셔야 합니다.");
		return;
	}
	
	if(mDate == null || mDate.length <1){
		alert("제조일자는  반드시 입력하셔야 합니다.");
		return;
	}
	
	if(price == null || price.length <1){
		alert("가격은  반드시 입력하셔야 합니다.");
		return;
	}
	
		
	document.detailForm.action='/product/updateProduct';
	document.detailForm.submit();
}


$(function() {
		$("button:contains('취소')").on("click", function() {
				$('form')[0].reset();
		});
		
		$("button:contains('수정')").on("click", function() {
			fncUpdateProduct();
	});
});

$( function() {
	$( "#datepicker" ).datepicker({
		 dateFormat: 'yy-mm-dd',
	      showOn: "button",
	      buttonImage: "../images/ct_icon_date.gif",
	      buttonImageOnly: true,
	      buttonText: "Select date",
	      changeMonth: true,
	      changeYear: true
	    });
  } );
  
  
-->
</script>
<script type="text/javascript">
          
// 등록 이미지 등록 미리보기
function readInputFile(input) {
    if(input.files && input.files[0]) {
    	alert("1")
        var reader = new FileReader();
        reader.onload = function (e) {
        	alert("2");
            $('#preview').html("<img src="+ e.target.result +">");
        }
        reader.readAsDataURL(input.files[0]);
    }
}
 
$(".inp-img").on('change', function(){
    readInputFile(this);
});
 
 
// 등록 이미지 삭제 ( input file reset )
function resetInputFile($input, $preview) {
    var agent = navigator.userAgent.toLowerCase();
    if((navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1)) {
        // ie 일때
        $input.replaceWith($input.clone(true));
        $preview.empty();
    } else {
        //other
        $input.val("");
        $preview.empty();
    }       
}
 
$(".btn-delete").click(function(event) {
    var $input = $(".inp-img");
    var $preview = $('#preview');
    resetInputFile($input, $preview);
});
</script>


</head>

<body>

	<jsp:include page="/layout/toolbar.jsp" />




<div class="container">
<form name="detailForm"  method="post" enctype="multipart/form-data">
    <div class="wrapper">
        <div class="one"></div>
        <div class="two" >
					<!-- 이미지 미리보기 기능 구현하기 -->
        			<div id="preview"></div><!-- <input type="file" name="" class="inp-img" accept=".gif, .jpg, .png"> <span class="btn-delete">삭제</span> -->
        			<hr>
       				<hr>
       				<input multiple="multiple" type="file" name="file" value="${product.fileNameArr[0]}" class="ct_input_g" 
						style="width:300px; height:19px" >
		        	<c:forEach var="item" items="${product.fileNameArr}">
					<img src ="/images/uploadFiles/${item}" width=200><br/>
		       		</c:forEach>
       	</div>
        <div class="three">
         <div class="affix">
         
				<input type="hidden" name="prodNo" value="${product.prodNo}">
         		<h4>상품 정보 수정</h4>
         		<hr>
	          	<p>
	          	상품명 <input type="text" name="prodName" value="${product.prodName}" class="ct_input_g" 
						style="width:100px; height:19px"  maxLength="50" >
	          	</p>
	        	<p>
	        	상품 정보 <input 	type="text" name="prodDetail" value="${product.prodDetail}" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="100">
	        	</p>
		        <p>생년월일 <input type="text" name="manuDate" value="${product.manuDate}" id="datepicker" class="ct_input_g" style="width:100px; height:19px" ></p>
	        	<p>가격 <input 	type="text" name="price" value="${product.price}" class="ct_input_g" 
										style="width:100px; height:19px">원</p>
				<p>재고 <input 	type="text" name="stock" value="${product.stock}" class="ct_input_g" 
										style="width:100px; height:19px"></p>
	        	
	        	<hr>
	        	
	          	<p><button type="button" class="btn_">수정</button>  <button type="button" class="btn_">취소</button></p>
	          
        </div>
        			
       </div>
		        
        <div class="four">
		</div>      
        <div class="five"><!-- 공백 --></div>
    </div>
		</form>

</div>



</body>
</html>