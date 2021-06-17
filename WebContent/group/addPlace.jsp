<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Daum 지도 시작하기</title>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<!--[if lte IE 8]><script src="../resources/helios/assets/js/ie/html5shiv.js"></script><![endif]-->
	<link rel="stylesheet" href="../resources/helios/assets/css/bootstrap.min.css" />	
	<link rel="stylesheet" href="../resources/helios/assets/css/main.css" />
	<!--[if lte IE 8]><link rel="stylesheet" href="../resources/helios/assets/css/ie8.css" /><![endif]-->
			
	<script src="../resources/helios/assets/js/jquery.min.js"></script>
	<script src="../resources/helios/assets/js/jquery.dropotron.min.js"></script>
	<script src="../resources/helios/assets/js/jquery.scrolly.min.js"></script>
	<script src="../resources/helios/assets/js/jquery.onvisible.min.js"></script>
	<script src="../resources/helios/assets/js/skel.min.js"></script>
	<script src="../resources/helios/assets/js/util.js"></script>
	<!--[if lte IE 8]><script src="../resources/helios/assets/js/ie/respond.min.js"></script><![endif]-->
	<script src="../resources/helios/assets/js/main.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
	<script type="text/javascript">
		var x='';
		var y='';
		function getSearchResult(keyword, page){
				$('#result').empty();
				$.ajax({
					url : "https://dapi.kakao.com/v2/local/search/keyword.json",
					method : "GET", 
					dataType : "json", 
					data : {
						"query" : keyword,
						"page" : page,
						"size" : 4
					},
					headers : {
						"Accept" : "application/json",
						"Content-Type" : "application/json",
						"Authorization": "KakaoAK 4c6e53f28fe567efc82e2e871436b51a"
						
					},
					success : function(JSONData, status) {
						var documents = JSONData.documents;
						if(documents.length == 0){
							alert("검색 결과가 없습니다.");
							return;
						}
						for( i=0;i<documents.length;i++){
							var displayValue = "<div class=\"result\" style=\"padding: 30px 0 0 25px;\">"+
												"<h5 style=\"font-weight: bold;\">"+documents[i].place_name+"</h5>"+
												"<h6>지번 "+documents[i].address_name+"</h6>"+
												"<h6>도로명 "+documents[i].road_address_name+"</h6>"+
												"</div>";
							if(i==documents.length-1){
								displayValue += "<nav aria-label=\"...\">"
												+"<ul class=\"pager\">";
								var olderPage = page-1;
								var newerPage = page+1;
								if(olderPage>0){
									displayValue += "<li class=\"previous\">"
													+"<a href=\"javascript:getSearchResult(\'"+keyword+"\', "+olderPage+")\"><span aria-hidden=\"true\">&larr;</span>이전으로</a></li>"
								}

								if(((JSONData.meta.total_count%4!=0)&&(newerPage<JSONData.meta.total_count/4+1)) || ((JSONData.meta.total_count%4 == 0) && (newerPage<(JSONData.meta.total_count/4)) ) ){


									displayValue+= "<li class=\"next\"><a href=\"javascript:getSearchResult(\'"+keyword+"\', "+newerPage+")\">다음으로<span aria-hidden=\"true\">&rarr;</span></a></li>";
								}
								  displayValue += "</ul></nav>";
							}
							$('div#result').append(displayValue);
						}
						
						$('div.result').bind('click', function() {
							var index = $(this).index();
							x = ""+documents[index].x;
							y = ""+documents[index].y;
							map.setCenter(new daum.maps.LatLng(y, x))
							$("#x").val(x);
							$("#y").val(y);
							marker.setMap(null);
							marker = new daum.maps.Marker({ 
							    // 지도 중심좌표에 마커를 생성합니다 
							    position: map.getCenter(),
							}); 
							marker.setMap(map);
							searchDetailAddrFromCoords(marker.getPosition(), function(result, status) {
						        if (status === daum.maps.services.Status.OK) {
						            var detailAddr = '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
						            var buildingName ='<div class="buildingName"><h3>' + documents[index].place_name + '</h3></div>';
						            
						            var content = '<div class="bAddr">' +
						            				buildingName+
						                            detailAddr + 
						                        '</div>';
						            $('#content').val(content);
						            
						            $('#placeName').val(documents[index].place_name);
					    		    $('#placeDetail').val(result[0].address.address_name);

						            var resultDiv = document.getElementById('clickLatlng'); 
								    resultDiv.innerHTML = content;
						        }   
						    });
						})
					}
				})
		}
		$(function() {
			$('button.btn.btn-default:contains("검색")').bind('click', function() {
				var keyword = $('#keyword').val(); 
				getSearchResult(keyword, 1);
			})
		})
	</script>
	<style>
	body {
		background-color: #ffffff;
	}
	</style>
</head>
<body>
	<div class="container">
		<br/>
		<div class="col-sm-5">
			<form class="navbar-form navbar-left" role="search">
				<div class="col-sm-10">
					<input id="keyword" type="text" class="form-control" style="font-size: 12pt" placeholder="키워드 입력">
				</div>
				<div class="col-sm-2">
					<button type="button" class="btn btn-default">검색</button>

				</div> 
			</form>
			<div id="result" style="padding-top: 50px;">
			</div>
		</div>
		<div class="col-sm-7">
			<div align="center">
			  	<div id="map" style="width:360px;height:430px;"></div>
				<div id="clickLatlng"></div>
				<div align="right" style="padding-right: 30px;">
					<br>
					<button type="button" class="button addButton">추가하기</button>
				</div>
			</div> 
		</div>
	</div>

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=28f2a580a6a043772106fd68ca5e3561&libraries=services"></script>
	<script type="text/javascript" src="/resources/js/map_2.js"></script>
	<script>
		var geocoder = new daum.maps.services.Geocoder();
		// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다

		function searchAddrFromCoords(coords, callback) {
		    // 좌표로 행정동 주소 정보를 요청합니다
		    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
		}

		function searchDetailAddrFromCoords(coords, callback) {
		    // 좌표로 법정동 상세 주소 정보를 요청합니다
		    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
		}

		// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
		function displayCenterInfo(result, status) {
		    if (status === daum.maps.services.Status.OK) {
		        var infoDiv = document.getElementById('centerAddr');
		    }    
		}
		
		$(function() {
			$('button.button.addButton:contains("추가하기")').bind('click', function() {
				$('#lat', opener.document).val($("#x").val());
				$('#lng', opener.document).val($("#y").val());
				$('#content_pr', opener.document).val($("#content").val());
				$('#placeName_pr', opener.document).val($("#placeName").val());
				$('#placeDetail_pr', opener.document).val($("#placeDetail").val());

				window.close();
			})
		})

	</script>
	<input type="hidden" id="content">
	<input type="hidden" id="x">
	<input type="hidden" id="y">
	<input type="hidden" id="placeName">
	<input type="hidden" id="placeDetail">
</body>
</html>