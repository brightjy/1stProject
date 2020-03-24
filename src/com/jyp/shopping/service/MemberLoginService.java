package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jyp.shopping.dao.MemberDao;
import com.jyp.shopping.dto.MemberDto;

public class MemberLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		
		MemberDao dao = MemberDao.getInstance();		
		int result = dao.loginMember(mId, mPw);
		int resultDropChk = dao.memberDropChk(mId);
			
		
		if(result==MemberDao.SUCCESS) {
			MemberDto dto = dao.getMemberDto(mId);
			HttpSession session = request.getSession();
			session.setAttribute("member", dto);	
			
			request.setAttribute("memberLoginMsg", dto.getmName()+"님 반갑습니다.");
		}else if(resultDropChk==1) {
			request.setAttribute("memberLoginMsg", "로그인 실패. 탈퇴한 회원입니다.");	
		}else {
			request.setAttribute("memberLoginMsg", "로그인 실패. 아이디와 비밀번호를 확인하세요.");			
		}
	}

}
