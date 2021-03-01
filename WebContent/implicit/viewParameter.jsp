<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="java.util.Enumeration" %>
    <%@ page import="java.util.Map" %>
    <%
    	request.setCharacterEncoding("utf-8");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>request.getParameter() 메소드 사용</h2>
	name 파라미터 = <%= request.getParameter("name") %><br>
	address 파라미터 = <%= request.getParameter("address") %><br>
	<br>
	<h2>request.getParameterValues() 메소드 사용 </h2>
	<%
		String [] values = request.getParameterValues("pet");
		if(values != null){
			for(int i = 0 ; i<values.length; i++){%>
			
			<%= values[i] %>
		<%}
		}%>
	<h2>request.getParameterNames() 메소드 사용 </h2>
	<%
		Enumeration enumData = request.getParameterNames();
		while(enumData.hasMoreElements()){
			String name = (String)enumData.nextElement();%>
			
			<%= name %>
	<%}%>	
	
	<h2>request.getParameter.Map() 메소드 사용</h2>
	<%
		Map parameterMap = request.getParameterMap();
		String [] nameParam = (String[]) parameterMap.get("name");
		if(nameParam != null){%>
			name = <%= nameParam[0] %>
		<%}%>
</body>
</html>