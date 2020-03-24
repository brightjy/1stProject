package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.ProductDao;
import com.jyp.shopping.dto.ProductDto;

public class ReviewWriteViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// pId로 productDto 가져와서 상품 정보 넣기
		String oId = request.getParameter("oId");
		int pId = Integer.parseInt(request.getParameter("pId"));
		ProductDao dao = ProductDao.getInstance();
		ProductDto dto = dao.productContent(pId);
		request.setAttribute("product", dto);
		request.setAttribute("oId", oId);

	}

}
