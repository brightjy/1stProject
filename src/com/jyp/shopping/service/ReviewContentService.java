package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.ReviewDao;
import com.jyp.shopping.dto.ReviewDto;

public class ReviewContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// rId
		int rId = Integer.parseInt(request.getParameter("rId"));
		// rId로 상품 dto 받아오기(hit 올리면서)
		ReviewDao dao = ReviewDao.getInstance();
		ReviewDto dto = dao.reviewContent(rId);
		request.setAttribute("reviewContent", dto);

	}

}
