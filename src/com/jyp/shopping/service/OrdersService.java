package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jyp.shopping.dao.CartDao;
import com.jyp.shopping.dao.OrdersDao;
import com.jyp.shopping.dao.OrdersDetailDao;
import com.jyp.shopping.dao.ProductDao;
import com.jyp.shopping.dto.MemberDto;

public class OrdersService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 필요한 파라미터 값 받아오기
		HttpSession session = request.getSession();
		String mId = ((MemberDto)session.getAttribute("member")).getmId();
		String oName = request.getParameter("oName");
		String oPhone = request.getParameter("oPhone");
		String oAddress = request.getParameter("oAddress");
		String oPayment = request.getParameter("oPayment");
		
		// 오더 생성
		OrdersDao oDao = OrdersDao.getInstance();
		oDao.ordersInsert(mId, oName, oPhone, oAddress, oPayment);

		// 오더 디테일 생성
		String[] pId = request.getParameterValues("pId");
		String[] odAmount = request.getParameterValues("odAmount");
		OrdersDetailDao odDao = OrdersDetailDao.getInstance();
		for(int i=0 ; i<pId.length ; i++) {
			int result = odDao.ordersDetailInsert(pId[i], odAmount[i]);
			if(result!=1) break;
		}
		
		// 상품 재고 수정
		ProductDao pDao = ProductDao.getInstance();
		for(int i=0 ; i<pId.length ; i++) {
			pDao.afterOrdersStock(odAmount[i], pId[i]);
		}

		// 카트 비우기
		String[] cId = request.getParameterValues("cId");
		CartDao cDao = CartDao.getInstance();
		for(int i=0 ; i<cId.length ; i++ ) {
			cDao.cartDelete(cId[i]);
		}
		
		// 오더 정보 저장해서 넘기기
		// 1. 지금 막 생성된 오더 번호 가져오기
		int oId = oDao.getCurrOid();
		// 2. 오더번호로 오더 정보 가져오기(주문자 정보 - OrdersDto dto -> 뿌려줄 수 있게 저장하기)
		request.setAttribute("orders", oDao.ordersDto(oId));
		// 3. 오더 번호로 오더 디테일 정보 가져오기(주문한 상품 정보 - OrdersDetailDto dto -> 뿌려줄 수 있게 저장하기)
		request.setAttribute("ordersDetail", odDao.ordersDetailsDto(oId) );
	}

}
