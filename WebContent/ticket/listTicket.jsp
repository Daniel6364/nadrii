<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<title>나들이 티켓 목록</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="../resources/helios/assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="../resources/helios/assets/css/bootstrap.min.css" />	
<link rel="stylesheet" href="../resources/helios/assets/css/main.css" />
<!--[if lte IE 8]><link rel="stylesheet" href="../resources/helios/assets/css/ie8.css" /><![endif]-->

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
<script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>

<!-- //////////////////// JavaScript //////////////////// -->
<script type="text/javascript">
	function fncGetList(pageNo) {
//		alert("pageNo")
		$("#pageNo").val(pageNo);
		$("form").attr("method", "POST").attr("action", "/ticket/listTicket").submit();

	}

	//=================== "4 kind of sorting" Event 연결 =================== 
	$(function() {

		$("a[href='#']:contains('제목순')").bind("click", function(event) {
			//alert("제목순")
			event.preventDefault();
			self.location = "/ticket/listTicket?searchCondition=A";
		})
	});

	$(function() {
		$("a[href='#']:contains('조회순')").bind("click", function(event) {
			event.preventDefault();
			self.location = "/ticket/listTicket?searchCondition=B";
		})
	});

	$(function() {
		$("a[href='#']:contains('수정일순')").bind("click", function(event) {
			event.preventDefault();
			self.location = "/ticket/listTicket?searchCondition=C";
		})
	});

	$(function() {

		$("a[href='#']:contains('생성일순')").bind("click", function(event) {
			event.preventDefault();
			self.location = "/ticket/listTicket?searchCondition=D";
		})
	});

	function fncGetTicketSort(searchCondition) {

		alert("[value check] ==> " + searchCondition)

		self.location = "/ticket/listTicket"

	}

	//=================== "상세조회" Event 연결 ===================
	$(function() {
		$("a[href='#']:contains('상세조회')").bind("click", function(event) {
//			alert("상세조회")
			event.preventDefault();
			
			var contentId = $( $('input[name="contentId"]')[$("a[href='#']:contains('상세조회')").index(this)]).val();
			var contentTypeId = $( $('input[name="contentTypeId"]')[$("a[href='#']:contains('상세조회')").index(this)]).val();
			var title = $( $('input[name="title"]')[$("a[href='#']:contains('상세조회')").index(this)]).val();
			var firstimage = $( $('input[name="firstimage"]')[$("a[href='#']:contains('상세조회')").index(this)]).val();
			var encodeTitle = encodeURI(encodeURIComponent(title));

			self.location = "/ticket/getTicket?" + "contentId="
					+ contentId + "&contentTypeId=" + contentTypeId
					+ "&title=" + encodeTitle+"&firstimage="+firstimage ;

		})
		
	});

	$(function() {
		$(".image.featured").bind("click", function(event) {
//			alert("사진")
			event.preventDefault();
			
			var contentId = $( $('input[name="contentId"]')[$(".image.featured").index(this)]).val();
			var contentTypeId = $( $('input[name="contentTypeId"]')[$(".image.featured").index(this)]).val();
			var title = $( $('input[name="title"]')[$(".image.featured").index(this)]).val();
			var firstimage = $( $('input[name="firstimage"]')[$(".image.featured").index(this)]).val();
			var encodeTitle = encodeURI(encodeURIComponent(title));

			self.location = "/ticket/getTicket?" + "contentId="
					+ contentId + "&contentTypeId=" + contentTypeId
					+ "&title=" + encodeTitle+"&firstimage="+firstimage ;
			
		})
	});
	
	
 	function fncGetTicket() {
		$(".page-wrapper").attr("method", "POST").attr("action", "/ticket/getTicket").submit();
	} 
 	
 	
 	$( function() {

 		$("header h1").on("click", function(){
			self.location="../ticket/listTicket";
		});
 		
 		$('#searchKeyword').keydown(function(key) {
			var data = $(this).val();
			if(key.keyCode==13){
				if(data!=""){
					fncGetList(1);
				}else{	
		 			alert("검색어를 입력해주세요");
				}
			}
		});
 		
 	})

 	
</script>

