<%@page import="memberone.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="memberone.StudentDAO"%>
<%
	StudentDAO dao = StudentDAO.getInstance();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="script.js?v=<%=System.currentTimeMillis()%>"></script>
<title>수정페이지</title>
</head>
<%
	String loginID = (String) session.getAttribute("loginID");
	StudentVO vo = dao.getMember(loginID);
%>
<body>
<form action="modifyProc.jsp" name="regForm" method="post">
		<table>
			<tr>	
				<td>회원가입 수정 정보 입력</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><%= vo.getId() %></td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td><input type="password" name="pass" value="<%= vo.getPass()%>"/></td>
			</tr>
			<tr>
				<td>패스워드 확인</td>
				<td><input type="password" name="repass" value="<%= vo.getPass()%>"/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><%= vo.getName()%></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>
					<input type="text" name="phone1" size="5" value="<%= vo.getPhone1()%>"/>
					<input type="text" name="phone2" size="5"  value="<%= vo.getPhone2()%>"/>
					<input type="text" name="phone3" size="5"  value="<%= vo.getPhone3()%>"/>
				</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" value="<%= vo.getEmail()%>"/></td>
			</tr>
			<tr>
				<td>우편번호</td>
				<td>
				<input type="text" name="zipcode" value="<%= vo.getZipcode() %>"/>
				<input type="button" value ="찾기" onClick="zipCheck()"/>
				</td>
			</tr>
			<tr>
				<td>주소1</td>
				<td><input type="text" name="address1" size="50" value="<%= vo.getAddress1()%>"/></td>
			</tr>
			<tr>
				<td>주소2</td>
				<td><input type="text" name="address2" size="30" value="<%= vo.getAddress2()%>"/></td>
			</tr>
			<tr>
				<td>회원가입</td>
				<td>	
					<input type="button" value="정보수정" onClick="updateCheck()"/>&nbsp;&nbsp;
					<input type="button" value="취소" onClick="javascript:window.location='login.jsp'"/>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>