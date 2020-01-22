<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<!-- ���� : http://getbootstrap.com/css/   ���� -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
    	 body >  div.container{ 
        	border: 1px solid #D6CDB7;
            margin-top: 10px;
        }
    </style>
    
    <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
		//============= "�α���"  Event ���� =============
		$( function() {
			
			$("#userId").focus();
			
			//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$("button").on("click" , function() {
				var id=$("input:text").val();
				var pw=$("input:password").val();
				
				if(id == null || id.length <1) {
					alert('ID �� �Է����� �����̽��ϴ�.');
					$("#userId").focus();
					return;
				}
				
				if(pw == null || pw.length <1) {
					alert('�н����带 �Է����� �����̽��ϴ�.');
					$("#password").focus();
					return;
				}
				
				var user = new Object();
				user.userId = id;
				user.password = pw;
				
				$.ajax({
	                url:"/user/json/login" ,
	                type: "POST",
	                data: JSON.stringify(user),
	                dataType: "json",
	                headers : {
	    				"Accept" : "application/json",
	    				"Content-Type" : "application/json"
	    			},
	                success: function(result){
	                	self.location = "/index.jsp";
	                },
	                error: function(error) {
	                	console.log(error);
	                	$("input:text").val("")
	                	$("input:password").val("")
	                	alert("��ġ�ϴ� ȸ�� ������ �����ϴ�.")
	                	$("#userId").focus()
	                }
	            });
				
				
				/* $("form").attr("method","POST").attr("action","/user/login").attr("target","_parent").submit(); */
			});
		});	
		
		
		//============= ȸ��������ȭ���̵� =============
		$( function() {
			//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$("a:contains('����')").on("click" , function() {
				self.location = "/user/addUser"
			});
			
		});
		
	</script>		
	
</head>

<body>

	<!-- ToolBar Start /////////////////////////////////////-->
	<div class="navbar  navbar-default">
        <div class="container">
        	<a class="navbar-brand" href="/index.jsp"><img src="/images/uploadFiles/logo.png" height="60"></a>
   		
   		
   		<div class="collapse navbar-collapse"  id="target">
	             <ul class="nav navbar-nav navbar-right">
	             	 <c:if test="${ empty user }">
	                 <li><a href="#"><img src="/images/uploadFiles/1574782342740_lfa.gif" height="55" > ȸ������</a></li>
	                 <li><a href="#"><img src="/images/uploadFiles/1574782342740_evei.gif" height="55" > �α���</a></li>
	                 </c:if>
	                 <c:if test="${ ! empty user }">
	                 <li><a href="#"><img src="/images/uploadFiles/1574783017035_ggg.gif" height="55" > ����������</a></li>
	                 <li><a href="#"><img src="/images/uploadFiles/1574783017036_vvv.gif" height="55"> �α׾ƿ�</a></li>
	                 </c:if>
	                 <li><a href="#"><img src="/images/uploadFiles/1574782342739_npa.gif" height="55" >  ��ٱ���</a></li>
	           	</ul>
	       </div>
   	</div>
   	</div>
   	
   		
   	<!-- ToolBar End /////////////////////////////////////-->	
	
	<!--  ȭ�鱸�� div Start /////////////////////////////////////-->
	<div class="container">
		<!--  row Start /////////////////////////////////////-->
		<div class="row">
				
			<div class="col-md-3">
			</div>
	   	 	
	 	 	<div class="col-md-6">
	 	 	
		 	 	<br/><br/>
				
				<div class="jumbotron">	 	 	
		 	 		<p align="center"><img src="/images/uploadFiles/logo.png" width="400"/></p>

			        <form class="form-horizontal">
		  
					  <div class="form-group">
					    <label for="userId" class="col-sm-4 control-label">���̵�</label>
					    <div class="col-sm-6">
					      <input type="text" class="form-control" name="userId" id="userId"  placeholder="���̵�" >
					    </div>
					  </div>
					  
					  <div class="form-group">
					    <label for="password" class="col-sm-4 control-label">�н�����</label>
					    <div class="col-sm-6">
					      <input type="password" class="form-control" name="password" id="password" placeholder="�н�����" >
					    </div>
					  </div>
					  
					  <div class="form-group">
					    <div class="col-sm-offset-4 col-sm-6 text-center">
					      <p><button type="button" class="btn btn-warning"  >�α���</button>
					      <a class="btn btn-warning" href="#" role="button">ȸ������</a></p>
					       <a id="kakao-login-btn"></a>
							<a href="http://developers.kakao.com/logout"></a>
							<p id="kakao-login-result"></p>
					    </div>
					  </div>
			
					</form>
			   	 </div>
			
			</div>
			
  	 	</div>
  	 	<!--  row Start /////////////////////////////////////-->
  	 	
 	</div>
 	<!--  ȭ�鱸�� div end /////////////////////////////////////-->

<script>
// ����� ���� JavaScript Ű�� ������ �ּ���.
	Kakao.init('cbd12c83cb6b8b59a21c95762e4b01ba');
// īī�� �α��� ��ư�� �����մϴ�.
	Kakao.Auth.createLoginButton({
	
	    container: '#kakao-login-btn',
	    success: function(authObj) {
	    	
	    	Kakao.API.request({
	    		url: '/v1/user/me',
	    		success: function(res) {
	    		var status = res;
		    	var mail = res.kaccount_email;
		    	//var name = res.properties.nickname;
	    		document.getElementById('kakao-login-result').innerText = 'success: ' + JSON.stringify(res);
	    		
	    		  alert(":: res :: \n "+JSON.stringify(res)); //<---- kakao.api.request ���� �ҷ��� ����� json���·� ���
	    		  alert("email : "+mail);
	    		  alert("�г��� : "+status.nickname);
	  			      
	  
	              console.log(res.id);//<---- �ܼ� �α׿� id ���� ���(id�� res�ȿ� �ֱ� ������  res.id �� �ҷ��´�)
	              console.log(res.kaccount_email);//<---- �ܼ� �α׿� email ���� ��� (������� �˰���?)
	              //console.log(res.profile_image);
	              console.log(res.properties['nickname']);//<---- �ܼ� �α׿� �г��� ���(properties�� �ִ� nickname ���� 
	              // res.properties.nickname���ε� ���� ���� )
	              //console.log(res.created);
	              //console.log(res.status);
	              console.log(authObj.access_token);//<---- �ܼ� �α׿� ��ū�� ���
	  
	  
	             alert(res.properties.nickname+"�� ȯ���մϴ�.");
	    	},
	    	
  
            
          
    		//fncKakaoLogin(authObj);
    		/* Kakao.Auth.setAccessToken(accessTokenFromServer); */
		  		fail: function(err) {
		     		alert(JSON.stringify(err));
		     		document.getElementById('kakao-login-result').innerText = 'fail: ' + JSON.stringify(err);
		  		}
			})
	    }
	    });


	function fncKakaoLogin(authObj) {
		var data = JSON.stringify(authObj);
		
		$.ajax({
            url:"/user/json/kakaoLogin" ,
            type: "POST",
            data: data,
            dataType: "json",
            headers : {
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},
            success: function(res){
               alert("restController :: json/kakaoLogin �ٳ��"); 
      
            },
            error: function(error) {
            	alert("restController :: ����")
            }
        });
	}
	
</script>
</body>

</html>