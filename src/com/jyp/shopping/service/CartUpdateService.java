package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.CartDao;

public class CartUpdateService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// pId, cAmount, cartUpdateMsg
		int cId = Integer.parseInt(request.getParameter("cId"));
		int cAmount = Integer.parseInt(request.getParameter("cAmount"));
		
		CartDao dao = CartDao.getInstance();
		int result = dao.cartUpdate(cAmount, cId);
		
		if(result==CartDao.SUCCESS) {
			request.setAttribute("cartMsg", "상품 수량이 변경되었습니다." );
		}else {
			request.setAttribute("cartMsg", "상품 수량 변경 실패");
		}
	}
}
