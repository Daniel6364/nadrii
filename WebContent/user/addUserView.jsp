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
    <!-- <script src="https://code.jquery.com/jquery-3.1.1.js"></script> -->
   <!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script>-->
   <script src="https://code.jquery.com/jquery-2.2.3.js"></script>
   <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script> -->
   <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>-->
   
   <!-- tabs -->
	  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	  
	  <link rel="stylesheet" href="/resources/helios/assets/css/main.css" />
	  
	  <!-- Scripts 
			<script src="/resources/helios/assets/js/jquery.min.js"></script>-->
			<script src="/resources/helios/assets/js/jquery.dropotron.min.js"></script>
			<script src="/resources/helios/assets/js/jquery.scrolly.min.js"></script>
			<script src="/resources/helios/assets/js/jquery.onvisible.min.js"></script>
			<script src="/resources/helios/assets/js/skel.min.js"></script>
			<script src="/resources/helios/assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="/resources/helios/assets/js/ie/respond.min.js"></script><![endif]-->
		    <script src="/resources/helios/assets/js/main.js"></script>
   
   <!--  ///////////////////////// CSS ////////////////////////// -->
   <style>
       body > div.container{
           border: 3px solid #D6CDB7;
            margin-top: 10px;
            border:none;
        }
        
        .container-fluid {
  padding-right: 15px;
  padding-left: 15px;
  margin-right: auto;
  margin-left: auto;
}
  
    </style>
    
              
<script>

$(document).ready(function() { 

   $("#userId").val('');
   idCheckFlag = false;
   $(".signupbtn").prop("disabled", true);

});


//   이메일 인증 
function checkSend(){
   var email = $("#email").val();
   var frm = $("#frm").serialize();
   if(email !=""){
      $.ajax({
         type:"POST",
         dataType : "json",
         url:"/user/check",
         async: false,
         data:frm,     //    onclick();
         success :function(result){
            
            $("#btn_submit").hide();
            $("#btn_chkSuccess").css("display","block");
            $("#confirmNum").css("display","block");
         },
         error:function(request,status,error){
              alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
          }
      });
   }else{
      alert("이메일을 입력해주세요");
      $("#email").focus();
      return false;
   }
   
}

