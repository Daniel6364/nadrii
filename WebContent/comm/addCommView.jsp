<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="../resources/helios/assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="../resources/helios/assets/css/bootstrap.min.css" />	
<link rel="stylesheet" href="../resources/helios/assets/css/main.css" />
<!--[if lte IE 8]><link rel="stylesheet" href="../resources/helios/assets/css/ie8.css" /><![endif]-->
		
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../resources/helios/assets/js/jquery.min.js"></script>
<script src="../resources/helios/assets/js/jquery.dropotron.min.js"></script>
<script src="../resources/helios/assets/js/jquery.scrolly.min.js"></script>
<script src="../resources/helios/assets/js/jquery.onvisible.min.js"></script>
<script src="../resources/helios/assets/js/skel.min.js"></script>
<script src="../resources/helios/assets/js/util.js"></script>
<!--[if lte IE 8]><script src="../resources/helios/assets/js/ie/respond.min.js"></script><![endif]-->
<script src="../resources/helios/assets/js/main.js"></script>
<script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>

<script type="text/javascript">
$(function() {
	$(document).ready(function() {
		if($('input[name="userId"]').val()==null || $('input[name="userId"]').val()==""){
			self.location="../user/loginView.jsp"
		}
	})
	
	$('a#submit').bind('click', function() {
		if($('input[name="title"]').val() == null || $('input[name="title"]').val() == ''){
			alert('제목을 입력해주세요');
			return;
		}else if($('textarea[name="text"]').val() == null || $('textarea[name="text"]').val() == ''){
			alert('내용을 입력해주세요')
			return;
		}else if($('input[name="lat"]').val() == null || $('input[name="lat"]').val() == ''){
			alert('방문하신 장소를 하나 이상 등록해주세요')
			return;
		}else if($('input[name="thumbNailFileName"]').val() == null || $('input[name="thumbNailFileName"]').val() == ''){
			alert('썸네일 이미지를 선택해주세요')
			return;
		}	
		var hashtags=',';
		$('button.selectedhashtagButtons').each(function() {
			hashtags += $(this).val()+',';
		})
		$('input[name="hashtag"]').val(hashtags);
		if("${menu}"=="update"){
			$('form[name=postData]').attr("action", "updateComm").attr("method", "POST").submit();
		}else{
			$('form[name="postData"]').attr("action", "addComm").attr("method", "POST").submit();
		}
	})
	
	$("header h1").on("click", function(){
		self.location = "../comm/listComm";
	});
})
</script>
<style type="text/css">
input:focus{
	outline:none;
}
select:focus{
	outline:none;
}
button[data-original-title='More Color']{
	width: 20px !important;
}
.panel-heading{
	text-align: center !important;
}
.wrapper{
	margin: 0;
	padding: 6em 0 10em 0;
}
.placeButton{
	width: auto !important;
	height: auto;
	padding: 0.5em;
	align-items: unset;
}
</style>

</head>
<body class="no-sidebar">
	<div id="page-wrapper">
				<div id="header">
			<jsp:include page="../layout/toolbar.jsp"></jsp:include>
			<!-- Inner -->
			<div class="inner">
				<header>
					<h1>나만의 나들이</h1>
				</header>
			</div>
		</div>
		<div class="body">
			<div class="wrapper style1">
				<div class="container" align="center">
				<br/>
					<form name="postData">
					<c:if test="${community.postNo != null}">			
						<input type="hidden" name="postNo" value="${community.postNo}">
					</c:if>
						<input type="hidden" name="userId" value="${loginUser.userId}">
						<%-- ${loginUser.userId} --%>
						<div class="form-group">
						    <input type="title" class="form-control" name="title" placeholder="제목을 입력하세요" value="${community.title}">
						 </div>
						 <div>
						  	<jsp:include page="noteEditor.jsp"></jsp:include>
						 </div>
				 		 <br/>
						 <div id="cndHashTags">
							 <c:if test="${! empty hashtags}">
							 	<c:set var="i" value="0" />
							 	<c:forEach var="hashtags" items="${hashtags}">
							 		<c:set var="i" value="${i+1}" />
							 			<button type="button" class="selectedhashtagButtons" value="${hashtags}"><span class="fas fa-check-circle"></span>&nbsp;#${hashtags}</button>&nbsp;
							 	</c:forEach>
							 </c:if>
						 </div>
						 <br/>	
					</form> 
					<div class="box-bottom" >
						<a id="submit" style="">저장하기</a>
					</div>
					<br/>
				</div>	
			</div>
		</div>
	</div>
</body>
<style type="text/css">
#cndHashTags > button{
	width: auto !important;
	height: auto;
	line-height: 1.5em;
	position: relative;
	font-weight: 400;
}
</style>
</html>