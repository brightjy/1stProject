package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.ProductDao;

public class ProductListBestService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ProductDao dao = ProductDao.getInstance();
		request.setAttribute("productListBest", dao.productListBest());

	}

}
