package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.ReviewDao;

public class ReviewListMemberService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// reviewListMember.do -> reviewListMember.do?pageNum=1 이렇게 넘겨줄 예정
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 10;
		final int BLOCKSIZE = 5;
		
		int startRow = (currentPage-1)*PAGESIZE+1;
		int endRow = (startRow+PAGESIZE)-1;		
		
		// mId
		String mId = request.getParameter("mId");
		ReviewDao dao = ReviewDao.getInstance();
		request.setAttribute("reviewListMember", dao.reviewListMid(mId, startRow, endRow));
		
		// 페이징
		// pageCnt, startPage, endPage, BLOCKSIZE, pageNum(currentPage) 저ㅋ장ㅋ
		int totCnt = dao.reviewMemberTotCnt(mId);
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
