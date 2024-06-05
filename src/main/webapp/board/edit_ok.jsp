<%@page import="magic.board.BoardDBBean"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean class="magic.board.BoardBean" id="board"></jsp:useBean>
<jsp:setProperty property="*" name="board"/>

<%
	BoardDBBean db = BoardDBBean.getInstance();
	
	int re = db.editBoard(board);
	
	if(re == 1) { //수정 성공
		response.sendRedirect("list.jsp");
	} else if(re == 0) { //비밀번호 불일치
%>
		<script>
			alert("비밀번호가 맞치않습니다.");
			history.back();
		</script>
<%
	} else if(re == -1) { //숫정 실패
%>
		<script>
			alert("수정에 실패했습니다.");
			history.back();
		</script>
<%
	}
%>