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
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<!-- tabs -->
	 	  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
   
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
	
		// 생일
		$( function() {
			$( ".datepicker" ).datepicker({
				changeMonth: true,
				changeYear: true,
				yearRange: "1900:2018",
			});
		} );
		 
		 
//		 function addUserPlus(){		 
//			 var check01 = $("#check01").val;
//			 var checkboxValues = [];
//			    $("input[name='fruits']:checked").each(function(i) {
//			        checkboxValues.push($(this).val());
//			    });
//			 var allData = {"check01": check01, "checkArray": checkboxValues};
			 
//			 var data = "userName=" +$("#userName").val();
//			 if($("userName").val() == ''){
//		   			alert("당신 이름이 맞습니까?");
///		   			$("#userName").focus();
////		   			return;
////		   		}
//			 if(confirm("추가정보를 입력하시겠습니까?")){
//				 $.ajax({
//					 data : allData,
//					 url : "/user/addUserPlus",
//					 type : "POST",
////				     contentType : "application; charset=UTF-8",
//					 success : function(data){
//						 if(result.msg != "success"){
//							 alert("다시한번 확인해 주세요.");
//							 window.opener.location.reload();
//							 return;
//						 }
//						 if(result.msg == "success"){
//							 alert("추가정보 입력되었습니다.");
//							 window.opener.location.reload();
//							 self.close();
//							 locatuin.href="/user/main";
//						 }						 
//					 }
//				 })
//				 }
//			 }
		 
		
		function fncUpdateUser() {
			//			 var data = "userName=" +$("#userName").val();
			//			 if($("userName").val() == ''){
			//		   			alert("당신 이름이 맞습니까?");
			//		   			$("#userName").focus();
			//		   			return;
			//		   		}

			if ($("#password").val() == '') {
				alert("비밀번호를 입력해주세요.");
				$("#password").focus();
				return;
			}

			if ($("#password2").val() == '') {
				alert("비밀번호 확인을 입력해주세요.");
				$("#password2").focus();
				return;
			}
			
			if($("#birth").val() == ''){
				alert("생 년 월 일 입력해주세요.");
				$("#birth").focus();
				return;
			}
			
			if($("#password") != null && $("password") != null){
				var radio = $("input:radio[name='gender']:checked").val(); // checked 없으면 체크한 값이 안나옴...
				console.log("radiobutton : " + radio)
				var birth = $("input[name='birth']").val();
				console.log("생년월일 확인 : " + birth)
				var name = $("input[name='userName']").val();
				console.log("사용자 이름 : " + name)
				var phone = $("hidden[name='phone']").val();
				console.log("핸드폰번호 : " + phone)
				var img = $("hidden[name='profileImageFile']").val(
						$("#image_section").attr("src"));
	
				var value = "";
				if ($("input:text[name='phone2']").val() != ""
						&& $("input:text[name='phone3']").val() != "") {
					var value = $("option:selected").val() + "-"
							+ $("input[name='phone2']").val() + "-"
							+ $("input[name='phone3']").val();
				}
				$("input:hidden[name='phone']").val(value);
				
				$("input").attr("class", "form-control")
	
				$("form").attr("method", "POST").attr("action", "/user/updateUser")
						.submit();
			}
			return;
		}
		//	============== 비밀번호 유효성 검사 =====================
		function checkPwd() {
			var password = $("#password").val();
			var password2 = $("#password2").val();

			if (password == password2) {
				$(".signupbtn").prop("disabled", false);
				$(".signupbtn").css("background-color", "#ef8376");
				$("#password2").css("background-color", "#B0F6AC");
				return;
			}

			if (password == "" || password2 == "") {
				$(".signupbtn").prop("disabled", true);
				$(".signupbtn").css("background-color", "#aaaaaa");
				return;
			}

			if (password != password2) {
				$(".signupbtn").prop("disabled", true);
				$(".signupbtn").css("background-color", "#aaaaaa");
				$("#password2").css("background-color", "#FFCECE");
				return;
			}
		}

		//============= '프로필 사진' ==============
		 $(function() {
			 $("#fileName").change(function(){
				 alert();
				 sendFile(this);
			}); 
		 })
		
		function readURL(input) {
		    if (input.files && input.files[0]) {
		        var reader = new FileReader();
		 
		        reader.onload = function (e) {
		        	$('#image_section').attr('src', e.target.result);
		        }
		 
		        reader.readAsDataURL(input.files[0]);
		    }
		}

		//	버튼 말풍선
		$(document).ready(function() {
			$('[data-toggle="popover"]').popover();
		});

		// ===== '추가' 버튼 처리 =====
		/* $(function() {
		        $("button.btn.btn-success.col-xs-3").on('click', function(){
		        	fncUpdateUser();
		        })
		    }); */

		// ===== '취소' 버튼 처리 =====
		function delchk() {
			if (confirm("취소하시겠습니까?")) {
				location.href = "/user/main";
			}
		}
		function sendFile(input) {              
	         // 파일 전송을 위한 폼생성
			data = new FormData();
		 	data.append("uploadFile", input.files[0]);
		 	$.ajax({ // ajax를 통해 파일 업로드 처리
		 	    data : data,
		 	    type : "POST",
		 	    url : "uploadImage",
		 	    cache : false,
		 	    contentType : false,
		 	    processData : false,
		 	    success : function(data) { // 처리가 성공할 경우
	                $('#image_section').attr("src", data.relativeUrl)
	                $('#profileImageFile').val(data.relativeUrl)
	 	        },
 	        	error : function() {
					alert("파일 업로드에 실패했습니다.")
				}
		 	});
		 }
	</script>		
    	
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

	.updatebtn01{
		font-size:24px;
		margin-top:-6px;
	}
	
	.cancel{
		margin-left:40px;
	}
	
	.cancel01{
		font-size:24px;
		margin-top:-6px;
	}
    
    .groupbtn{
    	padding:50px 0 70px 0;
    }
    
    @media screen and (max-width: 680px){
    	.cancel{ margin:10% 0 10% 0;}
    	.signupbtn{margin-top:10%;}
    	.container{padding:5%}
    	
    } 
    
    .butgroup{margin-bottom:10%;}
    
    body {
    	background: #ffffff;
    }
    </style>
 
