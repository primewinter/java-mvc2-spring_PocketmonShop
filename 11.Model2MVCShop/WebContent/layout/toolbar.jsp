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
	                
<!--                 <li>
                <form class="navbar-form navbar-left" name="toolSearch" id="toolSearch">
                	 <div class="form-group">
			    	<input type="text" class="form-control" name="searchKeyword" placeholder="���ϸ� �˻�"><input type="hidden"  name="searchCondition" value="0"/>
			    	</div>
			    	<button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-search"></i></button>
		    	</form>
               </li> -->
               
               <!-- 	 <li>
	                     <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/61503ea8.png"> ���ڻ� ������</span>
	                     </a>
                 </li> -->
                 <li>
	                     <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/61503ea8.png"> �Խ���</span>
	                     </a>
                 </li>
				<li>
	                     <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/61503ea8.png"> �÷��� ä��</span>
	                     </a>
                 </li>
                 <li>
	                     <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/61503ea8.png"> ����</span>
	                     </a>
                 </li>


	              <c:if test="${sessionScope.user.role == 'admin'}">
	              <li>
		                     <a  href="#" role="button" aria-expanded="false">
		                         <span ><img src="/images/menu/4d731c7b96.png"> ȸ�����</span>
		                     </a>
		         </li>
		                         
	              <!-- ���Ű��� DrowDown -->
	             <!--  <li>
	                    <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/062e125c6327.png"> ���ϸ���</span>
	                    </a>
	              </li>
	              
	              <li>
	                    <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/01f389ce3.png"> �ֹ�����</span>
	                    </a>
	              </li>	             -->  
	              
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
 		<div class="pushToast">
 		</div>
 		<div class="footerBar-content">
				    <!-- ��� �޽��� �����ִ� â -->
				    <div class="pushBar">
				    <div class="deletePush">
				    �� �ҽ� �˸�
				    <a href='javascript:deletePush()'><i class="far fa-trash-alt"></i></a><hr>
				    	<div class="pushList" style="overflow-y:auto; width:300px; height:350px;">
				    	</div>
				    </div>
				    <br />
				    <script type="text/javascript">
				        var pushAddr = "ws://192.168.0.82:8080/websocket/${user.userId}";
				        var webSocket = new WebSocket(pushAddr);
				        var userId = '${user.userId}';
				        //�� ������ ����Ǿ��� �� ȣ��Ǵ� �̺�Ʈ
				        webSocket.onopen = function(message){
				              console.log('[push] : connection opened.')
				          	  //�� ���Ͽ��� �޽����� ������� �� ȣ��Ǵ� �̺�Ʈ
					          webSocket.onmessage = function(message){
				            	  console.log("push �Դ� ::: "+message.data)
				            	  var obj = JSON.parse(message.data);
				            	  var pushType = obj.pushType;
				            	  console.log("pushType :: "+pushType);
				            	  if(pushType=='P') {
				            		  var pushMsg = obj.pushMsg+" ���� �����̳���?";
				            		  console.log(pushMsg);
				            		  console.log(obj.refId)
				            		  /* var html = '<div class="toast" role="alert" aria-live="assertive" aria-atomic="true">';
				            		  html += '<div class="toast-header">';
				            		  html += '!';
				            		  //<img src="..." class="rounded mr-2" alt="...">
				            		  html += '<strong class="mr-auto">Bootstrap</strong>';
				            		  html += '<small>'+obj.pushTime+'</small>';
				            		  html += '<button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">';
				            		  html += '<span aria-hidden="true">����?</span>';
				            		  html += '</button>';
				            		  html += '</div>';
				            		  html += '<div class="toast-body">';
				            		  html += obj.pushMsg
				            		  html += '</div>';
				            		  html += '</div>';  */
				            		  $('.pushToast').html(pushMsg);
				            		  //$('.pushToast').html(html);
				            		  
				            	  } else if(pushType == 'chat'){
				            		  // ä�ø�� ���ε�
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
				        
				        function sendPush(receiverId, pushType) {
				        	var push = new Object();
				        	console.log("[sendPush] receiverId : "+receiverId+" || pushType : "+pushType);
							push.receiverId = receiverId;
							push.pushType = pushType;
						  	webSocket.send(JSON.stringify({push}));
							console.log("push ������ ::"+JSON.stringify({push}));
				        }
				        
				    </script>
				    </div>
				    
				    <div class="container accChatBar ">
				  	  	 <div class="row">
							    <div class="col-sm accChatList">
							    </div>
							    <div class="col-sm accChatRoom">
										     <!-- �۽� �޽��� �ۼ��ϴ� â -->
									        <input id="textAccMessage" type="text">
									        <!-- �۽� ��ư -->
									        <input onclick="sendAccMessage()" value="Send" type="button">
									
									        <!-- ���� ��ư -->
									        <input onclick="disconnectAcc()" value="Disconnect" type="button">
										    <br />
										    <!-- ��� �޽��� �����ִ� â -->
										    <textarea id="messageAccArea" rows="5" cols="20"></textarea>
										    
										     <script type="text/javascript">
										     var accChatAddr;
										     var accChatSocket;
										      function enterRoom(roomId) {
												        accChatAddr = "ws://192.168.0.82:8080/accSocket/"+roomId+"/${user.userId}";
												        accChatSocket = new WebSocket(accChatAddr);
												        var messageAccArea = document.getElementById("messageAccArea");
												        //�� ������ ����Ǿ��� �� ȣ��Ǵ� �̺�Ʈ
												        accChatSocket.onopen = function(message){
												              console.log('[accChat] : connection opened.')
												          	  //�� ���Ͽ��� �޽����� ������� �� ȣ��Ǵ� �̺�Ʈ
													          accChatSocket.onmessage = function(message){
												            	  console.log("accChat �Դ� ::: "+message.data);
												            	  messageAccArea.value += message.data+"\n";
												            	  //var obj = JSON.parse(message.data);
													        };
												            
												        };
										      
												        //�� ������ ������ �� ȣ��Ǵ� �̺�Ʈ
												        accChatSocket.onclose = function(message){
												        	messageAccArea.value += "accChat ������ ���������ϴ�.\n";
												        };
												        //�� ������ ������ ���� �� ȣ��Ǵ� �̺�Ʈ
												        accChatSocket.onerror = function(message){
												        	messageAccArea.value += "accChat ������ �߻��߽��ϴ�.\n";
												        };
										      }
												        
												       /*  //Send ��ư�� ������ ����Ǵ� �Լ�
												        function sendAccMessage(){
															var chat = new Object();
															chat.chatRoomId = ;
															chat.senderId = userId;
															chat.chatContent = document.getElementById("textAccMessage").value;
															console.log("[sendAccMessage] chatRoomId : "+chat.chatRoomId+" || senderId : "+chat.senderId+" || chatContent : "+chat.chatContent);
												        	
												            //messageTextArea.value += "Send to Server => "+message.value+"\n";
												            //���������� textMessage��ü�� ���� ������.
												            accChatSocket.send(JSON.stringify({chat}));
												            //textMessage��ü�� �� �ʱ�ȭ
												            messageAccArea.value ="";
												        } */
												        //������ ����
												        function disconnectAcc(){
												        	accChatSocket.close();
												        }
										        
										    </script>
							    	</div>
					     </div>
				    </div>
 		</div>
 		<h4>�˸� ����</h4><i class="fas fa-bell fa-2x"></i>
 		<h3>���� ä��</h3>
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
		 	
		 	$( "a:contains('����')" ).on("click" , function() {
		 		self.location = "/chat/donghaeng.jsp"
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
					 //tag += "<a href='javascript:deletePush()'><i class=\"far fa-trash-alt\"></i></a><hr>";
					 for(var i = 0 in list) {
						tag += "<table width=95%>"
					 	tag += "<tr>"
					 	tag += "<td style='margin:auto;text-align:center' width='20%'>"
						tag += "<input type='checkbox'  class='custom-control-input' name='chk' id='"+list[i].pushId+"' value='"+list[i].pushId+"'>"; // style='display:none;'
					 	/* tag += "<a href='/board/getBoard?boardNo="+list[i].refId+"'>";
					 	tag += list[i].pushMsg+"</a><br>"; */
					 	if(list[i].pushType.trim()=='R') {
					 		tag += "<img src='/images/icon/push_reply9.png'>";
					 		tag += "</td>"
				 			tag += "<td style='text-align:left;'>"
			 				tag += "<label  class='custom-control-label' for='"+list[i].pushId+"' ><font size='2' font color='black'><a href='/board/getBoard?boardNo="+list[i].refId+"'>";
					 	} else if (list[i].pushType.trim() == 'I' ) {
					 		tag += "<img src='/images/icon/push_invite.png'>";
					 		//tag += "<font size='3' font color='black'><a href='/myPage' >"; // �ʴ� ��� ����Ʈ ��ũ
					 	} else if (list[i].pushType.trim() == 'A') {
					 		tag += "<img src='/images/icon/push_acc.png'>";
					 		//tag += "<font size='3' font color='black'><a href='/myPage/' >" // ���� ��û ��� ����Ʈ ��ũ
					 	}
					 		tag += list[i].pushMsg+"</font></label></td>";
					 		tag += "<tr>";
					 		tag += "<td></td><td>";
					 		tag += "<label  class='custom-control-label' for='"+list[i].pushId+"'><font size='1' font color='gray'>"+list[i].pushTime+"</font></a></label>";
					 		tag += "</td>";
					 		tag += "</tr>";
					 }
					 tag += "</table>"
					 tag += "</div>"
					  tag += "<style>"
					  +"label { font-weight: normal; font-color: white; }"
						/*+ "input[type=\"checkbox\"] {"
						 +    "display:none;"
						 +"}"
						 +"input[type=\"checkbox\"] + label span {"
						     +"display: inline-block;"
						     +"width: 24px;"
						     +"height: 24px;"
						     +"margin: -2px 10px 0 0;"
						     +"vertical-align: middle;"
						     +"background: url(checkbox.svg) left top no-repeat;"
						     +"cursor: pointer;"
						     +"background-size: cover;"
						 +"}"
						 +"input[type=\"checkbox\"]:checked + label span {"
						     +"background:url(checkbox.svg)  -26px top no-repeat;"
						      +"background-size: cover;"
						 +"}"*/
						 +"</style>";  
					 
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
					
					var length = list.length;
				
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
					tag += "<table width=95%>"
				 	tag += "<tr>"
				 	tag += "<td style='margin:auto;text-align:center' width='20%'>"
					tag += "<input type='checkbox'  class='custom-control-input' name='chk' id='"+vo.pushId+"' value='"+vo.pushId+"'>"; // style='display:none;'
				 	if(vo.pushType.trim()=='R') {
				 		tag += "<img src='/images/icon/push_reply9.png'>";
				 		tag += "</td>"
			 			tag += "<td style='text-align:left;'>"
		 				tag += "<label  class='custom-control-label' for='"+vo.pushId+"' ><font size='2' font color='black'><a href='/board/getBoard?boardNo="+vo.refId+"'>";
				 	} else if (vo.pushType.trim() == 'I' ) {
				 		tag += "<img src='/images/icon/push_invite.png'>";
				 		//tag += "<font size='3' font color='black'><a href='/myPage' >"; // �ʴ� ��� ����Ʈ ��ũ
				 	} else if (vo.pushType.trim() == 'A') {
				 		tag += "<img src='/images/icon/push_acc.png'>";
				 		//tag += "<font size='3' font color='black'><a href='/myPage/' >" // ���� ��û ��� ����Ʈ ��ũ
				 	}
			 		tag += vo.pushMsg+"</font></label></td>";
			 		tag += "<tr>";
			 		tag += "<td></td><td>";
			 		tag += "<label  class='custom-control-label' for='"+vo.pushId+"'><font size='1' font color='gray'>"+vo.pushTime+"</font></a></label>";
			 		tag += "</td>";
			 		tag += "</tr>";
					tag += "</table>"
					tag += "</div>"
/* 			 
			 
			 tag += "<input type='checkbox' name='chk' id='"+vo.pushId+"' value='"+vo.pushId+"'>";
			 tag+= "<a href='/board/getBoard?boardNo="+vo.refId+"'>";
			 tag +=vo.pushMsg+"</a><br>"; */
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
			 console.log("������ �迭 :: \n");
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
		 
		 //========================================================
		function getChatList() {
			console.log("ä�ù� ��� ��ȸ");
    		var userId = '${user.userId}';
    		
    		$.ajax({
        		url : "/chat/json/getChatRoomList/"+userId ,
				type : "GET",
				dataType : "json",
				headers : {
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				success : function(result) {
					console.log("ä�ù� ��ȸ ����");
					console.log(result);
					var list = JSON.stringify({result});
					var html = "";
					for(var i in result) {
						html += "<input type='button' name='roomId' value='"+result[i]._id.toString()+"' onclick='getChat("+result[i]._id.toString()+")'>"+result[i].chatRoomName + "<br/>";
					}
					$(".accChatList").append(html);
				},
				error : function(error) {
					console.log("ä�ù� ��ȸ ����");
					console.log(error);
				}
			 });
			 
		 }
	
		 var roomId;
		 
		 function getChat(roomNo) {
			 console.log(roomNo +"�� �� ä�ø޽��� �ҷ����� ����")
			 enterRoom(roomNo);
			 roomId = roomNo;
			 $.ajax({
				url: "/chat/json/getChat/"+roomNo ,
			 	type: "GET",
			 	dataType : "json",
				headers : {
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				success : function(result) {
					console.log("getChat() ����");
					console.log(result);
					var list = JSON.stringify({result});
					var html = "";
					for(var i in result) {
						html += "<br/>"+result[i].senderId+" : "+result[i].chatContent +" (���� �ð� : "+result[i].chatDate+") ";
						//console.log( result[i].senderId+" : "+result[i].chatContent +" (���� �ð� : "+result[i].chatDate+")");
					}
					
					$("#messageAccArea").append(html);
					
				},
				error: function(error) {
					console.log("getChat() ����")
					console.log(error);
				}
			 })
		 }
		 
		 //Send ��ư�� ������ ����Ǵ� �Լ�
	        function sendAccMessage(){
				var chat = new Object();
				chat.chatRoomId = roomId;
				chat.senderId = userId;
				chat.chatContent = document.getElementById("textAccMessage").value;
				console.log("[sendAccMessage] chatRoomId : "+chat.chatRoomId+" || senderId : "+chat.senderId+" || chatContent : "+chat.chatContent);
	        	
	            //messageTextArea.value += "Send to Server => "+message.value+"\n";
	            //���������� textMessage��ü�� ���� ������.
	            accChatSocket.send(JSON.stringify({chat}));
	            //textMessage��ü�� �� �ʱ�ȭ
	            messageAccArea.value ="";
	        }
		 
		  jQuery(document).ready(function($) {
			  
			  getChatList();
			  //getChat();
			  
			  if(userId != null && userId != '' ) {
			  	getPushList(userId);
			  	getUnreadCount(userId);
			  	
			  }
	            // hide the menu when the page load
	            $(".pushBar").hide();
	            $(".accChatBar").slideToggle(300);

	            // when .menuBtn is clicked, do this
	            $(".footerBar h4").click(function() {

	            	readPush(userId);
					setTimeout(() => getUnreadCount(userId), 50);
					
	                // open the menu with slide effect
	                $(".pushBar").slideToggle(300);

	            });
	            
	            $(".footerBar h3").click(function() {
	            	$(".accChatBar").slideToggle(300);
	            })

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
        	background-color: white;
        }
        
        

	</style>