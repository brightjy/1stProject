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
<link href='${conPath }/css/qnaWrite.css' rel='stylesheet'>
<script src="${conPath }/ckeditor/ckeditor.js"></script>
<script>
$(document).ready(function(){
	CKEDITOR.replace('qContent');
	/* CKEDITOR.instances.pDiscount.getData(); */
});
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
<c:set var="dto" value="${qnaDto }"/>
<div id="qnaWrite_wrap">
	<div id="qnaWrite">
	<form action="${conPath }/qnaReply.do" method="post"> <!-- 답변 글은 파일 첨부 하지 않는다. -->
		<input type="hidden" name="aId" value="${admin.aId }"> <!-- 답변 글은 관리자만 달 수 있다. -->
		<!-- qnaReply.do 시 필요한 원글 정보 : qGroup, qStep, qIndent, pageNum -->
		<input type="hidden" name="qId" value="${dto.qId }">
		<input type="hidden" name="qGroup" value="${dto.qGroup }">
		<input type="hidden" name="qStep" value="${dto.qStep }">
		<input type="hidden" name="qIndent" value="${dto.qIndent }">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<table>
			<tr>
				<td class="title">
					카테고리
				</td>
				<td class="content">
					<input type="text" name="qCategory" value="문의답변" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td class="title">
					제목
				</td>
				<td class="content">
					<input type="text" name="qTitle" required="required" value="${dto.qTitle } [re] : ">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea name="qContent" id="qContent">${dto.qContent }</textarea>
					<script>
						CKEDITOR.replace('qContent');
					</script>
				</td>
			</tr>
			<tr>
				<td class="title">
					비밀번호
				</td>
				<td class="content">
					<input type="password" name="qPw" required="required" value="12345678" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="colspan">
					<input type="submit" value="Reply" class="btn">
					<input type="button" value="List" class="btn" onclick="location.href='${conPath}/qnaList.do?pageNum=${param.pageNum }'">
				</td>
			</tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>