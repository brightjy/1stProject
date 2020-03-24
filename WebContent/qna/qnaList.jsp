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
<link href='${conPath }/css/qnaList.css' rel='stylesheet'>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	
});
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
<c:if test="${not empty qnaWriteResultMsg }">
<script>
		alert('${qnaWriteResultMsg}');
	</script>
</c:if>
	<div id="qnaList_wrap">
		<div id="qnaList">
			<div class="subject">
				<span>&nbsp; QnA &nbsp;</span>
			</div>
			<div class="right">
				<a class="btn" href="${conPath}/qnaWriteView.do">
					Write
				</a>
			</div>
			<div class="qnaList">
				<table>
					<c:if test="${qnaList.size() eq 0 }">
						<tr>
							<td colspan="6">
								작성된 글이 없습니다.
							</td>
						</tr>
					</c:if>
					<c:if test="${qnaList.size() != 0 }">
						<c:forEach var="dto" items="${qnaList }">
							<tr>
								<td id="qnaId">
									${dto.qId }
								</td>
								<td id="qCateogory">
									${dto.qCategory }
								</td>
								<td class="left" id="title">
									<c:forEach var="i" begin="1" end="${dto.qIndent }">
										<c:if test="${i != dto.qIndent }">
											&nbsp; &nbsp; &nbsp;
										</c:if>
										<c:if test="${i eq dto.qIndent }">
											└─
										</c:if>
									</c:forEach>
									<a href="${conPath }/qnaContent.do?qId=${dto.qId}&pageNum=${pageNum}">
										${dto.qTitle }								
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
									<fmt:formatDate value="${dto.qDate }" type="date" pattern="YY-MM-dd(E)"/>								
								</td>
								<td id="hit">
									${dto.qHit }
								</td>
							</tr>	
						</c:forEach>								
					</c:if>
				</table>
				<div class="paging">
					<c:if test="${startPage > BLOCKSIZE }">
						<a href="${conPath }/qnaList.do?pageNum=${startPage-1}"> 〈  &nbsp; </a>
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<c:if test="${i eq pageNum }">
							<b> &nbsp; ${i }</b>
						</c:if>
						<c:if test="${i != pageNum }">
							<a href="${conPath }/qnaList.do?pageNum=${i }">&nbsp; ${i }</a>
						</c:if>
					</c:forEach>
					<c:if test="${endPage < pageCnt }">
						<a href="${conPath }/qnaList.do?pageNum=${endPage+1}">  &nbsp; 〉 </a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>