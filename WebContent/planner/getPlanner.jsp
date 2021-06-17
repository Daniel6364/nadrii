<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<!--
	Helios by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
	<title>나들이 플래너</title>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />

	<!--[if lte IE 8]><script src="/resources/helios/assets/js/ie/html5shiv.js"></script><![endif]-->
	<link rel="stylesheet" href="../resources/helios/assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/resources/helios/assets/css/main.css" />
	<!--[if lte IE 8]><link rel="stylesheet" href="/resources/helios/assets/css/ie8.css" /><![endif]-->

	<!-- Scripts -->
	<script src="/resources/helios/assets/js/jquery.min.js"></script>
	<script src="/resources/helios/assets/js/jquery.dropotron.min.js"></script>
	<script src="/resources/helios/assets/js/jquery.scrolly.min.js"></script>
	<script src="/resources/helios/assets/js/jquery.onvisible.min.js"></script>
	<script src="/resources/helios/assets/js/skel.min.js"></script>
	<script src="/resources/helios/assets/js/util.js"></script>
	<!--[if lte IE 8]><script src="/resources/helios/assets/js/ie/respond.min.js"></script><![endif]-->
	<script src="/resources/helios/assets/js/main.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=162ee19a901cbbe89c0c4b261ddecca3&libraries=services"></script>
	
	<style type="text/css">

	a:hover{
		text-decoration: none;
	}
	footer a {
		color: #555 !important;
	}
	
	.post>footer {
		background: #ffff;
	}

	#groupName {
		display: inline-block;
		font-family: "Raleway", Helvetica, sans-serif;
		font-size: 0.6em;
		font-weight: 400;
		letter-spacing: 0.25em;
		line-height: 1;
		margin: 0 0 0 0em;
		padding: 0 2em 0 0em;
		float: left;
	}
	
	#count {
		display: inline-block;
		font-family: "Raleway", Helvetica, sans-serif;
		font-size: 0.6em;
		font-weight: 400;
		letter-spacing: 0.25em;
		line-height: 1;
		margin: 0 0 0 2em;
		padding: 10px 2em 0 2em;
		float: right;
	}
	
	#pathImg {
		text-align: center;
		word-break: keep-all;
		
	}
	
	#markerImg {
		display: inline-block;
		width: 100px;
		font-size: 17px;
		line-height: 23px;
		vertical-align: middle;
		text-align: center;
		margin: 14px;
		position: relative;	
	}
	
	#path {
		text-align: center;
		word-break: keep-all;
		position: absolute;
		width: 992px;
	}
	
	#address {
		display: inline-block;
		width: 100px;
		font-size: 17px;
		line-height: 23px;
		vertical-align: middle;
		text-align: center;
		margin: 14px;
	}
		
	.panel-heading{
		display: none; 
		border-style: solid; 
		border-width: 2px;
		border-top-left-radius: 12px;
		border-top-right-radius: 12px;
		border-color: #afafaf;
	}
	
	#sidebar{
		padding-top: 210px;
	}
	
	@media screen and (max-width: 736px){
		#sidebar{
			padding-top: 40px;
			margin-top: -70px;
		}
	}
	
	.popover.fade.top.in > h3{
		font-weight: bold;
	}
	
	/************************애니메이션 종료위치를 설정하기 위해 변수별로 따로 css지정***************************/
	.transport1 {
		display: inline-block;
		width: 75px;
		height: 57px;
		font-size: 16px;
		line-height: 20px;
		vertical-align: middle;
		text-align: center;
		position: absolute;
		animation: mymove1 3s infinite;
		margin-bottom: -20px;
		z-index:9999;
	}
	
	
	.transport1 {
		animation-timing-function: linear;
	}
	
	@keyframes mymove1 {
		from {left: 20px;
		}
		
		to {
			left: 130px;
		}
	}
	
	.transport2 {
		display: inline-block;
		width: 75px;
		height: 57px;
		font-size: 16px;
		line-height: 20px;
		vertical-align: middle;
		text-align: center;
		position: absolute;
		animation: mymove2 6s infinite;
		margin-bottom: -20px;
		z-index:9999;
	}
	
	.transport2 {
		animation-timing-function: linear;
	}
	
	@keyframes mymove2 {
		from {left: 20px;
		}
		
		to {
			left: 260px;
		}
	}
	
	.transport3 {
		display: inline-block;
		width: 75px;
		height: 57px;
		font-size: 16px;
		line-height: 20px;
		vertical-align: middle;
		text-align: center;
		position: absolute;
		animation: mymove3 9s infinite;
		margin-bottom: -20px;
		z-index:9999;
	}
	
	.transport3 {
		animation-timing-function: linear;
	}
	
	@keyframes mymove3 {
		from {left: 20px;
		}
		
		to {
			left: 390px;
		}
	}
	
	.transport4 {
		display: inline-block;
		width: 75px;
		height: 57px;
		font-size: 16px;
		line-height: 20px;
		vertical-align: middle;
		text-align: center;
		position: absolute;
		animation: mymove4 12s infinite;
		margin-bottom: -20px;
		z-index:9999;
	}
	
	.transport4 {
		animation-timing-function: linear;
	}
	
	@keyframes mymove4 {
		from {left: 20px;
		}
		
		to {
			left: 520px;
		}
	}
	
	.transport5 {
		display: inline-block;
		width: 75px;
		height: 57px;
		font-size: 16px;
		line-height: 20px;
		vertical-align: middle;
		text-align: center;
		position: absolute;
		animation: mymove5 15s infinite;
		margin-bottom: -20px;
		z-index:9999;
	}
	
	.transport5 {
		animation-timing-function: linear;
	}
	
	@keyframes mymove5 {
		from {left: 20px;
		}
		
		to {
			left: 650px;
		}
	}
	
	.transport6 {
		display: inline-block;
		width: 75px;
		height: 57px;
		font-size: 16px;
		line-height: 20px;
		vertical-align: middle;
		text-align: center;
		position: absolute;
		animation: mymove6 18s infinite;
		margin-bottom: -20px;
		z-index:9999;
	}
	
	.transport6 {
		animation-timing-function: linear;
	}
	
	@keyframes mymove6 {
		from {left: 20px;
		}
		
		to {
			left: 780px;
		}
	}
	
	.transport7 {
		display: inline-block;
		width: 75px;
		height: 57px;
		font-size: 16px;
		line-height: 20px;
		vertical-align: middle;
		text-align: center;
		position: absolute;
		animation: mymove7 21s infinite;
		margin-bottom: -20px;
		z-index:9999;
	}
	
	.transport7 {
		animation-timing-function: linear;
	}
	
	@keyframes mymove7 {
		from {left: 20px;
		}
		
		to {
			left: 910px;
		}
	}

	/* 길 이미지 크기조절 */
	.road1 {
		width: 133%;
		height: 10px;
		position: absolute;
		text-align: center;
		margin-top: 51px;
		margin-left: -36px;
	}
	
	.road2 {
	    width: 260%;
	    height: 10px;
	    position: absolute;
	    text-align: center;
	    margin-top: 51px;
		margin-left: -36px;
	}
	
	.road3 {
		width: 390%;
		height: 10px;
		position: absolute;
		text-align: center;
		margin-top: 51px;
		margin-left: -36px;
	}
	
	.road4 {
		width: 520%;
		height: 10px;
		position: absolute;
		text-align: center;
		margin-top: 51px;
		margin-left: -36px;
	}
	
	.road5 {
		width: 650%;
	    height: 10px;
	    position: absolute;
	    text-align: center;
	    margin-top: 51px;
		margin-left: -36px;
	}
	
	.road6 {
	    width: 780%;
	    height: 10px;
	    position: absolute;
	    text-align: center;
	    margin-top: 51px;
		margin-left: -36px;
	}
	
	#loading {
		 width: 100%;  
		 height: 100%;  
		 top: 0px;
		 left: 0px;
		 position: fixed;  
		 display: block;  
		 opacity: 0.7;  
		 background-color: #fff;  
		 z-index: 99;  
		 text-align: center; 
	 } 
	  
	#loading-image {  
		 position: absolute;  
		 top: 50%;  
		 left: 50%; 
		 z-index: 100; 
	 }