<style>
			.filter {
			  position: relative;
			  -webkit-filter: contrast(140%) sepia(50%);
			  filter: contrast(140%) sepia(50%);
			}
			.filter::before {
			  content: "";
			  display: block;
			  height: 100%;
			  width: 100%;
			  top: 0;
			  left: 0;
			  position: absolute;
			  pointer-events: none;
			  mix-blend-mode: lighten;
			  background: rgba(161, 44, 199, 0.31);
			}
			#nav {
			    background-color: #3b2b48;
			    opacity: 0.7;
			}
			div.wrapper.style1{
				background-image: url("/resources/images/background_3.jpg");
			}
			#searchKeyword{
				border-radius: 30px;
				width: 400px;
				height: 50px;
				padding: 1em 1em 1em 2.5em;
			}
			.image.featured{
				margin: 0 0 1em 0;
			}
			article {
				height: 550px;
			}
			.icons {
				position: absolute;
				bottom: 10px;
				right: 10px;
			}
			.author {
				position: absolute;
				bottom: 0px;
				left: 10px;
			}
			a.author:hover {
				text-decoration: none !important;
			}
			input:focus{
				outline:none;
			}
			svg[name="full"]{
				color: #F05643 !important;
			}
			.row > * {
			    padding: 24px 0 0 24px;
			    margin: 0 !important;
			}			
			#header{
				padding: 6.5em 0 1.5em 0;
 			}
 			a.button{
 				padding: 0 10px;
 			}
 			.wrapper{
 				padding-top: 4em;
 				margin: 0;
 			}
			#nav{
				padding: 22px 0;
			}

</style>
</head>

<body class="no-sidebar">

			<!-- Header -->
				<div id="header">
					<jsp:include page="/layout/toolbar.jsp" />
					
					<div class="inner">
						<header>
							<h1>나들이 티켓</h1>
							<p>기다리지말고 예매하세요</p>
						</header>
					</div>		
				</div>
				
			<form action="navigation">
			<!-- Features -->
				<div class="wrapper style1">
					<section id="features" class="container special">
						<div class="continer">
						
							<header>
								<p>조회하실 티켓 정보를 <strong class="text-danger">선택</strong>해 주세요.</p>
							</header>
							
							<footer>
								
							</footer>
							
							<div class="col-sm-12" style="padding-bottom: 40px;">
								<h6 style="color: #8a8c91; float: left; font-style:normal;">
									현재 ${ resultPage.pageNo } 페이지 / 전체 ${ resultPage.totalCount } 건
								</h6>
							</div>
							
							<div>
								<span style="float: left; padding-bottom: 13px;">
									<a href="#" style="border-bottom: 1px solid; padding: 6px;">제목순</a>&nbsp;
									<a href="#" style="border-bottom: 1px solid; padding: 6px;">조회순</a>&nbsp;
									<a href="#" style="border-bottom: 1px solid; padding: 6px;">수정일순</a>&nbsp;
									<a href="#" style="border-bottom: 1px solid; padding: 6px;">생성일순</a>
								</span>
								<div style="float: right;">
									<span class="fas fa-search" style="position: relative; margin-right: -40px;"></span>
									<input type="text" name="searchKeyword" id="searchKeyword" value="" style="width: 100%"/>
								</div>
							</div>
							
						</div>
						
						
						
						<div class="row">
						
							<c:forEach var="tt" items="${tourTicket}" varStatus="num">
								<article class="4u 12u(mobile) special">
									<div style="background-color: white; height: 490px; padding: 10px 10px 0 10px; position: relative;">
										<a href="#" class="image featured">
											<img src="${ tt.firstimage }" alt="" style="height: 302.36px;"/>
										</a>
										<header style="height: 30px;">
											<h3>${ num.count }. ${ tt.title }</h3>
										</header>
										<span class="timestamp" style="float: right;">조회수 : ${ tt.readcount }</span>
										<p>
											기&nbsp;간 : ${ tt.eventstartdate } ~ ${ tt.eventenddate }
										</p>
										<a href="#" class="button 6u" style="float: right;">상세조회</a>
									
										
										<!-- PageNavigation을 위한 값을 보내는 부분  -->
										<input type="hidden" name="firstimage" value="${ tt.firstimage }">
										<input type="hidden" name="contentId" value="${ tt.contentid }">
										<input type="hidden" name="contentTypeId" value="${ tt.contenttypeid }">
										<input type="hidden" name="title" value="${ tt.title }">
										<input type="hidden" name="eventstartdate" value="${ tt.eventstartdate }">
										<input type="hidden" name="eventenddate" value="${ tt.eventenddate }">
									</div>
								</article>
							</c:forEach> 
								
						</div>
						
					</section>
					
					<!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
					<input type="hidden" id="pageNo" name="pageNo" value="" /> 
					<input type="hidden" name="searchCondition" id="searchCondition" value="">		
					<jsp:include page="../common/pageNavigator_openApi.jsp"/>
				</div>
						
		</form>
		
		<jsp:include page="/layout/footer.jsp" />

	</body>
</html>