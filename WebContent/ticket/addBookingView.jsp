<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="ko">

<head>
<meta charset="UTF-8">

<title>예매정보입력</title>

	<!-- 참조 : http://getbootstrap.com/css/   참조 -->
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
	<script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<style>
	input:focus {
		outline: none;
	}
	
	.wrapper {
		margin: 0;
	}

	.toastMessage {
    	width:400px;
    	height:auto;
    	position:fixed;
    	left:50%;
	    margin-left:-200px;
	    bottom:15px;
	    background-color: #000000;
	    color: #F0F0F0;
	    font-size: 18px;
	    padding:12px;
	    text-align:center;
	    border-radius: 2px;
	    -webkit-box-shadow: 0px 0px 24px -1px rgba(56, 56, 56, 1);
	    -moz-box-shadow: 0px 0px 24px -1px rgba(56, 56, 56, 1);
	    box-shadow: 0px 0px 24px -1px rgba(56, 56, 56, 1);
	    z-index: 100;
	}
	a:hover{
		text-decoration: none;
	}
	#nav{
		padding: 22px 0;
	}
	@media screen and (max-width: 736px){
		.row > section, .row > article {
			margin: 0;
		}	
		div #sidebar{
			margin-top: -50px;
		}
	}

</style>




<!-- //////////////////// JavaScript //////////////////// -->
<script type="text/javascript">

	function makeToast(title) {
		$('#toastMessage').text(title).fadeIn(400).delay(1500).fadeOut(400);
	}
	
	var totalTicketCount = "";
	var ticketPrice = "";
	var ticketCount = "";
	var totalTicketPrice = "";

	//=================== "장바구니 담기" Event 연결 =================== 
	$(function() {
		$("a[href='#']:contains('장바구니 담기')").bind('click', function() {
	//		$(".modal-body .text-center.title").empty();
		//	$(".modal-body h3:contains('- 선택한 티켓매수')").empty();
			//$(".modal-body h3:contains('- 총 결제요금 : ￦ ')").empty();
			fncBasketList();
		})
	});

	function fncBasketList() {
		
		totalTicketPrice = 0;
		
		
		$(".modal-body .text-center.title").append('${ tourTicket.title }');
		
		for (var i = 0; i < $(".ticketPrice").length; i++) {
			ticketPrice = $(".ticketPrice").eq(i).val();
			ticketCount = $(".ticketCount").eq(i).val();
			
			totalTicketCount += ticketCount * 1;

			if (ticketCount != 0) {
				var sumPriceTicket = (ticketPrice * 1) * ticketCount;
				totalTicketPrice = (totalTicketPrice * 1)
						+ (sumPriceTicket * 1);
				$(".modal-body h3:contains('- 선택한 티켓매수')").append( 
						"<h4>&nbsp;&nbsp;&nbsp;&nbsp;￦ "
						+ ticketPrice 
						+ " = "
						+ "<strong class='text-danger'>"
						+ ticketCount
						+ " 매</strong></h4>"
				);
			}
		}
		$(".modal-body h3:contains('- 총 결제요금 : ￦ ')").append(
				"<strong class='text-danger' name='totalTicketCount'>"
				+ totalTicketPrice
				+ "</strong>"
		);
		
		
	}
	
