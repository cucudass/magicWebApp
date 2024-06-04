<%@page import="magic.member.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String id = request.getParameter("mem_uid");
		String pwd = request.getParameter("mem_pwd");
		
		MemberDBBean manager = MemberDBBean.getInstance();
		
		int check = manager.userCheck(id, pwd);
		
		MemberBean mb = manager.getMember(id);
		
		if(mb == null) { //회원 정보가 없읍
	%>
		<script>
			alert("존재하지 않는 회원입니다.");
			history.go(-1);
		</script>
	<%
		} else {//회원 있음
			String name = mb.getMem_name();
			if(check == 1) {
				//세션에 사용자 정보 추가
				session.setAttribute("uid", id); //회원 아이디
				session.setAttribute("name", name); //회원 이름
				session.setAttribute("Member", "yes"); //회원 여부
				response.sendRedirect("main.jsp");
			} else if(check == 0) {
	%>
			<script>
				alert("비밀번호가 맞지 않습니다.");
				history.go(-1);
			</script>
	<%
			} else { //아이디 불일치
	%>
			<script>
				alert("아이디가 맞지 않습니다.");
				history.go(-1);
			</script>
	<%
			}
		}
	%>
</body>
</html>