
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<!--[if lte IE 8]><script src="/resources/helios/assets/js/ie/html5shiv.js"></script><![endif]-->
	<link rel="stylesheet" href="../resources/helios/assets/css/bootstrap.min.css" />	
	<link rel="stylesheet" href="../resources/helios/assets/css/main.css" />
	<!--[if lte IE 8]><link rel="stylesheet" href="/resources/helios/assets/css/ie8.css" /><![endif]-->
	
	<script src="/resources/helios/assets/js/jquery.min.js"></script>
	<script src="/resources/helios/assets/js/jquery.dropotron.min.js"></script>
	<script src="/resources/helios/assets/js/jquery.scrolly.min.js"></script>
	<script src="/resources/helios/assets/js/jquery.onvisible.min.js"></script> 
	<script src="/resources/helios/assets/js/skel.min.js"></script>
	<script src="/resources/helios/assets/js/util.js"></script>
	<!--[if lte IE 8]><script src="/resources/helios/assets/js/ie/respond.min.js"></script><![endif]-->
	<script src="/resources/helios/assets/js/main.js"></script>
	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="../resources/css/keywordSearch.css" rel="stylesheet">
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 	
	<script type="text/javascript">
	
// 무한 스크롤 구현, getTheme.jsp 대신 다이얼로그 창으로 화면 구성함
// 미비점 : 현재 다이얼로그 창에서 지도를 보여 주는 방식은 기존존재했던 지도 위에 덧붙이는 방법. 화면상 드래그시 그대로 노출됨.
// 사유 : 지도 공간, 호출 CDN, 호출 대상을 모두 분리해서 코딩했더니 기존에 지도에 덧붙는 방법으로 호출되고, 다이얼로그 tag와 충돌하면서 지도가 깨지는 현상 발생. 
// 임시 해결방법 : 재차 호출의 경우 문제 없이 호출되는 것을 확인해서 맵생성, 다이얼로그 생성을 각각 2번씩 호출함(우회 코딩)
	
	///////////////////무한 스크롤 시작
	///*
	//page할 변수
	var page = 1;
	var federalPage = 0;
	var nationalPage =0;
	var flag =0;
	//*/
	
	//onload 시 page 변환 출력 페이지는 1, 현재 page는 2
	$(function(){
		page++;
		if($(".4u").val()==null){
			listTrip();
		}
	});
	
	//스크롤이 끝에 닿을 때를 캐치
	$(window).scroll(function() { 
		if($(window).scrollTop() == $(document).height() - $(window).height() & flag ==1){
			flag=0;
		}
		
		if ($(window).scrollTop() >= $(document).height() - $(window).height()& flag ==0) {
			flag=1;
			listTrip(page);
			
			page++;
		}
	});
	
	//페이지 네이게이션을 수행하는 JS
	function listTrip(){
	
		var areaCode = "${areaCode}";
		var localName ="${localName}";
		if(areaCode ==""){
			areaCode ="0";
		}
		if(localName==""){
			localName="0";
		}	
		
		$.ajax({
			url:"../trip/json/list"+'${trip}'+"/"+page+"/"+areaCode+"/"+localName,
			
			method:"GET",
			asyn :false,
			dataType:"json",
			headers :{
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},
						
			success: function(returnData){
				
				var data = returnData.list;
				
				if(data.length != 0){
																
					for(var a =0; a<data.length;++a){
						
						var postNo;
						if(!(data[a].trip == '' || data[a].trip == null)){
							postNo = data[a].trip.postNo;
						}else{
							postNo = '';
						}
						
						var dpValue =						
							"<article class='4u 12u(mobile) special'>"
							+"<div style='background-color: white; height: 400px; padding: 10px 10px 0 10px; position: relative;'>"
							+"<input type='hidden' name='postNo' value='"+postNo+"'/>"
							+"<a href='#' class='image featured'><img src='"+data[a].firstimage+"' alt='' style='height: 245px;'/></a>"
							+"<input type='hidden' name='contentid' value='"+data[a].contentid+"'/>"
  							+"<input type='hidden' name='contenttypeid' value='"+data[a].contenttypeid+"'/>"
							+"<header  style='text-align: center;'>"
							+"<h3><a href='#' name='title'>"+data[a].title+"</a></h3>"
							+"</header>"
							+"<div style='text-align: right;'>"
							+"<p>"+data[a].addr1+"</p>"
							+"</div>"
							+"<div>"
							+"<div class='icons' style='float: right;'>"
							+"<span class='wishIcon'></span>&nbsp;<span class='wishCount'></span>"
							+"</div></div>"
							+"</div>"
						+"</article>";
						$(".row").append(dpValue);	
					}
					getSomething($("article").length);
					
				}else{
					federalPage++;
					$.ajax({
						url:"../trip/json/list"+'${trip}'+"/"+federalPage+"/"+areaCode+"/"+"0",
						method:"GET",
						asyn :false,
						dataType:"json",
						headers :{
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
						success: function(returnData){
							flag =1;
							var data = returnData.list;
							if(data.length!=0){
																											
								for(var a =0; a<data.length;++a){
									var postNo;
									if( !(data[a].trip == '' || data[a].trip == null)){
										postNo = data[a].trip.postNo;
									}else{
										postNo = '';
									}
									var dpValue =
										
										"<article class='4u 12u(mobile) special'>"
										+"<div style='background-color: white; height: 400px; padding: 10px 10px 0 10px; position: relative;'>"
										+"<input type='hidden' name='postNo' value='"+postNo+"'/>"
										+"<a href='#' class='image featured'><img src='"+data[a].firstimage+"' alt='' style='height: 245px;'/></a>"
										+"<input type='hidden' name='contentid' value='"+data[a].contentid+"'/>"
		      							+"<input type='hidden' name='contenttypeid' value='"+data[a].contenttypeid+"'/>"
										+"<header  style='text-align: center;'>"
											+"<h3><a href='#' name='title'>"+data[a].title+"</a></h3>"
										+"</header>"
										+"<div style='text-align: right;'>"
										+"<p>"+data[a].addr1+"</p>"
										+"</div>"
										+"<div>"
										+"<div class='icons' style='float: right;'>"
										+"<span class='wishIcon'></span>&nbsp;<span class='wishCount'></span>"
										+"</div></div>"
										+"</div>"
									+"</article>";
									$(".row").append(dpValue);
									
								}
								getSomething($("article").length);
								
							}else{
								nationalPage++;
								$.ajax({
									url:"../trip/json/list"+'${trip}'+"/"+federalPage+"/"+"0"+"/"+"0",
									method:"GET",
									asyn :false,
									dataType:"json",
									headers :{
										"Accept" : "application/json",
										"Content-Type" : "application/json"
									},
									success: function(returnData){
										flag =1;
										var data = returnData.list;
										if(data.length!=0){
																														
											for(var a =0; a<data.length;++a){
												var postNo;
												if(!(data[a].trip == '' || data[a].trip == null)){
													postNo = data[a].trip.postNo;
												}else{
													postNo = '';
												}
												var dpValue =
													
													"<article class='4u 12u(mobile) special'>"
													+"<div style='background-color: white; height: 400px; padding: 10px 10px 0 10px; position: relative;'>"
													+"<input type='hidden' name='postNo' value='"+postNo+"'/>"
													+"<a href='#' class='image featured'><img src='"+data[a].firstimage+"' alt='' style='height: 245px;'/></a>"
													+"<input type='hidden' name='contentid' value='"+data[a].contentid+"'/>"
					      							+"<input type='hidden' name='contenttypeid' value='"+data[a].contenttypeid+"'/>"
													+"<header  style='text-align: center;'>"
														+"<h3><a href='#' name='title'>"+data[a].title+"</a></h3>"
													+"</header>"
													+"<div style='text-align: right;'>"
													+"<p>"+data[a].addr1+"</p>"
													+"</div>"
													+"<div>"
													+"<div class='icons' style='float: right;'>"
													+"<span class='wishIcon'></span>&nbsp;<span class='wishCount'></span>"
													+"</div></div>"
													+"</div>"
												+"</article>";
											      												
												$(".row").append(dpValue);
											}
										}
										getSomething($("article").length);
									}
								});
										
							}
						}
					});
				}
			}
								
			
			
		});
	}
	
	window.onload = function(){
		getSomething($("article").length);
	}		
	
	function getSomething(k){
		
		for(var i=0; i< k ; i++){
			var postNo = $($('input[name="postNo"]')[i]).val();
			console.log(postNo);
			if( !(postNo == '' || postNo == null )){
				
				$.ajax({
					url:"../wish/json/listWishByPost/"+postNo,
					method:"GET",
					async: false,
					success:function(returnData){
						
						console.log(i+" wish::"+postNo);
						console.log(returnData);
						
						$("article.special:nth-child("+(i+1)+") .wishCount").empty();
						$("article.special:nth-child("+(i+1)+") .wishCount").append(returnData.totalCount);
						
						$("article.special:nth-child("+(i+1)+") .wishIcon").empty();
						if( ((JSON.stringify(returnData.list)).indexOf("${loginUser.userId}") == -1) || ("${loginUser.userId}"=='') || ("${loginUser.userId}" == null) ){
		                	$("article.special:nth-child("+(i+1)+") .wishIcon").append('<i class="far fa-bookmark"></i>');
		                }else{
		                    $("article.special:nth-child("+(i+1)+") .wishIcon").append('<i class="fas fa-bookmark" name="full"></i>');
		                }
					}
				});
			}else{
				 
				$("article.special:nth-child("+(i+1)+") .wishCount").empty();
				$("article.special:nth-child("+(i+1)+") .wishCount").append('0');
				
				$("article.special:nth-child("+(i+1)+") .wishIcon").empty();
                $("article.special:nth-child("+(i+1)+") .wishIcon").append('<i class="far fa-bookmark"></i>');
               
			}
			
			
					
		}//for문
	}
	
	///////토스트 창/////////
	function makeToast(title) {
		$('#toastMessage').text(title).fadeIn(400).delay(3000).fadeOut(400);
	}
	///////////////////////////////////////////
	///*
	
	
	function getTheme(contentid, contenttypeid){
		$.ajax({
			url:"../trip/json/getTrip/"+contentid+"/"+contenttypeid+"",
			method:"GET",
			dataType:"json",
			headers :{
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},
						
			success: function(returnData){
				var common = returnData.getTrip;
				var fee = returnData.getDetail;
				mapx = common.mapx;
				mapy = common.mapy;
				contentid =common.contentid;
				contenttypeid = common.contenttypeid;

				$("#tourName").remove();
				$("#tourAddress").remove();
			
				$("#tourCharge").remove();
				$("#tourOverView").remove();
				
				var tourName = "";
				var tourImage = "";
				var tourAddress ="";
				var tourCharge ="";
				var tourOverView ="";
						
				
				if(common.title != null){
					tourName= "<h5 id='tourName'><strong> "+ common.title+"</strong></h5>";
				}else{
					tourName= "<h5 id='tourName'> 제공정보 없음 </h5>";
				}
				
				
				if(common.addr1 != null){
					tourAddress= "<h5 id='tourAddress'>"+ common.addr1+"</h5>";
				}else{
					tourAddress= "<h5 id='tourAddress'> 제공정보 없음 </h5>";
				}
				
				if(fee.usefee != null){
					tourCharge= "<h5 id='tourCharge'>"+ fee.usefee+"</h5>";
				}else{
					tourCharge= "<h5 id='tourCharge'> 제공정보 없음 </h5>";
				}
				
				if(common.overview != null){
					tourOverView= "<h5 id='tourOverView'>"+ common.overview+"</h5>";
				}else{
					tourOverView= "<h5 id='tourOverView'> 제공정보 없음 </h5>";
				}
				
				$("#titleName").append(tourName);	
				$("#address").append(tourAddress);
				$("#fee").append(tourCharge);
				$("#description").append(tourOverView);
				
				makeMap();
			
				$('#dialog').dialog({
				    autoOpen: false,
				    resizable: false,
				    width: 800,
				    height: 700,
				    open: function(event, ui){
				    	
						$("#dialog").css('overflow', 'auto');
						$(".ui-dialog .ui-dialog-titlebar").css("padding","0");
						$(".ui-widget-header").css("border","0");
						$(".table>tbody>tr>td").css("border","0");
						
					}					
				});
		
				$('#dialog').dialog('open');
				
				
				makeMap();
		
				$('#dialog').dialog('open');

			}
		});
	}
	
	/////////////////////////

		function addWish(contentid) {
			$.ajax({
				url : "../wish/json/addWishFromTrip/" + contentid + "",
				method : "GET",
				dataType : "json",
				headers : {
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				success : function(returnData) {
					var message = returnData.message;
					if (message == "ok") {
						makeToast("위시리스트 저장되었습니다.");
					} else {
						makeToast("이미 저장된 장소");
					}
					getWish();
				}
			});
		}
	
		function deleteWish(k){
			
			$.ajax({
				url: "../wish/json/deleteWish/"+k,
				method: "POST",
				dataType: "json",
				success:function(returnData){
					getWish(k);
				}
			});	
		}

		function getWish(k) {

			$.ajax({
				url : "../wish/json/listWishByPost/" + k,
				dataType : "json",
				success : function(returnData) {
					$(".wishCount1").empty();
					$(".wishCount1").append(returnData.totalCount);

					$("span.wishIcon1").empty();

					if ( ((JSON.stringify(returnData.list)).indexOf("${loginUser.userId}") == -1) || ("${loginUser.userId}" == '') || ("${loginUser.userId}" == null) ) {
						$("span.wishIcon1").append('<span class="far fa-bookmark"></span>');
					} else {
						$("span.wishIcon1").append('<span class="fas fa-bookmark full"></span>');
					}
				}
			});
		}

		$(function() {

			$("a.wish1").bind("click", function(e) {
				if ($("span.wishIcon1 svg").attr("data-prefix") == 'fas') {
					deleteWish($("#dialog tr td input[name='postNo']").val());
				} else {
					if(${ loginUser != null }){
									
						//해당 컨텐츠아이디에 있는 여행지를 호출없으면 저장, 있으면 업데이트 카운트
						addTripToDB(contentid, contenttypeid);
						
						//위에서 저장한 것을 위시리스트에 재저장 
						addWish(contentid);
						//makeToast(");
				  	}else{
				  		makeToast("로그인 해주세요");
				  	}
				  	e.preventDefault();
				}

			});
		});

		/////////////////////

		//우리 디비에 데이터를 호출하고 저장할 때 호출
		function addTripToDB(contentid, contenttypeid) {
			$.ajax({
				url : "../trip/json/getTrip/" + contentid + "/" + contenttypeid
						+ "",
				method : "GET",
				dataType : "json",
				headers : {
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				success : function(data) {
					var trip = data.result;
					$("#dialog tr td input[name='postNo']").val(trip.postNo);
					getWish(trip.postNo);
				}
			})
		}

		var contenttypeid;
		var contentid;
		var postNo;
		$(function() {
			$(document).on("click", "img", function(e) {
				contentid = $($("input[name = 'contentid']")[$("img").index(this)]).val();
				contenttypeid = $($("input[name = 'contenttypeid']")[$("img").index(this)]).val();
				e.preventDefault();
				getTheme(contentid, contenttypeid);
				addTripToDB(contentid, contenttypeid);
			});
		});

		$(function() {
			$('#searchKeyword').keydown(
				function(key) {
					var data = $(this).val();
					if (key.keyCode == 13) {
						if (data != "") {
							$("form input[name='pageNo']").val(page);
							$('form').attr('method', 'POST').attr('action','../trip/listSearch').submit();
						} else {
							alert("검색어를 입력해주세요");
						}
					}
			});
			
			$("header h1").on("click", function(){
				self.location = "../trip/getTheme";
			});
		});

		///////////////////
	</script>
	<!-- 지도 생성하는 CDN 및 맵에 담을 내용 확인 -->
	
	  <script type="text/javascript">
	  function makeMap(){
		var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		var options = { //지도를 생성할 때 필요한 기본 옵션
		center: new daum.maps.LatLng(mapy,mapx), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
		
		};
	
		var map = new daum.maps.Map(container, options); //지도 생성 및 객체 리턴
	  
		var markerPosition  = new daum.maps.LatLng(mapy,mapx); 
	
		//마커를 생성
		var marker = new daum.maps.Marker({
		   position: markerPosition
		});
		
		//마커가 지도 위에 표시
		marker.setMap(map);
	  }
	</script>
<style>
	img {
		cursor: pointer;
		width:"282px";
		height:"187px";
	}
	#dialog{
		background-color: #ffffff;
		z-index: 1000;
	}
	
	.ui-dialog-titlebar{
		background-color: #ffffff;
	}
	
	.wrapper.style1{
		background-color: #e6f9ff;
	}
	
	.4u header{
		background-color:#fff 
	}
		
	#wish{
		background: #000000;
		padding: 4px;
	}
	
	td{
		padding: 4px;
	}
		
	article{
 		height: 400px; 
	}
	
	.carousel{
		background-color: #5d4f7166;
	}

	#keyword, #search{
		padding-bottom:10px;
		padding-top:10px;
	}
	#search{
		background: #605b7b;
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
	
	#logo{
		z-index: 10;
	}
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
		height: 430px;
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
	#header{
		padding: 6.5em 0 1.5em 0;
 	}
 	.wrapper{
 		padding-top: 0;
 	}
 	.row > * {
		padding: 24px 0 0 24px;
		margin: 0 !important;
	}	
	li > span{
		position: relative !important;
		color: #555555;
	}
	.block::before{
		color: #F05643 !important;
	}
	.full{
		color: #F05643 !important;
	}
	.wrapper{
		margin: 0;
	}
