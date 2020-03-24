package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.ProductDao;
import com.jyp.shopping.dao.ReviewDao;
import com.jyp.shopping.dto.ProductDto;
import com.jyp.shopping.dto.ReviewDto;

public class ReviewReplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String aId = request.getParameter("aId");
		int rId = Integer.parseInt(request.getParameter("rId"));
		int rGroup = Integer.parseInt(request.getParameter("rGroup"));
		int rStep = Integer.parseInt(request.getParameter("rStep"));
		int rIndent = Integer.parseInt(request.getParameter("rIndent"));
		int oId = Integer.parseInt(request.getParameter("oId"));
		int pId = Integer.parseInt(request.getParameter("pId"));
		String rPw = request.getParameter("rPw");
		String rTitle = request.getParameter("rTitle");
		String rContent = request.getParameter("rContent");		
		ProductDao pDao = ProductDao.getInstance();
		ProductDto pDto = pDao.productContent(pId);
		String pageNum = request.getParameter("pageNum");
		String rIp = request.getRemoteAddr();
		
		ReviewDao dao = ReviewDao.getInstance();
		ReviewDto dto = new ReviewDto(rId, oId, pId, null, aId, rPw, rTitle, rContent, null, null, null, 0, rGroup, rStep, rIndent, rIp, pDto.getpName(), pDto.getpImage1());
		int result = dao.reviewReply(dto);
		if(result==ReviewDao.SUCCESS) {
			request.setAttribute("reviewMsg","답변 글쓰기 성공" );
			request.setAttribute("pageNum", pageNum);
		}else {
			request.setAttribute("reviewMsg","답변 글쓰기 실패" );
		}
	}

}
