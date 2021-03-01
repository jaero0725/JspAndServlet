<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<% request.setCharacterEncoding("utf-8"); %>
    <%@page import="memberone.StudentDAO"%>
    <%
    StudentDAO dao = StudentDAO.getInstance();
    %>
    <jsp:useBean id="vo" class="memberone.StudentVO"/>
    <jsp:setProperty name="vo" property="*"/>
      <%
    	boolean flag = dao.memberInsert(vo);
  	  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	if(flag){
		out.println("<b>회원가입을 환영 드립니다. </b><br>");
		out.println("<a href=login.jsp>로그인</a>");
	} else{
		out.println("<b>다시 입력하여 주십시오.</b><br>");
		out.println("<a href=regForm.jsp> 다시 가입</a>");
	}
%>
</body>
</html>