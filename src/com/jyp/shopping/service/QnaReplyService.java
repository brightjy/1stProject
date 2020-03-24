package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.QnaDao;
import com.jyp.shopping.dto.QnaDto;

public class QnaReplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String aId = request.getParameter("aId");
		int qId = Integer.parseInt(request.getParameter("qId"));
		int qGroup = Integer.parseInt(request.getParameter("qGroup"));
		int qStep = Integer.parseInt(request.getParameter("qStep"));
		int qIndent = Integer.parseInt(request.getParameter("qIndent"));
		String qCategory = request.getParameter("qCategory");
		String qTitle = request.getParameter("qTitle");
		String qContent = request.getParameter("qContent");
		String qPw = request.getParameter("qPw");
		String qIp = request.getRemoteAddr();
		String pageNum = request.getParameter("pageNum");
		
		QnaDao dao = QnaDao.getInstance();
		QnaDto dto = new QnaDto(qId, qCategory, null, aId, qPw, qTitle, qContent, null, null, null, 0, qGroup, qStep, qIndent, qIp);
		int result = dao.qnaReply(dto);
		if(result==QnaDao.SUCCESS) {
			request.setAttribute("qnaWriteResultMsg","답변 글쓰기 성공" );
			request.setAttribute("pageNum", pageNum);
		}else {
			request.setAttribute("qnaWriteResultMsg","답변 글쓰기 실패" );
		}
		

	}

}
