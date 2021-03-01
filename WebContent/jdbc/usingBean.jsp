<%@page import="jdbc.TempMemberVO"%>
<%@page import="java.sql.*, java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp에서 DB 연동 첫번째.</title>
</head>
<body>
	<h2>beans 를 사용한 db연동 예제.</h2>
	<br>
	<h3>회원정보</h3>
	<table border="1">
		<tr>
			<td>id</td>
			<td>passwd</td>
			<td>name</td>
			<td>mem_num1</td>
			<td>mem_num2</td>
			<td>email</td>
			<td>phone</td>
			<td>zipcode</td>
			<td>address</td>
			<td>job</td>
		</tr>
		<jsp:useBean id="dao" class="jdbc.tempMemberDAO" scope="page" />

		<%
		Vector <TempMemberVO> vlist =dao.getMemberList();
		int counter = vlist.size();
		for(int i = 0 ; i<vlist.size(); i++){
			TempMemberVO vo = vlist.elementAt(i);	
		%>
		<tr>
			<td><%= vo.getId()%></td>
			<td><%= vo.getPasswd()%></td>
			<td><%= vo.getName()%></td>
			<td><%= vo.getMem_num1() %></td>
			<td><%= vo.getMem_num2() %></td>
			<td><%= vo.getEmail()%></td>
			<td><%=vo.getPhone() %></td>
			<td><%=vo.getZipcode()%></td>
			<td><%=vo.getAddress()%></td>
			<td><%=vo.getJob()%></td>
		</tr>
		<%
	  	  }
		%>
	</table>
	<br>
	<br> total records :<%=counter %>
</body>
</html>