package com.kitri.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.service.MemberServiceImpl;
import com.kitri.util.MoveUrl;
import com.kitri.util.SiteConstance;
//사용자의 요구를 받고 어떻게 이동시키는 컨트롤러
@WebServlet("/user")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			String act = request.getParameter("act");
//			if(act.equals("mvjoin"){ mvjoin이 null일 때를 고려하지 못해서 null-point-exception이 발생한다
			
			String path = "/index.jsp";
			
			if("mvjoin".equals(act)) {
				MoveUrl.redirect(request, response, "/user/member/member.jsp");
				
			} else if ("mvlogin".equals(act)) {
				MoveUrl.redirect(request, response, "/user/login/login.jsp");
				
			} else if ("idcheck".equals(act)) {
				String sid = request.getParameter("sid");
				String resultXML = MemberServiceImpl.getMemberSerivce().idCheck(sid);
				
				response.setContentType("text/xml;charset=UTF-8"); //text 형식으로 받지만 xml형식으로 내보낸다
				PrintWriter out = response.getWriter();
				out.print(resultXML);
				
			} else if ("zipsearch".equals(act)) {
				String doro = request.getParameter("doro");
//				System.out.println(doro);
				String resultXML = MemberServiceImpl.getMemberSerivce().zipSearch(doro);
//				System.out.println(resultXML);
				response.setContentType("text/xml;charset=UTF-8"); //text 형식으로 받지만 xml형식으로 내보낸다
				PrintWriter out = response.getWriter();
				out.print(resultXML);
			} else if ("register".equals(act)) {
				path = MemberController.getMemberController().register(request, response);
				MoveUrl.forward(request, response, path);//redirect는 가지고 있는 값을 버리고 간다
			} else if ("login".equals(act)) {
				path = MemberController.getMemberController().login(request, response);
				MoveUrl.redirect(request, response, path);
			} else if ("logout".equals(act)) {
				path = MemberController.getMemberController().logout(request,response);
				MoveUrl.redirect(request, response, path);
			} else if ("".equals(act)) {
				
			} else if ("".equals(act)) {
				
			}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(SiteConstance.ENCODE);
		doGet(request, response);
	}

}

