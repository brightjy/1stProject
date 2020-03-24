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
<link href='${conPath }/css/tempPwView.css' rel='stylesheet'>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	
});
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
<div id="tempPwView_wrap">
		<div id="tempPwView">
			<form action="${conPath }/memberTempPw.do" method="post">
				<table>
					<tr>
						<th>
							임시 비밀번호 생성
						</th>
					</tr>
					<tr>
						<td class="info">
							<p>
								 아이디와 가입 시 입력한 이름,이메일 주소를 입력해주세요. <br><br>
								정보가 일치하면, 아래에 임시 비밀번호가 발급됩니다.
							</p>
						</td>
					</tr>
					<tr>
						<td>
							아이디 <input type="text" id="mId" name="mId" required="required" placeholder="영문소문자/숫자 4~12자">
						</td>
					</tr>
					<tr>
						<td>
							이름 <input type="text" id="mName" name="mName" required="required">
						</td>
					</tr>
					<tr>
						<td>
							이메일<input type="email" id="mEmail" name="mEmail" required="required" placeholder="예) abcd@efgh.com">
						</td>
					</tr>
				</table>
				<c:if test="${empty tempPw }">
					<input type="submit" value="임시 비밀번호 발급" class="btn">				
				</c:if>
				<c:if test="${not empty tempPw }">
					<input type="button" value="로그인" class="btn" onclick="location.href='${conPath}/memberLoginView.do'">
				</c:if>
				<c:if test="${not empty tempPw }">
						<p id="tempPw">임시 비밀번호 : ${tempPw }</p>
				</c:if>
				<c:if test="${not empty tempPwError }">
						<p>${tempPwError }</p>
				</c:if>
			</form>
		</div>
	</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>