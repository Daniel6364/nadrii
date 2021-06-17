<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<!--  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>-->
<script src="https://code.jquery.com/jquery-2.1.4.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- tabs -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-2.1.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/helios/assets/css/main.css" />
<script defer
	src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
<title>Insert title here</title>
<style type="text/css">
.button01 {
	font-size: 14px;
	padding: 20px 0 18px;
	font-weight: 700;
	width: 30%;
	position: relative;
	outline: 0;
	border: 0;
}

hr {
	position: relative;
	display: block;
	top: 0;
	margin-bottom: 0;
	height: 0;
	border: 0;
	width: 95%;
	border-top: solid 1px rgba(128, 128, 128, 0.2);
	border-bottom: solid 1px rgba(128, 128, 128, 0.2);
}
</style>
<script type="text/javascript">
var currentPage = 1;
var maxPage;
$(function() {
	$('a.col-xs-4').bind('click', function() {
		$('a.col-xs-4').css("border", "0")
		$('span.button01').css("color", "#5b5b5b")
		$(this).css("border-bottom", "4px solid #FE8A71");
		$(this).children().css("color", "#FE8A71")
		if($(this).children().html() == "좋아요"){
			getLike('add');
		}else if($(this).children().html() == "댓글"){
			getComments('add');
		}else{
			getJoin('add');
		}
	})
	$('#logContainer').on('click', 'a.more', function() {
		if(currentPage < maxPage){
		    currentPage++;
		    if($('#logContainer').find('input[name="type"]').val() == "좋아요"){
		    	getLike("update");
		    }else if($('#logContainer').find('input[name="type"]').val() == "댓글"){
		    	getComments("update");
		    }else{
		    	getJoin('update');	
		    }
		        
		}else{
		    return;
		}
	})	
})

function getLike(menu) {
	if(menu == 'add'){
		currentPage=1;
	}
	$.ajax({
		url: "/like/json/listLikeById",
		method: "GET",
		data: {
			"currentPage": currentPage,
			"searchKeyword": "${user.userId}"
		},
		headers: {
			"Accept": "application/json"
		},
		success: function(JSONData) {
			if(JSONData.totalCount%12 == 0){
				maxPage = JSONData.totalCount / 12;
			}else{
				maxPage = Math.floor(JSONData.totalCount / 12)+1;
			}
			console.log(maxPage);
			var html='<div class="likes" align="center"><input type="hidden" name="type" value="좋아요">';
			if(currentPage > 1){
				html += '<hr/>';
			}
			console.log(JSONData)
			for(var i=0;i<JSONData.list.length;i++){
				if(JSONData.title[i] != null){
					html += '<div class="row" style="padding: 1em 0 0 0; margin: 1em 0 1em 0;">'
						+'<div class="col-xs-3" align="center" style="padding:0; color: gray;">'
						+'<span>'+JSONData.yearNMonth[i]+'.</span>'
						+'<span style="font-size:2em; font-weight:700">'+JSONData.day[i]+'</span>'
						+'</div>'
						+'<div class="col-xs-3" align="center" style="padding-top:0; padding-left: 0; padding-right: 0;">'
						+'<i class="fas fa-heart" style="color: #F05643; font-size:1em; "></i>&nbsp;좋아요'
						+'</div>'
						+'<div class="col-xs-6" align="left" style="padding-top:0; padding-left: 0;">'
						+'<p id="addedTitle" style="padding: 0 0 0 5%;"><span style="font-size: 1em; font-weight: 900; color: #3b2b48">'
						+JSONData.title[i]+'<input type="hidden" name="postNo" value="'+JSONData.list[i].postNo+'">'
						+'</span></p>'
						+'</div>'
						+'</div>';
					if(i != JSONData.list.length-1){
						html += '<hr/>';
					}
				}
				if(currentPage < maxPage){
					html+='<a class="more">+ 더보기</a></div>'
				}
			}
			if(menu == 'add'){
				$('#logContainer').html(html);
			}else{
				$('#logContainer').find('a.more').remove();
				$('#logContainer').append(html);
			}
			$('#logContainer').on('click', 'p#addedTitle', function() {
				var postNo = $($('input[name="postNo"]')[$('p#addedTitle').index(this)]).val();
					if(postNo == null){
						alert("삭제된 게시물입니다.");
					}else{
						if(postNo.indexOf("60") != 0 && postNo.indexOf("40") != 0){
							self.location = "/comm/getComm?postNo="+postNo;
						}else if(postNo.indexOf("40") != 0){
							self.location = "/group/getGroup?groupNo="+postNo;
						}else{
							self.location = "/planner/getPlanner?postNo="+postNo;
						}
					}
				})
			}
		})
	}
