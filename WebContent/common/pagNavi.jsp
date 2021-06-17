<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.naviContainer{
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
  font-family: 'Open Sans', sans-serif;
  color: #222;
}

.naviContainer a{
  text-decoration: none;
}

.naviContainer p, .naviContainer li, .naviContainer a{
  font-size: 14px;
}

/* GRID */

.twelve { width: 100%; }
.eleven { width: 91.53%; }
.ten { width: 83.06%; }
.nine { width: 74.6%; }
.eight { width: 66.13%; }
.seven { width: 57.66%; }
.six { width: 49.2%; }
.five { width: 40.73%; }
.four { width: 32.26%; }
.three { width: 23.8%; }
.two { width: 15.33%; }
.one { width: 6.866%; }

/* COLUMNS */

.naviContainer .col {
	display: block;
	float:left;
	margin: 1% 0 1% 1.6%;
}

.naviContainer .col:first-of-type {
  margin-left: 0;
}

.naviContainer{
  width: 100%;
  max-width: 940px;
  margin: 0 auto;
  position: relative;
  text-align: center;
}

/* CLEARFIX */

.naviContainer .cf:before,
.naviContainer .cf:after {
    content: " ";
    display: table;
}

.naviContainer .cf:after {
    clear: both;
}

.naviContainer .cf {
    *zoom: 1;
}

/* GENERAL STYLES */

.naviContainer .pagination{
  padding: 30px 0;
}

.naviContainer .pagination ul{
  margin: 0;
  padding: 0;
  list-style-type: none;
}

.naviContainer .pagination a{
  display: inline-block;
  padding: 10px 18px;
  color: #222;
}

/* ONE */

.naviContainer .p1 a{
  width: 40px;
  height: 40px;
  line-height: 40px;
  padding: 0;
  text-align: center;
}

.naviContainer .p1 a.is-active{
  background-color: #2ecc71;
  border-radius: 100%;
  color: #fff;
}

</style>


<div class="naviContainer">

	<div class="pagination p1">
		  <!-- 크기조절 :  pagination-lg pagination-sm-->
		  <ul>
		    
		    <!--  <<== 좌측 nav -->
		  	<c:if test="${ resultPage.pageNo <= resultPage.pageUnit }">
		 		<li class="disabled">
			</c:if>
			<c:if test="${ resultPage.pageNo > resultPage.pageUnit }">
				<li>
			</c:if>
		      <a href="javascript:fncGetList('${ resultPage.pageNo-1}')" aria-label="Previous">
		        &laquo;
		      </a>
		    </li>
		    
		    <!--  중앙  -->
			<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
				
				<c:if test="${ resultPage.pageNo == i }">
					<!--  현재 page 가르킬경우 : active -->
				    <li class="active">
				    	<a href="javascript:fncGetList('${ i }');">${ i }<span class="sr-only">(current)</span></a>
				    </li>
				</c:if>	
				
				<c:if test="${ resultPage.pageNo != i}">	
					<li>
						<a href="javascript:fncGetList('${ i }');">${ i }</a>
					</li>
				</c:if>
			</c:forEach>
		    
		     <!--  우측 nav==>> -->
		     <c:if test="${ resultPage.endUnitPage >= resultPage.maxPage }">
		  		<li class="disabled">
			</c:if>
			<c:if test="${ resultPage.endUnitPage < resultPage.maxPage }">
				<li>
			</c:if>
		      <a href="javascript:fncGetList('${resultPage.endUnitPage+1}')" aria-label="Next">
		        &raquo;
		      </a>
		    </li>
		  </ul>
	</div>
</div>


