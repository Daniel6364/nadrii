<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="../resources/helios/assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="../resources/helios/assets/css/bootstrap.min.css" />	
<link rel="stylesheet" href="../resources/helios/assets/css/main.css" />
<!--[if lte IE 8]><link rel="stylesheet" href="../resources/helios/assets/css/ie8.css" /><![endif]-->
		
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../resources/helios/assets/js/jquery.min.js"></script>
<script src="../resources/helios/assets/js/jquery.dropotron.min.js"></script>
<script src="../resources/helios/assets/js/jquery.scrolly.min.js"></script>
<script src="../resources/helios/assets/js/jquery.onvisible.min.js"></script>
<script src="../resources/helios/assets/js/skel.min.js"></script>
<script src="../resources/helios/assets/js/util.js"></script>
<!--[if lte IE 8]><script src="../resources/helios/assets/js/ie/respond.min.js"></script><![endif]-->
<script src="../resources/helios/assets/js/main.js"></script>

				
<script type="text/javascript">
	$(function() {
		$('a[name="submit"]').bind('click', function() {
			$('form[name="postData"]').attr("action", "addGroup").attr("method", "POST").submit();
		});
		
		$("header h1").on("click", function(){
			self.location = "../group/listGroup";
		});
	});
</script>
<style type="text/css">
input:focus{
	outline:none;
}
select:focus{
	outline:none;
}
button[data-original-title='More Color']{
	width: 20px !important;
}
.panel-heading{
	text-align: center !important;
}
.wrapper{
	margin: 0;
	padding: 6em 0 10em 0;
}

@media screen and (max-width: 736px){
	.wrapper {
	    padding: 2em 0 4em 0;
	}
}

</style>

</head>
<body class="no-sidebar">
	<div id="page-wrapper">
		<!-- Header -->
		<div id="header">
			<!-- Inner -->
			<div class="inner">
				<header>
				<h1>나들이 모임</h1>
				</header>
			</div>
			<jsp:include page="../layout/toolbar.jsp" />
		</div>

		<div class="wrapper style1">

			<div class="container">
				<article id="main" class="special">
					<form name="postData">
						<div>
							<span>
								<input type="text" class="form" style="font-size: 17pt; width: 100%; padding: 10px; " name="title" value="" placeholder="제목" />
							</span>
						</div>					
						<div style="padding-top: 10px">
							<jsp:include page="noteEditor.jsp"></jsp:include>
						</div>
					</form>
					<div style="text-align: center;">	
						<a href="#" class="button" name="submit" style="width: 20% !important; font-size: 0.8em;">save</a>
					</div>
				</article>
			</div>
		</div>
	</div>
</body>
</html>