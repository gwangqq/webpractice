package com.kitri.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class MemberRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		super.init(config);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("회원가입하러 왔다......");
//	1. data get(이름, 아이디, 비번, 이메일1, 이메일2, 전번1, 전번2, 전번3, 우편번호, 주소, 상세주소)
		Connection conn = null;
		PreparedStatement pstmt = null;

		request.setCharacterEncoding("UTF-8");
//		2. Logic : 1의 data를 insert

//		insert all - 한번에 여러개 넣을 때 사용
//		insert all 
//			into member (id, name, pass, email, emaildomain, joindate) 
//				values (?, ?, ?, ?, sysdate) 
//			into member_detail (id, zipcode, address, address_detail, tel1, tel2, tel3)
//				values (?,?,?,?,?,?,?)
//		select * from dual; --> 이걸 반드시 해야함!!!

		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String emailid = request.getParameter("emailid");
		String emaildomain = request.getParameter("emaildomain");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String zipcode = request.getParameter("zipcode");
		String address = request.getParameter("address");
		String addressdetail = request.getParameter("address_detail");// 같을 필요 없음
		int cnt = 0;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
			StringBuffer sql = new StringBuffer();
			sql.append("insert all  \n");
			sql.append("      into member(id,name,pass,emailid,emaildomain,joindate) \n");
			sql.append("      values(?, ?, ?, ?, ?, sysdate) \n");
			sql.append("      into member_detail(id,zipcode,address,address_detail,tel1,tel2,tel3) \n");
			sql.append("      values(?, ?, ?, ?, ?, ?, ?) \n");
			sql.append("select * from dual \n");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, id);
			pstmt.setString(++idx, name);
			pstmt.setString(++idx, pass);
			pstmt.setString(++idx, emailid);
			pstmt.setString(++idx, emaildomain);
			pstmt.setString(++idx, id);
			pstmt.setString(++idx, zipcode);
			pstmt.setString(++idx, address);
			pstmt.setString(++idx, addressdetail);
			pstmt.setString(++idx, tel1);
			pstmt.setString(++idx, tel2);
			pstmt.setString(++idx, tel3);
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (pstmt != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


//	3. response page : 2의 결과에 따라.
//	3-1. 실패시 :0, 성공시 :2(insert를 두번 하니깐)
//	!0: 홍길동님 회원가입을 환영합니다.
//	3-2. 0 : 서버 문제로 회원 가입이 실패하였습니다. 다음에 다시 시도하세요.
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("	<body>");
		if(cnt != 0) {
			out.println(name + "님 회원가입을 환영합니다.");
			out.println("로그인 후  모든 서비스를 이용할 수 있습니다.<br>");
			out.println("<a href = \"user/login.html\">로그인</a>");
		} else {
			out.println("<font color = \"red\" size = \"20\">");
			out.println("서버 문제로 회원가입이 실패했습니다.");
			out.println("다음에 시도해주세요");
			out.println("</font>");
		}
		out.println("	</body>");
		out.println("</html>");
	}

}
