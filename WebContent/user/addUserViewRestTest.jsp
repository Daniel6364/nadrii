<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>


<!DOCTYPE html>

<html lang="ko">
   
<head>
   <meta charset="UTF-8">
   
   <!-- 참조 : http://getbootstrap.com/css/   참조 -->
   <meta name="viewport" content="width=device-width, initial-scale=1.0" />
   
   <!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
   <!--  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>-->
   <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
   
   <!--  ///////////////////////// CSS ////////////////////////// -->
   <style>
       body > div.container{
           border: 3px solid #D6CDB7;
            margin-top: 10px;
        }
        
        .container-fluid {
  padding-right: 15px;
  padding-left: 15px;
  margin-right: auto;
  margin-left: auto;
}
  
    </style>
    
              
<script>

	$( function() {
		$("button[type='button']:contains('가입')").on("click",function(){
    		$("form").attr("method","POST").attr("action","/user/addUser").submit();
    	});
		
		$("button[type='button']:contains('restAdd')").on("click",function(){
    		restAdd();
    	});
		
		$("button[type='button']:contains('restLogin')").on("click",function(){
    		restLogin();
    	});
		
		$("button[type='button']:contains('restgetId')").on("click",function(){
    		restgetId();
    	});
		
		$("button[type='button']:contains('restgetPassword')").on("click",function(){
    		restgetPassword();
    	});
		
	});
    
	
	
	
	function restAdd(){
		var jsonData = JSON.stringify({
				"userId":$("#userId").val(),
				"password":$("#password").val(),
				"email":$("#email").val()
		});
		
		$.ajax({
            method: "POST",
            url: "/user/json/addUser",
            contentType: "application/json;charset=UTF-8",
            data:jsonData,
            dataType: "json",
            success: function(returnData) {
				
            	alert(returnData.systemMessage);
                

            }
        });
	}
	

	function restLogin(){
		var jsonData = JSON.stringify({
				"userId":$("#userId").val(),
				"password":$("#password").val(),
				"email":$("#email").val()
		});
		
		$.ajax({
            method: "POST",
            url: "/user/json/login",
            contentType: "application/json;charset=UTF-8",
            data:jsonData,
            dataType: "json",
            success: function(returnData) {
				
            	alert(returnData.systemMessage);
                

            }
        });
	}
	
	function restgetId(){
		var jsonData = JSON.stringify({
				"userId":$("#userId").val(),
				"password":$("#password").val(),
				"email":$("#email").val()
		});
		
		$.ajax({
            method: "POST",
            url: "/user/json/findIdPg",
            contentType: "application/json;charset=UTF-8",
            data:jsonData,
            dataType: "json",
            success: function(returnData) {
				
            	alert(returnData.systemMessage);
                

            }
        });
	}

	
	function restgetPassword(){
		var jsonData = JSON.stringify({
				"userId":$("#userId").val(),
				"password":$("#password").val(),
				"email":$("#email").val()
		});
		
		$.ajax({
            method: "POST",
            url: "/user/json/findPasswordPg",
            contentType: "application/json;charset=UTF-8",
            data:jsonData,
            dataType: "json",
            success: function(returnData) {
				
            	alert(returnData.systemMessage);
                

            }
        });
	}
    
</script>
</head>

<body>

   <!-- ToolBar Start /////////////////////////////////////-->
   <div class="navbar  navbar-default">
        <div class="container">
           <a class="navbar-brand" href="/index.jsp">Model2 MVC Shop</a>
         </div>
      </div>
      <!-- ToolBar End /////////////////////////////////////-->

   <!--  화면구성 div Start /////////////////////////////////////-->
   <div class="container">
   
      <h1 class="bg-success text-center">회 원 가 입</h1>
      
      <!-- form Start /////////////////////////////////////-->
      <form id ="frm" class="form-horizontal">
      <input id="checkNumStatus" name="checkNumStatus" type="hidden" value="N">
        <div class="form-group">
          <label for="userId" class="col-sm-offset-1 col-sm-3 control-label">아 이 디</label>
          <div class="col-sm-4">
            <input type="text" placeholder="Enter ID" class="form-control" id="userId" required class="userid" name="userId" value="${kakaoId}" autofocus>
            <span id = "chkMsg"></span>
          </div>
         <div id="htmlId"></div>
        </div>
        
        <div class="form-group">
          <label for="password" class="col-sm-offset-1 col-sm-3 control-label">비밀번호</label>
          <div class="col-sm-4">
            <input type="password" class="form-control password" id="password" name="password" placeholder="비밀번호"/>
          </div>
          <div id="htmlId"></div>
        </div>
        
        <div class="form-group">
          <label for="password2" class="col-sm-offset-1 col-sm-3 control-label">비밀번호 확인</label>
          <div class="col-sm-4">
            <input type="password" class="form-control" id="password2" name="password2" placeholder="비밀번호 확인" />
          </div>
        </div>
         <div class="form-group">
          <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">이메일</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" id="email" name="email" placeholder="이메일" />
          </div>
        </div> 
        
        <div class="form-group">
          <div class="col-sm-offset-4  col-sm-4 text-center">
            <button type="button" class="btn btn-success cancelbtn signupCheck signupbtn" >restAdd</button>
            <button type="button" class="btn btn-success cancelbtn signupCheck signupbtn" >restLogin</button>
			<button type="button" class="btn btn-success cancelbtn signupCheck signupbtn" >restgetId</button>
			<button type="button" class="btn btn-success cancelbtn signupCheck signupbtn" >restgetPassword</button>
			            

           <a class="btn btn-primary btn" href="#" role="button" >취소</a>
          </div>
        </div>
      </form>
      <!-- form Start /////////////////////////////////////-->

      
    </div>
   <!--  화면구성 div end /////////////////////////////////////-->

   <!--<input type="hidden" id="uid" value="${uid}" />  facebook 로그인 -->

</body>

</html>