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
<link href='${conPath }/css/productInsert.css' rel='stylesheet'>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="${conPath }/ckeditor/ckeditor.js"></script>
<script>
$(document).ready(function(){
	CKEDITOR.replace('pContent');
	CKEDITOR.instances.pDiscount.getData();
});
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<div id="productInsert_wrap">
		<div id="productInsert">
			<c:set var="dto" value="${ productModifyView}"/>
			<form action="${conPath }/productModify.do" method="post" enctype="multipart/form-data">
				<input type="hidden" name="pId" value="${dto.pId }">
				<input type="hidden" name="dbpImage1" value="${dto.pImage1 }">
				<input type="hidden" name="dbpImage2" value="${dto.pImage2 }">
				<table>
					<tr>
						<td class="title">
							카테고리
						</td>
						<td>
							<select id="pCategory" name="pCategory">
								<option selected>필수선택</option>
								<option value="FURNITURE">FURNITURE</option>
								<option value="PLANT">ETC</option>
							</select>
							<span id="pCategoryMsg"> &nbsp; &nbsp; </span>
						</td>
						
					</tr>
					<tr>
						<td class="title">
							상품명
						</td>
						<td>
							<input type="text" class="content" id="pName" name="pName" value="${dto.pName }" required="required" autofocus="autofocus">
							<span id="pNameMsg"> &nbsp; &nbsp; </span>
						</td>
					</tr>
					<tr>
						<td class="title">
							상품 가격
						</td>
						<td>
							<input type="text" class="content" id="pPrice" name="pPrice" value="${dto.pPrice }" required="required"> 원
							<span id="pPriceMsg"> &nbsp; &nbsp; </span>
						</td>
					</tr>
					<tr>
						<td class="title">
							상품 할인율(%)
						</td>
						<td>
							<input type="text" class="content" id="pDiscount" name="pDiscount" value="${dto.pDiscount }"> %
							<span id="pDiscountMsg"> &nbsp; &nbsp; </span>
						</td>
					</tr>
					<tr>
						<td class="title">
							대표 이미지(필수)
						</td>
						<td>
							<input type="file" class="content" id="pImage1" name="pImage1" >
							<c:if test="${not empty dto.pImage1 }">
								<p>기존 첨부 파일 : ${dto.pImage1}</p>
							</c:if>
						</td>
					</tr>
					<tr>
						<td class="title">
							서브 이미지(선택)
						</td>
						<td>
							<input type="file" class="content" id="pIamge2" name="pImage2" value="${dto.pImage2 }">
							<c:if test="${not empty dto.pImage2 }">
								<p>기존 첨부 파일 : ${dto.pImage2}</p>
							</c:if>
						</td>
					</tr>
					<tr>
						<td class="title">
							교환/환불 규정
						</td>
						<td>
							<input type="text" class="content" id="pPolicy1" name="pPolicy1" 
								value="${dto.pPolicy1 }">
							<span id="pPolicy1Msg"> &nbsp; &nbsp; </span>
						</td>
					</tr>
					<tr>
						<td class="title">
							배송 규정
						</td>
						<td>
							<input type="text" class="content" id="pPolicy2" name="pPolicy2" 
								value="${dto.pPolicy2 }">
							<span id="pPolicy2Msg"> &nbsp; &nbsp; </span>
						</td>
					</tr>
					<tr>
						<td class="title">
							재고 수량
						</td>
						<td>
							<input type="text" class="content" id="pStock" name="pStock" value="${dto.pStock }"> 개
							<span id="pStockMsg"> &nbsp; &nbsp; </span>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea name="pContent" id="pContent">${dto.pContent }</textarea>
							<script>
								CKEDITOR.replace('pContent');
							</script>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="colspan">
							<input type="submit" value="Modify" class="btn">
							<input type="button" value="List" class="btn" onclick="history.back()">
						</td>
					</tr>					
				</table>				
			</form>
		</div>
	</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>