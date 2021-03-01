<%@page import="java.sql.*"%>
<%@page import="jdbc.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String id = "", passwd = "",  name="", mem_num1 ="", mem_num2 ="", 
    	   e_mail ="", phone="",zipcode="", address="",job ="";
    int counter = 0;
    try{
    	conn = pool.getConnection();
    	stmt = conn.createStatement();
    	rs = stmt.executeQuery("select * from tempmember");
    	System.out.println("연결됨");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp에서 DB 커넥션 풀 사용.</title>
</head>
<body>
<h2>jdbc 커넥션</h2><br>
<h3>회원정보</h3>
<table border="1">
	<tr>
		<td> id </td>
		<td> passwd </td>
		<td> name </td>
		<td> mem_num1 </td>
		<td> mem_num2 </td>
		<td> email</td>
		<td> phone </td>
		<td> zipcode </td>
		<td> address </td>
		<td>job</td>
	</tr>
	
	<%
		if(rs!=null){
			while(rs.next()){
				id =rs.getString("id");
				passwd = rs.getString("passwd");
				name = rs.getString("name");
				mem_num1 = rs.getString("mem_num1");
				mem_num2 = rs.getString("mem_num2");
				e_mail = rs.getString("e_mail");
				phone = rs.getString("phone");
				zipcode = rs.getString("zipcode");
				address = rs.getString("address");
				job = rs.getString("job");
	%>
	<tr>
		<td> <%= id%> </td>
		<td> <%=passwd%> </td>
		<td> <%=name%></td>
		<td> <%=mem_num1 %></td>
		<td> <%=mem_num2 %></td>
		<td> <%=e_mail%></td>
		<td><%= phone %></td>
		<td> <%=zipcode%> </td>
		<td> <%=address %></td>
		<td><%=job%></td>
	</tr>
	<%
		counter++;
	  	  }
		}
	%>
	</table><br><br>
	total records : <%= counter %>
	<%
	    }catch(Exception e){
	    	System.out.println(e);
	    }finally{
	    	if(rs != null) try{ rs.close();} catch(SQLException e){}
	    	if(stmt != null) try{ stmt.close();} catch(SQLException e){}
	    	if(conn != null) try{ pool.releaseConnection(conn);} catch(SQLException e){}
	    }
	%>
</body>
</html>