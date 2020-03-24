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
<link href="${conPath}/css/login.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../main/header.jsp"/>
<c:if test="${not empty joinMemberResult }">
	<script>
		alert('${joinMemberResult}');
	</script>
</c:if>
	<div id="login_wrap">
		<div id="login">
			<div class="subject">
				<span>&nbsp; LOGIN &nbsp;</span>
			</div>
			<form action="${conPath }/memberLogin.do" method="post">
				<table>
					<tr>
						<td>
							아이디 <input type="text" name="mId" value="${mId }" required="required">
						</td>
					</tr>
					<tr>
						<td>
							비밀번호 <input type="password" name="mPw" required="required">
						</td>
					</tr>
				</table>
				<input type="submit" value="로그인" class="btn">
				<input type="button" value="회원가입" class="btn" onclick="location.href='${conPath}/memberJoinView.do'">
				<input type="button" value="비밀번호를 잊으셨나요?" class="btn" onclick="location.href='${conPath}/memberTempPwView.do'">
			</form>
		</div>
	</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>