package com.jyp.shopping.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberPwChkService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String mPw = request.getParameter("mPw");
		String mId = request.getParameter("mId");
		
		String mPwPattern1 = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{9,12}$"; // 영문대소문자+숫자+특수문자 9~12자
		String mPwPattern2 = "(.)\\1\\1\\1"; // 같은 문자 4개 이상 사용 불가
		
		Matcher matcher1 = Pattern.compile(mPwPattern1).matcher(mPw);
		Matcher matcher2 = Pattern.compile(mPwPattern2).matcher(mPw);
		
		if(mPw.contains(" ")) {
			request.setAttribute("mPwChkMsg", "비밀번호에 공백은 포함할 수 없습니다.");
		}else if(!mPw.contains(" ") && !matcher1.matches()) {
			request.setAttribute("mPwChkMsg", "영문대소문자+숫자+특수문자 9~12자 양식을 지켜주세요.");
		}else if(!mPw.contains(" ") && matcher1.matches() && matcher2.find()) {
			request.setAttribute("mPwChkMsg", "같은 문자 4개이상 연속 사용은 불가능합니다.");
		}else if(!mPw.contains(" ") && matcher1.matches() && !matcher2.find() && mPw.contains(mId)) {
			request.setAttribute("mPwChkMsg", "비밀번호에 ID는 포함할 수 없습니다.");
		}else {
			request.setAttribute("mPwChkMsg", "");
		}
	}

}
