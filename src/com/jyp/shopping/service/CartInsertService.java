package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jyp.shopping.dao.CartDao;
import com.jyp.shopping.dao.WishListDao;
import com.jyp.shopping.dto.MemberDto;

public class CartInsertService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		int pId = Integer.parseInt(request.getParameter("pId"));
		HttpSession session = request.getSession();
		String mId = ((MemberDto)session.getAttribute("member")).getmId();
		int cAmount = Integer.parseInt(request.getParameter("cAmount"));
		
		/*
		 * // 위시리스트 -> 카트에 추가한 경우, 카트에 추가한 상품 위시리스트에서 자동 삭제 WishListDao wDao =
		 * WishListDao.getInstance(); int result = wDao.wishListDelete(mId, pId);
		 */
		
		// 카트에 상품 넣기
		CartDao dao = CartDao.getInstance();
		int result = dao.cartInsert(mId, pId, cAmount);
		if(result==CartDao.SUCCESS) { 
			request.setAttribute("cartMsg", "장바구니에 상품을 추가하였습니다.");
		}else {
			request.setAttribute("cartMsg", "이미 추가된 상품입니다.");
		}
	}
}
