<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	request.setCharacterEncoding("utf-8");
    %>
    <jsp:useBean id="msg" class="sample.SimpleData"/>
    <jsp:setProperty name="msg" property="message"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
결과 메시지 : <jsp:getProperty name="msg" property="message"/>
</body>
</html>