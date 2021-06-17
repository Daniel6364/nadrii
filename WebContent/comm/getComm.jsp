<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>나만의 나들이</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		
		<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
		<link rel="stylesheet" href="/resources/helios/assets/css/main.css" />		
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
		<script src="https://code.jquery.com/jquery-2.1.4.js"></script>
		<script type="text/javascript">
		$(function(){
			$("a#modify").bind("click", function(){
				self.location="/comm/updateCommView?postNo=${community.postNo}";
			});
			
			$("a#delete").bind("click", function(){
				if(confirm("삭제하시겠습니까?")==true){
					self.location="/comm/deleteComm?postNo=${community.postNo}";
					alert("삭제되었습니다.");
				}else{
					return;	
				}		
			});
			
			var regDate = '${community.regDate}'.slice(0, -5);
			
			$("span.regDate").append(regDate);		
			
			$("header h1").on("click", function(){
				self.location = "../comm/listComm";				
			});
			
		});
		
		function clickProfile(){
			alert('${group.join.userId}');
		}

		function clickMessage(){	
			window.open("/message/addMessage?recevierId="+'${community.user.userId}',"addMessgeView","width=400, height=360,status=no, scrollbars=no, location=no");
		}

		function addLike(){
			
			$.ajax({
				url: "../like/json/addLike/"+groupNo,
				method: "POST",
				dataType: "json",
				success:function(returnData){
					getCountLike();
				}
			});	
			
		}

		var likeCount;

		function getCountLike(){
			
			$.ajax({
				url: "../like/json/getCountLike/"+groupNo,
				method: "POST",
				dataType: "json",
				success:function(returnData){
					
					likeCount = returnData.totalCount;

				}
			});	
		}

		$(function(){
			$(".like").bind("click", function(){
				alert(1);
			});
		});
		</script>
		<style type="text/css">
			.btn.btn-default{
				background-color: lightgray;
				width: auto;
				height: auto;
				padding: 10px;
			}
			img{
				max-width: 90%;
				height: auto;
			}
			hr{
				top: 2em;
				margin-bottom: 4em;
			}
			input.form-control{
				margin: 1px;
			}
			.wrapper{
				margin: 0 !important;
			}	
			a:hover{
				border: none;
				text-decoration: none;
			}
		</style>
	</head>
	<body class="no-sidebar">
		<div id="page-wrapper">
			<input type="hidden" name="postNo" value="${community.postNo}">
			<!-- Header -->
				<div id="header">
					<!-- Inner -->
						<div class="inner">
							<header>
								<h1>나만의 나들이</h1>
							</header>
						</div>
						
					<jsp:include page="../layout/toolbar.jsp" />
						
				</div>

			<!-- Main -->
				<div class="wrapper style1">

					<div class="container">
						<article id="main" class="special" align="center" style="position: relative; height:auto;">
							<header style="border-bottom: 1px solid; padding-bottom: 60px;">
								<h2><a href="#">${community.title}</a></h2>
								<br/>
								<div style="float: left;"> 
								<c:if test="${community.user.profileImageFile != null}">
									<img src="${community.user.profileImageFile}" alt="" style="width: 36px; height: 36px; border-radius: 5em;"/>
								</c:if>
								<c:if test="${community.user.profileImageFile == null}">
									<img src="../resources/assets/images/avatar.jpg" alt="" style="border-radius: 5em; height: 100%"/>
								</c:if>
									<a href="#none" style="position: relative;">
										<span style="vertical-align: top;" class="name" data-container="body" data-toggle="popover">&nbsp;&nbsp;${community.user.userId}</span>
									</a>
								</div>
								<div style="float: right;">
									<span style="padding-right: 30px;"><span class="fas fa-eye"></span> &nbsp;&nbsp;${community.viewCount}</span>
									<span class="regDate" style="border-left: 1px solid; padding-left: 30px;"></span>
								</div>
							</header>
							<br/>
							<div >
							<c:if test="${community.user.userId == loginUser.userId}">
								<div align="right">
									<a id="modify"><span class="far fa-edit"></span></a>
									&nbsp;
									<a id="delete"><span class="fas fa-eraser"></span></a>
								</div>
							</c:if>
							<c:if test="${community.user.userId != loginUser.userId}">
								<div align="right">
									<a id="modify"><span class="far fa-edit" style="display: none;"></span></a>
									&nbsp;
									<a id="delete"><span class="fas fa-eraser" style="display: none;"></span></a>
								</div>
							</c:if>
								${community.text}						
							</div>
							<br/><br/>
							<div>
								<span align= "left" class="col-xs-9" style="padding: 0;">
									<img alt="" src="/resources/images/hashtag.png" width="30" height="30">&nbsp;&nbsp;<span style="font-size: 1em;">${community.hashtag}</span>
								</span>
								<span align="right" class="col-xs-3">
									<input type="hidden" name="postNo" value="${community.postNo}">
									<jsp:include page="../common/like.jsp"></jsp:include>
								</span>
							</div>
						</article>
						<br/><br/><br/>
						<div>
						<jsp:include page="../common/comment2.jsp"></jsp:include>
						</div>
					</div>
				</div>
		</div>
		<!-- Scripts -->
			<script src="/resources/helios/assets/js/jquery.min.js"></script>
			<script src="/resources/helios/assets/js/jquery.dropotron.min.js"></script>
			<script src="/resources/helios/assets/js/jquery.scrolly.min.js"></script>
			<script src="/resources/helios/assets/js/jquery.onvisible.min.js"></script>
			<script src="/resources/helios/assets/js/skel.min.js"></script>
			<script src="/resources/helios/assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="/resources/helios/assets/js/main.js"></script>

	</body>
</html>