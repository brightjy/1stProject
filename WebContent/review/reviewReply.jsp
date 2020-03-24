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
<link href='${conPath }/css/reviewReply.css' rel='stylesheet'>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="${conPath }/ckeditor/ckeditor.js"></script>
<script>
$(document).ready(function(){
	CKEDITOR.replace('pContent');
	CKEDITOR.instances.pDiscount.getData();
});
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
<div id="reviewWrite_wrap">
	<div id="reviewWrite">
	<c:set var="dto" value="${reviewReplyView }"/>
		<form action="${conPath }/reviewReply.do" method="post">
		<input type="hidden" name="aId" value="${admin.aId }">
		<input type="hidden" name="rId" value="${dto.rId }">
		<input type="hidden" name="oId" value="${dto.oId }">
		<input type="hidden" name="pId" value="${dto.pId }">
		<input type="hidden" name="rGroup" value="${dto.rGroup }">
		<input type="hidden" name="rStep" value="${dto.rStep }">
		<input type="hidden" name="rIndent" value="${dto.rIndent }">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<table>
			<tr>
				<td class="title">
					상품명
				</td>
				<td class="content">
					<input type="text" name="pName" value="${dto.pName }" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td class="title">
					제목
				</td>
				<td class="content">
					<input type="text" name="rTitle" required="required" value="${dto.rTitle } [re] : ">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea name="rContent" id="rContent">${dto.rContent }</textarea>
					<script>
						CKEDITOR.replace('rContent');
					</script>
				</td>
			</tr>
			<tr>
				<td class="title">
					비밀번호
				</td>
				<td class="content">
					<input type="password" name="rPw" required="required" value="12345678" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="colspan">
					<input type="submit" value="Reply" class="btn">
					<input type="button" value="List" class="btn" onclick="history.back()">
				</td>
			</tr>
		</table>
		</form>
	</div>
</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>