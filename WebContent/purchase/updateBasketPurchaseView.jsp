<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
<title>결제정보입력</title>
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

<!--  ///////////////////////// JavaScript ////////////////////////// -->
<script type="text/javascript">

function fncUpdateBasketPurchase() {
	
	var myForm = document.popForm;
	var url = "/purchase/kakaoPay";
	window.open("",	"popForm", "toolbar=no, width=475, height=700, directories=no, status=no, scrollorbars=no, resizable=no");
	myForm.action = url;
	myForm.method = "post";
	myForm.target = "popForm";
	myForm.testVal = "${ kakaoUri }";
	myForm.submit();
	
//	$("form").attr("method", "POST").attr('action', '/purchase/kakaoPay').submit();
}

$( function(){
	$("a[name='kakaoPay']").bind("click", function(event){
		event.preventDefault();
		fncUpdateBasketPurchase(); 
	})
});

//=================== "취소" Event 연결 =================== 
function goBack() {
	window.history.back();
}


</script>
</head>

<body class="no-sidebar">
		<div id="page-wrapper">

			<!-- Header -->
				<div id="header">

					<div class="inner">
						<header>
							<h1><a href="/index.jsp" id="logo">N A D R I I</a></h1>
						</header>
					</div>
					
					<jsp:include page="/layout/toolbar.jsp" />

				</div>

<form action="navigation" name="popForm">

		<input type="hidden" name="sumPostNo" value="${ purchase.sumPostNo }">
		
		
		<!-- KakaoPay API Request -->
		<input type="hidden" name="cid" 				value="TC0ONETIME">
		<input type="hidden" name="partner_order_id" 	value="㈜나들이">
		<input type="hidden" name="partner_user_id" 	value="${ list[0].buyer.userId }">
		<input type="hidden" name="item_name" 			value="${ purchase.ticketTitle }">
		<input type="hidden" name="quantity" 			value="1">
		<input type="hidden" name="total_amount" 		value=${ purchase.totalTicketPrice }>
		<input type="hidden" name="tax_free_amount" 	value="${ purchase.taxFree }">
		<input type="hidden" name="approval_url" 		value="http://192.168.0.39:8080/purchase/kakaoPayCompleteB">
		<input type="hidden" name="cancel_url" 			value="http://192.168.0.39:8080/purchase/cancelPayment.jsp">
		<input type="hidden" name="fail_url" 			value="http://192.168.0.39:8080/purchase/failPayment.jsp">

	
	
			<!-- Main -->
				<div class="wrapper style1">

					<div class="container">
						<article id="main" class="special">
							<header>
								<h2><a href="#">결제정보입력</a></h2>
								<p>
									선택하신 티켓의 결제 진행을 위해 <strong class="text-danger">형식에 맞게 </strong>작성해 주세요.
								</p>
							</header>
						</article>
					</div><!-- container end -->

				</div><!-- wrapper style1 end -->

			<!-- Footer -->
				<div id="footer">
					<div class="container">
						<div class="row">

							<!-- Tweets -->
								<section class="4u 12u(mobile)">
									<header>
										<h2 class="icon fa-ticket circled"><span class="label">Tweets</span></h2>
									</header>
									<c:forEach var="list" items="${list}" varStatus="num">
									<ul class="divided">
										<li>
										<ul class="icons">
											<li>[${ num.count }] ${ list.postNo }</li>
										</ul>
											<article class="post stub">
												
												<span class="timestamp">티켓명</span>
												<header>
													<h3><a href="#">${ list.ticketTitle }</a></h3>
												</header>
												<span class="timestamp">예매일</span>
												<header>
													<h3><a href="#">${ list.bookingDate }</a></h3>
												</header>
											</article>
										</li>
										<li></li>
									</ul>
									</c:forEach>
								</section>

							<!-- Posts -->
								<section class="4u 12u(mobile)">
									<header>
										<h2 class="icon fa-user circled"><span class="label">Posts</span></h2>
									</header>
									<ul class="divided">
										<li>
											<article class="post stub">
												<span class="timestamp">아이디</span>
												<header>
													<h3><a href="#">${ list[0].buyer.userId }</a></h3>
													<input type="hidden" name="buyerId" value="${ list[0].buyer.userId }">
												</header>
											</article>
										</li>
										<li>
											<article class="post stub">
												<span class="timestamp">이 름</span>
												<header>
													<h3><a href="#">${ list[0].buyer.userName }</a></h3>
													<input type="hidden" name="buyerName" value="${ list[0].buyer.userName }">
												</header>
											</article>
										</li>
										<li>
											<article class="post stub">
												<span class="timestamp">연락처</span>
												<header>
													<h3><a href="#">${ list[0].buyer.phone }</a></h3>
													<input type="hidden" name="buyerPhone" value="${ list[0].buyer.phone }">
												</header>
											</article>
										</li>
										<li>
											<article class="post stub">
												<span class="timestamp">E-mail</span>
												<header>
													<h3><a href="#">${ list[0].buyer.email }</a></h3>
													<input type="hidden" name="buyerEmail" value="${ list[0].buyer.email }">
												</header>
											</article>
										</li>
									</ul>
								</section>

							<!-- Photos -->
								<section class="4u 12u(mobile)">
									<header>
										<h2 class="icon fa-credit-card circled"><span class="label">Photos</span></h2>
									</header>
									<ul class="divided">
										<li>
											<article class="post stub">
											<c:forEach var="list" items="${list}" varStatus="num">
												<span class="timestamp">선택한 티켓 : ${ list.ticketTitle }</span>
												<header>
													<h3><a href="#">
													<c:forEach var="ticket" items="${list.ticketP}" varStatus="num">
														<c:if test="${ list.ticketC[num.index] ne '0' }">
															￦ ${ ticket } : ${ list.ticketC[num.index] } 장
														</c:if>
													</c:forEach>
													</a></h3>
												</header>
											</c:forEach>
											</article>
										</li>
										<li>
											<article class="post stub">
												<header>
													<span class="timestamp">티켓금액</span>
													<h3><a href="#">￦ ${ purchase.totalTicketPrice }</a></h3>
													<h3>+</h3>
													<span class="timestamp">구매대행 금액</span>
													<h3><a href="#">￦ ${ purchase.taxFree }</a></h3>
												</header>
											</article>
										</li>
										<li>
											<article class="post stub">
												<header>
													<span class="timestamp">총 결제 금액</span>
													<h3><a href="#">￦ ${ purchase.ticketPayment }</a></h3>
												</header>
											</article>
										</li>
									</ul>
								</section>

						</div><!-- row end -->
						
						<hr />
						
						<article id="main" class="container special">
							<footer>
								<a href="#" class="button" onclick="goBack()">취 소</a>&nbsp;
								<a href="#" class="button icon fa-credit-card" name="kakaoPay"> KakaoPay 결제</a>
							</footer>
						</article>
						
						<hr>
						
						<jsp:include page="/layout/footer.jsp" />
						
					</div>
				</div>
</form>
		</div>

</body>


</html>