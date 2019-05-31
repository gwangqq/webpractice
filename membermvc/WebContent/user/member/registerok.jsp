<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "com.kitri.member.model.MemberDetailDto"%>
<%@ include file="/template/header.jsp" %>
<%
MemberDetailDto memberdetailDto = (MemberDetailDto)request.getAttribute("userInfo");
%>

<%=memberdetailDto.getName() %>님 회원가입을 환영합니다.
로그인 후  모든 서비스를 이용할 수 있습니다.<br>
가입하신 정보는 아래와 같습니다.<br>
이름 : <%=memberdetailDto.getName()%><br>
아이디 : <%=memberdetailDto.getId()%><br>
이메일 : <%=memberdetailDto.getEmailid()%>@<%=memberdetailDto.getEmaildomain() %><br>
전화번호 : <%=memberdetailDto.getTel1()%>-<%=memberdetailDto.getTel2()%>-<%=memberdetailDto.getTel3()%><br>
주소 : <%=memberdetailDto.getZipcode() %> <%=memberdetailDto.getAddress() %> <%= memberdetailDto.getAddressdetail() %><br>
<a href = "<%=root%>/user/login/login.jsp">로그인</a>
<%@ include file="/template/footer.jsp" %>