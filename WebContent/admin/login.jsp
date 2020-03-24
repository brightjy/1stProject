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
	<div id="login_wrap">
		<div id="login">
			<div class="subject">
				<span>&nbsp; Admin LOGIN &nbsp;</span>
			</div>
			<form action="${conPath }/adminLogin.do" method="post">
				<table>
					<tr>
						<td>
							아이디 <input type="text" name="aId" value="${admin.aId }" required="required">
						</td>
					</tr>
					<tr>
						<td>
							비밀번호 <input type="password" name="aPw" required="required">
						</td>
					</tr>
				</table>
				<input type="submit" value="로그인" class="btn">
			</form>
		</div>
	</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>