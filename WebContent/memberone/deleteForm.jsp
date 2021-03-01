<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
<script type="text/javascript">
	function begin(){
		document.myForm.pass.focus();
	}
	function checkIt(){
		if(!document.myForm.pass.value){
			alert("비밀번호를 입력하세요.");
			document.myForm.pass.focus();
			return false;
		}
	}
</script>
</head>
<body onload="begin()">
<form action="deleteProc.jsp" method="post" name="myForm" onsubmit="return checkIt()">
	<table border="1">
		<tr>
			<td colspan ="2"> 회원탈퇴</td>
		</tr>
		<tr>
			<td> 비밀번호 입력</td>
			<td><input type="password" name="pass" size="15"></td>
		</tr>
		<tr>
			<td> 
				<input type="submit" value="회원탈퇴">
				<input type="button" value="취소" onClick="javascript:window.location='login.jsp'">
			</td>
		</tr>
	</table>
</form>
</body>
</html>