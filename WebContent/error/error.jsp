<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ page isErrorPage = "true"  %>
<% response.setStatus(HttpServletResponse.SC_OK); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	에러페이지입니다. <br>
	<%= exception.getClass().getName() %><br>
	<%= exception.getMessage() %>
</body>
</html>