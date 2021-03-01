<%@page import="boardone.BoardVO"%>
<%@page import="boardone.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="view/color.jsp" %>
<%
	int num =Integer.parseInt(request.getParameter("num"));
	String pageNum =request.getParameter("pageNum");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src=""></script>
<title>게시판</title>
<script type="text/javascript">

</script>
<body bgcolor="<%=bodyback_c%>">
	<center><b>글삭제</b></center><br>
	<form method="post" name="delForm" action="deleteProc.jsp" onsubmit="return deleteSave()">
		<table border="1" align="center" width="360">
			<tr height="30">
				<td align=center bgcolor="<%= value_c%>">비밀번호를 입력하세요</td>
			</tr>
			<tr height="30">
				<td align=center >비밀번호 :
					<input type="password" name="pass" size="8" maxlength="12"/>
					<input type="hidden" name="num" value="<%=num%>"/>
				 </td>
			</tr>
			<tr height="30">
				<td align=center bgcolor="<%= value_c%>">비밀번호 :
					<input type="submit" value="글삭제"/>
					<input type="button" value="글목록" onClick="document.location.href='list.jsp?pageNum=<%=pageNum%>'"/>
				 </td>
			</tr>
		</table>
	</form>
</body>
</html>