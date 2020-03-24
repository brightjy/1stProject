package com.jyp.shopping.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.ProductDao;
import com.jyp.shopping.dto.ProductDto;

public class ProductListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// productList.do -> productList.do?pageNum=1
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 9;
		final int BLOCKSIZE = 3;
		
		int startRow = (currentPage-1)*PAGESIZE+1;
		int endRow = (startRow+PAGESIZE)-1;
		
		// 리스트 가져오기
		ProductDao dao = ProductDao.getInstance();
		ArrayList<ProductDto> dtos = dao.productList(startRow, endRow);
		request.setAttribute("productList", dtos);
		
		// 페이징처리
		// pageCnt, startPage, endPage, BLOCKSIZE, pageNum, currentPage
		int totCnt = dao.productTotCnt();
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage+BLOCKSIZE-1;
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("pageNum", currentPage);
	}

}
