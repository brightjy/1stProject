package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.QnaDao;

public class QnaModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int qId = Integer.parseInt(request.getParameter("qId"));
		QnaDao dao = QnaDao.getInstance();
		request.setAttribute("qnaModifyView", dao.qnaDto(qId));

	}

}
