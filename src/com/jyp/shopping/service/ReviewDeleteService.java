package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.ReviewDao;

public class ReviewDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rId = Integer.parseInt(request.getParameter("rId"));
		String rPw = request.getParameter("rPw");	

		ReviewDao dao = ReviewDao.getInstance();
		int result = dao.reviewDelete(rId, rPw);
		if(result==ReviewDao.SUCCESS) {
			request.setAttribute("reviewMsg", "리뷰가 삭제되었습니다.");
		}else {
			request.setAttribute("reviewMsg", "리뷰삭제 실패. 관리자에게 문의하세요.");
		}
	}

}
