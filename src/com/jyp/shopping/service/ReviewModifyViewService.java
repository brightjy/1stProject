package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.ReviewDao;

public class ReviewModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rId = Integer.parseInt(request.getParameter("rId"));
		ReviewDao dao = ReviewDao.getInstance();
		request.setAttribute("reviewModifyView", dao.reviewDtoRid(rId));

	}

}
