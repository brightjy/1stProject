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
<link href="${conPath}/css/main.css" rel="stylesheet">
<script>
	$(document).ready(function(){

	});
</script>
</head>
<body>
<header>
	<div id="top_menu">
		<ul>
			<c:if test="${empty member && empty admin}">
			<!-- 회원도, 관리자도 로그인 하지 않은 경우(guest) -->
			<li>
				<a href="${conPath }/memberLoginView.do">
					Log in
				</a>
			</li>
			<li>
				<a href="${conPath }/memberJoinView.do">
					Join
				</a>
			</li>
			</c:if>
			<c:if test="${not empty member && empty admin }">
			<!-- 회원 로그인 한 경우 -->
			<li>
				<a href="${conPath }/cartList.do?mId=${member.mId}">
					Cart
				</a>
				<p id="cartMsg">
					&nbsp; &nbsp;
				</p>
			</li>
			<li>
				<a href="${conPath }/wishList.do?mId=${member.mId}">
					Wish List
				</a>
			</li>
			<li>
				<a href="${conPath }/myPageView.do">
					My Page
				</a>
			</li>
			<li>
				<a href="${conPath }/memberLogout.do">
					Log out
				</a>
			</li>
			<li>
				<p> 반갑습니다. ${member.mName } 고객님 </p>			
			</li>
			</c:if>
			<c:if test="${not empty admin && empty member }">
			<!-- 관리자 로그인 한 경우 -->
			<li>
				<a href="${conPath }/adminLogout.do">
					관리자모드 나가기
				</a>	
			</li>
			<li>
				<a href="${conPath }/qnaList.do">
					문의관리
				</a>
			</li>
			<li>
				<a href="${conPath }/reviewList.do">
					리뷰관리
				</a>
			</li>
			<li>
				<a href="${conPath }/memberList.do">
					회원관리
				</a>
			</li>
			<li>
				<a href="${conPath }/ordersListAdmin.do">
					주문관리
				</a>
			</li>
			<li>
				<a href="${conPath }/productList.do">
					상품관리
				</a>
			</li>
			</c:if>
		</ul>
	</div>
	<div id="global_menu">
		<ul>
			<li class="menu">
				<a href="${conPath }/productList.do">
					Store
				</a>
			</li>
			<li>
				<a href="${conPath }/productListBest.do">
					Best 30
				</a>				
			</li>
			<li class="menu">
				<a href="${conPath }/reviewList.do">
					Review
				</a>	
			</li>
			<li>
				<c:if test="${not empty member || not empty admin }">
					<a href="${conPath }/qnaList.do">
						QnA
					</a>
				</c:if>				
			</li>
			<li>
				<c:if test="${not empty member }">
					<a href="${conPath }/wishList.do">
						Wish List
					</a>
				</c:if>				
			</li>
			<li>
				<c:if test="${not empty member }">
					<a href="${conPath }/cartList.do">
						Cart
					</a>
				</c:if>				
			</li>
		</ul>
	</div>
	<div id="logo">
		<a href="index.jsp">
			/
		</a>
	</div>
</header>
</body>
</html>