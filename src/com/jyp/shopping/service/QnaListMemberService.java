package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.QnaDao;

public class QnaListMemberService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// qnaListMember.do -> qnaListMember.do?pageNum=1
				String pageNum = request.getParameter("pageNum");
				if(pageNum==null) pageNum="1";
				int currentPage = Integer.parseInt(pageNum);
				final int PAGESIZE=10, BLOCKSIZE=5;
				int startRow = (currentPage-1)*PAGESIZE+1;
				int endRow = (startRow+PAGESIZE)-1;
				
				// mId 받아서 dao 호출
				String mId = request.getParameter("mId");
				QnaDao dao = QnaDao.getInstance();
				request.setAttribute("qnaListMember", dao.qnaListMember(mId, startRow, endRow));
				
				// 페이징
				// pageCnt, startPage, endPage, BLOCKSIZE, pageNum(currentPage) 저ㅋ장ㅋ
				int totCnt = dao.qnaTotCntMember(mId);
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
