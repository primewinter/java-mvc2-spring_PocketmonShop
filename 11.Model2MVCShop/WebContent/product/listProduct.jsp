<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">


<head>
	<meta charset="EUC-KR">
	
	<!-- 참조 : http://getbootstrap.com/css/   참조 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
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
    
    #columns {
	column-width: 250px;
	column-gap: 30px;
	max-width: 1100px;
	margin: 50px auto;
	display: block;
	}

div#columns figure {
	background: #fefefe;
	border: 2px solid #fcfcfc;
	box-shadow: 0 1px 2px rgba(34, 25, 25, 0.4);
	margin: 0 2px 15px;
	padding: 15px;
	padding-bottom: 10px;
	transition: opacity .4s ease-in-out;
  display: inline-block;
  column-break-inside: avoid;
  	text-align:center;
}

div#columns figure img {
	width: 80%; height: auto;
	border-bottom: 1px solid #ccc;
	padding-bottom: 15px;
	margin-bottom: 5px;
}

div#columns figure figcaption {
	color: #444;
	text-align:left;
}

div#columns small { 
  text-transform: uppercase;
  color: #aaa;
} 

div#columns small a { 
  color: #666; 
  text-decoration: none; 
  transition: .4s color;
}


@media screen and (max-width: 750px) { 
  #columns { column-gap: 0px; }
  #columns figure { width: 100%; }
}

    </style>
<script type="text/javascript">


function fncGetList(){
	var a = arguments;
	switch (a.length){
     case 1: //-- 매개변수가 하나일 때
     		var currentPage = a[0];
     	   	$("#currentPage").val(currentPage);
     	    $("form").attr("method" , "POST").attr("action" , "/product/listProduct").submit();
		break;
	case 2: //-- 매개변수가 두 개 일 때
			$("#currentPage").val(a[0]);
			$("#sort").val(a[1]);
			console.log("sort ::: "+$("#sort").val());
			$("form").attr("method" , "POST").attr("action" , "/product/listProduct").submit();
		break;
	}
}

	 $(function() {
			//==> 검색 Event 연결처리부분
			 $( "#button1" ).on("click" , function() {
				$("#currentPage").val("1")
				$("form[name='detailForm']").attr("method" , "POST").attr("action" , "/product/deleteProduct").submit();
			});
	 });
	 
	 
	 // List 검색 버튼 모음
	 $(function() {
		 
			 $( "button:contains('검색')" ).on("click" , function() {
				 fncGetList(1);
			 });
			
			
			$("figure>img").on("click", function() {
				var prodNo = $($(this).parent()).find("input[name='prodNo']").val();
				self.location = "/product/getProduct?prodNo="+prodNo.trim();
			});
			
			$("figcaption p").children("font").on("click", function() {
				var prodNo = $($(this).parents()).children("input:hidden").val();
				alert(prodNo);
				if('${user.role}'=='admin'){
					self.location = "/product/updateProduct?prodNo="+prodNo.trim();
				} else {
					self.location = "/product/getProduct?prodNo="+prodNo.trim();
				}
			});
			
		});	
	 
	 
	 //무한 스크롤------------------------------------------------------------------------------------------------------------
	 let isEnd = false;
	 var currentPage = 2;
   /*  $(function(){
        $(window).scroll(function(){
        	let $window = $(this);
        	let scrollTop = $window.scrollTop();
            let windowHeight = $window.height();
            let documentHeight = $(document).height();
            
            console.log("현재 페이지 : "+currentPage);
            console.log("documentHeight:" + documentHeight + " | scrollTop:" + scrollTop + " | windowHeight: " + windowHeight );
            
            // scrollbar의 thumb가 바닥 전 30px까지 도달 하면 리스트를 가져온다.
            if( scrollTop + windowHeight + 1 > documentHeight ){
            	console.log("바닥 전 10px 이어서 리스트 갖고 올게요");
                fetchList();
            }
            
        })
    }); */
    
    $(function() {

        // 스크롤 감지
        var didScroll = false;

        $(window).on('scroll', function() { didScroll = true; });

        setInterval(function() {
            var bottomHeight = $(window).scrollTop() + $(window).height() + 60;
            var docHeight = $(document).height();

            if (didScroll) {
                didScroll = false;
                if (bottomHeight > docHeight) fetchList();
            }
        }, 250);
    });
    
    
    function fetchList(){
        if(isEnd == true){
            return;
        }
        
        var search = new Object();
        search.currentPage = currentPage;
        search.sort = $("#sort").val();
        search.searchKeyword = $("#searchKeyword").val();
        search.searchCondition = $("#searchCondition").val();
		
        console.log("data로 가져갈 jsonData ===> currentPage : " +search.currentPage
        					+" || sort : "+search.sort
        					+" || searchKeyword : "+search.searchKeyword
        					+" || searchCondition : "+search.searchCondition);
        
        // 방명록 리스트를 가져올 때 시작 번호
        // renderList 함수에서 html 코드를 보면 <li> 태그에 data-no 속성이 있는 것을 알 수 있다.
        // ajax에서는 data- 속성의 값을 가져오기 위해 data() 함수를 제공.
        
        let startNo = $(".thumbnail:last-child") || 0;
        $.ajax({
            url:"/product/json/listProduct" ,
            type: "POST",
            data: 	JSON.stringify({
            				search
            			}) ,
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
                console.log("내용 result : "+result+" | currentPage : "+currentPage);
                if( length < 5 ){
                    isEnd = true;
                    console.log("isEnd= true;")
                }
                $.each(result, function(index, vo){
                	console.log("갖고온 vo : "+vo.prodNo+" | renderList(false) ? "+false)
                   	renderList(false, vo);
                })
                currentPage++;
            },
            error: function(error) {
            	console.log("에러났습니다...."+error);
            }
        });
    }
    
   function renderList(mode,vo){
	   console.log("renderList 들어왔다");
        // 리스트 html을 정의
        var role = '${user.role}';
        var html = "" 
        	+"<figure style=\"column-break-inside: avoid;width:100%;text-align:center;\">"
        			+"<input type=\"hidden\" name=\"prodNo\" value='"+vo.prodNo+"'>"
           					+ ""
        
			       if(role == 'admin') {
				           html +=   "<p style=\"text-align:left;\">"
						    	       	+"<input type=\"checkbox\" name=\"chk_prodNo\" value=\""+vo.prodNo+"\">"
						        	   	+" 재고 : "+vo.stock+"</p><br>"
			        }
           
	    html +=					 "<input type=\"hidden\" name=\"prodNo\" value=\""+vo.prodNo+"\"><a href=\"/product/getProduct?prodNo="+vo.prodNo+"\" style=\"text-decoration: none;color:#333;\">"
	    								+"<img src=\"../images/uploadFiles/"+vo.fileNameArr[0]+"\" style=\"width: 80%; height: auto;border-bottom: 1px solid #ccc;padding-bottom: 15px;margin-bottom: 5px;\">"

	    		
	    		if(role == 'admin') {
	 	html += "</a><a href=\"/product/updateProduct?prodNo="+vo.prodNo+"\" style=\"text-decoration: none;color:#333;\">	"     			
	    			
	    		}
	    html	+="<figcaption style=\"text-align:left;\">"
					+"<p align=\"left\"><h4>"+vo.prodName+"</h4>"
					+"<font size=\"3\">&#8361; "+vo.price+"</font><br/>"
					+  vo.prodDetail +"<br/><br/>"
					+"구매 "+vo.best+"</a><br/></p>"
					+"</figcaption>"
			+"</figure>"
			+""
        
        if( mode ) {
        	console.log("prepend ::: \n"+html+"\n\n")
            $("#columns").prepend(html);
        } else {
        	console.log("append :::\n "+html+"\n\n")
            $("#columns").append(html);
        }
			
	}
   
