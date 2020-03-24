package com.jyp.shopping.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.ProductDao;
import com.jyp.shopping.dao.ReviewDao;
import com.jyp.shopping.dto.ProductDto;
import com.jyp.shopping.dto.ReviewDto;

public class ProductContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// pId 받아오기 (선택한 상품 정보)
		int pId = Integer.parseInt(request.getParameter("pId"));
		// pId로 상품 정보 불러오기
		ProductDao dao = ProductDao.getInstance();
		ProductDto dto = dao.productContent(pId); 
		request.setAttribute("dto", dto);
		// pId로 해당 상품 리뷰 리스트 불러오기
			// 리뷰 리스트 페이징 용
			String pageNum = request.getParameter("pageNum");
			if(pageNum==null || pageNum.equals("")) {
				pageNum = "1";
			}
			int currentPage = Integer.parseInt(pageNum);
			final int PAGESIZE = 10;
			final int BLOCKSIZE = 5;
			
			int startRow = (currentPage-1)*PAGESIZE+1;
			int endRow = (startRow+PAGESIZE)-1;
			// 리뷰 리스트 가져오기
			ReviewDao rDao = ReviewDao.getInstance();
			ArrayList<ReviewDto> rDtos = rDao.reviewListpId(pId, startRow, endRow);
			request.setAttribute("reviewList", rDtos);
		// 페이징
		// pageCnt, startPage, endPage, BLOCKSIZE, pageNum(currentPage) 저ㅋ장ㅋ
		int totCnt = rDao.reviewTotCntpId(pId);
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
