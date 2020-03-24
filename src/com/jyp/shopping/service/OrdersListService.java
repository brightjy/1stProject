package com.jyp.shopping.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jyp.shopping.dao.OrdersDetailDao;
import com.jyp.shopping.dto.MemberDto;
import com.jyp.shopping.dto.OrdersDetailDto;

public class OrdersListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 페이징 처리할 때 필요한 작업
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum="1";
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE=10, BLOCKSIZE=10;
		int startRow = (currentPage-1)*PAGESIZE+1;
		int endRow = (startRow+PAGESIZE)-1;
		
		// mId로 ordersDetailDto 가져오기
		HttpSession session = request.getSession();
		String mId = ((MemberDto)session.getAttribute("member")).getmId();
		OrdersDetailDao odDao = OrdersDetailDao.getInstance();
		ArrayList<OrdersDetailDto> odDtos = odDao.ordersDetailsDto(mId, startRow, endRow);
		request.setAttribute("ordersList", odDtos);
		
		//페이징
		int totCnt = odDao.ordersDetailTotCnt(mId);
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = (startPage+BLOCKSIZE)-1;
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", currentPage);
		request.setAttribute("pageCnt", pageCnt);
	}

}
