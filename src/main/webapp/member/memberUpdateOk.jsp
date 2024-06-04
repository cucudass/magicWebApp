<%@page import="magic.member.MemberDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean class="magic.member.MemberBean" id="member"/>
<jsp:setProperty property="*" name="member"/>
<%
	String uid = (String) session.getAttribute("uid");
	MemberDBBean manager = MemberDBBean.getInstance();
	
	member.setMem_uid(uid);
	
	int re = manager.updateMember(member);
	
	if(re == 1) {
%>
	<script>
		alert("입력하신 대로 회원 정보가 수정되었습니다.");
		location = "main.jsp";
	</script>
<%
	} else {
%>
	<script>
		alert("수정이 실패했습니다.");
		history.back();
	</script>
<%
	}
%>