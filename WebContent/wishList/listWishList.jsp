<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Insert title here</title>

<link rel="stylesheet" href="../resources/helios/assets/css/main.css" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<style type="text/css">

#header{
	background-color: #ffffcd;
	background-image: url("/resources/images/tripIcon/tripInfo.jpg");
}

#nav{
	background-image: url("../resources/helios/images/header.jpg");	
	background-color: #44324a;
	opacity: 0.7;
	
}
.carousel{
	background-color: #ffffff;
}


.jbTable {
        display: table;
        width: 100%;
      }
.jbTableRow {
        display: table-row;
        
      }
.jbTableCell {
        display: table-cell;
      }
.jbText {
        width: 100%;
      }
.jbSubmit {
        width: 1%;
      }
.jbText input {
        width: 100%;
}

img{
	padding-left: 10px;
	padding-right: 10px; 
}

</style>
 
 
</head>

<body class="no-sidebar">

		<div id="page-wrapper">

			<div id="header">
					<header>
						<h1><a href="../index.jsp" id="logo">위시리스트</a></h1>
					</header>
			</div>

			<section class="carousel">
				<div class="reel" style="overflow: visible; transform: translate(0px, 0px);">
					<c:forEach var ="list" items="${list}">			
						<span>	
							${list.tripNo.tripName}
						</span>
					</c:forEach>		
				</div>
				<span class="forward" style="display: block;"></span>
				<span class="backward" style="display: block;"></span>
			</section>
			
			
			
			
		</div>
				
		
		<script src="../resources/helios/assets/js/jquery.min.js"></script>
		<script src="../resources/helios/assets/js/jquery.dropotron.min.js"></script>
		<script src="../resources/helios/assets/js/jquery.scrolly.min.js"></script>
		<script src="../resources/helios/assets/js/jquery.onvisible.min.js"></script>
		<script src="../resources/helios/assets/js/skel.min.js"></script>
		<script src="../resources/helios/assets/js/util.js"></script>
		<script src="../resources/helios/assets/js/main.js"></script>
		

</body>

</html>