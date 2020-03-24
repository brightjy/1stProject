package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.ProductDao;
import com.jyp.shopping.dto.ProductDto;

public class ProductModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// pId
		int pId = Integer.parseInt(request.getParameter("pId"));
		ProductDao dao = ProductDao.getInstance();
		ProductDto dto = dao.productContent(pId);
		request.setAttribute("productModifyView", dto);
	}

}
