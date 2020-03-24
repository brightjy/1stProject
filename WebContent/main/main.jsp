<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${conPath}/css/main.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#btn1").click(function(){
		$("#main_wrap").css({'background-image':'url(${conPath}/img/web_background1.jpg)',
			'background-repeat' : 'no-repeat', 'background-position':'center center'});
	});
	$("#btn2").click(function(){
		$("#main_wrap").css({'background-image':'url(${conPath}/img/web_background2.jpg)',
			'background-repeat' : 'no-repeat', 'background-position':'center center'});
	});
	$("#btn3").click(function(){
		$("#main_wrap").css({'background-image':'url(${conPath}/img/web_background.jpg)',
			'background-repeat' : 'no-repeat', 'background-position':'center center'});
	});
});
</script>
</head>
<body>
<jsp:include page="header.jsp"/>
<c:if test="${not empty memberLoginMsg }">
	<script>
		alert('${memberLoginMsg}');
/* 		location.href="${referer }"; */
	</script>
</c:if>
<c:if test="${not empty joinMemberResult }">
	<script>
		alert('${joinMemberResult}');
	</script>
</c:if>
<c:if test="${not empty deleteMemberResult }">
	<script>
		alert('${deleteMemberResult }');
	</script>
</c:if>
<c:if test="${not empty adminLoginMsg }">
	<script>
		alert('${adminLoginMsg}');
/* 		location.href="${referer }"; */
	</script>
</c:if>
<div id=wrap>
	<div id="main_wrap">
	</div>
	<div id="main_btns">
		<ul>
			<li id="btn1">
				●
			</li>
			<li id="btn2">
				●
			</li>
			<li id="btn3">
				●
			</li>
		</ul>
	</div>
</div>
<jsp:include page="../product/productListBest.jsp"/>
<jsp:include page="footer.jsp"/>
</body>
</html>