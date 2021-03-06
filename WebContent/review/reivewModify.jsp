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
<link href="${conPath}/css/reviewWrite.css" rel="stylesheet">
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
	<c:set var="dto" value="${reviewModifyView }"/>
		<form action="${conPath }/reviewModify.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="rId" value="${dto.rId }">
		<input type="hidden" name="oId" value="${dto.oId }">
		<input type="hidden" name="pId" value="${dto.pId }">
		<input type="hidden" name="dbrFile1" value="${dto.rFile1 }">
		<input type="hidden" name="dbrFile2" value="${dto.rFile2 }">		
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
					<input type="text" name="rTitle" value="${dto.rTitle }" required="required">
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
					파일첨부(1)
				</td>
				<td class="content">
					<input type="file" name="rFile1">
					<c:if test="${not empty dto.rFile1 }">
						<p>기존 첨부 파일 : ${dto.rFile1}</p>
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="title">
					파일첨부(2)
				</td>
				<td class="content">
					<input type="file" name="rFile2">
					<c:if test="${not empty dto.rFile2 }">
						<p>기존 첨부 파일 : ${dto.rFile2}</p>
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="title">
					비밀번호
				</td>
				<td class="content">
					<input type="password" name="rPw" required="required">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="colspan">
					<input type="submit" value="Modify" class="btn">
					<input type="button" value="List" class="btn" onclick="location.href='${conPath}/reviewList.do?pageNum=${param.pageNum }'">	
					<input type="button" value="Delete" class="btn" onclick="location.href='${conPath}/reviewDelete.do?rId=${dto.rId }&rPw=${dto.rPw }'">
				</td>
			</tr>
		</table>
		</form>
	</div>
</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>