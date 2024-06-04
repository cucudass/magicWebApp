<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("Member") == null) {
%>
		<jsp:forward page="login.jsp"/>
<%
	}
%>
<%
	String uid = (String) session.getAttribute("uid");
	String name = (String) session.getAttribute("name");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" align="center">
		<form method="post" action="logOut.jsp">
			<tr heigth="30">
				<td>
					안녕하세요. <%=name %>(<%=uid %>)님
				</td>
			</tr>
			<tr height="30">
				<td align="center">
					<input type="submit" value="로그아웃">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="수정" onclick="location='memberUpdate.jsp'">
				</td>
			</tr>
		</form>
	</table>
</body>
</html>