</script>
<script>
	
	//=================== "장바구니 내 저장하기" Event 연결 =================== 
	$(function() {
		$("button:contains('저장하기')").bind("click", function() {
			var flag = 'basket';
			fncAddPurchase(flag);
//			alert("저장하기 완료")
			return;
		});
	});

	
	//=================== "취소" Event 연결 =================== 
	function goBack() {
    	window.history.back();
	}
	
	
	//=================== "결제하기" Event 연결 =================== 
	$(function() {
		$("a[href='#']:contains('결제하기')").bind("click", function() {
//			alert("결제하기")
			var flag = 'purchase';
			fncAddPurchase(flag);
		});
	});
	
	//==================== "수량증감" Event 연결 =================== 
	
	function fncTicketCount(indexVal, currentVal, countVal) {

//		console.log("[fncTicketCount START]")

		var inputVal = $(".ticketCount").eq(indexVal).val();
		var textVal = $("h3[name='priceCount']").eq(indexVal).text();
/*
		console.log("[1] inputVal : " + inputVal)
		console.log("[2] textVal : " + textVal)
//*/
		if (countVal == 'plus') {
//			console.log("[Plus]")
			inputVal = $(".ticketCount").eq(indexVal).val(currentVal * 1 + 1);
			textVal = $("h3[name='priceCount']").eq(indexVal).text(currentVal * 1 + 1);
		}

		if (countVal == 'minus' && currentVal >= 1) {
//			console.log("[Minus]")
			inputVal = $(".ticketCount").eq(indexVal).val(currentVal * 1 - 1);
			textVal = $("h3[name='priceCount']").eq(indexVal).text(currentVal * 1 - 1);
		} else if (countVal == 'minus' && currentVal <= 0) {
			console.log("[티켓 수량이 0 보다 작음]")
		}
		
	}

	//==================== + / - 버튼 처리 ====================

	$(function() {

		$(".icon.fa-minus").bind("click", function(event) {

			event.preventDefault();

			var indexVal = $(".icon.fa-minus").index(this);
			var countVal = 'minus';
			var currentVal = $(".ticketCount").eq(indexVal).val();

			/*
			console.log("- 인덱스 값 : " + indexVal)
			console.log("- 카운트 값 : " + countVal)
			console.log("- 현재 값 : " + currentVal)
			//*/
			
			if (currentVal <= 0) {
				makeToast("티켓 수량을 확인 하시기 바랍니다.")
			}

			fncTicketCount(indexVal, currentVal, countVal);

		});

		$(".icon.fa-plus").bind("click", function(event) {

			event.preventDefault();

			var indexVal = $(".icon.fa-plus").index(this);
			var countVal = 'plus';
			var currentVal = $(".ticketCount").eq(indexVal).val();

			/*
			console.log("+ 인덱스 값 : " + indexVal)
			console.log("+ 카운트 값 : " + countVal)
			console.log("+ 현재 값 : " + currentVal)
			//*/

			fncTicketCount(indexVal, currentVal, countVal);
		});

	});

	
	// ===== Form 유효성 검증 후 Navigation =====
	function fncAddPurchase(flag) {
		var name = $("input[name='buyerName']").val();
		var phone = $("input[name='buyerPhone']").val();
		
		for (var i = 0; i < $(".ticketPrice").length; i++) {		
			ticketPrice = $(".ticketPrice").eq(i).val();
			ticketCount = $(".ticketCount").eq(i).val();
			console.log('2. 티켓 구매수량 확인 : ' + ticketPrice + " = " + ticketCount)
			totalTicketCount += ticketCount * 1;
		}
		
//		console.log("티켓 몇장 구매 했니? " + totalTicketPrice)
		
		if (totalTicketCount == 0 || totalTicketCount == "") {
			makeToast("티켓 수량을 확인하시기 바랍니다.")
/*			
			history.go(0);
			$(".label.label-warning[name='titleB']").empty();
			$(".modal-body").empty();
			$(".label.label-success").empty();
//*/			
			return;
		}
		if (name == null || name.length < 1) {
			makeToast("이름은 반드시 입력해야 합니다.");
/*
			$(".label.label-warning[name='titleB']").empty();
			$(".modal-body").empty();
			$(".label.label-success").empty();
//*/			
			return;
		}
		if (phone == null || phone.length < 1) {
			makeToast("연락처는 반드시 입력해야 합니다.");
/*	
			$(".label.label-warning[name='titleB']").empty();
			$(".modal-body").empty();
			$(".label.label-success").empty();
//*/			
			return;
		}
		//==>"이메일" 유효성 Check Event 처리 및 연결
		$(function() {
			$("input[name='email']").bind( "change", function() {
				var email = $("input[name='email']").val();
				if (email != "" && (email.indexOf('@') < 1 || email.indexOf('.') == -1)) {
					makeToast("이메일 형식이 아닙니다.");
				}
			});
		});
		
		if (flag == 'basket') {

			var ticketPriceAll = "";
			for (var i = 0; i < $(".ticketPrice").length; i++) {	
				ticketPrice = $(".ticketPrice").eq(i).val();
				ticketCount = $(".ticketCount").eq(i).val();
				
	//			alert(ticketPrice)
	//			alert(ticketCount)
				
				if (ticketCount != 0) {
					ticketPriceAll += ticketPrice + "=" + ticketCount + "&";
	//				alert(ticketPriceAll)
				}
			}
			
			var basketData = {
					"ticketTitle" : '${ tourTicket.title }' ,
					"ticketImage" : '${ tourTicket.firstimage }' , 
					"bookingDate" : '${ bookingDate }' ,
					"ticketPriceAll" : ticketPriceAll ,
					"contentId" : '${ tourTicket.contentid }' ,
					"contentTypeId" : '${ tourTicket.contenttypeid }' ,
					"flag" : flag,
					"buyerId" : $("input[name='buyerId']").val() ,
					"buyerName" : $("input[name='buyerName']").val() ,
					"buyerPhone" : $("input[name='buyerPhone']").val() ,
					"buyerEmail" : $("input[name='buyerEmail']").val() ,
				}
			
			var jsonData = JSON.stringify(basketData);
			
			
			$.ajax (
					{
						url : "/purchase/json/addBasket",
						method : "POST",
						dataType : "json",
						headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
						data:jsonData,
						success : function() {
					//		alert("들어온거 확인");
						}
					});		
					alert("장바구니 저장 완료");
					location.reload();
		} else {
			
//			alert("flag 값 확인 : " + flag)
			
			$("form")
			.attr("method", "POST")
			.attr("action", "/purchase/addPurchase/" + flag)
			.submit();
		}
	}

	//=================== "선택티켓결제 취소" 버튼 Event 연결 ===================
	$(function() {
		$("#myModal button[name='modalCancel']").bind("click", function() {
//					alert("선택티켓결제 취소")
					$(".text-center.title").empty();
					$(".modal-body h4").empty();
//					$(".modal-body h3:contains('- 총 결제요금') strong").empty();
					$("strong.text-danger[name='totalTicketCount']").empty();
					$("strong.text-danger[name='totalTicketCount']").val('0');
				})
				
		$("header h1").on("click", function(){
			self.location="../ticket/listTicket";
		})				
	});	
	
	
	
	
	
