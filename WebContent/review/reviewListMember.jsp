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
<link href='${conPath }/css/reviewList.css' rel='stylesheet'>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	
});
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<div id="reviewList_wrap">
		<div id="reviewList">
			<div class="subject">
				<span>&nbsp; REVIEW &nbsp;</span>
			</div>
			<div class="reviewList">
				<table>
					<c:if test="${reviewListMember.size() eq 0 }">
						<tr>
							<td colspan="6">
								작성된 글이 없습니다.
							</td>
						</tr>
					</c:if>
					<c:if test="${reviewListMember.size() != 0 }">
						<c:forEach var="dto" items="${reviewListMember }">
							<tr>
								<td id="reviewId">
									${dto.rId }
								</td>
								<td id="productImg">
									<div class="productImg">
										<a href="${conPath }/productContent.do?pId=${dto.pId}">										
											<img src="${conPath }/productImg/${dto.pImage1}" alt="상품사진">							
										</a>
									</div>
								</td>
								<td id="productName">
									<a href="${conPath }/productContent.do?pId=${dto.pId}">			
										${dto.pName }
									</a>
								</td>
								<td class="left" id="title">
									<c:forEach var="i" begin="1" end="${dto.rIndent }">
										<c:if test="${i != dto.rIndent }">
											&nbsp; &nbsp; &nbsp;
										</c:if>
										<c:if test="${i eq dto.rIndent }">
											└─
										</c:if>
									</c:forEach>
									<a href="${conPath }/reviewContent.do?rId=${dto.rId}&pageNum=${pageNum}">
										${dto.rTitle }								
									</a>								
								</td>
								<td id="writer">
									<c:if test="${not empty dto.aId }">
										${dto.aId }
									</c:if>
									<c:if test="${not empty dto.mId }">
										${dto.mId }
									</c:if>
								</td>
								<td id="date">
									<fmt:formatDate value="${dto.rDate }" type="date" pattern="YY-MM-dd(E)"/>								
								</td>
								<td id="hit">
									${dto.rHit }
								</td>
							</tr>	
						</c:forEach>								
					</c:if>
				</table>
				<div class="paging">
					<c:if test="${startPage > BLOCKSIZE }">
						<a href="${conPath }/reviewListMember.do?pageNum=${startPage-1}"> 〈  &nbsp; </a>
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<c:if test="${i eq pageNum }">
							<b> &nbsp; ${i }</b>
						</c:if>
						<c:if test="${i != pageNum }">
							<a href="${conPath }/reviewListMember.do?pageNum=${i }">&nbsp; ${i }</a>
						</c:if>
					</c:forEach>
					<c:if test="${endPage < pageCnt }">
						<a href="${conPath }/reviewListMember.do?pageNum=${endPage+1}">  &nbsp; 〉 </a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>