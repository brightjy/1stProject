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
<script>
$(document).ready(function(){	
	$("#submit").click(function(){
		var pCategory = $("#pCategory").val();
		var pName = $("#pName").val();
		var pPrice = $("#pPrice").val();
		var pDiscount = $("#pDiscount").val();
		var pImage1 = $("pImage1").val();
		var pPolicy1 = $("pPolicy1").val();
		var pPolicy2 = $("pPolicy2").val();
		var pStock = $("pStock").val();
		if(!pCategory || !pName || !pPrice || !pDiscount || !pImage1 || !pImage2 || !pPolicy1 || !pPolicy2 || !pStock){
			alert('작성란을 모두 기재했는지 확인해주세요. (*할인이 없을 시 0입력)');
			return false();
		}
	});	
});

</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<div id="productInsert_wrap">
		<div id="productInsert">
			<form action="${conPath }/productInsert.do" method="post" enctype="multipart/form-data">
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
							<input type="text" class="content" id="pName" name="pName" autofocus="autofocus">
							<span id="pNameMsg"> &nbsp; &nbsp; </span>
						</td>
					</tr>
					<tr>
						<td class="title">
							상품 가격
						</td>
						<td>
							<input type="text" class="content" id="pPrice" name="pPrice"> 원
							<span id="pPriceMsg"> &nbsp; &nbsp; </span>
						</td>
					</tr>
					<tr>
						<td class="title">
							상품 할인율(%)
						</td>
						<td>
							<input type="text" class="content" id="pDiscount" name="pDiscount"> %
							<span id="pDiscountMsg"> &nbsp; &nbsp; </span>
						</td>
					</tr>
					<tr>
						<td class="title">
							대표 이미지(필수)
						</td>
						<td>
							<input type="file" class="content" id="pImage1" name="pImage1" required="required">
							<span id="pImage1Msg"> &nbsp; &nbsp; </span>
						</td>
					</tr>
					<tr>
						<td class="title">
							서브 이미지(선택)
						</td>
						<td>
							<input type="file" class="content" id="pIamge2" name="pImage2">
							<span id="pImage2Msg"> &nbsp; &nbsp; </span>
						</td>
					</tr>
					<tr>
						<td class="title">
							교환/환불 규정
						</td>
						<td>
							<input type="text" class="content" id="pPolicy1" name="pPolicy1" 
								value="제품 하자 시 30일 이내 무상 교환">
							<span id="pPolicy1Msg"> &nbsp; &nbsp; </span>
						</td>
					</tr>
					<tr>
						<td class="title">
							배송 규정
						</td>
						<td>
							<input type="text" class="content" id="pPolicy2" name="pPolicy2" 
								value="배송비 기본 1만 원, 10만 원 이상 배송비 무료">
							<span id="pPolicy2Msg"> &nbsp; &nbsp; </span>
						</td>
					</tr>
					<tr>
						<td class="title">
							재고 수량
						</td>
						<td>
							<input type="text" class="content" id="pStock" name="pStock"> 개
							<span id="pStockMsg"> &nbsp; &nbsp; </span>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea name="pContent" id="pContent"></textarea>
							<script>
								CKEDITOR.replace('pContent');
							</script>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="colspan">
							<input type="submit" id="submit" value="Write" class="btn">
							<input type="button" value="List" class="btn" onclick="history.back()">
						</td>
					</tr>					
				</table>				
			</form>
		</div>
	</div>
<jsp:include page="../main/footer.jsp"/>
<script src="${conPath }/ckeditor/ckeditor.js"></script>
<script>
$(document).ready(function(){
	CKEDITOR.replace('pContent');
	CKEDITOR.instances.pDiscount.getData();

	
});
</script>
</body>
</html>