</script>
</head>

<body>

<jsp:include page="/layout/toolbar.jsp" />

 <form class="form-inline" name="detailForm">
<div class="container">
	
		<div class="page-header text-info">
		포 켓 몬 스 터
	    </div>
	    
	    <!-- table 위쪽 검색 Start /////////////////////////////////////-->
	    <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
		    	</p>
		    	<p>
					<c:if test="${user.role=='admin' }">
						<input type="button" id="button1"  value="삭제"class="btn btn-default"style="margin-top:3px;"/>
					</c:if>
					<a href="javascript:fncGetList('1', 'toLow');">[높은가격순▼]</a>
					<a href="javascript:fncGetList('1', 'toHigh');">[낮은가격순▲]</a>
		    	</p>
		    </div>
		
		<div class="col-md-6 text-right">
		
			   
			    
				  <div class="form-group">
				    <select class="form-control" name="searchCondition"  id="searchCondition">
						<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품명</option>
						<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>상세정보</option>
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
				  <input type="hidden" id="sort" name="sort" value="${! empty search.sort ? search.sort : '' }">
				
	    	</div>
	    	
		</div>
		<div class="row">
			<div id="columns" style="display:block;">
				<c:set var="i" value="0" />
					<c:forEach var="product" items="${list}">
					<c:set var="i" value="${ i+1 }" />
					<figure>
	        					<input type="hidden" name="prodNo" value="${product.prodNo}">
					          	<c:set var="role" value = "${user.role}"/>
										<c:if test="${user.role=='admin' }">
												<p style="text-align:left;">
												<input type="checkbox" name="chk_prodNo" value="${product.prodNo}"> 재고 : ${product.stock }</p><br>
										</c:if>
										<img src="../images/uploadFiles/${product.fileNameArr[0]}">
				       		    		<figcaption>
												<h4>${product.prodName}</h4>
												<font size="3">${product.price}원</font><br/>
												${product.prodDetail }<br/>
												<br/>
												구매 ${product.best}<br/>
									</figcaption>
					</figure>
				</c:forEach>
			</div>
      	</div>
 	</div>
</form>	

</body>
</html>