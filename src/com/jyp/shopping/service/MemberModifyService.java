package com.jyp.shopping.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jyp.shopping.dao.MemberDao;
import com.jyp.shopping.dto.MemberDto;

public class MemberModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		String mName = request.getParameter("mName");
		String mEmail = request.getParameter("mEmail");
		String mPhone = request.getParameter("mPhone");
		String mAddress = request.getParameter("mAddress");
		String mBirthStr = request.getParameter("mBirth");
		Date mBirth = null;
		if(!mBirthStr.equals("")) {
			mBirth = Date.valueOf(mBirthStr);
		}
		
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto(mId, mPw, mName, mEmail, mPhone, mAddress, mBirth, null, 0); 
		
		int result = dao.modifyMember(dto);
		if(result==MemberDao.SUCCESS) {
			HttpSession session = request.getSession();
			session.setAttribute("member", dto);
			request.setAttribute("modifyResult", "회원정보 수정성공");
		}else {
			request.setAttribute("modifyResult", "회원정보 수정실패");
		}
	}
}
