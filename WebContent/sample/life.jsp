<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%!
    	int numOne = 0;
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int numTwo = 0;
		numOne++; //새로고침할때마다 증가
		numTwo++; //새로고침할때마다 초기화 
	%>
	
	<li> 전역변수 딜클레이션: <%= numOne %></li>
	<li> 지역변수 스클립트릿: <%= numTwo %></li>
	
	<table border=1px color=black>
		<caption>구구단</caption>
		<tr>
		<% for(int i = 1 ; i<=9; i++){ %>
			<td> <%= i%> 단</td>
		<%}%>
		</tr>
		<% for(int i = 1 ; i<=9; i++){ %>
		<tr>
		<%for(int j = 1; j<=9; j++){%>
			<td><%= j %> X <%= i %> = <%= i*j %>&nbsp;&nbsp;</td>
		<% } %>
		</tr>
		<%}%>
	</table>
</body>
</html>