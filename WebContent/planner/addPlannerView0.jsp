<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">


<head>

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

	<!-- Latest compiled and minified JavaScript -->
	<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<div id="header">

		<jsp:include page="/layout/toolbar.jsp" />

	</div>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">

<link href="../resources/css/keywordSearch.css?version=1"
	rel="stylesheet">
<script src="../resources/script/html2canvas.js"></script>

<script type="text/javascript">

	$(function() {
		$("#save").on("click",function() {
			save();
		
			$("input[name='lat']").val(lat);
			$("input[name='lng']").val(lng);
			
			var a = 1;
			
			capture(a);
			
		});
	});
	
	$(function() {
		$("#update").on("click",function() {
			save();
			
			$("input[name='lat']").val(lat);
			$("input[name='lng']").val(lng);

			var a = 2;
			
			capture(a);
			
		});
	});
	
	
	///////////////////////////위 위시 위시리스트///////////////////////////////////
	$(function(){
		$("#wish").on("click",function(){
			alert("뭐야 이게");
			window.open("/wish/listTripFromWish?userId=" +'${loginUser.userId}',"wishList","width=300, height=350,status=no, scrollbars=no, location=no");
			//listWishFromTrip();
		});
	});
	
	function listWishFromTrip(){
		$.ajax({
			url:"/wish/json/listTripFromWish/"+'${loginUser.userId}',
			method:"GET",
			dataType:"json",
			headers :{
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},
			success: function(returnData){
				var imgList = returnData.list;
				var tagValue= "";
				for (var i = 0; i < imgList.length; i++) {
					tagValue = "<img src='"+ imgList[i].tripNo.thumbnailImageFile+"'/>"
								+"<input type='hidden' name='latFromWish' value='"+imgList[i].tripNo.lat+"'/>"
								+"<input type='hidden' name='lngFromWish' value='"+imgList[i].tripNo.lng+"'/>";
					$(".reel").append(tagValue);			
				}
				
			}
		});
	}
	
/*********************** 캡쳐기능 펑션생성 **************************/
	
	function capture(a) {
        html2canvas($("#captureArea").get(0)).then(function(canvas) {
				console.log(canvas);
              
                $("#imgSrc").val(canvas.toDataURL("image/png"));
                if(a == 1){
                		$("form[name='addPlanner']").attr("method", "POST").attr("action", "/planner/addPlanner").submit();
                }
				if(a == 2){
					$("form[name='addPlanner']").attr("method", "POST").attr("action", "/planner/updatePlanner").submit();
				}
		});
	}
	
	//////////////////////////////////////////////////////////////////////////////
</script>

<style type="text/css">
h4 {
	color: #506fed !important;
}
.panel-heading{
	display: none; 
	border-style: solid; 
	border-width: 3px;
	border-color: #adbdf9;;
}

</style>

