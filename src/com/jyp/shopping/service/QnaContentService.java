package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.QnaDao;
import com.jyp.shopping.dto.QnaDto;

public class QnaContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// qId
		int qId = Integer.parseInt(request.getParameter("qId"));
		QnaDao dao = QnaDao.getInstance();
		QnaDto dto = dao.qnaContent(qId);
		request.setAttribute("qnaContent", dto);

	}

}
