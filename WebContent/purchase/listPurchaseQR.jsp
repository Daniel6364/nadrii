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
		$("a[href='#']:contains('담당자 확인 후 클릭')").bind("click", function(event) {
			event.preventDefault();
//			alert( $( $("input[name='postNo']")[ $("a[href='#']:contains('담당자 확인')").index(this) ] ).val() )
			fncUpdatePurchaseList($( $("input[name='postNo']")[ $("a[href='#']:contains('담당자 확인')").index(this) ] ).val());
		})
	});

	function fncUpdatePurchaseList(postNo) {
		
//		alert("fncUpdatePurchaseList")
		
		$.ajax (
				{
					url : "/purchase/json/updatePurchaseList",
					method : "POST",
					dataType : "String",
					headers : {
						"Accept" : "application/json",
						"Content-Type" : "application/json"
					},
					data:postNo,
					success : function() {
			//			alert("들어온거 확인");
					}
				})
		alert("입장권 티켓확인이 완료 되었습니다.")
		location.reload();
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

<form action="navigation">

			<!-- Main -->
				<div class="wrapper style1">

					<div class="container">
						
						<article id="main" class="special">
							<header>
								<h2><a href="#">나들이 QR코드 <br>티켓 확인 시스템</a></h2>
								<p>
									구매자 아이디 : <strong class="text-danger">${ list.userId }</strong>
								</p>
								<p>
									구매자 이 름 : <strong class="text-danger">${ list.userName }</strong>
								</p>
							</header>
							
						</article>
					
<!-- ///////////////////////////////////////////////////////////////////////////////////// -->

						<c:forEach var="list" items="${list}" varStatus="num">					
						<div class="row">

						<!-- Posts -->
						<section class="4u 12u(mobile)">
							<header>
								<h3>No.${ num.count } : ${ list.ticketTitle }</h3>
							</header>
							
							<ul class="divided">
							
								<li>
									<article class="post stub">
										<header>
											[예매일자]
											<h3><a href="#">${ list.bookingDate }</a></h3>
											[티켓확인]<br>
											<c:forEach var="ticketInfo" items="${list.ticketP}" varStatus="num">
												<c:if test="${ list.ticketC[num.index] ne 0 }">
													￦ ${ ticketInfo } = ${ list.ticketC[num.index] }ea<br>
												</c:if>
											</c:forEach>	
										</header>
									</article>
								</li>
							
							</ul>
							
						<a href="#" class="button">담당자 확인 후 클릭</a>
						<input type="hidden" name="postNo" value="${ list.postNo }">
						</section>
						</div>
						
						</c:forEach>
						
<!-- ///////////////////////////////////////////////////////////////////////////////////// -->		

							<input type="hidden" name="sumPostNo">
							<!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
							<input type="hidden" id="pageNo" name="pageNo" value=""/>
						
						<c:if test="${ empty list }">
							<article id="main" class="container special">
								<footer>
									<h2><a href="#" class="text-danger" data-toggle="tooltip" data-placement="bottom" title="클릭시 나들이 티켓 목록으로 이동">등록된 티켓이 없습니다.</a></h2>
								</footer>
							</article>
						</c:if>
						
					</div><!-- container end -->
					
					<hr/>	
					<jsp:include page="../common/pageNavigator_openApi.jsp"/>
					
				</div><!-- wrapper style1 End -->

	</form>

			<jsp:include page="../layout/footer.jsp" />
			
		
	</div>


</body>

</html>