</head>
<body>
	<div style="width: 70%; height: 800px; float: left; border-top:1px solid black;">

		<div class="map_wrap">
			<div id="map"
				style="width: 100%; height: 800px; position: relative; overflow: hidden; padding:10px;"></div>
				
				<!--  --><!--  --><!--  --><!--  --><!--위시리스트 이미지 넣기  --><!--  --><!--  --><!--  --><!--  --><!--  -->
				<section class="carousel">
					<div class="reel" style="overflow: visible; transform: translate(0px, 0px);">
								
						
						
							
					</div>
					<span class="forward" style="display: block;"></span>
					<span class="backward" style="display: block;"></span>
				</section>
				<!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  -->
				
				
			<div id="menu_wrap" class="bg_white">
				<div class="option">
						<form onsubmit="searchPlaces(); return false;">
							키워드 : <input type="text" value="" id="keyword" size="15" style="padding:0; width:50%;">
							<button type="submit" id="searchListSubmit" style="padding: 0;font-size: 9pt;width: 60px;background-color: navajowhite;">검색하기</button>
						</form>
						<ul id="placesList" style="display: none;"></ul>
						
						<text id="button">▼</text>
						<ul id="placesWishList" style="display: none;"></ul>
						<!--  --><!--  --><!--  --><!--  --><!--  -->
						
						<text id="wishButton">▲</text>
						<!--  --><!--  --><!--  --><!--  --><!--  -->
						
				</div>
			</div>
			<div id="pagination"></div>
			
				
			</div>

			<div id="clickLatlng"></div>

		</div>
	</div>

	<div style="width: 30%; height: 800px; float: left; overflow-x:hidden; overflow-y: auto;border-top:1px solid black; border-left: 1px solid black;">
		<form name="addPlanner">
			<div class="container-fulid" style="margin:15px;">

				<div class="row">
					<div class="col-md-12">
						<h4>플래너 제목 : <input type="text" name="title" value="${planner.title }" placeholder="제목을 입력해주세요." style="padding:3px;width:70%"/></h4>
						<c:if test="${status == 'normal'}">
							<input type="hidden" name="imgSrc" id="imgSrc" />
							<input type="button" id="save" value="플래너 저장" style="float: right;" />
						</c:if>
						<c:if test="${status == 'update'}">
							<input type="hidden" name="imgSrc" id="imgSrc" />	
							<input type="button" id="update" value="플래너 수정" style="float: right;" />
						</c:if>
						<hr>
					</div>

					<div class="col-md-12" id=exButtonCreate style="float:right;"></div>
					<div class="col-md-12" id="captureArea" style="padding:10px;">
						<div class="col-md-2" id="subPointer" style="padding:0;"></div>
						<div class="col-md-10">
							<ul id="sortable" class="pointer" style="width: 300px">
							</ul>
						</div>
					</div>
	
					<div class="col-md-12" style="padding:10px; ">
						<input type="button" value="경로탐색" style="float:right;" onclick="javascript:search1(1)">
						<!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  -->
						<input type="button" id="wish" value="위시리스트" style="float:right;" >
						<!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  -->
					</div>

					<div id="roadContent" class="col-md-12" style="padding:10px;">
						<div class="panel-group" id="accordion" role="tablist"
							aria-multiselectable="true">
							<div class="panel-heading" role="tab" id="head1">
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse"
										data-parent="#accordion" href="#collapse1"
										aria-expanded="true" aria-controls="collapse1"> <span
										class="glyphicon glyphicon-flag"></span>&nbsp;경로1
									</a>
								</h4>
							</div>
							<div id="collapse1" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="head1">
								<div class="panel-body">
									<div id="roadStartContent"></div>
								</div>
							</div>
							<div class="panel-heading" role="tab" id="head2">
								<h4 class="panel-title">
									<a class="collapsed" role="button" data-toggle="collapse"
										data-parent="#accordion" href="#collapse2"
										aria-expanded="false" aria-controls="collapse2"> <span
										class="glyphicon glyphicon-flag"></span>&nbsp;경로2
									</a>
								</h4>
							</div>
							<div id="collapse2" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="head2">
								<div class="panel-body">
									<div id="roadPass1Content"></div>
								</div>
							</div>
							<div class="panel-heading" role="tab" id="head3">
								<h4 class="panel-title">
									<a class="collapsed" role="button" data-toggle="collapse"
										data-parent="#accordion" href="#collapse3"
										aria-expanded="false" aria-controls="collapse3"> <span
										class="glyphicon glyphicon-flag"></span>&nbsp;경로3
									</a>
								</h4>
							</div>
							<div id="collapse3" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="head3">
								<div class="panel-body">
									<div id="roadPass2Content"></div>
								</div>
							</div>
							<div class="panel-heading" role="tab" id="head4">
								<h4 class="panel-title">
									<a class="collapsed" role="button" data-toggle="collapse"
										data-parent="#accordion" href="#collapse4"
										aria-expanded="false" aria-controls="collapse4"> <span
										class="glyphicon glyphicon-flag"></span>&nbsp;경로4
									</a>
								</h4>
							</div>
							<div id="collapse4" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="head4">
								<div class="panel-body">
									<div id="roadPass3Content"></div>
								</div>
							</div>
							<div class="panel-heading" role="tab" id="head5">
								<h4 class="panel-title">
									<a class="collapsed" role="button" data-toggle="collapse"
										data-parent="#accordion" href="#collapse5"
										aria-expanded="false" aria-controls="collapse5"> <span
										class="glyphicon glyphicon-flag"></span>&nbsp;경로5
									</a>
								</h4>
							</div>
							<div id="collapse5" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="head5">
								<div class="panel-body">
									<div id="roadPass4Content"></div>
								</div>
							</div>
							<div class="panel-heading" role="tab" id="head6">
								<h4 class="panel-title">
									<a class="collapsed" role="button" data-toggle="collapse"
										data-parent="#accordion" href="#collapse6"
										aria-expanded="false" aria-controls="collapse6"> <span
										class="glyphicon glyphicon-flag"></span>&nbsp;경로6
									</a>
								</h4>
							</div>
							<div id="collapse6" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="head6">
								<div class="panel-body">
									<div id="roadPass5Content"></div>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<textarea class="form-control" rows="25" cols="blue" name="text" placeholder="플랜세부계획을 짜주세요~!">${planner.text}</textarea>
					</div>

					<input type="hidden" name="lat" value=""> 
					<input type="hidden" name="lng" value="">
					<c:if test="${status == 'update'}">
						<input type="hidden" name="postNo" value="${planner.postNo }">
					</c:if>
				</div>
			</div>
		</form>
	</div>
	
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=162ee19a901cbbe89c0c4b261ddecca3&libraries=services"></script>

	<jsp:include page="../planner/plannerSub/mapAndRoadSearch.jsp" />
	
	<c:forEach items="${ dbLat }" varStatus="num">
		<input type="hidden" class="lat" name="lat" value="${ dbLat[num.index] }">
		<input type="hidden" class="lng" name="lng" value="${ dbLng[num.index] }">
	</c:forEach>
	

</body>
</html>