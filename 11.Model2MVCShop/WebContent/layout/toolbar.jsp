<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- ToolBar Start /////////////////////////////////////-->
<nav class="navbar navbar-default navbar-fixed-top" style="background-color: #4E1EB4">
	
	<div class="container-fluid">
	       
		<!-- toolBar Button Start //////////////////////// -->
		<div class="navbar-header">
		    <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#target">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		    </button>
		    <a class="navbar-brand"><img alt="�ų��� ���θ�" src="/images/uploadFiles/logo.png" height="250%"></a>
		</div>
		<!-- toolBar Button End //////////////////////// -->
	    <!--  dropdown hover Start -->
		<div 	class="collapse navbar-collapse" id="target" 
	       			data-hover="dropdown" data-animations="fadeInDownNew fadeInRightNew fadeInUpNew fadeInLeftNew">
	         
	         	<!-- Tool Bar �� �پ��ϰ� ����ϸ�.... -->
	             <ul class="nav navbar-nav">
	             
	              <!--  ȸ������ DrowDown -->
	              <br/>
	              <li>
	                     <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/f5d86abe85.png"> ���ϸ���</span>
	                     </a>
                 </li>
	                
                <li>
                <form class="navbar-form navbar-left" name="toolSearch" id="toolSearch">
                	 <div class="form-group">
			    	<input type="text" class="form-control" name="searchKeyword" placeholder="���ϸ� �˻�"><input type="hidden"  name="searchCondition" value="0"/>
			    	</div>
			    	<button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-search"></i></button>
		    	</form>
               </li>
               
               	 <li>
	                     <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/61503ea8.png"> ���ڻ� ������</span>
	                     </a>
                 </li>


	              <c:if test="${sessionScope.user.role == 'admin'}">
	              <li>
		                     <a  href="#" role="button" aria-expanded="false">
		                         <span ><img src="/images/menu/4d731c7b96.png"> ȸ�����</span>
		                     </a>
		         </li>
		                         
	              <!-- ���Ű��� DrowDown -->
	              <li>
	                    <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/062e125c6327.png"> ���ϸ���</span>
	                    </a>
	              </li>
	              
	              <li>
	                    <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/01f389ce3.png"> �ֹ�����</span>
	                    </a>
	              </li>	              
	              
                  </c:if>
                  
                  
	             </ul>
             	
             	
	             <ul class="nav navbar-nav navbar-right">
	             	<br/>
	             	 <c:if test="${ empty user }">
	                 <li><a href="#" ><i class="glyphicon glyphicon-bookmark"></i> ȸ������</a></li>
	                 <li><a href="#" ><i class="glyphicon glyphicon-tag" ></i> �α���</a></li>
	                 </c:if>
	                 <c:if test="${ ! empty user }">
	                 <li class="dropdown">
	                 	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
		                 	<span><i class="glyphicon glyphicon-user"></i> My Page</span>
		                  	<span class="caret"></span>
	                     </a>
	                     <ul class="dropdown-menu">
	                          <li><a href="#">���� ����</a></li>
	                          <li><a href="#">��� ����</a></li>
	                         <li class="divider"></li>
	                         <li><a href="#">�� ���� ����</a></li>
	                         <li><a href="#">����Ʈ ����</a></li>
	                         <li class="divider"></li>
	                         <li><a href="#">�α׾ƿ�</a></li>
	                     </ul>
	                 </li>
	                 </c:if>
	                 <li><a href="#" class="listBasket"><i class="glyphicon glyphicon-shopping-cart"></i> ��ٱ���</a></li>
	           	</ul>
		<!-- dropdown hover END -->	       
	    </div>
	</div>
</nav>
		<!-- ToolBar End /////////////////////////////////////-->
 	
   	
   	
   	<script type="text/javascript">
	
		//============= logout Event  ó�� =============	
		$("button[type='submit']").on("click", function() {
		        $("#currentPage").val(1);
		        $("form[name='toolSearch']").attr("method" , "POST").attr("action" , "/product/listProduct").submit();
		});
			
		 $(function() {
		 	$(".navbar-brand").on("click", function() {
		 		$(self.location).attr("href", "/product/listProduct");
		 	});
			
		 	$( "a:contains('���ϸ���')" ).on("click" , function() {
				$(self.location).attr("href","/product/listProduct");
			});
		 	
		 	$( "a:contains('���ڻ�')" ).on("click" , function() {
		 		self.location = "/product/crawling.jsp"
			});
			
		 	$("a[href='#' ]:contains('���ϸ���')").on("click" , function() {
				self.location = "/product/addProductView.jsp"
			});
		 	
		 	$("a[href='#' ]:contains('�ֹ�����')").on("click" , function() {
		 		self.location = "/purchase/listSale"
			});
			
			
		 	$("a[href='#' ]:contains('ȸ������')").on("click" , function() {
				self.location = "/user/addUser"
			});
		 	
		 	$("a[href='#' ]:contains('�α���')").on("click" , function() {
				self.location = "/user/login"
			});
			
		 	$("a[href='#' ]:contains('����')").on("click" , function() {
				$(self.location).attr("href","/user/myPage.jsp");
			}); 
		 	
		 	$("a[href='#' ]:contains('���� ����')").on("click" , function() {
				self.location = "/purchase/listPurchase"
			}); 
		 	
		 	$("a[href='#' ]:contains('��� ����')").on("click" , function() {
				self.location = "/purchase/listCancelPurchase"
			}); 
		 	
		 	$("a[href='#' ]:contains('����Ʈ')").on("click" , function() {
		 		self.location = "/user/pointHistory"
			}); 
			
		 	$("a[href='#' ]:contains('�α׾ƿ�')").on("click" , function() {
				$(self.location).attr("href","/user/logout");
			}); 
			
		 	$(".listBasket:contains('��ٱ���')").on("click" , function() {
				$(self.location).attr("href","/basket/listBasket");
			}); 
		
		 	$("a:contains('ȸ�����')").on("click" , function() {
				self.location = "/user/listUser"
			}); 
	
		
		 	$( "a:contains('�� ���� ����')" ).on("click" , function() {
				$(self.location).attr("href","/user/getUser?userId=${sessionScope.user.userId}");
			});
	 });
		

		
	</script>  
	<style>
	 .container {
        	padding-top : 20px;
        	text-align:center;
        	vertical-align: middle;
        	font-family:  'Noto Sans KR', sans-serif;
        	font-size: 10pt;
        }
        
        .dropdown-menu {
        	text-align:left;
        	font-family:  'Noto Sans KR', sans-serif;
        	font-size: 10pt;
        }
	</style>