</style>

<script>

$(window).load(function() {
    $('#loading').hide();  
});

$(function(){
	
	$("#list").bind("click", function(){
		self.location="/planner/getMyPlannerList";
	});
	
	$("#modify").bind("click", function(){
		self.location="../planner/updatePlanner?postNo=${planner.postNo}";
	});
	
	
	$("#delete").bind("click", function(){
		if(confirm("삭제하시겠습니까?")==true){
			self.location="/planner/deletePlanner?postNo=${planner.postNo}";
			alert("삭제되었습니다.");
		}else{
			return;	
		}		
	});
	
	$(".startImg").on("click", function(){
		var zoomMove = geoPosition;
		
		var bounds = new daum.maps.LatLngBounds();
		
		for(var i=0; i<2; i++){
			bounds.extend(zoomMove[i]);				
		}		
		map.setBounds(bounds);
	});
	
	$(".passImg1").on("click", function(){
		var zoomMove = geoPosition;
		
		var bounds = new daum.maps.LatLngBounds();
		
		for(var i=1; i<3; i++){
			bounds.extend(zoomMove[i]);				
		}		
		map.setBounds(bounds);
	});
	
	$(".passImg2").on("click", function(){
		var zoomMove = geoPosition;
		
		var bounds = new daum.maps.LatLngBounds();
		
		for(var i=2; i<4; i++){
			bounds.extend(zoomMove[i]);				
		}		
		map.setBounds(bounds);
	});
	
	$(".passImg3").on("click", function(){
		var zoomMove = geoPosition;
		
		var bounds = new daum.maps.LatLngBounds();
		
		for(var i=3; i<5; i++){
			bounds.extend(zoomMove[i]);				
		}		
		map.setBounds(bounds);
	});
	
	$(".passImg4").on("click", function(){
		var zoomMove = geoPosition;
		
		var bounds = new daum.maps.LatLngBounds();
		
		for(var i=4; i<6; i++){
			bounds.extend(zoomMove[i]);				
		}		
		map.setBounds(bounds);
	});
	
	$(".passImg5").on("click", function(){
		var zoomMove = geoPosition;
		
		var bounds = new daum.maps.LatLngBounds();
		
		for(var i=5; i<7; i++){
			bounds.extend(zoomMove[i]);				
		}		
		map.setBounds(bounds);
	});
	
	$(".endImg").on("click", function(){
		mapMove();
	});
	
	$(".header.logo").on("click", function(){
		self.location="../planner/getUserPlannerList";
	});
	
});

