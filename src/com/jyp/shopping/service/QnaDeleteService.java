package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.QnaDao;

public class QnaDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// qId
		int qId = Integer.parseInt(request.getParameter("qId"));
		String qPw = request.getParameter("qPw");
		QnaDao dao = QnaDao.getInstance();
		int result = dao.qnaDelete(qId, qPw);
		if(result==QnaDao.SUCCESS) {
			request.setAttribute("qnaWriteResultMsg", "문의글이 삭제되었습니다.");
		}else {
			request.setAttribute("qnaWriteResultMsg", "비밀번호 불일치로 문의글 삭제 실패하였습니다.");
		}
	}

}