//이메일 인증 번호 확인
function checkSuccess(){
   var confirmNum = $("#confirmNum").val();
   var frm = $("#frm").serialize();
   if(confirmNum !=""){
      $.ajax({
         type:"POST",
         dataType : "json",
         url:"/user/checkSuccess",
         async: false,
         data:frm,     //    onclick();
         success :function(result){
            if(result.result == "success"){
               alert("인증 확인되었습니다.");
               $("#checkNumStatus").val("Y");
            }else{
               alert("인증 번호가 다릅니다.");
               $("#checkNumStatus").val("N");
            }
         },
         error:function(request,status,error){
              alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
          }
      });
   }else{
      alert("인증번호를 입력해주세요");
      $("#confirmNum").focus();
      return false;
   }
   
}


 
///     아이디와 비밀번호가 맞지 않을 경우 가입버튼 비활성화를 위한 변수설정
    var idCheckFlag = false;
    var pwdCheck = false;
    //아이디 체크하여 가입버튼 비활성화, 중복확인.
     
    function checkId() {
    
        //var data = "userId=" + $("#userId").val();
        //var userId = $("#userId").val();
        var data = "userId=" + $("#userId").val();
        $.ajaxSettings.traditional = true;
  	    //var frm = $("#frm").serialize();
  	    //alert("아이디 중복체크");
        $.ajax({
               type:"POST",
        //  data : frm,
            data : data,
            url : "/user/json/checkId",     
            dataType: "json",
            success : function(result) {			/* function(result) */
               if(result.check == 1){
                  alert("아이디가 중복되었습니다.");
                  idCheckFlag = false;
                  $("#userId").css("background-color", "#FFCECE");
                  $(".signupbtn").prop("disabled", true);
                   $(".signupbtn").css("background-color", "#aaaaaa");
                   $("#htmlId").html("아이디 중복입니다.").css('color','red');
                  return;
               }else{
                  //alert("사용 가능합니다.");
                  idCheckFlag = true;
                  $("#userId").css("background-color", "#B0F6AC");
                  $(".signupbtn").prop("disabled", false);
                  $("#htmlId").html("사용가능한 아이디 입니다.").css('color','blue');
               }
            },error:function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });    
        
    }
    
    
   
   
    
    function joinform(){
       location.href="getUser.jsp"
          var str3 = document.getElementById('join');

      str3.submit();

      alert("가입이 완료되었습니다.")
   }
    
      var password = $("#password").val();
      var password2 = $("#password2").val();
      
      function checkPwd(){
      if(password == password2 ){
         $("#password2").css("background-color", "#B0F6AC");
         return;
      }
      
      if(password != password2 ){
         $(".signupbtn").prop("disabled", true);
         $(".signupbtn").css("background-color", "#aaaaaa");
         $("#password2").css("background-color", "#FFCECE");
         return;
      }
     }
   
   
   function emailValid(){
      var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/; 
      var email = $("#email").val();
      if(!regExp.test(email)){
         $(".signupbtn").prop("disabled", true);
         $("#email").css("background-color", "#FFCECE");
         return;
      }
      
      if(regExp.test(email)){
         $(".signupbtn").prop("disabled", false);
         $("#email").css("background-color", "#B0F6AC");
      }
   }

  //*/  
     
   
    function addUser(){
       var data = "userId=" + $("#userId").val();
       data += "&password=" + $("#password").val(); 
         data += "&email=" + $("#email").val();
       if($("#userId").val() == ''){
          alert("아이디를 입력해주세요.");
          $("#userId").focus();
          return;
       }
         
         if(idCheckFlag == false){
          alert("아이디가 사용중입니다.");
          $("#userId").val('');
          $("#userId").focus();
          $("#userId").css("background-color", "#B0F6AC");
          return;
       }
         
         if($("#password").val() == ''){
            alert("비밀번호를 입력해주세요.");
            $("#password").focus();
            return;
         }
         
         if($("#password2").val() == ''){
            alert("비밀번호 확인을 입력해주세요.");
            $("#password2").focus();
            return;
         }
       
       if($("#password").val() != $("#password2").val()){
          alert("비밀번호가 일치하지 않습니다.");
          $("#password").val('');
          $("#password2").val('');
          $("#password").focus();
          return;
       }
       
       if($("#email").val() == ''){
          alert("이메일을 입력해주세요.");
          $("#email").focus();
          return;
       }
       
       alert($("#checkNumStatus").val());
       if($("#confirmNum").val() == ""){
          alert("인증번호를 입력해주세요");
          $("#confirmNum").focus();
          return false;
       }
       
        if($("#checkNumStatus").val() == "Y"){
          alert("이메일 인증 완료 되었습니다.");
          return;
       }
       
       if($("#checkNumStatus").val() != "N"){
           alert("이메일 인증이 실패 되었습니다. \n 재인증 해주세요");
           return;
        } 
  }
    
    function delchk(){
        if(confirm("취소하시겠습니까?")){
            location.href = "/user/main";
        }
    }
   
	$( function() {
		$("button[type='button']:contains('가입')").on("click",function(){

    		$("form").attr("method","POST").attr("action","/user/addUser").submit();
    	});
		
		$("button[type='button']:contains('restTest')").on("click",function(){
    		self.location ="addUserViewRestTest.jsp"; 
    	});
		
	});
    
    
</script>
</head>

<body>

   <!-- ToolBar Start /////////////////////////////////////-->
   <!-- Header -->
				<div id="header">

					<div class="inner">
						<header>
							<h1><a href="/index.jsp" id="logo">N A D R I I</a></h1>
						</header>
					</div>
					
						<jsp:include page="/layout/toolbar.jsp" />

				</div>
      <!-- ToolBar End /////////////////////////////////////-->
   <!--  화면구성 div Start /////////////////////////////////////-->
   <div class="container">
  	 <div class="page-header text-info" style="border:none;">
     	 <h1 class="bg-success text-center" style="background:#f0f4f4; font-size:30px; border-bottom: 2px solid #ddd; padding-bottom: 30px; color:#656565">나 들 이 회 원 가 입</h1>
      </div>
      
      <!-- form Start /////////////////////////////////////-->
      <form id ="frm" class="form-horizontal" style="padding-top:50px;">
      <input id="checkNumStatus" name="checkNumStatus" type="hidden" value="N">
        <div class="form-group icon01">
          <label for="userId" class="col-sm-offset-1 col-sm-3 control-label">아 이 디</label>
          <div class="col-sm-4">
            <input type="text" placeholder="Enter ID" class="form-control" oninput="checkId();" id="userId" required class="userid" name="userId" value="${kakaoId}" autofocus>
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
            <input type="text" class="form-control" id="email" name="email" placeholder="이메일" value="${outerUser.email}" />
          </div>
        </div> 
        
        <div class="form-group">
          <div class="col-sm-offset-4  col-sm-4 text-center">
            <button type="button" class="btn btn-success cancelbtn signupCheck signupbtn" >가입</button>
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