package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jyp.shopping.dao.MemberDao;

public class MemberDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String mId = request.getParameter("mId");
		MemberDao dao = MemberDao.getInstance();
		int result = dao.deleteMember(mId);
		if(result==MemberDao.SUCCESS) {
			request.setAttribute("deleteMemberResult", "회원탈퇴 성공. 그동안 감사했습니다.");
			HttpSession session = request.getSession();
			session.invalidate();
		}else {
			request.setAttribute("deleteMemberResult", "회원탈퇴 실패. 관리자에게 문의하세요.");
		}

	}

}
