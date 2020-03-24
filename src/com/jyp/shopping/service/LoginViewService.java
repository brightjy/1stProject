package com.jyp.shopping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String referer = (String)request.getHeader("REFERER");	
		HttpSession session = request.getSession();
		session.setAttribute("referer", referer);

	}

}
