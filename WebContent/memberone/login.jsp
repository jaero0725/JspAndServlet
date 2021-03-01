<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//session을 이용하여 session값이 존재할 경우 로그인한 것으로 간주하여 회원들만 봐야할 로그인 화면을 보여준다.
    	String loginID = (String)session.getAttribute("loginID");
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지 </title>
</head>
<body>

<!-- 세션의 유무에 따라 보이는 내용이 다르게 구현한다.  -->
<%
//1. 로그인을 성공하여 세션이 연결된 경우 보이는 화면
if(loginID != null){%>
		<table>
			<tr>
				<td colspan ="3" align ="center"><%= loginID %>님 환영합니다.</td>
			</tr>
			<tr>
				<td><a href= "modifyForm.jsp">정보수정</a> </td>
				<td><a href= "deleteForm.jsp">회원탈퇴</a> </td>
				<td><a href= "logout.jsp">로그아웃</a> </td>				
			</tr>
		</table>
<%}
// 2. 세션이 없을 경우, 첫 로그인 화면 페이지.
else{
%>
	<form action="loginProc.jsp" method="post">
		<table>
			<tr>
				<td>회원 로그인</td>
			</tr>
			<tr>
				<td>아이디 </td>
				<td><input type="text" name="id" size=20></td>				
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pass" size=20></td>				
			</tr>
			<tr>
				<td>
					<input type="submit" value="로그인"/>
					<input type="button" value="회원가입" onClick="javascript:window.location='regForm.jsp'"/>
				</td>
			</tr>
		</table>
	</form>
<%} %>
</body>
</html>