<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String root = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align = "center">
<h3>servlet을 이용한  회원가입 &amp; 로그인</h3>
<a href = "<%=root%>/user?act=mvjoin">회원가입</a><br>
<!-- 무조건 controller를 거쳐서 간야한다. 바로 jsp 파일로 가는 경우는 없다 -->
<a href = "<%=root%>/user?act=mvlogin">로그인</a><br>
<!-- 쿼리스트링으로 클라이언트가 원하는 요청을 지정한다 -->
</div>
</body>
</html>