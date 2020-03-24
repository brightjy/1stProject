<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <title>Product Content</title>
 <link href='${conPath }/css/productContent.css' rel='stylesheet'>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
<c:if test="${not empty cartMsg }">
	<script>
		alert('${cartMsg }');
	</script>
</c:if>
<c:if test="${not empty wishListMsg }">
	<script>
		alert('${wishListMsg }');
	</script>
</c:if>
	<div id="productContent_wrap">
		<div id="top">
			<div id="productImage">
				<div class="pImage">
					<img src="${conPath}/productImg/${dto.pImage1 }" alt="상품사진1">		
				</div>
			</div>
	  		<div id="product_info">
				<div id="accordion">
					<h3>${dto.pName }</h3>
					<div>
						<p>
							구매 전 아래 규정 및 하단의 상세 내용을 참고해주세요.
						</p>
					</div>
					<h3>Exchange / Return</h3>
					<div>
						<p>
							${dto.pPolicy1 }
						</p>
					</div>
					<h3>Delivery</h3>
					<div>
						<p>
							${dto.pPolicy2 }
						</p>
					</div>
				</div>
				<div id="productPrice">
					<form action="${conPath }/cartInsert.do" method="get">
					<input type="hidden" id="pId" name="pId" value="${dto.pId }">
					<input type="hidden" id="mId" name="mId" value="${member.mId }"> <!-- 세션 값 받아옴 -->
					<table>
						<tr>
							<th>
								판매가격
							</th>
							<td>
								<fmt:formatNumber value="${dto.pPrice }" pattern="#,###,###"/>원
							</td>
						</tr>
						<c:if test="${dto.pDiscount != 0 }">
							<tr>
								<th>
									할인가격
								</th>
								<td >
									<c:set var="discountedPrice" value="${dto.pPrice*(1-(dto.pDiscount/100)) }"/>
									<fmt:formatNumber value="${discountedPrice }" pattern="#,###,###"/>원
									<span>
										(${dto.pDiscount }%할인)
									</span>
								</td>
							</tr>
						</c:if>
						<tr>
							<th>
								수량
							</th>
							<td>
								<select name="cAmount" id="cAmount">
									<option value='1'>1</option>
									<option value='2'>2</option>
									<option value='3'>3</option>
									<option value='4'>4</option>
									<option value='5'>5</option>
								</select> 개
							</td>
						</tr>
						<tr>					
							<td colspan="2">
								<c:if test="${not empty member }">
									<%-- <input type="button" value="BUY NOW" onclick="location.href='${conPath }/ordersView.do'"> --%>
									<input type="submit" id="cartInsert" value="Add to Cart" class="btn">
									<input type="button" value="go to Cart" class="btn" onclick="location.href='${conPath }/cartList.do?mId=${member.mId}'">
									<input type="button" class="heartbtn" onclick="location.href='${conPath}/wishListInsert.do?pId=${dto.pId}&mId=${member.mId}'">
								</c:if>
								<c:if test="${not empty admin }">
									<input type="button" value="상품 정보 수정" class="guestBtn"
										onclick="location.href='${conPath}/productModifyView.do?pId=${dto.pId }'">
									<input type="button" value="상품 삭제" class="guestBtn"
										onclick="location.href='${conPath}/productDelete.do?pId=${dto.pId }'">
								</c:if>
								<c:if test="${empty member && empty admin }">
									<input type="button" value="상품 구매는 로그인 후 가능합니다." class="guestBtn"
										onclick="location.href='${conPath}/memberLoginView.do'">
								</c:if>
							</td>
						</tr>
					</table>
					</form>
				</div>
			</div>
			</div>
		<div id="bottom">
			<div class="bottomInfo">
				<div class="pImage">
					<c:if test="${ not empty dto.pImage2 }">
						<img src="${conPath}/productImg/${dto.pImage2 }" alt="상품사진2">
					</c:if>
					<c:if test="${empty dto.pImage2  }">					
					</c:if>
				</div>
				<div class="pContent">
				 	${dto.pContent }
				</div>
			</div>
			<div class="reviewList">
				<table>
					<tr>
						<td colspan="4" id="firstrow" class="left">
							Review.
						</td>
					</tr>
					<c:if test="${reviewList.size() eq 0 }">
						<tr>
							<td colspan="6">
								작성된 리뷰가 없습니다.
							</td>
						</tr>
					</c:if>
					<c:if test="${reviewList.size() != 0 }">
						<c:forEach var="dto" items="${reviewList }">
							<tr>
								<td class="left" id="title">
									<c:forEach var="i" begin="1" end="${dto.rIndent }">
										<c:if test="${i != dto.rIndent }">
											&nbsp; &nbsp; &nbsp;
										</c:if>
										<c:if test="${i eq dto.rIndent }">
											└─
										</c:if>
									</c:forEach>
									<a href="${conPath }/reviewContent.do?rId=${dto.rId}&pageNum=${pageNum}">
										${dto.rTitle }								
									</a>								
								</td>
								<td id="writer">
									<c:if test="${not empty dto.aId }">
										${dto.aId }
									</c:if>
									<c:if test="${not empty dto.mId }">
										${dto.mId }
									</c:if>
								</td>
								<td id="date">
									<fmt:formatDate value="${dto.rDate }" type="date" pattern="YY-MM-dd(E)"/>								
								</td>
								<td id="hit">
									${dto.rHit }
								</td>
							</tr>	
						</c:forEach>								
					</c:if>
				</table>
				<div class="paging">
					<c:if test="${startPage > BLOCKSIZE }">
						<a href="${conPath }/productContent.do?pageNum=${startPage-1}"> 〈  &nbsp; </a>
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<c:if test="${i eq pageNum }">
							<b> &nbsp; ${i }</b>
						</c:if>
						<c:if test="${i != pageNum }">
							<a href="${conPath }/productContent.do?pageNum=${i }">&nbsp; ${i }</a>
						</c:if>
					</c:forEach>
					<c:if test="${endPage < pageCnt }">
						<a href="${conPath }/productContent.do?pageNum=${endPage+1}">  &nbsp; 〉 </a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="../main/footer.jsp"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
  $( function() {
    $( "#accordion" ).accordion({
      	collapsible: true
    });  
    $("h3").click(function(){
    	$(this).css("background-color","white").css("color","black");
    });
  });
  
  $( function() {
	  function setCookie(cookie_name, value, days){
		  
	  }
	  
  });
  </script>
</body>
</html>