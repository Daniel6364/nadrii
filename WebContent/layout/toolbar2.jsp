<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>나들이</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<<<<<<< HEAD
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>


<!-- css -->
	<link href="/resources/css/bootstrap.min.css" rel="stylesheet" />
	<link href="/resources/css/jcarousel.css" rel="stylesheet" />
	<link href="/resources/css/flexslider.css" rel="stylesheet" />
	<link href="/resources/css/style.css" rel="stylesheet" />
	<!-- Theme skin -->
	
	<link href="/resources/skins/default.css" rel="stylesheet" />

	<!-- //////////////////// CSS //////////////////// -->

	<style type="text/css">

		.container {
			padding: 20px;
        }

	</style>

	<script type="text/javascript">
	
	//========== '나들이 정보' 버튼 처리 Event ==========
	$( function() {
		$("a[href='#' ]:contains('나들이 정보')").on("click" , function() {
			self.location = "../trip/getTheme"
		});
	});

	$( function() {
		console.log("세션 정보 : ${session}")
		
		$("a[href='#' ]:contains('나들이 티켓')").bind("click" , function() {
			self.location = "/ticket/listTicket"
		});

	});

	$( function() {
        $(".glyphicon.glyphicon-envelope").bind("click" , function() {
        	//alert("쪽지함")
      		self.location = "/message/listMessage"
        });
    });
	
	//============= "장바구니" 화면이동 =============
    $( function() {
       $(".glyphicon.glyphicon-shopping-cart").bind("click" , function() {

//      	 alert("장바구니")
          self.location = "/purchase/listBasket"
       });
    });
	
	
	
	
	</script>
	
</head>


<body>
		<nav class="navbar navbar-default navbar-static-top">
			<div class="container">
				<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
							data-target="#bs-example-navbar-collapse-1" >
	                        <span class="icon-bar"></span>
	                        <span class="icon-bar"></span>
	                        <span class="icon-bar"></span>
	                   	</button>
					<a class="navbar-brand" href="/index.jsp"><span>Na</span>drii</a>
				</div>
				<!-- navbar-header End -->

				
				
				<div class="navbar-collapse collapse ">

					<ul class="nav navbar-nav navbar-right">
					<c:if test="${ ! empty loginUser }">
							<li>
								<a href="#">
								<span>
									<c:if test="${ loginUser.userName eq null }">
										${ loginUser.userId }
									</c:if>
										${ loginUser.userName }
										님 환영합니다.
								</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
								</a>
							</li>
							<li><a href="/user/getUser?userId=${loginUser.userId}">MyPage</a></li>
							<li><a href="/user/logoutProc">Logout</a></li>
						</c:if>
						<c:if test="${  empty loginUser }">
							<li><a href="/user/loginView.jsp">Login</a></li>
							<li><a href="/user/addUserView.jsp">Join</a></li>
						</c:if>
					</ul>
				</div>
			
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="../index.jsp">Home</a></li>
						<li><a href="#">나들이 정보</a></li>
						<li><a href="/comm/listComm">나만의 나들이</a></li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false">나들이 플래너 
								<b class=" icon-angle-down"></b>
							</a>
							<ul class="dropdown-menu">

								<li><a href="/planner/getUserPlannerList">플래너 게시판</a></li>
								<li><a href="/planner/addPlannerView">플래너 작성</a></li>
								<li><a href="/planner/getMyPlannerList">나의 플래너</a></li>
							</ul>
						</li>
						<li><a href="#">나들이 티켓</a></li>
						<li><a href="/group/listGroup">나들이모임</a></li>
					</ul>
				</div>
			<!-- Container End -->
		</nav>
		<!-- navbar End -->
