package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jyp.shopping.dao.AdminDao;
import com.jyp.shopping.dto.AdminDto;

public class AdminLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// aId, aPw
		String aId = request.getParameter("aId");
		String aPw = request.getParameter("aPw");
		
		AdminDao dao = AdminDao.getInstance();
		int result = dao.loginAdmin(aId, aPw);
		
		if(result==AdminDao.SUCCESS) {
			AdminDto dto = dao.getAdminDto(aId);
			HttpSession session = request.getSession();
			session.setAttribute("admin", dto);
			request.setAttribute("adminLoginMsg", "관리자 아이디로 로그인하였습니다.");
		}else {
			request.setAttribute("adminLoginMsg", "관리자 아이디로 로그인실패. 관리자에게 문의하세요.");
		}
	}

}
