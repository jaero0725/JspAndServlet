<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="java.util.Enumeration" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	초기화 파라미터 목록
	<ul>
	<%
		Enumeration enumData = application.getInitParameterNames();
		while(enumData.hasMoreElements()){
			String initParamName = (String) enumData.nextElement();
	%>
	<li><%= initParamName %> = <%= application.getInitParameter(initParamName) %>
	<%} %>
	</ul>
</body>
</html>