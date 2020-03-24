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
<link href="${conPath}/css/memberView.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../main/header.jsp"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
   <script>
 $(document).ready(function(){
	 $( "#datepicker" ).datepicker({
	 		dateFormat : 'yy-mm-dd',
	 		showMonthAfterYear : true,
	    	changeMonth: true,
	        changeYear: true
		} );	
    $("#mId").keyup(function(){
    	var mId = $("#mId").val(); // 사용자가 mId에 입력한 값
    	$.ajax({
    		url: '${conPath}/memberIdChk.do',
    		type: 'post',
    		dataType: 'html',
    		data: "mId="+mId+"&mPw="+mPw,
    		success: function(data, status){
    			$("#mIdChkMsg").html(data);
    		} 
    	});
    });
    $("#mPw").keyup(function(){
    	var mPw = $("#mPw").val(); // 사용자가 mPw에 입력한 값
    	var mId = $("#mId").val(); // 사용자가 mId에 입력한 값
    	$.ajax({
    		url: '${conPath}/memberPwChk.do',
    		type: 'post',
    		dataType: 'html',
    		data: "mPw="+mPw+"&mId="+mId,
    		success: function(data, status){
    			$("#mPwPatternChkMsg").html(data);
    		}
    	});
    });
    $("#mPwChk").keyup(function(){
    	var mPw = $("#mPw").val(); // 사용자가 mPw에 입력한 값
    	var mPwChk = $("#mPwChk").val(); // 사용자가 mPwChk에 입력한 값
    	if(mPw!=mPwChk){
    		$('#mPwChkMsg').html("비밀번호가 일치하지 않습니다.");
    	}else{
    		$('#mPwChkMsg').html("");
    	}
    });
    $("#mName").keyup(function(){ // 이름 유효성은 공백 유무만 검사
    	var mName = $("#mName").val(); // 사용자가 mName에 입력한 값
    	if(mName == " "){
    		$('#mNameChkMsg').html("이름에 공백은 포함할 수 없습니다.");
    	}else{
    		$('#mNameChkMsg').html("");
    	}
    });
    $('#mEmail').keyup(function(){
    	var mEmail = $("#mEmail").val();
    	var mEmailPattern = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
    	if(mEmail != " " && !mEmailPattern.test(mEmail)){
    		$('#mEmailChkMsg').html("이메일 형식(aaa@도메인)에 맞게 입력해주세요.");
    	}else{
    		$('#mEmailChkMsg').html("");
    	}
    });
    $('#mPhone').keyup(function(){
    	var mPhone = $("#mPhone").val();
    	var mPhonePattern = RegExp(/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/);
    	if(mPhone != " " && !mPhonePattern.test(mPhone)){
    		$('#mPhoneChkMsg').html("전화번호 형식(010-1234-5678)에 맞게 입력해주세요.");
    	}else{
    		$('#mPhoneChkMsg').html("");
    	}
    });
    $('#mAddress').keyup(function(){
    	var mAddress = $("#mAddress").val();
    	if(mAddress == " "){
    		$('#mAddressChkMsg').html("우편번호와 주소를 입력해주세요.");
    	}
    });
 });
 </script>
 <c:if test="${not empty modifyResult  }">
 	<script>
 		alert('${modifyResult}');
 	</script>
 </c:if>
<div id="memberView_wrap">
	<div id="memberView">
		<form action="${conPath }/memberModify.do" method="post">
			<table>
				<tr>
					<th>
						필수정보
					</th>
				</tr>
				<tr>
					<td>
						아이디 <input type="text" id="mId" name="mId" value="${member.mId }" required="required" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td	class="msg" id="mIdChkMsg">
						&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						비밀번호<input type="password" id="mPw" name="mPw" required="required" placeholder="영문대소문자+숫자+특수문자 9~12자">
					</td>
				</tr>
				<tr>
					<td class="msg" id="mPwPatternChkMsg">
						&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						비밀번호 확인<input type="password" id="mPwChk" name="mPwChk" required="required">
					</td>
				</tr>
				<tr>
					<td	class="msg" id="mPwChkMsg">
						&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						이름 <input type="text" id="mName" name="mName" value="${member.mName }" required="required">
					</td>
				</tr>
				<tr>
					<td	class="msg" id="mNameChkMsg">
						&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						이메일<input type="email" id="mEmail" name="mEmail" value="${member.mEmail }" required="required" placeholder="예) abcd@efgh.com">
					</td>
				</tr>
				<tr>
					<td class="msg" id="mEmailChkMsg">
						&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						핸드폰<input type="text" id="mPhone" name="mPhone" value="${member.mPhone }" required="required" placeholder="예) 010-1234-5678">
					</td>
				</tr>
				<tr>
					<td	class="msg" id="mPhoneChkMsg">
						&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						주소<input type="text" id="mAddress" name="mAddress" value="${member.mAddress }" required="required" placeholder="예) 03011 서울시 종로구 통일로18나길">
					</td>
				</tr>
				<tr>
					<td	class="msg" id="mAddressChkMsg">
						&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</table>
			<br>
			<table>
				<tr>
					<th>
						선택정보
					</th>
				</tr>
				<tr>
					<td>
						생일<input type="text" name="mBirth" value="${member.mBirth }" class="date" id="datepicker">
					</td>
				</tr>
				<tr>
					<td class="msg" id="mBirthChk">
						&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</table>
			<input type="submit" id="submit" value="회원정보 수정" class="btn">
		</form>
	</div>
</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>