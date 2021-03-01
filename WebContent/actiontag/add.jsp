<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="actiontag.Customer" %>
    <%request.setCharacterEncoding("utf-8"); %>
    <jsp:useBean id="customer" class="actiontag.Customer" scope="page"/>
    <jsp:setProperty name="customer" property="*"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
	<li>이름 : <jsp:getProperty name="customer" property="name"/></li>
	<li>이메일 : <jsp:getProperty name="customer" property="email"/></li>
	<li>전화 : <jsp:getProperty name="customer" property="phone"/></li>
	</ul>
	<ul>
	</ul>
</body>
</html>