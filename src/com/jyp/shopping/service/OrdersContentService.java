package com.jyp.shopping.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.MemberDao;
import com.jyp.shopping.dao.OrdersDao;
import com.jyp.shopping.dao.OrdersDetailDao;
import com.jyp.shopping.dto.MemberDto;
import com.jyp.shopping.dto.OrdersDetailDto;
import com.jyp.shopping.dto.OrdersDto;

public class OrdersContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// oId, odId
		// 오더 번호로 오더 디테일 조회해서 정보 가져오기
		int oId = Integer.parseInt(request.getParameter("oId"));
		OrdersDao oDao = OrdersDao.getInstance();
		OrdersDetailDao odDao = OrdersDetailDao.getInstance();
		OrdersDto oDto = oDao.ordersDto(oId);
		String mId = oDto.getmId();
		MemberDao mDao = MemberDao.getInstance(); 
		MemberDto mDto = mDao.getMemberDto(mId);
		ArrayList<OrdersDetailDto> odDtos = odDao.ordersDetailsDto(oId);
		request.setAttribute("orders", oDto);
		request.setAttribute("ordersDetail", odDtos);
		request.setAttribute("member", mDto);
		

	}

}
