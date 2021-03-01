<%@page import="boardone.BoardVO"%>
<%@page import="boardone.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="view/color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src=""></script>
<title>게시판</title>
</head>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum =request.getParameter("pageNum");
	try{
		BoardDAO dbPro = BoardDAO.getInstance();
		BoardVO article = dbPro.getArticle(num); //getArticle을 쓴다. %>
	<body bgcolor="<%= bodyback_c%>">	
		<div align="center">
			<b>글수정</b><br>
			<form method="post" name="writeform" action="updateProc.jsp?pageNum=<%=pageNum %>" onSubmit="return writeSave()">
				<table width="400" border="1" bgcolor="<%=bodyback_c%>  align="center">
				<tr>
					<td width="70" bgcolor="<%= value_c %>" align="center">이름</td>
					<td align="left" width="330">
						<input type="text" size="10" maxlength="10" name="writer" value="<%= article.getWriter() %>"/>
						<input type="hidden" name="num" value="<%=article.getNum() %>"/>
					</td>
				</tr>
				<tr>
					<td width="70" bgcolor="<%= value_c %>" align="center">제목</td>
					<td align="left" width="330">
						<input type="text" size="40" maxlength="30" name="subject" value="<%= article.getSubject() %>"/>
						<input type="hidden" name="num" value="<%=article.getSubject() %>"/>
					</td>
				</tr>
				<tr>
					<td width="70" bgcolor="<%= value_c %>" align="center">이메일</td>
					<td align="left" width="330">
							<input type="text" size="40" maxlength="30" name="email" value="<%= article.getEmail() %>"/>
						<input type="hidden" name="num" value="<%=article.getEmail() %>"/>
					</td>
				</tr>
					<tr>
					<td width="70" bgcolor="<%= value_c %>" align="center">내용</td>
					<td align="left" width="330">
						<textarea rows="13" cols="40" name="content"><%=article.getContent() %></textarea>
					</td>
				</tr>
				<tr>
					<td width="70" bgcolor="<%= value_c %>" align="center">비밀번호</td>
					<td align="left" width="330">
						<input type="text" size="10" maxlength="10" name="pass" value="<%= article.getPass() %>"/>
						<input type="hidden" name="num" value="<%=article.getPass() %>"/>
					</td>
				</tr>
				<tr>
					<td colspan=2  bgcolor="<%= value_c %>" align="center">
						<input type="submit" value="글수정"/>
						<input type="reset" value="다시작성"/>
						<input type="button" value="목록보기" onClick="document.location.href='list.jsp?pageNum=<%=pageNum %>'"/>
					</td>
				</tr>
				</table>
			</form>
		</div>
	</body>
	<%} catch(Exception e) {} %>
</html>