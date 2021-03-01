<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>옵션 선택 화면</title>
</head>
<body>
<%
/*
액션 태그는 7개 
page를 활용 
1) 페이지 이동하기
<jsp:forward page="이동할페이지">
	<jsp:param name="변수명" value="값"/>
</jsp:forward>

request.getContextPath() -> myWeb 까지 
*/
%>
<!-- http://localhost:8080/myWeb/forward/view.jsp?code=B -->
<form action="<%= request.getContextPath() %>/forward/view.jsp">
	보고싶은 페이지 선택 : 
	<select name="code">
		<option value="A">A페이지 </option>
		<option value="B">B페이지 </option>
		<option value="C">C페이지 </option>
	</select>
	<input type="submit" value="이동">
</form>
</body>
</html>