</head>

<body>

	<!-- ToolBar Start /////////////////////////////////////-->
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
	
		<h1 class="bg-primary text-center" id="formImg" style="background:#f0f4f4; border-bottom: 2px solid #ddd; font-size:30px; padding-bottom: 30px; color:#656565;">내 정 보 수 정</h1>
		
		<!-- form Start /////////////////////////////////////-->
		<form class="form-horizontal" style="padding-top:50px;">
		
		  <div class="form-group">
          <label for="password" class="col-sm-offset-1 col-sm-3 control-label" style="color:#68A4C4">비밀번호</label>
          <div class="col-sm-4">
            <input type="password" class="form-control password" id="password" name="password" placeholder="비밀번호" style="border-radius: 25px;">
          </div>
          <div id="htmlId"></div>
        </div>
        
        <div class="form-group">
          <label for="password2" class="col-sm-offset-1 col-sm-3 control-label" style="color:#68A4C4">비밀번호 확인</label>
          <div class="col-sm-4">
            <input type="password" class="form-control" id="password2" name="password2" placeholder="비밀번호 확인" oninput="checkPwd();" style="border-radius: 25px;">
          </div>
        </div>
		  
		  
		  <div class="form-group">
		    <label for="birth" class="col-sm-offset-1 col-sm-3 control-label">생 년 월 일</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control datepicker" id="birth" name="birth" value="${user.birth}" placeholder="생 년 월 일" style="border-radius: 25px;">
		      <span id = "chkMsg"></span>
		    </div>
		
		  </div>
		  <!--  
		  <form action="/addUserPlus" class="form-group">
		    <label for="children" class="col-sm-offset-1 col-sm-3 control-label">자녀 유 무</label>
		    <div class="col-sm-4">
		   	  <input type="hidden" id="check01" value="abcd">
		      <label><input type="checkbox" name="fruits" value="있음">있음</label>
			  <label><input type="checkbox" name="fruits" value="없음">없음</label>
		    </div>
		  </form>
		  -->
		  		  
		  <div class="form-group">
		    <label for="gender" class="col-sm-offset-1 col-sm-3 control-label">성 별</label>
		    <div class="col-sm-4">
		      <input type="hidden" id="check02" value="abcd">
		      <label style="padding-right: 60px; float:left;"><input type="radio" name="gender" value="man" ${user.gender == 'man' ? 'checked="checked"' : ''}style="margin-right: 5px;">남</label>
		      <label style="padding-right: 60px"><input type="radio" name="gender" value="woman" ${user.gender == 'woman' ? 'checked="checked"' : ''}style="margin-right: 5px;">여</label>
		    </div>
		   </div>
		  
		  <div class="form-group">
		    <label for="userName" class="col-sm-offset-1 col-sm-3 control-label">이름</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="userName" name="userName" placeholder="회원이름" value="${user.userName}" style="border-radius: 25px;">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">휴대전화번호</label>
		     <div class="col-sm-2">
		      <select class="form-control" name="phone1" id="phone1" style="border-radius: 25px;">
		      		<option value="010" ${user.phone1 == '010' ? 'selected' : ''}>010</option>
		      		<option value="010" ${user.phone1 == '011' ? 'selected' : ''}>011</option>
		      		<option value="010" ${user.phone1 == '016' ? 'selected' : ''}>016</option>
		      		<option value="010" ${user.phone1 == '017' ? 'selected' : ''}>017</option>
		      		<option value="010" ${user.phone1 == '019' ? 'selected' : ''}>019</option>
				</select>
		    </div>
		    <div class="col-sm-2">
		      <input type="text" class="form-control" id="phone2" name="phone2" value="${user.phone2 }" style="border-radius: 25px;">
		    </div>
		    <div class="col-sm-2">
		      <input type="text" class="form-control" id="phone3" name="phone3" value="${user.phone3 }" style="border-radius: 25px;">
		    </div>
		    <input type="hidden" name="phone"/>
		  </div>
		  
		  <div class="form-group">
		  	<label for="profileImageFile" class="col-sm-offset-1 col-sm-3 control-label">프로필 사진</label>
			  <div class="col-sm-2">
				  <!-- <form id="form" runat="server" method="post" enctype="multipart/form-data"> -->
				    <input type="file" id="fileName" value=""/>
				    <img id="image_section" src="${empty user.profileImageFile? '#':user.profileImageFile}" alt="your image" style="border:1px solid #ddd; margin-top:5px" />
				    <input type="hidden" id="profileImageFile" name="profileImageFile" value="${user.profileImageFile}"/>
				<!-- </form> -->
		     </div>
		   </div>
		  
		  <div class="form-group row butgroup">
		    <article class="col-sm-offset-4  col-sm-4 text-center" style="width:90%;">
		      <!-- <button type="button" class="button btn btn-success col-xs-3 signupbtn" data-toggle="popover"
		      data-trigger="hover"data-placement="bottom" 
		      style="margin:0 5% 12% 0; " data-content="이름, 핸드폰번호 다시 확인!" onclick="update()">수 정</button> -->
		      <input type='button' id="updateUser" class="button btn btn-success col-xs-3 signupbtn" value="submit" onclick="fncUpdateUser()"/>
			  <a class="button btn btn-primary col-xs-3 cancel" href="#" role="button" onclick="delchk()">취&nbsp;소</a>
		    </article>
		  </div>
		</form>
		<!-- form Start /////////////////////////////////////-->
		
 	</div>
	<!--  화면구성 div end /////////////////////////////////////-->

	<!-- 메인 script-->
	<jsp:include page="../layout/footer.jsp"></jsp:include>	
		
</body>

</html>