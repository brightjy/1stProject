package com.jyp.shopping.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.service.AdminLoginService;
import com.jyp.shopping.service.AdminLogoutService;
import com.jyp.shopping.service.CartDeleteService;
import com.jyp.shopping.service.CartInsertService;
import com.jyp.shopping.service.CartListService;
import com.jyp.shopping.service.CartUpdateService;
import com.jyp.shopping.service.MemberDeleteService;
import com.jyp.shopping.service.MemberIdChkService;
import com.jyp.shopping.service.MemberJoinService;
import com.jyp.shopping.service.MemberListService;
import com.jyp.shopping.service.MemberTempPwService;
import com.jyp.shopping.service.MemberLoginService;
import com.jyp.shopping.service.LoginViewService;
import com.jyp.shopping.service.MemberLogoutService;
import com.jyp.shopping.service.MemberModifyService;
import com.jyp.shopping.service.MemberPwChkService;
import com.jyp.shopping.service.OrdersContentService;
import com.jyp.shopping.service.OrdersListAdminService;
import com.jyp.shopping.service.OrdersListService;
import com.jyp.shopping.service.OrdersService;
import com.jyp.shopping.service.OrdersUpdateService;
import com.jyp.shopping.service.OrdersViewService;
import com.jyp.shopping.service.ProductContentService;
import com.jyp.shopping.service.ProductDeleteService;
import com.jyp.shopping.service.ProductInsertService;
import com.jyp.shopping.service.ProductListBestService;
import com.jyp.shopping.service.ProductListService;
import com.jyp.shopping.service.ProductModifyService;
import com.jyp.shopping.service.ProductModifyViewService;
import com.jyp.shopping.service.WishListInsertService;
import com.jyp.shopping.service.WishListService;
import com.jyp.shopping.service.QnaContentService;
import com.jyp.shopping.service.QnaDeleteService;
import com.jyp.shopping.service.QnaListService;
import com.jyp.shopping.service.QnaModifyService;
import com.jyp.shopping.service.QnaModifyViewService;
import com.jyp.shopping.service.QnaReplyService;
import com.jyp.shopping.service.QnaReplyViewService;
import com.jyp.shopping.service.QnaWriteService;
import com.jyp.shopping.service.ReviewContentService;
import com.jyp.shopping.service.ReviewDeleteService;
import com.jyp.shopping.service.ReviewListService;
import com.jyp.shopping.service.ReviewModifyService;
import com.jyp.shopping.service.ReviewModifyViewService;
import com.jyp.shopping.service.ReviewReplyService;
import com.jyp.shopping.service.ReviewReplyViewService;
import com.jyp.shopping.service.ReviewWriteService;
import com.jyp.shopping.service.ReviewWriteViewService;
import com.jyp.shopping.service.Service;
import com.jyp.shopping.service.WishListDeleteService;
import com.jyp.shopping.service.QnaListMemberService;
import com.jyp.shopping.service.ReviewListMemberService;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		Service service = null;
		String viewPage = null;
		
		if(command.equals("/main.do")){
			service = new ProductListBestService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(command.equals("/memberJoinView.do")) {
			viewPage = "member/join.jsp";
		}else if(command.equals("/memberIdChk.do")) { // 아이디 유효성 체크(누가 쓰고 있는 건 아닌지?)
			service = new MemberIdChkService();
			service.execute(request, response);
			viewPage = "member/mIdChk.jsp";
		}else if(command.equals("/memberPwChk.do")) { // 비밀번호 유효성 체크(*비밀번호&비밀번호 확인 값 일치여부는 해당 페이지에서 체크)
			service = new MemberPwChkService();
			service.execute(request, response);
			viewPage = "member/mPwChk.jsp";
		}else if(command.equals("/memberJoin.do")) {
			service = new MemberJoinService();
			service.execute(request, response);
			viewPage = "memberLoginView.do";
		}else if(command.equals("/memberLoginView.do")) {
			service = new LoginViewService(); // 로그인 시 이전 페이지 세션 값 저장용
			service.execute(request, response);
			viewPage = "member/login.jsp";
		}else if(command.equals("/memberTempPwView.do")) {
			viewPage = "member/tempPwView.jsp";
		}else if(command.equals("/memberTempPw.do")) {
			service = new MemberTempPwService();
			service.execute(request, response);
			viewPage = "member/tempPwView.jsp";
		}else if(command.equals("/memberLogin.do")) {
			service = new MemberLoginService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(command.equals("/memberLogout.do")) {
			service = new MemberLogoutService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(command.equals("/productList.do")){
			service = new ProductListService();
			service.execute(request, response);
			viewPage = "product/productList.jsp";
		}else if(command.equals("/productListBest.do")) {
			service = new ProductListBestService();
			service.execute(request, response);
			viewPage = "product/productListBest.jsp";
		}else if(command.equals("/productContent.do")) {
			service = new ProductContentService();
			service.execute(request, response);
			viewPage = "product/productContent.jsp";
		}else if(command.equals("/cartInsert.do")){
			service = new CartInsertService();
			service.execute(request, response);
			viewPage = "productContent.do";
		}else if(command.equals("/wishListInsert.do")) {
			service = new WishListInsertService();
			service.execute(request, response);
			viewPage = "productContent.do";
		}else if(command.equals("/wishList.do")) {
			service = new WishListService();
			service.execute(request, response);
			viewPage = "wishList/wishList.jsp";
		}else if(command.equals("/wishListDelete.do")) {
			service = new WishListDeleteService();
			service.execute(request, response);
			viewPage = "wishList.do";
		}else if(command.equals("/cartList.do")) {
			service = new CartListService();
			service.execute(request, response);
			viewPage = "cart/cartList.jsp";
		}else if(command.equals("/cartUpdate.do")){
			service = new CartUpdateService();
			service.execute(request, response);
			viewPage = "cartList.do";
		}else if(command.equals("/cartDelete.do")){
			service = new CartDeleteService();
			service.execute(request, response);
			viewPage = "cartList.do";		
		}else if(command.equals("/ordersView.do")){
			service = new OrdersViewService();
			service.execute(request, response);
			viewPage = "orders/ordersView.jsp";
		}else if(command.equals("/orders.do")){
			service = new OrdersService();
			service.execute(request, response);
			viewPage = "orders/ordersResult.jsp";
		}else if(command.equals("/myPageView.do")){
			viewPage = "member/myPageView.jsp"; 
		}else if(command.equals("/ordersList.do")){
			service = new OrdersListService();
			service.execute(request, response);
			viewPage = "orders/ordersList.jsp";
		}else if(command.equals("/ordersContent.do")){
			service = new OrdersContentService();
			service.execute(request, response);
			viewPage = "orders/ordersResult.jsp";		
		}else if(command.equals("/reviewWriteView.do")){
			service = new ReviewWriteViewService();
			service.execute(request, response);
			viewPage = "review/reviewWrite.jsp";
		}else if(command.equals("/reviewWrite.do")){
			service = new ReviewWriteService();
			service.execute(request, response);
			viewPage = "reviewList.do";
		}else if(command.equals("/reviewListMember.do")) {
			service = new ReviewListMemberService();
			service.execute(request, response);
			viewPage = "review/reviewListMember.jsp";
		}else if(command.equals("/reviewContent.do")) {
			service = new ReviewContentService();
			service.execute(request, response);
			viewPage = "review/reviewContent.jsp";
		}else if(command.equals("/reviewModifyView.do")){
			service = new ReviewModifyViewService();
			service.execute(request, response);
			viewPage = "review/reivewModify.jsp";			
		}else if(command.equals("/reviewModify.do")){
			service = new ReviewModifyService();
			service.execute(request, response);
			viewPage = "reviewList.do";
		}else if(command.equals("/reviewDelete.do")){
			service = new ReviewDeleteService();
			service.execute(request, response);
			viewPage = "reviewList.do";
		}else if(command.equals("/reviewList.do")) {
			service = new ReviewListService();
			service.execute(request, response);
			viewPage = "review/reviewList.jsp";
		}else if(command.equals("/qnaList.do")){
			service = new QnaListService();
			service.execute(request, response);
			viewPage = "qna/qnaList.jsp";	
		}else if(command.equals("/qnaWriteView.do")){
			viewPage = "qna/qnaWrite.jsp";			
		}else if(command.equals("/qnaWrite.do")){
			service = new QnaWriteService();
			service.execute(request, response);
			viewPage = "qnaList.do";
		}else if(command.equals("/qnaListMember.do")) {
			service = new QnaListMemberService();
			service.execute(request, response);
			viewPage = "qna/qnaListMember.jsp";
		}else if(command.equals("/qnaContent.do")){
			service = new QnaContentService();
			service.execute(request, response);
			viewPage = "qna/qnaContent.jsp";
		}else if(command.equals("/qnaModifyView.do")){
			service = new QnaModifyViewService();
			service.execute(request, response);
			viewPage = "qna/qnaModify.jsp";
		}else if(command.equals("/qnaModify.do")){
			service = new QnaModifyService();
			service.execute(request, response);
			viewPage = "qnaList.do";
		}else if(command.equals("/qnaDelete.do")){
			service = new QnaDeleteService();
			service.execute(request, response);
			viewPage = "qnaList.do";		
		}else if(command.equals("/memberModifyView.do")) {
			viewPage = "member/memberView.jsp";
		}else if(command.equals("/memberModify.do")){
			service = new MemberModifyService();
			service.execute(request, response);
			viewPage = "main.do";		
		}else if(command.equals("/memberDelete.do")) {
			service = new MemberDeleteService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(command.equals("/adminLoginView.do")){
			service = new LoginViewService(); // 로그인 시 이전 페이지 세션 값 저장용
			service.execute(request, response);
			viewPage = "admin/login.jsp";
		}else if(command.equals("/adminLogin.do")){
			service = new AdminLoginService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(command.equals("/adminLogout.do")){
			service = new AdminLogoutService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(command.equals("/productInsertView.do")){
			viewPage = "product/productInsert.jsp";
		}else if(command.equals("/productInsert.do")){
			service = new ProductInsertService();
			service.execute(request, response);
			viewPage = "productList.do";
		}else if(command.equals("/productModifyView.do")){
			service = new ProductModifyViewService();
			service.execute(request, response);
			viewPage = "product/productModify.jsp";
		}else if(command.equals("/productModify.do")){
			service = new ProductModifyService();
			service.execute(request, response);
			viewPage = "productList.do";
		}else if(command.equals("/productDelete.do")){
			service = new ProductDeleteService();
			service.execute(request, response);
			viewPage = "productList.do";
		}else if(command.equals("/ordersListAdmin.do")){
			service = new OrdersListAdminService();
			service.execute(request, response);
			viewPage = "orders/ordersListAdmin.jsp";
		}else if(command.equals("/ordersUpdate.do")){
			service = new OrdersUpdateService();
			service.execute(request, response);
			viewPage = "ordersContent.do";
		}else if(command.equals("/memberList.do")){
			service = new MemberListService();
			service.execute(request, response);
			viewPage = "member/memberList.jsp";
		}else if(command.equals("/memberDelete.do")){
			service = new MemberDeleteService();
			service.execute(request, response);
			viewPage = "member/memberList.jsp";
		}else if(command.equals("/reviewReplyView.do")){
			service = new ReviewReplyViewService();
			service.execute(request, response);
			viewPage = "review/reviewReply.jsp";
		}else if(command.equals("/reviewReply.do")){
			service = new ReviewReplyService();
			service.execute(request, response);
			viewPage = "reviewList.do";
		}else if(command.equals("/qnaReplyView.do")){
			service = new QnaReplyViewService();
			service.execute(request, response);
			viewPage = "qna/qnaReply.jsp";
		}else if(command.equals("/qnaReply.do")){
			service = new QnaReplyService();
			service.execute(request, response);
			viewPage = "qnaList.do";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
