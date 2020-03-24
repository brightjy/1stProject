<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product List</title>
<link href='${conPath }/css/productList.css' rel='stylesheet'>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	
});
</script>
</head>
<body>
<c:if test="${not empty productInsertMsg }">
	<script>
		alert('${productInsertMsg }');
	</script>
</c:if>
<jsp:include page="../main/header.jsp"/>
	<div id="productList_wrap">
		<div id="productList">
			<div class="subject">
				<span>&nbsp; NEW IN &nbsp;</span>
			</div>
			<c:if test="${not empty admin }">
				<div class="right">
					<a class="btn" href="${conPath}/productInsertView.do">
						상품등록
					</a>
				</div>
			</c:if>
				<table id="product_wrap">
					<tr>
						<c:set var="idx" value="0" scope="page"/>
						<c:forEach var="dto" items="${productList }">
							<td>
								<div class="productImg">
									<a href="${conPath }/productContent.do?pId=${dto.pId}&pageNum=${pageNum}">
										<img src="${conPath }/productImg/${dto.pImage1}" alt="상품사진">
									</a>
								</div>
								<div class="productName">
									<a href="${conPath }/productContent.do?pId=${dto.pId}&pageNum=${pageNum}">
										<b> ${dto.pName }</b>							
									</a>
								</div>
								<div class="productPrice">
									<fmt:formatNumber value="${dto.pPrice }" pattern="#,###,###"/>
								</div>
							<c:set var="idx" value="${idx+1 }"/>
							</td>
							<c:if test="${idx%3==0 }">
								</tr><tr>
							</c:if>
						</c:forEach>
					</tr>
				</table>
			<div class="paging">
				<c:if test="${startPage > BLOCKSIZE }">
					<a href="${conPath }/productList.do?pageNum=${startPage-1}"> 〈  &nbsp; </a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:if test="${i eq pageNum }">
						<b> &nbsp; ${i }</b>
					</c:if>
					<c:if test="${i != pageNum }">
						<a href="${conPath }/productList.do?pageNum=${i }">&nbsp; ${i }</a>
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="${conPath }/productList.do?pageNum=${endPage+1}">  &nbsp; 〉 </a>
				</c:if>
			</div>
		</div>
	</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>