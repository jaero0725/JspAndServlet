<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int bufferSize = out.getBufferSize();
	int remainSize = out.getRemaining();
	int usedSize = bufferSize - remainSize;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
버퍼 전체 크기 : <%= bufferSize %><br>
사용한 버퍼 크기 : <%= usedSize %><br>
남은 버퍼 : <% out.println(remainSize); %><br>

</body>
</html>