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
                 <li>
	                     <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/61503ea8.png"> �Խ���</span>
	                     </a>
                 </li>
				<li>
	                     <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/61503ea8.png"> ä��</span>
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
 	<div class="footerBar">
 		<div class="footerBar-content">
 			 
				    <!-- ��� �޽��� �����ִ� â -->
				    <div class="pushList" style="overflow-y:auto; width:200px; height:220px;">
				    </div>
				    <br />
				    
				    <script type="text/javascript">
				        var pushAddr = "ws://192.168.0.82:8080/websocket/${user.userId}";
				        var webSocket = new WebSocket(pushAddr);
				        //�� ������ ����Ǿ��� �� ȣ��Ǵ� �̺�Ʈ
				        webSocket.onopen = function(message){
				              console.log('[push] : connection opened.')
				          	  //�� ���Ͽ��� �޽����� ������� �� ȣ��Ǵ� �̺�Ʈ
					          webSocket.onmessage = function(message){
				            	  console.log("push �Դ� ::: "+message.data)
				            	  var obj = JSON.parse(message.data);
				            	  var pushType = obj.pushType;
				            	  if(pushType=='T') {
				            		  var pushMsg = obj.pushMsg;
				            		  console.log(pushMsg);
				            	  } else {
					        	  	getPushList(userId);
					        	  	getUnreadCount(userId);
				            	  }
					        };
				            
				        };
				        //�� ������ ������ �� ȣ��Ǵ� �̺�Ʈ
				        webSocket.onclose = function(message){
				            console.log("push ������ ���������ϴ�.\n");
				        };
				        //�� ������ ������ ���� �� ȣ��Ǵ� �̺�Ʈ
				        webSocket.onerror = function(message){
				            console.log("push ������ �߻��߽��ϴ�.\n");
				        };
				        
				        //������ ����
				        function disconnect(){
				            webSocket.close();
				        }
				    </script>
 		</div>
 		<h4>�˸� ����</h4>
 	</div>
   	
   	
   	<script type="text/javascript">
    var userId = '${user.userId}';

		//============= logout Event  ó�� =============	
		$("button[type='submit']").on("click", function() {
		        $("#currentPage").val(1);
		        $("form[name='toolSearch']").attr("method" , "POST").attr("action" , "/product/listProduct").submit();
		});
			
		 $(function() {
		 	$(".navbar-brand").on("click", function() {
		 		$(self.location).attr("href", "/index.jsp");
		 	});
			
		 	$( "a:contains('���ϸ���')" ).on("click" , function() {
				$(self.location).attr("href","/product/listProduct");
			});
		 	
		 	$( "a:contains('���ڻ�')" ).on("click" , function() {
		 		self.location = "/product/crawling.jsp"
			});
		 	
		 	$( "a:contains('ä��')" ).on("click" , function() {
		 		self.location = "/chat/enterChat.jsp"
			});
		 	
		 	$( "a:contains('�Խ���')" ).on("click" , function() {
		 		self.location = "/board/listBoard"
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
		 
		 function getPushList(userId) {
			 $.ajax({
				 url : "/push/json/getPushList/"+userId,
				 type : "GET",
				 dataType : "json",
				 headers : {
					 "Accept" : "application/json",
					 "Content-Type" : "application/json"
				 },
				 success : function(result) {
					 console.log(result);
					 var list = result.list;
					 var resultPage = result.resultPage;
					 var search = result.search;
					 console.log("list.size : "+list.size);
					 
					 var tag = "<div class='chkPushList'>"
					 tag += "<a href='javascript:deletePush()'>����</a><hr>";
					 for(var i = 0 in list) {
					 	tag += "<input type='checkbox' name='chk' id='"+list[i].pushId+"' value='"+list[i].pushId+"'>";
					 	tag+= "<a href='/board/getBoard?boardNo="+list[i].refId+"'>";
					 	tag += list[i].pushMsg+"</a><br>";
					 }
					 
					 tag += "</div>"
					 
					 $('.pushList').html(tag);
					 console.log("resultPage : "+resultPage);
					 console.log("search : "+search);
				 },
				 error : function(error) {
					 console.log("�˸� ���� ��� ����");
					 console.log(error);
				 }
			 })
		 }
		 
		 // <���ѽ�ũ�� ----------------------------------------------------------------------------
		var pushPage = 2;
	    var end = false;
	    
	    // ��ũ�� ����
	    $(".pushList").scroll(function(){
	    	var scrollTop = $(this).scrollTop(); // ���� ��ũ���� ��ġ
	    	var innerHeight = $(this).innerHeight(); // �е����� ������ ���� .pushList ������ ����
	    	var scrollHeight = $(this).prop('scrollHeight'); // ��ũ�� ��Ű�� �ʾ��� ���� ��ü ����
	    	//console.log("ScrollTop : "+scrollTop+"|| innerHeight : "+innerHeight+"|| scrollHeight : "+scrollHeight);
	    	if( scrollTop + innerHeight >= scrollHeight ) { // scrollTop() �� innerHeight() ���� ���� ���� scrollHeight() ���� ���ų� ũ�� �Ǹ� �� �Ʒ� ���� ����
	    		console.log("��ũ�� ���ϴܿ� ��ġ")
				scrollList();
	    	}
	    })
	    
		 // ��ũ�� ���ϴܿ� ��ġ �� �۵��� function(���ѽ�ũ��) : ����Ʈ ȣ��
		 function scrollList() {
			 if(end == true){  //�� �̻� �ҷ��� data�� ���� ��� return
				 console.log("if(end == true) ����")
				 return;
			 }
			console.log("scrollList ����")
			 
			 var search = new Object();
			 search.currentPage = pushPage;
			 
			 $.ajax({
				url : "/push/json/getPushList/"+userId,
				type : "POST",
				data : JSON.stringify({search}),
				dataType : "json",
				headers : {
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				success : function(list) {
					 console.log(list);
					
					var length = list.length;
					console.log("�޾ƿ� data�� ���� : "+length);
					console.log("currentPage : "+pushPage)
				
					if( length < 5 ){
						isEnd = true;
						console.log("���� �����Ͱ� 5�� �̸��̹Ƿ� isEnd = true;")
					}
					
					 $.each(list, function(index, vo){
						showList(vo);
					})
					
					pushPage++;
				},
				error : function(error){
					console.log("fetchList ����");
					console.log(error);
				}
			 })
		 }
		 
		 // ����Ʈ ȣ�� �� �۵��� function : ����Ʈ ȭ�鿡 ���
		 function showList(vo) {
			 var tag = "";
			 tag += "<input type='checkbox' name='chk' id='"+vo.pushId+"' value='"+vo.pushId+"'>";
			 tag+= "<a href='/board/getBoard?boardNo="+vo.refId+"'>";
			 tag +=vo.pushMsg+"</a><br>";
		  	 $(".chkPushList").append(tag);
		 }
		 
		// ----------------------------------------------------------------------------------------- ���ѽ�ũ��>
		
		 function getUnreadCount(userId) {
			 $.ajax({
				 url : "/push/json/getUnreadCount/"+userId,
				 type : "GET",
				 dataType : "json",
				 headers : {
					 "Accept" : "application/json",
					 "Content-Type" : "application/json"
				 },
				 success : function(result) {
					 console.log("�� ���� �˸� ���� ��� ���� : "+result+"��")
					 var h = "<h4>�˸� ����<font color='red'>"+result+"</font></h4>";
					 $(".footerBar h4").html(h);
				 },
				 error : function(error) {
					 console.log("�� ���� �˸� ���� ��� ����");
					 console.log(error);
				 }
			 })
		 }
		 
		 function readPush(userId) {
			 $.ajax({
				 url : "/push/json/readPush/"+userId,
				 type : "GET",
				 headers : {
					 "Accept" : "application/json",
					 "Content-Type" : "application/json"
				 },
				 success : function() {
					 console.log("�˸� �б� ����")
				 },
				 error : function(error) {
					 console.log("�˸� �б� ����");
					 console.log(error);
				 }
			 })
		 }
		 
		 function deletePush() {
			 console.log("���� ����")
			 
			// �迭 ����
			 var arrayParam = new Array();
			 //each�� loop�� ���鼭 checkbox�� check�� ���� ������ ����ش�.
			 $("input:checkbox[name='chk']:checked").each(function(){
			 	arrayParam.push($(this).val());
			 });
			 console.log("�迭!!! \n");
			 console.log(arrayParam);
			 
			  var formData = JSON.stringify(arrayParam);
		       
			 $.ajax({
				url : "/push/json/deletePush",
				type : "POST",
				data : formData,
				headers : {
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				success : function() {
					console.log("�˸� ���� ����");
					getPushList(userId);
				},
				error : function(error) {
					console.log("�˸� ���� ����");
					console.log(error);
				}
			 })
			 
		 }
		 
		  jQuery(document).ready(function($) {
				
			  getPushList(userId);
			  getUnreadCount(userId);
			  
	            // hide the menu when the page load
	            $(".footerBar-content").hide();

	            // when .menuBtn is clicked, do this
	            $(".footerBar h4").click(function() {

	            	readPush(userId);
					setTimeout(() => getUnreadCount(userId), 50);
					
	                // open the menu with slide effect
	                $(".footerBar-content").slideToggle(300);

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
        
        .footerBar {
        	position: fixed;
        	right: 50px;
        	bottom: 50px;
        	text-align: right;
        }
        
        .footerBar-content {
        	background-color: lightpink;
        }
        

	</style>