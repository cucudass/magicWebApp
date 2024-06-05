<%@page import="magic.board.BoardDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	int b_id = Integer.parseInt(request.getParameter("b_id"));
	String b_pwd = request.getParameter("b_pwd");
	
	BoardDBBean db = BoardDBBean.getInstance();
	int re = db.deleteBoard(b_id, b_pwd);
	
	if(re == 1) { //비밀번호 일치로 글목록 이동
		response.sendRedirect("list.jsp");
	} else if (re == 0) { //비밀번호 불일치
%>
	<script>
		alert("비밀번호가 맞지 않습니다.");
		history.back();
	</script>
<%
	} else if(re == -1) { //삭제 실패
%>
	<script>
		alert("삭제에 실패했습니다.");
		history.back();
	</script>
<%		
	}
%>