</style>

<title>여행지 찾기</title>
</head>
<body class="no-sidebar">

	<div id="page-wrapper">

		<div id="header">

			<div class="inner">
				<header>
					<h1>나들이 정보</h1>
				</header>
			</div>
			<jsp:include page="/layout/toolbar.jsp" />

		</div>
		
		<div class="wrapper style1">

			<section id="features" class="container special">
			
				<div class="continer">
	
					<div class="col-sm-12" style="padding-bottom: 40px;">
						<h6 style="color: #8a8c91; float: left; font-style: normal;">
							<c:if test="${search.searchKeyword != null}">
								'${search.searchKeyword}' 검색 결과 &nbsp;&nbsp;>> &nbsp; ${resultPage.totalCount}개 게시물
							</c:if>							
						</h6>
					</div>
	
					<div>
						<form class="search" style="float: right">
							<span class="fas fa-search"
								style="position: relative; margin-right: -40px;"></span> <input
								type="text" name="keyword" id="searchKeyword" value=""
								style="width: 100%" /> <input type="hidden" name="pageNo"
								value="" /> <input type="hidden" name="areaCode"
								value="${areaCode}" /> <input type="hidden" name="localName"
								value="${localName}" />
						</form>
					</div>
				</div>
				
				<div class="row">
					<c:forEach var ="list" items="${list}">
						<article class="4u 12u(mobile) special">
							<div style="background-color: white; height: 400px; padding: 10px 10px 0 10px; position: relative;">
								<input type='hidden' name='postNo' value='${list.trip.postNo}'/>
								<a href="#" class="image featured"><img src="${list.firstimage}" alt="" style="height: 245px;"/></a>
								<input type="hidden" name="contentid" value="${list.contentid}"/>
			          			<input type="hidden" name="contenttypeid" value="${list.contenttypeid}"/>
								<header style="text-align: center;">
									<h3><a href="#" name='title'>${list.title}</a></h3>
									<h3></h3>
								</header>
								<div style="text-align: right;">
									<p>${list.addr1}</p>
								</div>
								<div>
									<div class="icons" style="float: right;">
										<span class="wishIcon"></span>&nbsp;<span class="wishCount"></span>
									</div>
								</div> 
							</div>
						</article>
								
					</c:forEach>
				</div>
			</section>
		</div>
	</div>
				 		
	<div id="toastMessage" class='toastMessage' style='display:none;'>Toast</div>		
	
	<div id="dialog" style='display:none;'>
		<table class="table">
    		<tbody>
        		<tr>
        			<td colspan="2">
			        	<div id="map" style="width:100%;height:300px;"></div>
		        	</td>
        		</tr>
        		<tr>
   					<td id="titleName" style="font-size: 18pt;">
   						<input type="hidden" name="postNo" value=""/>
   					</td>       
   					<td align="right">
						<span>
						<a href="#none" class="wish1" style="font-size: 17pt;"><span class="wishIcon1"></span>&nbsp;<span class="wishCount1"></span></a>
						</span>
   					</td>   	
        		</tr>
        		<tr >
	        	 	<td colspan="2" id="address"></td>
        		</tr>
        		<tr>
					<td colspan="2" id="fee"></td>
          	    </tr>
        		<tr>
                  	<td colspan="2" id="description"></td>
          		</tr>
      		</tbody>
      		
    	</table>
    	
     	
	</div> 
	
		
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5a4ea92513a5052cd0e179704e1e5f5f"></script>					
</body>
</html>