<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="../resources/helios/assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="../resources/helios/assets/css/bootstrap.min.css" />	
		<link rel="stylesheet" href="../resources/helios/assets/css/main.css" />
		<!--[if lte IE 8]><link rel="stylesheet" href="../resources/helios/assets/css/ie8.css" /><![endif]-->

		<!-- Scripts -->
		<script src="../resources/helios/assets/js/jquery.min.js"></script>
		<script src="../resources/helios/assets/js/jquery.dropotron.min.js"></script>
		<script src="../resources/helios/assets/js/jquery.scrolly.min.js"></script>
		<script src="../resources/helios/assets/js/jquery.onvisible.min.js"></script>
		<script src="../resources/helios/assets/js/skel.min.js"></script>
		<script src="../resources/helios/assets/js/util.js"></script>
		<!--[if lte IE 8]><script src="../resources/helios/assets/js/ie/respond.min.js"></script><![endif]-->
		
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="../resources/helios/assets/js/main.js"></script>
		<link href="../resources/css/keywordSearch.css" rel="stylesheet">
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
		<script src="../resources/script/html2canvas.js"></script>
		
		<style>
		#nav{
			padding: 22px;
		}
		h4 {
			color: #6d6d6d !important;
		}
		.panel-heading{
			display: none; 
			border-style: solid; 
			border-width: 2px;
			border-top-left-radius: 12px;
			border-top-right-radius: 12px;
			border-color: #afafaf;
		}	
		.map_wrap, .map_wrap * {
		    font-size: 15px !important;
		}		
		th{
			padding: 10px;
			vertical-align: middle;
		}	
		input[type="button"]{
			padding: 0 5px;
			font-size: 18px !important;
		}	
		.ui-dialog .ui-dialog-titlebar {
		    padding: 0em 1em;
		    position: relative;
		}
		.ui-widget-header {
    		background: #f9a11b;
    	}
    	.ui-dialog .ui-dialog-title {
		    color: #ffffff;
	    }
	    input:focus{
			outline:none;
		}
		textarea:focus{
			outline:none;
		}
		</style>
		<script>

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
			
		///////////////////////////위 위시 위시리스트///////////////////////////////////
		$(function(){
			$("#wish").on("click",function(){
				//window.open("/wish/listTripFromWish?userId=" +'${loginUser.userId}',"wishList","width=300, height=350,status=no, scrollbars=no, location=no");
				listWishFromTrip();
				
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
					$("#reel").empty();
					var imgList = returnData.list;
					var tagValue= "";
					
					for (var i = 0; i < imgList.length; i++) {
						tagValue = "<tr><th align='center' style='padding-left: 0 !important;'>"+(i+1)+"</th><th align='center'><img src='"+ imgList[i].tripNo.thumbnailImageFile+"' style='height: 50px; width: 70px;'/></th>"
										+"<th align='center'><a href='#' class='tripName'>"+imgList[i].tripNo.tripName+"</a></th><th align='left'>"+imgList[i].tripNo.address+"</th>"+
										"<input type='hidden' name='tripName' value='"+imgList[i].tripNo.tripName+"'/>"+"<input type='hidden' name='latFromWish' value='"+imgList[i].tripNo.lat+"'/>"+"<input type='hidden' name='lngFromWish' value='"+imgList[i].tripNo.lng+"'/></tr>";
						
						$("#reel").append(tagValue);			
						$("#wishTable").dialog({
							width: 700,
							height: 500,
							title: 'WISH LIST',
							open: function(event, ui){
								$("#wishTable").css('overflow', 'auto');
							}
						});						
					}
					
					$(function(){
						$(".tripName").on("click", function(){
							
							var wishLatlng = new daum.maps.LatLng($($("input[name='latFromWish']")[$(".tripName").index(this)]).val(), $($("input[name='lngFromWish']")[$(".tripName").index(this)]).val());
							infowindow.close();
							
							clickMarker.setPosition(wishLatlng);
							clickMarker.setMap(map);
							
							map.setLevel(6);
							map.setCenter(wishLatlng);
							
							var infoContent = '<div style="padding:5px;">'
													+'<a href="#" id="start" style="color:red;" onclick="javascript:start()">'
													+'<img src="http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/red_b.png" style="width:30px;">&nbsp;&nbsp;출발지</a><br>'
													+'<a href="#" id="pass" style="color:green;" onclick="javascript:pass()">'
													+'<img src="http://t1.daumcdn.net/localimg/localimages/07/2013/img/green_b_1.png" style="width:30px;">&nbsp;&nbsp;경유지</a><br>'
													+'<a href="#" id="end" style="color:blue;" onclick="javascript:end()">'
													+'<img src="http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/blue_b.png" style="width:30px;">&nbsp;&nbsp;도착지</a></div>';
					
							var wishInfoContent = '<span style="padding-left: 10px;">'+$($("input[name='tripName']")[$(".tripName").index(this)]).val()+'</span></br>'+infoContent;
							console.log(wishInfoContent);
							speInfoWindow.setContent(wishInfoContent);
							speInfoWindow.setMap(map);
				
						    speInfoWindow.open(map,clickMarker);
						    
						});						
					});
					
				}
			});
		}
			
		</script>
	</head>
	<body class="right-sidebar">
		<div id="page-wrapper">	
		<!-- Header -->
			<div id="header"  style="padding: 5px 0 2em 0;">
				<jsp:include page="../layout/toolbar.jsp" />		
			</div>		
		<!-- Main -->
				<div style="background: #ffffff;">
					<div style="margin: 0; padding: 0;">
						<div class="row 200%">
							<div id="content" style="width: 70%; height: 800px; float: left; border-top:1px solid black;">
								<div class="map_wrap">
									<div id="map" style="height: 830px; position: relative; overflow: hidden; padding:10px;"></div>
										
										<!--  --><!--  --><!--  --><!--  --><!--위시리스트 이미지 넣기  --><!--  --><!--  --><!--  --><!--  --><!--  -->
											<div id="wishTable">
												<span id="reel" style='font-size: 11pt !important;'></span>
											</div>
										<!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  --><!--  -->
										
										
									<div id="menu_wrap" class="bg_white">
										<div class="option">
											<form onsubmit="searchPlaces(); return false; runEffect();" style="margin: -8px">
												키워드 : <input type="text" value="" id="keyword" size="15" style="padding: 0; width:50%;">
												<span type="submit" id="searchListSubmit" style="color: #ffffff; padding: 5px; font-size: 10pt !important; background-color: #df7366; border-radius: 0.5em;">검색</span>
											</form>
											<ul id="placesList" style="display: none;"></ul>
											<a id="toggleButton"><span class="fas fa-chevron-circle-down" style="color: #df7366; width: 25px"></span></a>
											
											<ul id="placesWishList" style="display: none;"></ul>
											
										</div>
									</div>
									<div id="pagination"></div>
									
								</div>
							</div>
							<div id="sidebar" style="width: 30%; padding: 140px 40px 40px 40px; height: 940px; float: left; overflow-x:hidden; overflow-y: auto; border-top:1px solid black; border-left: 1px solid black;">
								<form name="addPlanner">
								<div>
									<c:if test="${status == 'normal'}">
										<input type="hidden" name="imgSrc" id="imgSrc" />
										<input type="button" id="save" value="플래너 저장" style="float: right;" />
									</c:if>
									<c:if test="${status == 'update'}">
										<input type="hidden" name="imgSrc" id="imgSrc" />
										<input type="button" id="update" value="플래너 수정" style="float: right;" />
									</c:if>
										<input type="text" name="title" value="${planner.title}" placeholder="플래너 제목" style="padding:8px; width:80%; font-size: 13pt;"/>
								</div>
								
								<div style="overflow: hidden; padding-top: 20px;">
									<div id="exButtonCreate" style="float:right;"></div>
								</div>
								
								<div id="captureArea">
									<div style="padding:30px 45px 30px 30px; overflow: hidden;">
										<div id="subPointer" style="float: left; overflow: hidden;"></div>
										<div style="float: right; overflow: hidden;">
											<ul id="sortable" class="pointer" style="width: 300px; ">
											</ul>
										</div>
									</div>
								</div>
								
								<div style="overflow: hidden;">
									<input type="button" value="경로탐색" style="float:right;" onclick="javascript:search1(1)">
									<input type="button" id="wish" value="위시리스트" style="float:left; background: #f9a11b;" >
								</div>
								
								<div id="roadContent" style="padding: 30px 10px 10px 10px; overflow: hidden; font-size: 12pt">
									<div class="panel-group" id="accordion" role="tablist"
										aria-multiselectable="true">
										<div class="panel-heading" role="tab" id="head1">
											<h4 class="panel-title">
												<a role="button" data-toggle="collapse"
													data-parent="#accordion" href="#collapse1"
													aria-expanded="true" aria-controls="collapse1"> <span
													class="fas fa-flag-checkered"></span>&nbsp;경로1
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
													class="fas fa-flag-checkered"></span>&nbsp;경로2
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
													class="fas fa-flag-checkered"></span>&nbsp;경로3
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
													class="fas fa-flag-checkered"></span>&nbsp;경로4
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
													class="fas fa-flag-checkered"></span>&nbsp;경로5
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
													class="fas fa-flag-checkered"></span>&nbsp;경로6
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
								
								<div>
									<textarea class="form" rows="7" cols="blue" name="text" placeholder="플랜세부계획을 작성해주세요~!">${planner.text}</textarea>
								</div>
								
								<input type="hidden" name="lat" value=""> 
								<input type="hidden" name="lng" value="">
								<c:if test="${status == 'update'}">
									<input type="hidden" name="postNo" value="${planner.postNo }">
								</c:if>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=162ee19a901cbbe89c0c4b261ddecca3&libraries=services"></script>
	<jsp:include page="../planner/plannerSub/mapAndRoadSearch.jsp" />
	<c:forEach items="${ dbLat }" varStatus="num">
		<input type="hidden" class="lat" name="lat" value="${ dbLat[num.index] }">
		<input type="hidden" class="lng" name="lng" value="${ dbLng[num.index] }">
	</c:forEach>
	</body>
</html>