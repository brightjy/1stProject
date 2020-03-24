package com.jyp.shopping.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.CartDao;
import com.jyp.shopping.dto.CartDto;

public class OrdersViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		// 카트의 선택상품 정보 받아옴
		String[] cId = request.getParameterValues("box");
		CartDao dao = CartDao.getInstance();
		ArrayList<CartDto> dtos = new ArrayList<CartDto>();
		for(String tempcId : cId) {
			dtos.add(dao.cartDto(Integer.parseInt(tempcId)));
		}
		request.setAttribute("ordersView", dtos);

	}

}
