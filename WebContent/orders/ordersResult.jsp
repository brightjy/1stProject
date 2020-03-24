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
<link href='${conPath }/css/ordersResult.css' rel='stylesheet'>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	
});
</script>
</head>
<body>
<c:if test="${not empty ordersMsg }">
	<script>
		alert('${ordersMsg }');
	</script>
</c:if>
<jsp:include page="../main/header.jsp"/>
	<div id="ordersResult_wrap">
		<div id="ordersResult">
			<div class="subject">
				<span>&nbsp; 주문 상세 내역  &nbsp;</span>
			</div>
			<c:if test="${empty admin }">
				<div class="subject">
					감사합니다. 주문이 완료되었습니다. (주문번호 : ${orders.oId } )
				</div>	
			</c:if>			
		<c:if test="${not empty admin }">
		<table>
			<tr>
				<td colspan="2" class="firstrow">
					주문 배송 처리 현황
				</td>
			</tr>
			<tr>
				<td class="title">
					송장번호 
				</td>
				<td>
					<form action="${conPath }/ordersUpdate.do">
						<input type="hidden" name="oId" value="${orders.oId }">
						<c:if test="${orders.oInvoice eq 0 }">
							<input type="text" class="oInvoice" name="oInvoice" value="">
						</c:if>
						<c:if test="${orders.oInvoice != 0 }">
							<input type="text" class="oInvoice" name="oInvoice" value="${orders.oInvoice}">
						</c:if>
						<input type="submit" class="btn" value="배송처리완료">	
					</form>
				</td>
			</tr>
		</table>
		</c:if>
		<table>
			<tr>
				<td colspan="6" class="firstrow">
					주문 상품 정보	
				</td>
			</tr>
			<c:set var="sum" value="0"/>
			<c:forEach var="dto" items="${ordersDetail }">
			<tr>
				<td id="img">
					<a href="${conPath }/productContent.do?pId=${dto.pId}">										
						<img src="${conPath }/productImg/${dto.pImage1}" alt="상품사진">							
					</a>
				</td>
				<td id="name">
					<a href="${conPath }/productContent.do?pId=${dto.pId}">			
						${dto.pName }
					</a>
				</td>
				<td id="price">
					<fmt:formatNumber value="${dto.pPrice }" pattern="#,###,###" />원				
				</td>
				<td id="amount">
					${dto.odAmount }개
				</td>
				<td id="totsum">
					<fmt:formatNumber value="${dto.odTotsum }" pattern="#,###,###" />원						
					<c:set var="sum" value="${sum+dto.odTotsum }"/>
				</td>
				<c:if test="${empty admin  }">
					<td	id="reviewWrite">						
						<c:if test="${orders.oInvoice !=0 }">
							<button class="btn" onclick="location.href='${conPath}/reviewWriteView.do?oId=${dto.oId }&pId=${dto.pId }'">리뷰작성</button>
						</c:if>
						<br>
						<button class="btn" onclick="location.href='${conPath }/productContent.do?pId=${dto.pId}'">재구매</button>
					</td>
				</c:if>
			</tr>
			</c:forEach>
		</table>
		<table>
			<tr>
				<td colspan="2" class="firstrow">
					구매자 정보
				</td>
			</tr>
			<tr>
				<td class="title">
					주문자
				</td>
				<td>
					${member.mName }
				</td>
			</tr>
			<tr>
				<td class="title">
					이메일 주소
				</td>
				<td>
					${member.mEmail }
				</td>
			</tr>
			<tr>
				<td class="title">
					휴대폰 번호
				</td>
				<td>
					${member.mPhone }
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td colspan="2" class="firstrow">
					결제 정보
				</td>
			</tr>
			<tr>
				<td class="title">
					결제 금액
				</td>
				<td>
					<fmt:formatNumber value="${sum }" pattern="#,###,###"/> 원
				</td>
			</tr>
			<tr>
				<td class="title">
					결제 수단
				</td>
				<td>
					${orders.oPayment }
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td colspan="2" class="firstrow">
					배송지 정보
				</td>
			</tr>
			<tr>
				<td class="title">
					받는 사람
				</td>
				<td>
					${orders.oName }
				</td>
			</tr>
			<tr>
				<td class="title">
					휴대폰 번호
				</td>
				<td>
					${orders.oPhone }
				</td>
			</tr>
			<tr>
				<td class="title">
					주소
				</td>
				<td>
					${orders.oAddress }
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td class="lastrow">				
					<button class="btn" id="lastbtn" onclick="location.href='${conPath}/ordersList.do'">List</button>		
				</td>
			</tr>
		</table>
		</div>
	</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>