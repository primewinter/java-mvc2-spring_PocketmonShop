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
            vertical-align:center;
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
            font-family:  'Noto Sans KR', sans-serif;
        }
     </style>
	<script type="text/javascript">
				$("input[name='prodName']").focus();
				
				function fncAddProduct() {
					// Form 유효성 검증
					var name=$("input[name='prodName']").val();
					var prodDetail=$("textarea[name='prodDetail']").val();
					var manuDate=$("input[name='manuDate']").val();
					var price=$("input[name='price']").val();
					var stock=$("input[name='stock']").val();
					
				
					if(name == null || name.length <1){
						alert("상품명은 반드시 입력하셔야 합니다.");
						return;
					}
					if(prodDetail == null || prodDetail.length <1){
						alert("상품상세정보는 반드시 입력하셔야 합니다.");
						return;
					}
					if(manuDate == null || manuDate.length <1){
						alert("제조일자는 반드시 입력하셔야 합니다.");
						return;
					}
					if(price == null || price.length <1){
						alert("가격은 반드시 입력하셔야 합니다.");
						return;
					}
					if(stock == null || price.length <1){
						alert("재고는 반드시 입력하셔야 합니다.");
						return;
					}
					
					$("form").attr("method" , "POST").attr("action" , "/product/addProduct").submit();
				}
				
				 $(function() {
						 $( "button:contains('등록')" ).on("click" , function() {
							fncAddProduct();
						});
					});	
				 
				$(function() {
					 $( "button:contains('취소')" ).on("click" , function() {
							$("form")[0].reset();
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
	</script>

</head>

<body>

	<jsp:include page="/layout/toolbar.jsp" />



<div class="container">
    <div class="wrapper">
        <div class="one"></div>
        <div class="two" > 
		        <img id="blah" src="/images/uploadFiles/logo.png"  width="50%"/>
        </div>
        <div class="three">
       		 <form name="detailForm"  method="post" enctype="multipart/form-data">
       			<h4>포켓몬 등록</h4>
       			<hr>
       			<p>포켓몬 이름 <input 	type="text" name="prodName" class="ct_input_g" style="width:130px; height:19px"/></p>
       			<p>짧은 소개글 <textarea name="prodDetail" class="ct_input_g" rows="3" cols="30" 
							style="width:200px; height:57px"></textarea></p>
       			<p>생년월일 <input type="text" name="manuDate" id="datepicker" class="ct_input_g" style="width:100px; height:19px" ></p>
       			<p>분양가 <input type="text" name="price" class="ct_input_g"  style="width:130px; height:19px"  maxLength="100"/>원
       			<p>수량 <input	type="text" name="stock" class="ct_input_g"  style="width:130px; height:19px"  maxLength="100"/>
       			<p>포켓몬 이미지 <input multiple="multiple" type="file" name="file"  id="fileBox" />
  			</form>
        </div>
        <div class="four">
        <p><button type="button" class="btn_">등록</button> <button type="button" class="btn_">취소</button></p>
        </div>      
        <div class="five"><!-- 공백 --></div>
    </div>
</div>
	
 <script>
 
 $(function() {
     $("#fileBox").on('change', function(){
         readURL(this);
     });
 });

 function readURL(input) {
	 if (input.files && input.files[0]) {
	     var reader = new FileReader();

	     reader.onload = function (e) {
	             $('#blah').attr('src', e.target.result);
	         }
	       reader.readAsDataURL(input.files[0]);
	     } 
	 };
	 
    




        
</script>        


</body>
</html>