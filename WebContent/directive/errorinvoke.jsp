<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page errorPage ="/error/error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
안녕? 
<!-- 일부러 오류내기  -->
name 파라미터 값 : <%= request.getParameter("name").toUpperCase() %>
</body>
</html>