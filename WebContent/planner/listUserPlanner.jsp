<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Nadrii :: 나들이 플래너 게시판</title>
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="../resources/helios/assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="../resources/helios/assets/css/bootstrap.min.css" />	
		<link rel="stylesheet" href="../resources/helios/assets/css/main.css" />
		<!--[if lte IE 8]><link rel="stylesheet" href="../resources/helios/assets/css/ie8.css" /><![endif]-->
				
		<script src="../resources/helios/assets/js/jquery.min.js"></script>
		<script src="../resources/helios/assets/js/jquery.dropotron.min.js"></script>
		<script src="../resources/helios/assets/js/jquery.scrolly.min.js"></script>
		<script src="../resources/helios/assets/js/jquery.onvisible.min.js"></script>
		<script src="../resources/helios/assets/js/skel.min.js"></script>
		<script src="../resources/helios/assets/js/util.js"></script>
		<!--[if lte IE 8]><script src="../resources/helios/assets/js/ie/respond.min.js"></script><![endif]-->
		<script src="../resources/helios/assets/js/main.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
				
		<script type="text/javascript">
		
		window.onload = function(){
			 getSomething();
		}
		
		$(function() {
			$('#searchKeyword').keydown(function(key) {
				var data = $(this).val();
				if(key.keyCode==13){
					if(data!=""){
						$('form.search').attr('method', 'POST').attr('action', '../planner/getUserPlannerList').submit();
					}else{	
			 			alert("검색어를 입력해주세요");
					}
				}
			});
			
			$("#plannerWrite").on("click", function(){
				self.location="../planner/addPlannerView";
			});
			
			$('.image.featured').on('click', function() {
				var postNo = $($('input[name="postNo"]')[$('.image.featured').index(this)]).val();
				self.location = "../planner/getPlanner?postNo="+postNo;
			});
			
			$('a[name="title"]').on('click', function() {
				var postNo = $($('input[name="postNo"]')[$('a[name="title"]').index(this)]).val();
				self.location = "../planner/getPlanner?postNo="+postNo;
			});	
			
			$(".author span").on("click", function(){
				var userId = $($("input[id='userId']")[$(".author span").index(this)]).val();
				//self.location="../user/getUserProfile?userId="+userId;	
			});
			
			$("header h1").on("click", function(){
				self.location="../planner/getUserPlannerList";
			});
			
			$('[data-toggle="popover"]').popover({ 
				html: true,
				container: 'body',
				content: '<a href="#none" class="message" onclick="javascript:clickMessage()" style="color: #656565;"> 쪽지 보내기 <span class="fas fa-envelope"></span></a>',
				placement: 'bottom',
			});	
		});
		
		function clickProfile(){
			alert(userId);
		}
		
		function clickMessage(){
			window.open("../message/addMessage?receiverId="+userId,"addMessgeView","width=400, height=360,status=no, scrollbars=no, location=no");
		}
		
		var userId;
		
		function getIndex(k){
			userId = $($("input[name='userId']")[$(".author span").index(k)]).val();
		}
		
		function getSomething(){
			
			var totalCount = ${resultPage.totalCount};
			
			for(var i=0; i< totalCount ; i++){
				var postNo = $($('input[name="postNo"]')[i]).val();
				
				$.ajax({
					url: "../like/json/listLikeByPost/"+postNo,
					dataType: "json",
					async: false,
					success:function(returnData){
						
						$("article.special:nth-child("+(i+1)+") .like").empty();
						$("article.special:nth-child("+(i+1)+") .like").append(returnData.totalCount);
						
						if( ((JSON.stringify(returnData.list)).indexOf("${loginUser.userId}") == -1) || ("${loginUser.userId}"=='') || ("${loginUser.userId}" == null) ){
		                	$("article.special:nth-child("+(i+1)+") .heart").append('<i class="far fa-heart"></i>');
		                }else{
		                    $("article.special:nth-child("+(i+1)+") .heart").append('<i class="fas fa-heart" name="full"></i>');
		                }
					}
				});	
				
				$.ajax({
					url:"/common/listCommentByPost",
					method:"GET",
					async: false,
					data:{
						"postNo": postNo
					},
					success: function(JSONData) {
						
						$("article.special:nth-child("+(i+1)+") .comment").empty();
						$("article.special:nth-child("+(i+1)+") .comment").append(JSONData.totalCount);
						
					}
				});
						
			}//for문
		}
				
		</script>
		<style type="text/css">
			.filter {
			  position: relative;
			  -webkit-filter: contrast(140%) sepia(50%);
			  filter: contrast(140%) sepia(50%);
			}
			.filter::before {
			  content: "";
			  display: block;
			  height: 100%;
			  width: 100%;
			  top: 0;
			  left: 0;
			  position: absolute;
			  pointer-events: none;
			  mix-blend-mode: lighten;
			  background: rgba(161, 44, 199, 0.31);
			}
			#nav {
			    background-color: #3b2b48;
			    opacity: 0.7;
			}
			div.wrapper.style1{
				background-image: url("/resources/images/background_3.jpg");
			}
			#searchKeyword{
				border-radius: 30px;
				width: 400px;
				height: 50px;
				padding: 1em 1em 1em 2.5em;
			}
			.image.featured{
				margin: 0 0 1em 0;
			}
			article {
				height: 500px;
			}
			.icons {
				position: absolute;
				bottom: 10px;
				right: 10px;
			}
			.author {
				position: absolute;
				bottom: 0px;
				left: 10px;
			}
			a.author:hover {
				text-decoration: none !important;
			}
			input:focus{
				outline:none;
			}
			svg[name="full"]{
				color: #F05643 !important;
			}
			.row > * {
			    padding: 24px 0 0 24px;
			    margin: 0 !important;
			}
			#header{
				padding: 6.5em 0 1.5em 0;
 			}
		</style>
	</head>
	<body>
		<!-- Header -->
		<div id="header">
			<!-- Inner -->
			<div class="inner">
				<header>
					<h1>나들이 플래너</h1>
					<p>나의 나들이 플래너를 회원과 공유하세요</p>
				</header>
			</div>
			<jsp:include page="../layout/toolbar.jsp" />
		</div>
		<div class="wrapper style1">
			<section id="features" class="container special">
				<div class="continer">
					<div class="col-sm-12" style="padding-bottom: 40px;">
						<h6 style="color: #8a8c91; float: left; font-style:normal;">
						<c:if test="${search.searchKeyword != null}">
							'${search.searchKeyword}' 검색 결과 &nbsp;&nbsp;>> &nbsp;
						</c:if>
						<c:if test="${search.searchKeyword == null}">
							전체
						</c:if>
							 ${resultPage.totalCount}개 게시물
						</h6>
					</div>
					<div style="">
						<div id="plannerWrite" style="font-size: 30px; float: left; color: #6c5e78; margin: 10px;">
							<a class="fas fa-plus"></a>
						</div>
						<form class="search" style="float: right;">
							<span class="fas fa-search" style="position: relative; margin-right: -40px;"></span>
							<input type="text" name="searchKeyword" id="searchKeyword" value="" style="width: 100%"/>
						</form>
					</div>
				</div>	
				<div class="row">
					<c:set var="i" value="0" />
					<c:forEach var="planner" items="${list}">
						<c:set var="i" value="${i+1}"/>
							<article class="4u 12u(mobile) special">
								<div style="background-color: white; height: 450px; padding: 10px 10px 0 10px; position: relative;">
									<input type="hidden" name="postNo" value="${planner.postNo}"/>
									<a href="#" class="image featured"><span class="PlannerThumbNail">
										<img src="${planner.photo }" alt="" class="filter" height="245px">
									</span></a>
									<header style="text-align: center;"><br>
										<h3><a href="#none" name="title">${planner.title}</a></h3>
										<time class="published" datetime="${planner.regDate}">${planner.regDate}</time>
									</header>
																	
									<div>
										<div class="author" style="float: left;"> 
											<c:if test="${empty planner.user.profileImageFile}">
												<img src="../resources/assets/images/avatar.jpg" alt="" style="border-radius: 15em; height: 1.8em; width: 1.8em"/>
											</c:if>
											<c:if test="${! empty planner.user.profileImageFile}">
												<img src="${planner.user.profileImageFile}" alt="" style="border-radius: 15em; height: 1.8em; width: 1.8em"/>
											</c:if>
											<a href="#none" style="position: relative;">
												<input type="hidden" name="plannerMakerId" value="${planner.user.userId}"/>
												<span style="vertical-align: top;" class="name" data-container="body" data-toggle="popover" onclick="javascript:getIndex(this);">&nbsp;&nbsp;${planner.user.userId}</span>
											</a>
										</div>
										<div class="icons" style="float: right;">
											<span>view :  ${planner.viewCount}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<span class="heart"></span>&nbsp;<span class="like"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="far fa-comment"></i>&nbsp;<span class="comment"></span>
										</div>
									</div>
								</div>
						</article>
					</c:forEach>
				</div>
			</section>
		</div>
	</body>
</html>