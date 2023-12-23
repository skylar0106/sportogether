<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
.button {
	width: 286px;
	height: 27px;
	position: absolute;
	background: #8AD6D9;
	border-radius: 10px;
	text-align: center;
	color: white;
	font-size: 18px;
	font-family: Inter;
	font-weight: 700;
	word-wrap: break-word;
	border: none;
}
</style>
<title>login</title>
<script>
function login() {
	if (form.userId.value == "") {
		alert("사용자 ID를 입력하십시오.");
		form.userId.focus();
		return false;
	} 
	if (form.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.password.focus();
		return false;
	}		
	

	form.submit();
}

function userRegister(targetUri) {
	form.action = targetUri;
	form.method="GET";		// register form 요청
	form.submit();
}

</script>
</head>
<body>
<!-- login form  -->
<form name="form" method="POST" action="<c:url value='/user/login'/>">
	<div
		style="width: 100%; height: 100%; position: relative; background: white">
		<div
			style="width: 1280px; height: 185px; left: 0px; top: 0px; position: absolute">
 		<!-- <img
				style="width: 1280px; height: 185px; left: 0px; top: 0px; position: absolute"
				src="Rectangle20.png" /> -->
			<div style="left: 14px; top: 16px; position: absolute">
				<span
					style="color: #555B65; font-size: 35px; font-family: Inter; font-weight: 600; word-wrap: break-word">sport</span><span
					style="color: #1C437C; font-size: 35px; font-family: Inter; font-weight: 600; word-wrap: break-word">ogether</span>
			</div>
			<div
				style="left: 14px; top: 103px; position: absolute; color: #555B65; font-size: 48px; font-family: Inter; font-weight: 600; word-wrap: break-word">HAVING
				FUN</div>
		</div>

		<div
			style="left: 22px; top: 206px; position: absolute; color: #7D7C7C; font-size: 20px; font-family: Inter; font-weight: 100; word-wrap: break-word">로그인</div>
		<div
			style="width: 1260px; height: 0px; left: 9px; top: 247px; position: absolute; border: 1px black solid"></div>

		<div
			style="left: 505px; top: 366px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">회원
			ID</div>
		<input type="text"
			style="width: 219px; height: 23px; left: 563px; top: 363px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name="userId">


		<div
			style="left: 496px; top: 415px; position: absolute; color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">비밀번호</div>
		<input type="password"
			style="width: 219px; height: 23px; left: 563px; top: 412px; position: absolute; background: rgba(217, 217, 217, 0); border-radius: 10px; border: 1px #7D7C7C solid" name="password">


		<div style="left: 496px; top: 461px; position: absolute">
			<input type = "button" class="button" value = "로그인" onClick="login()">
		</div>

		


		<div style="left: 548px; top: 558px; position: absolute">
			<span style="color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 100; word-wrap: break-word">회원이아니신가요? </span>
			<span>
			<input type = "button" style="color: #7D7C7C; font-size: 15px; font-family: Inter; font-weight: 900; word-wrap: break-word; border : 0; background-color: transparent" value = "회원가입" onClick = "userRegister('<c:url value='/user/register'/>')">
			</span>
		</div>

		<div
			style="width: 1280px; height: 147px; left: -1px; top: 685px; position: absolute; background: rgba(0, 0, 0, 0.30)"></div>
		<div
			style="left: 585px; top: 755px; position: absolute; color: white; font-size: 15px; font-family: Inter; font-style: italic; font-weight: 900; word-wrap: break-word">@5VENJURES</div>
	</div>
	</form>
</body>
</html>
