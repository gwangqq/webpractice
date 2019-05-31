package com.kitri.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MoveUrl {

	public static void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath()+path);
	}
//	redirect는 location 어디는지 갈 수 있다. 네이버, 다음 등등 | forward는 path, 프로젝트 내에서만 움직일 수 있다.
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
}
