<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<table border="1" align="center">
		<form name="reg_frm" method="post" action="registerOk.jsp">
			<tr>
				<td colspan="2" align="center">
					<h1>회원 가입 신청</h1>
					'*' 표시 항목은 필수 입력 항목입니다.
				</td>
			</tr>
			<tr height="30">
				<td width="80">User ID</td>
				<td>
					<input type="text" name="mem_uid" value="" size="20">*
				</td>
			</tr>
			<tr height="30">
				<td width="80">암호</td>
				<td>
					<input type="password" name="mem_pwd" value="" size="20">*
				</td>
			</tr>
			<tr height="30">
				<td width="80">암호 확인</td>
				<td>
					<input type="password" name="pwd_check" value="" size="20">*
				</td>
			</tr>
			<tr height="30">
				<td width="80">이    름</td>
				<td>
					<input type="text" name="mem_name" value="" size="20">*
				</td>
			</tr>
			<tr height="30">
				<td width="80">E-mail</td>
				<td>
					<input type="text" name="mem_email" value="" size="20">*
				</td>
			</tr>
			<tr height="30">
				<td width="80">주소</td>
				<td>
					<input type="text" name="mem_addr" value="" size="40">
				</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<input type="button" value="등록" onclick="javascript:check_ok()">
					<input type="reset" value="다시입력">
				</td>
			</tr>
		</form>
	</table>
</body>
</html>