function getComments(menu) {
	if(menu == 'add'){
		currentPage=1;
	}
	$.ajax({
		url: "/common/listCommentById",
		method: "GET",
		data: {
			"currentPage": currentPage,
			"searchKeyword": '${user.userId}'
		},
		headers: {
			"Accept": "application/json",
			"Content-Type": "application/json"
		},
		success: function(JSONData) {
			console.log(JSONData)
			if(JSONData.totalCount%12 == 0){
				maxPage = JSONData.totalCount / 12;
			}else{
				maxPage = Math.floor(JSONData.totalCount / 12)+1;
			}
			console.log(maxPage);
			var html = '<div class="comments" align="center"><input type="hidden" name="type" value="댓글">';
			if(currentPage > 1){
				html += '<hr/>';
			}
			for(var i=0;i<JSONData.comments.length;i++){
				if(JSONData.title[i] != null){
					html += '<div class="row" style="padding: 1em 0 0 0; margin: 1em 0 1em 0;">'
						+'<div class="col-xs-3" align="center" style="padding:0; color: gray;">'
						+'<span>'+JSONData.yearNMonth[i]+'.</span>'
						+'<span style="font-size:2em; font-weight:700">'+JSONData.day[i]+'</span>'
						+'</div>'
						+'<div class="col-xs-3" align="center" style="padding-top:0; padding-left: 0; padding-right: 0;">';
						html += '<i class="fas fa-comment" style="font-size:1em;"></i>&nbsp;댓글'
						+'</div>'
						+'<div class="col-xs-6" align="left" style="padding: 0 0 0 5%;">'
						+'<p id="addedTitle"><span style="font-size: 1em; font-weight: 900; color: #3b2b48">'
						+JSONData.title[i]+'<input type="hidden" name="postNo" value="'+JSONData.comments[i].postNo+'">'
						+'</span></p>'
						+JSONData.comments[i].text
						+'</div>'
						+'</div>';
						if(i != JSONData.comments.length-1){
							html += '<hr/>';
						}	
				}
			}
			if(currentPage < maxPage){
				html+='<a class="more">+ 더보기</a></div>'
			}
			if(menu == 'add'){
				$('#logContainer').html(html);
			}else{
				$('#logContainer').find('a.more').remove();
				$('#logContainer').append(html);
			}
			$('#logContainer').on('click', 'p#addedTitle', function() {
				var postNo = $($('input[name="postNo"]')[$('p#addedTitle').index(this)]).val();
				if(postNo == null){
					alert("삭제된 게시물입니다.");
				}else{
					if(postNo.indexOf("30") == 0){
						self.location = "/comm/getComm?postNo="+postNo;
					}else if(postNo.indexOf("60") == 0){
						self.location = "/group/getGroup?groupNo="+postNo;
					}else{
						self.location = "/planner/getPlanner?postNo="+postNo;
					}
				}
			})
		}
	})
}
	function getJoin(menu) {
		if (menu == 'add') {
			currentPage = 1;
		}
		$.ajax({
					url : "/join/json/listJoinById",
					method : "GET",
					data : {
						"currentPage" : currentPage
					},
					headers : {
						"Accept" : "application/json",
						"Content-Type" : "application/json"
					},
					success : function(JSONData) {
						console.log(JSONData);
						if (JSONData.totalCount % 12 == 0) {
							maxPage = JSONData.totalCount / 12;
						} else {
							maxPage = Math.floor(JSONData.totalCount / 12) + 1;
						}
						console.log(maxPage);
						var html = '<div class="joins" align="center"><input type="hidden" name="type" value="모임">';
						if (currentPage > 1) {
							html += '<hr/>';
						}
						for (var i = 0; i < JSONData.totalCount; i++) {
							if (JSONData.title[i] != null) {
								html += '<div class="row" style="padding: 1em 0 0 0; margin: 1em 0 1em 0;">'
										+ '<div class="col-xs-3" align="center" style="padding:0; color: gray;">'
										+ '<span>'
										+ JSONData.yearNMonth[i]
										+ '.</span>'
										+ '<span style="font-size:2em; font-weight:700">'
										+ JSONData.day[i]
										+ '</span>'
										+ '</div>'
										+ '<div class="col-xs-3" align="center" style="padding-top:0; padding-left: 0; padding-right: 0;">';
								html += '<i class="fas fa-users" style="font-size:1em;"></i>&nbsp;모임'
										+ '</div>'
										+ '<div class="col-xs-6" align="left" style="padding: 0 0 0 5%;">'
										+ '<p id="addedTitle"><span style="font-size: 1em; font-weight: 900; color: #3b2b48">'
										+ JSONData.title[i]
										+ '<input type="hidden" name="postNo" value="'+JSONData.list[i].groupNo+'">'
										+ '</span></p>' + '</div>' + '</div>';
								if (i != JSONData.totalCount - 1) {
									html += '<hr/>';
								}
							}
						}
						if (currentPage < maxPage) {
							html += '<a class="more">+ 더보기</a></div>'
						}
						if (menu == 'add') {
							$('#logContainer').html(html);
						} else {
							$('#logContainer').find('a.more').remove();
							$('#logContainer').append(html);
						}
						$('#logContainer').on('click','p#addedTitle',function() {
							var postNo = $($('input[name="postNo"]')[$('p#addedTitle').index(this)]).val();
							if (postNo == null) {
								alert("삭제된 게시물입니다.");
							} else {
								if (postNo.indexOf("30") == 0) {
									self.location = "/comm/getComm?postNo="+ postNo;
								} else if (postNo.indexOf("60") == 0) {
									self.location = "/group/getGroup?groupNo="+ postNo;
								} else {
									self.location = "/planner/getPlanner?postNo="+ postNo;
								}
							}
						})
					}
				})
	} 
