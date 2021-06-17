<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
<title>내 티켓 보여주기</title>
<meta charset="utf-8">


	<meta name="viewport" content="width=device-width, initial-scale=1.0" />

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

	<!-- Optional theme -->
	<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous"> -->

	<!--[if lte IE 8]><script src="/resources/helios/assets/js/ie/html5shiv.js"></script><![endif]-->
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

	<!-- Latest compiled and minified JavaScript -->
	<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<!-- //////////////////// JavaScript //////////////////// -->
<script type="text/javascript">

$(function(){
	$( "a[href='#']:contains('확 인')" ).bind("click", function(){
//		alert("확인")
		self.location = "/index.jsp"
	});
});

	</script>
	<style>
	#nav{
		padding: 22px;
	}
	body{
		background-color: white;
	}
	</style>
</head>

<body class="no-sidebar">
		<div id="page-wrapper">

			<!-- Header -->
				<div id="header">
					
					<div class="inner">
						<header>
							<h1>나들이 티켓</h1>
						</header>
					</div>
					
					<jsp:include page="/layout/toolbar.jsp" />

				</div>
				
				<a href="#" class="image featured"><img src="#" alt=""></a>
		
				<article id="main" class="container special">
					<header>
						<h2>
						<c:if test="${ user.qrCode eq null }">
							티켓이 없습니다.
						</c:if>
						<c:if test="${ user.qrCode ne null }">
							<img src="../resources/qrcode/${ user.qrCode }" alt="">
						</c:if>
						</h2>
						<p>입장시 티켓을 보여주세요.</p>
					</header>
					<footer>
						<a href="#" class="button">확 인</a>
					</footer>
				</article>
				<br>	
				<br>	
				
				<jsp:include page="/layout/footer.jsp" />
		
		</div>
 	
</body>
</html>