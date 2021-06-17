<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
	$(function() {
		$('.dropdown').dropdown();
	});
</script>
<!-- Header -->
<header id="header">
	<h1>
		<a href="/index.jsp"><strong>N A D R I I</strong></a>
	</h1>
	<nav class="links">
		<ul>
			<li><a href="/trip/getTheme">나들이 정보</a></li>
			<li><a href="/comm/listComm">나만의 나들이</a></li>
			<li>
				<div class="dropdown">
					<a href="#" data-toggle="dropdown" role="button"
						aria-expanded="false"> 나들이 플래너 <b class=" icon-angle-down"></b>
					</a>

					<ul class="dropdown-menu">
						<li><a href="/planner/getUserPlannerList">플래너 게시판</a></li>
						<li><a href="/planner/addPlannerView">플래너 작성</a></li>
						<li><a href="/planner/getMyPlannerList">나의 플래너</a></li>
					</ul>
				</div>
			</li>
			<li><a href="/ticket/listTicket">나들이 티켓</a></li>
			<li><a href="/group/listGroup">나들이 모임</a></li>
		</ul>

		<ul>
			<li><a href="/trip/getTheme">장바구니</a></li>
			<li><a href="/comm/listComm">쪽지보내기</a></li>
		</ul>
	</nav>
	<nav class="main">
		<ul>
			<li class="search"><a class="fa-search" href="#search">Search</a>
				<form id="search" method="get" action="#">
					<input type="text" name="query" placeholder="Search" />
				</form></li>
			<li class="menu"><a class="fa-bars" href="#menu">Menu</a></li>
		</ul>
	</nav>
</header>

<!-- Menu -->
<section id="menu">

	<!-- Search -->
	<section>
		<form class="search" method="get" action="#">
			<input type="text" name="query" placeholder="Search" />
		</form>
	</section>

	<!-- Links -->
	<section>
		<ul class="links">
			<li><a href="#">
					<h3>나들이 정보</h3>
					<p>어디로 나들이 갈지 미리 확인해 보세요.</p>
			</a></li>
			<li><a href="#">
					<h3>나만의 나들이</h3>
					<p>내가 다녀온 나들이 정보를 함께 공유해요.</p>
			</a></li>
			<li><a href="#">
					<h3>나들이 플래너</h3>
					<p>이번 주말 나들이 계획을 미리 세워보자.</p>
			</a></li>
			<li><a href="#">
					<h3>나들이 티켓</h3>
					<p>나들이 가기 전 미리 티켓을 예매하세요.</p>
			</a></li>
			<li><a href="#">
					<h3>나들이 모임</h3>
					<p>우리 함께 나들이 가서 친해져 볼까?</p>
			</a></li>
		</ul>
	</section>

	<!-- Actions -->
	<section>
		<ul class="actions vertical">
			<li><a href="#" class="button big fit">Log In</a></li>
		</ul>
	</section>

</section>


<link rel="stylesheet" href="assets/css/main.css" />
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/skel.min.js"></script>
<script src="assets/js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="assets/js/main.js"></script>