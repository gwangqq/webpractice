<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String name = getInitParameter("name");
%>    
<%@ include file="/template/header.jsp" %>

<font size = "20" color = "red">
아이디 또는 비밀번호를 확인하세요.<br>
등록되지 않은 아이디거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.
</font>
<%@ include file="/template/footer.jsp" %>