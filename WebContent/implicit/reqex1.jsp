<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	클라이언트 ip : <%= request.getRemoteAddr() %> <br>
	요청 uri : <%= request.getRequestURI() %><br>
	요청 url : <%= request.getRequestURL() %><br>
	컨텍스트 경로 : <%= request.getContextPath() %><br>
</body>
</html>