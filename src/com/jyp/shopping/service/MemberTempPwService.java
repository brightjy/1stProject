package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.MemberDao;

public class MemberTempPwService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// mId, mName, mEmail 받아오기
		String mId = request.getParameter("mId");
		String mName = request.getParameter("mName");
		String mEmail = request.getParameter("mEmail");
		// 임시 비밀번호 변수 미리 선언
		String tempPw = "";
		// 해당 정보의 회원이 있으면(searchMember dao 호출 -> EXIST
		MemberDao dao = MemberDao.getInstance();
		int result = dao.searchMember(mId, mName, mEmail);
		if(result==MemberDao.EXISTMEMBER) {
			// 랜덤 비밀번호 생성
			int index = 0;
			char[] charSet = new char[] {
				'0','1','2','3','4','5','6','7','8','9',
				'a','b','c','d','e','f','g','h','i','j',
				'K','L','M','N','O','P','Q','R','S','T',
				'U','V','W','X','Y','Z'};
			
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<12; i++) {
				index = (int)(charSet.length*Math.random());
				sb.append(charSet[index]);
				tempPw = sb.toString();
			}
			// 해당 회원 회원 정보에서 비밀번호도 sb 로 바꿔주기
			int modifyResult = dao.modifyTempPw(tempPw, mId, mName, mEmail);
			if(modifyResult==MemberDao.SUCCESS) {
				// 생성한 비밀번호 -> setAttribute
				request.setAttribute("tempPw", tempPw);
			}else {
				System.out.println("비밀번호 데이터베이스 반영 실패");
			}
		}else {
			//  tempPwError -> setAttribute
			request.setAttribute("tempPwError", "정보가 일치하는 회원이 없습니다. 관리자에게 문의하세요.");
		}		
	}
}