</script>
</head>
<body onload="javascript:getLike('add');">
<input type="hidden" name="searchKeyword" value="${user.userId}">
 	  <div id="header">
			<div class="inner">
				<header>
					<h1><a href="/index.jsp" id="logo">N A D R I I</a></h1>
				</header>
			</div>	
			<jsp:include page="/layout/toolbar.jsp" />
     </div>
     <div class="container" align="center">
     	<div name="userProfile" style="margin: 20px;height: auto;">
     		<span class="col-xs-4" style="width: 30%" align="right">
     			<c:if test="${! empty user.profileImageFile}">
     				<a><img alt="" src="${user.profileImageFile}" style="width: 60%;"></a>
     			</c:if>
      			<c:if test="${empty user.profileImageFile}">
      				<a><img alt="" src="/resources/images/00742106_105752.jpg" style="width: 60%;"></a>
     			</c:if>
     		</span>
     		<span style="font:bold; font-size: 2em !important; color: #3b2b48;" class="col-xs-8">
     			<p style="padding: 0.5em 0.5em 0.5em 0;">
	     			<a style="font-weight: 700;">${user.userId}</a>
	     			<br/>
	     			<span style="font-size: 12pt;">${user.email}</span>
     			</p>
     			<c:if test="${! empty user.userName}">
     				<p><span style="font-size: 0.4em; color: gray; font-weight:700">이름</span><span style="font-size:0.6em; padding: 0.3em !important;">${! empty user.userName? user.userName:'-'}</span>&nbsp;<span style="font-size: 0.4em; color: gray; font-weight:700">휴대전화</span><span style="padding: 0.3em !important; font-size: 0.6em;">${! empty user.phone? user.phone:'-'}</span>&nbsp;
     				<span style="font-size: 0.4em; color: gray; font-weight:700">성별</span><span style="font-size:0.6em; padding: 0.3em !important;">${! empty user.gender? user.gender:'-'}</span>&nbsp;<span style="font-size: 0.4em; color: gray; font-weight:700">생년월일</span><span style="font-size:0.6em; padding: 0.3em !important;">${!empty user.birth? user.birth:'-'}</span></p>
     				<a style="float: left; font-size: 0.5em; margin: 10px;" href="/user/updateUser?userId=${user.userId}"><span class="fas fa-cog"></span> 프로필수정</a>
     			</c:if>
     			<c:if test="${empty user.userName}">
     				<a style="float: left; font-size: 0.5em; margin: 10px;" href="/user/updateUser?userId=${user.userId}"><span class="far fa-plus-square"></span> 추가 정보를 입력하고 더 다양한 나들이를 즐겨보세요</a>
     			</c:if>
     		</span>
     	</div>
     </div>
     <div class="container">
     	<div style="background-color: white; margin-top:10pt;">
	     	<div style="margin: 20px; padding-bottom:2em; height: inherit;">
	     		<a class="col-xs-4" align="center"><span class="button01" align="center">좋아요</span></a>
	     		<a class="col-xs-4" align="center"><span class="button01" align="center">댓글</span></a>
	     		<a class="col-xs-4" align="center"><span class="button01" align="center">내 모임</span></a>
	     	</div>
	     	<div id="logContainer"></div>
     	</div>
     </div>
     <br/>

     <jsp:include page="../layout/footer.jsp"></jsp:include>
   		<script src="../resources/helios/assets/js/jquery.min.js"></script>
		<script src="../resources/helios/assets/js/jquery.dropotron.min.js"></script>
		<script src="../resources/helios/assets/js/jquery.scrolly.min.js"></script>
		<script src="../resources/helios/assets/js/jquery.onvisible.min.js"></script>
		<script src="../resources/helios/assets/js/skel.min.js"></script>
		<script src="../resources/helios/assets/js/util.js"></script>
		<script src="../resources/helios/assets/js/main.js"></script>
     

</body>
<style type="text/css">
	p{
		margin-bottom: 0;
	}
</style>
</html>