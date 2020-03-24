package com.jyp.shopping.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.MemberDao;

public class MemberIdChkService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//ajax 통해  memberIdChk.do?mId=mId 로 들어옴.
		String mId = request.getParameter("mId");
		String mIdPattern = "^(?=.*\\d)||(?=.*[a-z]).{4,12}$"; // 숫자/영문소문자 4~12자리 
		
		MemberDao dao = MemberDao.getInstance();
		Matcher matcher = Pattern.compile(mIdPattern).matcher(mId);
		// mId 유무 체크하는 dao 소환
		int result = dao.mIdChk(mId);
		
		if(mId.contains(" ")) {
			request.setAttribute("mIdChkMsg", "아이디에 공백은 포함할 수 없습니다.");
		}else if(!mId.contains(" ") && !matcher.matches()){
			request.setAttribute("mIdChkMsg", "ID는 숫자+영문소문자 4~12자리 양식을 지켜주세요.");
		}else if(!mId.contains(" ") && matcher.matches() && result==MemberDao.EXIST) {
			request.setAttribute("mIdChkMsg", "이미 사용 중인 아이디 입니다.");
		}else{
			request.setAttribute("mIdChkMsg", "");
		}
	}

}
