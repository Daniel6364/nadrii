<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- Inner -->
<div class="inner">
	<header>
		<h1>
			N A D R I I
		</h1>
		<hr />
		<p>Check your precious holiday by NADRII</p>
	</header>
	<footer>
		<c:if test="${  empty loginUser }">	
		<a id="startButton" class="button circled scrolly">Start</a>
		</c:if>
		<c:if test="${ !empty loginUser }">	
		<a href="#carousel" class="button circled scrolly">Start</a>
		</c:if>		
	</footer>
</div>



