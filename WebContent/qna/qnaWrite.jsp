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
<link href='${conPath }/css/qnaWrite.css' rel='stylesheet'>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="${conPath }/ckeditor/ckeditor.js"></script>
<script>
$(document).ready(function(){
	CKEDITOR.replace('qContent');
	CKEDITOR.instances.pDiscount.getData();
});
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
<div id="qnaWrite_wrap">
	<div id="qnaWrite">
		<form action="${conPath }/qnaWrite.do" method="post" enctype="multipart/form-data">
		<c:if test="${not empty member }">
			<input type="hidden" name="mId" value="${member.mId }">
		</c:if>
		<c:if test="${not empty admin }">
			<input type="hidden" name="aId" value="${admin.aId }">
		</c:if>
		<table>
			<tr>
				<td class="title">
					카테고리
				</td>
				<td class="content">
					<select name="qCategory">
						<option value="상품문의">상품문의</option>
						<option value="배송문의">배송문의</option>
						<option value="기타문의">기타문의</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="title">
					제목
				</td>
				<td class="content">
					<input type="text" name="qTitle" required="required" autofocus="autofocus">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea name="qContent" id="qContent"></textarea>
					<script>
						CKEDITOR.replace('qContent');
					</script>
				</td>
			</tr>
			<tr>
				<td class="title">
					파일첨부(1)
				</td>
				<td class="content">
					<input type="file" name="qFile1">
				</td>
			</tr>
			<tr>
				<td class="title">
					파일첨부(2)
				</td>
				<td class="content">
					<input type="file" name="qFile2">
				</td>
			</tr>
			<tr>
				<td class="title">
					비밀번호
				</td>
				<td class="content">
					<input type="password" name="qPw" required="required">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="colspan">
					<input type="submit" value="Write" class="btn">
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