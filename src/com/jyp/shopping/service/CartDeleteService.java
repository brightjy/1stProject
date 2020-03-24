package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.CartDao;

public class CartDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// cId, cartDeleteMsg
		int cId = Integer.parseInt(request.getParameter("cId"));
		CartDao dao = CartDao.getInstance();
		int result = dao.cartDelete(cId);
		if(result==CartDao.SUCCESS) {
			request.setAttribute("cartMsg", "선택하신 상품을 삭제했습니다.");			
		}else {
			request.setAttribute("cartMsg", "선택 상품 삭제 실패");		
		}
	}

}
