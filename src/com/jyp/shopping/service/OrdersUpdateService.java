package com.jyp.shopping.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.OrdersDao;
import com.jyp.shopping.dto.OrdersDto;

public class OrdersUpdateService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int oId = Integer.parseInt(request.getParameter("oId"));
		int oInvoice = Integer.parseInt(request.getParameter("oInvoice"));
		
		// 인보이스 저장
		OrdersDao oDao = OrdersDao.getInstance();
		int result = oDao.ordersModify(oInvoice, oId);
		
		// 저장된 인보이스 정보 넣기
		OrdersDto oDtos = oDao.ordersDto(oId);
		request.setAttribute("orders", oDtos);
		
		if(result==OrdersDao.SUCCESS) {
			request.setAttribute("ordersMsg", "인보이스 정보가 저장되었습니다.");
		}else {
			request.setAttribute("ordersMsg", "인보이스 정보 저장 실패");
		}
	}

}
