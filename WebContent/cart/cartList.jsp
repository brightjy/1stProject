<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href='${conPath }/css/cartList.css' rel='stylesheet'>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.1.min.js"></script>
<script>
$(document).ready(function(){
	$('#checkAll').click(function(){
		$('input:checkbox[name="box"]').each(function(){
			$(this).attr("checked",true);
		});
	});
	$('.changeAmount').each(function(index, item){
		$(this).click(function(){
			var cId = $('.cId'+index).val();
			var pPrice = $('.pPrice'+index).val();
			var cAmount = $('.cAmount'+index).val();
			var cTotsum = $('.cTotSum'+index).val();
			var sum = 0;
			$.ajax({
				url: '${conPath}/cartUpdate.do',
				type: 'get',
				dataType: 'html',
				data: "cId="+cId+"&cAmount="+cAmount,
				success: function(data, status){
					$('.cTotSum'+index).val(pPrice*cAmount);			
					$('.cTotSum').each(function(idx, data){
						sum += Number($(data).val());
					});					
					$('.totalsum').html(sum+'원');				
				}
			});
		});
	});
	$('#makeOrders').click(function(){
		if($('input:checkbox[name="box"]').filter(':checked').size()==0){
			alert('주문할 상품을 선택해주세요.')
			return false;
		}
	});
});
</script>
</head>
<body>
<c:if test="${not empty cartMsg }">
	<script>
		alert('${cartMsg}');
	</script>
</c:if>
<jsp:include page="../main/header.jsp"/>
	<div id="cartList_wrap">
		<div id="cartList">
			<div class="subject">
				<span>&nbsp; Cart &nbsp;</span>
			</div>
			<div class="cartList">					
				<form action="${conPath }/ordersView.do">
					<table>
						<c:if test="${cartList.size() eq 0 }">
							<tr>
								<td colspan="6">
									장바구니가 비었습니다.
								</td>
							</tr>
						</c:if>
						<c:if test="${cartList.size() != 0 }">						
							<c:set var="sum" value="0"/>
							<c:set var="i" value="0"/>
							<c:forEach var="dto" items="${cartList }">
								<input type="hidden" name="mId" id="mId" value="${member.mId }">
								<input type="hidden" class="cId${i }" name="cId" value="${dto.cId }">
								<input type="hidden" name="pId" value="${dto.pId }">
									<tr>
										<td id="checkbox">
											<input type="checkbox" name="box" value="${dto.cId }">
										</td>
										<td id="productImg">
											<div class="productImg">
												<a href="${conPath }/productContent.do?pId=${dto.pId}">										
													<img src="${conPath }/productImg/${dto.pImage1}" alt="상품사진">							
												</a>
											</div>
										</td>
										<td id="productName" class="left">
											<a href="${conPath }/productContent.do?pId=${dto.pId}">			
												${dto.pName }
											</a>
										</td>	
										<td id="productPrice">
											<input type="text" id="pPrice" class="pPrice${i }" name="pPrice" value="${dto.pPrice }" readonly="readonly">
										</td>
										<td id="productAmount">
											<input type="text" id="cAmount" class="cAmount${i }" name="cAmount" value="${dto.cAmount }">
											<div>
												<input type="button" class="btn changeAmount" value="수량변경"> 
												<input type="button" class="btn" id="delete" value="삭제" onclick="location.href='${conPath}/cartDelete.do?cId=${dto.cId }'">
											</div>
										</td>
										<td id="sum">
											<input type="text" name="cTotsum" id="cTotsum" class="cTotSum${i } cTotSum" value="${dto.cTotsum }">
											<c:set var="sum" value="${sum+dto.cTotsum }"/>
											<c:set var="i" value="${i+1 }"/>
										</td>
									</tr>	
							</c:forEach>
							<tr>
								<td colspan="6" class="right">
									Total. <b id="totalsum" class="totalsum"><fmt:formatNumber value="${sum }" pattern="#,###,###"/> 원</b>
								</td>
							</tr>	
							<tr>
								<td colspan="6" class="colspan">
									<input type="button" class="largeBtn" id="checkAll" value="전체선택">
									<input type="submit" class="largeBtn" id="makeOrders" value="선택상품 주문">
								</td>
							</tr>
						</c:if>
					</table>
					</form>							
				<div class="paging">
					<c:if test="${startPage > BLOCKSIZE }">
						<a href="${conPath }/reviewList.do?pageNum=${startPage-1}"> 〈  &nbsp; </a>
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<c:if test="${i eq pageNum }">
							<b> &nbsp; ${i }</b>
						</c:if>
						<c:if test="${i != pageNum }">
							<a href="${conPath }/reviewList.do?pageNum=${i }">&nbsp; ${i }</a>
						</c:if>
					</c:forEach>
					<c:if test="${endPage < pageCnt }">
						<a href="${conPath }/reviewList.do?pageNum=${endPage+1}">  &nbsp; 〉 </a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>