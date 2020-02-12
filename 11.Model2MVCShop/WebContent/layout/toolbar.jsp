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
		    <a class="navbar-brand"><img alt="신나는 쇼핑몰" src="/images/uploadFiles/logo.png" height="250%"></a>
		</div>
		<!-- toolBar Button End //////////////////////// -->
	    <!--  dropdown hover Start -->
		<div 	class="collapse navbar-collapse" id="target" 
	       			data-hover="dropdown" data-animations="fadeInDownNew fadeInRightNew fadeInUpNew fadeInLeftNew">
	         
	         	<!-- Tool Bar 를 다양하게 사용하면.... -->
	             <ul class="nav navbar-nav">
	             
	              <!--  회원관리 DrowDown -->
	              <br/>
	              <li>
	                     <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/f5d86abe85.png"> 포켓몬스터</span>
	                     </a>
                 </li>
	                
<!--                 <li>
                <form class="navbar-form navbar-left" name="toolSearch" id="toolSearch">
                	 <div class="form-group">
			    	<input type="text" class="form-control" name="searchKeyword" placeholder="포켓몬 검색"><input type="hidden"  name="searchCondition" value="0"/>
			    	</div>
			    	<button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-search"></i></button>
		    	</form>
               </li> -->
               
               <!-- 	 <li>
	                     <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/61503ea8.png"> 오박사 연구소</span>
	                     </a>
                 </li> -->
                 <li>
	                     <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/61503ea8.png"> 게시판</span>
	                     </a>
                 </li>
				<li>
	                     <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/61503ea8.png"> 플래너 채팅</span>
	                     </a>
                 </li>
                 <li>
	                     <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/61503ea8.png"> 동행</span>
	                     </a>
                 </li>


	              <c:if test="${sessionScope.user.role == 'admin'}">
	              <li>
		                     <a  href="#" role="button" aria-expanded="false">
		                         <span ><img src="/images/menu/4d731c7b96.png"> 회원목록</span>
		                     </a>
		         </li>
		                         
	              <!-- 구매관리 DrowDown -->
	             <!--  <li>
	                    <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/062e125c6327.png"> 포켓몬등록</span>
	                    </a>
	              </li>
	              
	              <li>
	                    <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/01f389ce3.png"> 주문관리</span>
	                    </a>
	              </li>	             -->  
	              
                  </c:if>
                  
                  
	             </ul>
             	
             	
	             <ul class="nav navbar-nav navbar-right">
	             	<br/>
	             	 <c:if test="${ empty user }">
	                 <li><a href="#" ><i class="glyphicon glyphicon-bookmark"></i> 회원가입</a></li>
	                 <li><a href="#" ><i class="glyphicon glyphicon-tag" ></i> 로그인</a></li>
	                 </c:if>
	                 <c:if test="${ ! empty user }">
	                 <li class="dropdown">
	                 	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
		                 	<span><i class="glyphicon glyphicon-user"></i> My Page</span>
		                  	<span class="caret"></span>
	                     </a>
	                     <ul class="dropdown-menu">
	                          <li><a href="#">구매 내역</a></li>
	                          <li><a href="#">취소 내역</a></li>
	                         <li class="divider"></li>
	                         <li><a href="#">내 정보 보기</a></li>
	                         <li><a href="#">포인트 내역</a></li>
	                         <li class="divider"></li>
	                         <li><a href="#">로그아웃</a></li>
	                     </ul>
	                 </li>
	                 </c:if>
	                 <li><a href="#" class="listBasket"><i class="glyphicon glyphicon-shopping-cart"></i> 장바구니</a></li>
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
				    <!-- 결과 메시지 보여주는 창 -->
				    <div class="pushBar">
				    <div class="deletePush">
				    내 소식 알림
				    <a href='javascript:deletePush()'><i class="far fa-trash-alt"></i></a><hr>
				    	<div class="pushList" style="overflow-y:auto; width:300px; height:350px;">
				    	</div>
				    </div>
				    <br />
				    <script type="text/javascript">
				        var pushAddr = "ws://192.168.0.82:8080/websocket/${user.userId}";
				        var webSocket = new WebSocket(pushAddr);
				        var userId = '${user.userId}';
				        //웹 소켓이 연결되었을 때 호출되는 이벤트
				        webSocket.onopen = function(message){
				              console.log('[push] : connection opened.')
				          	  //웹 소켓에서 메시지가 날라왔을 때 호출되는 이벤트
					          webSocket.onmessage = function(message){
				            	  console.log("push 왔다 ::: "+message.data)
				            	  var obj = JSON.parse(message.data);
				            	  var pushType = obj.pushType;
				            	  console.log("pushType :: "+pushType);
				            	  if(pushType=='P') {
				            		  var pushMsg = obj.pushMsg+" 잊지 않으셨나요?";
				            		  console.log(pushMsg);
				            		  console.log(obj.refId)
				            		  /* var html = '<div class="toast" role="alert" aria-live="assertive" aria-atomic="true">';
				            		  html += '<div class="toast-header">';
				            		  html += '!';
				            		  //<img src="..." class="rounded mr-2" alt="...">
				            		  html += '<strong class="mr-auto">Bootstrap</strong>';
				            		  html += '<small>'+obj.pushTime+'</small>';
				            		  html += '<button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">';
				            		  html += '<span aria-hidden="true">모지?</span>';
				            		  html += '</button>';
				            		  html += '</div>';
				            		  html += '<div class="toast-body">';
				            		  html += obj.pushMsg
				            		  html += '</div>';
				            		  html += '</div>';  */
				            		  $('.pushToast').html(pushMsg);
				            		  //$('.pushToast').html(html);
				            		  
				            	  } else if(pushType == 'chat'){
				            		  // 채팅목록 리로드
				            	  } else {
					        	  	getPushList(userId);
					        	  	getUnreadCount(userId);
				            	  }
					        };
				            
				        };
				        //웹 소켓이 닫혔을 때 호출되는 이벤트
				        webSocket.onclose = function(message){
				            console.log("push 접속이 끊어졌습니다.\n");
				        };
				        //웹 소켓이 에러가 났을 때 호출되는 이벤트
				        webSocket.onerror = function(message){
				            console.log("push 에러가 발생했습니다.\n");
				        };
				        
				        //웹소켓 종료
				        function disconnect(){
				            webSocket.close();
				        }
				        
				        function sendPush(receiverId, pushType) {
				        	var push = new Object();
				        	console.log("[sendPush] receiverId : "+receiverId+" || pushType : "+pushType);
							push.receiverId = receiverId;
							push.pushType = pushType;
						  	webSocket.send(JSON.stringify({push}));
							console.log("push 보냈음 ::"+JSON.stringify({push}));
				        }
				        
				    </script>
				    </div>
				    
				    <div class="container accChatBar ">
				  	  	 <div class="row">
							    <div class="col-sm accChatList">
							    </div>
							    <div class="col-sm accChatRoom">
										     <!-- 송신 메시지 작성하는 창 -->
									        <input id="textAccMessage" type="text">
									        <!-- 송신 버튼 -->
									        <input onclick="sendAccMessage()" value="Send" type="button">
									
									        <!-- 종료 버튼 -->
									        <input onclick="disconnectAcc()" value="Disconnect" type="button">
										    <br />
										    <!-- 결과 메시지 보여주는 창 -->
										    <textarea id="messageAccArea" rows="5" cols="20"></textarea>
										    
										     <script type="text/javascript">
										     var accChatAddr;
										     var accChatSocket;
										      function enterRoom(roomId) {
												        accChatAddr = "ws://192.168.0.82:8080/accSocket/"+roomId+"/${user.userId}";
												        accChatSocket = new WebSocket(accChatAddr);
												        var messageAccArea = document.getElementById("messageAccArea");
												        //웹 소켓이 연결되었을 때 호출되는 이벤트
												        accChatSocket.onopen = function(message){
												              console.log('[accChat] : connection opened.')
												          	  //웹 소켓에서 메시지가 날라왔을 때 호출되는 이벤트
													          accChatSocket.onmessage = function(message){
												            	  console.log("accChat 왔다 ::: "+message.data);
												            	  messageAccArea.value += message.data+"\n";
												            	  //var obj = JSON.parse(message.data);
													        };
												            
												        };
										      
												        //웹 소켓이 닫혔을 때 호출되는 이벤트
												        accChatSocket.onclose = function(message){
												        	messageAccArea.value += "accChat 접속이 끊어졌습니다.\n";
												        };
												        //웹 소켓이 에러가 났을 때 호출되는 이벤트
												        accChatSocket.onerror = function(message){
												        	messageAccArea.value += "accChat 에러가 발생했습니다.\n";
												        };
										      }
												        
												       /*  //Send 버튼을 누르면 실행되는 함수
												        function sendAccMessage(){
															var chat = new Object();
															chat.chatRoomId = ;
															chat.senderId = userId;
															chat.chatContent = document.getElementById("textAccMessage").value;
															console.log("[sendAccMessage] chatRoomId : "+chat.chatRoomId+" || senderId : "+chat.senderId+" || chatContent : "+chat.chatContent);
												        	
												            //messageTextArea.value += "Send to Server => "+message.value+"\n";
												            //웹소켓으로 textMessage객체의 값을 보낸다.
												            accChatSocket.send(JSON.stringify({chat}));
												            //textMessage객체의 값 초기화
												            messageAccArea.value ="";
												        } */
												        //웹소켓 종료
												        function disconnectAcc(){
												        	accChatSocket.close();
												        }
										        
										    </script>
							    	</div>
					     </div>
				    </div>
 		</div>
 		<h4>알림 내역</h4><i class="fas fa-bell fa-2x"></i>
 		<h3>동행 채팅</h3>
 	</div>
   	
   	
   	<script type="text/javascript">
    var userId = '${user.userId}';

		//============= logout Event  처리 =============	
		$("button[type='submit']").on("click", function() {
		        $("#currentPage").val(1);
		        $("form[name='toolSearch']").attr("method" , "POST").attr("action" , "/product/listProduct").submit();
		});
			
		 $(function() {
		 	$(".navbar-brand").on("click", function() {
		 		$(self.location).attr("href", "/index.jsp");
		 	});
			
		 	$( "a:contains('포켓몬스터')" ).on("click" , function() {
				$(self.location).attr("href","/product/listProduct");
			});
		 	
		 	$( "a:contains('오박사')" ).on("click" , function() {
		 		self.location = "/product/crawling.jsp"
			});
		 	
		 	$( "a:contains('채팅')" ).on("click" , function() {
		 		self.location = "/chat/enterChat.jsp"
			});
		 	
		 	$( "a:contains('동행')" ).on("click" , function() {
		 		self.location = "/chat/donghaeng.jsp"
			});
		 	
		 	$( "a:contains('게시판')" ).on("click" , function() {
		 		self.location = "/board/listBoard"
			});
			
		 	$("a[href='#' ]:contains('포켓몬등록')").on("click" , function() {
				self.location = "/product/addProductView.jsp"
			});
		 	
		 	$("a[href='#' ]:contains('주문관리')").on("click" , function() {
		 		self.location = "/purchase/listSale"
			});
			
			
		 	$("a[href='#' ]:contains('회원가입')").on("click" , function() {
				self.location = "/user/addUser"
			});
		 	
		 	$("a[href='#' ]:contains('로그인')").on("click" , function() {
				self.location = "/user/login"
			});
			
		 	$("a[href='#' ]:contains('마이')").on("click" , function() {
				$(self.location).attr("href","/user/myPage.jsp");
			}); 
		 	
		 	$("a[href='#' ]:contains('구매 내역')").on("click" , function() {
				self.location = "/purchase/listPurchase"
			}); 
		 	
		 	$("a[href='#' ]:contains('취소 내역')").on("click" , function() {
				self.location = "/purchase/listCancelPurchase"
			}); 
		 	
		 	$("a[href='#' ]:contains('포인트')").on("click" , function() {
		 		self.location = "/user/pointHistory"
			}); 
			
		 	$("a[href='#' ]:contains('로그아웃')").on("click" , function() {
				$(self.location).attr("href","/user/logout");
			}); 
			
		 	$(".listBasket:contains('장바구니')").on("click" , function() {
				$(self.location).attr("href","/basket/listBasket");
			}); 
		
		 	$("a:contains('회원목록')").on("click" , function() {
				self.location = "/user/listUser"
			}); 
	
		
		 	$( "a:contains('내 정보 보기')" ).on("click" , function() {
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
					 		//tag += "<font size='3' font color='black'><a href='/myPage' >"; // 초대 목록 리스트 링크
					 	} else if (list[i].pushType.trim() == 'A') {
					 		tag += "<img src='/images/icon/push_acc.png'>";
					 		//tag += "<font size='3' font color='black'><a href='/myPage/' >" // 동행 신청 목록 리스트 링크
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
					 console.log("알림 내역 출력 실패");
					 console.log(error);
				 }
			 })
		 }
		 
		 // <무한스크롤 ----------------------------------------------------------------------------
		var pushPage = 2;
	    var end = false;
	    
	    // 스크롤 감지
	    $(".pushList").scroll(function(){
	    	var scrollTop = $(this).scrollTop(); // 현재 스크롤의 위치
	    	var innerHeight = $(this).innerHeight(); // 패딩값을 포함한 현재 .pushList 구역의 높이
	    	var scrollHeight = $(this).prop('scrollHeight'); // 스크롤 시키지 않았을 때의 전체 높이
	    	//console.log("ScrollTop : "+scrollTop+"|| innerHeight : "+innerHeight+"|| scrollHeight : "+scrollHeight);
	    	if( scrollTop + innerHeight >= scrollHeight ) { // scrollTop() 과 innerHeight() 값을 더한 값이 scrollHeight() 보다 같거나 크게 되면 맨 아래 감지 가능
	    		console.log("스크롤 최하단에 위치")
				scrollList();
	    	}
	    })
	    
		 // 스크롤 최하단에 위치 시 작동할 function(무한스크롤) : 리스트 호출
		 function scrollList() {
			 if(end == true){  //더 이상 불러올 data가 없는 경우 return
				 console.log("if(end == true) 들어옴")
				 return;
			 }
			console.log("scrollList 시작")
			 
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
						console.log("남은 데이터가 5개 미만이므로 isEnd = true;")
					}
					
					 $.each(list, function(index, vo){
						showList(vo);
					})
					
					pushPage++;
				},
				error : function(error){
					console.log("fetchList 실패");
					console.log(error);
				}
			 })
		 }
		 
		 // 리스트 호출 시 작동할 function : 리스트 화면에 출력
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
				 		//tag += "<font size='3' font color='black'><a href='/myPage' >"; // 초대 목록 리스트 링크
				 	} else if (vo.pushType.trim() == 'A') {
				 		tag += "<img src='/images/icon/push_acc.png'>";
				 		//tag += "<font size='3' font color='black'><a href='/myPage/' >" // 동행 신청 목록 리스트 링크
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
		 
		// ----------------------------------------------------------------------------------------- 무한스크롤>
		
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
					 console.log("안 읽은 알림 개수 출력 성공 : "+result+"개")
					 var h = "<h4>알림 내역<font color='red'>"+result+"</font></h4>";
					 $(".footerBar h4").html(h);
				 },
				 error : function(error) {
					 console.log("안 읽은 알림 개수 출력 실패");
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
					 console.log("알림 읽기 성공")
				 },
				 error : function(error) {
					 console.log("알림 읽기 실패");
					 console.log(error);
				 }
			 })
		 }
		 
		 function deletePush() {
			 console.log("삭제 시작")
			 
			// 배열 선언
			 var arrayParam = new Array();
			 //each로 loop를 돌면서 checkbox의 check된 값을 가져와 담아준다.
			 $("input:checkbox[name='chk']:checked").each(function(){
			 	arrayParam.push($(this).val());
			 });
			 console.log("삭제할 배열 :: \n");
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
					console.log("알림 삭제 성공");
					getPushList(userId);
				},
				error : function(error) {
					console.log("알림 삭제 실패");
					console.log(error);
				}
			 })
			 
		 }
		 
		 //========================================================
		function getChatList() {
			console.log("채팅방 목록 조회");
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
					console.log("채팅방 조회 성공");
					console.log(result);
					var list = JSON.stringify({result});
					var html = "";
					for(var i in result) {
						html += "<input type='button' name='roomId' value='"+result[i]._id.toString()+"' onclick='getChat("+result[i]._id.toString()+")'>"+result[i].chatRoomName + "<br/>";
					}
					$(".accChatList").append(html);
				},
				error : function(error) {
					console.log("채팅방 조회 실패");
					console.log(error);
				}
			 });
			 
		 }
	
		 var roomId;
		 
		 function getChat(roomNo) {
			 console.log(roomNo +"번 방 채팅메시지 불러오기 시작")
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
					console.log("getChat() 성공");
					console.log(result);
					var list = JSON.stringify({result});
					var html = "";
					for(var i in result) {
						html += "<br/>"+result[i].senderId+" : "+result[i].chatContent +" (보낸 시간 : "+result[i].chatDate+") ";
						//console.log( result[i].senderId+" : "+result[i].chatContent +" (보낸 시간 : "+result[i].chatDate+")");
					}
					
					$("#messageAccArea").append(html);
					
				},
				error: function(error) {
					console.log("getChat() 실패")
					console.log(error);
				}
			 })
		 }
		 
		 //Send 버튼을 누르면 실행되는 함수
	        function sendAccMessage(){
				var chat = new Object();
				chat.chatRoomId = roomId;
				chat.senderId = userId;
				chat.chatContent = document.getElementById("textAccMessage").value;
				console.log("[sendAccMessage] chatRoomId : "+chat.chatRoomId+" || senderId : "+chat.senderId+" || chatContent : "+chat.chatContent);
	        	
	            //messageTextArea.value += "Send to Server => "+message.value+"\n";
	            //웹소켓으로 textMessage객체의 값을 보낸다.
	            accChatSocket.send(JSON.stringify({chat}));
	            //textMessage객체의 값 초기화
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