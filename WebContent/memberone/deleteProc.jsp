
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="memberone.StudentDAO"%>
    <%StudentDAO dao = StudentDAO.getInstance();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
</head>
<body onload="timerStart()">
	<%	
		String id = (String)session.getAttribute("loginID");
		String pass = request.getParameter("pass");
		int check = dao.deleteMember(id,pass);
		
//성공하면  1 
	if(check == 1){
		session.invalidate();
	%>
	<font size="5" >
		회원정보가 삭제되었습니다.<br><br>
		<b id="time"></b>
	</font>
	<%}
	//비밀번호 틀린경우
	else{		
	%>
	<script>
		alert("비밀번호가 맞지 않습니다.");
		history.go(-1);
	</script>
	<%} %>
</body>
</html>