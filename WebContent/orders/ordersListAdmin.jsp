<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href='${conPath }/css/ordersList.css' rel='stylesheet'>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	
});
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<div id="ordersList_wrap">
		<div id="ordersList">
			<div class="subject">
				<span>&nbsp; 주문 내역  &nbsp;</span>
			</div>
			<table>
			<c:forEach var="dto" items="${ordersList}">
			<tr>
				<td id="oDate">
					${dto.oDate }
				</td>
				<td id="oId">
					${dto.oId }
				</td>
				<td id="mId">
					${dto.mId }
				</td>
				<td id="oName">
					${dto.oName }
				</td>
				<td id="oPhone">
					${dto.oPhone }
				</td>
				<td id="oAddress">
					${dto.oAddress }
				</td>
				<td id="oPayment">
					${dto.oPayment }
				</td>
				<td	id="reviewWrite">
					<button class="btn" onclick="location.href='${conPath }/ordersContent.do?oId=${dto.oId}'">상세보기 / 배송처리</button>
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="8"></td>
			</tr>
		</table>
			<div class="paging">
				<c:if test="${startPage > BLOCKSIZE }">
					<a href="${conPath }/ordersListAdmin.do?pageNum=${startPage-1}"> 〈  &nbsp; </a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i eq pageNum }">
						<b> &nbsp; ${i }</b>
					</c:if>
					<c:if test="${i != pageNum }">
						<a href="${conPath }/ordersListAdmin.do?pageNum=${i }">&nbsp; ${i }</a>
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="${conPath }/ordersListAdmin.do?pageNum=${endPage+1}">  &nbsp; 〉 </a>
				</c:if>
			</div>
		</div>
	</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>