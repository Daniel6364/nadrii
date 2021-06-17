<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<c:forEach var="list" items="${list}">
	<img src="${list.tripNo.thumbnailImageFile}" alt="" style="height: 250.42px;">
</c:forEach>

<script src="../resources/helios/assets/js/jquery.min.js"></script>
<script src="../resources/helios/assets/js/jquery.dropotron.min.js"></script>
<script src="../resources/helios/assets/js/jquery.scrolly.min.js"></script>
<script src="../resources/helios/assets/js/jquery.onvisible.min.js"></script>
<script src="../resources/helios/assets/js/skel.min.js"></script>
<script src="../resources/helios/assets/js/util.js"></script>
<script src="../resources/helios/assets/js/main.js"></script>
