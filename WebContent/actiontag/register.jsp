<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
회원가입 <br>
<form method="post" action="add.jsp">
	<table>
		<tr>
			<td>이름 </td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td>이메일 </td>
			<td><input type="text" name="email"></td>
		</tr>
		<tr>
			<td>전화</td>
			<td><input type="text" name="phone"></td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="가입"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>