</body>
=======
<!-- Latest compiled and minified JavaScript -->	
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

	<!-- css -->
	<link href="/resources/css/bootstrap.min.css" rel="stylesheet" />
	<link href="/resources/css/jcarousel.css" rel="stylesheet" />
	<link href="/resources/css/flexslider.css" rel="stylesheet" />
	<link href="/resources/css/style.css" rel="stylesheet" />
	<!-- Theme skin -->
	<link href="/resources/skins/default.css" rel="stylesheet" />
	
	
	
	
	<style type="text/css">
		.container {
			padding: 20px;
        }
	</style>

	<script type="text/javascript">
	
	//========== '나들이 정보' 버튼 처리 Event ==========
	$( function() {
		$("a[href='#' ]:contains('나들이 정보')").on("click" , function() {
			self.location = "../trip/getTheme"
		});
	});

	$( function() {

		console.log("세션 정보 : ${session}")
		
		$("a[href='#' ]:contains('나들이 티켓')").bind("click" , function() {
			self.location = "/ticket/listTicket"
		});

	});


	$( function() {

        $(".glyphicon.glyphicon-envelope").bind("click" , function() {
        	//alert("쪽지함")
      		self.location = "/message/listMessage"
        });
    });
	

	//============= "장바구니" 화면이동 =============
    $( function() {
       $("a[href='#']:contains('Logout')").bind("click" , function() {
			alert("나갈거임");
	        self.location = "/user/logout";
       });
    });
	
	</script>
	
</head>



<body>
		<nav class="navbar navbar-default navbar-static-top">
			<div class="container">
				<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
							data-target="#bs-example-navbar-collapse-1" >
	                        <span class="icon-bar"></span>
	                        <span class="icon-bar"></span>
	                        <span class="icon-bar"></span>
	                   	</button>
					<a class="navbar-brand" href="/index.jsp"><span>Na</span>drii</a>
				</div>
				<!-- navbar-header End -->

				
				
				<div class="navbar-collapse collapse ">

					<ul class="nav navbar-nav navbar-right">
					<c:if test="${ ! empty loginUser }">
							<li>
								<a href="/user/getUser?userId=${loginUser.userId}">
								<img src="/resources/images/00742106_105752.jpg" alt="..." class="img-circle" width="30px" height="30px">
								<span>
									<c:if test="${ loginUser.userName eq null }">
										${ loginUser.userId }
									</c:if>
										${ loginUser.userName }
										님
								</span>
								</a>
							</li>
							<li>
								<a href="/purchase/listBasket">
									<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true" 
										data-toggle="tooltip" data-placement="bottom" title="장바구니"></span>
								</a>
							</li>
							<li>
								<a href="/purchase/listPurchase">
									<span class="glyphicon glyphicon-qrcode" aria-hidden="true" 
										data-toggle="tooltip" data-placement="bottom" title="구매한티켓"></span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="glyphicon glyphicon-envelope" aria-hidden="true"
										data-toggle="tooltip" data-placement="bottom" title="쪽지"></span>
								</a>
							</li>
							<li><a href=""><span class="glyphicon glyphicon-bell"></span></a></li>
							<li><a href="/user/logoutProc">Logout</a></li>
						</c:if>
						<c:if test="${  empty loginUser }">
							<li><a href="/user/loginView.jsp" >Login</a></li>
							<li><a href="/user/addUserView.jsp">Join</a></li>
						</c:if>
					</ul>
				</div>
			
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="../index.jsp">Home</a></li>
						<li><a href="#">나들이 정보</a></li>
						<li><a href="/comm/listComm">나만의 나들이</a></li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false">나들이 플래너 
								<b class=" icon-angle-down"></b>
							</a>
							<ul class="dropdown-menu">

								<li><a href="/planner/getUserPlannerList">플래너 게시판</a></li>
								<li><a href="/planner/addPlannerView">플래너 작성</a></li>
								<li><a href="/planner/getMyPlannerList">나의 플래너</a></li>
							</ul>
						</li>
						<li><a href="/ticket/listTicket">나들이 티켓</a></li>
						<li><a href="/group/listGroup">나들이모임</a></li>
					</ul>
				</div>
			<!-- Container End -->
		</nav>
		<!-- navbar End -->
</body>

>>>>>>> refs/remotes/origin/master
</html>