function check_ok(){
	var b_name = document.reg_frm.b_name.value;
	var b_email = document.reg_frm.b_email.value;
	var b_title = document.reg_frm.b_title.value;
	var b_pwd = document.reg_frm.b_pwd.value;
	var b_content = document.reg_frm.b_content.value;
	
	if(b_name == "") {
		alert("이름을 써주세요.");
		document.reg_frm.b_name.focus();
		return;
	}
	
	if(b_email == "") {
		alert("이메일을 써주세요.");
		document.reg_frm.b_email.focus();
		return;
	}
	
	if(b_title == "") {
		alert("제목을 써주세요.");
		document.reg_frm.b_title.focus();
		return;
	}
	
	if(b_pwd == "") {
		alert("암호를 써주세요.");
		document.reg_frm.b_pwd.focus();
		return;
	}
	
	if(b_content == "") {
		alert("암호를 써주세요.");
		document.reg_frm.b_content.focus();
		return;
	}
	
	reg_frm.submit();
}

function delete_ok() {
	var b_pwd = document.del_frm.b_pwd.value;
	if(b_pwd.length  == 0){
		alert("암호를 입력하세요....");
		document.del_frm.b_pwd.focus();
		return
	}
	del_frm.submit();
}

function update_ok() {
	var b_pwd = document.edit_frm.b_pwd.value;
	if(b_pwd.length  == 0){
		alert("암호를 입력하세요....");
		document.edit_frm.b_pwd.focus();
		return
	}
	edit_frm.submit();
}
