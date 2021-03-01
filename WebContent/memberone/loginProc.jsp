<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("utf-8"); %>
  	<%@page import="memberone.StudentDAO"%>
    <%
    StudentDAO dao = StudentDAO.getInstance();
    %>
    <%
    	String id = request.getParameter("id");
    	String pass = request.getParameter("pass");	
    	int check = dao.loginCheck(id, pass);
    %>
  		<%
    	//1) 로그인 성공한 경우  - 1
    	if(check == 1) {
    		session.setAttribute("loginID", id); //세션을 생성한다.
    		response.sendRedirect("login.jsp");
    	} 	
    	//2) 아이디는 있지만, 비밀번호가 틀린 경우   - 0
    	else if(check == 0){ %>
    		<script>
    			alert("비밀번호가 틀렸습니다.");
    			history.go(-1);
    		</script>
    	<% } 
    	//3) 아이디 자체가 존재 하지 않는 경우  - -1
    	else{ %>
    		<script>
    			alert("아이디가 존재하지 않습니다.");
    			history.go(-1);
    		</script>
   		<%}%>