</script>

	
</head>

<body class="left-sidebar">
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


<form>
			<!-- Main -->
				<div class="wrapper style1">
				
				<div id="toastMessage" class='toastMessage' style='display:none;'>Toast</div>	
				
				<input type="hidden" name="contentId" value="${ tourTicket.contentid }">
				<input type="hidden" name="contentTypeId" value="${ tourTicket.contenttypeid }">
				<input type="hidden" name="ticketTitle" value="${ tourTicket.title }">
				<input type="hidden" name="eventstartdate" value="${ tourTicket.eventstartdate }">
				<input type="hidden" name="eventenddate" value="${ tourTicket.eventenddate }">
				<input type="hidden" name="bookingDate" value="${ bookingDate }">
				<input type="hidden" name="eventplace" value="${ tourTicket.eventplace }">
				<input type="hidden" name="ticketImage" value="${ detailImage.originimgurl }">

					<div class="container">
						<div class="row 200%">
							<div class="4u 12u(mobile)" id="sidebar">
								<hr class="first" />
								<section>
									<header>
										<h3 class="icon fa-user">
											<a href="#">예매정보입력</a>
										</h3>
									</header>
									<p>
										구매하실 티켓에 대한 개인정보를 <strong class="text-danger">형식에 맞게 </strong>입력해 주세요.
									</p>
								</section>
								<hr />
								
								<section>
									
									<c:if test="${ user.userId eq null }">
									<header>
										<h3 class="icon fa-lock"><a href="#"> 로그인 후 이용가능</a></h3>
									</header>
									<div class="row 50%">
										
										<div class="12u">
											<a href="/user/login" name="login" class="button 12u">로 그 인</a>&nbsp;
										</div>	
										<div class="12u">
											<a href="/index.jsp" name="login" class="button 12u">메인으로</a>
										</div>	
									</div>
									</c:if>
									
									<c:if test="${ user.userId ne null }">
									<header>
										<h3><a href="#">개인정보 입력</a></h3>
									</header>
									<p>
										<strong class="text-danger">개인 정보</strong>를 확인하시기 바랍니다.
									</p>
									
									<div class="row 50%">
										
										<div class="4u">
											<h2 class="icon fa-user circled"><span class="label">user</span></h2>
										</div>
										
										<div class="8u">
											<input type="text" name="buyerId" id="demo-name" value="${ user.userId }" placeholder=" ID" readonly>
										</div>
										
										<div class="8u">
											<c:if test="${ user.userName eq null }">
												<input type="text" name="buyerName" id="demo-name" value="${ user.userName }" placeholder=" Name">
											</c:if>
											<c:if test="${ user.userName ne null }">	
												<input type="text" name="buyerName" id="demo-name" value="${ user.userName }" placeholder=" Name" readonly>
											</c:if>
										</div>

									</div>
								</section>
								
								
								<section>		
									<div class="row 50%">
									
										<div class="4u">
											<h2 class="icon fa-phone circled"><span class="label">user</span></h2>
										</div>
										
										<div class="8u">
											<c:if test="${ user.phone eq null }">
												<input type="text" name="buyerPhone" id="demo-name" value="${ user.phone }" placeholder=" Phone">
											</c:if>	
											<c:if test="${ user.phone ne null }">
												<input type="text" name="buyerPhone" id="demo-name" value="${ user.phone }" placeholder=" Phone" readonly>
											</c:if>
										</div>
										<div class="8u">
											<input type="text" name="buyerEmail" id="demo-name" value="${ user.email }" placeholder=" Email" readonly>
										</div>

									</div>
									
								</section>
								</c:if>
									<hr>
								
								<section>
									<header>
										<h3><a href="#">입장권 구매</a></h3>
									</header>
									<p>
										<strong class="text-danger">구매 수량</strong>을 선택하시기 바랍니다.
									</p>
									
									<c:forEach items="${ priceList }" varStatus="status">
									<div class="row 50%">
										
										<input type="hidden" class="ticketCount" name="ticketCount" value=0>
										<input type="hidden" class="ticketPrice" name="ticketPrice" value="${ priceList[status.index] }">
									
										<div class="4u">
											<h2 class="icon fa-money circled"><span class="label">Ticket</span></h2>
										</div>
										
										<div class="4u">
											<h4>￦ ${ priceList[status.index] }</h4>
										</div>
										<div class="4u">
											<h3 class="icon fa-plus"></h3>
										</div>
										
										<div class="4u">
											<h3 class="icon" name="priceCount">0</h3>
										</div>
										<div class="4u">
											<h3 class="icon fa-minus"></h3>
										</div>

									</div>
									</c:forEach>
								</section>
								
							</div>
							
							<div class="8u 12u(mobile) important(mobile)" id="content">
								<article id="main">
									<header>
										<h2 style="margin-top: -20px; padding-bottom: 20px;"><a href="#">${ tourTicket.title }</a></h2>
									</header>
									<a href="#" class="image featured">
							<%-- 			<img src="${ detailImage.originimgurl }" alt="" style="height: 413.86px;"/> --%>
										<img src="${ tourTicket.firstimage }" alt="" />
									</a>
									
									
									<div class="row">
										<div class="6u">	
											<section>
												<header>
													<h3><span class="fas fa-check-circle"></span>&nbsp;선택한 예매일자</h3>
												</header>
												<p>
													${ bookingDate }
												</p>
											</section>
										</div>
											
										<div class="6u">	
											<section>
												<header>
													<h3><span class="far fa-money-bill-alt"></span>&nbsp;이용요금</h3>
												</header>
												<p>
													${ detailIntro.usetimefestival }
												</p>
											</section>
										</div>
									</div>	

								</article>
							</div>
							<c:if test="${ user.userId ne null }">
							
							<section id="features" class="container special">
								<div class="row">
									<article class="4u 12u(mobile) special">
										<header>
											<a href="#" name="cancel" class="button 12u" onclick="goBack()">취&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소</a>
										</header>
									</article>
									<article class="4u 12u(mobile) special">
										<header>
											<a href="#" name="basket" class="button 12u" data-toggle="modal" data-target="#myModal">장바구니 담기</a>
										</header>
									</article>
									<article class="4u 12u(mobile) special">
										<header>
											<a href="#" name="purchase" class="button 12u">결제하기</a>
										</header>
									</article>
								</div>
							</section>		
							
							</c:if>
						</div><!-- row 200% end -->
						
					</div><!-- container -->

				</div>
				
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						장바구니에 저장 하시겠습니까?
					</h4>
				</div>
			
				<div class="modal-body">
					<h3 class="text-center title"></h3><br>
					<h3>- 선택한 예매일자 : <strong class='text-danger'>${ bookingDate }</strong></h3>
					<h3>- 선택한 티켓매수</h3>
					
					
					<h3>- 총 결제요금 : ￦ </h3>
				
				</div>
			
				<div class="modal-footer">
					<button type="button" class="button" data-dismiss="modal">저장하기</button>&nbsp;
					<button type="button" class="button" name="modalCancel" data-dismiss="modal">취&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소</button>
				</div>
			</div>
		</div>
	</div>

</form>

			<jsp:include page="/layout/footer.jsp" />

		</div>

	</body>

</html>