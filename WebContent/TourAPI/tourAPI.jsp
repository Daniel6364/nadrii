
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.1.4.min.js"></script>

<script type="text/javascript">
	$(function() {
		$("#thumbnail-label").on("click", function() {
			alert($("div.row").html());
		});
	});

	$(window).scroll(
			function() {
				if ($(window).scrollTop() == $(document).height()
						- $(window).height()) {
					//var dpValue =  
					$(".row").append();

				}
			});
</script>


<title>박물관찾기</title>
</head>
<body>

	<div class="container">

		<div class="bs-example"
			data-example-id="thumbnails-with-custom-content">
			<div class="row">

				<input type="hidden" name="pageNo" value="${pageNo}" />
				<c:set var="i" value="0" />
				<c:forEach var="product" items="${museum}">

					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<img data-src="holder.js/100%x200" alt="100%x200"
								src="${museum.get(i).firstimage2 }" data-holder-rendered="true"
								style="height: 200px; width: 100%; display: block;">
							<div class="caption">
								<h3 id="thumbnail-label">${museum.get(i).title}<a
										class="anchorjs-link" href="#thumbnail-label"><span
										class="anchorjs-icon"></span></a>
								</h3>
								<p>${museum.get(i).addr1}</p>
								<input type="hidden" name="pageNo"
									value="${museum.get(i).contentid}" />
								<p>
									<a href="#" class="btn btn-primary" role="button">공유</a> <a
										href="#" class="btn btn-default" role="button">좋아요</a> <a
										href="#" class="btn btn-danger" role="button">위시리스트</a>
								</p>
							</div>
						</div>
					</div>
					<c:set var="i" value="${i+1}">
					</c:set>
				</c:forEach>

			</div>
		</div>
	</div>


</body>
</html>