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
	                
                <li>
                <form class="navbar-form navbar-left" name="toolSearch" id="toolSearch">
                	 <div class="form-group">
			    	<input type="text" class="form-control" name="searchKeyword" placeholder="포켓몬 검색"><input type="hidden"  name="searchCondition" value="0"/>
			    	</div>
			    	<button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-search"></i></button>
		    	</form>
               </li>
               
               	 <li>
	                     <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/61503ea8.png"> 오박사 연구소</span>
	                     </a>
                 </li>
                 <li>
	                     <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/61503ea8.png"> 게시판</span>
	                     </a>
                 </li>


	              <c:if test="${sessionScope.user.role == 'admin'}">
	              <li>
		                     <a  href="#" role="button" aria-expanded="false">
		                         <span ><img src="/images/menu/4d731c7b96.png"> 회원목록</span>
		                     </a>
		         </li>
		                         
	              <!-- 구매관리 DrowDown -->
	              <li>
	                    <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/062e125c6327.png"> 포켓몬등록</span>
	                    </a>
	              </li>
	              
	              <li>
	                    <a  href="#" role="button" aria-expanded="false">
	                         <span ><img src="/images/menu/01f389ce3.png"> 주문관리</span>
	                    </a>
	              </li>	              
	              
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
 		<div class="footerBar-content">
 			 
				    <!-- 결과 메시지 보여주는 창 -->
				    <textarea id="messageTextArea" rows="10" cols="30"></textarea><br>
				     <!-- 송신 메시지 작성하는 창 -->
			        <input id="textMessage" type="text">
			        <!-- 송신 버튼 -->
			        <input onclick="sendMessage()" value="Send" type="button">
			
			        <!-- 종료 버튼 -->
			        <input onclick="disconnect()" value="Disconnect" type="button">
				    <br />
				    <script type="text/javascript">
				        //WebSocketEx는 프로젝트 이름
				        //websocket 클래스 이름
				        var chatAddr = "ws://192.168.0.82:8080/websocket/${user.userId}";
				        var webSocket = new WebSocket(chatAddr);
				        var messageTextArea = document.getElementById("messageTextArea");
				        //웹 소켓이 연결되었을 때 호출되는 이벤트
				        webSocket.onopen = function(message){
				            console.log('Info : connection opened.')
				          //웹 소켓에서 메시지가 날라왔을 때 호출되는 이벤트
					        webSocket.onmessage = function(message){
					            messageTextArea.value += "\n"+message.data;
				        	  $("#messageTextArea").scrollTop($("#messageTextArea")[0].scrollHeight);
					        };
				            
				        };
				        //웹 소켓이 닫혔을 때 호출되는 이벤트
				        webSocket.onclose = function(message){
				            messageTextArea.value += "접속이 끊어졌습니다.\n";
				        };
				        //웹 소켓이 에러가 났을 때 호출되는 이벤트
				        webSocket.onerror = function(message){
				            messageTextArea.value += "에러가 발생했습니다.\n";
				        };
				        
				        //Send 버튼을 누르면 실행되는 함수
				        function sendMessage(){
				        	var user = "${user.userId} : ";
				            var message = document.getElementById("textMessage");
				            //messageTextArea.value += "Send to Server => "+message.value+"\n";
				            //웹소켓으로 textMessage객체의 값을 보낸다.
				            webSocket.send(user+message.value);
				            $("#messageTextArea").scrollTop($("#messageTextArea")[0].scrollHeight);
				            //textMessage객체의 값 초기화
				            message.value = "";
				        }
				        //웹소켓 종료
				        function disconnect(){
				            webSocket.close();
				        }
				    </script>
 		</div>
 		<h4>채팅</h4>
 	</div>
   	
   	
   	<script type="text/javascript">
	
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
		 
		  jQuery(document).ready(function($) {

	            // hide the menu when the page load
	            $(".footerBar-content").hide();

	            // when .menuBtn is clicked, do this
	            $("h4").click(function() {

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
        }
        

	</style>