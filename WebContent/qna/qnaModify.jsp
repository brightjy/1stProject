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
<script>
$(document).ready(function(){
	
});
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<c:if test="${not empty member }">
		<input type="hidden" name="mId" value="${member.mId }">
	</c:if>
	<c:if test="${not empty admin }">
		<input type="hidden" name="aId" value="${admin.aId }">
	</c:if>
	<c:set var="dto" value="${qnaModifyView }" />
<div id="qnaWrite_wrap">
	<div id="qnaWrite">
		<form action="${conPath }/qnaModify.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="qId" value="${dto.qId }">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="dbqFile1" value="${dto.qFile1 }">
		<input type="hidden" name="dbqFile2" value="${dto.qFile2 }">
		<table>
			<tr>
				<td class="title">
					카테고리
				</td>
				<td class="content">
					<select name="qCategory" id="qCategory"
						style="background-color:lightgrey"
						onFocus="this.initialSelect = this.selectedIndex;"
						onChange="this.selectedIndex = this.initialSelect;">
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
					<input type="text" name="qTitle" value="${dto.qTitle }" required="required">
				</td>
			</tr>
			<tr>
				<td	class="title">
					내용
				</td>
				<td class="content">
					<textarea name="qContent">${dto.qContent }</textarea>
				</td>
			</tr>
			<tr>
				<td class="title">
					파일첨부(1)
				</td>
				<td class="content">
					<input type="file" name="qFile1" >
					<c:if test="${not empty dto.qFile1 }">
						<p>기존 첨부 파일 : ${dto.qFile1}</p>
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="title">
					파일첨부(2)
				</td>
				<td class="content">
					<input type="file" name="qFile2">
					<c:if test="${not empty dto.qFile2 }">
						<p>기존 첨부 파일 : ${dto.qFile2}</p>
					</c:if>
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
					<input type="submit" value="Modify" class="btn">
					<input type="button" value="List" class="btn" onclick="location.href='${conPath}/qnaList.do?pageNum=${param.pageNum }'">
					<input type="button" value="Delete" class="btn" onclick="location.href='${conPath}/qnaDelete.do?qId=${dto.qId }&qPw=${dto.qPw }'">
				</td>
			</tr>
		</table>
		</form>
	</div>
</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>