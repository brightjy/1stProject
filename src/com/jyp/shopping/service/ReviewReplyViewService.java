package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.ReviewDao;
import com.jyp.shopping.dto.ReviewDto;

public class ReviewReplyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// reviewDto
		int rId = Integer.parseInt(request.getParameter("rId"));
		ReviewDao dao = ReviewDao.getInstance();
		ReviewDto dto = dao.reviewContent(rId);
		request.setAttribute("reviewReplyView", dto);
	}

}
