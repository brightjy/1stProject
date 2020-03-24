<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My page</title>
<link href='${conPath }/css/mypageView.css' rel='stylesheet'>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#submit").click(function(){
		if(confirm("정말 탈퇴하시겠습니까?")==true){
			document.submit();
		}else{
			return false;
		}
	});
});
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<div id="myPage_wrap">
		<div id="myPage">
			<div class="subject">
				<span>&nbsp; My Page &nbsp;</span>
			</div>
			<table>
				<tr>
					<td>
						<a href="${conPath }/memberModifyView.do">
							&nbsp;정보수정&nbsp;
						</a>
					</td>
					<td>
						<a href="${conPath }/wishList.do?mId=${member.mId}">
							&nbsp;위시리스트&nbsp;
						</a>
					</td>
					<td>
						<a href="${conPath }/ordersList.do">
							&nbsp;주문내역&nbsp;
						</a>
					</td>
				</tr>
				<tr>
					<td>
						<a href="${conPath }/reviewListMember.do?mId=${member.mId}">
							&nbsp;리뷰관리&nbsp;
						</a>
					</td>
					<td>
						<a href="${conPath }/qnaListMember.do?mId=${member.mId}">
							&nbsp;문의관리&nbsp;
						</a>
					</td>
					<td>
						<form action="${conPath }/memberDelete.do">
							<input type="hidden" name="mId" value="${member.mId }">
							<input type="submit" value="회원탈퇴" id="submit">
						</form>
					</td>
				</tr>
			</table>
		</div>
	</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>