'use strict';

function MM00regist(){
	location.href='/MemberInformation/views/regist.jsp';
}

function MM00update(){
	location.href='/MemberInformation/views/update.jsp';
}

function MM00delete(){
	location.href='/MemberInformation/views/delete.jsp';
}

function MM01reset() {
	document.getElementById('last_name').value='';
	document.getElementById('first_name').value='';
	document.getElementById('birth_year').value='';
	document.getElementById('birth_month').value='';
	document.getElementById('birth_day').value='';
	document.getElementById('phone_number').value='';
	document.getElementById('mail_address').value='';
	document.getElementById('job').value='';
}

function MM01back(){
	window.sessionStorage.clear();
	location.href='/MemberInformation/views/menu.jsp';
}

function MM02back(){
	location.href='/MemberInformation/views/menu.jsp';
}

function MM03back(){
	location.href='/MemberInformation/views/menu.jsp';
}