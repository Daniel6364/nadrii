<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">

<head>
<title>장바구니 목록</title>
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
	$("form").attr("method", "POST").attr("action", "/ticket/listBasket").submit();
}



	//=================== "전체티켓결제" 버튼 Event 연결 =================== 
	$(function() {
//		$("button:contains('전체티켓결제')").bind("click", function() {
		$("a[href='#']:contains('전체티켓결제')").bind("click", function(event) {
			event.preventDefault();
//			alert("전체티켓결제 버튼")
			
			$("input[name='postNo']").prop("checked", true);

			var postCount = $("input[name='postNo']:checked").length;
			var sumPostNo = "";

			for (var i = 0; i < postCount; i++) {
				if (i != postCount - 1) {
					var postNo = $($("input[name='postNo']:checked")[i]).val() + ",";
				} else {
					var postNo = $($("input[name='postNo']:checked")[i]).val();
				}
				sumPostNo += postNo;
			}
//			alert(sumPostNo)
			$("input[name='sumPostNo']").val(sumPostNo);
		})
	});

	
	//=================== "전체선택취소" 버튼 Event 연결 =================== 
	$(function() {
//		$("button:contains('전체선택취소')").bind("click", function() {
		$("a[href='#']:contains('전체선택취소')").bind("click", function(event) {
			event.preventDefault();		
			$("input[name='postNo']").prop("checked", false);
		})
	});

	//=================== "선택티켓결제" 버튼 Event 연결 ===================

	$(function() {
//		$("button:contains('선택티켓결제')").bind("click", function() {
		$("a[href='#']:contains('선택티켓결제')").bind("click", function() {

					var postCount = $("input[name='postNo']:checked").length;
					var postNoCheck = $("input[name='postNo']:checked").val();
					
					if (postCount == 0) {
						alert("선택된 티켓이 없습니다.")
						return false;

					} else if (postCount != 0) {

						var sumPostNo = "";

						for (var i = 0; i < postCount; i++) {
							if (i != (postCount - 1)) {
								var postNo = $($("input[name='postNo']:checked")[i]).val()	+ ",";
							} else {
								var postNo = $($("input[name='postNo']:checked")[i]).val();
							}
							sumPostNo += postNo;
						}
						alert("선택하신 티켓 번호 확인 : " + sumPostNo)
						$("input[name='sumPostNo']").val(sumPostNo);
						$("span[name='ticketCount']").append(postCount);
					}
				})
	});

	//=================== "선택티켓삭제" 버튼 Event 연결 ===================
	$(function() {
//		$("button:contains('선택티켓삭제')").bind("click", function() {
		$("a[href='#']:contains('선택티켓삭제')").bind("click", function() {

			var postCount = $("input[name='postNo']:checked").length;
			if (postCount == 0) {
				alert("선택된 티켓이 없습니다.")
				return false;
			}

			var sumPostNo = "";

			for (var i = 0; i < postCount; i++) {
				if (i != postCount - 1) {
					var postNo = $($("input[name='postNo']:checked")[i]).val() + ",";
				} else {
					var postNo = $($("input[name='postNo']:checked")[i]).val();
				}
				sumPostNo += postNo;
			}
//			alert(sumPostNo)
			$("input[name='sumPostNo']").val(sumPostNo);
			$("span[name='ticketCount']").append(postCount);
		})
	});

	//=================== "장바구니에서 삭제" 버튼 Event 연결 ===================
	$(function() {
		$("button:contains('장바구니에서 삭제')").bind("click", function() {
//			alert("장바구니에서 삭제")
			fncDeleteBasketList();
		})
	});

	//=================== "결제예매정보 페이지로 이동" 버튼 Event 연결 ===================
	$(function() {
		$("button:contains('결제예매정보 페이지로 이동')").bind("click", function() {
//			alert("결제예매정보 페이지로 이동")
			fncUpdateBasketPurchase();
		})
	});

	//=================== "선택티켓삭제 취소" 버튼 Event 연결 ===================
	$(function() {
		$("#selectedTicketDelete button:contains('취 소')").bind("click",
				function() {
//					alert("선택티켓삭제 취소")
					$("span[name='ticketCount']").empty();
				})
	});

	//=================== "선택티켓결제 취소" 버튼 Event 연결 ===================
	$(function() {
		$("#selectedTicketPayment button:contains('취 소')").bind("click",
				function() {
//					alert("선택티켓결제 취소")
					$("span[name='ticketCount']").empty();
				})
	});

	//=================== "전체선택티켓 취소" 버튼 Event 연결 ===================
	$(function() {
		$("#allTicketPayment button:contains('취 소')").bind("click", function() {
//			alert("전체선택티켓 취소")
			$("input[name='postNo']").prop("checked", false);
		})
	});

	//=================== "fncAddBasketPurchase()" 버튼 Event 연결 ===================	
	function fncUpdateBasketPurchase() {
//		alert("fncUpdateBasketPurchase()")
		$("form").attr("method", "POST").attr("action",	"/purchase/updateBasketPurchase/").submit();
	}

	//=================== "fncDelBasketList()" 버튼 Event 연결 ===================	
	function fncDeleteBasketList() {
//		alert("fncDeleteBasketList()")
		
//		$("form").attr("method", "POST").attr("action",	"/purchase/deleteBasketList/").submit();
		
		// ajax 로 구현할것

		var basketData = {
				"sumPostNo" : $("input[name='sumPostNo']").val()
		}
		
		var jsonData = JSON.stringify(basketData);
		
		$.ajax (
				{
					url : "/purchase/json/deleteBasketList",
					method : "POST",
					dataType : "json",
					headers : {
						"Accept" : "application/json",
						"Content-Type" : "application/json"
					},
					data:jsonData,
					success : function() {
			//			alert("들어온거 확인");
					}
				})
		alert("선택하신 티켓이 장바구니에서 삭제 되었습니다.");
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
								<h2>장바구니 목록</h2>
								<p>
									<strong class="text-success">
									<c:if test="${ user.userName eq null }">
										${ user.userId }
									</c:if>
										${ user.userName }
									</strong> 회원님의  <strong class="text-danger">장바구니</strong> 목록 입니다.
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

							<!-- selectBox -->
								<section class="4u 12u(mobile)">
									<ul class="divided">
										<li>
											<article class="post stub">
												<header>
													<label class="button">
														<input type="checkbox" name="postNo" value="${ list.postNo }"> 
															선택하기
													</label>
												</header>
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
								<article id="main" class="special">
									<header>
										<h2><a href="/ticket/listTicket" class="text-danger" data-toggle="tooltip" data-placement="bottom" title="클릭시 나들이 티켓 목록으로 이동">장바구니에 등록된 티켓이 없습니다.</a></h2>
									</header>
								</article>	
							</c:if>
							<c:if test="${ !empty list }">	
								<article id="main" class="container special">
									<footer>
										<a href="#" class="button">전체선택취소</a>&nbsp;
										<a href="#" class="button" data-toggle="modal" data-target="#allTicketPayment">전체티켓결제</a>&nbsp;
										<a href="#" class="button" data-toggle="modal" data-target="#selectedTicketPayment">선택티켓결제</a>&nbsp;
										<a href="#" class="button" data-toggle="modal" data-target="#selectedTicketDelete">선택티켓삭제</a>
									</footer>
								</article>
							</c:if>
							
							
					</div><!-- container end -->
					
					
					
					<hr/>	
					<c:if test="${ !empty list }">
					<jsp:include page="../common/pageNavigator_openApi.jsp"/>
					</c:if>
				</div><!-- wrapper style1 End -->
				
				<!-- '전체티켓결제' Modal -->
			<div class="modal fade" id="allTicketPayment" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">전체결제</h4>
						</div>
						<div class="modal-body">
							<h3 class="text-center">장바구니에 있는 모든 티켓을</h3> 
							<h3 class="text-center text-danger">결제 하시겠습니까?</h3>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">
								취 소
							</button>
							<button type="button" class="btn btn-danger">
								결제예매정보 페이지로 이동
							</button>
						</div>
					</div>
				</div>
			</div>

			<!-- '선택티켓결제' Modal --> 
			<div class="modal fade" id="selectedTicketPayment" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">선택결제</h4>
						</div>
						<div class="modal-body">
							<h3 class="text-center">선택하신 <span class="label label-warning" name='ticketCount'></span> 장의 티켓을</h3> 
							<h3 class="text-center text-danger">결제 하시겠습니까?</h3>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">
								취 소
							</button>
							<button type="button" class="btn btn-danger">
								결제예매정보 페이지로 이동
							</button>
						</div>
					</div>
				</div>
			</div>
			
			<!-- '선택티켓삭제' Modal --> 
			<div class="modal fade" id="selectedTicketDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">선택티켓삭제</h4>
						</div>
						<div class="modal-body">
							<h3 class="text-center">선택하신 <span class="label label-warning" name='ticketCount'></span> 장의 티켓을</h3> 
							<h3 class="text-center text-danger">삭제 하시겠습니까?</h3>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">
								취 소
							</button>
							<button type="button" class="btn btn-danger">
								장바구니에서 삭제
							</button>
						</div>
					</div>
				</div>
			</div>
				
				
				
				
</form>			

		<jsp:include page="/layout/footer.jsp" />
							
		</div><!-- page-wrapper End -->	
	</body>



</html>