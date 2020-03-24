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
<link href='${conPath }/css/reviewContent.css' rel='stylesheet'>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	
});
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<div id="reviewContent_wrap">
		<div id="reviewContent">
			<div id="product">
				<table>
					<tr>
						<th id="productImg">
							<div class="productImg">
								<c:set var="dto" value="${reviewContent }"></c:set>
								<a href="${conPath }/productContent.do?pId=${dto.pId}">										
									<img src="${conPath }/productImg/${dto.pImage1}" alt="상품사진">							
								</a>
							</div>
						</th>
						<td id="productInfo">
							${dto.pName }
							<button class="btn" onclick="location.href='${conPath }/productContent.do?pId=${dto.pId}'">상품 상세보기</button>
						</td>
					</tr>							
				</table>
			</div>
			<div id="review">
				<table>
					<tr>
						<td class="id">
							no.
						</td>
						<td class="title">
							Title
						</td>
						<td class="writer">
							Writer
						</td>
						<td class="ip">
							Ip
						</td>
						<td class="date">
							Date
						</td>
						<td class="hit">
							hit
						</td>
					</tr>
					<tr>
						<td class="id">
							${dto.rId }
						</td>
						<td class="title">
							${dto.rTitle }
						</td>
						<td class="writer">
							<c:if test="${not empty dto.mId }">
								${dto.mId }
							</c:if>
							<c:if test="${not empty dto.aId }">
								${dto.aId }
							</c:if>
						</td>
						<td class="ip">
							${dto.rIp }
						</td>
						<td class="date">
							<fmt:formatDate value="${dto.rDate }" pattern="YY-MM-dd(E)"/>
						</td>
						<td class="hit">
							${dto.rHit }
						</td>
					</tr>
					<tr>
						<td class="content" colspan="6">
							${dto.rContent }
						</td>
					</tr>
				</table>
			</div>
			<div id="file">
				<table>
					<tr>
						<c:if test="${not empty dto.rFile1 }">
							<td	class="fileName">
								첨부 이미지(1)
							</td>
							<td	class="file">
								<img src="${conPath }/reviewImg/${dto.rFile1 }" alt="첨부 이미지 1 ">
							</td>
						</c:if>
					</tr>
					<tr>
						<c:if test="${not empty dto.rFile2 }">						
							<td	class="fileName">
								첨부 이미지(2)
							</td>
							<td	class="file">
								<img src="${conPath }/reviewImg/${dto.rFile2 }" alt="첨부 이미지 2 ">
							</td>
						</c:if>
					</tr>
					<tr>
						<td colspan="2" class="colspan">
							<button class="btn" onclick="location.href='${conPath}/reviewModifyView.do?rId=${dto.rId}&pageNum=${param.pageNum}'">Modify</button>
							<button class="btn" onclick="location.href='${conPath}/reviewList.do?pageNum=${param.pageNum}'">List</button>
							<c:if test="${not empty admin }">
								<button class="btn" onclick="location.href='${conPath}/reviewReplyView.do?rId=${dto.rId }&pageNum=${param.pageNum}'">Reply</button>
							</c:if>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>