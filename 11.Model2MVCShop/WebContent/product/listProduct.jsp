<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">


<head>
	<meta charset="EUC-KR">
	
	<!-- ���� : http://getbootstrap.com/css/   ���� -->
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
   
   
   <!-- jQuery UI toolTip ��� CSS-->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <!-- jQuery UI toolTip ��� JS-->
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
     case 1: //-- �Ű������� �ϳ��� ��
     		var currentPage = a[0];
     	   	$("#currentPage").val(currentPage);
     	    $("form").attr("method" , "POST").attr("action" , "/product/listProduct").submit();
		break;
	case 2: //-- �Ű������� �� �� �� ��
			$("#currentPage").val(a[0]);
			$("#sort").val(a[1]);
			console.log("sort ::: "+$("#sort").val());
			$("form").attr("method" , "POST").attr("action" , "/product/listProduct").submit();
		break;
	}
}

	 $(function() {
			//==> �˻� Event ����ó���κ�
			 $( "#button1" ).on("click" , function() {
				$("#currentPage").val("1")
				$("form[name='detailForm']").attr("method" , "POST").attr("action" , "/product/deleteProduct").submit();
			});
	 });
	 
	 
	 // List �˻� ��ư ����
	 $(function() {
		 
			 $( "button:contains('�˻�')" ).on("click" , function() {
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
	 
	 
	 //���� ��ũ��------------------------------------------------------------------------------------------------------------
	 let isEnd = false;
	 var currentPage = 2;
   /*  $(function(){
        $(window).scroll(function(){
        	let $window = $(this);
        	let scrollTop = $window.scrollTop();
            let windowHeight = $window.height();
            let documentHeight = $(document).height();
            
            console.log("���� ������ : "+currentPage);
            console.log("documentHeight:" + documentHeight + " | scrollTop:" + scrollTop + " | windowHeight: " + windowHeight );
            
            // scrollbar�� thumb�� �ٴ� �� 30px���� ���� �ϸ� ����Ʈ�� �����´�.
            if( scrollTop + windowHeight + 1 > documentHeight ){
            	console.log("�ٴ� �� 10px �̾ ����Ʈ ���� �ðԿ�");
                fetchList();
            }
            
        })
    }); */
    
    $(function() {

        // ��ũ�� ����
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
		
        console.log("data�� ������ jsonData ===> currentPage : " +search.currentPage
        					+" || sort : "+search.sort
        					+" || searchKeyword : "+search.searchKeyword
        					+" || searchCondition : "+search.searchCondition);
        
        // ���� ����Ʈ�� ������ �� ���� ��ȣ
        // renderList �Լ����� html �ڵ带 ���� <li> �±׿� data-no �Ӽ��� �ִ� ���� �� �� �ִ�.
        // ajax������ data- �Ӽ��� ���� �������� ���� data() �Լ��� ����.
        
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
                // ��Ʈ�ѷ����� ������ ���� ����Ʈ�� result.data�� ��ܿ����� �ߴ�.
                // ���� �����Ͱ� 5�� ������ ��� ���� ��ũ�� ����
                let length = result.length;
                console.log("result.length() : "+length);
                console.log("���� result : "+result+" | currentPage : "+currentPage);
                if( length < 5 ){
                    isEnd = true;
                    console.log("isEnd= true;")
                }
                $.each(result, function(index, vo){
                	console.log("����� vo : "+vo.prodNo+" | renderList(false) ? "+false)
                   	renderList(false, vo);
                })
                currentPage++;
            },
            error: function(error) {
            	console.log("���������ϴ�...."+error);
            }
        });
    }
    
   function renderList(mode,vo){
	   console.log("renderList ���Դ�");
        // ����Ʈ html�� ����
        var role = '${user.role}';
        var html = "" 
        	+"<figure style=\"column-break-inside: avoid;width:100%;text-align:center;\">"
        			+"<input type=\"hidden\" name=\"prodNo\" value='"+vo.prodNo+"'>"
           					+ ""
        
			       if(role == 'admin') {
				           html +=   "<p style=\"text-align:left;\">"
						    	       	+"<input type=\"checkbox\" name=\"chk_prodNo\" value=\""+vo.prodNo+"\">"
						        	   	+" ��� : "+vo.stock+"</p><br>"
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
					+"���� "+vo.best+"</a><br/></p>"
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
		�� �� �� �� ��
	    </div>
	    
	    <!-- table ���� �˻� Start /////////////////////////////////////-->
	    <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		��ü  ${resultPage.totalCount } �Ǽ�, ���� ${resultPage.currentPage}  ������
		    	</p>
		    	<p>
					<c:if test="${user.role=='admin' }">
						<input type="button" id="button1"  value="����"class="btn btn-default"style="margin-top:3px;"/>
					</c:if>
					<a href="javascript:fncGetList('1', 'toLow');">[�������ݼ���]</a>
					<a href="javascript:fncGetList('1', 'toHigh');">[�������ݼ���]</a>
		    	</p>
		    </div>
		
		<div class="col-md-6 text-right">
		
			   
			    
				  <div class="form-group">
				    <select class="form-control" name="searchCondition"  id="searchCondition">
						<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>��ǰ��</option>
						<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>������</option>
					</select>
				  </div>
				  
				  <div class="form-group">
				    <label class="sr-only" for="searchKeyword">�˻���</label>
				    <input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="�˻���"
				    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
				  </div>
				  
				  <button type="button" class="btn btn-default">�˻�</button>
				  
				  <!-- PageNavigation ���� ������ ���� ������ �κ� -->
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
												<input type="checkbox" name="chk_prodNo" value="${product.prodNo}"> ��� : ${product.stock }</p><br>
										</c:if>
										<img src="../images/uploadFiles/${product.fileNameArr[0]}">
				       		    		<figcaption>
												<h4>${product.prodName}</h4>
												<font size="3">${product.price}��</font><br/>
												${product.prodDetail }<br/>
												<br/>
												���� ${product.best}<br/>
									</figcaption>
					</figure>
				</c:forEach>
			</div>
      	</div>
 	</div>
</form>	

</body>
</html>