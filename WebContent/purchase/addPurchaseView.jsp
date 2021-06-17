<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
<title>결제정보입력</title>
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


	function fncAddPurchase() {

		var myForm = document.popForm;
		var url = "/purchase/kakaoPay";
		window.open("",	"popForm", "toolbar=no, width=475, height=700, directories=no, status=no, scrollorbars=no, resizable=no");
		myForm.action = url;
		myForm.method = "post";
		myForm.target = "popForm";
		myForm.testVal = "${ kakaoUri }";
		myForm.submit();
		
		
		/*
		 var openDialog = function (uri, name, options, closeCallback) {
			    var win = window.open(uri, "", options);
			    var interval = window.setInterval(function() {
			        try {
			            if (win == null || win.closed) {
			            	
			                window.clearInterval(interval);
			                closeCallback(win);
			            }
			        }
			        catch (e) {
			        }
			    }, 1000);
			    return win;
	        }
		*/
		
		//	$("form").attr("method", "POST").attr('action', '/purchase/kakaoPay').submit();

	}

	$(function() {
		$("a[name='kakaoPay']").bind("click", function(event) {
			event.preventDefault();
			fncAddPurchase();
		})
	});

	//=================== "취소" Event 연결 =================== 
	function goBack() {
		history.go(-1);
	}
</script>
</head>


<body class="no-sidebar">
		<div id="page-wrapper">

			<!-- Header -->
				<div id="header">

					<div class="inner">
						<header>
							<h1></h1>
						</header>
					</div>
					
					<jsp:include page="/layout/toolbar.jsp" />

				</div>

<form action="navigation" name="popForm">

		<!-- Purchase Info -->
		<input type="hidden" name="contentId" 			value="${ purchase.contentId }">
		<input type="hidden" name="contentTypeId" 		value="${ purchase.contentTypeId }">
		<input type="hidden" name="ticketTitle"			value="${ purchase.ticketTitle }">
		<input type="hidden" name="ticketImage" 		value="${ purchase.ticketImage }">
		<input type="hidden" name="bookingDate" 		value="${ purchase.bookingDate }">
		<input type="hidden" name="flag" 				value="${ purchase.flag }">
		<input type="hidden" name="totalTicketPrice" 	value="${ purchase.totalTicketPrice }">
		<input type="hidden" name="taxFree"				value="${ purchase.taxFree }">
		<input type="hidden" name="ticketPayment" 		value="${ purchase.ticketPayment }">
		<c:forEach items="${ purchase.ticketPrice }" varStatus="status">
			<input type="hidden" name="ticketCount" value="${ purchase.ticketCount[status.index] }">
			<input type="hidden" name="ticketPrice" value="${ purchase.ticketPrice[status.index] }">
		</c:forEach>
		
		
		<!-- KakaoPay API Request -->
		<input type="hidden" name="cid" 				value="TC0ONETIME">
		<input type="hidden" name="partner_order_id" 	value="㈜나들이">
		<input type="hidden" name="partner_user_id" 	value="${ user.userId }">
		<input type="hidden" name="item_name" 			value="${ purchase.ticketTitle }">
		<input type="hidden" name="quantity" 			value="1">
		<input type="hidden" name="total_amount" 		value=${ purchase.totalTicketPrice }>
		<input type="hidden" name="tax_free_amount" 	value="${ purchase.taxFree }">
		<input type="hidden" name="approval_url" 		value="http://192.168.0.39:8080/purchase/kakaoPayComplete">
		<input type="hidden" name="cancel_url" 			value="http://192.168.0.39:8080/purchase/cancelPayment.jsp">
		<input type="hidden" name="fail_url" 			value="http://192.168.0.39:8080/purchase/failPayment.jsp">
	
	
	
			<!-- Main -->
				<div class="wrapper style1">

					<div class="container">
						<article id="main" class="special">
							<header>
								<h2>결제정보확인</h2>
								<p>
									결제를 진행하시려면 <br><strong class="text-danger">KakaoPay 결제</strong> 버튼을 눌러 주세요.
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
									<ul class="divided">
										<li>
											<article class="post stub">
												<span class="timestamp">티켓명</span>
												<header>
													<h3>${ purchase.ticketTitle }</h3>
												</header>
											</article>
										</li>
										<li>
											<article class="post stub">
												<span class="timestamp">예매일</span>
												<header>
													<h3>${ purchase.bookingDate }</h3>
												</header>
											</article>
										</li>
									</ul>
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
													<h3>${ purchase.buyerId }</h3>
													<input type="hidden" name="buyerId" value="${ purchase.buyerId }">
												</header>
											</article>
										</li>
										<li>
											<article class="post stub">
												<span class="timestamp">이 름</span>
												<header>
													<h3>${ purchase.buyerName }</h3>
													<input type="hidden" name="buyerName" value="${ purchase.buyerName }">
												</header>
											</article>
										</li>
										<li>
											<article class="post stub">
												<span class="timestamp">연락처</span>
												<header>
													<h3>${ purchase.buyerPhone }</h3>
													<input type="hidden" name="buyerPhone" value="${ purchase.buyerPhone }">
												</header>
											</article>
										</li>
										<li>
											<article class="post stub">
												<span class="timestamp">E-mail</span>
												<header>
													<h3>${ purchase.buyerEmail }</h3>
													<input type="hidden" name="buyerEmail" value="${ purchase.buyerEmail }">
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
												<span class="timestamp">선택한 티켓</span>
												<header>
													<h3>
													<c:forEach items="${ purchase.ticketCount }" varStatus="status">
														<c:if test="${ purchase.ticketCount[status.index] ne '0' }">
															￦ ${ purchase.ticketPrice[status.index] } : ${ purchase.ticketCount[status.index] } 장
														</c:if>
													</c:forEach>
													</h3>
												</header>
											</article>
										</li>
										<li>
											<article class="post stub">
												<header>
													<span class="timestamp">티켓금액</span>
													<h3>￦ ${ purchase.totalTicketPrice }</h3>
													<h3>+</h3>
													<span class="timestamp">구매대행 금액</span>
													<h3>￦ ${ purchase.taxFree }</h3>
												</header>
											</article>
										</li>
										<li>
											<article class="post stub">
												<header>
													<span class="timestamp">총 결제 금액</span>
													<h3>￦ ${ purchase.ticketPayment }</h3>
												</header>
											</article>
										</li>
									</ul>
								</section>

						</div><!-- row end -->
						
						<hr />
						
						<article id="main" class="container special">
							<footer>
								<a href="#" class="button 3u" onclick="goBack()">취 소</a>&nbsp;
								<a href="#" class="button icon fa-credit-card 3u" name="kakaoPay"> KakaoPay 결제</a>
							</footer>
						</article>
						
						<hr />
						
						<jsp:include page="/layout/footer.jsp" />
						
						
						
					</div>
				</div>
</form>
		</div>

</body>



</html>