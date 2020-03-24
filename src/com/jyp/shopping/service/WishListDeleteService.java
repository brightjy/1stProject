package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.WishListDao;

public class WishListDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String mId = request.getParameter("mId");
		int pId = Integer.parseInt(request.getParameter("pId"));
		WishListDao dao = WishListDao.getInstance();
		
		int result = dao.wishListDelete(mId, pId);
		if(result==WishListDao.SUCCESS) {
			request.setAttribute("wishListMsg", "위시리스트에서 상품을 삭제했습니다.");
		}else {
			request.setAttribute("wishListMsg", "위시리스트에서 상품을 삭제실패. 관리자에게 문의하세요.");
		}

	}

}
