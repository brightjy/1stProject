package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.WishListDao;

public class WishListInsertService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 장바구니 추가하기
		int pId = Integer.parseInt(request.getParameter("pId"));
		String mId = request.getParameter("mId");
		WishListDao dao = WishListDao.getInstance();
		int result = dao.wishListInsert(pId, mId);
		if(result==WishListDao.SUCCESS) {
			request.setAttribute("wishListMsg", "위시리스트에 상품이 추가되었습니다.");
		}else {
			request.setAttribute("wishListMsg", "위시리스트에 이미 추가된 상품입니다.");
		}

	}

}
