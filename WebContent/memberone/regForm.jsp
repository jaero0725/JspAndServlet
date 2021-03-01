<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="script.js?v=<%=System.currentTimeMillis() %>"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="regProc.jsp" name="regForm" method="post">
		<table>
			<tr>	
				<td>회원가입 정보 입력</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id"/>&nbsp;<input type="button" value="중복확인" onClick="idCheck(this.form.id.value)"></td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td><input type="password" name="pass"/></td>
			</tr>
			<tr>
				<td>패스워드 확인</td>
				<td><input type="password" name="repass"/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>
					<select name="phone1">
						<option value="02">02</option>
						<option value="011">011</option>
						<option value="010">010</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
					</select>
					<input type="text" name="phone2" size="5"/>
					<input type="text" name="phone3" size="5"/>
				</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email"/></td>
			</tr>
			<tr>
				<td>우편번호</td>
				<td><input type="text" name="zipcode"/><input type="button" value ="찾기" onClick="zipCheck()"/></td>
			</tr>
			<tr>
				<td>주소1</td>
				<td><input type="text" name="address1" size="50"/></td>
			</tr>
			<tr>
				<td>주소2</td>
				<td><input type="text" name="address2" size="30"/></td>
			</tr>
			<tr>
				<td>회원가입</td>
				<td>	
					<input type="button" value="회원가입" onClick="inputCheck()"/>
					<input type="reset" value="다시입력"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>