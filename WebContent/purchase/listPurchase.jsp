<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">

<head>
<title>구매한 티켓 목록</title>
<meta charset="UTF-8">

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
<!--  ///////////////////////// JavaScript ////////////////////////// -->
<script type="text/javascript">

function fncGetList(pageNo) {
	$("#pageNo").val(pageNo);
	$("form").attr("method", "POST").attr("action", "/purchase/listPurchase").submit();
}



	//=================== "메인으로" 버튼 Event 연결 =================== 
	$(function() {
		$("a[href='#']:contains('메인으로')").bind("click", function() {
			self.location = "/index.jsp"
		})
	});

	
	
</script>

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

<form action="navigation">
			<!-- Main -->
				<div class="wrapper style1">

					<div class="container">
						
						<article id="main" class="special">
							<header>
								<h2><a href="#">구매한 티켓 목록</a></h2>
								<p>
									<strong class="text-success">
									<c:if test="${ user.userName eq null }">
										${ user.userId }
									</c:if>
										${ user.userName }
									</strong> 회원님의  <strong class="text-danger">구매하신 티켓</strong> 목록 입니다.
								</p>
							</header>
							
						</article>
					
					</div>


					<div class="container">
					
						<c:forEach var="list" items="${list}" varStatus="num">
						<span class="timestamp">No.${ num.count } ${ list.postNo }</span>
						<div class="row">

							<!-- Photos -->
								<section class="4u 12u(mobile)">
									<div class="row 25% no-collapse">
										<div class="12u">
											<a href="#" class="image fit"><img src="${ list.ticketImage }" alt="" /></a>
										</div>
									</div>
								</section>	
							
							<!-- content -->
								<section class="4u 12u(mobile)">
									<ul class="divided">
										<li>
											<article class="tweet">
												<h3><a href="#">${ list.ticketTitle }</a></h3>
												<span class="timestamp">&nbsp;</span>
												<h4>예매일자</h4>
												<p>&nbsp;&nbsp;${ list.bookingDate }</p>
												<h4>취소 가능일자</h4>
												<p>&nbsp;&nbsp;${ list.cancelDate } 까지</p>
												<p>
													<c:forEach var="ticketInfo" items="${list.ticketP}" varStatus="num">
														￦ ${ ticketInfo } = ${ list.ticketC[num.index] } 장
													</c:forEach>
												</p>
											</article>
										</li>
									</ul>
								</section>

						</div><!-- row end -->
						<br>
						</c:forEach>
					
							<input type="hidden" name="sumPostNo">
							<!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
							<input type="hidden" id="pageNo" name="pageNo" value=""/>
						
						<c:if test="${ empty list }">
							<article id="main" class="container special">
								<footer>
									<h2><a href="/ticket/listTicket" class="text-danger" data-toggle="tooltip" data-placement="bottom" title="클릭시 나들이 티켓 목록으로 이동">구매한 티켓 목록에 등록된 티켓이 없습니다.</a></h2>
								</footer>
							</article>
						</c:if>
						<c:if test="${ !empty list }">	
							<article id="main" class="container special">
								<footer>
									<a href="#" class="button">메인으로</a>
								</footer>
							</article>
						</c:if>
					</div><!-- container end -->
					
					<hr/>	
					<c:if test="${ !empty list }">	
						<jsp:include page="../common/pageNavigator_openApi.jsp"/>
					</c:if>
				</div><!-- wrapper style1 End -->

	</form>

			<jsp:include page="/layout/footer.jsp" />
			
		
	</div>


</body>

</html>