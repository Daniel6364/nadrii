<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
	<head>
	
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">

	var xhr = new XMLHttpRequest();
	var url = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon'; /*URL*/
	var queryParams = '?' + encodeURIComponent('ServiceKey') + '='+'TXwTxp6UgYcwVcjkfbd9ADOmCH5gqN3dBN1uTJxIhpQ8yIKcZ9eQQzWkMrrFR2%2FsJoZAOrPvydYKOeK8G1VtQw%3D%3D'; /*Service Key*/
	queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10); /*한 페이지 결과 수*/
	queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1); /*현재 페이지 번호*/
	queryParams += '&' + encodeURIComponent('MobileOS') + '=' + encodeURIComponent('ETC); /*IOS(아이폰),AND(안드로이드),WIN(원도우폰),ETC*/
	queryParams += '&' + encodeURIComponent('MobileApp') + '=' + encodeURIComponent('AppTest); /*서비스명=어플명*/
	queryParams += '&' + encodeURIComponent('contentId') + '=' + encodeURIComponent('126508); /*콘텐츠 ID*/
	queryParams += '&' + encodeURIComponent('contentTypeId') + '=' + encodeURIComponent('); /*관광타입(관광지, 숙박 등) ID*/
	queryParams += '&' + encodeURIComponent('defaultYN') + '=' + encodeURIComponent('); /*기본정보 조회여부*/
	queryParams += '&' + encodeURIComponent('firstImageYN') + '=' + encodeURIComponent('Y); /*원본, 썸네일 대표이미지 조회여부*/
	queryParams += '&' + encodeURIComponent('areacodeYN') + '=' + encodeURIComponent('Y); /*지역코드, 시군구코드 조회여부*/
	queryParams += '&' + encodeURIComponent('catcodeYN') + '=' + encodeURIComponent('Y); /*서비스분류코드(대,중,소 코드) 조회여부*/
	queryParams += '&' + encodeURIComponent('addrinfoYN') + '=' + encodeURIComponent('Y); /*주소, 상세주소 조회여부*/
	queryParams += '&' + encodeURIComponent('mapinfoYN') + '=' + encodeURIComponent('Y); /*좌표 X,Y 조회여부*/
	queryParams += '&' + encodeURIComponent('overviewYN') + '=' + encodeURIComponent('Y); /*콘텐츠 개요 조회여부*/
	queryParams += '&' + encodeURIComponent('_type') + '=' + encodeURIComponent('json);
	xhr.open('GET', url + queryParams);
	xhr.onreadystatechange = function () {
	    if (this.readyState == 4) {
	        alert('Status: '+this.status+' Headers: '+JSON.stringify(this.getAllResponseHeaders())+' Body: '+this.responseText);
	    }
	};

	xhr.send('');
	
	
	</script>
		
		<title>Insert title here</title>
	</head>


	<body>
	
	${resultMsg}
	
		
	</body>
</html>