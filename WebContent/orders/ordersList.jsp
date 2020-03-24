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
					${dto.oId } <br>
					<a href="${conPath }/ordersContent.do?oId=${dto.oId}">				
						상세보기 >
					</a>
				</td>
				<td id="img">
					<a href="${conPath }/productContent.do?pId=${dto.pId}">										
						<img src="${conPath }/productImg/${dto.pImage1}" alt="상품사진">							
					</a>
				</td>
				<td id="pName" class="left">
					<a href="${conPath }/productContent.do?pId=${dto.pId}">			
						${dto.pName }
					</a>
				</td>
				<td id="pPrice">
					<fmt:formatNumber value="${dto.pPrice }" pattern="#,###,###" />원				
				</td>
				<td id="pAmount">
					${dto.odAmount }개
				</td>
				<td id="odTotsum">
					<fmt:formatNumber value="${dto.odTotsum }" pattern="#,###,###" />원						
				</td>
				<td	id="reviewWrite">
					<button class="btn" onclick="location.href='${conPath }/ordersContent.do?oId=${dto.oId}'">상세보기</button>
					<br>
					<button class="btn" onclick="location.href='${conPath }/productContent.do?pId=${dto.pId}'">재구매</button>
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="8"></td>
			</tr>
		</table>
			<div class="paging">
				<c:if test="${startPage > BLOCKSIZE }">
					<a href="${conPath }/ordersList.do?pageNum=${startPage-1}"> 〈  &nbsp; </a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i eq pageNum }">
						<b> &nbsp; ${i }</b>
					</c:if>
					<c:if test="${i != pageNum }">
						<a href="${conPath }/ordersList.do?pageNum=${i }">&nbsp; ${i }</a>
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="${conPath }/ordersList.do?pageNum=${endPage+1}">  &nbsp; 〉 </a>
				</c:if>
			</div>
		</div>
	</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>