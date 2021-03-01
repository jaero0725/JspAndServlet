
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<%@page import="memberone.StudentVO"%>
<%@page import="memberone.StudentDAO"%>
<%StudentDAO dao = StudentDAO.getInstance();%>
<jsp:useBean id="vo" class="memberone.StudentVO"/>
    <jsp:setProperty name="vo" property="*"/>
<%
	String loginID = (String) session.getAttribute("loginID");
	vo.setId(loginID);
	dao.updateMember(vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
	var setTime = 3;
	function timer(){
		m = setTime + "초 후에 로그인 페이지로 이동합니다.";	// 남은 시간 계산
		var msg = m;
		document.all.time.innerHTML = msg;		
		setTime--;				
		if (setTime < 0) {			
			clearInterval(tid);	
			location.href="login.jsp";
		}
	}
	function timerStart(){ 
		tid = setInterval('timer()',1000) 
	};
</script>
<title>회원 수정 완료</title>
</head>
<body onload="timerStart()">
	<font size="5" >
		회원정보가 수정되었습니다.<br><br>
		<b id="time"></b>
	</font>
</body>
</html>