package com.kitri.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.dao.RepBoardDAO;
import com.kitri.dto.RepBoard;
import com.kitri.exception.AddException;
import com.kitri.service.RepBoardService;

@WebServlet("/writeboard")
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	RepBoard repBoard;
	RepBoardService service;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서블릿으로 들어옴");
		request.setCharacterEncoding("UTF-8");
		repBoard = new RepBoard();
		service = new RepBoardService();
		String subject = request.getParameter("subject");
		String writer = request.getParameter("writer");
		String password = request.getParameter("password");
		String contents= request.getParameter("contents");
		
		repBoard.setBoard_subject(subject);
		repBoard.setBoard_writer(writer);
		repBoard.setBoard_password(password);
		repBoard.setBoard_contents(contents);
		
		try {
			service.write(repBoard);
			request.setAttribute("result", "글쓰기 성공");
		} catch (AddException e) {
			e.printStackTrace();
			request.setAttribute("result", "글쓰기 실패");
		}
		
		String path = "/writeboardresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
