<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${conPath}/css/memberList.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){

});
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<div id="memberList_wrap">
		<div id="memberList">
			<div class="subject">
				<span>&nbsp; 회원 리스트  &nbsp;</span>
			</div>
			<table>
				<tr>
					<td class="title">
						아이디
					</td>
					<td class="title">
						이름
					</td>
					<td class="title">
						이메일
					</td>
					<td class="title">
						핸드폰
					</td>
					<td class="title">
						주소
					</td>
					<td class="title">
						생일
					</td>
					<td class="title">
						가입일
					</td>
					<td class="title">
						탈퇴여부
					</td>
					<td class="title">
						*
					</td>
				</tr>
				<c:if test="${memberList.size() eq 0 }">
				<tr>
					<td colspan="8">
						가입한 회원이 없습니다.
					</td>
				</tr>
				</c:if>
				<c:if test="${memberList.size() != 0 }">
					<c:forEach items="${memberList }" var="dto">
						<tr>
							<td>
								${dto.mId }
							</td>
							<td>
								${dto.mName }
							</td>
							<td>
								${dto.mEmail }
							</td>
							<td>
								${dto.mPhone }
							</td>
							<td>
								${dto.mAddress }
							</td>
							<td>
								${dto.mBirth }
							</td>
							<td>
								<fmt:formatDate value="${dto.mDate }" pattern="yy/MM/dd(E)"/>
							</td>
							<td>
								${dto.mDrop }
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<div class="paging">
					<c:if test="${startPage > BLOCKSIZE }">
						<a href="${conPath }/memberList.do?pageNum=${startPage-1}"> 〈  &nbsp; </a>
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<c:if test="${i eq pageNum }">
							<b> &nbsp; ${i }</b>
						</c:if>
						<c:if test="${i != pageNum }">
							<a href="${conPath }/memberList.do?pageNum=${i }">&nbsp; ${i }</a>
						</c:if>
					</c:forEach>
					<c:if test="${endPage < pageCnt }">
						<a href="${conPath }/memberList.do?pageNum=${endPage+1}">  &nbsp; 〉 </a>
					</c:if>
				</div>
		</div>
	</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>