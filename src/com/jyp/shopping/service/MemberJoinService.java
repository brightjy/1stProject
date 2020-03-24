package com.jyp.shopping.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jyp.shopping.dao.MemberDao;
import com.jyp.shopping.dto.MemberDto;

public class MemberJoinService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		String mName = request.getParameter("mName");
		String mEmail = request.getParameter("mEmail");
		String mPhone = request.getParameter("mPhone");
		String postcode = request.getParameter("postcode");
		String address = request.getParameter("address");
		String detailAddress = request.getParameter("detailAddress");
		String mAddress = postcode + "" + address + "" + detailAddress;
		String mBirthStr = request.getParameter("mBirth");
		Date mBirth = null;
		if(!mBirthStr.equals("")) {
			mBirth = Date.valueOf(mBirthStr);
		}
		MemberDao dao = MemberDao.getInstance();
		
		// Id 중복체크
		int result = dao.mIdChk(mId);
		if(result==MemberDao.NONEXIST) {
			MemberDto dto = new MemberDto(mId, mPw, mName, mEmail, mPhone, mAddress, mBirth, null, 0);
			result = dao.JoinMember(dto);
			if(result==MemberDao.SUCCESS) {
				HttpSession session = request.getSession();
				session.setAttribute("mId", mId);
				request.setAttribute("joinMemberResult", "회원가입이 성공적으로 완료되었습니다.");
			}else {
				request.setAttribute("joinMemberResult", "회원가입 실패하였습니다.");
			}
		}else {
			request.setAttribute("errorMsg", "이미 사용중인 아이디 입니다.");
		}

	}

}
