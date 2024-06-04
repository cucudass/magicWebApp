// 유효성 검사(validation check)
function check_ok(){
	// DOM 구조 -> html document
	// 폼 이름에 name 값으로 찾아감
	var mem_uid = document.reg_frm.mem_uid.value;
	var mem_pwd = document.reg_frm.mem_pwd.value;
	var pwd_check = document.reg_frm.pwd_check.value;
	var mem_name = document.reg_frm.mem_name.value;
	var mem_email = document.reg_frm.mem_uid.value;
	
	//if(document.reg_frm.mem_uid.value == "") {
	//if(reg_frm.mem_uid.value == "") {
	//if(reg_frm.mem_uid.length == 0) {
	if(mem_uid == '') {
		alert("아이디를 써주세요.");
		document.reg_frm.mem_uid.focus();
		return;
	}
	
	if(mem_uid.length < 4) {
		alert("아이디는 4글자 이상이어야 합니다.");
		document.reg_frm.mem_uid.focus();
		return;
	}
	
	if(mem_pwd.length > 0) {
		alert("패스워드를 써주세요.");
		document.reg_frm.mem_pwd.focus();
		return;
	}
	
	if(mem_pwd != pwd_check) {
		alert("패스워드가 일치하지 않습니다.");
		document.reg_frm.pwd_check.focus();
		return;
	}
	
	if(mem_name == "") {
		alert("이름을 써주세요.");
		document.reg_frm.mem_name.focus();
		return;
	}
	
	if(mem_email == "") {
		alert("이메일을 써주세요.");
		document.reg_frm.pwd_check.focus();
		return;
	}
	// 폼 이름이 reg_frm 에서 action 속성의 파일을 호출
	reg_frm.submit();
}

function update_check_ok(){
	var mem_pwd = document.upd_frm.mem_pwd.value;
	var pwd_check = document.upd_frm.pwd_check.value;
	var mem_email = document.upd_frm.mem_email.value;
	
	if(mem_pwd.length > 0) {
		alert("패스워드를 써주세요.");
		document.upd_frm.mem_pwd.focus();
		return;
	}
	
	if(mem_pwd != pwd_check) {
		alert("패스워드가 일치하지 않습니다.");
		document.upd_frm.pwd_check.focus();
		return;
	}
	
	if(mem_email == "") {
		alert("이메일을 써주세요.");
		document.upd_frm.pwd_check.focus();
		return;
	}
	upd_frm.submit();
}