package com.kitri.member.controller;

import javax.servlet.http.*;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.member.model.service.MemberServiceImpl;

public class MemberController {
	
	private static MemberController memberController;
	
	static {
		memberController = new MemberController();
	}
	
	private MemberController() {}
	
	public static MemberController getMemberController() {
		return memberController;
	}
	
	protected String register(HttpServletRequest request, HttpServletResponse response) {
		String path = "/index.jsp";
		MemberDetailDto memberdetailDto = new MemberDetailDto();
		memberdetailDto.setName(request.getParameter("name"));
		memberdetailDto.setId(request.getParameter("id"));
		memberdetailDto.setPass(request.getParameter("pass"));
		memberdetailDto.setEmailid(request.getParameter("emailid"));
		memberdetailDto.setEmaildomain(request.getParameter("emaildomain"));
		memberdetailDto.setTel1(request.getParameter("tel1"));
		memberdetailDto.setTel2(request.getParameter("tel2"));
		memberdetailDto.setTel3(request.getParameter("tel3"));
		memberdetailDto.setZipcode(request.getParameter("zipcode"));
		memberdetailDto.setAddress(request.getParameter("address"));
		memberdetailDto.setAddressdetail(request.getParameter("address_detail"));
		
		int cnt = MemberServiceImpl.getMemberSerivce().registerMember(memberdetailDto);
		
		if (cnt != 0) {
			request.setAttribute("userInfo", memberdetailDto);
			path = "/user/member/registerok.jsp";
		} else {
			path = "/user/member/registerfail.jsp";
		}
		
		return path;
	}

	public String login(HttpServletRequest request, HttpServletResponse response) {
		String path = "/index.jsp";
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		MemberDto memberDto = MemberServiceImpl.getMemberSerivce().loginMember(id, pass);
		if(memberDto != null) {
//			//////////////////////////////Cookie/////////////////////
			String idsv = request.getParameter("idsave");
			if("idsave".equals(idsv)) {
			Cookie cookie = new Cookie("kid_info", id);
			cookie.setDomain("localhost");
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(60*60*24*365*50);
			response.addCookie(cookie);
			} else {
				Cookie cookie[] = request.getCookies();
				
				if(cookie != null){
					for(Cookie c : cookie){
						if("kid_inf".equals(c.getName())) {
							c.setDomain("localhost");
							c.setPath(request.getContextPath());
							c.setMaxAge(0);
							response.addCookie(c);
							break;
						}
					}
				}
				
			}
//		//////////////////////////////////////////////////////////			
//		///////////////////////////Session////////////////////////
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", memberDto);
			path = "/user/login/loginok.jsp";
		} else {
			path = "/user/login/loginfail.jsp";
		}
		return path;
	}

	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
//		session.setAttribute("userInfo", null);
//		session.removeAttribute("userInfo");
		session.invalidate();
		return "/user/login/login.jsp";
	}

}