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
<link href='${conPath }/css/qnaContent.css' rel='stylesheet'>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	
});
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<div id="qnaContent_wrap">
		<div id="qnaContent">
			<c:set var="dto" value="${qnaContent }"/>
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
						Hit
					</td>
				</tr>
				<tr>
					<td class="id">
						${dto.qId }
					</td>
					<td class="title">
						${dto.qTitle }
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
						${dto.qIp }
					</td>
					<td class="date">
						<fmt:formatDate value="${dto.qDate }" pattern="YY-MM-dd(E)"/>
					</td>
					<td class="hit">
						${dto.qHit }
					</td>
				</tr>
				<tr>
					<td class="content" colspan="6">
						${dto.qContent }
					</td>
				</tr>
			</table>
			<div id="file">
				<table id="fileTable">
					<tr>
						<c:if test="${not empty dto.qFile1 }">
							<td	class="fileName">
								첨부파일(1)
							</td>
							<td	class="file">
								<p>${dto.qFile1 }</p>
							</td>
						</c:if>
					</tr>
					<tr>
						<c:if test="${not empty dto.qFile2 }">						
							<td	class="fileName">
								첨부파일(2)
							</td>
							<td	class="file">
								<p>${dto.qFile2 }</p>
							</td>
						</c:if>
					</tr>
					<tr>
						<td colspan="2" class="colspan">
							<button class="btn" onclick="location.href='${conPath}/qnaModifyView.do?qId=${dto.qId}&pageNum=${param.pageNum}'">Modify</button>
							<button class="btn" onclick="location.href='${conPath}/qnaList.do?pageNum=${param.pageNum}'">List</button>
							<c:if test="${not empty admin }">
								<button class="btn" onclick="location.href='${conPath}/qnaReplyView.do?qId=${dto.qId }&pageNum=${param.pageNum }'">Reply</button>
								<button class="btn" onclick="location.href='${conPath}/qnaDelete.do?qId=${dto.qId }&qPw=${dto.qPw }&pageNum=${param.pageNum }'">Delete</button>
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