/*******************toggle 사용**********************/
$(function () {
	$('[data-toggle="popover"]').popover({html: true})
})

</script>

</head>
<body class="right-sidebar">
<div id="loading"><img id="loading-image" src="../resources/images/loading.gif" alt="Loading…" /></div>
	<div id="page-wrapper">
		<!-- Header -->
		<div id="header">
			<!-- Inner -->
			<div class="inner">
				<header>
					<h1>
						<a class="header logo">나들이 플래너</a>
					</h1>
				</header>
			</div>
			<jsp:include page="../layout/toolbar.jsp" />
		</div>

		<!-- Main -->
		<div class="wrapper style1">

			<div class="container">
				<div class="row 200%">
					<div class="8u 12u(mobile)" id="content">
						<article id="main">
							<header style="margin-bottom: 1.5em;">
								<h2>${planner.title}</h2>
								<div style="float: left;">
									<c:if test="${empty planner.user.profileImageFile}">
										<img src="../resources/assets/images/avatar.jpg" alt="" style="border-radius: 5em; height: 36px; width: 36px;"/>
									</c:if>
									<c:if test="${! empty planner.user.profileImageFile}">
										<img src="${planner.user.profileImageFile}" alt="" style="border-radius: 5em; height: 36px; width: 36px;"/>
									</c:if>
									 <a href="#none"
										style="position: relative;"> <span
										style="vertical-align: top;" class="name" data-container="body"
										data-toggle="popover">&nbsp;&nbsp;${planner.user.userId}</span>
									</a>
								</div>
								<div style="float: right;">
									<span style="padding-right: 30px;">view :
										&nbsp;${planner.viewCount}</span> <span
										style="border-left: 1px solid; padding-left: 30px;">${planner.regDate}</span>
								</div>
								<br />
							</header>
							<div style="border-top: 1px solid; padding: 20px 0;">
								<c:if test="${planner.user.userId == loginUser.userId}">
									<div align="right">
										<a id="modify"><span class="far fa-edit"></span></a>
										&nbsp;
										<a id="delete"><span class="fas fa-eraser"></span></a>
									</div>
								</c:if>
							</div>
							
							<div id="map" style="width: 100%; height: 450px;"></div>
							<section id="pathImg">
							</section>
							
							<br>
							
							<section>
								${planner.text}
							</section>
							
						</article>
					</div>
					
					
					<div class="4u 12u(mobile)" id="sidebar">
					
						<hr class="first" style="margin-bottom: 40px;"/>
						
						
						<div id="roadContent" style="padding: 30px 10px 10px 10px; overflow: hidden; font-size: 12pt">
							<div class="panel-group" id="accordion" role="tablist"
								aria-multiselectable="true">
								<div class="panel-heading" role="tab" id="head1">
									<h4 class="panel-title">
										<a role="button" data-toggle="collapse"
											data-parent="#accordion" href="#collapse1"
											aria-expanded="true" aria-controls="collapse1"> <span
											class="fas fa-flag-checkered"></span>&nbsp;경로1
										</a>
									</h4>
								</div>
								<div id="collapse1" class="panel-collapse collapse in"
									role="tabpanel" aria-labelledby="head1">
									<div class="panel-body">
										<div id="roadStartContent"></div>
									</div>
								</div>
								<div class="panel-heading" role="tab" id="head2">
									<h4 class="panel-title">
										<a class="collapsed" role="button" data-toggle="collapse"
											data-parent="#accordion" href="#collapse2"
											aria-expanded="false" aria-controls="collapse2"> <span
											class="fas fa-flag-checkered"></span>&nbsp;경로2
										</a>
									</h4>
								</div>
								<div id="collapse2" class="panel-collapse collapse"
									role="tabpanel" aria-labelledby="head2">
									<div class="panel-body">
										<div id="roadPass1Content"></div>
									</div>
								</div>
								<div class="panel-heading" role="tab" id="head3">
									<h4 class="panel-title">
										<a class="collapsed" role="button" data-toggle="collapse"
											data-parent="#accordion" href="#collapse3"
											aria-expanded="false" aria-controls="collapse3"> <span
											class="fas fa-flag-checkered"></span>&nbsp;경로3
										</a>
									</h4>
								</div>
								<div id="collapse3" class="panel-collapse collapse"
									role="tabpanel" aria-labelledby="head3">
									<div class="panel-body">
										<div id="roadPass2Content"></div>
									</div>
								</div>
								<div class="panel-heading" role="tab" id="head4">
									<h4 class="panel-title">
										<a class="collapsed" role="button" data-toggle="collapse"
											data-parent="#accordion" href="#collapse4"
											aria-expanded="false" aria-controls="collapse4"> <span
											class="fas fa-flag-checkered"></span>&nbsp;경로4
										</a>
									</h4>
								</div>
								<div id="collapse4" class="panel-collapse collapse"
									role="tabpanel" aria-labelledby="head4">
									<div class="panel-body">
										<div id="roadPass3Content"></div>
									</div>
								</div>
								<div class="panel-heading" role="tab" id="head5">
									<h4 class="panel-title">
										<a class="collapsed" role="button" data-toggle="collapse"
											data-parent="#accordion" href="#collapse5"
											aria-expanded="false" aria-controls="collapse5"> <span
											class="fas fa-flag-checkered"></span>&nbsp;경로5
										</a>
									</h4>
								</div>
								<div id="collapse5" class="panel-collapse collapse"
									role="tabpanel" aria-labelledby="head5">
									<div class="panel-body">
										<div id="roadPass4Content"></div>
									</div>
								</div>
								<div class="panel-heading" role="tab" id="head6">
									<h4 class="panel-title">
										<a class="collapsed" role="button" data-toggle="collapse"
											data-parent="#accordion" href="#collapse6"
											aria-expanded="false" aria-controls="collapse6"> <span
											class="fas fa-flag-checkered"></span>&nbsp;경로6
										</a>
									</h4>
								</div>
								<div id="collapse6" class="panel-collapse collapse"
									role="tabpanel" aria-labelledby="head6">
									<div class="panel-body">
										<div id="roadPass5Content"></div>
									</div>
								</div>
							</div>
						</div>
											
					</div>
				</div>
				
				<article id="main" class="special">
					<input type="hidden" name="postNo" value="${planner.postNo}">
					<jsp:include page="../common/like.jsp"></jsp:include>
					<div>
						<jsp:include page="../common/comment2.jsp"></jsp:include>
					</div>
				</article>
				
			</div>

		</div>
	<jsp:include page="../planner/plannerSub/getPlannerSub.jsp"></jsp:include>
	</div>
</body>
</html>