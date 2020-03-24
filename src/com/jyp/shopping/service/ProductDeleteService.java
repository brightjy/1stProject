package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.ProductDao;

public class ProductDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// pId
		int pId = Integer.parseInt(request.getParameter("pId"));
		ProductDao dao = ProductDao.getInstance();
		int result = dao.productDelete(pId);
		if(result==ProductDao.SUCCESS) {
			request.setAttribute("productInsertMsg", "상품삭제 성공");
		}else {
			request.setAttribute("productInsertMsg", "상품삭제 실패");
		}

	}

}
