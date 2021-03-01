<%@page import="memberone.ZipCodeVO"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="memberone.StudentDAO"%>
    <%
    StudentDAO dao = StudentDAO.getInstance();
    %>
<%
	request.setCharacterEncoding("utf-8");
	String check = request.getParameter("check");
	String dong = request.getParameter("dong");
	Vector<ZipCodeVO> zipcodeList = dao.zipcodeRead(dong);
	int totalList = zipcodeList.size();
%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="script.js"></script>
<script>
	function dongCheck(){
		if(document.zipForm.dong.value == ""){
			alert("동이름을 입력하세요");
			document.zipForm.dong.focus();
			return;
		}
		document.zipForm.submit();
	}
	function sendAddress(zipcode,sido,gugun,dong,ri,bunji){
		var address = sido + " " +gugun + " " + dong + " " + ri + " " + bunji;
		opener.document.regForm.zipcode.value=zipcode;
		opener.document.regForm.address1.value=address;
		self.close();
	}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="zipCheck.jsp" method="get" name="zipForm">
		<table>
			<tr>
				<td>동이름 입력 : <input type="text" name="dong" type="text">
				<input type="button" value="검색" onClick = "dongCheck()"/>
				</td>
			</tr>
		</table>
		<input type="hidden" name="check" value="n">
	</form>
	
<table>
<%if(check.equals("n")){
	if(zipcodeList.isEmpty()){%>
	<tr><td>검색된 결과가 없습니다.</td></tr>
	<%} else{%>
	<tr><td><br># 검색 후 , 아래 우편번호를 클릭하면 자동으로 입력됩니다.</td></tr>
	<%
		for(int i = 0 ; i <totalList; i++){
			ZipCodeVO vo = zipcodeList.elementAt(i);
			String t1 = vo.getZipcode();
			String t2 = vo.getSido();
			String t3 = vo.getGugun();
			String t4 = vo.getDong();
			String t5 = vo.getRi();
			String t6 = vo.getBunji();
			if(t5 == null) t5 =" ";
			if(t6 == null) t6 =" ";%>
		<tr>
			<td>
				<a href="javascript:sendAddress('<%=t1 %>','<%=t2 %>','<%=t3 %>' ,'<%=t4 %>','<%=t5 %>','<%=t6%>')">
					<%=t1 %>&nbsp;<%=t2 %>&nbsp;<%=t3 %>&nbsp;<%=t4 %>&nbsp;<%=t5 %>&nbsp;<%=t6 %>
				</a><br>
			</td>
		</tr>
		<%} //end for
		}//end else
	}//end first if
%>
		<tr>
				<td>
				<a href="javascript:this.close();">닫기</a>
				</td>
		</tr>
	</table>
</body>
</html>