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
<link href='${conPath }/css/ordersView.css' rel='stylesheet'>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#btn").click(function(){
		var oName = $('#mName').val();
		var oPhone = $('#mPhone').val();
		var oAddress = $('#mAddress').val();
		$('#oName').val(oName);
		$('#oPhone').val(oPhone);
		$('#oAddress').val(oAddress);
	});
	
	$("#submit").click(function(){
		var oName = $('#oName').val();
		var oPhone = $("#oPhone").val();
		var oAddress = $("#oAddress").val();
		if(!oName|| !oPhone || !oAddress){
			alert('배송지 정보를 입력해주세요.');
			return false;
		}
	});
	
});
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<div id="ordersView_wrap">
		<div id="ordersView">
			<div class="subject">
				<span>&nbsp; 주문 상품  &nbsp;</span>
			</div>
			<form action="${conPath }/orders.do">
				<table id="ordersList">
					<c:set var="sum" value="0"/>
					<c:forEach var="dto" items="${ordersView }">
						<tr>
							<td id="productImg">
								<div class="productImg">
									<a href="${conPath }/productContent.do?pId=${dto.pId}">										
										<img src="${conPath }/productImg/${dto.pImage1}" alt="상품사진">							
									</a>
								</div>
								<input type="hidden" name="pId" value="${dto.pId }">
								<input type="hidden" name="cId" value="${dto.cId }">
							</td>
							<td id="productName" class="left">
								<a href="${conPath }/productContent.do?pId=${dto.pId}">			
									${dto.pName }
								</a>
							</td>
							<td id="productPrice">
								<input type="text" id="pPrice" name="pPrice" value="${dto.pPrice }" readonly="readonly">
							</td>
							<td id="productAmount">
								<input type="text" id="cAmount" name="odAmount" value="${dto.cAmount }">
							</td>
							<td id="sum">
								<input type="text" name="cTotsum" id="cTotsum" value="${dto.cTotsum }">
								<c:set var="sum" value="${sum+dto.cTotsum }"/>
							</td>
						</tr>
					</c:forEach>
									
				</table>
				<div class="subject">
					<span>&nbsp; 주문고객 / 배송지 정보  &nbsp;</span>
				</div>
				<table id="ordersInfo">
					<tr>
						<td rowspan="3" class="title">
							주문하시는 분
						</td>
						<td class="subtitle">
							이름
						</td>
						<td class="content">
							<input type="text" id="mName" name="mName" value="${member.mName }">
						</td>
					</tr>
					<tr>
						<td class="subtitle">
							휴대폰
						</td>
						<td class="content">
							<input type="text" id="mPhone" name="mPhone" value="${member.mPhone }">
						</td>
					</tr>
					<tr>
						<td class="subtitle">
							주소
						</td>
						<td class="content">
							<input type="text" id="mAddress" name="mAddress" value="${member.mAddress }">
						</td>
					</tr>
					<tr>
						<td colspan="3" id="middleBtn">
							<input type="button" id="btn" class="largeBtn" value="주문자 정보와 동일 적용">
						</td>
					</tr>
					<tr>
						<td rowspan="3" class="title">
							받으시는 분
						</td>
						<td class="subtitle">
							이름
						</td>
						<td class="content">
							<input type="text" id="oName" name="oName">
						</td>
					</tr>
					<tr>
						<td class="subtitle">
							휴대폰
						</td>
						<td class="content">
							<input type="text" id="oPhone" name="oPhone">
						</td>
					</tr>
					<tr>
						<td class="subtitle">
							주소
						</td>
						<td class="content">
							<input type="text" id="oAddress" name="oAddress">
						</td>
					</tr>
				</table>
				<div class="subject">
					<span>&nbsp; 결제정보 선택  &nbsp;</span>
				</div>
				<table id="payment">
					<tr>
						<td>
							<input type="radio" name="oPayment" value="계좌이체" checked="checked"> 계좌이체
							 &nbsp; &nbsp; &nbsp;
							<input type="radio" name="oPayment" value="신용카드" > 신용카드
							 &nbsp; &nbsp; &nbsp;
							<input type="radio" name="oPayment" value="신용카드" > 무통장입금
							 &nbsp; &nbsp; &nbsp;
							<input type="radio" name="oPayment" value="신용카드" > 핸드폰 결제
							 &nbsp; &nbsp; &nbsp;
						</td>
					</tr>
				</table>
				<div class="subject">
					<span>&nbsp; 주문금액 확인 및 결제  &nbsp;</span>
				</div>
				<table>
					<tr>
						<td id="finalCheck">
							Total. <b id="totalsum"><fmt:formatNumber value="${sum }" pattern="#,###,###"/> 원</b>
						</td>
					</tr>
					<tr>
						<td class="tdBtn">
							<input type="submit" id="submit" class="btn" value="결제하기">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>