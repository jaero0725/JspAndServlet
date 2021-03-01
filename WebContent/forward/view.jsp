<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String code =request.getParameter("code");
	String viewPageURI = null;
	
	if(code.equals("A")){
		viewPageURI = "viewModule/a.jsp";
	} else if(code.equals("B")){
		viewPageURI = "viewModule/b.jsp";
	} else if(code.equals("C")){
		viewPageURI = "viewModule/c.jsp";
	}
%>
<!-- forward 시키는 것 .
url은 안바뀐다는점. 데이터를 가지고가지만. -->
<jsp:forward page="<%=viewPageURI %>"/>