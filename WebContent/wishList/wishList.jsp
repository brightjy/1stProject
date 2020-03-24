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
<link href='${conPath }/css/wishList.css' rel='stylesheet'>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	$('#checkAll').click(function(){
		$('input:checkbox[name="pId"]').each(function(){
			$(this).attr("checked",true);
		});
	});
	$('#cartInsertBtn').click(function(){
		var pId = $("#pId").val();
		var cAmount = $("#cAmount").val();
		$.ajax({
			url: '${conPath}/cartInsert.do',
			dataType: 'html',
			data : "pId="+pId+"&cAmount="+cAmount,
			success: function(data, status){
				alert('장바구니에 상품이 추가되었습니다.');
				location.href='${conPath}/cartList.do';
			}
		});
	});
});
</script>
</head>
<body>
<c:if test="${not empty wishListMsg }">
	<script>
		alert('${wishListMsg }');
	</script>
</c:if>
<jsp:include page="../main/header.jsp"/>
	<div id="wishList_wrap">
		<div id="wishList">
			<div class="subject">
				<span>&nbsp; Wish List &nbsp;</span>
			</div>
			<div class="wishList">
				<form action="${conPath }/cartInsert.do">
					<table>
						<c:if test="${wishList.size() eq 0 }">
							<tr>
								<td colspan="6">
									위시리스트 상품이 없습니다.
								</td>
							</tr>
						</c:if>
						<c:if test="${wishList.size() != 0 }">
							<c:forEach var="dto" items="${wishList }">						
								<tr>
									<td id="checkbox">
										<input type="checkbox" id="pId" name="pId" value="${dto.pId }">
									</td>
									<td id="productImg">
										<div class="productImg">
											<a href="${conPath }/productContent.do?pId=${dto.pId}">										
												<img src="${conPath }/productImg/${dto.pImage1}" alt="상품사진">							
											</a>
										</div>
									</td>
									<td id="productName">
										<a href="${conPath }/productContent.do?pId=${dto.pId}">			
											${dto.pName }
										</a>
									</td>								
									<td id="productPrice">
										<fmt:formatNumber value="${dto.pPrice }" pattern="#,###,###" />원								
									</td>
									<td id="productAmount">
										<input type="text" id="cAmount" class="cAmount" name="cAmount" value="1">
									</td>
									<td id="btn">
										<input type="button" class="btn" id = "cartInsertBtn" value="카트에 넣기">
										<br>
										<input type="button" class="btn" value="상품 삭제" onclick="location.href='${conPath}/wishListDelete.do?mId=${dto.mId }&pId=${dto.pId }'">								
									</td>
								</tr>	
							</c:forEach>								
						</c:if>
					</table>
				</form>
				<div class="paging">
					<c:if test="${startPage > BLOCKSIZE }">
						<a href="${conPath }/wishList.do?pageNum=${startPage-1}"> 〈  &nbsp; </a>
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<c:if test="${i eq pageNum }">
							<b> &nbsp; ${i }</b>
						</c:if>
						<c:if test="${i != pageNum }">
							<a href="${conPath }/wishList.do?pageNum=${i }">&nbsp; ${i }</a>
						</c:if>
					</c:forEach>
					<c:if test="${endPage < pageCnt }">
						<a href="${conPath }/wishList.do?pageNum=${endPage+1}">  &nbsp; 〉 </a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>