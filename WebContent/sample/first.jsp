<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
	//스크립트 릿  - 연산 처리 지역변수 선언 가능
	String scriptlet = " 스크립트릿 입니다.";
	String comment = "주석문입니다.";
	out.println("내장 객체를 이용한 출력 :" + declation + "<br><br>");
	%>
	
	선언문 출력하기 (변수) : <%= declation %> <br><br>
	선언문 출력하기 (메서드) : <%= declationMethod() %> <br><br>
	선언문 출력하기 (변수) : <%= scriptlet %> <br><br>

	<!-- JSP에서사용하는 HTML 주석부분 -->
	<!--  HTML 주석 : <%= comment %> --><br><br>
	<%-- jsp 주석문. --%><br><br>
	
	<%!
		String declation = "선언문입니다.";
		public String declationMethod(){
			return declation;
		}
	%>
	
</body>
</html>